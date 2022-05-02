package com.example.restservice.repositories;

import com.example.restservice.rest.entity.TransportRoute;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransportRepository extends MongoRepository<TransportRoute, String> {

}