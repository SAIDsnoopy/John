class Calculator {
    // Static Variable
    public static double PI = 3.14159;
    // Instance Variable
    public String calculatorModel;

    public Calculator(String model) {
        this.calculatorModel = model;
    }

    // 1. Static Method: Performs a generic utility calculation. No object state needed.
    public static double calculateCircleArea(double radius) {
        return PI * radius * radius; 
        // return calculatorModel; // ERROR: Static methods cannot access instance variables!
    }

    // 2. Non-Static Method: Relies on individual object state.
    public void printDeviceSummary() {
        System.out.println("Model: " + calculatorModel + " | Constant PI: " + PI);
    }
}

public class Main {
    public static void main(String[] args) {
        // Calling static method DIRECTLY using the class name (No object created yet)
        double area = Calculator.calculateCircleArea(5.0);
        
        // Calling non-static method requires an object allocation step
        Calculator myCalc = new Calculator("Texas Instruments TI-84");
        myCalc.printDeviceSummary(); 
    }
}