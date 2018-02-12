package com.ceiba.parking.services;

import com.ceiba.parking.domain.Vehicle;
import com.ceiba.parking.domain.VehicleType;
import com.ceiba.parking.repositories.ParkingTicketRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Calendar;

import static com.ceiba.parking.builder.VehicleTestDataBuilder.aVehicle;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ParkingServiceGuardServiceImplTest {

    ParkingGuardService parkingGuardService;
    ParkingTicketRepository parkingTicketRepository;
    CalculatorParkingGuard calculatorParkingGuard;
    public static final String VEHICLE_CAN_ENTER = "Vehicle cannot enter, license begin for A and today is not available day for it";

    @Before
    public void setUp(){
        CalendarGuard calendarGuard = new CalendarGuard();
        parkingGuardService = new ParkingGuardServiceImpl(parkingTicketRepository, calendarGuard, calculatorParkingGuard);
    }


    @Test
    public void canEnterVehicle(){
        Vehicle moto = aVehicle()
                .withLicense("ACD123")
                .withType(VehicleType.MOTORCYCLE)
                .withEngine(150)
                .withIsParking(true)
                .build();
        try {
            parkingGuardService.canEnterVehicle(moto);
        }catch (RuntimeException e){
                assertEquals(VEHICLE_CAN_ENTER, e.getMessage());
        }
    }

    @Test
    public void licenseVehicleBeginToA_DayIsDifferentToMondayAndSunday(){
        //Arrange
        Vehicle car = aVehicle()
                .withLicense("ACD124")
                .withType(VehicleType.CAR)
                .withIsParking(true)
                .build();
        try {
            CalendarGuard calendarGuard = Mockito.mock(CalendarGuard.class);
            Mockito.when(calendarGuard.getActualWeekDay()).thenReturn(Calendar.TUESDAY);

            //Act
            parkingGuardService = new ParkingGuardServiceImpl(parkingTicketRepository, calendarGuard, calculatorParkingGuard);
            parkingGuardService.canEnterVehicle(car);
            fail();
        }catch (RuntimeException e){
            //Assert
            assertEquals(VEHICLE_CAN_ENTER, e.getMessage());
        }
    }

    @Test
    public void licenseVehicleBeginToA_DayIsMonday(){
        //Arrange
        Vehicle moto = aVehicle()
                .withLicense("ACD125")
                .withType(VehicleType.MOTORCYCLE)
                .withEngine(150)
                .withIsParking(true)
                .build();
        try {
            CalendarGuard calendarGuard = Mockito.mock(CalendarGuard.class);
            Mockito.when(calendarGuard.getActualWeekDay()).thenReturn(Calendar.MONDAY);

            //Act
            parkingGuardService = new ParkingGuardServiceImpl(parkingTicketRepository, calendarGuard, calculatorParkingGuard);
            parkingGuardService.canEnterVehicle(moto);
        }catch (RuntimeException e){
            //Assert
            assertEquals(VEHICLE_CAN_ENTER, e.getMessage());
        }
    }

    @Test
    public void licenseVehicleBeginToA_DayIsSunday(){
        //Arrange
        Vehicle moto = aVehicle()
                .withLicense("ACD126")
                .withType(VehicleType.MOTORCYCLE)
                .withIsParking(true)
                .build();
        try {
            CalendarGuard calendarGuard = Mockito.mock(CalendarGuard.class);
            Mockito.when(calendarGuard.getActualWeekDay()).thenReturn(Calendar.SUNDAY);

            //Act
            parkingGuardService = new ParkingGuardServiceImpl(parkingTicketRepository, calendarGuard, calculatorParkingGuard);
            parkingGuardService.canEnterVehicle(moto);
        }catch (RuntimeException e){
            //Assert
            assertEquals(VEHICLE_CAN_ENTER, e.getMessage());
        }
    }
}
