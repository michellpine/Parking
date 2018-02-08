package com.ceiba.parking.services;

import com.ceiba.parking.domain.*;
import com.ceiba.parking.repositories.ParkingTicketRepository;
import exception.ParkingException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
public class ParkingGuardServiceImpl implements ParkingGuardService {

    private CalendarGuard calendarGuard;
    private CalculatorParkingGuard calculatorParkingGuard;
    private ParkingTicketRepository parkingTicketRepository;

    private static final int CAR_CELLS = 19;
    private static final int MOTORCYCLE_CELLS = 9;

    public ParkingGuardServiceImpl(ParkingTicketRepository parkingTicketRepository,
                                   CalendarGuard calendarGuard, CalculatorParkingGuard calculatorParkingGuard) {
        this.parkingTicketRepository = parkingTicketRepository;
        this.calendarGuard          = calendarGuard;
        this.calculatorParkingGuard = calculatorParkingGuard;
    }

    @Override
    public boolean canEnterVehicle(Vehicle vehicle) {
        if (vehicle.getLicense().toUpperCase().startsWith("A")
                && calendarGuard.getActualWeekDay() != java.util.Calendar.MONDAY
                && calendarGuard.getActualWeekDay() != java.util.Calendar.SUNDAY) {
            throw new ParkingException("Vehicle cannot enter, license begin for A and today is not available day for it");
        }
        return true;
    }

    /*
    public int howManyCars(Car car){
        return parkingTicketRepository.findCar(car.getType()).size();
    }

    public int howManyMotorcycles(Motorcycle motor){
        return parkingTicketRepository.findMotorcycle(motor.getType()).size();
    }
    */

    @Override
    @Transactional
    public Mono<ParkingTicket> enterCar(Car vehicle) {
        /*if (howManyCars(vehicle) > CAR_CELLS) {
            throw new ParkingException("Vehicle cannot enter, there are not more cells available for cars");
        }*/
        ParkingTicket parkingTicket = new ParkingTicket(vehicle.getLicense(), vehicle.getType(), calendarGuard.getActualDate(), null, 0, 0);
        return parkingTicketRepository.save(parkingTicket);
    }

    @Override
    @Transactional
    public Mono<ParkingTicket> enterMotorcycle(Motorcycle vehicle) {
        /*if (howManyMotorcycles(vehicle) > CAR_CELLS) {
            throw new ParkingException("Vehicle cannot enter, there are not more cells available for motorcycles");
        }*/
        ParkingTicket parkingTicket = new ParkingTicket(vehicle.getLicense(), vehicle.getType(), calendarGuard.getActualDate(), null, 0, 0);
        return parkingTicketRepository.save(parkingTicket);
    }

    /*
    @Override
    public void outMotorcycle(Motorcycle motorcycle) {
        motorcycle.setParking(false);
    }
    */
}
