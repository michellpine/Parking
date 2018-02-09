package com.ceiba.parking.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
public class Motorcycle extends Vehicle{

    @Id
    protected String id= UUID.randomUUID().toString();
    private int engine;

    public Motorcycle(){
    }

    public Motorcycle(String license, VehicleType type, int engine, boolean isParking) {
        super(license, type, isParking);
        this.engine = engine;
    }

    public int getEngine() {
        return engine;
    }

    public void setEngine(int engine) {
        this.engine = engine;
    }
}