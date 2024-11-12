package Controller;

import Entities.*;
import Services.PropertyBookingService;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

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
    // -------------------- Review Operations --------------------

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
