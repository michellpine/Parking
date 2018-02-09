package com.ceiba.parking.repositories;

import com.ceiba.parking.domain.Car;
import com.ceiba.parking.domain.ParkingTicket;
import com.ceiba.parking.domain.VehicleType;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;

import java.util.List;


public interface ParkingTicketRepository extends ReactiveMongoRepository<ParkingTicket, String> {

    Flux<ParkingTicket> findByCar_isParking(boolean isParking);
    Flux<ParkingTicket> findByMotorcycle_isParking(boolean isParking);
}