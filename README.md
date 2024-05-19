# Fabrick Coding Challenge Task 2 - Airports and stations

<div align="center">
  <img src="https://aviationweather.gov/img/NWS_logo.svg" alt="aviation weather logo" style="max-height: 300px;">
</div>

This project is a REST API providing an endpoint to retrieve closest observation stations given an airport and closest airports given an observation station. The project uses the [Aviation Weather Center Open API](https://aviationweather.gov/data/api/) to retrieve the data. The API is a Spring Boot and Java 22 application development project. The project also includes unit testing of the service layer using JUnit and Mockito. The project uses Redis as a cache to reduce the number of requests to the [Aviation Weather Center Open API](https://aviationweather.gov/data/api/#/Data/dataStationInfo).

## Configuration

- **Java 22**
- **Apache Maven 3.9.6**
- **Redis Stack** running on localhost:6379 (default configuration) (Docker is recommended)
- 
## Test

- In order to test remember to start the Redis Stack server
- a Postman collection is provided at the following path:
  
  ```http\Task 2.postman_collection.json```

### Redis docker image configuration

[Redis Docker documentation](https://redis.io/learn/operate/orchestration/docker)

```shell
$ docker run -d --name my-redis-stack -p 6379:6379  redis/redis-stack-server:latest
```

---

## Task 2 - Airports and stations

The goal of this task is to code a **REST** API project that expose the following APIs:
* Given an airport, it will find all the closest observation stations
* Given an observation station, it will find all the closest airports

Here is an example of the APIs we want to expose:

```
GET /api/fabrick/v1.0/airports/{airportId}/stations?closestBy={value?0.0}
[
   {
    "id": "KAFF",
    "site": "Air Force Academy Arfld",
    "state": "CO",
    "country": "US",
    "latitude": 38.971,
    "longitude": -104.816,
    "elevation:: 2003
  },
  ...
]


GET /api/fabrick/v1.0/stations/{stationId}/airports?closestBy={value?0.0}
[
  {
    "id": "KDEN",
    "name: "DENVER/DENVER_INTL",
    "state": "CO",
    "country": "US",
    "latitude": 39.8617,
    "longitude": -104.6732,
    "elevation": 1656.6
  },
  ...
]
```

You can rely on the official [Aviation  Weather Center Open API](https://aviationweather.gov/data/api/); have a look at the APIs **Airport Info** and **Station Info**

### Constraints

* The query parameter ```closestBy``` is optional, by default ```0.0```
* ```airportId``` and ```stationId``` are the **ICAO Airport Codes**
* Please note that ```closestBy``` should be used as the bounding box modifier, so for example if I send ```closestBy=1.0``` and I have ```latitude=2.0``` and ```longitude=2.0```, the bounding box should be ```(1.0, 1.0, 3.0, 3.0)```

### Bonus points

* Implement unit testing using some mocking stub framework
* Implement a local cache or database layer; please note that the database layer should be bootstrapped by the application (we don't want to deal with external systems)

