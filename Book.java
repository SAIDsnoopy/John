// ==========================================
// PARTIE A : Structure des Classes
// ==========================================

// Classe abstraite de base
abstract class Book {
    private String title;
    protected double price;

    // Constructeur exigeant le titre
    public Book(String title) {
        this.title = title;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    // Méthode abstraite à implémenter par les sous-classes
    public abstract void setPrice();
}

// Sous-classe Fiction
class Fiction extends Book {
    public Fiction(String title) {
        super(title);
        setPrice(); // Appel automatique lors de la construction
    }

    @Override
    public void setPrice() {
        this.price = 24.99;
    }
}

// Sous-classe NonFiction
class NonFiction extends Book {
    public NonFiction(String title) {
        super(title);
        setPrice(); // Appel automatique lors de la construction
    }

    @Override
    public void setPrice() {
        this.price = 37.99;
    }
}

// ==========================================
// PARTIE B & DEMONSTRATION : Application Principale
// ==========================================
public class UseBookAndArray {
    public static void main(String[] args) {
        
        // 1. Démonstration de la Question 1.a (Création individuelle et affichage)
        System.out.println("=== QUESTION 1.a : Démonstration Individuelle ===");
        
        Book unLivreFiction = new Fiction("Le Meilleur des Mondes");
        Book unLivreNonFiction = new NonFiction("Sapiens");

        System.out.println("Livre de Fiction -> Titre : " + unLivreFiction.getTitle() + " | Prix : $" + unLivreFiction.getPrice());
        System.out.println("Livre de Non-Fiction -> Titre : " + unLivreNonFiction.getTitle() + " | Prix : $" + unLivreNonFiction.getPrice());
        
        System.out.println(); // Ligne vide pour aérer

        // 2. Démonstration de la Question 1.b (Tableau de 10 livres et boucle for)
        System.out.println("=== QUESTION 1.b : Tableau de 10 Livres (BookArray) ===");
        
        Book[] catalogue = new Book[10];

        // Remplissage du tableau avec un mélange de Fiction et NonFiction
        catalogue[0] = new Fiction("1984");
        catalogue[1] = new NonFiction("Une brève histoire du temps");
        catalogue[2] = new Fiction("Le Seigneur des Anneaux");
        catalogue[3] = new NonFiction("Système 1 / Système 2");
        catalogue[4] = new Fiction("Dune");
        catalogue[5] = new NonFiction("Le gène égoïste");
        catalogue[6] = new Fiction("Fondation");
        catalogue[7] = new NonFiction("Cosmos");
        catalogue[8] = new Fiction("Neuromancien");
        catalogue[9] = new NonFiction("De l'inégalité parmi les sociétés");

        // Boucle for pour afficher les détails des 10 livres
        for (int i = 0; i < catalogue.length; i++) {
            System.out.println("Livre n°" + (i + 1) + " : \"" + catalogue[i].getTitle() + 
                               "\" | Prix : $" + catalogue[i].getPrice());
        }
    }
}