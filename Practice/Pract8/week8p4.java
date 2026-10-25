import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

final class DateText {
    private static final DateTimeFormatter MONTH_DAY = DateTimeFormatter.ofPattern("MMM d", Locale.ENGLISH);

    private DateText() {
    }

    static String day(LocalDate date) {
        return date.format(MONTH_DAY);
    }

    static String range(LocalDate start, LocalDate end) {
        if (start.getMonth() == end.getMonth()) {
            return day(start) + "-" + end.getDayOfMonth();
        }
        return day(start) + "-" + day(end);
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

abstract class Room {
    private final int number;
    private final List<Reservation> reservations = new ArrayList<>();

    Room(int number) {
        this.number = number;
    }

    abstract String category();

    abstract double ratePerNight();

    double calculatePrice(int nights) {
        return ratePerNight() * nights;
    }

    String label() {
        return category() + " " + number;
    }

    boolean isAvailable(LocalDate start, LocalDate end) {
        for (Reservation r : reservations) {
            if (r.isActive() && r.overlaps(start, end)) {
                return false;
            }
        }
        return true;
    }

    void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }
}

class StandardRoom extends Room {
    StandardRoom(int number) {
        super(number);
    }

    @Override
    String category() {
        return "Standard Room";
    }

    @Override
    double ratePerNight() {
        return 100.0;
    }
}

class DeluxeRoom extends Room {
    DeluxeRoom(int number) {
        super(number);
    }

    @Override
    String category() {
        return "Deluxe Room";
    }

    @Override
    double ratePerNight() {
        return 180.0;
    }
}

class Suite extends Room {
    Suite(int number) {
        super(number);
    }

    @Override
    String category() {
        return "Suite";
    }

    @Override
    double ratePerNight() {
        return 300.0;
    }

    @Override
    double calculatePrice(int nights) {
        double total = super.calculatePrice(nights);
        if (nights >= 7) {
            total = total * 0.9;
        }
        return total;
    }
}

class Reservation {
    private final Customer customer;
    private final Room room;
    private final LocalDate checkIn;
    private final LocalDate checkOut;
    private final double price;
    private boolean active = true;

    Reservation(Customer customer, Room room, LocalDate checkIn, LocalDate checkOut) {
        this.customer = customer;
        this.room = room;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        int nights = (int) ChronoUnit.DAYS.between(checkIn, checkOut);
        this.price = room.calculatePrice(nights);
    }

    Customer getCustomer() {
        return customer;
    }

    Room getRoom() {
        return room;
    }

    double getPrice() {
        return price;
    }

    boolean isActive() {
        return active;
    }

    LocalDate cancellationDeadline() {
        return checkIn.minusDays(2);
    }

    boolean overlaps(LocalDate start, LocalDate end) {
        return start.isBefore(checkOut) && checkIn.isBefore(end);
    }

    String describe() {
        return customer.getName() + ", " + room.label() + " (" + DateText.range(checkIn, checkOut) + ")";
    }

    boolean cancel(LocalDate today) {
        if (!active) {
            System.out.println("Reservation for " + describe() + " is already cancelled.");
            return false;
        }
        if (!today.isBefore(cancellationDeadline())) {
            System.out.println("Cannot cancel reservation for " + describe() + ": cancellation deadline ("
                    + DateText.day(cancellationDeadline()) + ") has passed.");
            return false;
        }
        active = false;
        System.out.println("Reservation for " + describe() + " cancelled successfully.");
        return true;
    }
}

class Hotel {
    boolean checkAvailability(Room room, LocalDate start, LocalDate end) {
        boolean available = room.isAvailable(start, end);
        String period = DateText.day(start) + " to " + DateText.day(end);
        if (available) {
            System.out.println(room.label() + " is available from " + period + ".");
        } else {
            System.out.println(room.label() + " is not available from " + period + ".");
        }
        return available;
    }

    Reservation reserve(Customer customer, Room room, LocalDate start, LocalDate end) {
        if (!end.isAfter(start)) {
            System.out.println("Invalid stay: check-out must be after check-in.");
            return null;
        }
        if (!room.isAvailable(start, end)) {
            System.out.println(room.label() + " is not available from " + DateText.day(start) + " to "
                    + DateText.day(end) + ".");
            return null;
        }
        Reservation reservation = new Reservation(customer, room, start, end);
        room.addReservation(reservation);
        System.out.println("Reservation confirmed for " + reservation.describe() + ". Price: $"
                + String.format("%.2f", reservation.getPrice()) + ".");
        return reservation;
    }
}

public class week8p4 {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        Room standard101 = new StandardRoom(101);
        Room deluxe201 = new DeluxeRoom(201);

        Customer a = new Customer("Customer A");
        Customer b = new Customer("Customer B");
        Customer c = new Customer("Customer C");

        LocalDate today = LocalDate.of(2025, 12, 20);

        hotel.checkAvailability(standard101, LocalDate.of(2026, 1, 1), LocalDate.of(2026, 1, 5));
        Reservation resA = hotel.reserve(a, standard101, LocalDate.of(2026, 1, 1), LocalDate.of(2026, 1, 5));
        hotel.reserve(b, standard101, LocalDate.of(2026, 1, 3), LocalDate.of(2026, 1, 7));
        resA.cancel(today);
        hotel.reserve(c, deluxe201, LocalDate.of(2026, 2, 10), LocalDate.of(2026, 2, 12));
    }
}
