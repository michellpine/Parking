package com.ceiba.parking.services;

import com.ceiba.parking.domain.Car;
import com.ceiba.parking.domain.Motorcycle;
import com.ceiba.parking.domain.Vehicle;
import com.ceiba.parking.repositories.CarRepository;
import com.ceiba.parking.repositories.MotorcycleRepository;
import org.reactivestreams.Publisher;
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

    @Override
    public boolean canEnterVehicle(Vehicle vehicle) {
        if (vehicle.getLicense().toUpperCase().startsWith("A")
                && calendarGuard.getActualDay() != java.util.Calendar.MONDAY
                && calendarGuard.getActualDay() != java.util.Calendar.SUNDAY) {
            throw new IllegalArgumentException("Vehicle cannot enter, license begin for A and today is not available day for it");
        }
        return true;
    }

    //Cars Responsaility
    @Override
    @Transactional
    public Mono<Void> saveCar(Publisher<Car> vehicle) {
        if (carRepository.count().block() > CAR_CELLS) {
            throw new IllegalArgumentException("Vehicle cannot enter, there are not more cells available for cars");
        }
        return carRepository.saveAll(vehicle).then();
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
    public Mono<Void> saveMotorcycle(Publisher<Motorcycle> vehicle) {
        if(motorcycleRepository.count().block()>MOTORCYCLE_CELLS){
            throw new IllegalArgumentException("Vehicle cannot enter, there are not more cells available for motorcycles");
        }
        return motorcycleRepository.saveAll(vehicle).then();
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
