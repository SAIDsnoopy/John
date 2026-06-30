// Base class for a standard cabin rental
class CabinRental {
    private int cabinNumber;
    private double rentalRate;

    // Constructor that sets the rate based on the cabin number
    public CabinRental(int cabinNumber) {
        this.cabinNumber = cabinNumber;
        
        // Cabins 1, 2, and 3 are $950; all others are $1,100
        if (cabinNumber >= 1 && cabinNumber <= 3) {
            this.rentalRate = 950.00;
        } else {
            this.rentalRate = 1100.00;
        }
    }

    // Getter for cabin number
    public int getCabinNumber() {
        return cabinNumber;
    }

    // Getter for weekly rental rate
    public double getRentalRate() {
        return rentalRate;
    }

    // Helper method to update rate (used by the subclass)
    protected void setRentalRate(double rentalRate) {
        this.rentalRate = rentalRate;
    }
}

// Extended class for rentals during holiday weeks
class HolidayCabinRental extends CabinRental {

    // Constructor that calls the base constructor and adds the $150 surcharge
    public HolidayCabinRental(int cabinNumber) {
        super(cabinNumber);
        // Fetch the regular rate calculated by the parent and add $150
        double holidayRate = getRentalRate() + 150.00;
        setRentalRate(holidayRate);
    }
}

// Main application to demonstrate and test the functionality
public class DemoCabinRental {
    public static void main(String[] args) {
        System.out.println("--- Leeland Lakeside Resort Rental Demo ---\n");

        // Testing Standard Rates (Cabins 1-3 vs Others)
        CabinRental standardCabinLow = new CabinRental(2);
        CabinRental standardCabinHigh = new CabinRental(5);

        System.out.println("Standard Week Bookings:");
        System.out.println("Cabin #" + standardCabinLow.getCabinNumber() + " Rate: $" + standardCabinLow.getRentalRate() + "/week");
        System.out.println("Cabin #" + standardCabinHigh.getCabinNumber() + " Rate: $" + standardCabinHigh.getRentalRate() + "/week");
        System.out.println();

        // Testing Holiday Rates (Cabins 1-3 vs Others with $150 surcharge)
        HolidayCabinRental holidayCabinLow = new HolidayCabinRental(2);
        HolidayCabinRental holidayCabinHigh = new HolidayCabinRental(5);

        System.out.println("Holiday Week Bookings (Includes $150 Surcharge):");
        System.out.println("Cabin #" + holidayCabinLow.getCabinNumber() + " Holiday Rate: $" + holidayCabinLow.getRentalRate() + "/week");
        System.out.println("Cabin #" + holidayCabinHigh.getCabinNumber() + " Holiday Rate: $" + holidayCabinHigh.getRentalRate() + "/week");
    }
}