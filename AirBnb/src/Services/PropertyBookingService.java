package Services;


import Entities.*;
import Repository.IRepository;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Service class for managing property bookings.
 */
public class PropertyBookingService {
    private final IRepository<Host> hostRepo;
    private final IRepository<Guest> guestRepo;
    private final IRepository<Property> propertyRepo;
    private final IRepository<Booking> bookingRepo;
    private final IRepository<Review> reviewRepo;
    private final IRepository<Amenity> amenityRepo;
    private final IRepository<Location> locationRepo;
    private final IRepository<CancellationPolicy> cancellationPolicyRepo;
    private final IRepository<Payment> paymentRepo;

    public PropertyBookingService(
            IRepository<Host> hostRepo,
            IRepository<Guest> guestRepo,
            IRepository<Property> propertyRepo,
            IRepository<Booking> bookingRepo,
            IRepository<Review> reviewRepo,
            IRepository<Amenity> amenityRepo,
            IRepository<Location> locationRepo,
            IRepository<CancellationPolicy> cancellationPolicyRepo,
            IRepository<Payment> paymentRepo) {
        this.hostRepo = hostRepo;
        this.guestRepo = guestRepo;
        this.propertyRepo = propertyRepo;
        this.bookingRepo = bookingRepo;
        this.reviewRepo = reviewRepo;
        this.amenityRepo = amenityRepo;
        this.locationRepo = locationRepo;
        this.cancellationPolicyRepo = cancellationPolicyRepo;
        this.paymentRepo = paymentRepo;
    }

    /**
     * Initializes the repositories with sample data.
     */
    public void initializeRepositories() {
        hostRepo.getAll();
        guestRepo.getAll();
        propertyRepo.getAll();
        bookingRepo.getAll();
        reviewRepo.getAll();
        amenityRepo.getAll();
    }

    // -------------------- Host and Guest Management --------------------

    /**
     * Adds a new host.
     *
     * @param host the host to be added
     */
    public void addHost(Host host) {
        if (host != null)
            hostRepo.create(host);
    }

    /**
     * Adds a new guest.
     *
     * @param guest the guest to be added
     */
    public void addGuest(Guest guest) {
        if (guest != null)
            guestRepo.create(guest);
    }

    /**
     * Retrieves all hosts.
     *
     * @return a list of all hosts
     */
    public List<Host> getAllHosts() { return hostRepo.getAll(); }

    /**
     * Retrieves all guests.
     *
     * @return a list of all guests
     */
    public List<Guest> getAllGuests() { return guestRepo.getAll(); }

    /**
     * Retrieves a host by their ID.
     *
     * @param id the ID of the host
     * @return the host with the specified ID
     */
    public Host getHostById(int id) {
        return hostRepo.read(id);
    }

    /**
     * Retrieves a guest by their ID.
     *
     * @param id the ID of the guest
     * @return the guest with the specified ID
     */
    public Guest getGuestById(int id) {
        return guestRepo.read(id);
    }

    /**
     * Retrieves bookings for a specific guest.
     *
     * @param guestId the ID of the guest
     * @return a list of bookings for the guest
     */
    public List<Booking> getBookingsForGuest(int guestId) {
        return bookingRepo.getAll().stream()
                .filter(booking -> booking.getGuestID() == guestId)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves properties managed by a specific host.
     *
     * @param hostId the ID of the host
     * @return a list of properties managed by the host
     */
    public List<Property> getPropertiesForHost(int hostId) {
        return propertyRepo.getAll().stream()
                .filter(property -> property.getHostID() == hostId)
                .collect(Collectors.toList());
    }

    // -------------------- Property and Amenity Management --------------------

    /**
     * Adds a new property.
     *
     * @param property the property to be added
     */
    public void addProperty(Property property) {
        if (property != null)
            propertyRepo.create(property);
    }

    /**
     * Retrieves all properties.
     *
     * @return a list of all properties
     */
    public List<Property> getAllProperties() { return propertyRepo.getAll(); }

    /**
     * Retrieves a property by its ID.
     *
     * @param id the ID of the property
     * @return the property with the specified ID
     */
    public Property getPropertyById(int id) {
        return propertyRepo.read(id);
    }

    /**
     * Retrieves properties in a specific location.
     *
     * @param location the location to search for properties
     * @return a list of properties in the specified location
     */
    public List<Property> getPropertiesByLocation(Location location) {
        return propertyRepo.getAll().stream()
                .filter(p -> p.getLocation().equals(location))
                .collect(Collectors.toList());
    }

    /**
     * Adds an amenity to a property.
     *
     * @param property the property to which the amenity is to be added
     * @param amenity the amenity to be added
     */
    public void addAmenityToProperty(Property property, Amenity amenity) {
        if (property != null && amenity != null) {
            amenityRepo.create(amenity);
            System.out.println("Amenity added to property: " + amenity.getName());
        }
    }

    /**
     * Retrieves amenities for a property.
     *
     * @param property the property whose amenities are to be retrieved
     * @return a list of amenities for the property
     */
    public List<Amenity> getAmenitiesForProperty(Property property) {
        return amenityRepo.getAll().stream()
                .filter(amenity -> amenity.getPropertyID() == property.getId())
                .collect(Collectors.toList());
    }

    /**
     * Retrieves the property associated with a booking.
     *
     * @param booking the booking whose property is to be retrieved
     * @return the property associated with the booking
     */
    public Property getPropertyForBooking(Booking booking) {
        return propertyRepo.read(booking.getPropertyID());
    }

    /**
     * Filters properties by a specific location.
     *
     * @param location the location to filter properties by
     * @return a list of properties in the specified location
     */
    public List<Property> filterPropertiesByLocation(Location location) {
        return propertyRepo.getAll().stream()
                .filter(property -> property.getLocation().equals(location))
                .collect(Collectors.toList());
    }

    // -------------------- Booking Management --------------------

    /**
     * Books a property for a guest.
     *
     * @param guest the guest booking the property
     * @param property the property to be booked
     * @param checkInDate the check-in date
     * @param checkOutDate the check-out date
     * @return true if the booking was successful, false otherwise
     */
    public boolean bookProperty(Guest guest, Property property, Date checkInDate, Date checkOutDate) {
        if (checkAvailability(property.getId(), checkInDate, checkOutDate)) {
            double totalPrice = property.getPricePerNight() * getDaysBetween(checkInDate, checkOutDate);

            int paymentId = generateUniqueId();
            Payment payment = new Payment(paymentId, totalPrice, new Date());
            payment.processPayment();

            int bookingId = generateUniqueId();
            Booking booking = new Booking(bookingId, checkOutDate, checkInDate, totalPrice, guest.getId(), property.getId(), payment);

            bookingRepo.create(booking);
            // System.out.println("Booking created successfully for property: " + property.getAddress() + " with ID: " + booking.getId());

            return true;
        }
        // System.out.println("Property is not available for the requested dates.");
        return false;
    }

    /**
     * Retrieves bookings for a property.
     *
     * @param propertyId the ID of the property
     * @return a list of bookings for the property
     */
    public List<Booking> getBookingsForProperty(int propertyId) {
        return bookingRepo.getAll().stream()
                .filter(booking -> booking.getPropertyId() == propertyId)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a booking by its ID.
     *
     * @param bookingId the ID of the booking
     * @return the booking with the specified ID
     */
    public Booking getBookingById(int bookingId) {
        return bookingRepo.read(bookingId);
    }

    /**
     * Filters guests who have made more than a specified number of bookings.
     *
     * @param minBookings the minimum number of bookings a guest must have
     * @return a list of guests with bookings above the specified threshold
     */
    public List<Guest> filterGuestsByBookingCount(int minBookings) {
        Map<Integer, Long> guestBookingCount = bookingRepo.getAll().stream()
                .collect(Collectors.groupingBy(Booking::getGuestID, Collectors.counting()));

        return guestRepo.getAll().stream()
                .filter(guest -> guestBookingCount.getOrDefault(guest.getId(), 0L) > minBookings)
                .collect(Collectors.toList());
    }

    // -------------------- Review Management --------------------

    /**
     * Adds a review for a property.
     *
     * @param guest the guest leaving the review
     * @param property the property being reviewed
     * @param rating the rating given by the guest
     * @param comment the comment given by the guest
     */
    public void addReview(Guest guest, Property property, double rating, String comment) {
        if (guest != null && property != null) {
            Review review = new Review(0, guest.getId(), property.getId(), rating, comment, new Date());
            reviewRepo.create(review);
            System.out.println("Review added for property: " + property.getAddress());
        } else {
            System.out.println("Invalid guest or property for review.");
        }
    }

    /**
     * Retrieves reviews for a property.
     *
     * @param propertyId the ID of the property
     * @return a list of reviews for the property
     */
    public List<Review> getReviewsForProperty(int propertyId) {
        return reviewRepo.getAll().stream()
                .filter(review -> review.getPropertyID() == propertyId)
                .collect(Collectors.toList());
    }

    // -------------------- Utility Methods --------------------

    /**
     * Calculates the number of days between two dates.
     *
     * @param start the start date
     * @param end the end date
     * @return the number of days between the start and end dates
     */
    private long getDaysBetween(Date start, Date end) {
        return (end.getTime() - start.getTime()) / (1000 * 60 * 60 * 24);
    }

    /**
     * Generates a unique ID.
     *
     * @return a unique ID
     */
    private int generateUniqueId() {
        return (int) (Math.random() * 10000);
    }

    /**
     * Processes a payment for a booking.
     *
     * @param booking the booking for which the payment is to be processed
     */
    public void processPaymentForBooking(Booking booking) {
        Payment payment = booking.getPayment();
        if (!payment.isProcessed()) {
            payment.processPayment();
            System.out.println("Payment processed for booking ID: " + booking.getId());
        } else {
            System.out.println("Payment has already been processed for this booking.");
        }
    }

    /**
     * Retrieves payments received by a host.
     *
     * @param hostId the ID of the host
     * @return a list of payments received by the host
     */
    public List<Payment> getPaymentsForHost(int hostId) {
        return bookingRepo.getAll().stream()
                .filter(booking -> getPropertyById(booking.getPropertyID()).getHostID() == hostId)
                .map(Booking::getPayment)
                .filter(Payment::isProcessed)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves transaction history for a host.
     *
     * @param hostId the ID of the host
     * @return a list of transactions for the host
     */
    public List<Payment> getTransactionHistoryForHost(int hostId) {
        return bookingRepo.getAll().stream()
                .filter(booking -> getPropertyById(booking.getPropertyId()).getHostID() == hostId)
                .map(Booking::getPayment)
                .collect(Collectors.toList());
    }

    public boolean checkAvailability(int propertyId, Date checkInDate, Date checkOutDate) {
        List<Booking> bookings = bookingRepo.getAll().stream()
                .filter(booking -> booking.getPropertyID() == propertyId)
                .collect(Collectors.toList());

        for (Booking booking : bookings) {
            if (booking.getCheckInDate().before(checkOutDate) && booking.getCheckOutDate().after(checkInDate)) {
                return false; // Overlapping booking found
            }
        }
        return true; // No overlapping bookings
    }
}
