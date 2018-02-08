package com.ceiba.parking.domain;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Motorcycle extends Vehicle{

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