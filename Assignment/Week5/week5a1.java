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
            return (accessorContext.equals("SAME_CLASS") || accessorContext.equals("SAME_PACKAGE"))
                    ? "ALLOWED" : "DENIED";
        }
        return "ALLOWED";
    }

    static String summarizeByModifier(String[][] attempts) {
        String[] modifiers = {"private", "default", "protected", "public"};
        int[] allowedCounts = new int[modifiers.length];
        int[] deniedCounts = new int[modifiers.length];

        for (String[] attempt : attempts) {
            String modifier = attempt[0];
            String result = classifyAccess(modifier, attempt[1]);
            for (int i = 0; i < modifiers.length; i++) {
                if (modifiers[i].equals(modifier)) {
                    if (result.equals("ALLOWED")) {
                        allowedCounts[i]++;
                    } else {
                        deniedCounts[i]++;
                    }
                    break;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < modifiers.length; i++) {
            if (i > 0) {
                sb.append(" | ");
            }
            sb.append(modifiers[i]).append(": ")
                    .append(allowedCounts[i]).append(" allowed / ")
                    .append(deniedCounts[i]).append(" denied");
        }
        return sb.toString();
    }
}

class LibraryMember {
    private String membershipPin;
    String branchCode;
    protected double finesOwed;
    public String displayName;

    LibraryMember(String membershipPin, String branchCode, double finesOwed, String displayName) {
        this.membershipPin = membershipPin;
        this.branchCode = branchCode;
        this.finesOwed = finesOwed;
        this.displayName = displayName;
    }
}

public class week5a1 {
    public static void main(String[] args) {
        System.out.println(AccessChecker.classifyAccess("private", "SAME_CLASS"));
        System.out.println(AccessChecker.classifyAccess("protected", "DIFFERENT_PACKAGE"));
        System.out.println(AccessChecker.summarizeByModifier(new String[][]{
                {"private", "SAME_CLASS"},
                {"private", "SAME_PACKAGE"},
                {"default", "SAME_PACKAGE"},
                {"default", "DIFFERENT_PACKAGE"},
                {"protected", "SAME_PACKAGE"},
                {"protected", "SAME_CLASS"},
                {"public", "DIFFERENT_PACKAGE"}
        }));
    }
}
