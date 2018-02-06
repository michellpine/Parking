package com.ceiba.parking.services;

import com.ceiba.parking.domain.Car;
import com.ceiba.parking.domain.Motorcycle;
import com.ceiba.parking.domain.Vehicle;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ParkingGuardService {
    boolean canEnterVehicle(Vehicle vehicle);
    Mono<Car> saveCar(Car vehicle);
    Mono<Motorcycle> saveMotorcycle(Motorcycle vehicle);
    List<Car> showParkingCars(Car car);
    List<Motorcycle> showParkingMotorcycles(Motorcycle moto);
    void outCar(Car car);
    void outMotorcycle(Motorcycle motor);
}
