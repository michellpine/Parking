package com.ceiba.parking.repositories;

import com.ceiba.parking.domain.ParkingTicket;
import com.ceiba.parking.domain.VehicleType;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.List;


public interface ParkingTicketRepository extends ReactiveMongoRepository<ParkingTicket, String> {

    Flux<ParkingTicket> findByVehicle_isParking(boolean isParking);
    Flux<ParkingTicket> findByVehicleType(VehicleType vehicleType);
}