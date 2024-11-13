import Controller.PropertyBookingController;
import Entities.*;
import Services.PropertyBookingService;
import Repository.InMemoryRepo;
import Views.AdminView;
import Views.GuestView;
import Views.HostView;

import java.util.Scanner;

/**
 * Main application class for the Property Booking System.
 * Initializes repositories, services, controllers, and views for the application.
 * Provides a main menu interface to navigate between different views: Admin, Guest, and Host.
 */
public class PropertyBookingApp {
    private final PropertyBookingController controller;
    private final Scanner scanner;

    /**
     * Constructs a new PropertyBookingApp with the specified controller.
     * Initializes a Scanner instance for user input.
     *
     * @param controller the main controller used for managing application logic.
     */
    public PropertyBookingApp(PropertyBookingController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Main method to start the Property Booking application.
     * Initializes repositories, services, controllers, and the main application instance.
     * Runs the main application loop.
     *
     * @param args command-line arguments (not used).
     */
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

    /**
     * Runs the main application loop.
     * Displays the main menu and handles user input to navigate between views.
     */
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

    /**
     * Displays the main menu options to the user.
     * Options include navigating to Admin, Guest, and Host views or exiting the application.
     */
    private void showMainMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. Admin View");
        System.out.println("2. Guest View");
        System.out.println("3. Host View");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }
}
