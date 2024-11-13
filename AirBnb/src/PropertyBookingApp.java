import Controller.PropertyBookingController;
import Entities.*;
import Services.PropertyBookingService;
import Repository.InMemoryRepo;
import Views.AdminView;
import Views.GuestView;
import Views.HostView;

import java.util.Scanner;

public class PropertyBookingApp {
    private final PropertyBookingController controller;
    private final Scanner scanner;

    public PropertyBookingApp(PropertyBookingController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        // Initialize Repositories
        InMemoryRepo<Host> hostRepo = new InMemoryRepo<>();
        InMemoryRepo<Guest> guestRepo = new InMemoryRepo<>();
        InMemoryRepo<Property> propertyRepo = new InMemoryRepo<>();
        InMemoryRepo<Booking> bookingRepo = new InMemoryRepo<>();
        InMemoryRepo<Review> reviewRepo = new InMemoryRepo<>();
        InMemoryRepo<Amenity> amenityRepo = new InMemoryRepo<>();

        // Initialize Service
        PropertyBookingService bookingService = new PropertyBookingService(
                hostRepo, guestRepo, propertyRepo, bookingRepo, reviewRepo, amenityRepo);

        // Initialize Controller
        PropertyBookingController controller = new PropertyBookingController(bookingService);

        bookingService.initializeRepositories();

        // Initialize Application
        PropertyBookingApp app = new PropertyBookingApp(controller);
        app.run();
    }

    private void run() {
        boolean running = true;
        while (running) {
            showMainMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> new AdminView(controller, scanner).run();
                case 2 -> new GuestView(controller, scanner).run();
                case 3 -> new HostView(controller, scanner).run();
                case 0 -> running = false;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
        System.out.println("Exiting the Property Booking System. Goodbye!");
    }

    private void showMainMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. Admin View");
        System.out.println("2. Guest View");
        System.out.println("3. Host View");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }
}