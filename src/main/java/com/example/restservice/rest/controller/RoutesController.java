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

    public Route deleteRoute(final String id) {
        final Optional<Route> route = routesRepository.findById(id);
        routesRepository.deleteById(id);
        return route.orElse(null);
    }

    public List<Route> getRoutes() {
        return routesRepository.findAll();
    }

}
