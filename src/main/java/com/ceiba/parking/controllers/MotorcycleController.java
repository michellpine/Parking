package com.ceiba.parking.controllers;

import com.ceiba.parking.domain.Car;
import com.ceiba.parking.domain.Motorcycle;
import com.ceiba.parking.services.ParkingGuardService;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class MotorcycleController {
    private ParkingGuardService parkingGuardService;

    public MotorcycleController(ParkingGuardService parkingGuardService) {
        this.parkingGuardService = parkingGuardService;
    }

    @GetMapping("/api/motorcycles")
    Flux<Motorcycle> listMotorcycles(){
        return parkingGuardService.showMotorcycles();
    }

    @GetMapping("/api/motorcycles/{id}")
    Mono<Motorcycle> getMotorcycleById(@PathVariable String id){
        return parkingGuardService.findMotorcycle(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/motorcycles")
    Mono<Void> createMotorcycle(@RequestBody Publisher<Motorcycle> motoStream){
        return parkingGuardService.saveMotorcycle(motoStream);
    }
}
