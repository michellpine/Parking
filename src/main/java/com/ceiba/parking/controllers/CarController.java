package com.ceiba.parking.controllers;

import com.ceiba.parking.domain.Car;
import com.ceiba.parking.domain.ParkingTicket;
import com.ceiba.parking.services.ParkingGuardService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class CarController {
    private ParkingGuardService parkingGuardService;

    public CarController(ParkingGuardService parkingGuardService) {
        this.parkingGuardService = parkingGuardService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/cars")
    Mono<ParkingTicket> enterCar(@RequestBody Car car){
        return parkingGuardService.enterCar(car);
    }

    @PatchMapping("/api/cars/{id}")
    Mono<ParkingTicket> outCar(@PathVariable String id, @RequestBody Car car){
        ParkingTicket ticketUpdate = parkingGuardService.findParkingTicket(id);
        return parkingGuardService.outCar(ticketUpdate, car);
    }

    @GetMapping("/api/vehicles/")
    List<ParkingTicket> getVehiclesParking(){
        return parkingGuardService.findParkingVehicles();
    }
}
