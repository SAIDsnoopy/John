import java.util.Scanner;

// ==========================================
// 1. RoomDimension Class
// ==========================================
class RoomDimension {
    private double length;
    private double width;

    // Constructor
    public RoomDimension(double len, double w) {
        this.length = len;
        this.width = w;
    }

    // Method to calculate and return the floor area
    public double getArea() {
        return this.length * this.width;
    }

    // toString method as specified in the UML diagram
    @Override
    public String toString() {
        return "Length: " + length + " ft, Width: " + width + " ft (Area: " + getArea() + " sq ft)";
    }
}

// ==========================================
// 2. RoomCarpet Class (Object Composition)
// ==========================================
class RoomCarpet {
    private RoomDimension size; // Composition: RoomCarpet HAS-A RoomDimension
    private double carpetCost;  // Cost per square foot

    // Constructor
    public RoomCarpet(RoomDimension dim, double cost) {
        // To avoid sharing mutable references, we can create a copy of the object
        this.size = new RoomDimension(dim.getArea() / 1.0, 1.0); 
        // Or assign it directly as a standard object aggregation:
        this.size = dim;
        this.carpetCost = cost;
    }

    // Method to calculate total cost: Area * cost per square foot
    public double getTotalCost() {
        return size.getArea() * carpetCost;
    }

    // toString method as specified in the UML diagram
    @Override
    public String toString() {
        return size.toString() + "\nCarpet Cost: $" + carpetCost + " per sq ft\nTotal Installation Cost: $" + String.format("%.2f", getTotalCost());
    }
}

// ==========================================
// 3. Main Driver Application
// ==========================================
public class CarpetCalculator {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        System.out.println("--- Westfield Carpet Company Pricing Portal ---\n");

        // Step 1: Prompt user for room dimensions
        System.out.print("Enter the length of the room (in feet): ");
        double length = keyboard.nextDouble();

        System.out.print("Enter the width of the room (in feet): ");
        double width = keyboard.nextDouble();

        // Step 2: Create a RoomDimension object
        RoomDimension dimensions = new RoomDimension(length, width);

        // Step 3: Prompt user for carpet price per square foot
        System.out.print("Enter the carpet price per square foot: $");
        double pricePerSqFt = keyboard.nextDouble();

        // Step 4: Create a RoomCarpet object passing the dimension object and price
        RoomCarpet carpetOrder = new RoomCarpet(dimensions, pricePerSqFt);

        // Step 5: Display results using the toString methods
        System.out.println("\n=================================");
        System.out.println("          QUOTATION SUMMARY      ");
        System.out.println("=================================");
        System.out.println(carpetOrder.toString());
        System.out.println("=================================");

        keyboard.close();
    }
}