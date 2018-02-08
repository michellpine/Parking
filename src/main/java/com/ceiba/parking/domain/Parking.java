package com.ceiba.parking.domain;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Parking {

    protected String id;
    protected int carCells;
    protected int motorcycleCells;
    protected int valueHourCar;
    protected int valueDayCar;
    protected int valueHourMotorcycle;
    protected int valueDatMotorcycle;

    public Parking(int carCells, int motorcycleCells) {
        this.carCells = carCells;
        this.motorcycleCells = motorcycleCells;
    }
}
