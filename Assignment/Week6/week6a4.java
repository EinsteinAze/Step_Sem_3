class GymMember {
    private String memberId;
    private int monthlyFee;
    private int sessionsAttended;

    public GymMember(String memberId, int monthlyFee) {
        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
    }

    void attendSession() {
        sessionsAttended++;
    }

    int getSessionsAttended() {
        return sessionsAttended;
    }

    String displayInfo() {
        return "Standard | Sessions: " + sessionsAttended;
    }
}

class PremiumMember extends GymMember {
    private String trainerName;

    public PremiumMember(String memberId, int monthlyFee, String trainerName) {
        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    String getTrainerName() {
        return trainerName;
    }

    @Override
    String displayInfo() {
        return "Premium | Trainer: " + trainerName + " | Sessions: " + getSessionsAttended();
    }
}

public class week6a4 {
    static String batchPrint(GymMember[] members) {
        StringBuilder sb = new StringBuilder();
        for (GymMember member : members) {
            sb.append(member.displayInfo());
            if (member instanceof PremiumMember) {
                PremiumMember pm = (PremiumMember) member;
                sb.append(" [Trainer via downcast: ").append(pm.getTrainerName()).append("]");
            }
            sb.append(" | ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(batchPrint(new GymMember[]{
                new GymMember("MEM6", 1000),
                new PremiumMember("MEM7", 2000, "Coach Riya")
        }));

        GymMember plain = new GymMember("MEM8", 1000);
        try {
            PremiumMember bad = (PremiumMember) plain;
        } catch (ClassCastException e) {
            System.out.println("ClassCastException at runtime");
        }
    }
}
