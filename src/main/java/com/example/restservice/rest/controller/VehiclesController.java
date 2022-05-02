package com.example.restservice.rest.controller;

import com.example.restservice.repositories.VehiclesRepository;
import com.example.restservice.rest.entity.Vehicle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class VehiclesController {

    @Autowired
    private VehiclesRepository vehiclesRepository;

    final Logger logger = LoggerFactory.getLogger(VehiclesController.class);

    public Vehicle getVehicleById(final String id) {
        return vehiclesRepository.findById(id).orElse(null);
    }

    public List<Vehicle> getVehicles() {
        return vehiclesRepository.findAll();
    }

    public Vehicle modifyVehicle(final String id, final int numberOfSeats, final int gasTank,
            final String descriptionOfVehicle) {
        final Vehicle vehicle = vehiclesRepository.findById(id)
                .orElse(new Vehicle());

        vehicle.setId(id);
        vehicle.setNumberOfSeats(numberOfSeats);
        vehicle.setGasTank(gasTank);
        vehicle.setDescriptionOfVehicle(descriptionOfVehicle);

        vehiclesRepository.save(vehicle);
        return vehicle;
    }

    public Vehicle deleteVehicle(final String id) {
        final Optional<Vehicle> vehicle = vehiclesRepository.findById(id);
		vehiclesRepository.deleteById(id);
        return vehicle.orElse(null);
    }
}
