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
    protected Date dateArrive;
    protected Date dateOut;
    protected long totalHours;
    protected int valueToPay;
    protected Car car;
    protected Motorcycle motorcycle;

    public ParkingTicket(String license, VehicleType vehicleType, Date dateArrive, Date dateOut, long totalHours, int valueToPay) {
        this.license = license;
        this.vehicleType = vehicleType;
        this.dateArrive = dateArrive;
        this.dateOut = dateOut;
        this.totalHours = totalHours;
        this.valueToPay = valueToPay;
    }
/*
    public ParkingTicket addCar(Car car){
        this.car = car;
        return this;
    }

    public ParkingTicket addMotorcycle(Motorcycle motorcycle){
        this.motorcycle = motorcycle;
        return  this;
    }
*/
}
