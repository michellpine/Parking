package com.ceiba.parking.controllers;

import com.ceiba.parking.domain.Car;
import com.ceiba.parking.domain.VehicleType;
import com.ceiba.parking.repositories.CarRepository;
import com.ceiba.parking.services.ParkingGuardService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.reactivestreams.Publisher;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.ceiba.parking.builder.CarTestDataBuilder.aCar;
import static org.mockito.ArgumentMatchers.any;

public class CarControllerTest {

    CarController carController;
    ParkingGuardService parkingGuardService;
    WebTestClient webTestClient;

    CarRepository carRepository;

    @Before
    public void setUp() throws Exception {
        parkingGuardService = Mockito.mock(ParkingGuardService.class);
        carController = new CarController(parkingGuardService);
        webTestClient = WebTestClient.bindToController(carController).build();

        carRepository = Mockito.mock(CarRepository.class);
    }
/*
    @Test
    public void listCars() {
        Car car = aCar()
                .withLicense("XCD123")
                .withType(VehicleType.CAR)
                .withIsParking(true)
                .build();
        BDDMockito.given(parkingGuardService.showCars())
                .willReturn(Flux.just(car));

        webTestClient.get()
                .uri("/api/cars")
                .exchange()
                .expectBodyList(Car.class)
                .hasSize(1);
    }

    @Test
    public void getById() {
        Car car = aCar()
                .withLicense("XCD123")
                .withType(VehicleType.CAR)
                .withIsParking(true)
                .build();
        BDDMockito.given(parkingGuardService.findCar("someid"))
                .willReturn(Mono.just(car));

        webTestClient.get()
                .uri("/api/cars/someid")
                .exchange()
                .expectBody(Car.class);
    }*/

    @Test
    public void createCar() {
        Car car = new Car();

        BDDMockito.given(carRepository.saveAll(any(Publisher.class)))
                .willReturn(Flux.just(car));

        Car car1 = aCar()
                .withLicense("XCD123")
                .withType(VehicleType.CAR)
                .withIsParking(true)
                .build();

        Mono<Car> carMono = Mono.just(car1);

        webTestClient.post()
                .uri("/api/cars")
                .body(carMono, Car.class)
                .exchange()
                .expectStatus()
                .isCreated();
    }
}