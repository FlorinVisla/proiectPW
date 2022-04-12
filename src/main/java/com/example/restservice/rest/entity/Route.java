package com.example.restservice.rest.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Route {

	@Id
	private long id;

	private Station start;
	private Station end;
	private List<Station> stations;
	private List<Vehicle> vehicles;
}
