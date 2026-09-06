class Employee2 {
    String empName;
    double salary;
    static String companyName = "Bright Horizon Technologies";
    static int employeeCount = 0;

    Employee2(String empName, double salary) {
        this.empName = empName;
        this.salary = salary;
        employeeCount++;
    }

    static void printCompanyInfo() {
        System.out.println(companyName);
        System.out.println("Employees on record: " + employeeCount);
    }
}

public class week3a5 {
    public static void main(String[] args) {
        Employee2 employee1 = new Employee2("Ravi", 40000);
        Employee2 employee2 = new Employee2("Meera", 55000);
        Employee2 employee3 = new Employee2("Karthik", 62000);

        Employee2.printCompanyInfo();
    }
}
