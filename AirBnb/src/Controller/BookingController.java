package Controller;

import Entities.Booking;
import Entities.Payment;
import Repository.IRepository;
import Repository.InMemoryRepo;

import java.util.Date;
import java.util.List;

public class BookingController {
    private final IRepository<Booking> bookingRepo;

    public BookingController() {
        this.bookingRepo = new InMemoryRepo<>();
    }

    public void addBooking(Date checkInDate, Date checkOutDate, double totalPrice, int guestID, int propertyID, Payment payment) {
        Booking booking = new Booking(0, checkOutDate, checkInDate, totalPrice, guestID, propertyID, payment);
        bookingRepo.create(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepo.getAll();
    }

    public void cancelBooking(int bookingID) {
        bookingRepo.delete(bookingID);
    }

    public Booking getBookingById(int bookingID) {
        return bookingRepo.read(bookingID);
    }

    public void addBooking(Booking booking) {
    }
}
