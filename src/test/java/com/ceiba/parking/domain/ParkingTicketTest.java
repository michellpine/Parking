package com.ceiba.parking.domain;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

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