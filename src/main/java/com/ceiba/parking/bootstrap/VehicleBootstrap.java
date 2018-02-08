package com.ceiba.parking.bootstrap;

import com.ceiba.parking.domain.*;
import com.ceiba.parking.repositories.ParkingTicketRepository;
import com.ceiba.parking.services.CalculatorParkingGuard;
import com.ceiba.parking.services.CalendarGuard;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
        Calendar date  = new GregorianCalendar(2018,1, 8, 17, 56, 38);
        Date dateOut = date.getTime();
        if(parkingTicketRepository.count().block() == 0){
            Car car = new Car("SED12", VehicleType.CAR, true);
            ParkingTicket parkingTicketCar =
                    new ParkingTicket(car.getLicense(), car.getType(), calendarGuard.getActualDate(), dateOut, calculatorParkingGuard.getCountHours(calendarGuard.getActualDate(), dateOut), 5000);
            parkingTicketRepository.save(parkingTicketCar).block();

            Motorcycle motor = new Motorcycle("sed123", VehicleType.MOTORCYCLE, 150, true);
            ParkingTicket parkingTicketMotor =
                    new ParkingTicket(motor.getLicense(), motor.getType(), calendarGuard.getActualDate(), dateOut, calculatorParkingGuard.getCountHours(calendarGuard.getActualDate(), dateOut), 1000);
            parkingTicketRepository.save(parkingTicketMotor).block();
        }
    }
}
