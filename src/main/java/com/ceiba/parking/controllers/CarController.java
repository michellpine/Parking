package com.ceiba.parking.controllers;

import com.ceiba.parking.domain.Car;
import com.ceiba.parking.repositories.CarRepository;
import com.ceiba.parking.services.ParkingGuardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class CarController {
    private ParkingGuardService parkingGuardService;

    public CarController(ParkingGuardService parkingGuardService) {
        this.parkingGuardService = parkingGuardService;
    }

    @GetMapping("/api/cars")
    Flux<Car> list(){
        return parkingGuardService.showCars();
    }

    @GetMapping("/api/cars/{id}")
    Mono<Car> getById(@PathVariable String id){
        return parkingGuardService.findCar(id);
    }
}
