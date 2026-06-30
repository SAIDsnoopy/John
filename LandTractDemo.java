import java.util.Scanner;

// ==========================================
// LandTract Class Definition
// ==========================================
class LandTract {
    private double length;
    private double width;

    // Constructor to initialize length and width
    public LandTract(double length, double width) {
        this.length = length;
        this.width = width;
    }

    // Method to calculate and return the tract's area
    public double getArea() {
        return this.length * this.width;
    }

    // Equals method to compare two LandTract objects by area size
    @Override
    public boolean equals(Object obj) {
        // Check if the object is compared with itself
        if (this == obj) {
            return true;
        }
        
        // Check if the passed object is an instance of LandTract
        if (obj instanceof LandTract) {
            LandTract otherTract = (LandTract) obj;
            // Compare the areas of both tracts
            return this.getArea() == otherTract.getArea();
        }
        
        return false;
    }

    // Helper method to represent the object dimensions as a string
    @Override
    public String toString() {
        return "Dimensions: " + length + " x " + width;
    }
}

// ==========================================
// Main Application / Demo Program
// ==========================================
public class LandTractDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("--- LandTract Dimension Comparator ---\n");

        // --- Tract 1 Inputs ---
        System.out.println("Enter dimensions for the First Tract of land:");
        System.out.print("  Length: ");
        double length1 = scanner.nextDouble();
        System.out.print("  Width:  ");
        double width1 = scanner.nextDouble();
        LandTract tract1 = new LandTract(length1, width1);

        // --- Tract 2 Inputs ---
        System.out.println("\nEnter dimensions for the Second Tract of land:");
        System.out.print("  Length: ");
        double length2 = scanner.nextDouble();
        System.out.print("  Width:  ");
        double width2 = scanner.nextDouble();
        LandTract tract2 = new LandTract(length2, width2);

        // --- Display Area Results ---
        System.out.println("\n--- Summary Results ---");
        System.out.printf("Tract 1 -> %s | Calculated Area: %.2f%n", tract1.toString(), tract1.getArea());
        System.out.printf("Tract 2 -> %s | Calculated Area: %.2f%n", tract2.toString(), tract2.getArea());
        System.out.println("------------------------");

        // --- Size Evaluation using the equals method ---
        if (tract1.equals(tract2)) {
            System.out.println("Result: The two tracts of land are EQUAL in size.");
        } else {
            System.out.println("Result: The two tracts of land are NOT equal in size.");
        }

        scanner.close();
    }
}