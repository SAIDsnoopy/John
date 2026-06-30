// ==========================================
// 1. Base Class: Person
// ==========================================
class Person {
    private String name;
    private String address;
    private String telephoneNumber;

    // Default Constructor
    public Person() {
        this.name = "";
        this.address = "";
        this.telephoneNumber = "";
    }

    // Parameterized Constructor
    public Person(String name, String address, String telephoneNumber) {
        this.name = name;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
    }

    // Accessors (Getters)
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getTelephoneNumber() { return telephoneNumber; }

    // Mutators (Setters)
    public void setName(String name) { this.name = name; }
    public void setAddress(String address) { this.address = address; }
    public void setTelephoneNumber(String telephoneNumber) { this.telephoneNumber = telephoneNumber; }
}

// ==========================================
// 2. Subclass: Customer (Extends Person)
// ==========================================
class Customer extends Person {
    private String customerNumber;
    private boolean mailable; // true = wishes to be on mailing list

    // Default Constructor
    public Customer() {
        super();
        this.customerNumber = "";
        this.mailable = false;
    }

    // Parameterized Constructor
    public Customer(String name, String address, String telephoneNumber, String customerNumber, boolean mailable) {
        super(name, address, telephoneNumber); // Call Person constructor
        this.customerNumber = customerNumber;
        this.mailable = mailable;
    }

    // Accessors (Getters)
    public String getCustomerNumber() { return customerNumber; }
    public boolean isMailable() { return mailable; }

    // Mutators (Setters)
    public void setCustomerNumber(String customerNumber) { this.customerNumber = customerNumber; }
    public void setMailable(boolean mailable) { this.mailable = mailable; }
}

// ==========================================
// 3. Subclass: PreferredCustomer (Extends Customer)
// ==========================================
class PreferredCustomer extends Customer {
    private double purchasesAmount;
    private double discountLevel; // Represented as a decimal percentage (e.g., 0.05 for 5%)

    // Default Constructor
    public PreferredCustomer() {
        super();
        this.purchasesAmount = 0.0;
        this.discountLevel = 0.0;
    }

    // Parameterized Constructor
    public PreferredCustomer(String name, String address, String telephoneNumber, 
                             String customerNumber, boolean mailable, double purchasesAmount) {
        super(name, address, telephoneNumber, customerNumber, mailable);
        this.purchasesAmount = purchasesAmount;
        determineDiscountLevel(); // Set discount dynamically based on purchase tier
    }

    // Accessors (Getters)
    public double getPurchasesAmount() { return purchasesAmount; }
    public double getDiscountLevel() { return discountLevel; }

    // Mutators (Setters)
    public void setPurchasesAmount(double purchasesAmount) {
        this.purchasesAmount = purchasesAmount;
        determineDiscountLevel(); // Recalculate discount if the spending level updates
    }

    // Helper method to determine the discount percentage based on tier criteria
    private void determineDiscountLevel() {
        if (this.purchasesAmount >= 2000.0) {
            this.discountLevel = 0.10; // 10%
        } else if (this.purchasesAmount >= 1500.0) {
            this.discountLevel = 0.07; // 7%
        } else if (this.purchasesAmount >= 1000.0) {
            this.discountLevel = 0.06; // 6%
        } else if (this.purchasesAmount >= 500.0) {
            this.discountLevel = 0.05; // 5%
        } else {
            this.discountLevel = 0.00; // 0%
        }
    }
}

// ==========================================
// 4. Main Application: StoreApplication
// ==========================================
public class StoreApplication {
    public static void main(String[] args) {
        System.out.println("=== Retail Store Preferred Customer Tier Demo ===\n");

        // 1. Create a customer who has spent £450 (No discount tier reached yet)
        PreferredCustomer customer1 = new PreferredCustomer(
            "Alice Smith", "123 High St, London", "020-7946-0192", "CUST-001", true, 450.00
        );
        displayCustomerDetails(customer1);

        // 2. Create a customer who has spent £1,250 (Qualifies for 6% discount)
        PreferredCustomer customer2 = new PreferredCustomer(
            "Bob Jones", "45 Station Rd, Manchester", "0161-496-0234", "CUST-002", false, 1250.00
        );
        displayCustomerDetails(customer2);

        // 3. Update Alice's purchase total to demonstrate dynamic tier recalculation
        System.out.println("--> Update: Alice Smith spends an extra £1,600...");
        customer1.setPurchasesAmount(customer1.getPurchasesAmount() + 1600.00); // Total now £2,050
        displayCustomerDetails(customer1);
    }

    // Helper method to clean up output presentation
    private static void displayCustomerDetails(PreferredCustomer cust) {
        System.out.println("Customer Profile:");
        System.out.println("  Name             : " + cust.getName());
        System.out.println("  Address          : " + cust.getAddress());
        System.out.println("  Phone            : " + cust.getTelephoneNumber());
        System.out.println("  ID Number        : " + cust.getCustomerNumber());
        System.out.println("  Mailing List     : " + (cust.isMailable() ? "Yes" : "No"));
        System.out.println("  Total Spent      : £" + String.format("%.2f", cust.getPurchasesAmount()));
        System.out.println("  Discount Status  : " + (cust.getDiscountLevel() * 100) + "% off future purchases");
        System.out.println("-----------------------------------------------\n");
    }
}