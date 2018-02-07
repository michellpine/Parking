package com.ceiba.parking.services;

import com.ceiba.parking.domain.Car;
import com.ceiba.parking.domain.Motorcycle;
import com.ceiba.parking.domain.Vehicle;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ParkingGuardService {
    boolean canEnterVehicle(Vehicle vehicle);
    Mono<Car> saveCar(Car vehicle);
    void outCar(Car car);

    Mono<Motorcycle> saveMotorcycle(Motorcycle vehicle);
    void outMotorcycle(Motorcycle motor);
}
