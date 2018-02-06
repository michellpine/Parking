package com.ceiba.parking.controllers;

import com.ceiba.parking.domain.Motorcycle;
import com.ceiba.parking.domain.VehicleType;
import com.ceiba.parking.repositories.MotorcycleRepository;
import com.ceiba.parking.services.ParkingGuardService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.reactivestreams.Publisher;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.ceiba.parking.builder.MotorcycleTestDataBuilder.aMotorcycle;
import static org.mockito.ArgumentMatchers.any;

public class MotorcycleControllerTest {

    MotorcycleController motorcycleController;
    ParkingGuardService parkingGuardService;
    WebTestClient webTestClient;

    MotorcycleRepository motorcycleRepository;

    @Before
    public void setUp() throws Exception {
        parkingGuardService = Mockito.mock(ParkingGuardService.class);
        motorcycleController = new MotorcycleController(parkingGuardService);
        webTestClient = WebTestClient.bindToController(motorcycleController).build();

        motorcycleRepository = Mockito.mock(MotorcycleRepository.class);
    }

    @Test
    public void listMotorcycles(){
        Motorcycle motor = aMotorcycle()
                .withLicense("ABC123")
                .withType(VehicleType.MOTORCYCLE)
                .withIsParking(true)
                .build();
        BDDMockito.given(parkingGuardService.showMotorcycles())
                .willReturn(Flux.just(motor));

        webTestClient.get()
                .uri("/api/motorcycles")
                .exchange()
                .expectBodyList(Motorcycle.class)
                .hasSize(1);
    }


    @Test
    public void getById() {
        Motorcycle motor = aMotorcycle()
                .withLicense("ABC123")
                .withType(VehicleType.MOTORCYCLE)
                .withIsParking(true)
                .build();
        BDDMockito.given(parkingGuardService.findMotorcycle("someid"))
                .willReturn(Mono.just(motor));

        webTestClient.get()
                .uri("/api/motorcycles/someid")
                .exchange()
                .expectBody(Motorcycle.class);
    }
/*
    @Test
    public void createMotorcycle() {
        Motorcycle motorcycle = new Motorcycle();

        BDDMockito.given(motorcycleRepository.saveAll(any(Publisher.class)))
                .willReturn(Flux.just(motorcycle));

        Motorcycle motor = aMotorcycle()
                .withLicense("XBC123")
                .withType(VehicleType.MOTORCYCLE)
                .withEngine(150)
                .withIsParking(true)
                .build();

        Mono<Motorcycle> motorcycleMono = Mono.just(motor);

        webTestClient.post()
                .uri("/motorcycles/cars")
                .body(motorcycleMono, Motorcycle.class)
                .exchange()
                .expectStatus()
                .isCreated();
    }*/

}