package com.ceiba.parking.services;

import com.ceiba.parking.domain.*;
import reactor.core.publisher.Mono;

import java.util.List;


public interface ParkingGuardService {
    boolean canEnterVehicle(Vehicle vehicle);
    Mono<ParkingTicket> enterVehicle(Vehicle vehicle);

    int howManyCars();
    int howManyMotorcycles();

    ParkingTicket findParkingTicket(String id);
    List<ParkingTicket> findParkingVehicles();

    Mono<ParkingTicket> outVehicle(ParkingTicket parkingTicket, Vehicle vehicle);


}
