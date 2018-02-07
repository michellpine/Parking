package com.ceiba.parking.services;

import com.ceiba.parking.domain.CalendarGuard;
import com.ceiba.parking.domain.Car;
import com.ceiba.parking.domain.Motorcycle;
import com.ceiba.parking.domain.VehicleType;
import com.ceiba.parking.repositories.CarRepository;
import com.ceiba.parking.repositories.MotorcycleRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Calendar;

import static com.ceiba.parking.builder.CarTestDataBuilder.aCar;
import static com.ceiba.parking.builder.MotorcycleTestDataBuilder.aMotorcycle;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ParkingServiceGuardServiceImplTest {

    ParkingGuardService parkingGuardService;
    CarRepository carRepository;
    MotorcycleRepository motorcycleRepository;
    public static final String VEHICLE_CAN_ENTER = "Vehicle cannot enter, license begin for A and today is not available day for it";
/*
    @Before
    public void setUp(){
        CalendarGuard calendarGuard = new CalendarGuard();
        parkingGuardService = new ParkingGuardServiceImpl(carRepository, motorcycleRepository, calendarGuard);
    }


    @Test
    public void canEnterVehicle(){
        Motorcycle moto = aMotorcycle()
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
        Car car = aCar()
                .withLicense("ACD124")
                .withType(VehicleType.CAR)
                .withIsParking(true)
                .build();
        try {
            CalendarGuard calendarGuard = Mockito.mock(CalendarGuard.class);
            Mockito.when(calendarGuard.getActualDay()).thenReturn(Calendar.TUESDAY);

            //Act
            parkingGuardService = new ParkingGuardServiceImpl(carRepository, motorcycleRepository, calendarGuard);
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
        Motorcycle moto = aMotorcycle()
                .withLicense("ACD125")
                .withType(VehicleType.MOTORCYCLE)
                .withEngine(150)
                .withIsParking(true)
                .build();
        try {
            CalendarGuard calendarGuard = Mockito.mock(CalendarGuard.class);
            Mockito.when(calendarGuard.getActualDay()).thenReturn(Calendar.MONDAY);

            //Act
            parkingGuardService = new ParkingGuardServiceImpl(carRepository, motorcycleRepository, calendarGuard);
            parkingGuardService.canEnterVehicle(moto);
        }catch (RuntimeException e){
            //Assert
            assertEquals(VEHICLE_CAN_ENTER, e.getMessage());
        }
    }

    @Test
    public void licenseVehicleBeginToA_DayIsSunday(){
        //Arrange
        Motorcycle moto = aMotorcycle()
                .withLicense("ACD126")
                .withType(VehicleType.MOTORCYCLE)
                .withIsParking(true)
                .build();
        try {
            CalendarGuard calendarGuard = Mockito.mock(CalendarGuard.class);
            Mockito.when(calendarGuard.getActualDay()).thenReturn(Calendar.SUNDAY);

            //Act
            parkingGuardService = new ParkingGuardServiceImpl(carRepository, motorcycleRepository, calendarGuard);
            parkingGuardService.canEnterVehicle(moto);
        }catch (RuntimeException e){
            //Assert
            assertEquals(VEHICLE_CAN_ENTER, e.getMessage());
        }
    }

*/
}
