# ShopFlow Notification Service

## Service Description

The ShopFlow Notification Service is a background service responsible for sending notifications to users. It is a Kafka consumer that listens for events from other services and triggers notifications accordingly.

## Tech Stack

- **Java 17**
- **Spring Boot 2.7.5**
- **Spring for Apache Kafka** for event-driven communication.
- **Maven** for dependency management.

## How to Run in Dev Mode

To run the service in development mode, you can use the following Maven command:

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

This will start the service on port `8085` and connect to the development Kafka broker as configured in `src/main/resources/application-dev.properties`.

## API Endpoints

This service does not expose any RESTful endpoints. It operates purely as a background worker that consumes Kafka events.

## Communication with Other Services

The Notification Service communicates with other services asynchronously via Apache Kafka.

### Consumed Events

- **`payment_success_events`**

  The service subscribes to the `payment_success_events` Kafka topic. When it receives an event, it simulates sending a notification (e.g., an email or SMS) to the user to confirm their payment.
