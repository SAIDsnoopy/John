// ==========================================
// a) Abstract Class: DiscountPolicy
// ==========================================
abstract class DiscountPolicy {
    // Abstract method to compute total discount value
    public abstract double computeDiscount(int count, double itemCost);
}

// ==========================================
// b) Subclass: BulkDiscount
// ==========================================
class BulkDiscount extends DiscountPolicy {
    private int minimum;
    private double percent; // Expressed as a percentage (e.g., 10 for 10%)

    // Constructor
    public BulkDiscount(int minimum, double percent) {
        this.minimum = minimum;
        this.percent = percent;
    }

    @Override
    public double computeDiscount(int count, double itemCost) {
        // If quantity purchased is strictly MORE than minimum, apply discount
        if (count > minimum) {
            double totalCostWithoutDiscount = count * itemCost;
            return totalCostWithoutDiscount * (percent / 100.0);
        }
        return 0.0;
    }
}

// ==========================================
// c) Subclass: BuyNItemsGetOneFree
// ==========================================
class BuyNItemsGetOneFree extends DiscountPolicy {
    private int n;

    // Constructor
    public BuyNItemsGetOneFree(int n) {
        this.n = n;
    }

    @Override
    public double computeDiscount(int count, double itemCost) {
        // Every nth item is free. The number of free items is calculated using integer division.
        int freeItems = count / n;
        return freeItems * itemCost;
    }
}

// ==========================================
// d) Subclass: CombinedDiscount
// ==========================================
class CombinedDiscount extends DiscountPolicy {
    private DiscountPolicy policy1;
    private DiscountPolicy policy2;

    // Constructor taking two DiscountPolicy objects
    public CombinedDiscount(DiscountPolicy policy1, DiscountPolicy policy2) {
        this.policy1 = policy1;
        this.policy2 = policy2;
    }

    @Override
    public double computeDiscount(int count, double itemCost) {
        // Calculate discounts from both policies
        double discount1 = policy1.computeDiscount(count, itemCost);
        double discount2 = policy2.computeDiscount(count, itemCost);
        
        // Return the maximum value between the two discounts
        return Math.max(discount1, discount2);
    }
}

// ==========================================
// Application Demo Class
// ==========================================
public class DiscountDemo {
    public static void main(String[] args) {
        double itemCost = 10.00; // Cost matching the prompt's example
        int nValue = 3;          // Every 3rd item is free

        System.out.println("--- Discount Policy Evaluation System ---\n");

        // Instantiate the policy rules
        DiscountPolicy bulk = new BulkDiscount(5, 15); // Minimum 5 items, 15% discount
        DiscountPolicy buyNFree = new BuyNItemsGetOneFree(nValue);
        DiscountPolicy combined = new CombinedDiscount(bulk, buyNFree);

        // --- Verification of Part C (Table Check) ---
        System.out.println("Testing 'Buy 3 Get 1 Free' policy to verify prompt table data ($10 per item):");
        System.out.println("Count\tDiscount Value ($)");
        System.out.println("---------------------------");
        for (int count = 1; count <= 7; count++) {
            double discountVal = buyNFree.computeDiscount(count, itemCost);
            System.out.println(count + "\t$" + String.format("%.2f", discountVal));
        }
        System.out.println();

        // --- Verification of Part D (Combined Policy Overlap) ---
        System.out.println("Testing 'Combined Discount' Strategy (Bulk vs Buy-N-Free):");
        System.out.println("Config: Bulk (Min: 5, 15%) vs Buy 3 Get 1 Free. Item Cost: $10.00");
        System.out.println("\nItems\tBulk Disc.\tBuy-N Disc.\tBest Chosen (Combined)");
        System.out.println("----------------------------------------------------------------------");
        
        int[] testCounts = {4, 6, 12};
        for (int count : testCounts) {
            double bDisc = bulk.computeDiscount(count, itemCost);
            double nDisc = buyNFree.computeDiscount(count, itemCost);
            double cDisc = combined.computeDiscount(count, itemCost);
            
            System.out.printf("%d\t$%.2f\t\t$%.2f\t\t$%.2f%n", count, bDisc, nDisc, cDisc);
        }
    }
}