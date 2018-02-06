package com.ceiba.parking.repositories;

import com.ceiba.parking.domain.Motorcycle;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MotorcycleRepository extends ReactiveMongoRepository<Motorcycle, String> {

}
