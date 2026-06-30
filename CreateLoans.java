import java.util.Scanner;

// 1. Interface
interface LoanConstants {
    int SHORT_TERM = 1;
    int MEDIUM_TERM = 3;
    int LONG_TERM = 5;
    
    double MAX_LOAN_AMOUNT = 100000.00;
    String COMPANY_NAME = "Sanchez Construction Loan Co.";
}

// 2. Classe abstraite Loan
abstract class Loan implements LoanConstants {
    protected String loanNumber;
    protected String customerLastName;
    protected double loanAmount;
    protected double interestRate;
    protected int term;

    public Loan(String loanNumber, String customerLastName, double loanAmount, int term) {
        this.loanNumber = loanNumber;
        this.customerLastName = customerLastName;

        // Limiter le montant à 100 000 $
        if (loanAmount > MAX_LOAN_AMOUNT) {
            System.out.println("Le montant demandé dépasse le maximum. Plafonnement du prêt à $" + MAX_LOAN_AMOUNT);
            this.loanAmount = MAX_LOAN_AMOUNT;
        } else {
            this.loanAmount = loanAmount;
        }

        // Forcer les durées invalides à 1 an (court terme)
        if (term == SHORT_TERM || term == MEDIUM_TERM || term == LONG_TERM) {
            this.term = term;
        } else {
            System.out.println("Durée invalide. La durée est forcée à " + SHORT_TERM + " an(s).");
            this.term = SHORT_TERM;
        }
    }

    @Override
    public String toString() {
        double loanFee = loanAmount * interestRate * term;
        double totalOwed = loanAmount + loanFee;

        return String.format("%s\nNuméro de prêt: %s\nNom de famille: %s\nMontant du prêt: $%.2f\nTaux d'intérêt: %.2f%%\nDurée: %d an(s)\nMontant total dû à l'échéance: $%.2f",
                COMPANY_NAME, loanNumber, customerLastName, loanAmount, (interestRate * 100), term, totalOwed);
    }
}

// 3. Classe BusinessLoan
class BusinessLoan extends Loan {
    public BusinessLoan(String loanNumber, String customerLastName, double loanAmount, int term, double primeRate) {
        super(loanNumber, customerLastName, loanAmount, term);
        // Le taux d'intérêt est de 1 % de plus que le taux préférentiel
        this.interestRate = primeRate + 0.01;
    }
}

// 4. Classe PersonalLoan
class PersonalLoan extends Loan {
    public PersonalLoan(String loanNumber, String customerLastName, double loanAmount, int term, double primeRate) {
        super(loanNumber, customerLastName, loanAmount, term);
        // Le taux d'intérêt est de 2 % de plus que le taux préférentiel
        this.interestRate = primeRate + 0.02;
    }
}

// 5. Classe principale (Publique)
public class CreateLoans {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Loan[] loans = new Loan[5];

        System.out.print("Entrez le taux d'intérêt préférentiel actuel (en décimal, ex. 0.05 pour 5%) : ");
        double primeRate = scanner.nextDouble();

        for (int i = 0; i < loans.length; i++) {
            System.out.println("\n--- Saisie des détails pour le prêt " + (i + 1) + " ---");

            System.out.print("Entrez le type de prêt (1 pour Affaires, 2 pour Personnel) : ");
            int type = scanner.nextInt();
            scanner.nextLine(); // Consommer le caractère de retour à la ligne

            System.out.print("Entrez le numéro du prêt : ");
            String loanNumber = scanner.nextLine();

            System.out.print("Entrez le nom de famille du client : ");
            String lastName = scanner.nextLine();

            System.out.print("Entrez le montant du prêt : ");
            double amount = scanner.nextDouble();

            System.out.print("Entrez la durée (1, 3 ou 5 ans) : ");
            int term = scanner.nextInt();

            // Création du bon objet selon le choix de l'utilisateur
            if (type == 1) {
                loans[i] = new BusinessLoan(loanNumber, lastName, amount, term, primeRate);
            } else if (type == 2) {
                loans[i] = new PersonalLoan(loanNumber, lastName, amount, term, primeRate);
            } else {
                System.out.println("Type de prêt invalide. Définition par défaut sur Prêt Personnel.");
                loans[i] = new PersonalLoan(loanNumber, lastName, amount, term, primeRate);
            }
        }

        // Afficher tous les prêts enregistrés
        System.out.println("\n=================================");
        System.out.println("       TOUS LES PRÊTS CRÉÉS      ");
        System.out.println("=================================\n");

        for (Loan loan : loans) {
            System.out.println(loan.toString());
            System.out.println("---------------------------------");
        }

        scanner.close();
    }
}