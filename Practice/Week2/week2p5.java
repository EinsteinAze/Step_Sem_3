public class week2p5 {

    String normalizeReference(String raw) {
        String trimmed = raw.trim();
        if (trimmed.length() < 3) return trimmed;
        String bankCode = trimmed.substring(0, 3).toUpperCase();
        String rest = trimmed.substring(3);
        return bankCode + rest;
    }

    String validateAndFormat(String reference) {
        if (reference.length() != 14) {
            return "Invalid: wrong length";
        }
        String bankCode = reference.substring(0, 3);
        String body = reference.substring(3);

        for (int i = 0; i < bankCode.length(); i++) {
            if (!Character.isLetter(bankCode.charAt(i))) {
                return "Invalid: bank code must be 3 letters";
            }
        }

        for (int i = 0; i < body.length(); i++) {
            if (!Character.isDigit(body.charAt(i))) {
                return "Invalid: remaining characters must be digits";
            }
        }

        String date = body.substring(0, 6);
        String seq = body.substring(6);
        String dd = date.substring(0, 2);
        String mm = date.substring(2, 4);
        String yy = date.substring(4, 6);

        StringBuilder result = new StringBuilder();
        result.append("[").append(bankCode).append("] DATE: ").append(dd).append("/").append(mm).append("/").append(yy).append(" | SEQ: ").append(seq);
        return result.toString();
    }

    public static void main(String[] args) {
        week2p5 generator = new week2p5();

        String raw1 = " hdf03022600042 ";
        String normalized1 = generator.normalizeReference(raw1);
        System.out.println(generator.validateAndFormat(normalized1));

        String raw2 = "12F03022600042";
        String normalized2 = generator.normalizeReference(raw2);
        System.out.println(generator.validateAndFormat(normalized2));
    }
}
