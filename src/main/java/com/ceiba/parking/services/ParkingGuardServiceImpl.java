package com.ceiba.parking.services;

import com.ceiba.parking.domain.Car;
import com.ceiba.parking.domain.Motorcycle;
import com.ceiba.parking.domain.Vehicle;
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

    private static final int CAR_CELLS = 19;
    private static final int MOTORCYCLE_CELLS = 9;

    public ParkingGuardServiceImpl(CarRepository carRepository, MotorcycleRepository motorcycleRepository, CalendarGuard calendarGuard) {
        this.carRepository = carRepository;
        this.motorcycleRepository = motorcycleRepository;
        this.calendarGuard = calendarGuard;
    }

    //Cars Responsaility
    @Override
    @Transactional
    public Mono<Car> saveCar(Car vehicle) {
        if (carRepository.count().block() > CAR_CELLS) {
            throw new ParkingException("Vehicle cannot enter, there are not more cells available for cars");
        }
        if (vehicle.getLicense().toUpperCase().startsWith("A")
                && calendarGuard.getActualDay() != java.util.Calendar.MONDAY
                && calendarGuard.getActualDay() != java.util.Calendar.SUNDAY) {
            throw new ParkingException("Vehicle cannot enter, license begin for A and today is not available day for it");
        }
        return carRepository.save(vehicle);
    }

    @Override
    public Flux<Car> showCars() {
        return carRepository.findAll();
    }

    @Override
    public Mono<Car> findCar(String id) {
        return carRepository.findById(id);
    }

    @Override
    public void outCar(Car car) {
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
    public Mono<Motorcycle> findMotorcycle(String id) {
        return motorcycleRepository.findById(id);
    }

    @Override
    public Flux<Motorcycle> showMotorcycles() {
        return motorcycleRepository.findAll();
    }

    @Override
    public void outMotorcycle(Motorcycle motorcycle) {
        motorcycle.setParking(false);
    }
}
