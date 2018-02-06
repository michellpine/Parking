package com.ceiba.parking.services;

import com.ceiba.parking.domain.Car;
import com.ceiba.parking.domain.Motorcycle;
import com.ceiba.parking.domain.Vehicle;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ParkingGuardService {
    boolean canEnterVehicle(Vehicle vehicle);
    Mono<Void> saveCar(Publisher<Car> vehicle);
    Mono<Void> saveMotorcycle(Publisher<Motorcycle> vehicle);
    Flux<Car> showCars();
    Mono<Car> findCar(String id);
    Flux<Motorcycle> showMotorcycles();
    void outCar(Car car);
    void outMotorcycle(Motorcycle motor);
}
