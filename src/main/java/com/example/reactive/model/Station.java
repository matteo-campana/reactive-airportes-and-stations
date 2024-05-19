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
public class Station {

    // {
    // "id": "KAFF",
    // "site": "Air Force Academy Arfld",
    // "state": "CO",
    // "country": "US",
    // "latitude": 38.971,
    // "longitude": -104.816,
    // "elevation:: 2003
    // },

    private String id;
    private String site;
    private String state;
    private String country;
    private double latitude;
    private double longitude;
    private double elevation;

}
