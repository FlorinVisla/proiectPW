package com.example.restservice.rest.controller;

import com.example.restservice.rest.entity.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

@Controller
public class RoutesController {

    final Logger logger = LoggerFactory.getLogger(RoutesController.class);

    public Route getRouteById(final long id) {

		/*
		Here we'll do the logic. For this example we'll probably read from the db
		 */
        return null;
    }

    public Route modifyRoute(final long routeId) {
		/*
		Here we'll do the logic. For this example we'll probably read from the db
		 */
//        final RouteWithVehicles route = dbSimulator.getRoutes().stream().filter(e -> e.getId() == routeId).findAny().orElse(null);
//        final Vehicle transportVehicle = dbSimulator.getTransportVehicles()
//                .stream()
//                .filter(e -> e.getId() == idVehicul)
//                .findAny()
//                .orElse(null);
//
//        if (route != null && transportVehicle != null) {
//            final List<Vehicle> vehiculeCareFolosescRuta =
//                    Optional.ofNullable(route.getVehicles()).orElse(new ArrayList<>());
//            vehiculeCareFolosescRuta.add(transportVehicle);
//            route.setVehicles(vehiculeCareFolosescRuta);
//            logger.info("Added vehicle with id: {} to route with id: {}", idVehicul, routeId);
//            return route;
//        }

//        logger.warn("Couldn't add the vehicle to the route. route: {}, vehicle: {}", routeId, idVehicul);
        return null;
    }
}
