package com.ceiba.parking.services;

import com.ceiba.parking.domain.ParkingTicket;
import com.ceiba.parking.domain.Vehicle;
import com.ceiba.parking.domain.VehicleType;
import com.ceiba.parking.repositories.ParkingTicketRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;

import java.util.Calendar;

import static com.ceiba.parking.builder.VehicleTestDataBuilder.aVehicle;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

public class ParkingServiceGuardServiceImplTest {

    ParkingGuardService parkingGuardService;
    ParkingTicketRepository parkingTicketRepository;
    CalculatorParkingGuard calculatorParkingGuard;
    public static final String VEHICLE_CAN_ENTER = "Vehicle cannot enter, license begin for A and today is not available day for it";
    public static final String NO_AVAILABLE_CAR_CELLS = "Vehicle cannot enter, there are not more cells available for cars";
    public static final String NO_AVAILABLE_MOTORCYCLES_CELLS ="Vehicle cannot enter, there are not more cells available for motorcycles";
    public static final String VEHICLE_IS_NOT_A_MOTORYCLE = "Please enter the engine for the motorcycle";
    public static final String CAR_HAS_NOT_ENGINE = "Please remove the engine for the car";


    @Before
    public void setUp() {
        CalendarGuard calendarGuard = new CalendarGuard();
        parkingGuardService = new ParkingGuardServiceImpl(parkingTicketRepository, calendarGuard, calculatorParkingGuard);
    }

    @Test
    public void validateEntryConditionsForCar() {
        //Arrange
        Vehicle car = aVehicle()
                .withLicense("DCD123")
                .withType(VehicleType.CAR)
                .withIsParking(true)
                .build();
        //Act
        parkingGuardService = Mockito.mock(ParkingGuardService.class);
        when(parkingGuardService.howManyVehiclesAreParking(car.getType())).thenReturn(21);
        try {
            parkingGuardService.validateEntryConditions(car);
        }catch (RuntimeException e){
            assertEquals(NO_AVAILABLE_CAR_CELLS, e.getMessage());
        }
    }

    @Test
    public void validateEntryConditionsForMotorcycles() {
        //Arrange
        Vehicle moto = aVehicle()
                .withLicense("FCD123")
                .withType(VehicleType.MOTORCYCLE)
                .withEngine(300)
                .withIsParking(true)
                .build();
        //Act
        parkingGuardService = Mockito.mock(ParkingGuardService.class);
        when(parkingGuardService.howManyVehiclesAreParking(moto.getType())).thenReturn(11);
        try {
            parkingGuardService.validateEntryConditions(moto);
        }catch (RuntimeException e){
            assertEquals(NO_AVAILABLE_MOTORCYCLES_CELLS, e.getMessage());
        }
    }

    @Test
    public void howManyCarsAreParking() {
        //Arrange
        Vehicle car = aVehicle()
                .withLicense("DCD123")
                .withType(VehicleType.CAR)
                .withIsParking(true)
                .build();
        Vehicle car1 = aVehicle()
                .withLicense("FCD123")
                .withType(VehicleType.CAR)
                .withIsParking(true)
                .build();
        ParkingTicket parkingTicket = new ParkingTicket(car.getLicense(), car.getType(), "17-Dec-2018 11:00:38", null, 0, 0);
        parkingTicket.addVehicle(car);
        ParkingTicket parkingTicket1 = new ParkingTicket(car1.getLicense(), car1.getType(), "17-Dec-2018 11:00:38", null, 0, 0);
        parkingTicket1.addVehicle(car1);
        //Act
        parkingTicketRepository = Mockito.mock(ParkingTicketRepository.class);
        CalendarGuard calendarGuard = Mockito.mock(CalendarGuard.class);
        given(parkingTicketRepository.findByVehicle_isParking(true))
                .willReturn(Flux.just(parkingTicket, parkingTicket1));
        parkingGuardService = new ParkingGuardServiceImpl(parkingTicketRepository, calendarGuard, calculatorParkingGuard);

        //Arrenge
        assertEquals(2, parkingGuardService.howManyVehiclesAreParking(VehicleType.CAR));
    }

    @Test
    public void howManyMotorcyclesAreParking() {
        //Arrange
        Vehicle moto = aVehicle()
                .withLicense("DCD123")
                .withType(VehicleType.MOTORCYCLE)
                .withEngine(200)
                .withIsParking(true)
                .build();
        Vehicle moto1 = aVehicle()
                .withLicense("FCD123")
                .withType(VehicleType.MOTORCYCLE)
                .withIsParking(true)
                .withEngine(500)
                .build();
        ParkingTicket parkingTicket = new ParkingTicket(moto.getLicense(), moto.getType(), "17-Dec-2018 11:00:38", null, 0, 0);
        parkingTicket.addVehicle(moto);
        ParkingTicket parkingTicket1 = new ParkingTicket(moto.getLicense(), moto.getType(), "17-Dec-2018 11:00:38", null, 0, 0);
        parkingTicket1.addVehicle(moto1);
        //Act
        parkingTicketRepository = Mockito.mock(ParkingTicketRepository.class);
        CalendarGuard calendarGuard = Mockito.mock(CalendarGuard.class);
        given(parkingTicketRepository.findByVehicle_isParking(true))
                .willReturn(Flux.just(parkingTicket, parkingTicket1));
        parkingGuardService = new ParkingGuardServiceImpl(parkingTicketRepository, calendarGuard, calculatorParkingGuard);

        //Arrenge
        assertEquals(2, parkingGuardService.howManyVehiclesAreParking(VehicleType.MOTORCYCLE));
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

    @Test
    public void validateTypeVehicleIsNotAMotorcycle() {
        //Arrange
        Vehicle moto = aVehicle()
                .withLicense("PCD124")
                .withType(VehicleType.MOTORCYCLE)
                .withIsParking(true)
                .build();

        //Act
        CalendarGuard calendarGuard = Mockito.mock(CalendarGuard.class);
        parkingGuardService = new ParkingGuardServiceImpl(parkingTicketRepository, calendarGuard, calculatorParkingGuard);
        try {
            parkingGuardService.validateTypeVehicle(moto);
        }catch (RuntimeException e){
            assertEquals(VEHICLE_IS_NOT_A_MOTORYCLE, e.getMessage());
        }
    }

    @Test
    public void validateCarHasNotEngine() {
        //Arrange
        Vehicle car = aVehicle()
                .withLicense("PCD124")
                .withType(VehicleType.CAR)
                .withEngine(100)
                .withIsParking(true)
                .build();

        //Act
        CalendarGuard calendarGuard = Mockito.mock(CalendarGuard.class);
        parkingGuardService = new ParkingGuardServiceImpl(parkingTicketRepository, calendarGuard, calculatorParkingGuard);
        try {
            parkingGuardService.validateTypeVehicle(car);
        }catch (RuntimeException e){
            assertEquals(CAR_HAS_NOT_ENGINE, e.getMessage());
        }
    }
}
