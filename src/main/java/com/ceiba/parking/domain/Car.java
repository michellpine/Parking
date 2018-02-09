package com.ceiba.parking.domain;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Car extends Vehicle {

    public Car(){
    }

    public Car(String license, VehicleType type, boolean isParking) {
        super(license, type, isParking);
    }
}
