package com.example.restservice.rest.controller;

import com.example.restservice.config.DbSimulator;
import com.example.restservice.rest.entity.PublicTransportVehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PublicTransportController {

    @Autowired
    private DbSimulator dbSimulator;

    public PublicTransportVehicle getVehicleById(final long id) {
		/*
		Here we'll do the logic. For this example we'll probably read from the db
		 */
        return dbSimulator.getTransportVehicles().stream().filter(transportVehicle -> transportVehicle.getId() == id)
                .findAny().orElse(null);
    }

    public PublicTransportVehicle addVehicleAndReturnId(final long id, final int numberOfSeats, final int gasTank,
            final String descriptionOfVehicle) {
		/*
		Here we'll do the logic. For this example we'll probably put stuff in the db
		 */
        final PublicTransportVehicle transportVehicle =
                new PublicTransportVehicle(id, numberOfSeats, gasTank, descriptionOfVehicle);
        dbSimulator.getTransportVehicles().add(transportVehicle);
        return transportVehicle;
    }
}
