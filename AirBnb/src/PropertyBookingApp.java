import Controller.PropertyBookingController;
import Entities.*;
import Repository.FileRepository;
import Repository.IRepository;
import Services.PropertyBookingService;
import Repository.InMemoryRepo;
import Views.AdminView;
import Views.GuestView;
import Views.HostView;

import java.util.Date;
import java.util.Scanner;

public class PropertyBookingApp {
    private final PropertyBookingController controller;
    private final Scanner scanner;

    public PropertyBookingApp(PropertyBookingController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public static void bulkInitialize(
            IRepository<Host> hostRepo,
            IRepository<Guest> guestRepo,
            IRepository<Property> propertyRepo,
            IRepository<Booking> bookingRepo,
            IRepository<Review> reviewRepo,
            IRepository<Amenity> amenityRepo,
            IRepository<Location> locationRepo,
            IRepository<CancellationPolicy> cancellationPolicyRepo,
            IRepository<Payment> paymentRepo) {

        hostRepo.create(new Host(1, "Andrei Popescu", "andrei.popescu@gmail.com", "0722334455", 4.5));
        hostRepo.create(new Host(2, "Maria Ionescu", "maria.ionescu@gmail.com", "0722445566", 4.7));
        hostRepo.create(new Host(3, "Ion Georgescu", "ion.georgescu@gmail.com", "0722556677", 4.8));
        hostRepo.create(new Host(4, "Elena Vasilescu", "elena.vasilescu@gmail.com", "0722667788", 4.6));
        hostRepo.create(new Host(5, "Cristian Dumitrescu", "cristian.dumitrescu@gmail.com", "0722778899", 4.9));

        guestRepo.create(new Guest(1, "Alexandru Mihai", "alexandru.mihai@gmail.com", "0722889900", 4.2));
        guestRepo.create(new Guest(2, "Ioana Marinescu", "ioana.marinescu@gmail.com", "0722990011", 4.3));
        guestRepo.create(new Guest(3, "Vlad Popa", "vlad.popa@gmail.com", "0722112233", 4.4));
        guestRepo.create(new Guest(4, "Ana Stan", "ana.stan@gmail.com", "0722223344", 4.5));
        guestRepo.create(new Guest(5, "Mihai Radu", "mihai.radu@gmail.com", "0722334455", 4.6));

        locationRepo.create(new Location(1, "Bucuresti", "Romania"));
        locationRepo.create(new Location(2, "Cluj-Napoca", "Romania"));
        locationRepo.create(new Location(3, "Timisoara", "Romania"));
        locationRepo.create(new Location(4, "Iasi", "Romania"));
        locationRepo.create(new Location(5, "Constanta", "Romania"));

        cancellationPolicyRepo.create(new CancellationPolicy(1, "Flexibil"));
        cancellationPolicyRepo.create(new CancellationPolicy(2, "Moderata"));
        cancellationPolicyRepo.create(new CancellationPolicy(3, "Stricta"));
        cancellationPolicyRepo.create(new CancellationPolicy(4, "Super Stricta"));
        cancellationPolicyRepo.create(new CancellationPolicy(5, "Nerambursabila"));

        propertyRepo.create(new Property(1, "Strada Principala 123", 100.0, "Apartament confortabil", locationRepo.read(1), null, cancellationPolicyRepo.read(1), 1));
        propertyRepo.create(new Property(2, "Strada Lateral 456", 150.0, "Casa spatioasa", locationRepo.read(2), null, cancellationPolicyRepo.read(2), 2));
        propertyRepo.create(new Property(3, "Strada Secundara 789", 200.0, "Vila de lux", locationRepo.read(3), null, cancellationPolicyRepo.read(3), 3));
        propertyRepo.create(new Property(4, "Strada Verde 101", 120.0, "Condo modern", locationRepo.read(4), null, cancellationPolicyRepo.read(4), 4));
        propertyRepo.create(new Property(5, "Strada Albastra 202", 180.0, "Cottage fermecator", locationRepo.read(5), null, cancellationPolicyRepo.read(5), 5));

        amenityRepo.create(new Amenity(1, "WiFi", "Internet de mare viteza", 1));
        amenityRepo.create(new Amenity(2, "Piscina", "Piscina exterioara", 2));
        amenityRepo.create(new Amenity(3, "Sala de fitness", "Sala de fitness complet echipata", 3));
        amenityRepo.create(new Amenity(4, "Parcare", "Loc de parcare gratuit", 4));
        amenityRepo.create(new Amenity(5, "Mic dejun", "Mic dejun gratuit", 5));

        paymentRepo.create(new Payment(1, 100.0, new Date()));
        paymentRepo.create(new Payment(2, 150.0, new Date()));
        paymentRepo.create(new Payment(3, 200.0, new Date()));
        paymentRepo.create(new Payment(4, 120.0, new Date()));
        paymentRepo.create(new Payment(5, 180.0, new Date()));

        bookingRepo.create(new Booking(1, new Date(), new Date(), 100.0, 1, 1, paymentRepo.read(1)));
        bookingRepo.create(new Booking(2, new Date(), new Date(), 150.0, 2, 2, paymentRepo.read(2)));
        bookingRepo.create(new Booking(3, new Date(), new Date(), 200.0, 3, 3, paymentRepo.read(3)));
        bookingRepo.create(new Booking(4, new Date(), new Date(), 120.0, 4, 4, paymentRepo.read(4)));
        bookingRepo.create(new Booking(5, new Date(), new Date(), 180.0, 5, 5, paymentRepo.read(5)));

        reviewRepo.create(new Review(1, 1, 1, 4.5, "Locatie excelenta!", new Date()));
        reviewRepo.create(new Review(2, 2, 2, 4.7, "Am adorat!", new Date()));
        reviewRepo.create(new Review(3, 3, 3, 4.8, "Experienta uimitoare!", new Date()));
        reviewRepo.create(new Review(4, 4, 4, 4.6, "Foarte confortabil!", new Date()));
        reviewRepo.create(new Review(5, 5, 5, 4.9, "Recomand cu caldura!", new Date()));
    }

    public static void main(String[] args) {
        boolean useFileRepository = true;

        IRepository<Host> hostRepo;
        IRepository<Guest> guestRepo;
        IRepository<Property> propertyRepo;
        IRepository<Booking> bookingRepo;
        IRepository<Review> reviewRepo;
        IRepository<Amenity> amenityRepo;
        IRepository<Location> locationRepo;
        IRepository<CancellationPolicy> cancellationPolicyRepo;
        IRepository<Payment> paymentRepo;

        if (useFileRepository) {
            String filePath = "C:\\Users\\tudor\\IdeaProjects\\AirBnB_fin\\AirBnb\\src\\Files\\";
            hostRepo = new FileRepository<>(filePath + "hosts.txt");
            guestRepo = new FileRepository<>(filePath + "guests.txt");
            propertyRepo = new FileRepository<>(filePath + "properties.txt");
            bookingRepo = new FileRepository<>(filePath + "bookings.txt");
            reviewRepo = new FileRepository<>(filePath + "reviews.txt");
            amenityRepo = new FileRepository<>(filePath + "amenities.txt");
            locationRepo = new FileRepository<>(filePath + "locations.txt");
            cancellationPolicyRepo = new FileRepository<>(filePath + "cancellationPolicies.txt");
            paymentRepo = new FileRepository<>(filePath + "payments.txt");
        } else {
            hostRepo = new InMemoryRepo<>();
            guestRepo = new InMemoryRepo<>();
            propertyRepo = new InMemoryRepo<>();
            bookingRepo = new InMemoryRepo<>();
            reviewRepo = new InMemoryRepo<>();
            amenityRepo = new InMemoryRepo<>();
            locationRepo = new InMemoryRepo<>();
            cancellationPolicyRepo = new InMemoryRepo<>();
            paymentRepo = new InMemoryRepo<>();
        }

        PropertyBookingService bookingService = new PropertyBookingService(
                hostRepo, guestRepo, propertyRepo, bookingRepo, reviewRepo, amenityRepo, locationRepo, cancellationPolicyRepo, paymentRepo);

        PropertyBookingController controller = new PropertyBookingController(bookingService);

        // bulkInitialize(hostRepo, guestRepo, propertyRepo, bookingRepo, reviewRepo, amenityRepo, locationRepo, cancellationPolicyRepo, paymentRepo);

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
