package com.ceiba.parking.domain;

import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class CalendarGuard {

    public int getActualDay(){
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
    }

    public long getHourDifferenceBetweenDates(Date dateArrive, Date dateOut){
        //Se tiene en cuenta la diferencia en minutos, ya que despues de pasado un
        //minuto, se cobra la siguiente hora
        double differenceInMinutes = (dateOut.getTime() - dateArrive.getTime())/(60*1000);
        if((differenceInMinutes/60) % 1 == 0){
            return (dateOut.getTime() - dateArrive.getTime())/(60*60*1000);
        }else{
            return (long) Math.ceil(differenceInMinutes/60);
        }
    }

}

