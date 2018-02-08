package com.ceiba.parking.services;

import com.ceiba.parking.domain.Car;
import com.ceiba.parking.domain.Motorcycle;
import com.ceiba.parking.domain.ParkingTicket;
import com.ceiba.parking.domain.Vehicle;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ParkingGuardService {
    boolean canEnterVehicle(Vehicle vehicle);
    Mono<ParkingTicket> enterCar(Car vehicle);

    Mono<ParkingTicket> enterMotorcycle(Motorcycle vehicle);
}
