package com.ceiba.parking.repositories;

import com.ceiba.parking.domain.ParkingTicket;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ParkingTicketRepository extends ReactiveMongoRepository<ParkingTicket, String> {

    Flux<ParkingTicket> findByVehicle_isParking(boolean isParking);
}