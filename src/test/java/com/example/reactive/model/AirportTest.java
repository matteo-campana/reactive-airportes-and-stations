package com.example.reactive.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AirportTest {
    @Test
    void testBuilder() {
        Airport airport = Airport.builder()
                .id("KDEN")
                .name("DENVER/DENVER_INTL")
                .state("CO")
                .country("US")
                .latitude(39.8617)
                .longitude(-104.6732)
                .elevation(1656.6)
                .build();

        assertEquals("KDEN", airport.getId());
        assertEquals("DENVER/DENVER_INTL", airport.getName());
        assertEquals("CO", airport.getState());
        assertEquals("US", airport.getCountry());
        assertEquals(39.8617, airport.getLatitude());
        assertEquals(-104.6732, airport.getLongitude());
        assertEquals(1656.6, airport.getElevation());
    }

    @Test
    void testCanEqual() {
        Airport airport1 = new Airport();
        Airport airport2 = new Airport();

        assertTrue(airport1.canEqual(airport2));
    }

    @Test
    void testEquals() {
        Airport airport1 = new Airport();
        Airport airport2 = new Airport();

        assertTrue(airport1.equals(airport2));
    }

    @Test
    void testGetCountry() {
        Airport airport = new Airport();
        airport.setCountry("US");

        assertEquals("US", airport.getCountry());
    }

    @Test
    void testGetElevation() {
        Airport airport = new Airport();
        airport.setElevation(1656.6);

        assertEquals(1656.6, airport.getElevation());
    }

    @Test
    void testGetId() {
        Airport airport = new Airport();
        airport.setId("KDEN");

        assertEquals("KDEN", airport.getId());
    }

    @Test
    void testGetLatitude() {
        Airport airport = new Airport();
        airport.setLatitude(39.8617);

        assertEquals(39.8617, airport.getLatitude());
    }

    @Test
    void testGetLongitude() {
        Airport airport = new Airport();
        airport.setLongitude(-104.6732);

        assertEquals(-104.6732, airport.getLongitude());
    }

    @Test
    void testGetName() {
        Airport airport = new Airport();
        airport.setName("DENVER/DENVER_INTL");

        assertEquals("DENVER/DENVER_INTL", airport.getName());
    }

    @Test
    void testGetState() {
        Airport airport = new Airport();
        airport.setState("CO");

        assertEquals("CO", airport.getState());
    }

    @Test
    void testHashCode() {
        Airport airport = new Airport();
        airport.setId("KDEN");

        assertEquals(airport.hashCode(), airport.hashCode());
    }

    @Test
    void testSetCountry() {
        Airport airport = new Airport();
        airport.setCountry("US");

        assertEquals("US", airport.getCountry());
    }

    @Test
    void testSetElevation() {
        Airport airport = new Airport();
        airport.setElevation(1656.6);

        assertEquals(1656.6, airport.getElevation());
    }

    @Test
    void testSetId() {
        Airport airport = new Airport();
        airport.setId("KDEN");

        assertEquals("KDEN", airport.getId());
    }

    @Test
    void testSetLatitude() {
        Airport airport = new Airport();
        airport.setLatitude(39.8617);

        assertEquals(39.8617, airport.getLatitude());
    }

    @Test
    void testSetLongitude() {
        Airport airport = new Airport();
        airport.setLongitude(-104.6732);

        assertEquals(-104.6732, airport.getLongitude());
    }

    @Test
    void testSetName() {
        Airport airport = new Airport();
        airport.setName("DENVER/DENVER_INTL");

        assertEquals("DENVER/DENVER_INTL", airport.getName());
    }

    @Test
    void testSetState() {
        Airport airport = new Airport();
        airport.setState("CO");

        assertEquals("CO", airport.getState());
    }

}