package com.airtribe.smartparkinglotsystem;

import com.airtribe.smartparkinglotsystem.entities.*;
import com.airtribe.smartparkinglotsystem.exception.*;
import com.airtribe.smartparkinglotsystem.factory.VehicleFactory;
import com.airtribe.smartparkinglotsystem.repository.*;
import com.airtribe.smartparkinglotsystem.service.*;

import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        VehicleRepository vehicleRepo = new VehicleRepository();
        ParkingTicketRepository parkingTicketRepo = new ParkingTicketRepository();
        ParkingFloorRepository parkingFloorRepo = new ParkingFloorRepository();
        PaymentRepository paymentRepo = new PaymentRepository();

        parkingFloorRepo.save(new ParkingFloor(1,"1st"));
        parkingFloorRepo.save(new ParkingFloor(2,"2nd"));

        parkingFloorRepo.addParkingLotToFloor(new ParkingLot(1,
                "1st", ParkingLotType.MOTORCYCLE),1);

        parkingFloorRepo.addParkingLotToFloor(new ParkingLot(2,
                "1st", ParkingLotType.MOTORCYCLE),1);

        parkingFloorRepo.addParkingLotToFloor(new ParkingLot(3,
                "1st", ParkingLotType.CAR),1);

        parkingFloorRepo.addParkingLotToFloor(new ParkingLot(4,
                "1st", ParkingLotType.BUS),1);

        parkingFloorRepo.addParkingLotToFloor(new ParkingLot(10,
                "1st", ParkingLotType.BUS),1);

        parkingFloorRepo.addParkingLotToFloor(new ParkingLot(7,
                "2nd", ParkingLotType.BUS),2);

        parkingFloorRepo.addParkingLotToFloor(new ParkingLot(8,
                "2nd", ParkingLotType.BUS),2);


        parkingFloorRepo.addParkingLotToFloor(new ParkingLot(5,
                "2nd", ParkingLotType.CAR),2);

        parkingFloorRepo.addParkingLotToFloor(new ParkingLot(6,
                "2nd", ParkingLotType.MOTORCYCLE),2);

        parkingFloorRepo.addParkingLotToFloor(new ParkingLot(9,
                "2nd", ParkingLotType.CAR),2);



        ParkingLotAllocationService lotAllocationService = new ParkingLotAllocationService(parkingFloorRepo);

        FeeCalculationService feeCalculationService = new FeeCalculationService();

        CheckInService checkInService = new CheckInService(lotAllocationService, parkingTicketRepo);

        CheckOutService checkOutService = new CheckOutService(lotAllocationService,
                parkingTicketRepo, feeCalculationService);

        PaymentService paymentService = new PaymentService(paymentRepo);

        ParkingService parkingService = new ParkingService(checkInService, checkOutService, paymentService);

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("\n=========Smart Parking Lots System==========");

                System.out.println("1. Check In Vehicle");
                System.out.println("2. Check Out Vehicle");
                System.out.println("3. Show All Available Parking Lots");
                System.out.println("4. Show All Registered Vehicles");
                System.out.println("5. Exit");

                int choice;

                try {
                    System.out.print("Please enter your choice: ");
                    choice = scanner.nextInt();
                    scanner.nextLine();
                }catch (InputMismatchException e){
                    System.out.println("Invalid Input");
                    scanner.nextLine();
                    continue;
                }

            switch (choice) {
                case 1 :
                    System.out.print("Vehicle Number:");
                    String vehicleNumber = scanner.nextLine();

                    if(vehicleRepo.exists(vehicleNumber)) {
                        System.out.println("Vehicle Already Exists");
                        try{
                            System.out.println("1. Park Registered Vehicle");
                            System.out.println("2. Exit");
                            System.out.print("Enter your Choice: ");
                            int choice2 =  scanner.nextInt();
                            scanner.nextLine();
                            if(choice2 == 1){
                                System.out.print("Enter Vehicle Number: ");
                                String vehicleNumber2 = scanner.nextLine();
                              ParkingTicket ticker2 = parkingTicketRepo.getActiveTicket(vehicleNumber2);
                              if(ticker2 == null) {
                                  Vehicle vehicle1 = vehicleRepo.findByVehicleNumber(vehicleNumber2);
                                  ParkingTicket ticket = parkingService.parkVehicle(vehicle1);
                                  System.out.println("Vehicle Parked Successfully");
                                  System.out.println("Parking Ticket ID: " + ticket.getParkingTicketID());
                                  System.out.println("Parking Lot: " + ticket.getParkingLot());
                                  break;
                              }else{
                                  System.out.println("Vehicle Already Parked");
                                  break;
                              }
                            }else break;
                        }catch (Exception e){
                            System.out.println("Invalid Input");
                        }
                        break;
                    }

                    System.out.println("Owner Name");
                    String ownerName = scanner.nextLine();

                    System.out.println("Vehicle Type");
                    System.out.println("1. Motorcycle");
                    System.out.println("2. Car");
                    System.out.println("3. Bus");

                    int choise1;

                    try {
                        choise1 = scanner.nextInt();
                        scanner.nextLine();
                    }catch (InputMismatchException e){
                        System.out.println("Invalid Input");
                       break;
                    }

                    VehicleType type;

                    switch (choise1) {
                        case 1 :
                            type = VehicleType.MOTORCYCLE;
                            break;

                            case 2 :
                            type = VehicleType.CAR;
                            break;

                            case 3 :
                            type = VehicleType.BUS;
                            break;

                            default:
                              throw new InvalidVehicleException("Invalid Vehicle Type");
                    }
                    Vehicle vehicle = VehicleFactory.createVehicle(vehicleNumber, ownerName, type);

                    vehicleRepo.save(vehicle);
                    try {
                        ParkingTicket ticket = parkingService.parkVehicle(vehicle);

                        System.out.println("Vehicle Parked Successfully");
                        System.out.println("Parking Ticket ID: " + ticket.getParkingTicketID());
                        System.out.println("Parking Lot: " + ticket.getParkingLot());
                        break;
                    }catch (ParkingFullException e){
                        System.out.println("Parking Lot Full");
                        break;
                    }
                case 2 :
                    try {
                        System.out.println("Vehicle Number: ");
                        String vehicleNo = scanner.nextLine();

                        ParkingTicket parkingTicket = parkingService.checkOut(vehicleNo);

                        System.out.println("Checked Out Successfully");
                        System.out.println("Amount : " + parkingTicket.getParkingFee());
                    }catch (VehicleNotFoundException e){
                        System.out.println("Vehicle Not Found");
                        break;
                    }

                case 3 :
                    System.out.println("\nAll Available Parking Lots");
                    for(ParkingLot lots : parkingFloorRepo.getAvailableParkingLots()){
                        System.out.println(lots);
                    }
                   break;

                case 4 :
                    System.out.println("\nAll Registered Vehicles");

                  Collection<Vehicle> all = vehicleRepo.findAll();
                  if(all.isEmpty()){
                      System.out.println("No Vehicles Found");
                  }else {
                      for (Vehicle v : all) {
                          System.out.println(v);
                      }
                  }
                    break;

                case 5 :
                    System.out.println("Thank you for using our SmartParking Lots");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
