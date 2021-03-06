package com.ceiba.parking.services;

import org.junit.Test;
import org.mockito.Mockito;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class CalendarGuardTest {

    @Test
    public void getActualWeekDay() {
        //Arrange
        CalendarGuard calendarGuard = Mockito.mock(CalendarGuard.class);
        //Act
        when(calendarGuard.getActualWeekDay()).thenReturn(Calendar.SUNDAY);
        //Arrange
        assertEquals(1 ,calendarGuard.getActualWeekDay());
    }

    @Test
    public void getActualDate(){
        DateFormat DATE_FORMAT = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");

    }

    @Test
    public void convertStringDateToDateFormat(){
        Calendar date1 = new GregorianCalendar(2018,11, 17, 11, 00, 38);
        Date fakeDate1 = date1.getTime();
        try{
            CalendarGuard calendarGuard = Mockito.mock(CalendarGuard.class);
            when(calendarGuard.stringToDate("17-Dec-2018 11:00:38")).thenReturn(fakeDate1);
            Date result = calendarGuard.stringToDate("17-Dec-2018 11:00:38");
            assertEquals(result, fakeDate1);
        }catch (RuntimeException e){
            e.getMessage();
        }
    }

}