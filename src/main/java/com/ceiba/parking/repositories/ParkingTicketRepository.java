package com.ceiba.parking.repositories;

import com.ceiba.parking.domain.ParkingTicket;
import com.ceiba.parking.domain.Vehicle;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.logging.StreamHandler;
import java.util.stream.Stream;


public interface ParkingTicketRepository extends ReactiveMongoRepository<ParkingTicket, String> {


}
