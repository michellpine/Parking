package com.ceiba.parking.services;

import com.ceiba.parking.domain.*;
import com.ceiba.parking.repositories.ParkingTicketRepository;
import exception.ParkingException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ParkingGuardServiceImpl implements ParkingGuardService {

    private CalendarGuard calendarGuard;
    private CalculatorParkingGuard calculatorParkingGuard;
    private ParkingTicketRepository parkingTicketRepository;

    static final int CARS_CELLS_AVAILABLE = 20;
    static final int MOTORCYCLE_CELLS_AVAILABLE = 10;

    public ParkingGuardServiceImpl(ParkingTicketRepository parkingTicketRepository,
                                   CalendarGuard calendarGuard, CalculatorParkingGuard calculatorParkingGuard) {
        this.parkingTicketRepository = parkingTicketRepository;
        this.calendarGuard          = calendarGuard;
        this.calculatorParkingGuard = calculatorParkingGuard;
    }


    @Override
    @Transactional
    public Mono<ParkingTicket> enterVehicle(Vehicle vehicle) {
        validateEntryConditions(vehicle);
        ParkingTicket parkingTicket = new ParkingTicket(vehicle.getLicense(), vehicle.getType(), calendarGuard.getActualDate(), null, 0, 0);
        parkingTicket.addVehicle(vehicle);

        return parkingTicketRepository.save(parkingTicket);
    }

    @Override
    public void validateEntryConditions(Vehicle vehicle) {
        canEnterVehicle(vehicle);
        if(vehicle.getType().equals(VehicleType.CAR) && howManyVehiclesAreParking(VehicleType.CAR)>= CARS_CELLS_AVAILABLE){
            throw new ParkingException("Vehicle cannot enter, there are not more cells available for cars");
        }
        if(vehicle.getType().equals(VehicleType.MOTORCYCLE) && howManyVehiclesAreParking(VehicleType.MOTORCYCLE)>= MOTORCYCLE_CELLS_AVAILABLE){
            throw new ParkingException("Vehicle cannot enter, there are not more cells available for motorcycles");
        }
    }

    @Override
    public boolean canEnterVehicle(Vehicle vehicle) {
        if (licenseBigintWithA(vehicle)
                && !isAvailableDay()) {
            throw new ParkingException("Vehicle cannot enter, license begin for A and today is not available day for it");
        }
        return true;
    }

    private boolean licenseBigintWithA(Vehicle vehicle) {
        return vehicle.getLicense().toUpperCase().startsWith("A");
    }

    private boolean isAvailableDay() {
        return calendarGuard.getActualWeekDay() == java.util.Calendar.MONDAY
                || calendarGuard.getActualWeekDay() == java.util.Calendar.SUNDAY;
    }

    @Override
    public int howManyVehiclesAreParking(VehicleType vehicleType) {
        List<ParkingTicket> vehiclesParking = new ArrayList<>();
        parkingTicketRepository.findByVehicle_isParking(true).toIterable().forEach(vehiclesParking::add);

        List<ParkingTicket> vehiclesParkingForType = vehiclesParking.stream()
                .filter(line -> line.getVehicleType().equals(vehicleType))
                .collect(Collectors.toList());
        return vehiclesParkingForType.size();
    }

    @Override
    public ParkingTicket findParkingTicket(String id) {
        return parkingTicketRepository.findById(id).block();
    }

    @Override
    public List<ParkingTicket> findParkingVehicles() {
        List<ParkingTicket> vehicles = new ArrayList<>();
        parkingTicketRepository.findByVehicle_isParking(true).toIterable().forEach(vehicles::add);
        return vehicles;
    }

    @Override
    public Mono<ParkingTicket> outVehicle(ParkingTicket ticket, Vehicle vehicle) {
        ticket.setDateOut(calendarGuard.getActualDate());
        ticket.setTotalHours(calculatorParkingGuard.getCountHours(calendarGuard.stringToDate(ticket.getDateArrive()), calendarGuard.stringToDate(ticket.getDateOut())));
        ticket.setValueToPay(calculatorParkingGuard.calculateValueToPay(ticket.getTotalHours(), ticket.getVehicleType(), vehicle.getEngine()));
        ticket.getVehicle().setParking(false);
        return parkingTicketRepository.save(ticket);
    }

}
