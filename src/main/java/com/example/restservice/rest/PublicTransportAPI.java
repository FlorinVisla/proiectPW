package com.example.restservice.rest;

import com.example.restservice.rest.controller.StationsController;
import com.example.restservice.rest.controller.TransportController;
import com.example.restservice.rest.entity.Station;
import com.example.restservice.rest.entity.TransportResponse;
import com.example.restservice.rest.entity.TransportRoute;
import com.example.restservice.rest.entity.Vehicle;
import com.example.restservice.rest.entity.Route;
import com.example.restservice.rest.controller.VehiclesController;
import com.example.restservice.rest.controller.RoutesController;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PublicTransportAPI {

	private final String X_API_KEY = "683779e5-7e06-55bd-bd21-46c169e26aef";

	@Autowired
	private VehiclesController vehiclesController;

	@Autowired
	private RoutesController routesController;

	@Autowired
	private StationsController stationsController;

	@Autowired
	private TransportController transportController;

	final Logger logger = LoggerFactory.getLogger(PublicTransportAPI.class);

	@Operation(summary = "Fetches details about a vehicle based on ID", tags = "Vehicles endpoints")
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/vehicle/{id}")
	@ApiImplicitParam(name = "X-Api-Key", allowableValues = X_API_KEY, required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "xxxx")
	public Vehicle getVehicleById(@PathVariable(value = "id") final String id) {

		return vehiclesController.getVehicleById(id);
	}

	@Operation(summary = "Fetches details about all vehicles", tags = "Vehicles endpoints")
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/vehicles")
	@ApiImplicitParam(name = "x-api-Key", allowableValues = X_API_KEY, required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "xxxx")
	public List<Vehicle> getVehicles() {

		return vehiclesController.getVehicles();
	}

	@Operation(summary = "Adds or changes a vehicle based on ID", tags = "Vehicles endpoints")
	@CrossOrigin(origins = "http://localhost:3000")
	@PutMapping("/vehicle/{id}")
	public Vehicle addVehicleAndReturnId(
			@PathVariable(value = "id") final String id,
			@RequestParam(value = "numberOfSeats", defaultValue = "25") final int numberOfSeats,
			@RequestParam(value = "gasTank", defaultValue = "80") final int gasTank,
			@RequestParam(value = "descriptionOfVehicle", defaultValue = "") final String descriptionOfVehicle) {

		return vehiclesController.modifyVehicle(id, numberOfSeats, gasTank, descriptionOfVehicle);
	}

	@Operation(summary = "Deletes a vehicle based on ID", tags = "Vehicles endpoints")
	@CrossOrigin(origins = "http://localhost:3000")
	@DeleteMapping("/vehicle/{id}")
	public Vehicle deleteVehicle(@PathVariable(value = "id") final String id) {

		return vehiclesController.deleteVehicle(id);
	}

	@Operation(summary = "Retrieves information about all the routes", tags = "Routes endpoints")
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/routes")
	public List<Route> getAllRoutes() {
		return routesController.getRoutes();
	}

	@Operation(summary = "Fetches details about a route based on ID", tags = "Routes endpoints")
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/route/{id}")
	public Route getRouteById(@PathVariable(value = "id") final String id) {

		return routesController.getRouteById(id);
	}

	@Operation(summary = "Adds or Modifies details about a route based on ID", tags = "Routes endpoints")
	@CrossOrigin(origins = "http://localhost:3000")
	@PutMapping("/route/{id}")
	public Route modifyRoute(@PathVariable(value = "id") final String id,
			@RequestParam(value = "description") final String description) {

		return routesController.modifyRoute(id, description);
	}

	@Operation(summary = "Deletes a route based on ID", tags = "Routes endpoints")
	@CrossOrigin(origins = "http://localhost:3000")
	@DeleteMapping("/route/{id}")
	public Route deleteRoute(@PathVariable(value = "id") final String id) {

		return routesController.deleteRoute(id);
	}

	@Operation(summary = "Fetches details about all stops(stations)", tags = "Stops endpoints")
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("stations")
	public List<Station> getStations() {

		return stationsController.getStations();
	}

	@Operation(summary = "Fetches details about a stop(station) based on ID", tags = "Stops endpoints")
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("station/{id}")
	public Station getStationById(@PathVariable(value = "id") final String id) {

		return stationsController.getStation(id);
	}

	@Operation(summary = "Adds or Modifies details about a stop(station) based on ID", tags = "Stops endpoints")
	@CrossOrigin(origins = "http://localhost:3000")
	@PutMapping("station/{id}")
	public Station Add(@PathVariable(value = "id") final String id,
			@RequestParam(value = "location") final String location) {

		return stationsController.addStation(id, location);
	}

	@Operation(summary = "Deletes a stop(station)", tags = "Stops endpoints")
	@CrossOrigin(origins = "http://localhost:3000")
	@DeleteMapping("station/{id}")
	public Station deleteStationById(@PathVariable(value = "id") final String id) {

		return stationsController.deleteStation(id);
	}

	@Operation(summary = "Modifies a transport route or adds a new one based on available routes", tags = "Transport endpoints")
	@CrossOrigin(origins = "http://localhost:3000")
	@PutMapping("/transport/route/{id}")
	public TransportRoute modifyTransportRoute(@PathVariable(value = "id") final String id,
			@RequestParam(value = "stationsIds") final String stationsIds) {

		return transportController.modifyTransportRoute(id, stationsIds);
	}

	@Operation(summary = "Retrieves a transport route information based on ID", tags = "Transport endpoints")
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/transport/route/{id}")
	public TransportRoute getTransportRoute(@PathVariable(value = "id") final String id) {

		return transportController.getTransportRoute(id);
	}

	@Operation(summary = "Adds vehicles to a transport route", tags = "Transport endpoints")
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/transport/vehicle/{id}")
	public TransportRoute addVehiclesToRoute(@PathVariable(value = "id") final String id,
			@RequestParam(value = "vehicleIds") final String vehicleIds) {

		return transportController.addVehicles(id, vehicleIds);
	}

	@Operation(summary = "Removes vehicles from a transport route", tags = "Transport endpoints")
	@CrossOrigin(origins = "http://localhost:3000")
	@DeleteMapping("/transport/vehicle/{id}")
	public TransportRoute removeVehiclesFromRoute(@PathVariable(value = "id") final String id,
			@RequestParam(value = "vehicleIds") final String vehicleIds) {

		return transportController.removeVehicles(id, vehicleIds);
	}

	@Operation(summary = "Retrieves all information about the transport system (routes and vehicles)", tags = "Transport endpoints")
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/transport")
	public TransportResponse getTransport() {

		return transportController.getTransport();
	}
}
