class AccessChecker {

    static String classifyAccess(String fieldModifier, String accessorContext) {
        if (fieldModifier.equals("private")) {
            return accessorContext.equals("SAME_CLASS") ? "ALLOWED" : "DENIED";
        }
        if (fieldModifier.equals("default")) {
            return (accessorContext.equals("SAME_CLASS") || accessorContext.equals("SAME_PACKAGE"))
                    ? "ALLOWED" : "DENIED";
        }
        if (fieldModifier.equals("protected")) {
            if (accessorContext.equals("SAME_CLASS")
                    || accessorContext.equals("SAME_PACKAGE")
                    || accessorContext.equals("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")) {
                return "ALLOWED";
            }
            return "DENIED";
        }
        return "ALLOWED";
    }
}

class MovieTicket {
    private int seatNumber;
    int screenId;
    protected double ticketPrice;
    public String movieTitle;

    MovieTicket(int seatNumber, int screenId, double ticketPrice, String movieTitle) {
        this.seatNumber = seatNumber;
        this.screenId = screenId;
        this.ticketPrice = ticketPrice;
        this.movieTitle = movieTitle;
    }
}

class PremiumMovieTicket extends MovieTicket {
    private int loyaltyPoints;

    PremiumMovieTicket(int seatNumber, int screenId, double ticketPrice, String movieTitle, int loyaltyPoints) {
        super(seatNumber, screenId, ticketPrice, movieTitle);
        this.loyaltyPoints = loyaltyPoints;
    }

    double readParentTicketPrice() {
        return this.ticketPrice;
    }
}

public class week5p2 {
    public static void main(String[] args) {
        System.out.println(AccessChecker.classifyAccess("protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"));
        System.out.println(AccessChecker.classifyAccess("protected", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"));
    }
}
