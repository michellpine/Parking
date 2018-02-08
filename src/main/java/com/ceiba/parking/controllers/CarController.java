package com.ceiba.parking.controllers;

import com.ceiba.parking.domain.Car;
import com.ceiba.parking.domain.ParkingTicket;
import com.ceiba.parking.services.ParkingGuardService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

    @GetMapping("/api/cars")
    Flux<ParkingTicket> list(){
        return parkingGuardService.showAllRegisters();
    }

    @GetMapping("/api/cars/{id}")
    Mono<ParkingTicket> getById(@PathVariable String id){
        return parkingGuardService.findRegister(id);
    }

    @PutMapping("/api/cars/{id}")
    Mono<ParkingTicket> outCar(@PathVariable String id, @RequestBody ParkingTicket parkingTicket){
        ParkingTicket ticket = parkingGuardService.findRegister(id).block();
        return parkingGuardService.outVehicle(ticket);
    }
}
