package com.example.restservice.rest.controller;

import com.example.restservice.repositories.RoutesRepository;
import com.example.restservice.repositories.StationRepository;
import com.example.restservice.repositories.TransportRepository;
import com.example.restservice.repositories.VehiclesRepository;
import com.example.restservice.rest.entity.Route;
import com.example.restservice.rest.entity.Station;
import com.example.restservice.rest.entity.TransportRoute;
import com.example.restservice.rest.entity.Vehicle;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.server.ResponseStatusException;

import javax.xml.ws.WebServiceException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

// mega pint of todo here
@Controller
public class TransportController {

    final Logger logger = LoggerFactory.getLogger(TransportController.class);

    @Autowired
    private RoutesRepository routesRepository;

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private VehiclesRepository vehiclesRepository;

    @Autowired
    private TransportRepository transportRepository;

    public TransportRoute modifyTransportRoute(final String id, final String stationsIds) {

        final TransportRoute transportRoute = resolveTransportRoute(id);

        if (!stationsIds.contains(",")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "A route should contain at least two stations (separated by comma)", new WebServiceException());
        }

        final List<String> stationIdList = Arrays.asList(stationsIds.split(","));

        // We're doing it to maintain order ¯\_( ͡❛ ͜ʖ ͡❛)_/¯ !
        final List<Station> listOfDbStations = stationIdList
                .stream()
                .map(stationId -> stationRepository.findById(stationId).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if (listOfDbStations.size() != stationIdList.size()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "At least one station not present in the database", new WebServiceException());
        }

        final Station startStation = listOfDbStations.get(0);
        final Station endStation   = listOfDbStations.get(listOfDbStations.size() - 1);

        transportRoute.setStart(startStation);
        transportRoute.setEnd(endStation);
        transportRoute.setVehicles(new ArrayList<>());
        transportRoute.setStops(listOfDbStations);

        transportRepository.save(transportRoute);
        return transportRoute;
    }

    public TransportRoute addVehicles(final String id, final String vehicleIds) {
        final TransportRoute transportRoute = transportRepository.findById(id).orElse(null);
        if (transportRoute == null) {
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED,
                    "Transport route not found", new WebServiceException());
        }

        final List<Vehicle> vehicles =
                Lists.newArrayList(vehiclesRepository.findAllById(Arrays.stream(vehicleIds.split(",")).collect(Collectors.toList())));
        addVehiclesToRoute(transportRoute, vehicles);

        transportRepository.save(transportRoute);
        return transportRoute;
    }

    private void addVehiclesToRoute(final TransportRoute transportRoute, final List<Vehicle> dbVehicles) {
        final List<Vehicle> vehicles = transportRoute.getVehicles();
        dbVehicles.forEach(vehicle -> {
            if (!vehicles.contains(vehicle)) {
                logger.info("The vehicle {} was added to {}", vehicle.getId(), transportRoute.getId());
                vehicles.add(vehicle);
            }
        });
    }

    public TransportRoute removeVehicles(final String id, final String vehicleIds) {
        final TransportRoute transportRoute = transportRepository.findById(id).orElse(null);
        if (transportRoute == null) {
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED,
                    "Transport route not found", new WebServiceException());
        }

        final List<String> vehiclesToBeRemoved = Lists.newArrayList(vehicleIds.split(","));

        transportRoute.setVehicles(resolveVehiclesAfterDeletion(id, transportRoute, vehiclesToBeRemoved));

        transportRepository.save(transportRoute);
        return transportRoute;
    }

    private List<Vehicle> resolveVehiclesAfterDeletion(final String id, final TransportRoute transportRoute,
            final List<String> vehiclesToBeRemoved) {
        return transportRoute.getVehicles().stream().filter(vehicle -> {
            logger.info("vehicle {} removed from route {}", vehicle.getId(), id);
            return !vehiclesToBeRemoved.contains(vehicle.getId());
        }).collect(Collectors.toList());
    }

    private TransportRoute resolveTransportRoute(final String id) {
        TransportRoute transportRoute = transportRepository.findById(id).orElse(null);

        if (transportRoute == null) {
            final Route abstractRoute = routesRepository.findById(id).orElse(null);
            if (abstractRoute != null) {
                transportRoute = new TransportRoute(abstractRoute.getId(), abstractRoute.getDescription());
            }
            else throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Route not found in the DB", new WebServiceException());
        }
        return transportRoute;
    }

    public List<TransportRoute> getTransport() {
        return transportRepository.findAll();
    }

    // btw this could've just been done on the frontend part... maybe just remove it?
    // bagamias e 4 dimineata
    public List<TransportRoute> getRoutesUsedByVehicles(final String ids) {
//        final List<String> vehicleIds = Arrays.asList(ids.split(","));
//
//        List<TransportRoute> transportRoutes = transportRepository.findAll();
//        for (String id : vehicleIds) {
//            e.getV
//            transportRoutes.stream().filter(e -> e.getVehicles().stream().map(x -> x.getId()) == id))
//        }

        return null;
    }

//    public Route modifyRoute(final String id, final String vehicleIds, final String stationsIds)
//            throws ResponseStatusException {
//
//        final Route route = routesRepository.findById(id).orElse(new Route());
//
//        if (!stationsIds.contains(",")) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
//                    "A route should contain at least two stations", new WebServiceException());
//        }
//
//        final List<String> vehicleIdList = vehicleIds != null ? Arrays.asList(vehicleIds.split(",")) : Collections.emptyList();
//        final List<String> stationIdList = Arrays.asList(stationsIds.split(","));
//
//        final List<Vehicle> listOfDbVehicles = Lists.newArrayList(vehiclesRepository.findAllById(vehicleIdList));
//
//        // We're doing it to maintain order ¯\_( ͡❛ ͜ʖ ͡❛)_/¯ !
//        final List<Station> listOfDbStations = stationIdList
//                .stream()
//                .map(stationId -> stationRepository.findById(stationId).orElse(null))
//                .filter(Objects::nonNull)
//                .collect(Collectors.toList());
//
//        if (listOfDbVehicles.size() != vehicleIdList.size() || listOfDbStations.size() != stationIdList.size()) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
//                    "At least one station or vehicle not present in the database", new WebServiceException());
//        }
//
//        final Station startStation = listOfDbStations.get(0);
//        final Station endStation   = listOfDbStations.get(listOfDbStations.size() - 1);
//
//        if (startStation == null || endStation == null) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
//                    "Start or end stations are not present in the database", new WebServiceException());
//        }
//
//        route.setId(id);
//        route.setStart(startStation);
//        route.setEnd(endStation);
//        route.setVehicles(listOfDbVehicles);
//        route.setStops(listOfDbStations);
//
//        routesRepository.save(route);
//        return route;
//    }

}
