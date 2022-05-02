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
public class TransportRoute extends Route{

	private Station start;
	private Station end;
	private List<Station> stops;
	private List<Vehicle> vehicles;

	public TransportRoute(final String id, final String description) {
		super(id, description);
	}
}
