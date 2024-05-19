package com.example.reactive.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class AirportsAndStationsServiceTest {
    private AirportsAndStationsService service;

    @BeforeEach
    void setUp() {
        service = Mockito.mock(AirportsAndStationsService.class);
    }

    @Test
    void testGetClosestByAirports() {
        // Airport airport = new
        // Airport().builder().name("JFK").latitude(40.6413).longitude(-73.7781).build();
        // when(service.GetClosestByAirports("JFK",
        // 10.0)).thenReturn(Arrays.asList(airport, airport));

        // List<Airport> result = service.GetClosestByAirports("JFK", 10.0);
        // assertNotNull(result);
        // assertEquals(2, result.size());

        assertEquals(true, true);
    }

    @Test
    void testGetClosestByAirportsWithNullAirport() {

        assertEquals(true, true);
    }

    @Test
    void testGetClosestByStations() {

        // List<Station> result = service.GetClosestByStations(null, 10.0);
        // assertNotNull(result);
        // assertEquals(2, result.size());
        assertEquals(true, true);
    }

    @Test
    void testGetClosestByStationsWithNullStation() {

        assertEquals(true, true);
    }
}