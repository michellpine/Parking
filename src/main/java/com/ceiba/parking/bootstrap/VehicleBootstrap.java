package com.ceiba.parking.bootstrap;

import com.ceiba.parking.domain.*;
import com.ceiba.parking.repositories.ParkingTicketRepository;
import com.ceiba.parking.services.CalculatorParkingGuard;
import com.ceiba.parking.services.CalendarGuard;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class VehicleBootstrap implements CommandLineRunner{

    private final ParkingTicketRepository parkingTicketRepository;
    private final CalendarGuard calendarGuard;

    public VehicleBootstrap(ParkingTicketRepository parkingTicketRepository,
                            CalendarGuard calendarGuard,
                            CalculatorParkingGuard calculatorParkingGuard ) {
        this.parkingTicketRepository = parkingTicketRepository;
        this.calendarGuard = calendarGuard;
    }

    @Override
    public void run(String... args) throws Exception {
        System.err.println("#### LOADING DATA ON BOOTSTRAP #####");
        if(parkingTicketRepository.count().block() == 0){
            Car car = new Car("SED12", VehicleType.CAR, true);
            ParkingTicket parkingTicketCar =
                    new ParkingTicket(car.getLicense(), car.getType(), calendarGuard.getActualDay(),  calendarGuard.getActualDay(), 0, 5000);
            parkingTicketCar.addCar(car);
            parkingTicketRepository.save(parkingTicketCar).block();
        }
    }
}
