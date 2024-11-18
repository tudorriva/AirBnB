package Views;

import Controller.PropertyBookingController;
import Entities.*;
import Helpers.HelperFunctions;

import java.util.List;
import java.util.Scanner;

public class HostView {
    private final PropertyBookingController controller;
    private final Scanner scanner;

    public HostView(PropertyBookingController controller, Scanner scanner) {
        this.controller = controller;
        this.scanner = scanner;
    }

    public void run() {
        System.out.println("\nSelect a Host:");
        List<Host> hosts = controller.getAllHosts();
        hosts.forEach(host -> System.out.println(host.getId() + ": " + host.getName()));
        System.out.print("Enter Host ID: ");
        int hostId = Integer.parseInt(scanner.nextLine());
        Host host = controller.getHostById(hostId);

        if (host == null) {
            System.out.println("Invalid Host ID.");
            return;
        }

        boolean running = true;
        while (running) {
            showHostMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> listProperty(host);
                case 2 -> addAmenity(host);
                case 3 -> controller.viewPaymentsForHost(host);
                case 4 -> controller.viewTransactionHistoryForHost(host);
                case 5 -> controller.showPropertiesForHost(host);
                case 0 -> running = false;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void showHostMenu() {
        System.out.println("\nHost Menu:");
        System.out.println("1. List a Property");
        System.out.println("2. Add an Amenity to Property");
        System.out.println("3. View Payments Received");
        System.out.println("4. View Transaction History");
        System.out.println("5. Show Properties Managed by Host");
        System.out.println("0. Go back");
        System.out.print("Choose an option: ");
    }

    private void listProperty(Host host) {
        int prop_id = HelperFunctions.randomId();
        System.out.print("Enter property address: ");
        String address = scanner.nextLine();
        System.out.print("Enter price per night: ");
        double pricePerNight = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter property description: ");
        String description = scanner.nextLine();

        System.out.print("Enter location city: ");
        String city = scanner.nextLine();
        System.out.print("Enter location country: ");
        String country = scanner.nextLine();
        int id = HelperFunctions.randomId();
        Location location = new Location(id, city, country);

        System.out.print("Enter amenity name: ");
        String amenityName = scanner.nextLine();
        System.out.print("Enter amenity description: ");
        String amenityDescription = scanner.nextLine();
        int id2 = HelperFunctions.randomId();
        Amenity amenity = new Amenity(id2, amenityName, amenityDescription, prop_id);

        System.out.print("Enter cancellation policy description: ");
        String cancellationPolicyDescription = scanner.nextLine();
        int id3 = HelperFunctions.randomId();
        CancellationPolicy cancellationPolicy = new CancellationPolicy(id3, cancellationPolicyDescription);

        controller.listProperty(prop_id, host, address, pricePerNight, description, location, amenity, cancellationPolicy);
    }

    private void addAmenity(Host host) {
        List<Property> properties = controller.getPropertiesForHost(host.getId());
        if (properties.isEmpty()) {
            System.out.println("No properties found for this host.");
            return;
        }

        System.out.println("Select a property to add an amenity:");
        for (int i = 0; i < properties.size(); i++) {
            System.out.println((i + 1) + ". " + properties.get(i).getAddress());
        }
        System.out.print("Enter property number: ");
        int propertyIndex = Integer.parseInt(scanner.nextLine()) - 1;

        if (propertyIndex < 0 || propertyIndex >= properties.size()) {
            System.out.println("Invalid property number.");
            return;
        }

        System.out.print("Enter amenity name: ");
        String name = scanner.nextLine();
        System.out.print("Enter amenity description: ");
        String description = scanner.nextLine();

        controller.addAmenityToProperty(host, propertyIndex, name, description);
    }
}