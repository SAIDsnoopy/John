import java.util.ArrayList;
import java.util.Scanner;

// ==========================================
// 1. Base Class: Person
// ==========================================
class Person {
    private String firstName;
    private String lastName;
    private String streetAddress;
    private String zipCode;
    private String phoneNumber;

    // Constructor to initialize all core details
    public Person(String firstName, String lastName, String streetAddress, String zipCode, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetAddress = streetAddress;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
    }

    // Display method showing all personal details
    public void display() {
        System.out.print("Name: " + firstName + " " + lastName +
                         " | Address: " + streetAddress + ", Zip: " + zipCode +
                         " | Phone: " + phoneNumber);
    }
}

// ==========================================
// 2. Subclass: CollegeEmployee (Inherits Person)
// ==========================================
class CollegeEmployee extends Person {
    private String ssn;
    private double annualSalary;
    private String departmentName;

    // Constructor passing core details up, initializing local fields
    public CollegeEmployee(String firstName, String lastName, String streetAddress, String zipCode, String phoneNumber,
                           String ssn, double annualSalary, String departmentName) {
        super(firstName, lastName, streetAddress, zipCode, phoneNumber);
        this.ssn = ssn;
        this.annualSalary = annualSalary;
        this.departmentName = departmentName;
    }

    // Overriding display method to incorporate professional employee items
    @Override
    public void display() {
        super.display();
        System.out.println("\n  [Employee Profile] SSN: " + ssn + 
                           " | Dept: " + departmentName + 
                           " | Annual Salary: $" + String.format("%.2f", annualSalary));
    }
}

// ==========================================
// 3. Subclass: Faculty (Inherits Person)
// ==========================================
class Faculty extends Person {
    private boolean isTemporary;
    private double monthlySalary;

    public Faculty(String firstName, String lastName, String streetAddress, String zipCode, String phoneNumber,
                   boolean isTemporary, double monthlySalary) {
        super(firstName, lastName, streetAddress, zipCode, phoneNumber);
        this.isTemporary = isTemporary;
        this.monthlySalary = monthlySalary;
    }

    // Overriding display method to print specialized academic fields
    @Override
    public void display() {
        super.display();
        System.out.println("\n  [Faculty Profile] Type: " + (isTemporary ? "Temporary" : "Tenured/Permanent") + 
                           " | Monthly Salary: $" + String.format("%.2f", monthlySalary));
    }
}

// ==========================================
// 4. Subclass: Student (Inherits Person)
// ==========================================
class Student extends Person {
    private String major;
    private double gpa;

    public Student(String firstName, String lastName, String streetAddress, String zipCode, String phoneNumber,
                   String major, double gpa) {
        super(firstName, lastName, streetAddress, zipCode, phoneNumber);
        this.major = major;
        this.gpa = gpa;
    }

    // Overriding display method to append educational trackers
    @Override
    public void display() {
        super.display();
        System.out.println("\n  [Student Profile] Major: " + major + " | GPA: " + gpa);
    }
}

// ==========================================
// 5. Main Application System: CollegeList
// ==========================================
public class CollegeList {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Lists acting as categorized database tables
        ArrayList<CollegeEmployee> employeesList = new ArrayList<>();
        ArrayList<Faculty> facultyList = new ArrayList<>();
        ArrayList<Student> studentsList = new ArrayList<>();

        char choice = ' ';

        System.out.println("--- Welcome to the College Personnel Information Registry ---\n");

        // Primary Entry Execution Loop
        while (true) {
            System.out.print("Select data entry type -> [C]ollege Employee, [F]aculty, [S]tudent, or [Q]uit & Generate Report: ");
            String input = scanner.nextLine().trim().toUpperCase();
            
            if (input.isEmpty()) continue;
            choice = input.charAt(0);

            if (choice == 'Q') {
                break; // Exit collection sequence loop
            }

            if (choice != 'C' && choice != 'F' && choice != 'S') {
                System.out.println("Invalid selection. Please try again.\n");
                continue;
            }

            // Gather standard information required by the Person base class
            System.out.println("\n--- Enter Personal Data Fields ---");
            System.out.print("First Name: ");     String fName = scanner.nextLine();
            System.out.print("Last Name: ");      String lName = scanner.nextLine();
            System.out.print("Street Address: "); String address = scanner.nextLine();
            System.out.print("Zip Code: ");       String zip = scanner.nextLine();
            System.out.print("Phone Number: ");   String phone = scanner.nextLine();

            // Branch down to gather specialized properties depending on type context
            switch (choice) {
                case 'C':
                    System.out.println("--- Enter Employee Specialized Details ---");
                    System.out.print("Social Security Number (SSN): "); String ssn = scanner.nextLine();
                    System.out.print("Annual Salary: "); double annSalary = scanner.nextDouble();
                    scanner.nextLine(); // Clear scanner buffer
                    System.out.print("Department Name: "); String dept = scanner.nextLine();
                    
                    employeesList.add(new CollegeEmployee(fName, lName, address, zip, phone, ssn, annSalary, dept));
                    break;

                case 'F':
                    System.out.println("--- Enter Faculty Specialized Details ---");
                    System.out.print("Is this position Temporary? (true/false): "); boolean temp = scanner.nextBoolean();
                    System.out.print("Monthly Salary: "); double monthSalary = scanner.nextDouble();
                    scanner.nextLine(); // Clear scanner buffer
                    
                    facultyList.add(new Faculty(fName, lName, address, zip, phone, temp, monthSalary));
                    break;

                case 'S':
                    System.out.println("--- Enter Student Academic Details ---");
                    System.out.print("Major Field of Study: "); String major = scanner.nextLine();
                    System.out.print("Grade Point Average (GPA): "); double gpa = scanner.nextDouble();
                    scanner.nextLine(); // Clear scanner buffer
                    
                    studentsList.add(new Student(fName, lName, address, zip, phone, major, gpa));
                    break;
            }
            System.out.println(">> Entry Recorded Successfully.\n");
        }

        // ==========================================
        // COMPILING & RENDERING TERMINAL DATA LOGS
        // ==========================================
        System.out.println("\n========================================================");
        System.out.println("               COLLEGE REGISTRY SUMMARY REPORTS          ");
        System.out.println("========================================================");

        // Report 1: College Employees
        System.out.println("\n--- COLLEGE EMPLOYEES REPORT ---");
        if (employeesList.isEmpty()) System.out.println("[No records available]");
        else {
            for (CollegeEmployee ce : employeesList) ce.display();
        }

        // Report 2: Faculty Members
        System.out.println("\n--- FACULTY REPORT ---");
        if (facultyList.isEmpty()) System.out.println("[No records available]");
        else {
            for (Faculty f : facultyList) f.display();
        }

        // Report 3: Students
        System.out.println("\n--- STUDENTS REPORT ---");
        if (studentsList.isEmpty()) System.out.println("[No records available]");
        else {
            for (Student s : studentsList) s.display();
        }
        
        System.out.println("\n==================== End of Report =====================");
        scanner.close();
    }
}