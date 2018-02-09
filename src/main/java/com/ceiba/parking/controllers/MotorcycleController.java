package com.ceiba.parking.controllers;

import com.ceiba.parking.domain.Motorcycle;
import com.ceiba.parking.domain.ParkingTicket;
import com.ceiba.parking.services.ParkingGuardService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class MotorcycleController {
    private ParkingGuardService parkingGuardService;

    public MotorcycleController(ParkingGuardService parkingGuardService) {
        this.parkingGuardService = parkingGuardService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/motorcycles")
    Mono<ParkingTicket> createMotorcycle(@RequestBody Motorcycle moto) {
        return parkingGuardService.enterMotorcycle(moto);
    }

    @PutMapping("/api/motorcycles/{id}")
    Mono<ParkingTicket> outMotorcycle(@PathVariable String id, @RequestBody ParkingTicket parkingTicket){
        ParkingTicket ticketUpdate = parkingGuardService.findRegister(id);
        return parkingGuardService.outVehicle(ticketUpdate);
    }
}
