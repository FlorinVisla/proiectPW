package com.example.restservice.rest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransportResponse {

    private List<TransportRoute> transportRoutes;
    private List<Vehicle> unusedVehicles;
}
