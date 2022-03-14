package com.example.restservice.rest.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PublicTransportVehicle {

	private long id;
	private int numberOfSeats;
	private int gasTank;
	private String descriptionOfVehicle;
}
