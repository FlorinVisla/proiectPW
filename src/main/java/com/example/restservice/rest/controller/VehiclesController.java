package com.example.restservice.rest.controller;

import com.example.restservice.repositories.VehiclesRepository;
import com.example.restservice.rest.entity.Vehicle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class VehiclesController {

    @Autowired
    private VehiclesRepository vehiclesRepository;

    final Logger logger = LoggerFactory.getLogger(VehiclesController.class);

    public Vehicle getVehicleById(final long id) {
        return vehiclesRepository.findById(id);
    }

    public Vehicle addVehicleAndReturnId(final long id, final int numberOfSeats, final int gasTank,
            final String descriptionOfVehicle) {
        final Vehicle vehicle = new Vehicle(id, numberOfSeats, gasTank, descriptionOfVehicle);
        vehiclesRepository.save(vehicle);
        return vehicle;
    }

    public Vehicle deleteVehicle(final long id) {
		/*
		Here we'll do the logic. For this example we'll probably put stuff in the db
		 */
        return null;
    }
}
