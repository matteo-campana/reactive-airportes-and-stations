package com.example.reactive.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StationTest {
    @Test
    void testBuilder() {
        Station station = Station.builder()
                .id("1")
                .country("Country")
                .state("State")
                .elevation(100.0)
                .latitude(10.0)
                .longitude(20.0)
                .site("Site")
                .build();

        assertEquals("1", station.getId());
        assertEquals("Country", station.getCountry());
        assertEquals("State", station.getState());
        assertEquals(100.0, station.getElevation());
        assertEquals(10.0, station.getLatitude());
        assertEquals(20.0, station.getLongitude());
        assertEquals("Site", station.getSite());
    }

    @Test
    void testCanEqual() {
        Station station1 = new Station();
        Station station2 = new Station();

        assertTrue(station1.canEqual(station2));
    }

    @Test
    void testEquals() {
        Station station1 = new Station();
        Station station2 = new Station();

        assertTrue(station1.equals(station2));
    }

    @Test
    void testGetCountry() {
        Station station = new Station();
        station.setCountry("Country");

        assertEquals("Country", station.getCountry());
    }

    @Test
    void testGetElevation() {
        Station station = new Station();
        station.setElevation(100.0);

        assertEquals(100.0, station.getElevation());
    }

    @Test
    void testGetId() {
        Station station = new Station();
        station.setId("1");

        assertEquals("1", station.getId());
    }

    @Test
    void testGetLatitude() {
        Station station = new Station();
        station.setLatitude(10.0);

        assertEquals(10.0, station.getLatitude());
    }

    @Test
    void testGetLongitude() {
        Station station = new Station();
        station.setLongitude(20.0);

        assertEquals(20.0, station.getLongitude());
    }

    @Test
    void testGetSite() {
        Station station = new Station();
        station.setSite("Site");

        assertEquals("Site", station.getSite());
    }

    @Test
    void testGetState() {
        Station station = new Station();
        station.setState("State");

        assertEquals("State", station.getState());
    }

    @Test
    void testHashCode() {
        Station station = new Station();
        station.setId("1");

        assertEquals(station.hashCode(), station.hashCode());
    }

    @Test
    void testSetCountry() {
        Station station = new Station();
        station.setCountry("Country");

        assertEquals("Country", station.getCountry());
    }

    @Test
    void testSetElevation() {
        Station station = new Station();
        station.setElevation(100.0);

        assertEquals(100.0, station.getElevation());
    }

    @Test
    void testSetId() {
        Station station = new Station();
        station.setId("1");

        assertEquals("1", station.getId());
    }

    @Test
    void testSetLatitude() {
        Station station = new Station();
        station.setLatitude(10.0);

        assertEquals(10.0, station.getLatitude());
    }

    @Test
    void testSetLongitude() {
        Station station = new Station();
        station.setLongitude(20.0);

        assertEquals(20.0, station.getLongitude());
    }

    @Test
    void testSetSite() {
        Station station = new Station();
        station.setSite("Site");

        assertEquals("Site", station.getSite());
    }

    @Test
    void testSetState() {
        Station station = new Station();
        station.setState("State");

        assertEquals("State", station.getState());
    }

}