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
    private final CalculatorParkingGuard calculatorParkingGuard;

    public VehicleBootstrap(ParkingTicketRepository parkingTicketRepository,
                            CalendarGuard calendarGuard,
                            CalculatorParkingGuard calculatorParkingGuard ) {
        this.parkingTicketRepository = parkingTicketRepository;
        this.calendarGuard = calendarGuard;
        this.calculatorParkingGuard = calculatorParkingGuard;
    }

    @Override
    public void run(String... args) throws Exception {
        System.err.println("#### LOADING DATA ON BOOTSTRAP #####");
        if(parkingTicketRepository.count().block() == 0){
            Vehicle vehicle = new Vehicle("SED12", VehicleType.CAR, true);
            ParkingTicket parkingTicketCar =
                    new ParkingTicket(vehicle.getLicense(), vehicle.getType(), calendarGuard.getActualDay(),  calendarGuard.getActualDay(), 0, 5000);
            parkingTicketCar.addVehicle(vehicle);
            parkingTicketRepository.save(parkingTicketCar).block();
        }
    }
}
