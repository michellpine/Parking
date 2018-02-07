package com.ceiba.parking.domain;

public abstract class Vehicle {
    protected String license;
    protected VehicleType type;
    protected boolean isParking;
    protected ParkingTicket parkingTicket;

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

    public Vehicle addParkingTicket(ParkingTicket parkingTicket) {
        this.parkingTicket = parkingTicket;
        return this;
    }
}
