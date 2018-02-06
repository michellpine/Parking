package com.ceiba.parking.domain;

import org.junit.Test;

import static com.ceiba.parking.builder.CarTestDataBuilder.aCar;
import static com.ceiba.parking.builder.MotorcycleTestDataBuilder.aMotorcycle;
import static org.junit.Assert.assertEquals;

public class VehicleTest {


    @Test
    public void getLicenseCar() {
        //Arrange
        Car vehicle = aCar()
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
        Motorcycle vehicle = aMotorcycle()
                .withLicense("ACD123")
                .withType(VehicleType.MOTORCYCLE)
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
        Car vehicle = aCar()
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
        Motorcycle vehicle = aMotorcycle()
                .withLicense("ACD123")
                .withType(VehicleType.MOTORCYCLE)
                .withEngine(150)
                .withIsParking(true)
                .build();
        //Act
        VehicleType result = vehicle.getType();
        //Assert
        assertEquals(VehicleType.MOTORCYCLE, result);
    }

    @Test
    public void setLicense() {
        //Arrenge
        Car car = new Car("DFW234", VehicleType.CAR, true);

        //Act
        String result = car.getLicense();

        //Assert
        assertEquals("DFW234", result );
    }

    @Test
    public void setType() {
        //Arrenge
        Car car = new Car("DFW234", VehicleType.CAR, true);

        //Act
        VehicleType result = car.getType();

        //Assert
        assertEquals(VehicleType.CAR, result);
    }
}