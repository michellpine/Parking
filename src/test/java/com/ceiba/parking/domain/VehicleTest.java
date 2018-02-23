package com.ceiba.parking.domain;

import org.junit.Test;

import static com.ceiba.parking.builder.VehicleTestDataBuilder.aVehicle;
import static org.junit.Assert.assertEquals;

public class VehicleTest {


    @Test
    public void getLicenseCar() {
        //Arrange
        Vehicle vehicle = aVehicle()
                .withLicense("ACD123")
                .withType(VehicleType.CAR)
                .build();
        //Act
        String result = vehicle.getLicense();
        //Assert
        assertEquals("ACD123", result);
    }

    @Test
    public void getLicenseMotorcycle() {
        //Arrange
        Vehicle vehicle = aVehicle()
                .withLicense("ACD123")
                .withType(VehicleType.BYKE)
                .withEngine(150)
                .build();
        //Act
        String result = vehicle.getLicense();
        //Assert
        assertEquals("ACD123", result);
    }

    @Test
    public void getTypeCar() {
        //Arrange
        Vehicle vehicle = aVehicle()
                .withLicense("ACD123")
                .withType(VehicleType.CAR)
                .withIsParking(true)
                .build();
        //Act
        VehicleType result = vehicle.getType();
        //Assert
        assertEquals(VehicleType.CAR, result);
    }

    @Test
    public void getTypeMotorcycle() {
        //Arrange
        Vehicle vehicle = aVehicle()
                .withLicense("ACD123")
                .withType(VehicleType.BYKE)
                .withEngine(150)
                .withIsParking(true)
                .build();
        //Act
        VehicleType result = vehicle.getType();
        //Assert
        assertEquals(VehicleType.BYKE, result);
    }

    @Test
    public void setLicense() {
        //Arrenge
        Vehicle car = new Vehicle("DFW234", VehicleType.CAR, true);

        //Act
        String result = car.getLicense();

        //Assert
        assertEquals("DFW234", result );
    }

    @Test
    public void setType() {
        //Arrenge
        Vehicle car = new Vehicle("DFW234", VehicleType.CAR, true);

        //Act
        VehicleType result = car.getType();

        //Assert
        assertEquals(VehicleType.CAR, result);
    }
}