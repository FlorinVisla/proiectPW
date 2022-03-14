package com.example.restservice.rest;

import com.example.restservice.rest.entity.PublicTransportVehicle;
import com.example.restservice.rest.entity.Route;
import com.example.restservice.rest.controller.PublicTransportController;
import com.example.restservice.rest.controller.RoutesController;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicTransportAPI {

	@Autowired
	private PublicTransportController publicTransportController;

	@Autowired
	private RoutesController routesController;

	final Logger logger = LoggerFactory.getLogger(PublicTransportAPI.class);

	@Operation(summary = "Fetches details about a transport based on ID stored in the DB", tags = "Transport API endpoints")
	@GetMapping("/getTransportVehicle")
	public PublicTransportVehicle getVehicleById(@RequestParam(value = "id") final long id) {

		logger.info("/getTransportVehicle was called with id {}", id);
		return publicTransportController.getVehicleById(id);
	}

	@Operation(summary = "Adds or changes a transport based on ID stored in the DB", tags = "Transport API endpoints")
	@PutMapping("/putTransportVehicle")
	public PublicTransportVehicle addVehicleAndReturnId(
			@RequestParam(value = "id") final long id,
			@RequestParam(value = "numberOfSeats", defaultValue = "25") final int numberOfSeats,
			@RequestParam(value = "gasTank", defaultValue = "80") final int gasTank,
			@RequestParam(value = "descriptionOfVehicle", defaultValue = "") final String descriptionOfVehicle) {

		logger.info("/putTransportVehicle was called with id {}", id);
		return publicTransportController.addVehicleAndReturnId(id, numberOfSeats, gasTank, descriptionOfVehicle);
	}

	@Operation(summary = "Deletes a transport based on ID from the DB", tags = "Transport API endpoints")
	@DeleteMapping("/deleteTransportVehicle")
	public PublicTransportVehicle deleteVehicle(@RequestParam(value = "id") final long id) {

		//todo
		logger.info("/deleteTransportVehicle was called with id {}", id);
		return null;
	}

	@Operation(summary = "Fetches details about a route based on ID stored in the DB", tags = "Routes API endpoints")
	@GetMapping("/getRoute")
	public Route getRouteById(@RequestParam(value = "id") final long id) {

		logger.info("/getRoute was called with id {}", id);
		return routesController.getRouteById(id);
	}

	@Operation(summary = "Modifies details about a route", tags = "Routes API endpoints")
	@PostMapping("/postRoute")
	public Route modifyRoute(@RequestParam(value = "routeId") final long routeId,
			@RequestParam(value = "vehId") final long vehId) {

		logger.info("/postRoute was called with routeId {} and vehId {}", routeId, vehId);
		return routesController.modifyRoute(routeId, vehId);
	}
}
