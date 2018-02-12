package com.ceiba.parking.controllers;


import com.ceiba.parking.domain.ParkingTicket;
import com.ceiba.parking.domain.Vehicle;
import com.ceiba.parking.services.ParkingGuardService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class VehicleController {
    private ParkingGuardService parkingGuardService;

    public VehicleController(ParkingGuardService parkingGuardService) {
        this.parkingGuardService = parkingGuardService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/vehicles")
    Mono<ParkingTicket> enterVehicle(@RequestBody Vehicle vehicle){
        return parkingGuardService.enterVehicle(vehicle);
    }

    @PatchMapping("/api/vehicles/{id}")
    Mono<ParkingTicket> outVehicle(@PathVariable String id, @RequestBody Vehicle vehicle){
        ParkingTicket ticketUpdate = parkingGuardService.findParkingTicket(id);
        return parkingGuardService.outVehicle(ticketUpdate, vehicle);
    }

    @GetMapping("/api/vehicles/")
    List<ParkingTicket> getVehiclesParking(){
        return parkingGuardService.findParkingVehicles();
    }
}
