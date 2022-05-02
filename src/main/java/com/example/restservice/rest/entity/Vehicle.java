package com.example.restservice.rest.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Vehicle {

	@Id
	private String id;

	private int numberOfSeats;
	private int gasTank;
	private String descriptionOfVehicle;
}
