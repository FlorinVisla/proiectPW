package com.example.restservice.rest.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Route {

	private long id;
	private Statie punctDePlecare;
	private Statie punctDeSosire;
	private List<Statie> listaDeStatii;
	private List<PublicTransportVehicle> vehiculeCareFolosescRuta;
}
