package Views;

import Controller.PropertyBookingController;
import Entities.Guest;
import Entities.Host;
import Helpers.*;
import java.util.Scanner;

public class AdminView {
    private final PropertyBookingController controller;
    private final Scanner scanner;

    public AdminView(PropertyBookingController controller, Scanner scanner) {
        this.controller = controller;
        this.scanner = scanner;
    }

    public void run() {
        boolean running = true;
        while (running) {
            showAdminMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> addHost();
                case 2 -> addGuest();
                case 3 -> controller.listAllHosts();
                case 4 -> controller.listAllGuests();
                case 5 -> filterGuestsByBookingCount();
                case 0 -> running = false;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void showAdminMenu() {
        System.out.println("\nAdmin Menu:");
        System.out.println("1. Add a new Host");
        System.out.println("2. Add a new Guest");
        System.out.println("3. View all Hosts");
        System.out.println("4. View all Guests");
        System.out.println("5. Filter Guests by Booking Count");
        System.out.println("0. Go back");
        System.out.print("Choose an option: ");
    }

    private void addHost() {
        System.out.print("Enter host name: ");
        String name = scanner.nextLine();
        System.out.print("Enter host email: ");
        String email = scanner.nextLine();
        System.out.print("Enter host phone number: ");
        String phone = scanner.nextLine();
        System.out.print("Enter host rating: ");
        double rating = Double.parseDouble(scanner.nextLine());

        int id = HelperFunctions.randomId();
        Host host = new Host(id, name, email, phone, rating);
        controller.addHost(host);
    }

    private void addGuest() {
        System.out.print("Enter guest name: ");
        String name = scanner.nextLine();
        System.out.print("Enter guest email: ");
        String email = scanner.nextLine();
        System.out.print("Enter guest phone number: ");
        String phone = scanner.nextLine();
        System.out.print("Enter guest rating: ");
        double rating = Double.parseDouble(scanner.nextLine());

        int id = HelperFunctions.randomId();
        Guest guest = new Guest(id, name, email, phone, rating);
        controller.addGuest(guest);
    }

    private void filterGuestsByBookingCount() {
        System.out.print("Enter minimum number of bookings: ");
        int minBookings = Integer.parseInt(scanner.nextLine());
        controller.filterGuestsByBookingCount(minBookings);
    }
}
