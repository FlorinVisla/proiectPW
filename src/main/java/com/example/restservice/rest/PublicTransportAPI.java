package com.example.restservice.rest;

import com.example.restservice.rest.entity.Vehicle;
import com.example.restservice.rest.entity.Route;
import com.example.restservice.rest.controller.PublicTransportController;
import com.example.restservice.rest.controller.RoutesController;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PublicTransportAPI {

	@Autowired
	private PublicTransportController publicTransportController;

	@Autowired
	private RoutesController routesController;

	final Logger logger = LoggerFactory.getLogger(PublicTransportAPI.class);

	@Operation(summary = "Fetches details about a vehicle based on ID", tags = "Vehicles endpoints")
	@GetMapping("/vehicle/{id}")
	public Vehicle getVehicleById(@PathVariable(value = "id") final long id) {

		logger.info("/Vehicle was called with id {}", id);
		return publicTransportController.getVehicleById(id);
	}

	@Operation(summary = "Fetches details about all vehicles", tags = "Vehicles endpoints")
	@GetMapping("/vehicles")
	public Vehicle getVehicles() {

		logger.info("/vehicles was called");
		return null;
	}

	@Operation(summary = "Adds or changes a vehicle based on ID", tags = "Vehicles endpoints")
	@PutMapping("/vehicle/{id}")
	public Vehicle addVehicleAndReturnId(
			@PathVariable(value = "id") final long id,
			@RequestParam(value = "numberOfSeats", defaultValue = "25") final int numberOfSeats,
			@RequestParam(value = "gasTank", defaultValue = "80") final int gasTank,
			@RequestParam(value = "descriptionOfVehicle", defaultValue = "") final String descriptionOfVehicle) {

		logger.info("/putTransportVehicle was called with id {}", id);
		return publicTransportController.addVehicleAndReturnId(id, numberOfSeats, gasTank, descriptionOfVehicle);
	}

	@Operation(summary = "Deletes a vehicle based on ID", tags = "Vehicles endpoints")
	@DeleteMapping("/vehicle/{id}")
	public Vehicle deleteVehicle(@PathVariable(value = "id") final long id) {

		logger.info("/deleteTransportVehicle was called with id {}", id);
		return null;
	}

	@Operation(summary = "Retrieves information about all the routes", tags = "Routes endpoints")
	@GetMapping("/routes")
	public List<Route> getAllRoutes() {

		return null;
	}

	@Operation(summary = "Fetches details about a route based on ID", tags = "Routes endpoints")
	@GetMapping("/route/{id}")
	public Route getRouteById(@PathVariable(value = "id") final long id) {

		logger.info("/getRoute was called with id {}", id);
		return routesController.getRouteById(id);
	}

	@Operation(summary = "Adds or Modifies details about a route based on ID", tags = "Routes endpoints")
	@PutMapping("/route/{id}")
	public Route modifyRoute(@PathVariable(value = "id") final long id) {

		logger.info("/route was called with id {}", id);
		return routesController.modifyRoute(id);
	}

	// The ids are separated by comma
	@Operation(summary = "Fetches all the vehicles belonging to a route or multiple routes", tags = "Transport endpoints")
	@GetMapping("/transport/vehicles{ids}")
	public List<Vehicle> getVehiclesOnRoute(@PathVariable(value = "ids") final String ids) {

		logger.info("/transport/vehicles was called with ids {}", ids);
		//to do move this into controller
		final List<Long> listAsLongs = Arrays.stream(ids.split(",")).map(Long::valueOf).collect(Collectors.toList());
		return null;
	}

	// The ids are separated by comma
	@Operation(summary = "Fetches all the routes used by the specified vehicles", tags = "Transport endpoints")
	@GetMapping("/transport/routes/{ids}")
	public List<Route> getRoutesUsedByVehicles(@PathVariable(value = "ids") final String ids) {

		logger.info("/transport/routes/ was called with ids {}", ids);
		//to do move this into controller
		final List<Long> listAsLongs = Arrays.stream(ids.split(",")).map(Long::valueOf).collect(Collectors.toList());
		return null;
	}

	// todo
	/**
	 * Will modify the vehicles using a route (by id).
	 * Will take ID of route and a LIST (JSON) of Vehicles (will match based on id)
	 * and modify their information. Will probably return a 400 if specified vehicles aren't belonging to the route
	 */
	@Operation(summary = "Modifies vehicles on a route, or/and adds vehicles to a route", tags = "Transport endpoints")
	@PostMapping("/transport/vehicles/{id}")
	public Route modifyRouteWithVehicles(@PathVariable(value = "id") final long id) {

		logger.info("/transport/vehicles/ was called with id {}", id);
		return routesController.modifyRoute(id);
	}

	@Operation(summary = "Retrieves all information about the transport system (routes and vehicles)", tags = "Transport endpoints")
	@GetMapping("/transport")
	public Route getTransport() {
		//get all info about the vehicles and routes
		return null;
	}
}
