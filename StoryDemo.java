// ==========================================
// 1. Abstract Base Class: Story
// ==========================================
abstract class Story {
    private String title;
    private String author;
    private int pages;
    private String message;

    // Category Constants for Page Boundaries
    public static final int NOVEL_MIN = 101;
    public static final int NOVELLA_MIN = 50;
    public static final int NOVELLA_MAX = 100;
    public static final int SHORT_STORY_MAX = 49;

    // Constructor
    public Story(String title, String author, int pages) {
        this.title = title;
        this.author = author;
        this.message = ""; // Default empty message
        // We call the abstract setPages method inside the constructor
        this.setPages(pages);
    }

    // Getters and Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public int getPages() { return pages; }
    
    // Protected setter to allow subclasses to write directly to the private pages field
    protected void setPagesDirectly(int pages) { this.pages = pages; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    // Abstract method to be overridden by subclasses
    public abstract void setPages(int pages);
}

// ==========================================
// 2. Subclass: Novel (> 100 pages)
// ==========================================
class Novel extends Story {
    public Novel(String title, String author, int pages) {
        super(title, author, pages);
    }

    @Override
    public void setPages(int pages) {
        setPagesDirectly(pages);
        if (pages < NOVEL_MIN) {
            int needed = NOVEL_MIN - pages;
            setMessage("ERROR: A Novel must have more than 100 pages. Please ADD " + needed + " page(s).");
        } else {
            setMessage("Valid page count for a Novel.");
        }
    }
}

// ==========================================
// 3. Subclass: Novella (50 to 100 pages inclusive)
// ==========================================
class Novella extends Story {
    public Novella(String title, String author, int pages) {
        super(title, author, pages);
    }

    @Override
    public void setPages(int pages) {
        setPagesDirectly(pages);
        if (pages < NOVELLA_MIN) {
            int needed = NOVELLA_MIN - pages;
            setMessage("ERROR: A Novella must have 50-100 pages. Please ADD " + needed + " page(s).");
        } else if (pages > NOVELLA_MAX) {
            int excess = pages - NOVELLA_MAX;
            setMessage("ERROR: A Novella must have 50-100 pages. Please CUT " + excess + " page(s).");
        } else {
            setMessage("Valid page count for a Novella.");
        }
    }
}

// ==========================================
// 4. Subclass: ShortStory (< 50 pages)
// ==========================================
class ShortStory extends Story {
    public ShortStory(String title, String author, int pages) {
        super(title, author, pages);
    }

    @Override
    public void setPages(int pages) {
        setPagesDirectly(pages);
        if (pages > SHORT_STORY_MAX) {
            int excess = pages - SHORT_STORY_MAX;
            setMessage("ERROR: A ShortStory must have fewer than 50 pages. Please CUT " + excess + " page(s).");
        } else {
            setMessage("Valid page count for a ShortStory.");
        }
    }
}

// ==========================================
// 5. Main Application: StoryDemo
// ==========================================
public class StoryDemo {
    public static void main(String[] args) {
        System.out.println("--- Picky Publishing House Editorial Log ---\n");

        // Array holding 6 stories: 1 valid and 1 invalid for each category
        Story[] submissions = new Story[6];

        // Novels
        submissions[0] = new Novel("The Great Beyond", "Jane Doe", 320);     // Valid
        submissions[1] = new Novel("Short Narrative", "John Smith", 85);    // Invalid (Too short)

        // Novellas
        submissions[2] = new Novella("Autumn Leaves", "Alice Green", 75);   // Valid
        submissions[3] = new Novella("Overwritten Tale", "Bob Miller", 120); // Invalid (Too long)

        // Short Stories
        submissions[4] = new ShortStory("The Blink", "Charlie Brown", 12);   // Valid
        submissions[5] = new ShortStory("Endless Echo", "Diana Prince", 55); // Invalid (Too long)

        // Display results using a loop
        for (int i = 0; i < submissions.length; i++) {
            Story s = submissions[i];
            System.out.println("Submission #" + (i + 1) + " [" + s.getClass().getSimpleName() + "]");
            System.out.println("  Title : " + s.getTitle());
            System.out.println("  Author: " + s.getAuthor());
            System.out.println("  Pages : " + s.getPages());
            System.out.println("  Status: " + s.getMessage());
            System.out.println("----------------------------------------------------------------");
        }
    }
}