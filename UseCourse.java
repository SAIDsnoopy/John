import java.util.Scanner;

// ==========================================
// Base Class: CollegeCourse
// ==========================================
class CollegeCourse {
    private String department;
    private int courseNumber;
    private int credits;
    protected double fee; // protected so the subclass can access/modify it

    // Constructor requiring department, course number, and credits
    public CollegeCourse(String department, int courseNumber, int credits) {
        this.department = department.toUpperCase(); // Ensure standard casing
        this.courseNumber = courseNumber;
        this.credits = credits;
        // Base fee is calculated at $120 per credit hour
        this.fee = credits * 120.00;
    }

    // Method to display standard course data
    public void display() {
        System.out.println("\n--- Course Information ---");
        System.out.println("Department:    " + department);
        System.out.println("Course Number: " + courseNumber);
        System.out.println("Credits:       " + credits);
        System.out.println("Total Fee:     $" + fee);
    }

    // Getter for department to assist the application logic
    public String getDepartment() {
        return department;
    }
}

// ==========================================
// Subclass: LabCourse
// ==========================================
class LabCourse extends CollegeCourse {

    // Constructor adds $50 to the calculated base fee
    public LabCourse(String department, int courseNumber, int credits) {
        super(department, courseNumber, credits);
        this.fee += 50.00; // Adding the lab fee surcharge
    }

    // Overridden display method to indicate it is a lab course
    @Override
    public void display() {
        super.display(); // Prints the standard information
        System.out.println("Note:          *This is a designated Lab Course (includes a $50.00 lab fee)*");
    }
}

// ==========================================
// Main Application: UseCourse
// ==========================================
public class UseCourse {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Prompting the user for course details
        System.out.print("Enter the department code (e.g., ENG, BIO, CIS): ");
        String dept = input.nextLine().trim().toUpperCase();

        System.out.print("Enter the course number (e.g., 101): ");
        int num = input.nextInt();

        System.out.print("Enter the number of credits: ");
        int credits = input.nextInt();

        // Check if the department qualifies for a Lab Course
        // Valid lab departments: BIO, CHM, CIS, PHY
        if (dept.equals("BIO") || dept.equals("CHM") || dept.equals("CIS") || dept.equals("PHY")) {
            // Instantiate a LabCourse
            LabCourse standardLab = new LabCourse(dept, num, credits);
            standardLab.display();
        } else {
            // Instantiate a standard CollegeCourse
            CollegeCourse standardCourse = new CollegeCourse(dept, num, credits);
            standardCourse.display();
        }

        input.close();
    }
}