package Services;


import Entities.*;
import Repository.IRepository;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Properties;
import java.util.stream.Collectors;

public class PropertyBookingService {
    private final IRepository<Host> hostRepo;
    private final IRepository<Guest> guestRepo;
    private final IRepository<Property> propertyRepo;
    private final IRepository<Booking> bookingRepo;
    private final IRepository<Review> reviewRepo;
    private final IRepository<Amenity> amenityRepo;


    public PropertyBookingService(
            IRepository<Host> hostRepo,
            IRepository<Guest> guestRepo,
            IRepository<Property> propertyRepo,
            IRepository<Booking> bookingRepo,
            IRepository<Review> reviewRepo,
            IRepository<Amenity> amenityRepo) {
        this.hostRepo = hostRepo;
        this.guestRepo = guestRepo;
        this.propertyRepo = propertyRepo;
        this.bookingRepo = bookingRepo;
        this.reviewRepo = reviewRepo;
        this.amenityRepo = amenityRepo;
    }

    private void initializeRepositories() {
        // Adding Hosts
        Host host1 = new Host(1, "Andrei Popescu", "andrei.popescu@example.com", "0722334455", 4.5);
        Host host2 = new Host(2, "Ioana Ionescu", "ioana.ionescu@example.com", "0722456789", 4.7);
        hostRepo.create(host1);
        hostRepo.create(host2);

        // Adding Guests
        Guest guest1 = new Guest(1, "Maria Enescu", "maria.enescu@example.com", "0721345678", 4.3);
        Guest guest2 = new Guest(2, "Bogdan Gheorghe", "bogdan.gheorghe@example.com", "0722987654", 4.6);
        guestRepo.create(guest1);
        guestRepo.create(guest2);

        // Adding Locations
        Location location1 = new Location(1, "București", "România");
        Location location2 = new Location(2, "Cluj-Napoca", "România");

        // Adding Amenities
        Amenity amenity1 = new Amenity(1, "WiFi", "Internet wireless de mare viteză");
        Amenity amenity2 = new Amenity(2, "Parcare", "Parcare gratuită pe proprietate");
        Amenity amenity3 = new Amenity(3, "Piscină", "Acces la piscină exterioară");

        amenityRepo.create(amenity1);
        amenityRepo.create(amenity2);
        amenityRepo.create(amenity3);

        // Adding Properties
        Property property1 = new Property(1, "Strada Libertății 10", 250.0, "Apartament modern în centrul orașului", location1, amenity1, new CancellationPolicy(1, "Politică de anulare flexibilă"), 1);
        Property property2 = new Property(2, "Bulevardul Eroilor 15", 300.0, "Vila spațioasă cu piscină", location2, amenity3, new CancellationPolicy(2, "Politică de anulare strictă"), 2);
        propertyRepo.create(property1);
        propertyRepo.create(property1);
        propertyRepo.create(property2);

    }


    // -------------------- Host and Guest Management --------------------

    public void addHost(Host host) {
        if (host != null)
            hostRepo.create(host);
    }

    public void addGuest(Guest guest) {
        if (guest != null)
            guestRepo.create(guest);
    }

    public List<Host> getAllHosts() { return hostRepo.getAll(); }

    public List<Guest> getAllGuests() { return guestRepo.getAll(); }

    public Host getHostById(int id) {
        return hostRepo.read(id);
    }
    public Guest getGuestById(int id) {
        return guestRepo.read(id);
    }

    public List<Booking> getBookingsForGuest(int guestId) {
        return bookingRepo.getAll().stream()
                .filter(booking -> booking.getGuestID() == guestId)
                .collect(Collectors.toList());
    }

    public List<Property> getPropertiesForHost(int hostId) {
        return propertyRepo.getAll().stream()
                .filter(property -> property.getHostID() == hostId)
                .collect(Collectors.toList());
    }




    // -------------------- Property and Amenity Management --------------------

    public void addProperty(Property property) {
        if (property != null)
            propertyRepo.create(property);
    }

    public List<Property> getAllProperties() { return propertyRepo.getAll(); }

    public Property getPropertyById(int id) {
        return propertyRepo.read(id);
    }

    public List<Property> getPropertiesByLocation(Location location) {
        return propertyRepo.getAll().stream()
                .filter(p -> p.getLocation().equals(location))
                .collect(Collectors.toList());
    }

    public void addAmenityToProperty(Property property, Amenity amenity) {
        if (property != null && amenity != null) {
            amenityRepo.create(amenity);
            System.out.println("Amenity added to property: " + amenity.getName());
        }
    }

    public List<Amenity> getAmenitiesForProperty(Property property) {
        return amenityRepo.getAll().stream()
                .filter(amenity -> amenity.getPropertyID() == property.getId())
                .collect(Collectors.toList());
    }

    public Property getPropertyForBooking(Booking booking) {
        return propertyRepo.read(booking.getPropertyID());
    }



    // -------------------- Booking Management --------------------

    public boolean bookProperty(Guest guest, Property property, Date checkInDate, Date checkOutDate) {
        if (property.checkAvailability(checkInDate, checkOutDate)) {
            double totalPrice = property.getPricePerNight() * getDaysBetween(checkInDate, checkOutDate);

            int paymentId = generateUniqueId();
            Payment payment = new Payment(paymentId, totalPrice, new Date());
            payment.processPayment();

            int bookingId = generateUniqueId();
            Booking booking = new Booking(bookingId, checkOutDate, checkInDate, totalPrice, guest.getId(), property.getId(), payment);

            bookingRepo.create(booking);
            System.out.println("Booking created successfully for property: " + property.getAddress() + " with ID: " + booking.getId());

            return true;
        }
        System.out.println("Property is not available for the requested dates.");
        return false;
    }


    public List<Booking> getBookingsForProperty(int propertyId) {
        return bookingRepo.getAll().stream()
                .filter(booking -> booking.getPropertyId() == propertyId)
                .collect(Collectors.toList());
    }

    public Booking getBookingById(int bookingId) {
        return bookingRepo.read(bookingId);
    }

    // -------------------- Review Management --------------------

    public void addReview(Guest guest, Property property, double rating, String comment) {
        if (guest != null && property != null) {
            Review review = new Review(0, guest.getId(), property.getId(), rating, comment, new Date());
            reviewRepo.create(review);
            System.out.println("Review added for property: " + property.getAddress());
        } else {
            System.out.println("Invalid guest or property for review.");
        }
    }

    public List<Review> getReviewsForProperty(int propertyId) {
        return reviewRepo.getAll().stream()
                .filter(review -> review.getPropertyID() == propertyId)
                .collect(Collectors.toList());
    }

    // -------------------- Utility Methods --------------------

    private long getDaysBetween(Date start, Date end) {
        return (end.getTime() - start.getTime()) / (1000 * 60 * 60 * 24);
    }

    private int generateUniqueId() {
        return (int) (Math.random() * 10000);
    }

    public void processPaymentForBooking(Booking booking) {
        Payment payment = booking.getPayment();
        if (!payment.isProcessed()) {
            payment.processPayment();
            System.out.println("Payment processed for booking ID: " + booking.getId());
        } else {
            System.out.println("Payment has already been processed for this booking.");
        }
    }

    public List<Payment> getPaymentsForHost(int hostId) {
        return bookingRepo.getAll().stream()
                .filter(booking -> booking.getPropertyID().getHostId() == hostId)
                .map(Booking::getPayment)
                .filter(Payment::isProcessed)
                .collect(Collectors.toList());
    }

    public List<Payment> getTransactionHistoryForHost(int hostId) {
        return bookingRepo.getAll().stream()
                .filter(booking -> booking.getPropertyId().getHostId() == hostId)
                .map(Booking::getPayment)
                .collect(Collectors.toList());
    }



}
