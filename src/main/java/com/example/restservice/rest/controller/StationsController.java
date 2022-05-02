package com.example.restservice.rest.controller;

import com.example.restservice.repositories.StationRepository;
import com.example.restservice.rest.entity.Station;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class StationsController {

    final Logger logger = LoggerFactory.getLogger(StationsController.class);

    @Autowired
    private StationRepository stationRepository;

    public List<Station> getStations() {
        return stationRepository.findAll();
    }

    public Station getStation(final String id) {
        return stationRepository.findById(id).orElse(null);
    }

    public Station addStation(final String id, final String location) {
        final Station station = stationRepository.findById(id).orElse(new Station());
        station.setId(id);
        station.setLocation(location);
        stationRepository.save(station);
        return station;
    }

    public Station deleteStation(final String id) {
        final Station station = stationRepository.findById(id).orElse(null);
        stationRepository.deleteById(id);
        return station;
    }

}
