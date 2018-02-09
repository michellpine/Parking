package com.ceiba.parking.domain;

import com.ceiba.parking.services.ParkingGuardService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static com.ceiba.parking.builder.CarTestDataBuilder.aCar;
import static org.junit.Assert.*;

public class ParkingTicketTest {

    ParkingTicket parkingTicket;

    @Before
    public void setUp() throws Exception {
        parkingTicket = Mockito.mock(ParkingTicket.class);
    }

    /*@Test
    public void addCar() {
        Car car = aCar()
                .withLicense("XCD123")
                .withType(VehicleType.CAR)
                .withIsParking(true)
                .build();

        parkingTicket.addCar(car);
        assertEquals(car, parkingTicket.getCar());

    }*/

    @Test
    public void addMotorcycle() {
    }
}