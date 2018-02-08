package com.ceiba.parking.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class ParkingTicket {

    @Id
    protected String id;
    protected String license;
    protected VehicleType vehicleType;
    protected String dateArrive;
    protected String dateOut;
    protected int totalHours;
    protected int valueToPay;
    protected Car car;
    protected Motorcycle motorcycle;

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

    public String getId() {
        return id;
    }

    public String getDateArrive() {
        return dateArrive;
    }

    public void setDateArrive(String dateArrive) {
        this.dateArrive = dateArrive;
    }

    public String getDateOut() {
        return dateOut;
    }

    public void setDateOut(String dateOut) {
        this.dateOut = dateOut;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(int totalHours) {
        this.totalHours = totalHours;
    }

    public int getValueToPay() {
        return valueToPay;
    }

    public void setValueToPay(int valueToPay) {
        this.valueToPay = valueToPay;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Motorcycle getMotorcycle() {
        return motorcycle;
    }

    public void setMotorcycle(Motorcycle motorcycle) {
        this.motorcycle = motorcycle;
    }

    public ParkingTicket addCar(Car car){
        this.car = car;
        return this;
    }

    public ParkingTicket addMotorcycle(Motorcycle motorcycle){
        this.motorcycle = motorcycle;
        return  this;
    }

}
