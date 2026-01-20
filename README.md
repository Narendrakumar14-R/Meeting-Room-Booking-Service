****Meeting Room Booking Service****

This is a Spring Boot REST application that manages meeting room creation and time-based bookings.
It enforces non-overlapping bookings per room, allows adjacent time slots, and guarantees correctness under concurrent requests.

**Features**

Create meeting rooms

Book time slots for a room

Prevent overlapping bookings (adjacent slots allowed)

Thread-safe booking under concurrent requests

In-memory data storage

Proper HTTP status codes (201, 400, 404, 409)

**API Endpoints (Example)**

POST /rooms – Create a room

POST /rooms/{roomId}/bookings – Book a time slot

GET /rooms/{roomId}/bookings – View bookings for a room

**Concurrency Handling**

The service uses synchronized logic at the service layer to ensure that when multiple booking requests target the same room and time range, only one booking succeeds and others receive a conflict response.

**Tech Stack**

Java

Spring Boot

REST APIs

In-memory data structures

Thread-safe service design

**How to Run**
mvn spring-boot:run


This project was implemented as a coding challenge with a focus on clean architecture, correctness, and concurrency safety.
