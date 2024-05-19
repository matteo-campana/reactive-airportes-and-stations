package com.example.reactive.controller;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.reactive.server.WebTestClient.bindToController;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.example.reactive.model.Airport;
import com.example.reactive.model.Station;
import com.example.reactive.service.AirportsAndStationsService;

import reactor.core.publisher.Flux;

public class AirportsAndStationsControllerTest {

        @Mock
        private AirportsAndStationsService airportsAndStationsService;

        @InjectMocks
        private AirportsAndStationsController airportsAndStationsController;

        private WebTestClient webTestClient;

        @BeforeEach
        public void setUp() {
                MockitoAnnotations.openMocks(this);
                webTestClient = bindToController(airportsAndStationsController).build();
        }

        @Test
        public void testGetStations() throws UnsupportedEncodingException {
                String airportId = "KAFF";
                double closestBy = 0.0;
                List<Station> stations = Arrays.asList();

                when(airportsAndStationsService.GetClosestByStations(anyString(), anyDouble())).thenReturn(stations);

                webTestClient.get()
                                .uri("/api/fabrick/v1.0/airports/{airportId}/stations?closestBy={closestBy}", airportId,
                                                closestBy)
                                .exchange()
                                .expectStatus().isOk()
                                .expectBodyList(Station.class).isEqualTo(stations);
        }

        @Test
        public void testGetAirports() throws UnsupportedEncodingException {
                String stationId = "KDEN";
                double closestBy = 0.0;
                List<Airport> airports = Arrays.asList();

                when(airportsAndStationsService.GetClosestByAirports(anyString(), anyDouble())).thenReturn(airports);

                webTestClient.get()
                                .uri("/api/fabrick/v1.0/stations/{stationId}/airports?closestBy={closestBy}", stationId,
                                                closestBy)
                                .exchange()
                                .expectStatus().isOk()
                                .expectBodyList(Airport.class).isEqualTo(airports);
        }
}
