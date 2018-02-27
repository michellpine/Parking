package com.ceiba.parking.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Document
public class ParkingTicket {

    @Id
    private String id= UUID.randomUUID().toString();
    private String license;
    private VehicleType vehicleType;

    @NotNull
    private String dateArrive;
    private String dateOut;
    private int totalHours;
    private int valueToPay;

    private Vehicle vehicle;

    public ParkingTicket() {}

    public ParkingTicket(String license, VehicleType vehicleType, String dateArrive, String dateOut, int totalHours, int valueToPay) {
        this.license = license;
        this.vehicleType = vehicleType;
        this.dateArrive = dateArrive;
        this.dateOut = dateOut;
        this.totalHours = totalHours;
        this.valueToPay = valueToPay;
    }

    public ParkingTicket addVehicle(Vehicle vehicle){
        this.vehicle = vehicle;
        return this;
    }

    public String getId() {
        return id;
    }

    public String getDateArrive() {
        return dateArrive;
    }

    public String getDateOut() {
        return dateOut;
    }

    public void setDateOut(String dateOut) {
        this.dateOut = dateOut;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public int getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(int totalHours) {
        this.totalHours = totalHours;
    }

    public void setValueToPay(int valueToPay) {
        this.valueToPay = valueToPay;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getValueToPay() {
        return valueToPay;
    }
}
