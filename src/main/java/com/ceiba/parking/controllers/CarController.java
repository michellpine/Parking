package com.ceiba.parking.controllers;

import com.ceiba.parking.domain.Car;
import com.ceiba.parking.services.ParkingGuardService;
import org.reactivestreams.Publisher;
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

    @GetMapping("/api/cars")
    Flux<Car> listCars(){
        return parkingGuardService.showCars();
    }

    @GetMapping("/api/cars/{id}")
    Mono<Car> getById(@PathVariable String id){
        return parkingGuardService.findCar(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/cars")
    Mono<Void> createCar(@RequestBody Publisher<Car> carStream){
        return parkingGuardService.saveCar(carStream);
    }
}
