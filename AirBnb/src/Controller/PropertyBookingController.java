package Controller;

import Entities.*;
import Services.PropertyBookingService;
import Helpers.HelperFunctions;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

public class PropertyBookingController {
    private final PropertyBookingService bookingService;

    public PropertyBookingController(PropertyBookingService bookingService) {
        this.bookingService = bookingService;
    }

    // -------------------- Host Operations --------------------

    public void addHost(Host host) {
        bookingService.addHost(host);
        System.out.println("Host added successfully");
    }

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

    public Host getHostById(int id) {
        return bookingService.getHostById(id);
    }

    public List<Host> getAllHosts() {
        return bookingService.getAllHosts();
    }

    public List<Property> getPropertiesForHost(int hostId) {
        return bookingService.getPropertiesForHost(hostId);
    }

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

    public void listProperty(Host host, String address, double pricePerNight, String description, Location location, Amenity amenity, CancellationPolicy cancellationPolicy) {
        int id = HelperFunctions.randomId();
        Property property = new Property(id, address, pricePerNight, description, location, amenity, cancellationPolicy, host.getId());
        bookingService.addProperty(property);
    }

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

    public void addGuest(Guest guest) {
        bookingService.addGuest(guest);
        System.out.println("Guest added successfully");
    }

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

    public Guest getGuestById(int id) {
        return bookingService.getGuestById(id);
    }

    public List<Guest> getAllGuests() {
        return bookingService.getAllGuests();
    }

    public List<Booking> getBookingsForGuest(int guestId) {
        return bookingService.getBookingsForGuest(guestId);
    }

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

    public void viewBookings(Guest guest) {
        List<Booking> bookings = bookingService.getBookingsForGuest(guest.getId());
        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
        } else {
            bookings.forEach(System.out::println);
        }
    }

    public void leaveReview(Guest guest, int propertyId, double rating, String comment) {
        Property property = bookingService.getPropertyById(propertyId);
        if (property != null) {
            bookingService.addReview(guest, property, rating, comment);
        } else {
            System.out.println("Invalid property ID.");
        }
    }

    public void viewAllPropertiesByLocation(Location location) {
        List<Property> properties = bookingService.getPropertiesByLocation(location);
        if (properties.isEmpty()) {
            System.out.println("No properties found for location: " + location);
        } else {
            properties.forEach(System.out::println);
        }
    }

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

    public void addProperty(Property property) {
        bookingService.addProperty(property);
        System.out.println("Property added successfully");
    }

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

    public Property getPropertyById(int id) {
        return bookingService.getPropertyById(id);
    }

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

    public void addAmenityToProperty(Property property, Amenity amenity) {
        bookingService.addAmenityToProperty(property, amenity);
        System.out.println("Amenity added to property: " + property.getAddress());
    }

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

    public void bookProperty(Guest guest, Property property, Date checkInDate, Date checkOutDate) {
        boolean success = bookingService.bookProperty(guest, property, checkInDate, checkOutDate);

        if (success) {
            System.out.println("Booking successful for property: " + property.getAddress());
        } else {
            System.out.println("Booking failed. The property may not be available for the requested dates.");
        }
    }

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

    public void addReview(Guest guest, Property property, double rating, String comment) {
        bookingService.addReview(guest, property, rating, comment);
        System.out.println("Review added for property: " + property.getAddress());
    }

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

    public void makePayment(Guest guest, int bookingId) {
        Booking booking = bookingService.getBookingById(bookingId);
        if (booking != null && booking.getGuestID() == guest.getId()) {
            bookingService.processPaymentForBooking(booking);
            System.out.println("Payment successful for booking ID: " + bookingId);
        } else {
            System.out.println("Invalid booking ID or this booking does not belong to you.");
        }
    }

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