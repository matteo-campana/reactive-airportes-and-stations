package com.example.reactive.controller;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.example.reactive.model.Airport;
import com.example.reactive.model.Station;
import com.example.reactive.service.AirportsAndStationsService;

import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class AirportsAndStationsControllerTest {

        // @Mock
        private AirportsAndStationsService airportsAndStationsService;

        // @InjectMocks
        private AirportsAndStationsController airportsAndStationsController;

        WebTestClient webTestClient;

        @BeforeEach
        public void setUp() {
                airportsAndStationsService = Mockito.mock(AirportsAndStationsService.class);
                airportsAndStationsController = new AirportsAndStationsController(airportsAndStationsService);
                // webTestClient =
                // WebTestClient.bindToController(airportsAndStationsController).build();
        }

        @Test
        public void testGetStations() throws UnsupportedEncodingException {

                Mockito.when(airportsAndStationsService.GetClosestByStations(Mockito.anyString(), Mockito.anyDouble()))
                                .thenReturn(Arrays.asList(new Station(
                                                "KAFF", "Air Force Academy Arfld", "CO", "US", 38.971, -104.816,
                                                2003)));

                StepVerifier.create(airportsAndStationsController.GetStations("KAFF", 0.0))
                                .expectNextMatches(st -> st.getId().equals("KAFF"))
                                .verifyComplete();

        }

        @Test
        public void testGetAirports() throws UnsupportedEncodingException {

                Mockito.when(airportsAndStationsService.GetClosestByAirports(Mockito.anyString(), Mockito.anyDouble()))
                                .thenReturn(Arrays.asList(new Airport(

                                                "KAFF", "Air Force Academy Arfld", "CO", "US", 38.971, -104.816,
                                                2003)));

                StepVerifier.create(airportsAndStationsController.GetAirports("KAFF", 0.0))
                                .expectNextMatches(st -> st.getId().equals("KAFF"))
                                .verifyComplete();
        }
}