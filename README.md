# OCPP 1.6J Implementation (Central System)

Run ``com.opitzconsulting.hackathon.Application``

Use JVM parameter ``-Dmicronaut.server.port=8180`` in order to change Micronaut HTTP server port so it does not collide with other application running.

##
Example requests

Websocket client https://www.postman.com/

```ws://localhost:8081/ws/CentralSystemService/{chargeBoxId}```

chargeBoxId kann zB CB-4711 sein

### Boot notification
Request
```json

[
    2,
    "1",
    "BootNotification",
    {
        "chargePointVendor": "ACME",
        "chargePointModel": "WALLBOX"
    }
]
```
Response
```json
[
    3,
    "1",
    {
        "status": "Accepted",
        "currentTime": "2021-11-10T14:59:52.029Z",
        "interval": 14400
    }
]
```

### Authorize
Request
```json
[
    2,
    "2",
    "Authorize",
    {
        "idTag": "0fc673"
    }
]
```
Response
```json
[
    3,
    "2",
    {
        "idTagInfo": {
            "status": "Accepted",
            "expiryDate": "2021-11-11T00:00:00.000Z"
        }
    }
]
```

### Start Transaction
Request
```json
[
  2,
  "3",
  "StartTransaction",
  {
    "connectorId": 1,
    "idTag": "0fc673",
    "meterStart": 10000,
    "timestamp": "2021-11-10T14:00:00.000Z"
  }
]
```
Response
```json
[
    3,
    "4",
    {
        "transactionId": 1,
        "idTagInfo": {
            "status": "Accepted",
            "expiryDate": "2021-11-11T00:00:00.000Z"
        }
    }
]
```

### Stop Transaction
Request
```json
[
  2,
  "4",
  "StopTransaction",
  {
    "idTag": "0fc673",
    "meterStop": 15000,
    "timestamp": "2021-11-10T15:15:00.000Z",
    "transactionId": 1,
    "reason": "EVDisconnected",
    "transactionData": [
      {
        "timestamp": "2021-11-10T14:15:00.000Z",
        "sampledValue": [
          {
            "value": "11000"
          }
        ]
      },
      {
        "timestamp": "2021-11-10T14:30:00.000Z",
        "sampledValue": [
          {
            "value": "12000"
          }
        ]
      },
      {
        "timestamp": "2021-11-10T14:45:00.000Z",
        "sampledValue": [
          {
            "value": "13000"
          }
        ]
      },
      {
        "timestamp": "2021-11-10T15:00:00.000Z",
        "sampledValue": [
          {
            "value": "14000"
          }
        ]
      }
    ]
  }
]
```
Response
```json
[
    3,
    "4",
    {
        "idTagInfo": {
            "status": "Accepted",
            "expiryDate": "2021-11-10T15:58:25.965Z"
        }
    }
]
```

## Micronaut 3.1.3 Documentation

- [User Guide](https://docs.micronaut.io/3.1.3/guide/index.html)
- [API Reference](https://docs.micronaut.io/3.1.3/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/3.1.3/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)

---

## Feature lombok documentation

- [Micronaut Project Lombok documentation](https://docs.micronaut.io/latest/guide/index.html#lombok)

- [https://projectlombok.org/features/all](https://projectlombok.org/features/all)

## Feature reactor documentation

- [Micronaut Reactor documentation](https://micronaut-projects.github.io/micronaut-reactor/snapshot/guide/index.html)

## Feature http-client documentation

- [Micronaut HTTP Client documentation](https://docs.micronaut.io/latest/guide/index.html#httpClient)

## Feature jax-rs documentation

- [Micronaut JAX-RS support documentation](https://micronaut-projects.github.io/micronaut-jaxrs/latest/guide/index.html)

## Feature jetty-server documentation

- [Micronaut Jetty Server documentation](https://micronaut-projects.github.io/micronaut-servlet/1.0.x/guide/index.html#jetty)

