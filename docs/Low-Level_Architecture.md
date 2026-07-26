                    SMART PARKING LOT SYSTEM
                  LOW-LEVEL ARCHITECTURE (LLD)
```text
┌──────────────────────────────────────────────────────────┐
│                  PRESENTATION LAYER                      │
│                                                        │
│                      Main.java                         │
│   - Takes user input                                   │
│   - Check-In / Check-Out                               │
│   - Shows available parking spots                      │
└──────────────────────────┬───────────────────────────────┘
│
▼
┌──────────────────────────────────────────────────────────┐
│                    FACADE LAYER                          │
│                                                        │
│                   ParkingService                       │
│   - parkVehicle()                                      │
│   - checkOut()                                         │
└──────────────────────────┬───────────────────────────────┘
│
▼
┌──────────────────────────────────────────────────────────┐
│                    SERVICE LAYER                         │
│                                                        │
│  ┌────────────────┐      ┌─────────────────┐           │
│  │ CheckInService │      │ CheckOutService │           │
│  └───────┬────────┘      └────────┬────────┘           │
│          │                        │                     │
│          ▼                        ▼                     │
│  ┌──────────────────┐    ┌──────────────────┐          │
│  │ ParkingLot       │    │ FeeCalculation   │          │
│  │ AllocationService│    │ Service          │          │
│  └──────────────────┘    └──────────────────┘          │
│                                                        │
│                  ┌────────────────┐                    │
│                  │ PaymentService │                    │
│                  └────────────────┘                    │
└──────────────────────────┬───────────────────────────────┘
│
▼
┌──────────────────────────────────────────────────────────┐
│                  REPOSITORY LAYER                        │
│                                                        │
│  ┌─────────────────────┐  ┌────────────────────────┐   │
│  │ VehicleRepository   │  │ ParkingFloorRepository│   │
│  └─────────────────────┘  └────────────────────────┘   │
│                                                        │
│  ┌─────────────────────┐  ┌────────────────────────┐   │
│  │ TicketRepository    │  │ PaymentRepository      │   │
│  └─────────────────────┘  └────────────────────────┘   │
│                                                        │
│       Storage: HashMap / ArrayList / Collections        │
└──────────────────────────┬───────────────────────────────┘
│
▼
┌──────────────────────────────────────────────────────────┐
│                    MODEL LAYER                           │
│                                                        │
│                    Vehicle                             │
│                       ▲                                │
│              ┌────────┼────────┐                       │
│              │        │        │                       │
│             Car   Motorcycle   Bus                     │
│                                                        │
│  ParkingFloor ────────► ParkingSpot/ParkingLot         │
│                              │                         │
│                              ▼                         │
│ Vehicle ─────────────► ParkingTicket                   │
│                              │                         │
│                              ▼                         │
│                           Payment                      │
└──────────────────────────────────────────────────────────┘
```

                  DESIGN PATTERNS
```text
VehicleFactory
│
├──► Car
├──► Motorcycle
└──► Bus

FeeStrategy
│
├──► CarFeeStrategy
├──► MotorcycleFeeStrategy
└──► BusFeeStrategy
```
Repository Pattern → Data Access
Facade Pattern     → ParkingService
Strategy Pattern   → Fee Calculation
Factory Pattern    → Vehicle Creation


                  CONCURRENCY HANDLING
```text
Vehicle 1 ──┐
│
▼
synchronized block
│
Find Available Spot
│
Occupy Spot
│
Release Lock
│
▼
Vehicle 2 can allocate next available spot
```
This prevents two vehicles from receiving
the same parking spot simultaneously.


                    DATABASE DESIGN
```text
ParkingFloor
│
│ 1 : N
▼
ParkingSpot
│
│
▼
ParkingTicket ◄──────── Vehicle
│
│ 1 : 1
▼
Payment
```

                    SYSTEM FLOW
```text
Vehicle Entry
│
▼
Main.java
│
▼
VehicleFactory
│
▼
ParkingService
│
▼
CheckInService
│
▼
ParkingLotAllocationService
│
▼
Find Available Spot
│
▼
Mark Spot Occupied
│
▼
Create ParkingTicket


Vehicle Exit
│
▼
ParkingService
│
▼
CheckOutService
│
▼
Find Active Ticket
│
▼
Set Exit Time
│
▼
FeeCalculationService
│
▼
Calculate Parking Fee
│
▼
Release Parking Spot
│
▼
PaymentService
│
▼
Create Payment
```