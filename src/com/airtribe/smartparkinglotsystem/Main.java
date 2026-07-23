package com.airtribe.smartparkinglotsystem;

import com.airtribe.smartparkinglotsystem.entities.*;
import com.airtribe.smartparkinglotsystem.exception.InvalidVehicleException;
import com.airtribe.smartparkinglotsystem.exception.VehicleNotFoundException;
import com.airtribe.smartparkinglotsystem.factory.VehicleFactory;
import com.airtribe.smartparkinglotsystem.repository.*;
import com.airtribe.smartparkinglotsystem.service.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        VehicleRepository vehicleRepo = new VehicleRepository();
        ParkingTicketRepository parkingTicketRepo = new ParkingTicketRepository();
        ParkingLotRepository parkingLotRepo = new ParkingLotRepository();
        PaymentRepository paymentRepo = new PaymentRepository();

        parkingLotRepo.add(new ParkingLot(1, "1st", ParkingLotType.MOTORCYCLE));

        parkingLotRepo.add(new ParkingLot(1, "1st", ParkingLotType.MOTORCYCLE));

        parkingLotRepo.add(new ParkingLot(1, "1st", ParkingLotType.CAR));

        parkingLotRepo.add(new ParkingLot(1, "1st", ParkingLotType.CAR));

        parkingLotRepo.add(new ParkingLot(1, "1st", ParkingLotType.BUS));

        parkingLotRepo.add(new ParkingLot(1, "1st", ParkingLotType.BUS));

        ParkingLotAllocationService lotAllocationService = new ParkingLotAllocationService(parkingLotRepo);

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
                System.out.println("4. Show All Vehicles");
                System.out.println("5. Exit");

                int choice;

                try {
                    System.out.print("Please enter your choice: ");
                    choice = scanner.nextInt();
                    scanner.nextLine();
                }catch (InputMismatchException e){
                    System.out.println("Please enter a valid choice");
                    scanner.nextLine();
                    continue;
                }

            switch (choice) {
                case 1 :
                    System.out.println("Owner Name");
                    String ownerName = scanner.nextLine();

                    System.out.println("Vehicle Number");
                    String vehicleNumber = scanner.nextLine();

                    System.out.println("Vehicle Type");
                    System.out.println("1. Motorcycle");
                    System.out.println("2. Car");
                    System.out.println("3. Bus");

                    int choise1;
                    try {
                        choise1 = scanner.nextInt();
                        scanner.nextLine();
                    }catch (InputMismatchException e){
                        System.out.println("Please enter a valid choice");
                        scanner.nextLine();
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
                    ParkingTicket ticket = parkingService.parkVehicle(vehicle);

                    System.out.println("Vehicle Parked Successfully");
                    System.out.println("Parking Ticket ID: " + ticket.getParkingTicketID());
                    System.out.println("Parking Lot: " + ticket.getParkingLot());
                    break;

                case 2 :
                    try {
                        System.out.println("Vehicle Number: ");
                        String vehicleNo = scanner.nextLine();

                        ParkingTicket parkingTicket = parkingService.checkOut(vehicleNo);

                        System.out.println("Checked Out Successfully");
                        System.out.println("Amount : " + parkingTicket.getParkingFee());
                    }catch (VehicleNotFoundException e){
                        System.out.println("Vehicle Not Found");
                    }
                    break;

                case 3 :
                    System.out.println("\nAll Available Parking Lots");
                    for(ParkingLot lots : parkingLotRepo.getAvailableParkingLots()){
                        System.out.println(lots);
                    }
                   break;

                case 4 :
                    System.out.println("\nAll Registered Vehicles");

                    for(Vehicle vehicles : vehicleRepo.findAll()){
                        if(vehicles == null){
                            System.out.println("No Vehicle Found");
                        }
                        System.out.println(vehicles);
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
