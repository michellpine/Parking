package com.ceiba.parking.services;

import com.ceiba.parking.domain.Car;
import com.ceiba.parking.domain.Motorcycle;
import com.ceiba.parking.domain.Vehicle;
import com.ceiba.parking.repositories.CarRepository;
import com.ceiba.parking.repositories.MotorcycleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;


@Service
public class ParkingGuardServiceImpl implements ParkingGuardService {

    private CalendarGuard calendarGuard;
    private CarRepository carRepository;
    private MotorcycleRepository motorcycleRepository;

    private static final int CAR_CELLS = 19;
    private static final int MOTORCYCLE_CELLS = 9;

    public ParkingGuardServiceImpl(CarRepository carRepository, MotorcycleRepository motorcycleRepository, CalendarGuard calendarGuard) {
        this.carRepository = carRepository;
        this.motorcycleRepository  = motorcycleRepository;
        this.calendarGuard = calendarGuard;
    }

    @Override
    public boolean canEnterVehicle(Vehicle vehicle){
        if(vehicle.getLicense().toUpperCase().startsWith("A")
                && calendarGuard.getActualDay() != java.util.Calendar.MONDAY
                && calendarGuard.getActualDay() != java.util.Calendar.SUNDAY){
            throw new IllegalArgumentException("Vehicle cannot enter, license begin for A and today is not available day for it");
        }
        return true;
    }

    @Override
    @Transactional
    public Mono<Car> saveCar(Car vehicle) {
        if(carRepository.count().block()>CAR_CELLS){
            throw new IllegalArgumentException("Vehicle cannot enter, there are not more cells available for cars");
        }
        return carRepository.save(vehicle);
    }

    @Override
    @Transactional
    public Mono<Motorcycle> saveMotorcycle(Motorcycle vehicle) {
        if(motorcycleRepository.count().block()>MOTORCYCLE_CELLS){
            throw new IllegalArgumentException("Vehicle cannot enter, there are not more cells available for motorcycles");
        }
        return motorcycleRepository.save(vehicle);
    }

    @Override
    public List<Car> showParkingCars(Car car) {
        List<Car> cars = new ArrayList<>();
        if(car.isParking()){
            carRepository.findAll();
        }
        return cars;
    }

    @Override
    public List<Motorcycle> showParkingMotorcycles(Motorcycle motorcycle) {
        List<Motorcycle> motors = new ArrayList<>();
        if(motorcycle.isParking()){
            motorcycleRepository.findAll();
        }
        return motors;
    }

    @Override
    public void outCar(Car car) {
        car.setParking(false);
    }

    @Override
    public void outMotorcycle(Motorcycle motorcycle) {
        motorcycle.setParking(false);
    }
}
