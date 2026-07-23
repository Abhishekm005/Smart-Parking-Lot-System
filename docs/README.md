# 🚗 Smart Parking Lot System

## Overview

The Smart Parking Lot System is a console-based backend application developed in Java 17.
It simulates the core functionalities of a smart parking lot, including vehicle check-in,
parking spot allocation, check-out, parking fee calculation, and real-time parking spot availability.

This project demonstrates Object-Oriented Programming (OOP), SOLID principles,
Layered Architecture**,
and commonly used Design Patterns.

## Features

* Vehicle Check-In
* Vehicle Check-Out
* Automatic Parking Spot Allocation
* Parking Fee Calculation
* Real-Time Parking Spot Availability
* Multi-Vehicle Type Support (Car, Motorcycle, Bus)
* Payment Processing
* Thread-Safe Spot Allocation using `synchronized`

## Technologies Used

* Java 17
* IntelliJ IDEA
* Java Collections Framework
* OOP
* SOLID Principles

## Design Patterns

* Factory Pattern
* Strategy Pattern
* Repository Pattern
* Service Layer Pattern

## Project Structure

* text
smart-parking-lot/
│
├── src/
│   ├── model/
│   ├── repository/
│   ├── service/
│   ├── strategy/
│   ├── factory/
│   ├── util/
│   ├── exception/
│   └── Main.java
│
├── docs/
│   └── Database_Schema.md
│
├── sql/
│   └── schema.sql
│
└── README.md

## Database Design

The project includes a relational database schema with the following entities:

* Vehicle
* ParkingFloor
* ParkingSpot
* ParkingTicket
* Payment

The SQL schema is available in **`sql/schema.sql`**.

## Functional Requirements

* Automatic parking spot allocation based on vehicle type.
* Vehicle entry and exit management.
* Parking fee calculation based on parking duration and vehicle type.
* Real-time parking spot availability updates.
* Thread-safe handling of simultaneous vehicle entry and exit.

## How to Run

1. Clone the repository.
2. Open the project in IntelliJ IDEA.
3. Configure **JDK 17**.
4. Run `Main.java`.

## Sample Menu

* text
===== SMART PARKING LOT =====

1. Check In Vehicle
2. Check Out Vehicle
3. Show Available Spots
4. Show All Vehicles
5. Exit

## Learning Outcomes

* Applied Object-Oriented Programming concepts.
* Followed SOLID principles for clean code.
* Implemented layered architecture.
* Used Factory, Strategy, and Repository design patterns.
* Designed a relational database schema.
* Implemented basic concurrency using `synchronized`.

