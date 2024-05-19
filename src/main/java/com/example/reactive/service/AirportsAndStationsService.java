package com.example.reactive.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.reactive.model.Airport;
import com.example.reactive.model.GeographicBoundingBox;
import com.example.reactive.model.Station;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AirportsAndStationsService {

    private RestTemplate restTemplate;
    private final String stationsUrl = "https://aviationweather.gov/api/data/stationinfo";
    private final String airportUrl = "https://aviationweather.gov/api/data/airport";
    private ObjectMapper objectMapper;

    Logger logger = LoggerFactory.getLogger(AirportsAndStationsService.class);

    public AirportsAndStationsService() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    public List<Airport> GetClosestByAirports(String airportId, double closestBy) {

        logger.debug("Airport ID : " + airportId);

        Airport airportReference = GetAirportById(airportId);

        if (airportReference == null) {
            throw new IllegalArgumentException("Airport not found");
        }

        logger.debug("calling GetBoundingBox...");

        GeographicBoundingBox geographicBoundingBox = GetBoundingBox(airportReference.getLatitude(),
                airportReference.getLongitude(), closestBy);

        logger.debug(GeographicBoundingBox.class.getName() + " : " + geographicBoundingBox.toString());

        logger.debug("calling GetAirportsByGeographicBoundingBox...");

        List<Airport> result = GetAirportsByGeographicBoundingBox(geographicBoundingBox);

        if (result == null) {
            logger.debug("result is null");
            return List.of();
        }

        logger.debug("result count: " + result.size());

        return result;
    }

    public List<Station> GetClosestByStations(String stationId, double closestBy) {

        if (stationId == null) {
            throw new IllegalArgumentException("Station ID is required");
        }

        logger.debug("Station ID : " + stationId);

        Station stationReference = GetStationById(stationId);

        if (stationReference == null) {
            logger.error("Station not found");
            throw new IllegalArgumentException("Station not found");
        }

        logger.debug("calling GetBoundingBox...");
        GeographicBoundingBox geographicBoundingBox = GetBoundingBox(stationReference.getLatitude(),
                stationReference.getLongitude(), closestBy);

        logger.debug(GeographicBoundingBox.class.getName() + " : " + geographicBoundingBox.toString());

        logger.debug("calling GetStationsByGeographicBoundingBox...");

        List<Station> result = GetStationsByGeographicBoundingBox(geographicBoundingBox);

        if (result == null) {
            logger.debug("result is null");
            return List.of();
        }

        logger.debug("result count: " + result.size());

        return result;
    }

    private Airport GetAirportById(String airportId) {

        // example of a call to the aviationweather API
        // curl -X 'GET' \
        // 'https://aviationweather.gov/api/data/airport?ids=KMCI%2CKORD%2CKBOS&bbox=100%2C-100%2C100%2C-100&format=json'
        // \
        // -H 'accept: */*'

        String url = UriComponentsBuilder.fromHttpUrl(airportUrl)
                .queryParam("ids", airportId)
                .queryParam("format", "json")
                .toUriString();

        String response = restTemplate.getForObject(url, String.class);

        JsonNode responseJson = null;

        try {
            responseJson = objectMapper.readTree(response);
        } catch (Exception e) {
            logger.error("url: " + url);
            throw new IllegalArgumentException("Error parsing response: " + e.getMessage() + " : " + response);
        }

        if (responseJson.isArray()) {

            // check that the response is not empty

            if (responseJson.size() == 0) {
                return null;
            }

            for (JsonNode node : responseJson) {
                Airport airport = Airport.builder()
                        .id(node.get("id").asText())
                        .name(node.get("name").asText())
                        .state(node.get("state").asText())
                        .country(node.get("country").asText())
                        .latitude(node.get("lat").asDouble())
                        .longitude(node.get("lon").asDouble())
                        .elevation(node.get("elev").asDouble())
                        .build();
                return airport;
            }
        }

        return null;

    }

    private Station GetStationById(String stationId) {

        if (stationId == null) {
            throw new IllegalArgumentException("Station ID is required");
        }

        // example of a call to the aviationweather API
        // curl -X 'GET' \
        // 'https://aviationweather.gov/api/data/stationinfo?ids=KORD&format=json' \
        // -H 'accept: */*'

        String url = UriComponentsBuilder.fromHttpUrl(stationsUrl)
                .queryParam("ids", stationId)
                .queryParam("format", "json")
                .toUriString();

        String response = restTemplate.getForObject(url, String.class);

        JsonNode responseJson = null;

        try {
            responseJson = objectMapper.readTree(response);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error parsing response: " + e.getMessage() + " : " + response);
        }

        if (responseJson.isArray()) {

            // check that the response is not empty

            if (responseJson.size() == 0) {
                return null;
            }

            logger.debug("responseJson size: " + responseJson);

            for (JsonNode node : responseJson) {
                Station station = Station.builder()
                        .id(node.get("icaoId").asText())
                        .site(node.get("site").asText())
                        .state(node.get("state").asText())
                        .country(node.get("country").asText())
                        .latitude(node.get("lat").asDouble())
                        .longitude(node.get("lon").asDouble())
                        .elevation(node.get("elev").asDouble())
                        .build();
                return station;
            }

        }

        logger.debug("responseJson is not an array");

        return null;
    }

    private List<Airport> GetAirportsByGeographicBoundingBox(GeographicBoundingBox geographicBoundingBox) {

        // example of a call to the aviationweather API
        // curl -X 'GET' \
        // 'https://aviationweather.gov/api/data/airport?bbox=40%2C-90%2C45%2C-85&format=json'
        // \
        // -H 'accept: */*'

        String url = UriComponentsBuilder.fromHttpUrl(stationsUrl)
                .queryParam("bbox", geographicBoundingBox.getLat0() + ","
                        + geographicBoundingBox.getLon0() + "," + geographicBoundingBox.getLat1() + ","
                        + geographicBoundingBox.getLon1())
                .queryParam("format", "json").toUriString();

        logger.debug("url: " + url);

        String response = restTemplate.getForObject(url, String.class);

        JsonNode responseJson = null;

        try {
            responseJson = objectMapper.readTree(response);
        } catch (Exception e) {

            logger.error("response: " + response);
            throw new IllegalArgumentException("Error parsing response: " + e.getMessage() + " : " + response);
        }

        if (responseJson.isArray()) {

            // check that the response is not empty

            if (responseJson.size() == 0) {
                return null;
            }

            logger.debug("responseJson size: " + responseJson);

            // {
            // "icaoId": "41029",
            // "iataId": "-",
            // "faaId": "-",
            // "wmoId": "-",
            // "lat": 32.81,
            // "lon": -79.63,
            // "elev": 0,
            // "site": "Capers Nearshore",
            // "state": "--",
            // "country": "US",
            // "priority": 8
            // },

            List<Airport> airports = new ArrayList<>();

            for (JsonNode node : responseJson) {
                String id = node.has("id") ? node.get("id").asText() : null;
                String name = node.has("name") ? node.get("name").asText() : null;
                String state = node.has("state") ? node.get("state").asText() : null;
                String country = node.has("country") ? node.get("country").asText() : null;
                double latitude = node.has("lat") ? node.get("lat").asDouble() : null;
                double longitude = node.has("lon") ? node.get("lon").asDouble() : null;
                double elevation = node.has("elev") ? node.get("elev").asDouble() : null;

                Airport airport = Airport.builder()
                        .id(id)
                        .name(name)
                        .state(state)
                        .country(country)
                        .latitude(latitude)
                        .longitude(longitude)
                        .elevation(elevation)
                        .build();
                airports.add(airport);
            }

            return airports;

        }

        logger.debug("responseJson is not an array");

        return null;

    }

    private List<Station> GetStationsByGeographicBoundingBox(GeographicBoundingBox geographicBoundingBox) {

        // curl -X 'GET' \
        // 'https://aviationweather.gov/api/data/stationinfo?bbox=40%2C-90%2C45%2C-85&format=json'
        // \
        // -H 'accept: */*'

        String url = UriComponentsBuilder.fromHttpUrl(stationsUrl)
                .queryParam("bbox", geographicBoundingBox.getLat0() + ","
                        + geographicBoundingBox.getLon0() + "," + geographicBoundingBox.getLat1() + ","
                        + geographicBoundingBox.getLon1())
                .queryParam("format", "json").toUriString();

        String response = restTemplate.getForObject(url, String.class);

        JsonNode responseJson = null;

        try {
            responseJson = objectMapper.readTree(response);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error parsing response: " + e.getMessage() + " : " + response);
        }

        if (responseJson.isArray()) {

            // check that the response is not empty

            if (responseJson.size() == 0) {
                return null;
            }

            List<Station> stations = new ArrayList<>();

            for (JsonNode node : responseJson) {
                Station station = Station.builder()
                        .id(node.has("icaoId") ? node.get("icaoId").asText() : null)
                        .site(node.has("site") ? node.get("site").asText() : null)
                        .state(node.has("state") ? node.get("state").asText() : null)
                        .country(node.has("country") ? node.get("country").asText() : null)
                        .latitude(node.has("lat") ? node.get("lat").asDouble() : null)
                        .longitude(node.has("lon") ? node.get("lon").asDouble() : null)
                        .elevation(node.has("elev") ? node.get("elev").asDouble() : null)
                        .build();
                stations.add(station);
            }

            return stations;

        }

        return null;
    }

    // ```closestBy``` should be used as the bounding box modifier, so for example
    // if I send ```closestBy=1.0``` and I have ```latitude=2.0``` and
    // ```longitude=2.0```, the bounding box should be ```(1.0, 1.0, 3.0, 3.0)```
    private GeographicBoundingBox GetBoundingBox(double latitude, double longitude, double closestBy) {

        GeographicBoundingBox geographicBoundingBox = GeographicBoundingBox.builder()
                .lat0(latitude - closestBy)
                .lon0(longitude - closestBy)
                .lat1(latitude + closestBy)
                .lon1(longitude + closestBy)
                .build();

        return geographicBoundingBox;

    }

    @SuppressWarnings("unused")
    private boolean IsWithinBoundingBox(double latitude, double longitude,
            GeographicBoundingBox geographicBoundingBox) {

        if (latitude >= geographicBoundingBox.getLat0() && latitude <= geographicBoundingBox.getLat1()
                && longitude >= geographicBoundingBox.getLon0() && longitude <= geographicBoundingBox.getLon1()) {
            return true;
        }

        return false;
    }

}
