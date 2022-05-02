package com.example.restservice.repositories;

import com.example.restservice.rest.entity.Station;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StationRepository extends MongoRepository<Station, String> {

}