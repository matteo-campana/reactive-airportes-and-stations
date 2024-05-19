package com.example.reactive.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.reactive.model.Airport;
import com.example.reactive.model.Station;
import com.example.reactive.service.AirportsAndStationsService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/fabrick/v1.0/")
@AllArgsConstructor
public class AirportsAndStationsController {

    @Autowired
    private final AirportsAndStationsService airportsAndStationsService;

    // GET /api/fabrick/v1.0/airports/{airportId}/stations?closestBy={value?0.0}
    // [
    // {
    // "id": "KAFF",
    // "site": "Air Force Academy Arfld",
    // "state": "CO",
    // "country": "US",
    // "latitude": 38.971,
    // "longitude": -104.816,
    // "elevation:: 2003
    // },
    // ...
    // ]

    private static final Logger logger = LoggerFactory.getLogger(AirportsAndStationsController.class);

    @Cacheable(value = "stations", key = "#airportId + #closestBy", unless = "#result.size() == 0")
    @GetMapping(value = "/airports/{airportId}/stations", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Station> GetStations(@PathVariable String airportId,
            @RequestParam(required = false, defaultValue = "0.0") double closestBy)
            throws UnsupportedEncodingException {

        logger.info("Requesting stations for airportId: " + airportId + " closestBy: " + closestBy);

        List<Station> stations = airportsAndStationsService.GetClosestByStations(airportId, closestBy);

        logger.info("Returning " + stations.size() + " stations");

        // return Flux.fromIterable(stations);

        return Flux.fromIterable(stations)
                .flatMap(Flux::just);
    }

    // GET /api/fabrick/v1.0/stations/{stationId}/airports?closestBy={value?0.0}
    // [
    // {
    // "id": "KDEN",
    // "name: "DENVER/DENVER_INTL",
    // "state": "CO",
    // "country": "US",
    // "latitude": 39.8617,
    // "longitude": -104.6732,
    // "elevation": 1656.6
    // },
    // ...
    // ]

    @Cacheable(value = "airports", key = "#stationId + #closestBy", unless = "#result.size() == 0")
    @GetMapping(value = "/stations/{stationId}/airports", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Airport> GetAirports(@PathVariable String stationId,
            @RequestParam(required = false, defaultValue = "0.0") double closestBy)
            throws UnsupportedEncodingException {

        logger.info("Requesting airports for stationId: " + stationId + " closestBy: " + closestBy);

        List<Airport> airports = airportsAndStationsService.GetClosestByAirports(stationId, closestBy);

        logger.info("Returning " + airports.size() + " airports");

        // return Flux.fromIterable(airports);

        return Flux.fromIterable(airports)
                .flatMap(Flux::just);

    }

}
