package com.example.restservice.rest.controller;

import com.example.restservice.repositories.RoutesRepository;
import com.example.restservice.rest.entity.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.List;
import java.util.Optional;

@Controller
public class RoutesController {

    final Logger logger = LoggerFactory.getLogger(RoutesController.class);

    @Autowired
    private RoutesRepository routesRepository;

    public Route getRouteById(final String id) {
		return routesRepository.findById(id).orElse(null);
    }

    public Route modifyRoute(final String id, final String description) {

        final Route route = routesRepository.findById(id).orElse(new Route());
        route.setDescription(description);
        route.setId(id);

        routesRepository.save(route);
        return route;
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

    public Route deleteRoute(final String id) {
        final Optional<Route> route = routesRepository.findById(id);
        routesRepository.deleteById(id);
        return route.orElse(null);
    }

    public List<Route> getRoutes() {
        return routesRepository.findAll();
    }

}
