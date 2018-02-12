package com.ceiba.parking.services;

import com.ceiba.parking.domain.*;
import reactor.core.publisher.Mono;

import java.util.List;


public interface ParkingGuardService {
    boolean canEnterVehicle(Vehicle vehicle);
    Mono<ParkingTicket> enterCar(Car vehicle);
    Mono<ParkingTicket> enterMotorcycle(Motorcycle vehicle);

    int howManyCars();
    int howManyMotorcycles();

    ParkingTicket findParkingTicket(String id);

    Mono<ParkingTicket> outCar(ParkingTicket parkingTicket, Car car);
    Mono<ParkingTicket> outMotorcycle(ParkingTicket parkingTicket, Motorcycle motorcycle);

    List<ParkingTicket> findParkingVehicles();
}
