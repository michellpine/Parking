package com.ceiba.parking.repositories;

import com.ceiba.parking.domain.ParkingTicket;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ParkingTicketRepository extends ReactiveMongoRepository<ParkingTicket, String> {


}
