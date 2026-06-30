import java.util.ArrayList;

// ==========================================
// a) Property Class
// ==========================================
class Property {
    private String streetAddress;
    private double rentPerMonth;
    private int numBedrooms;
    private boolean hasOffRoadParking;

    // Constructor initializing all fields
    public Property(String streetAddress, double rentPerMonth, int numBedrooms, boolean hasOffRoadParking) {
        this.streetAddress = streetAddress;
        this.rentPerMonth = rentPerMonth;
        this.numBedrooms = numBedrooms;
        this.hasOffRoadParking = hasOffRoadParking;
    }

    // Accessors (Getters)
    public String getStreetAddress() { return streetAddress; }
    public double getRentPerMonth() { return rentPerMonth; }
    public int getNumBedrooms() { return numBedrooms; }
    public boolean isHasOffRoadParking() { return hasOffRoadParking; }

    // Mutators (Setters)
    public void setStreetAddress(String streetAddress) { this.streetAddress = streetAddress; }
    public void setRentPerMonth(double rentPerMonth) { this.rentPerMonth = rentPerMonth; }
    public void setNumBedrooms(int numBedrooms) { this.numBedrooms = numBedrooms; }
    public void setHasOffRoadParking(boolean hasOffRoadParking) { this.hasOffRoadParking = hasOffRoadParking; }

    // Helper method to display a single property summary
    @Override
    public String toString() {
        return streetAddress + " (" + numBedrooms + " Bed, Parking: " + (hasOffRoadParking ? "Yes" : "No") + ") - $" + rentPerMonth + "/mo";
    }
}

// ==========================================
// b) Rentals Class (Manages group of properties)
// ==========================================
class Rentals {
    // Aggregation: Storing Property objects inside a dynamic list
    private ArrayList<Property> propertyList;

    // Constructor initializing the list container
    public Rentals() {
        this.propertyList = new ArrayList<>();
    }

    // Helper method to add properties to our system tracking
    public void addProperty(Property property) {
        if (property != null) {
            propertyList.add(property);
        }
    }

    // Method to remove a property given as an argument
    public void removeProperty(Property property) {
        if (propertyList.remove(property)) {
            System.out.println("-> Success: Removed \"" + property.getStreetAddress() + "\" from the available listings.");
        } else {
            System.out.println("-> Notice: \"" + property.getStreetAddress() + "\" was not found in active listings.");
        }
    }

    // Method comparing two properties and returning the one with the lowest monthly rent
    public Property getLowestRent(Property p1, Property p2) {
        if (p1 == null) return p2;
        if (p2 == null) return p1;
        
        if (p1.getRentPerMonth() <= p2.getRentPerMonth()) {
            return p1;
        } else {
            return p2;
        }
    }

    // Helper method to show current state of the rental list
    public void displayAllAvailable() {
        System.out.println("\n--- Current Available Rental Listings (" + propertyList.size() + ") ---");
        if (propertyList.isEmpty()) {
            System.out.println("[ No properties currently listed ]");
        } else {
            for (Property p : propertyList) {
                System.out.println(" • " + p.toString());
            }
        }
        System.out.println("----------------------------------------------");
    }
}

// ==========================================
// c) Main Application: PropertyDemo
// ==========================================
public class PropertyDemo {
    public static void main(String[] args) {
        System.out.println("=== Property Rental Management System ===\n");

        // 1. Setup the Rentals management cluster
        Rentals agency = new Rentals();

        // 2. Instantiate individual property objects
        Property prop1 = new Property("102 Baker Street", 1250.00, 2, false);
        Property prop2 = new Property("404 Maple Avenue", 950.00, 1, true);
        Property prop3 = new Property("789 Ocean Boulevard", 2100.00, 3, true);

        // 3. Demonstrate adding properties
        System.out.println("Adding new properties to listings...");
        agency.addProperty(prop1);
        agency.addProperty(prop2);
        agency.addProperty(prop3);
        
        agency.displayAllAvailable();

        // 4. Demonstrate comparing two properties for lowest rent
        System.out.println("\nComparing rents between '" + prop1.getStreetAddress() + "' and '" + prop2.getStreetAddress() + "'...");
        Property cheapest = agency.getLowestRent(prop1, prop2);
        System.out.println("Result -> Cheapest Option: " + cheapest.toString());

        System.out.println("\nComparing rents between '" + prop1.getStreetAddress() + "' and '" + prop3.getStreetAddress() + "'...");
        cheapest = agency.getLowestRent(prop1, prop3);
        System.out.println("Result -> Cheapest Option: " + cheapest.toString());
        System.out.println();

        // 5. Demonstrate removing a property from the list
        System.out.println("Processing a signed lease agreement...");
        agency.removeProperty(prop2); // Removes Maple Avenue

        // 6. Final display verifying state modifications
        agency.displayAllAvailable();
    }
}