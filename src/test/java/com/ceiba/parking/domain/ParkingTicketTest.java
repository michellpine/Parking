package com.ceiba.parking.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import static com.ceiba.parking.builder.VehicleTestDataBuilder.aVehicle;

public class ParkingTicketTest {



    @Test
    public void addVehicle(){
        //Arrange
        ParkingTicket parkingTicket = new ParkingTicket();
        Vehicle car = aVehicle()
                .withLicense("ACD123")
                .withType(VehicleType.CAR)
                .build();

        parkingTicket.addVehicle(car);

        Vehicle ticket = parkingTicket.getVehicle();

        assertEquals(car, ticket);
    }


}