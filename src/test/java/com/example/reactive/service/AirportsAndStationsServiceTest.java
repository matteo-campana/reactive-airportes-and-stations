package com.example.reactive.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import com.example.reactive.model.Airport;
import com.example.reactive.model.Station;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AirportsAndStationsServiceTest {

    private AirportsAndStationsService airportsAndStationsService;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        airportsAndStationsService = new AirportsAndStationsService();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testGetClosestByAirports() throws IOException {
        String stationId = "KORD";
        double closestBy = 0.5;

        String jsonResponse = new String(
                Files.readAllBytes(Paths.get(
                        new ClassPathResource("airports-response-KORD-0.1.json").getURI())));

        List<Airport> expectedAirports = Arrays.asList(objectMapper.readValue(jsonResponse, Airport[].class));

        // Mockito.when(airportsAndStationsService.GetClosestByAirports(stationId,
        // closestBy))
        // .thenReturn(expectedAirports);

        List<Airport> actualAirports = airportsAndStationsService.GetClosestByAirports(stationId, closestBy);

        assertEquals(expectedAirports, actualAirports);
    }

    @Test
    void testGetClosestByAirportsWithNullStation() throws UnsupportedEncodingException {
        String stationId = null;
        double closestBy = 0.5;

        // Mockito.when(airportsAndStationsService.GetClosestByAirports(stationId,
        // closestBy))
        // .thenThrow(new IllegalArgumentException("Station ID cannot be null"));

        List<Airport> actualAirports = airportsAndStationsService.GetClosestByAirports(stationId, closestBy);

        List<Airport> expectedAirports = Arrays.asList();

        assertEquals(expectedAirports, actualAirports);
    }

    @Test
    void testGetClosestByStations() throws IOException {
        String airportId = "KMCI";
        double closestBy = 0.5;

        String jsonResponse = new String(
                Files.readAllBytes(Paths.get(
                        new ClassPathResource("stations-response-KMCI-0.1.json").getURI())));

        List<Station> expectedStations = Arrays.asList(objectMapper.readValue(jsonResponse, Station[].class));

        // Mockito.when(airportsAndStationsService.GetClosestByStations(airportId,
        // closestBy))
        // .thenReturn(expectedStations);

        List<Station> actualStations = airportsAndStationsService.GetClosestByStations(airportId, closestBy);

        assertEquals(expectedStations, actualStations);
    }

    @Test
    void testGetClosestByStationsWithNullAirport() throws UnsupportedEncodingException {
        String airportId = null;
        double closestBy = 0.5;

        List<Station> actualAirports = airportsAndStationsService.GetClosestByStations(airportId, closestBy);

        List<Airport> expectedAirports = Arrays.asList();

        assertEquals(expectedAirports, actualAirports);
    }
}
