public class week2a1 {

    void checkPinLength(String pin) {
        if (pin.length() != 4) {
            System.out.println("Invalid PIN — must be exactly 4 digits.");
        } else {
            System.out.println("PIN length OK.");
        }
    }

    public static void main(String[] args) {
        week2a1 validator = new week2a1();
        validator.checkPinLength("482");
        validator.checkPinLength("4820");
    }
}
