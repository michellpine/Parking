package com.ceiba.parking.repositories;

import com.ceiba.parking.domain.ParkingTicket;
import com.ceiba.parking.domain.VehicleType;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import java.util.List;

public interface ParkingTicketRepository extends ReactiveMongoRepository<ParkingTicket, String> {

    //List<ParkingTicket> findCar(VehicleType vehicleType);
    //List<ParkingTicket> findMotorcycle(VehicleType vehicleType);

}
