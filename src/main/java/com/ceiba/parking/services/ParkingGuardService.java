package com.ceiba.parking.services;

import com.ceiba.parking.domain.Car;
import com.ceiba.parking.domain.Motorcycle;
import com.ceiba.parking.domain.ParkingTicket;
import com.ceiba.parking.domain.Vehicle;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ParkingGuardService {
    boolean canEnterVehicle(Vehicle vehicle);
    Mono<ParkingTicket> enterCar(Car vehicle);
    Mono<ParkingTicket> enterMotorcycle(Motorcycle vehicle);

    ParkingTicket findRegister(String id);

    Mono<ParkingTicket> outCar(ParkingTicket parkingTicket, Car car);
    Mono<ParkingTicket> outMotorcycle(ParkingTicket parkingTicket, Motorcycle motorcycle);
}
