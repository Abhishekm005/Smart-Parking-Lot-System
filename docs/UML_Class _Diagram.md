'========================
' Entities
'========================

abstract class Vehicle {
- vehicleId : long
- vehicleType : VehicleType
- vehicleNumber : String
- ownerName : String
+ toString() : String  
  }

class Car
class Motorcycle
class Bus

Vehicle <|-- Car
Vehicle <|-- Motorcycle
Vehicle <|-- Bus

class ParkingFloor {
- floorId : int
- floorName : String
- parkingLots : List<ParkingLot>
+ addParkingLot(p : ParkingLot)
  }

class ParkingLot {
- parkingLotId : long
- floorName : String
- parkingLotType : ParkingLotType
- available : boolean
+ occupyIfAvailable() : boolean
+ releaseParkingLot()
  }

ParkingFloor "1" o-- "*" ParkingLot

class ParkingTicket {
- parkingTicketId : long
- entryTime : LocalDateTime
- exitTime : LocalDateTime
- parkingFee : double
- status : TicketStatus
+ setExitTime(t : LocalDateTime)
  }

ParkingTicket --> Vehicle
ParkingTicket --> ParkingLot

class Payment {
- paymentId : long
- amount : double
- paymentTime : LocalDateTime
  }

Payment --> ParkingTicket

'========================
' Repositories
'========================

class VehicleRepository {
- vehicles : Map<String, Vehicle>
+ save(v : Vehicle)
+ findAll() : Collection<Vehicle>
  }

class ParkingFloorRepository {
- floors : List<ParkingFloor>
+ save(f : ParkingFloor)
+ addParkingLotToFloor(l : ParkingLot, floorId : int)
+ findAvailableLotByType(type : ParkingLotType) : ParkingLot
  }

class ParkingTicketRepository {
- tickets : Map<Long, ParkingTicket>
+ save(t : ParkingTicket)
+ findActiveByVehicleNumber(no : String) : ParkingTicket
  }

class PaymentRepository {
+ save(p : Payment)
  }

'========================
' Services
'========================

class ParkingLotAllocationService {
- parkingFloorRepo : ParkingFloorRepository
+ allocateParkingLot(vt : VehicleType) : ParkingLot
+ allocateParkingLotOnFloor(vt : VehicleType, floorId : int) : ParkingLot
  }

class CheckInService {
- lotAllocationService : ParkingLotAllocationService
- parkingTicketRepo : ParkingTicketRepository
+ checkIn(v : Vehicle) : ParkingTicket
  }

class CheckOutService {
- lotAllocationService : ParkingLotAllocationService
- parkingTicketRepo : ParkingTicketRepository
- feeCalculationService : FeeCalculationService
+ checkOut(vehicleNumber : String) : ParkingTicket
  }

class PaymentService {
- paymentRepo : PaymentRepository
+ pay(ticketId : long, amount : double)
  }

class ParkingService {
- checkInService : CheckInService
- checkOutService : CheckOutService
- paymentService : PaymentService
+ parkVehicle(v : Vehicle) : ParkingTicket
+ checkOut(vehicleNumber : String) : ParkingTicket
  }

class FeeCalculationService

'========================
' Factory & Utility
'========================

class VehicleFactory {
+ createVehicle(no : String, owner : String, type : VehicleType) : Vehicle
  }

class IdGenerator {
+ nextId() : long
  }

'========================
' Dependencies
'========================

ParkingService --> CheckInService
ParkingService --> CheckOutService
ParkingService --> PaymentService

CheckInService --> ParkingLotAllocationService
CheckInService --> ParkingTicketRepository

CheckOutService --> ParkingLotAllocationService
CheckOutService --> ParkingTicketRepository
CheckOutService --> FeeCalculationService

PaymentService --> PaymentRepository

ParkingLotAllocationService --> ParkingFloorRepository

VehicleFactory --> Vehicle
VehicleFactory --> IdGenerator


    
                                    +----------------------+
                                    |      Vehicle         |
                                    +----------------------+
                                    | - vehicleId:int      |
                                    | - vehicleNumber:String|
                                    | - ownerName:String   |
                                    | - vehicleType        |
                                    +----------------------+
                                                ^
                         ------------------------|-----------------------
                        |                        |                      |
                  +-----------+           +-------------+        +-----------+
                  |    Car    |           | Motorcycle  |        |    Bus    |
                  +-----------+           +-------------+        +-----------+

```text
+-----------------------+              +------------------------+
|    ParkingFloor       |1            *|     ParkingLot        |
+-----------------------+--------------+------------------------+
| floorId               |              | parkingLotId          |
| floorName             |              | parkingLotType        |
+-----------------------+              | available             |
| available              |
+------------------------+

+------------------------+
|    ParkingTicket       |
+------------------------+
| ticketId               |
| entryTime              |
| exitTime               |
| parkingFee             |
| status                 |
+------------------------+
|1
|
|*
+---------------------+
|      Vehicle        |
+---------------------+
      |
      |
      |1
      |
      |*
+----------------------+
|    ParkingLot        |
+----------------------+

+-----------------------+
|      Payment          |
+-----------------------+
| paymentId             |
| amount                |
| paymentTime           |
+-----------------------+
|
|1
|
|1
+------------------------+
|   ParkingTicket        |
+------------------------+
```