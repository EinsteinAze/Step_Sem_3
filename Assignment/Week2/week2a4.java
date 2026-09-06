public class week2a4 {

    String normalizeCode(String raw) {
        String trimmed = raw.trim();
        if (trimmed.length() < 3) return trimmed;
        String pubCode = trimmed.substring(0, 3).toUpperCase();
        String rest = trimmed.substring(3);
        return pubCode + rest;
    }

    String validateAndFormat(String code) {
        if (code.length() != 13) {
            return "Invalid: wrong length";
        }
        String pubCode = code.substring(0, 3);
        String body = code.substring(3);

        for (int i = 0; i < pubCode.length(); i++) {
            if (!Character.isLetter(pubCode.charAt(i))) {
                return "Invalid: publisher code must be 3 letters";
            }
        }

        for (int i = 0; i < body.length(); i++) {
            if (!Character.isDigit(body.charAt(i))) {
                return "Invalid: remaining characters must be digits";
            }
        }

        String year = body.substring(0, 4);
        String catalog = body.substring(4);

        StringBuilder result = new StringBuilder();
        result.append("[").append(pubCode).append("] YEAR: ").append(year).append(" | CATALOG: ").append(catalog);
        return result.toString();
    }

    public static void main(String[] args) {
        week2a4 validator = new week2a4();

        String raw1 = " pen2026004251 ";
        String normalized1 = validator.normalizeCode(raw1);
        System.out.println(validator.validateAndFormat(normalized1));

        String raw2 = "12N2026004251";
        String normalized2 = validator.normalizeCode(raw2);
        System.out.println(validator.validateAndFormat(normalized2));
    }
}
