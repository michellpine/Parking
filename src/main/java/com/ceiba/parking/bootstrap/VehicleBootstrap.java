package com.ceiba.parking.bootstrap;

import com.ceiba.parking.domain.Car;
import com.ceiba.parking.domain.Motorcycle;
import com.ceiba.parking.domain.VehicleType;
import com.ceiba.parking.repositories.CarRepository;
import com.ceiba.parking.repositories.MotorcycleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
        System.out.println("#### LOADING DATA ON BOOTSTRAP #####");
        if(carRepository.count().block() == 0){
            for(int i=0; i<3; i++){
                Car car = new Car("SED12"+i, VehicleType.CAR, true);
                carRepository.save(car).block();
            }

            System.out.println(("carros: "+ carRepository.count().block()));
        }

        if(motorcycleRepository.count().block()==0){
            Motorcycle motor = new Motorcycle("sed123", VehicleType.MOTORCYCLE, 150, true);
            motorcycleRepository.save(motor).block();
            System.out.println(("motos: "+ motorcycleRepository.count().block()));
        }

    }
}
