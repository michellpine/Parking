package com.ceiba.parking.services;

import exception.ParkingException;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
public class CalendarGuard {

    private DateFormat DATE_FORMAT = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");

    public int getActualWeekDay(){
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
    }

    public String getActualDay() {
        return DATE_FORMAT.format(Calendar.getInstance().getTime());
    }

    public Date stringToDate(String date) {
        Date convert = new Date();
        try{
            convert = DATE_FORMAT.parse(date);
        } catch (ParseException e) {
            throw new ParkingException("Invalida date format");
        }
        return convert;
    }
}

