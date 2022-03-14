package com.example.restservice.config;

import com.example.restservice.rest.entity.PublicTransportVehicle;
import com.example.restservice.rest.entity.Route;
import com.example.restservice.rest.entity.Statie;
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
    private final List<PublicTransportVehicle> listOfTransportVehicles = new ArrayList<>();

    //Populates something
    @PostConstruct
    private void init() {
        PublicTransportVehicle treishTrei =
                new PublicTransportVehicle(3301, 25, 85, "33");
        PublicTransportVehicle treishTreiScurt =
                new PublicTransportVehicle(3302, 25, 45, "33 short bus");
        PublicTransportVehicle treishTreiAniversar=
                new PublicTransportVehicle(3303, 25, 85, "33 EDITIE LIMITATA");

        listOfTransportVehicles.addAll(Arrays.asList(treishTrei, treishTreiScurt, treishTreiAniversar));

        final Statie caleaSagului = new Statie("Calea Sagului");
        final Statie catetrala = new Statie("Catetrala");

        listOfDBRoutes.addAll(Collections.singletonList(new Route(9933, caleaSagului, catetrala,
                Arrays.asList(caleaSagului, catetrala), new ArrayList<>(listOfTransportVehicles))));
    }

    public List<Route> getRoutes() {
        return listOfDBRoutes;
    }

    public List<PublicTransportVehicle> getTransportVehicles() {
        return listOfTransportVehicles;
    }
}
