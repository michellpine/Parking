package com.ceiba.parking.services;

import com.ceiba.parking.domain.VehicleType;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class CalculatorParkingGuardTest {

    CalculatorParkingGuard calculatorParkingGuard;

    @Before
    public void setUp() throws Exception {
        calculatorParkingGuard = new CalculatorParkingGuard();
    }

    @Test
    public void getCountHoursWithExactMinutes() {
        //Arrange
        Calendar date1 = new GregorianCalendar(2018,1, 7, 11, 56, 38);
        Date fake1 = date1.getTime();

        //Act
        Calendar date2 = new GregorianCalendar(2018,1, 7, 17, 56, 38);
        Date fake2 = date2.getTime();
        int result = calculatorParkingGuard.getCountHours(fake1, fake2);

        //Assert
        assertEquals(6, result);
    }

    @Test
    public void getCountHoursWithExtrasMinutes() {
        //Arrange
        Calendar date1 = new GregorianCalendar(2018,1, 7, 11, 00, 38);
        Date fake1 = date1.getTime();

        //Act
        Calendar date2 = new GregorianCalendar(2018,1, 7, 17, 56, 38);
        Date fake2 = date2.getTime();
        int result = calculatorParkingGuard.getCountHours(fake1, fake2);

        //Assert
        assertEquals(7, result);
    }

    @Test
    public void calculateValueToPayForACar() {
        //Arrange
        int hours = 27;
        VehicleType type = VehicleType.CAR;

        //Act
        int result = calculatorParkingGuard.calculateValueToPay(hours, type, 0);

        //Assert
        assertEquals(11000, result);
    }

    @Test
    public void calculateValueToPayForAMotorcycleWithALessCC() {
        //Arrange
        int hours = 27;
        VehicleType type = VehicleType.MOTORCYCLE;

        //Act
        int result = calculatorParkingGuard.calculateValueToPay(hours, type, 300);

        //Assert
        assertEquals(7500, result);
    }

    @Test
    public void calculateValueToPayForAMotorcycleWithABiggerCC() {
        //Arrange
        int hours = 27;
        VehicleType type = VehicleType.MOTORCYCLE;

        //Act
        int result = calculatorParkingGuard.calculateValueToPay(hours, type, 600);

        //Assert
        assertEquals(9500, result);
    }
}