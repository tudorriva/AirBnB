package Controller;

import Entities.*;
import Services.PropertyBookingService;
import Helpers.HelperFunctions;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * Controller class for managing property bookings.
 */
public class PropertyBookingController {
    private final PropertyBookingService bookingService;

    /**
     * Constructs a PropertyBookingController with the specified booking service.
     *
     * @param bookingService the booking service to be used by this controller
     */
    public PropertyBookingController(PropertyBookingService bookingService) {
        this.bookingService = bookingService;
    }

    // -------------------- Host Operations --------------------

    /**
     * Adds a new host.
     *
     * @param host the host to be added
     */
    public void addHost(Host host) {
        bookingService.addHost(host);
        System.out.println("Host added successfully");
    }

    /**
     * Lists all hosts.
     */
    public void listAllHosts() {
        List<Host> hosts = bookingService.getAllHosts();

        if (hosts.isEmpty()) {
            System.out.println("No hosts found.");
        } else {
            System.out.println("Hosts found:");
            for (Host host : hosts) {
                System.out.println(host);
            }
        }
    }

    /**
     * Retrieves a host by their ID.
     *
     * @param id the ID of the host
     * @return the host with the specified ID
     */
    public Host getHostById(int id) {
        return bookingService.getHostById(id);
    }

    /**
     * Retrieves all hosts.
     *
     * @return a list of all hosts
     */
    public List<Host> getAllHosts() {
        return bookingService.getAllHosts();
    }

    /**
     * Retrieves properties managed by a specific host.
     *
     * @param hostId the ID of the host
     * @return a list of properties managed by the host
     */
    public List<Property> getPropertiesForHost(int hostId) {
        return bookingService.getPropertiesForHost(hostId);
    }

    /**
     * Lists properties managed by a specific host.
     *
     * @param host the host whose properties are to be listed
     */
    public void listPropertiesForHost(Host host) {
        List<Property> properties = bookingService.getPropertiesForHost(host.getId());
        if (properties.isEmpty()) {
            System.out.println("No properties found for this host.");
        } else {
            System.out.println("Properties managed by " + host.getName() + ":");
            for (Property property : properties) {
                System.out.println(property);
            }
        }
    }

    /**
     * Shows properties managed by a specific host.
     *
     * @param host the host whose properties are to be shown
     */
    public void showPropertiesForHost(Host host) {
        List<Property> properties = bookingService.getPropertiesForHost(host.getId());
        if (properties.isEmpty()) {
            System.out.println("No properties found for this host.");
            return;
        }

        System.out.println("Properties managed by host " + host.getName() + ":");
        for (Property property : properties) {
            System.out.println("Property ID: " + property.getPropertyID());
            System.out.println("Address: " + property.getAddress());
            System.out.println("Price per Night: " + property.getPricePerNight());
            System.out.println("Description: " + property.getDescription());
            System.out.println("Location: " + property.getLocation().getCity() + ", " + property.getLocation().getCountry());
            System.out.println("Amenity: " + property.getAmenity().getName() + " - " + property.getAmenity().getDescription());
            System.out.println("Cancellation Policy: " + property.getCancellationPolicy().getDescription());
            System.out.println();
        }
    }

    /**
     * Lists a new property for a host.
     *
     * @param host the host listing the property
     * @param address the address of the property
     * @param pricePerNight the price per night for the property
     * @param description the description of the property
     * @param location the location of the property
     * @param amenity the amenity of the property
     * @param cancellationPolicy the cancellation policy of the property
     */
    public void listProperty(Host host, String address, double pricePerNight, String description, Location location, Amenity amenity, CancellationPolicy cancellationPolicy) {
        int id = HelperFunctions.randomId();
        Property property = new Property(id, address, pricePerNight, description, location, amenity, cancellationPolicy, host.getId());
        bookingService.addProperty(property);
    }

    /**
     * Adds an amenity to a property managed by a host.
     *
     * @param host the host managing the property
     * @param propertyIndex the index of the property in the host's property list
     * @param name the name of the amenity
     * @param description the description of the amenity
     */
    public void addAmenityToProperty(Host host, int propertyIndex, String name, String description) {
        List<Property> properties = getPropertiesForHost(host.getId());
        if (propertyIndex < 0 || propertyIndex >= properties.size()) {
            System.out.println("Invalid property number.");
            return;
        }

        Property property = properties.get(propertyIndex);
        int id = HelperFunctions.randomId();
        Amenity amenity = new Amenity(id, name, description);
        bookingService.addAmenityToProperty(property, amenity);
    }

    // -------------------- Guest Operations --------------------

    /**
     * Adds a new guest.
     *
     * @param guest the guest to be added
     */
    public void addGuest(Guest guest) {
        bookingService.addGuest(guest);
        System.out.println("Guest added successfully");
    }

    /**
     * Lists all guests.
     */
    public void listAllGuests() {
        List<Guest> guests = bookingService.getAllGuests();

        if (guests.isEmpty()) {
            System.out.println("No guests found.");
        } else {
            System.out.println("Guests found:");
            for (Guest guest : guests) {
                System.out.println(guest);
            }
        }
    }

    /**
     * Retrieves a guest by their ID.
     *
     * @param id the ID of the guest
     * @return the guest with the specified ID
     */
    public Guest getGuestById(int id) {
        return bookingService.getGuestById(id);
    }

    /**
     * Retrieves all guests.
     *
     * @return a list of all guests
     */
    public List<Guest> getAllGuests() {
        return bookingService.getAllGuests();
    }

    /**
     * Retrieves bookings for a specific guest.
     *
     * @param guestId the ID of the guest
     * @return a list of bookings for the guest
     */
    public List<Booking> getBookingsForGuest(int guestId) {
        return bookingService.getBookingsForGuest(guestId);
    }

    /**
     * Books a property for a guest.
     *
     * @param guest the guest booking the property
     * @param propertyId the ID of the property to be booked
     * @param checkInDate the check-in date
     * @param checkOutDate the check-out date
     */
    public void bookProperty(Guest guest, int propertyId, Date checkInDate, Date checkOutDate) {
        Property property = bookingService.getPropertyById(propertyId);
        if (property != null) {
            boolean success = bookingService.bookProperty(guest, property, checkInDate, checkOutDate);
            if (success) {
                System.out.println("Booking successful for property: " + property.getAddress());
            } else {
                System.out.println("Booking failed. The property may not be available for the requested dates.");
            }
        } else {
            System.out.println("Invalid property ID.");
        }
    }

    /**
     * Views bookings for a guest.
     *
     * @param guest the guest whose bookings are to be viewed
     */
    public void viewBookings(Guest guest) {
        List<Booking> bookings = bookingService.getBookingsForGuest(guest.getId());
        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
        } else {
            bookings.forEach(System.out::println);
        }
    }

    /**
     * Leaves a review for a property.
     *
     * @param guest the guest leaving the review
     * @param propertyId the ID of the property being reviewed
     * @param rating the rating given by the guest
     * @param comment the comment given by the guest
     */
    public void leaveReview(Guest guest, int propertyId, double rating, String comment) {
        Property property = bookingService.getPropertyById(propertyId);
        if (property != null) {
            bookingService.addReview(guest, property, rating, comment);
        } else {
            System.out.println("Invalid property ID.");
        }
    }

    /**
     * Views all properties in a specific location.
     *
     * @param location the location to search for properties
     */
    public void viewAllPropertiesByLocation(Location location) {
        List<Property> properties = bookingService.getPropertiesByLocation(location);
        if (properties.isEmpty()) {
            System.out.println("No properties found for location: " + location);
        } else {
            properties.forEach(System.out::println);
        }
    }

    /**
     * Views all properties available on a specific date.
     *
     * @param date the date to search for available properties
     */
    public void viewAllPropertiesByDate(Date date) {
        List<Property> properties = bookingService.getAllProperties().stream()
                .filter(property -> property.checkAvailability(date, date))
                .collect(Collectors.toList());
        if (properties.isEmpty()) {
            System.out.println("No properties available on: " + date);
        } else {
            properties.forEach(System.out::println);
        }
    }

    // -------------------- Property Operations --------------------

    /**
     * Adds a new property.
     *
     * @param property the property to be added
     */
    public void addProperty(Property property) {
        bookingService.addProperty(property);
        System.out.println("Property added successfully");
    }

    /**
     * Lists all properties.
     */
    public void listAllProperties() {
        List<Property> properties = bookingService.getAllProperties();

        if (properties.isEmpty()) {
            System.out.println("No properties found.");
        } else {
            System.out.println("Properties found:");
            for (Property property : properties) {
                System.out.println(property);
            }
        }
    }

    /**
     * Retrieves a property by its ID.
     *
     * @param id the ID of the property
     * @return the property with the specified ID
     */
    public Property getPropertyById(int id) {
        return bookingService.getPropertyById(id);
    }

    /**
     * Lists properties in a specific location.
     *
     * @param location the location to search for properties
     */
    public void listPropertiesByLocation(Location location) {
        List<Property> properties = bookingService.getPropertiesByLocation(location);

        if (properties.isEmpty()) {
            System.out.println("No properties found for location: " + location);
        } else {
            System.out.println("Properties found at location " + location + ":");
            for (Property property : properties) {
                System.out.println(property);
            }
        }
    }

    // -------------------- Amenity Operations --------------------

    /**
     * Adds an amenity to a property.
     *
     * @param property the property to which the amenity is to be added
     * @param amenity the amenity to be added
     */
    public void addAmenityToProperty(Property property, Amenity amenity) {
        bookingService.addAmenityToProperty(property, amenity);
        System.out.println("Amenity added to property: " + property.getAddress());
    }

    /**
     * Lists amenities for a property.
     *
     * @param property the property whose amenities are to be listed
     */
    public void listAmenitiesForProperty(Property property) {
        List<Amenity> amenities = bookingService.getAmenitiesForProperty(property);

        if (amenities.isEmpty()) {
            System.out.println("No amenities found for this property.");
        } else {
            System.out.println("Amenities for property " + property.getAddress() + ":");
            for (Amenity amenity : amenities) {
                System.out.println(amenity);
            }
        }
    }

    // -------------------- Booking Operations --------------------

    /**
     * Books a property for a guest.
     *
     * @param guest the guest booking the property
     * @param property the property to be booked
     * @param checkInDate the check-in date
     * @param checkOutDate the check-out date
     */
    public void bookProperty(Guest guest, Property property, Date checkInDate, Date checkOutDate) {
        boolean success = bookingService.bookProperty(guest, property, checkInDate, checkOutDate);

        if (success) {
            System.out.println("Booking successful for property: " + property.getAddress());
        } else {
            System.out.println("Booking failed. The property may not be available for the requested dates.");
        }
    }

    /**
     * Lists bookings for a property.
     *
     * @param propertyId the ID of the property
     */
    public void listBookingsForProperty(int propertyId) {
        List<Booking> bookings = bookingService.getBookingsForProperty(propertyId);

        if (bookings.isEmpty()) {
            System.out.println("No bookings found for this property.");
        } else {
            System.out.println("Bookings for property ID " + propertyId + ":");
            for (Booking booking : bookings) {
                System.out.println(booking);
            }
        }
    }

    // -------------------- Review Operations --------------------

    /**
     * Adds a review for a property.
     *
     * @param guest the guest leaving the review
     * @param property the property being reviewed
     * @param rating the rating given by the guest
     * @param comment the comment given by the guest
     */
    public void addReview(Guest guest, Property property, double rating, String comment) {
        bookingService.addReview(guest, property, rating, comment);
        System.out.println("Review added for property: " + property.getAddress());
    }

    /**
     * Lists reviews for a property.
     *
     * @param propertyId the ID of the property
     */
    public void listReviewsForProperty(int propertyId) {
        List<Review> reviews = bookingService.getReviewsForProperty(propertyId);

        if (reviews.isEmpty()) {
            System.out.println("No reviews found for this property.");
        } else {
            System.out.println("Reviews for property ID " + propertyId + ":");
            for (Review review : reviews) {
                System.out.println(review);
            }
        }
    }

    // -------------------- Payment Operations --------------------

    /**
     * Processes a payment for a booking.
     *
     * @param guest the guest making the payment
     * @param bookingId the ID of the booking
     */
    public void makePayment(Guest guest, int bookingId) {
        Booking booking = bookingService.getBookingById(bookingId);
        if (booking != null && booking.getGuestID() == guest.getId()) {
            bookingService.processPaymentForBooking(booking);
            System.out.println("Payment successful for booking ID: " + bookingId);
        } else {
            System.out.println("Invalid booking ID or this booking does not belong to you.");
        }
    }

    /**
     * Views payments received by a host.
     *
     * @param host the host whose payments are to be viewed
     */
    public void viewPaymentsForHost(Host host) {
        List<Payment> payments = bookingService.getPaymentsForHost(host.getId());
        if (payments.isEmpty()) {
            System.out.println("No payments received by this host.");
        } else {
            System.out.println("Payments received by " + host.getName() + ":");
            for (Payment payment : payments) {
                System.out.println(payment);
            }
        }
    }

    /**
     * Views transaction history for a host.
     *
     * @param host the host whose transaction history is to be viewed
     */
    public void viewTransactionHistoryForHost(Host host) {
        List<Payment> transactionHistory = bookingService.getTransactionHistoryForHost(host.getId());
        if (transactionHistory.isEmpty()) {
            System.out.println("No transaction history for this host.");
        } else {
            System.out.println("Transaction history for " + host.getName() + ":");
            for (Payment payment : transactionHistory) {
                System.out.println(payment);
            }
        }
    }
}