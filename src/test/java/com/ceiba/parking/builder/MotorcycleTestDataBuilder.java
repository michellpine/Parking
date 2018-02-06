package com.ceiba.parking.builder;

import com.ceiba.parking.domain.Motorcycle;
import com.ceiba.parking.domain.VehicleType;

public class MotorcycleTestDataBuilder {

    private String license;
    protected VehicleType type;
    private int engine;
    private boolean isParking;

    public MotorcycleTestDataBuilder() {
        this.license = "ADV123";
        this.type = VehicleType.MOTORCYCLE;
        this.engine = 250;
        this.isParking = false;
    }

    public MotorcycleTestDataBuilder withLicense(String license){
        this.license = license;
        return this;
    }

    public MotorcycleTestDataBuilder withType(VehicleType type){
        this.type = type;
        return this;
    }

    public MotorcycleTestDataBuilder withEngine(int engine){
        this.engine = engine;
        return this;
    }

    public MotorcycleTestDataBuilder withIsParking(boolean isParking){
        this.isParking = isParking;
        return this;
    }

    public Motorcycle build(){
        if(type.equals(VehicleType.MOTORCYCLE)){
            return new Motorcycle(license, type, engine, isParking);
        }
        return null;
    }

    public static MotorcycleTestDataBuilder aMotorcycle(){
        return new MotorcycleTestDataBuilder();

    }
}
