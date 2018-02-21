package com.ceiba.parking.services;

import com.ceiba.parking.domain.*;
import reactor.core.publisher.Mono;

import java.util.List;


public interface ParkingGuardService {

    Mono<ParkingTicket> enterVehicle(Vehicle vehicle);
    void validateEntryConditions(Vehicle vehicle);
    boolean canEnterVehicle(Vehicle vehicle);
    void validateTypeVehicle(Vehicle vehicle);

    int howManyVehiclesAreParking(VehicleType vehicleType);

    ParkingTicket findParkingTicket(String id);
    List<ParkingTicket> findParkingVehicles();

    Mono<ParkingTicket> outVehicle(ParkingTicket parkingTicket, Vehicle vehicle);

}
