package com.ceiba.parking.bootstrap;

import com.ceiba.parking.domain.*;
import com.ceiba.parking.repositories.CarRepository;
import com.ceiba.parking.repositories.MotorcycleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Component
public class VehicleBootstrap implements CommandLineRunner{

    private final CarRepository carRepository;
    private final MotorcycleRepository motorcycleRepository;

    public VehicleBootstrap(CarRepository carRepository, MotorcycleRepository motorcycleRepository) {
        this.carRepository = carRepository;
        this.motorcycleRepository = motorcycleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        ParkingTicket parkingTicket = new ParkingTicket();
        parkingTicket.setDateArrive(java.util.Calendar.getInstance().getTime());
        System.err.println("#### LOADING DATA ON BOOTSTRAP #####");
        if(carRepository.count().block() == 0){
            for(int i=0; i<3; i++){
                Car car = new Car("SED12"+i, VehicleType.CAR, true);
                car.addParkingTicket(parkingTicket);
                carRepository.save(car).block();
            }
        }

        if(motorcycleRepository.count().block()==0){
            Motorcycle motor = new Motorcycle("sed123", VehicleType.MOTORCYCLE, 150, true);
            motor.addParkingTicket(parkingTicket);
            motorcycleRepository.save(motor).block();
        }
        ParkingTicket parkingTicket1 = new ParkingTicket();
        Date date1  = Calendar.getInstance().getTime();
        Calendar b  = new GregorianCalendar(2018,1, 7, 17, 56, 38);
        Date date2 = b.getTime();
        //System.err.println(parkingTicket1.chargeParkingTicket(date1, date2));
    }
}
