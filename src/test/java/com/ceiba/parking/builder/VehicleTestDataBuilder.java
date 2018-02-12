package com.ceiba.parking.builder;

import com.ceiba.parking.domain.Vehicle;
import com.ceiba.parking.domain.VehicleType;

public class VehicleTestDataBuilder {

    private String license;
    protected VehicleType type;
    protected int engine;
    private boolean isParking;

    public VehicleTestDataBuilder() {
        this.license = "ADV123";
        this.type = VehicleType.CAR;
        this.isParking = false;
    }

    public VehicleTestDataBuilder withLicense(String license){
        this.license = license;
        return this;
    }

    public VehicleTestDataBuilder withType(VehicleType type){
        this.type = type;
        return this;
    }

    public VehicleTestDataBuilder withEngine(int engine){
        this.engine = engine;
        return this;
    }

    public VehicleTestDataBuilder withIsParking(boolean isParking){
        this.isParking = isParking;
        return this;
    }

    public Vehicle build(){
        if(type.equals(VehicleType.CAR)){
            return new Vehicle(license, type, isParking);
        }
        return new Vehicle(license, type, engine, isParking);
    }

    public static VehicleTestDataBuilder aVehicle(){
        return new VehicleTestDataBuilder();

    }
}
