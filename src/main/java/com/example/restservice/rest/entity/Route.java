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
	private Station start;
	private Station end;
	private List<Station> stations;
	private List<Vehicle> vehicles;
}
