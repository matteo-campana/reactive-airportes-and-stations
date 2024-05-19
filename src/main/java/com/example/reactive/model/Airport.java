package com.example.reactive.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonSerialize
@Builder
public class Airport {

    // {
    // "id": "KDEN",
    // "name: "DENVER/DENVER_INTL",
    // "state": "CO",
    // "country": "US",
    // "latitude": 39.8617,
    // "longitude": -104.6732,
    // "elevation": 1656.6
    // },

    private String id;
    private String name;
    private String state;
    private String country;
    private double latitude;
    private double longitude;
    private double elevation;

}
