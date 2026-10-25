import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

abstract class Seat {
    private final String id;

    Seat(String id) {
        this.id = id;
    }

    String getId() {
        return id;
    }

    abstract double getPrice();
}

class RegularSeat extends Seat {
    RegularSeat(String id) {
        super(id);
    }

    @Override
    double getPrice() {
        return 150.0;
    }
}

class PremiumSeat extends Seat {
    PremiumSeat(String id) {
        super(id);
    }

    @Override
    double getPrice() {
        return 250.0;
    }
}

class ReclinerSeat extends Seat {
    ReclinerSeat(String id) {
        super(id);
    }

    @Override
    double getPrice() {
        return 400.0;
    }
}

class Customer {
    private final String name;

    Customer(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }
}

class Show {
    private final String title;
    private final LocalDateTime startTime;
    private final Set<String> bookedSeatIds = new HashSet<>();

    Show(String title, LocalDateTime startTime) {
        this.title = title;
        this.startTime = startTime;
    }

    String getTitle() {
        return title;
    }

    LocalDateTime getStartTime() {
        return startTime;
    }

    boolean isBooked(String seatId) {
        return bookedSeatIds.contains(seatId);
    }

    void reserve(List<Seat> seats) {
        for (Seat s : seats) {
            bookedSeatIds.add(s.getId());
        }
    }

    void release(List<Seat> seats) {
        for (Seat s : seats) {
            bookedSeatIds.remove(s.getId());
        }
    }
}

class Booking {
    private final Customer customer;
    private final Show show;
    private final List<Seat> seats;
    private boolean cancelled;

    Booking(Customer customer, Show show, List<Seat> seats) {
        this.customer = customer;
        this.show = show;
        this.seats = new ArrayList<>(seats);
    }

    Customer getCustomer() {
        return customer;
    }

    Show getShow() {
        return show;
    }

    List<Seat> getSeats() {
        return Collections.unmodifiableList(seats);
    }

    boolean isCancelled() {
        return cancelled;
    }

    void markCancelled() {
        cancelled = true;
    }

    double total() {
        double sum = 0;
        for (Seat s : seats) {
            sum += s.getPrice();
        }
        return sum;
    }

    String seatList() {
        StringJoiner joiner = new StringJoiner(", ");
        for (Seat s : seats) {
            joiner.add(s.getId());
        }
        return joiner.toString();
    }
}

class BookingDesk {
    private static final int MAX_SEATS = 6;

    Booking book(Customer customer, Show show, Seat... requested) {
        if (requested.length == 0) {
            System.out.println("Select at least one seat.");
            return null;
        }
        if (requested.length > MAX_SEATS) {
            System.out.println("Cannot book more than " + MAX_SEATS + " seats in one booking.");
            return null;
        }
        Set<String> seen = new HashSet<>();
        for (Seat s : requested) {
            if (!seen.add(s.getId())) {
                System.out.println("Seat " + s.getId() + " was selected more than once.");
                return null;
            }
            if (show.isBooked(s.getId())) {
                System.out.println("Seat " + s.getId() + " is already booked for this show.");
                return null;
            }
        }
        List<Seat> seats = new ArrayList<>();
        Collections.addAll(seats, requested);
        show.reserve(seats);
        Booking booking = new Booking(customer, show, seats);
        System.out.println("Booking confirmed for " + customer.getName() + ": " + booking.seatList()
                + ". Total: ₹" + String.format("%.2f", booking.total()) + ".");
        return booking;
    }

    boolean cancel(Booking booking, LocalDateTime now) {
        if (booking.isCancelled()) {
            System.out.println(booking.getCustomer().getName() + "'s booking is already cancelled.");
            return false;
        }
        if (!now.isBefore(booking.getShow().getStartTime())) {
            System.out.println("Cannot cancel " + booking.getCustomer().getName()
                    + "'s booking: the show has already started.");
            return false;
        }
        booking.getShow().release(booking.getSeats());
        booking.markCancelled();
        System.out.println(booking.getCustomer().getName() + "'s booking cancelled. Seats " + booking.seatList()
                + " released.");
        return true;
    }
}

public class week8a3 {
    public static void main(String[] args) {
        BookingDesk desk = new BookingDesk();
        Show show = new Show("Campus Premiere", LocalDateTime.of(2026, 3, 20, 19, 0));
        LocalDateTime now = LocalDateTime.of(2026, 3, 20, 15, 0);

        Customer asha = new Customer("Asha");
        Customer ravi = new Customer("Ravi");
        Customer neha = new Customer("Neha");

        Booking ashaBooking = desk.book(asha, show, new RegularSeat("A1"), new RegularSeat("A2"), new PremiumSeat("F5"));
        desk.book(ravi, show, new RegularSeat("A2"));
        desk.book(ravi, show, new ReclinerSeat("R1"));
        desk.cancel(ashaBooking, now);
        desk.book(neha, show, new RegularSeat("A2"));
    }
}
