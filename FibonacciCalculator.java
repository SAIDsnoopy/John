import java.util.Scanner;

public class FibonacciCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Fibonacci Sequence Calculator ---");
        System.out.print("Enter the position (n) to find the nth Fibonacci number (starts at 0): ");
        
        int n = scanner.nextInt();

        // Handle edge case for negative input
        if (n < 0) {
            System.out.println("Invalid input! Please enter a non-negative integer (0 or greater).");
        } else {
            long result = getFibonacci(n);
            System.out.println("The Fibonacci number at index " + n + " is: " + result);
        }

        scanner.close();
    }

    /**
     * Iterative method to compute the nth Fibonacci number.
     * Uses a loop for optimal speed performance O(n) and minimal memory usage O(1).
     */
    public static long getFibonacci(int n) {
        // Base cases
        if (n == 0) return 0;
        if (n == 1) return 1;

        long previous = 0; // Represents F(n-2)
        long current = 1;  // Represents F(n-1)
        long nthFibonacci = 0;

        // Iterate forward to calculate sequential steps up to n
        for (int i = 2; i <= n; i++) {
            nthFibonacci = previous + current;
            previous = current; // Move previous forward
            current = nthFibonacci; // Move current forward
        }

        return nthFibonacci;
    }
}