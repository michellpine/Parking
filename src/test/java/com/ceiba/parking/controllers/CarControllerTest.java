package com.ceiba.parking.controllers;

import com.ceiba.parking.domain.Car;
import com.ceiba.parking.domain.ParkingTicket;
import com.ceiba.parking.domain.VehicleType;
import com.ceiba.parking.repositories.ParkingTicketRepository;
import com.ceiba.parking.services.ParkingGuardService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.reactivestreams.Publisher;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.ceiba.parking.builder.CarTestDataBuilder.aCar;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


public class CarControllerTest {

    CarController carController;
    ParkingGuardService parkingGuardService;
    WebTestClient webTestClient;
    ParkingTicketRepository parkingTicketRepository;

    @Before
    public void setUp() throws Exception {
        parkingGuardService = Mockito.mock(ParkingGuardService.class);
        carController = new CarController(parkingGuardService);
        webTestClient = WebTestClient.bindToController(carController).build();

        parkingTicketRepository = Mockito.mock(ParkingTicketRepository.class);
    }

    @Test
    public void enterCar() {
        Car car = new Car();
        given(parkingTicketRepository.saveAll(any(Publisher.class)))
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

    @Test
    public void getRegister() {
        ParkingTicket parkingTicket = new ParkingTicket("XCD124", VehicleType.CAR, "17-Dec-2018 11:00:38", null, 0, 0);

        given(parkingTicketRepository.findById("someid"))
                .willReturn(Mono.just(parkingTicket));

        webTestClient.get()
                .uri("/api/cars/someid")
                .exchange()
                .expectBody(ParkingTicket.class);
    }

    @Test
    public void outCarWithChange() {
        ParkingTicket parkingTicket = new ParkingTicket("XCD124", VehicleType.CAR, "17-Dec-2018 11:00:38", null, 0, 0);

        given(parkingTicketRepository.findById(anyString()))
                .willReturn(Mono.just(parkingTicket));

        given(parkingTicketRepository.save(any(ParkingTicket.class)))
                .willReturn(Mono.just(parkingTicket));

        parkingTicket.setDateOut("17-Dec-2018 15:00:38");
        Mono<ParkingTicket> catToUpdateMono = Mono.just(parkingTicket);

        webTestClient.patch()
                .uri("/api/cars/asdfasdf")
                .body(catToUpdateMono, ParkingTicket.class)
                .exchange()
                .expectStatus()
                .isOk();

        verify(parkingTicketRepository, never()).save(any());
    }

    @Test
    public void outCarWithNoChange() {
        ParkingTicket parkingTicket = new ParkingTicket("XCD124", VehicleType.CAR, "17-Dec-2018 11:00:38", null, 0, 0);

        given(parkingTicketRepository.findById(anyString()))
                .willReturn(Mono.just(parkingTicket));

        given(parkingTicketRepository.save(any(ParkingTicket.class)))
                .willReturn(Mono.just(parkingTicket));

        Mono<ParkingTicket> catToUpdateMono = Mono.just(parkingTicket);

        webTestClient.patch()
                .uri("/api/cars/asdfasdf")
                .body(catToUpdateMono, ParkingTicket.class)
                .exchange()
                .expectStatus()
                .isOk();

        verify(parkingTicketRepository, never()).save(any());
    }

}