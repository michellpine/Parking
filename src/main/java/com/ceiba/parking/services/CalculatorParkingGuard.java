package com.ceiba.parking.services;

import com.ceiba.parking.domain.Vehicle;
import com.ceiba.parking.domain.VehicleType;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CalculatorParkingGuard {

    private int VALUE_HOUR_CAR = 1000;
    private int VALUE_HOUR_MOTORCYCLE = 500;
    private int VALUE_DAY_CAR = 8000;
    private int VALUE_DAY_MOTORCYCLE = 600;

    public int getCountHours(Date dateArrive, Date dateOut) {
        //Se tiene en cuenta la diferencia en minutos, ya que despues de pasado un
        //minuto, se cobra la siguiente hora
        double differenceInMinutes = (dateOut.getTime() - dateArrive.getTime())/(60*1000);
        if((differenceInMinutes/60) % 1 == 0){
            return (int) ((dateOut.getTime() - dateArrive.getTime())/(60*60*1000));
        }else{
            return (int) Math.ceil(differenceInMinutes/60);
        }
    }

    public int calculateValueToPay(int hours, VehicleType type, int engine){
        int valueToPay = 0;
        int daysToPay = (hours/24);
        int hoursToPay = 0;
        if((hours % 24)>=9 && (hours % 24)<=23) {
            daysToPay++;
        }else {
            hoursToPay = hours % 24;
        }
        if(type.equals(VehicleType.MOTORCYCLE) && engine>500) {
            valueToPay = (daysToPay * VALUE_DAY_MOTORCYCLE) + (hoursToPay * VALUE_HOUR_MOTORCYCLE) + 2000;
        }else {
            valueToPay = (daysToPay * VALUE_DAY_MOTORCYCLE) + (hoursToPay * VALUE_HOUR_MOTORCYCLE);
        }
        valueToPay =  (daysToPay*VALUE_DAY_CAR)+(hoursToPay*VALUE_HOUR_CAR);
        return valueToPay;
    }

    public int calculateValueForMotorcycleWithEngineGreater(int engine, int valueToPay){
        if(engine>500){
            return valueToPay+2000;
        }
        return valueToPay;
    }
}
