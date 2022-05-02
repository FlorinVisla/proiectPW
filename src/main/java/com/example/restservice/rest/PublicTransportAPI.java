package com.example.restservice.rest;

import com.example.restservice.rest.controller.StationsController;
import com.example.restservice.rest.controller.TransportController;
import com.example.restservice.rest.entity.Station;
import com.example.restservice.rest.entity.TransportRoute;
import com.example.restservice.rest.entity.Vehicle;
import com.example.restservice.rest.entity.Route;
import com.example.restservice.rest.controller.VehiclesController;
import com.example.restservice.rest.controller.RoutesController;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PublicTransportAPI {

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
	@GetMapping("/vehicle/{id}")
	public Vehicle getVehicleById(@PathVariable(value = "id") final String id) {

		return vehiclesController.getVehicleById(id);
	}

	@Operation(summary = "Fetches details about all vehicles", tags = "Vehicles endpoints")
	@GetMapping("/vehicles")
	public List<Vehicle> getVehicles() {

		return vehiclesController.getVehicles();
	}

	@Operation(summary = "Adds or changes a vehicle based on ID", tags = "Vehicles endpoints")
	@PutMapping("/vehicle/{id}")
	public Vehicle addVehicleAndReturnId(
			@PathVariable(value = "id") final String id,
			@RequestParam(value = "numberOfSeats", defaultValue = "25") final int numberOfSeats,
			@RequestParam(value = "gasTank", defaultValue = "80") final int gasTank,
			@RequestParam(value = "descriptionOfVehicle", defaultValue = "") final String descriptionOfVehicle) {

		return vehiclesController.modifyVehicle(id, numberOfSeats, gasTank, descriptionOfVehicle);
	}

	@Operation(summary = "Deletes a vehicle based on ID", tags = "Vehicles endpoints")
	@DeleteMapping("/vehicle/{id}")
	public Vehicle deleteVehicle(@PathVariable(value = "id") final String id) {

		return vehiclesController.deleteVehicle(id);
	}

	@Operation(summary = "Retrieves information about all the routes", tags = "Routes endpoints")
	@GetMapping("/routes")
	public List<Route> getAllRoutes() {
		return routesController.getRoutes();
	}

	@Operation(summary = "Fetches details about a route based on ID", tags = "Routes endpoints")
	@GetMapping("/route/{id}")
	public Route getRouteById(@PathVariable(value = "id") final String id) {

		return routesController.getRouteById(id);
	}

	@Operation(summary = "Adds or Modifies details about a route based on ID", tags = "Routes endpoints")
	@PutMapping("/route/{id}")
	public Route modifyRoute(@PathVariable(value = "id") final String id,
			@RequestParam(value = "description") final String description) {

		return routesController.modifyRoute(id, description);
	}

	@Operation(summary = "Deletes a route based on ID", tags = "Routes endpoints")
	@DeleteMapping("/route/{id}")
	public Route deleteRoute(@PathVariable(value = "id") final String id) {

		return routesController.deleteRoute(id);
	}

	@Operation(summary = "Fetches details about all stops(stations)", tags = "Stops endpoints")
	@GetMapping("stations")
	public List<Station> getStations() {

		return stationsController.getStations();
	}

	@Operation(summary = "Fetches details about a stop(station) based on ID", tags = "Stops endpoints")
	@GetMapping("station/{id}")
	public Station getStationById(@PathVariable(value = "id") final String id) {

		return stationsController.getStation(id);
	}

	@Operation(summary = "Adds or Modifies details about a stop(station) based on ID", tags = "Stops endpoints")
	@PutMapping("station/{id}")
	public Station Add(@PathVariable(value = "id") final String id,
			@RequestParam(value = "location") final String location) {

		return stationsController.addStation(id, location);
	}

	@Operation(summary = "Deletes a stop(station)", tags = "Stops endpoints")
	@DeleteMapping("station/{id}")
	public Station deleteStationById(@PathVariable(value = "id") final String id) {

		return stationsController.deleteStation(id);
	}

	//todo
	// The ids are separated by comma
	@Operation(summary = "Fetches all the vehicles belonging to a route or multiple routes", tags = "Transport endpoints")
	@GetMapping("/transport/vehicles/{ids}")
	public List<Vehicle> getVehiclesOnRoute(@PathVariable(value = "ids") final String ids) {

		return null;
	}

	//todo
	// The ids are separated by comma
	@Operation(summary = "Fetches all the routes used by the specified vehicles", tags = "Transport endpoints")
	@GetMapping("/transport/routes/{ids}")
	public List<TransportRoute> getRoutesUsedByVehicles(@PathVariable(value = "ids") final String ids) {

		return transportController.getRoutesUsedByVehicles(ids);
	}

	@Operation(summary = "Modifies a transport route or adds a new one based on available routes", tags = "Transport endpoints")
	@PutMapping("/transport/route/{id}")
	public TransportRoute modifyTransportRoute(@PathVariable(value = "id") final String id,
			@RequestParam(value = "stationsIds") final String stationsIds) {

		return transportController.modifyTransportRoute(id, stationsIds);
	}

	@Operation(summary = "Adds vehicles to a transport route", tags = "Transport endpoints")
	@PostMapping("/transport/vehicle/{id}")
	public TransportRoute addVehiclesToRoute(@PathVariable(value = "id") final String id,
			@RequestParam(value = "vehicleIds") final String vehicleIds) {

		return transportController.addVehicles(id, vehicleIds);
	}

	@Operation(summary = "Removes vehicles from a transport route", tags = "Transport endpoints")
	@DeleteMapping("/transport/vehicle/{id}")
	public TransportRoute removeVehiclesFromRoute(@PathVariable(value = "id") final String id,
			@RequestParam(value = "vehicleIds") final String vehicleIds) {

		return transportController.removeVehicles(id, vehicleIds);
	}

	@Operation(summary = "Retrieves all information about the transport system (routes and vehicles)", tags = "Transport endpoints")
	@GetMapping("/transport")
	public List<TransportRoute> getTransport() {

		return transportController.getTransport();
	}
}
