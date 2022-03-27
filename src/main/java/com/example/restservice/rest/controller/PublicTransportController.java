package com.example.restservice.rest.controller;

import com.example.restservice.config.DbSimulator;
import com.example.restservice.rest.entity.Vehicle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PublicTransportController {

    @Autowired
    private DbSimulator dbSimulator;

    final Logger logger = LoggerFactory.getLogger(PublicTransportController.class);

    public Vehicle getVehicleById(final long id) {
		/*
		Here we'll do the logic. For this example we'll probably read from the db
		 */
        return dbSimulator.getTransportVehicles().stream().filter(transportVehicle -> transportVehicle.getId() == id)
                .findAny().orElse(new Vehicle(1, 20, 10, "Autobuz de teste"));
    }

    public Vehicle addVehicleAndReturnId(final long id, final int numberOfSeats, final int gasTank,
            final String descriptionOfVehicle) {
		/*
		Here we'll do the logic. For this example we'll probably put stuff in the db
		 */
        final Vehicle transportVehicle =
                new Vehicle(id, numberOfSeats, gasTank, descriptionOfVehicle);
        dbSimulator.getTransportVehicles().add(transportVehicle);
        return transportVehicle;
    }

    public Vehicle deleteVehicle(final long id) {
		/*
		Here we'll do the logic. For this example we'll probably put stuff in the db
		 */
        return null;
    }
}
