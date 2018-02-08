package com.ceiba.parking.services;

import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class CalendarGuard {

    public int getActualDay(){
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
    }

    public Date getActualDate(){
        return Calendar.getInstance().getTime();
    }

}

