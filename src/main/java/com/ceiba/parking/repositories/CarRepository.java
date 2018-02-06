package com.ceiba.parking.repositories;

import com.ceiba.parking.domain.Car;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CarRepository extends ReactiveMongoRepository<Car, String> {

}
