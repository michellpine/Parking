package com.ceiba.parking.services;

import com.ceiba.parking.domain.*;
import com.ceiba.parking.repositories.CarRepository;
import com.ceiba.parking.repositories.MotorcycleRepository;
import exception.ParkingException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ParkingGuardServiceImpl implements ParkingGuardService {

    private CalendarGuard calendarGuard;
    private CarRepository carRepository;
    private MotorcycleRepository motorcycleRepository;
    private ParkingTicket parkingTicket;

    private static final int CAR_CELLS = 19;
    private static final int MOTORCYCLE_CELLS = 9;

    public ParkingGuardServiceImpl(CarRepository carRepository, MotorcycleRepository motorcycleRepository,
                                   CalendarGuard calendarGuard, ParkingTicket parkingTicket) {
        this.carRepository = carRepository;
        this.motorcycleRepository = motorcycleRepository;
        this.calendarGuard = calendarGuard;
        this.parkingTicket = parkingTicket;
    }

    @Override
    public boolean canEnterVehicle(Vehicle vehicle) {
        if (vehicle.getLicense().toUpperCase().startsWith("A")
                && calendarGuard.getActualDay() != java.util.Calendar.MONDAY
                && calendarGuard.getActualDay() != java.util.Calendar.SUNDAY) {
            throw new ParkingException("Vehicle cannot enter, license begin for A and today is not available day for it");
        }
        return true;
    }

    //Cars Responsaility
    @Override
    @Transactional
    public Mono<Car> saveCar(Car vehicle) {
        if (carRepository.count().block() > CAR_CELLS) {
            throw new ParkingException("Vehicle cannot enter, there are not more cells available for cars");
        }
        parkingTicket.setDateArrive(calendarGuard.getActualDate());
        return carRepository.save(vehicle);
    }

    @Override
    public void outCar(Car car) {
        parkingTicket.setDateOut(calendarGuard.getActualDate());
        car.setParking(false);
    }


    //Motorcycle Responsaility
    @Override
    @Transactional
    public Mono<Motorcycle> saveMotorcycle(Motorcycle vehicle) {
        if(motorcycleRepository.count().block()>MOTORCYCLE_CELLS){
            throw new ParkingException("Vehicle cannot enter, there are not more cells available for motorcycles");
        }
        return motorcycleRepository.save(vehicle);
    }

    @Override
    public void outMotorcycle(Motorcycle motorcycle) {
        motorcycle.setParking(false);
    }
}
