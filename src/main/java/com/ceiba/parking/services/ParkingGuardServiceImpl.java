package com.ceiba.parking.services;

import com.ceiba.parking.domain.*;
import com.ceiba.parking.repositories.ParkingTicketRepository;
import exception.ParkingException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;


@Service
public class ParkingGuardServiceImpl implements ParkingGuardService {

    private CalendarGuard calendarGuard;
    private CalculatorParkingGuard calculatorParkingGuard;
    private ParkingTicketRepository parkingTicketRepository;

    static final int CAR_CELLS = 19;
    static final int MOTORCYCLE_CELLS = 9;

    public ParkingGuardServiceImpl(ParkingTicketRepository parkingTicketRepository,
                                   CalendarGuard calendarGuard, CalculatorParkingGuard calculatorParkingGuard) {
        this.parkingTicketRepository = parkingTicketRepository;
        this.calendarGuard          = calendarGuard;
        this.calculatorParkingGuard = calculatorParkingGuard;
    }

    public boolean canEnterVehicle(Vehicle vehicle) {
        if (vehicle.getLicense().toUpperCase().startsWith("A")
                && calendarGuard.getActualWeekDay() != java.util.Calendar.MONDAY
                && calendarGuard.getActualWeekDay() != java.util.Calendar.SUNDAY) {
            throw new ParkingException("Vehicle cannot enter, license begin for A and today is not available day for it");
        }
        return true;
    }

    @Override
    public int howManyCars(){
        List<ParkingTicket> cars = new ArrayList<>();
        parkingTicketRepository.findByVehicle_isParking(true).toIterable().forEach(cars::add);
        return cars.size();
    }

    @Override
    public int howManyMotorcycles(){
        List<ParkingTicket> motorcycles = new ArrayList<>();
        parkingTicketRepository.findByVehicle_isParking(true).toIterable().forEach(motorcycles::add);
        return motorcycles.size();
    }

    @Override
    @Transactional
    public Mono<ParkingTicket> enterVehicle(Vehicle vehicle) {
        if(!canEnterVehicle(vehicle)){
            return null;
        }else if(vehicle.getType().equals(VehicleType.CAR) && howManyCars()>20){
            throw new ParkingException("Vehicle cannot enter, there are not more cells available for cars");
        }else if(vehicle.getType().equals(VehicleType.MOTORCYCLE) && howManyMotorcycles()>10){
            throw new ParkingException("Vehicle cannot enter, there are not more cells available for motorcycles");
        }
        ParkingTicket parkingTicket = new ParkingTicket(vehicle.getLicense(), vehicle.getType(), calendarGuard.getActualDay(), null, 0, 0);
        parkingTicket.addVehicle(vehicle);
        return parkingTicketRepository.save(parkingTicket);
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
        ticket.setDateOut(calendarGuard.getActualDay());
        ticket.setTotalHours(calculatorParkingGuard.getCountHours(calendarGuard.stringToDate(ticket.getDateArrive()), calendarGuard.stringToDate(ticket.getDateOut())));
        ticket.setValueToPay(calculatorParkingGuard.calculateValueToPay(ticket.getTotalHours(), ticket.getVehicleType(), vehicle.getEngine()));
        ticket.getVehicle().setParking(false);
        return parkingTicketRepository.save(ticket);
    }

}
