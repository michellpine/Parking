package com.ceiba.parking.domain;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public abstract class Vehicle {
    protected String license;
    protected VehicleType type;
    protected boolean isParking;

    public Vehicle(){

    }

    public Vehicle(String license, VehicleType type, boolean isParking) {
        this.license = license;
        this.type = type;
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

}
