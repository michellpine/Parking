package com.ceiba.parking.domain;

import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

@Service
public class CalendarGuard {
    TimeUnit timeUnit;

    public int getActualDay(){
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
    }

    public void getActualHour(Date dateArrive, Date dateOut){
        long diffInDays = (dateOut.getTime() - dateArrive.getTime())/(1000);
        System.out.println("minutos: "+ diffInDays);
    }

}

