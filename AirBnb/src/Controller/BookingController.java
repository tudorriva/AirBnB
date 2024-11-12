import Entities.Booking;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookingController {
    private List<Booking> bookings;

    public BookingController() {
        bookings = new ArrayList<>();
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public List<Booking> getAllBookings() {
        return bookings;
    }

    public void cancelBooking(int bookingID) {
        bookings.removeIf(booking -> booking.getBookingID() == bookingID);
    }

    public Booking getBookingById(int bookingID) {
        return bookings.stream()
                .filter(booking -> booking.getBookingID() == bookingID)
                .findFirst()
                .orElse(null);
    }
}
