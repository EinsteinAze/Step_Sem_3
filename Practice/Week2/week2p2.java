public class week2p2 {

    void parseStudentRecord(String csvLine) {
        String[] fields = csvLine.split(",");
        if (fields.length != 3) {
            System.out.println("Invalid Record");
            return;
        }
        System.out.println("Name: " + fields[0] + " | Roll No: " + fields[1] + " | Dept: " + fields[2]);
    }

    public static void main(String[] args) {
        week2p2 parser = new week2p2();
        parser.parseStudentRecord("Ananya Verma,RA2211003010123,CSE");
        parser.parseStudentRecord("Ananya Verma,CSE");
    }
}
