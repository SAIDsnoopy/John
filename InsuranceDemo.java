import java.util.Scanner;

// ==========================================
// InsurancePolicy Class
// ==========================================
class InsurancePolicy {
    private String customerId;
    private String policyType; // "HEALTH" or "AUTO"

    // Default Constructor
    public InsurancePolicy() {
        this.customerId = "Unknown";
        this.policyType = "Unknown";
    }

    // Parameterized Constructor
    public InsurancePolicy(String customerId, String policyType) {
        this.customerId = customerId;
        this.policyType = policyType.toUpperCase().trim();
    }

    // Accessor and Mutator for Customer ID
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    // Accessor and Mutator for Policy Type
    public String getPolicyType() {
        return policyType;
    }

    public void setPolicyType(String policyType) {
        this.policyType = policyType.toUpperCase().trim();
    }

    // ==========================================
    // OVERLOADED METHODS FOR CALCULATING PREMIUM
    // ==========================================

    // Overloaded Method 1: Calculates Health Premium based on smoking status
    public double calculatePremium(boolean isSmoker) {
        if (isSmoker) {
            return 250.00;
        } else {
            return 190.00;
        }
    }

    // Overloaded Method 2: Calculates Auto Premium based on traffic tickets
    public double calculatePremium(int trafficTickets) {
        if (trafficTickets >= 3) {
            return 175.00;
        } else if (trafficTickets >= 1) {
            return 140.00;
        } else {
            return 95.00;
        }
    }
}

// ==========================================
// Demo Class Application
// ==========================================
public class InsuranceDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Insurance Policy Premium Portal ---\n");

        // Prompt for Customer ID
        System.out.print("Enter Customer ID: ");
        String id = scanner.nextLine();

        // Prompt for Policy Type
        System.out.print("Enter Policy Type (Health / Auto): ");
        String type = scanner.nextLine();

        // Create the instance of InsurancePolicy
        InsurancePolicy policy = new InsurancePolicy(id, type);

        double calculatedPremium = 0.0;
        boolean validPolicy = true;

        // Determine which calculations to run based on user selection
        if (policy.getPolicyType().equals("HEALTH")) {
            System.out.print("Are you a smoker? (yes / no): ");
            String smokerInput = scanner.next().trim().toLowerCase();
            boolean isSmoker = smokerInput.equals("yes") || smokerInput.equals("y");
            
            // Calls the boolean variant of the overloaded method
            calculatedPremium = policy.calculatePremium(isSmoker);

        } else if (policy.getPolicyType().equals("AUTO")) {
            System.out.print("Enter number of traffic tickets received in the last 3 years: ");
            int tickets = scanner.nextInt();
            
            // Calls the integer variant of the overloaded method
            calculatedPremium = policy.calculatePremium(tickets);

        } else {
            System.out.println("\nError: Invalid policy type selected.");
            validPolicy = false;
        }

        // Print statement displaying the results if valid inputs were caught
        if (validPolicy) {
            System.out.println("\n=================================");
            System.out.println("        PREMIUM INVOICE          ");
            System.out.println("=================================");
            System.out.println("Customer ID : " + policy.getCustomerId());
            System.out.println("Policy Type : " + policy.getPolicyType());
            System.out.println("Total Due   : $" + calculatedPremium);
            System.out.println("=================================");
        }

        scanner.close();
    }
}