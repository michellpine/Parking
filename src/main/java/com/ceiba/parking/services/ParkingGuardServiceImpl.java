package com.ceiba.parking.services;

import com.ceiba.parking.domain.*;
import com.ceiba.parking.repositories.ParkingTicketRepository;
import exception.ParkingException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Stream;

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
    @Transactional
    public Mono<ParkingTicket> enterCar(Car vehicle) {
        //throw new ParkingException("Vehicle cannot enter, there are not more cells available for cars");
        ParkingTicket parkingTicket = new ParkingTicket(vehicle.getLicense(), vehicle.getType(), calendarGuard.getActualDay(), null, 0, 0);
        parkingTicket.addCar(vehicle);
        if(!canEnterVehicle(vehicle)) {
            return null;
        }
        return parkingTicketRepository.save(parkingTicket);
    }

    @Override
    @Transactional
    public Mono<ParkingTicket> enterMotorcycle(Motorcycle vehicle) {
        //throw new ParkingException("Vehicle cannot enter, there are not more cells available for cars");
        ParkingTicket parkingTicket = new ParkingTicket(vehicle.getLicense(), vehicle.getType(), calendarGuard.getActualDay(), null, 0, 0);
        parkingTicket.addMotorcycle(vehicle);
        if(!canEnterVehicle(vehicle)) {
            return null;
        }
        return parkingTicketRepository.save(parkingTicket);
    }

    @Override
    public ParkingTicket findRegister(String id) {
        return parkingTicketRepository.findById(id).block();
    }

    @Override
    public Mono<ParkingTicket> outCar(ParkingTicket ticket, Car car) {
        ticket.setDateOut(calendarGuard.getActualDay());
        ticket.setTotalHours(calculatorParkingGuard.getCountHours(calendarGuard.stringToDate(ticket.getDateArrive()), calendarGuard.stringToDate(ticket.getDateOut())));
        ticket.setValueToPay(calculatorParkingGuard.calculateValueToPay(ticket.getTotalHours(), ticket.getVehicleType(), 0));
        ticket.getCar().setParking(false);
        return parkingTicketRepository.save(ticket);
    }

    @Override
    public Mono<ParkingTicket> outMotorcycle(ParkingTicket ticket, Motorcycle motorcycle) {
        ticket.setDateOut(calendarGuard.getActualDay());
        ticket.setTotalHours(calculatorParkingGuard.getCountHours(calendarGuard.stringToDate(ticket.getDateArrive()), calendarGuard.stringToDate(ticket.getDateOut())));
        ticket.setValueToPay(calculatorParkingGuard.calculateValueToPay(ticket.getTotalHours(), ticket.getVehicleType(), motorcycle.getEngine()));
        ticket.getMotorcycle().setParking(false);
        return parkingTicketRepository.save(ticket);
    }

}
