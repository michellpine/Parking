package com.ceiba.parking.services;

import com.ceiba.parking.domain.CalendarGuard;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class CalendarGuardTest {

    @Test
    public void getActualDay() {
        //Arrange
        CalendarGuard calendarGuard = Mockito.mock(CalendarGuard.class);
        //Act
        when(calendarGuard.getActualDay()).thenReturn(Calendar.SUNDAY);
        //Arrange
        assertEquals(1 ,calendarGuard.getActualDay());
    }
}