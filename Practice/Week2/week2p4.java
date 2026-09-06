public class week2p4 {

    String maskPhoneNumber(String phone) {
        if (phone.length() != 10 || !phone.chars().allMatch(Character::isDigit)) {
            return "Invalid phone number";
        }
        StringBuilder masked = new StringBuilder();
        masked.append("XXXXXX");
        masked.append("-");
        masked.append(phone.substring(6));
        return masked.toString();
    }

    public static void main(String[] args) {
        week2p4 formatter = new week2p4();
        System.out.println(formatter.maskPhoneNumber("9876543210"));
        System.out.println(formatter.maskPhoneNumber("98765"));
    }
}
