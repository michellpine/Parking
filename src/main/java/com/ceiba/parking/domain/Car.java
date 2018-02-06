package com.ceiba.parking.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;


@Document
public class Car extends Vehicle {

    @Id
    private String id = UUID.randomUUID().toString();

    public Car(){
    }

    public Car(String license, VehicleType type, boolean isParking) {
        super(license, type, isParking);
    }
}
