package com.example.reactive.controller;

import static org.mockito.Mockito.when;

import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.example.reactive.model.Airport;
import com.example.reactive.model.Station;
import com.example.reactive.service.AirportsAndStationsService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class AirportsAndStationsControllerTest {

    private WebTestClient webTestClient;

    private AirportsAndStationsService airportsAndStationsService;

    private AirportsAndStationsController airportsAndStationsController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        airportsAndStationsService = Mockito.mock(AirportsAndStationsService.class);
        airportsAndStationsController = new AirportsAndStationsController(airportsAndStationsService);
        webTestClient = WebTestClient.bindToController(airportsAndStationsController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testGetStations() throws Exception {
        String airportId = "KMCI";
        double closestBy = 0.5;

        String jsonResponse = new String(
                Files.readAllBytes(Paths.get(new ClassPathResource("stations-response-KMCI-0.1.json").getURI())));

        List<Station> stations = Arrays.asList(objectMapper.readValue(jsonResponse, Station[].class));

        when(airportsAndStationsService.GetClosestByStations(airportId, closestBy)).thenReturn(stations);

        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/fabrick/v1.0/airports/{airportId}/stations")
                        .queryParam("closestBy", closestBy)
                        .build(airportId))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(Station.class)
                .hasSize(1)
                .contains(stations.get(0));
    }

    @Test
    void testGetAirports() throws UnsupportedEncodingException {
        String stationId = "KDEN";
        double closestBy = 0.0;
        List<Airport> airports = Arrays.asList(
                new Airport("KDEN", "DENVER/DENVER_INTL", "CO", "US", 39.8617, -104.6732, 1656.6));

        when(airportsAndStationsService.GetClosestByAirports(stationId, closestBy)).thenReturn(airports);

        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/fabrick/v1.0/stations/{stationId}/airports")
                        .queryParam("closestBy", closestBy)
                        .build(stationId))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(Airport.class)
                .hasSize(1)
                .contains(airports.get(0));
    }
}
