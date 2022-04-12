package com.example.restservice.repositories;

import com.example.restservice.rest.entity.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VehiclesRepository extends MongoRepository<Vehicle, String> {

    // example
    Vehicle findById(long id);
}