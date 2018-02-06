package com.ceiba.parking.builder;

import com.ceiba.parking.domain.Car;
import com.ceiba.parking.domain.VehicleType;

public class CarTestDataBuilder {

    private String license;
    protected VehicleType type;
    private boolean isParking;

    public CarTestDataBuilder() {
        this.license = "ADV123";
        this.type = VehicleType.CAR;
        this.isParking = false;
    }

    public CarTestDataBuilder withLicense(String license){
        this.license = license;
        return this;
    }

    public CarTestDataBuilder withType(VehicleType type){
        this.type = type;
        return this;
    }

    public CarTestDataBuilder withIsParking(boolean isParking){
        this.isParking = isParking;
        return this;
    }

    public Car build(){
        if(type.equals(VehicleType.CAR)){
            return new Car(license, type, isParking);
        }
        return null;
    }

    public static CarTestDataBuilder aCar(){
        return new CarTestDataBuilder();

    }
}
