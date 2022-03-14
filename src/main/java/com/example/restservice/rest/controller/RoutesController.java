package com.example.restservice.rest.controller;

import com.example.restservice.config.DbSimulator;
import com.example.restservice.rest.entity.PublicTransportVehicle;
import com.example.restservice.rest.entity.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class RoutesController {

    @Autowired
    private DbSimulator dbSimulator;

    final Logger logger = LoggerFactory.getLogger(RoutesController.class);

    public Route getRouteById(final long id) {

		/*
		Here we'll do the logic. For this example we'll probably read from the db
		 */
        return dbSimulator.getRoutes().stream().filter(e -> e.getId() == id).findAny().orElse(null);
    }

    public Route modifyRoute(final long idRoute, final long idVehicul) {
		/*
		Here we'll do the logic. For this example we'll probably read from the db
		 */
        final Route route = dbSimulator.getRoutes().stream().filter(e -> e.getId() == idRoute).findAny().orElse(null);
        final PublicTransportVehicle transportVehicle = dbSimulator.getTransportVehicles()
                .stream()
                .filter(e -> e.getId() == idVehicul)
                .findAny()
                .orElse(null);

        if (route != null && transportVehicle != null) {
            final List<PublicTransportVehicle> vehiculeCareFolosescRuta =
                    Optional.ofNullable(route.getVehiculeCareFolosescRuta()).orElse(new ArrayList<>());
            vehiculeCareFolosescRuta.add(transportVehicle);
            route.setVehiculeCareFolosescRuta(vehiculeCareFolosescRuta);
            logger.info("Added vehicle with id: {} to route with id: {}", idVehicul, idRoute);
            return route;
        }

        logger.warn("Couldn't add the vehicle to the route. route: {}, vehicle: {}", idRoute, idVehicul);
        return null;
    }
}
