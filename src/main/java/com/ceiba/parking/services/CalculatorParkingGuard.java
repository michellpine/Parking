package com.ceiba.parking.services;

import com.ceiba.parking.domain.VehicleType;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CalculatorParkingGuard {

    private int VALUE_HOUR_CAR = 1000;
    private int VALUE_HOUR_MOTORCYCLE = 500;
    private int VALUE_DAY_CAR = 8000;
    private int VALUE_DAY_MOTORCYCLE = 6000;
    private int CC_LIMIT = 500;
    private int EXTRA_MONEY_TO_PAY_FOR_CC_LIMIT = 2000;
    private int TOTAL_HOURS_PER_DAY = 24;
    private int HOUR_TO_BEGIN_A_DAY = 9;
    private int HOUR_TO_END_A_DAY = 23;

    public int getCountHours(Date dateArrive, Date dateOut) {
        double differenceInMinutes = (dateOut.getTime() - dateArrive.getTime())/(60*1000);
        if((differenceInMinutes/60) % 1 == 0){
            return (int) ((dateOut.getTime() - dateArrive.getTime())/(60*60*1000));
        }else{
            return (int) Math.ceil(differenceInMinutes/60);
        }
    }

    public int calculateValueToPay(int hours, VehicleType type, int engine){
        int valueToPay;
        int daysToPay = (hours/TOTAL_HOURS_PER_DAY);
        int hoursToPay = 0;
        if((hours % TOTAL_HOURS_PER_DAY)>=HOUR_TO_BEGIN_A_DAY && (hours % TOTAL_HOURS_PER_DAY)<=HOUR_TO_END_A_DAY) {
            daysToPay++;
        }else {
            hoursToPay = hours % TOTAL_HOURS_PER_DAY;
        }
        if(type.equals(VehicleType.MOTORCYCLE) && engine>=CC_LIMIT){
            valueToPay = (daysToPay * VALUE_DAY_MOTORCYCLE) + (hoursToPay * VALUE_HOUR_MOTORCYCLE) + EXTRA_MONEY_TO_PAY_FOR_CC_LIMIT;
        }
        else {
            valueToPay = (daysToPay * VALUE_DAY_MOTORCYCLE) + (hoursToPay * VALUE_HOUR_MOTORCYCLE);
        }
        if(type.equals(VehicleType.CAR)){
            valueToPay = (daysToPay*VALUE_DAY_CAR)+(hoursToPay*VALUE_HOUR_CAR);
        }
        return valueToPay;
    }
}
