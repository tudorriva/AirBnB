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

    public void initializeRepositories() {
        // Adding Hosts
        Host host1 = new Host(1, "Andrei Popescu", "andrei.popescu@domeniu.ro", "0722334455", 4.5);
        Host host2 = new Host(2, "Ioana Ionescu", "ioana.ionescu@domeniu.ro", "0722456789", 4.7);
        Host host3 = new Host(3, "Mihai Georgescu", "mihai.georgescu@domeniu.ro", "0722123456", 4.8);
        hostRepo.create(host1);
        hostRepo.create(host2);
        hostRepo.create(host3);

        // Adding Guests
        Guest guest1 = new Guest(1, "Maria Enescu", "maria.enescu@domeniu.ro", "0721345678", 4.3);
        Guest guest2 = new Guest(2, "Bogdan Gheorghe", "bogdan.gheorghe@domeniu.ro", "0722987654", 4.6);
        Guest guest3 = new Guest(3, "Elena Popa", "elena.popa@domeniu.ro", "0723456789", 4.4);
        guestRepo.create(guest1);
        guestRepo.create(guest2);
        guestRepo.create(guest3);

        // Adding Locations
        Location location1 = new Location(1, "Bucuresti", "Romania");
        Location location2 = new Location(2, "Cluj-Napoca", "Romania");
        Location location3 = new Location(3, "Timisoara", "Romania");

        // Adding Amenities
        Amenity amenity1 = new Amenity(1, "WiFi", "Internet wireless de mare viteza");
        Amenity amenity2 = new Amenity(2, "Parcare", "Parcare gratuita pe proprietate");
        Amenity amenity3 = new Amenity(3, "Piscina", "Acces la piscina exterioara");
        Amenity amenity4 = new Amenity(4, "Aer conditionat", "Aer conditionat in toate camerele");

        amenityRepo.create(amenity1);
        amenityRepo.create(amenity2);
        amenityRepo.create(amenity3);
        amenityRepo.create(amenity4);

        // Adding Properties
        Property property1 = new Property(1, "Strada Libertatii 10", 250.0, "Apartament modern in centrul orasului", location1, amenity1, new CancellationPolicy(1, "Politica de anulare flexibila"), 1);
        Property property2 = new Property(2, "Bulevardul Eroilor 15", 300.0, "Vila spatioasa cu piscina", location2, amenity3, new CancellationPolicy(2, "Politica de anulare stricta"), 2);
        Property property3 = new Property(3, "Strada Unirii 20", 200.0, "Garsoniera confortabila", location3, amenity2, new CancellationPolicy(3, "Politica de anulare moderata"), 3);
        Property property4 = new Property(4, "Strada Independentei 5", 350.0, "Casa de vacanta cu aer conditionat", location1, amenity4, new CancellationPolicy(4, "Politica de anulare flexibila"), 1);
        propertyRepo.create(property1);
        propertyRepo.create(property2);
        propertyRepo.create(property3);
        propertyRepo.create(property4);

        // Adding Bookings
        Booking booking1 = new Booking(1, new Date(), new Date(), 250.0, guest1.getId(), property1.getPropertyID(), null);
        Booking booking2 = new Booking(2, new Date(), new Date(), 300.0, guest2.getId(), property2.getPropertyID(), null);
        Booking booking3 = new Booking(3, new Date(), new Date(), 200.0, guest3.getId(), property3.getPropertyID(), null);
        bookingRepo.create(booking1);
        bookingRepo.create(booking2);
        bookingRepo.create(booking3);

        // Adding Reviews
        Review review1 = new Review(1, guest1.getId(), property1.getPropertyID(), 4.5, "Locatie excelenta, foarte curata.", new Date());
        Review review2 = new Review(2, guest2.getId(), property2.getPropertyID(), 4.0, "Vila este foarte spatioasa si confortabila.", new Date());
        Review review3 = new Review(3, guest3.getId(), property3.getPropertyID(), 4.2, "Garsoniera este confortabila si bine echipata.", new Date());
        reviewRepo.create(review1);
        reviewRepo.create(review2);
        reviewRepo.create(review3);
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
                .filter(booking -> getPropertyById(booking.getPropertyID()).getHostID() == hostId)
                .map(Booking::getPayment)
                .filter(Payment::isProcessed)
                .collect(Collectors.toList());
    }

    public List<Payment> getTransactionHistoryForHost(int hostId) {
        return bookingRepo.getAll().stream()
                .filter(booking -> getPropertyById(booking.getPropertyId()).getHostID() == hostId)
                .map(Booking::getPayment)
                .collect(Collectors.toList());
    }



}
