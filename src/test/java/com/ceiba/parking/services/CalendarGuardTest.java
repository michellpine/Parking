package com.ceiba.parking.services;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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

    @Test
    public void convertStringDateToDateFormat(){
        Calendar date1 = new GregorianCalendar(2018,11, 17, 11, 00, 38);
        Date fake1 = date1.getTime();
        try{
            CalendarGuard calendarGuard = Mockito.mock(CalendarGuard.class);
            when(calendarGuard.stringToDate("17-Dec-2018 11:00:38")).thenReturn(fake1);
            Date result = calendarGuard.stringToDate("17-Dec-2018 11:00:38");
            assertEquals(result, fake1);
        }catch (RuntimeException e){
            e.getMessage();
        }
    }

}