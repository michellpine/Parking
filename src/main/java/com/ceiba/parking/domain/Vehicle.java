package com.ceiba.parking.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Document
public class Vehicle {

    @Id
    protected String id= UUID.randomUUID().toString();

    @NotBlank
    protected String license;

    @NotNull
    protected VehicleType type;
    protected int engine;

    @NotNull
    protected boolean isParking;

    public Vehicle(){

    }

    public Vehicle(String license, VehicleType type, boolean isParking) {
        this.license = license;
        this.type = type;
        this.isParking = isParking;
    }

    public Vehicle(String license, VehicleType type, int engine, boolean isParking) {
        this.license = license;
        this.type = type;
        this.engine = engine;
        this.isParking = isParking;
    }

    //Getters and Setters

    public String getLicense() {
        return license;
    }

    public VehicleType getType() {
        return type;
    }

    public boolean isParking() {
        return isParking;
    }

    public void setParking(boolean parking) {
        isParking = parking;
    }

    public int getEngine() {
        return engine;
    }
}
