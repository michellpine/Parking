package com.ceiba.parking.services;

import org.junit.Test;
import org.mockito.Mockito;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class CalendarGuardTest {


    @Test
    public void getActualDay() {
        //Arrange
        CalendarGuard calendarGuard = Mockito.mock(CalendarGuard.class);
        //Act
        when(calendarGuard.getActualWeekDay()).thenReturn(Calendar.SUNDAY);
        //Arrange
        assertEquals(1 ,calendarGuard.getActualWeekDay());
    }
/*
    @Test
    public void convertStringDateToDateFormat(){
       CalendarGuard calendarGuard = Mockito.mock(CalendarGuard.class);
        try{
            when(calendarGuard.stringToDate("31-Dec-1998 23:37:50")).thenReturn(Calendar.getInstance().set(1998,11,31));
            Date result = calendarGuard.stringToDate("31-Dec-1998 23:37:50");
            assertEquals(result, "Thu Dec 31 23:37:50 IST 1998");
        }catch (RuntimeException e){
            e.getMessage();
        }
    }
    */
}