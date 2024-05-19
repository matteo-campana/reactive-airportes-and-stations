package com.example.reactive.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GeographicBoundingBoxTest {
    @Test
    void testBuilder() {
        GeographicBoundingBox box = GeographicBoundingBox.builder()
                .lat0(10.0)
                .lat1(20.0)
                .lon0(30.0)
                .lon1(40.0)
                .build();

        assertEquals(10.0, box.getLat0());
        assertEquals(20.0, box.getLat1());
        assertEquals(30.0, box.getLon0());
        assertEquals(40.0, box.getLon1());
    }

    @Test
    void testCanEqual() {
        GeographicBoundingBox box1 = new GeographicBoundingBox();
        GeographicBoundingBox box2 = new GeographicBoundingBox();

        assertTrue(box1.canEqual(box2));
    }

    @Test
    void testEquals() {
        GeographicBoundingBox box1 = new GeographicBoundingBox();
        GeographicBoundingBox box2 = new GeographicBoundingBox();

        assertTrue(box1.equals(box2));
    }

    @Test
    void testGetLat0() {
        GeographicBoundingBox box = new GeographicBoundingBox();
        box.setLat0(10.0);

        assertEquals(10.0, box.getLat0());
    }

    @Test
    void testGetLat1() {
        GeographicBoundingBox box = new GeographicBoundingBox();
        box.setLat1(20.0);

        assertEquals(20.0, box.getLat1());
    }

    @Test
    void testGetLon0() {
        GeographicBoundingBox box = new GeographicBoundingBox();
        box.setLon0(30.0);

        assertEquals(30.0, box.getLon0());
    }

    @Test
    void testGetLon1() {
        GeographicBoundingBox box = new GeographicBoundingBox();
        box.setLon1(40.0);

        assertEquals(40.0, box.getLon1());
    }

    @Test
    void testHashCode() {
        GeographicBoundingBox box = new GeographicBoundingBox();
        box.setLat0(10.0);

        assertEquals(box.hashCode(), box.hashCode());
    }

    @Test
    void testSetLat0() {
        GeographicBoundingBox box = new GeographicBoundingBox();
        box.setLat0(10.0);

        assertEquals(10.0, box.getLat0());
    }

    @Test
    void testSetLat1() {
        GeographicBoundingBox box = new GeographicBoundingBox();
        box.setLat1(20.0);

        assertEquals(20.0, box.getLat1());
    }

    @Test
    void testSetLon0() {
        GeographicBoundingBox box = new GeographicBoundingBox();
        box.setLon0(30.0);

        assertEquals(30.0, box.getLon0());
    }

    @Test
    void testSetLon1() {
        GeographicBoundingBox box = new GeographicBoundingBox();
        box.setLon1(40.0);

        assertEquals(40.0, box.getLon1());
    }

}