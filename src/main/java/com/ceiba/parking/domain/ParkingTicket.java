package com.ceiba.parking.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Document
public class ParkingTicket {

    @Id
    protected String id= UUID.randomUUID().toString();
    protected String license;
    protected VehicleType vehicleType;

    @NotNull
    protected String dateArrive;
    protected String dateOut;
    protected int totalHours;
    protected int valueToPay;

    protected Vehicle vehicle;

    public ParkingTicket() {
    }

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

}
