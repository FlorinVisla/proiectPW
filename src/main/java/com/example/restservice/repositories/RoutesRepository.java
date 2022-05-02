package com.example.restservice.repositories;

import com.example.restservice.rest.entity.Route;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoutesRepository extends MongoRepository<Route, String> {

}