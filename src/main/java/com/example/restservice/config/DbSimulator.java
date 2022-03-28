package com.example.restservice.config;

import com.example.restservice.rest.entity.Route;
import com.example.restservice.rest.entity.Vehicle;
import com.example.restservice.rest.entity.Station;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * This is only for db simulation.
 * Please delete it once it's no longer needed.
 */
@Component
public class DbSimulator {

    //imagine this is the db;
    private final List<Route> listOfDBRoutes = new ArrayList<>();
    private final List<Vehicle> listOfTransportVehicles = new ArrayList<>();

    //Populates something
    @PostConstruct
    private void init() {
        Vehicle treishTrei =
                new Vehicle(3301, 25, 85, "33");
        Vehicle treishTreiScurt =
                new Vehicle(3302, 25, 45, "33 short bus");
        Vehicle treishTreiAniversar=
                new Vehicle(3303, 25, 85, "33 EDITIE LIMITATA");

        listOfTransportVehicles.addAll(Arrays.asList(treishTrei, treishTreiScurt, treishTreiAniversar));

        final Station caleaSagului = new Station("Calea Sagului");
        final Station catetrala = new Station("Catetrala");

        listOfDBRoutes.addAll(Collections.singletonList(new Route(9933, caleaSagului, catetrala,
                Arrays.asList(caleaSagului, catetrala), new ArrayList<>(listOfTransportVehicles))));
    }

    public List<Route> getRoutes() {
        return listOfDBRoutes;
    }

    public List<Vehicle> getTransportVehicles() {
        return listOfTransportVehicles;
    }
}
