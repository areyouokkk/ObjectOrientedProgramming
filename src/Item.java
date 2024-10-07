import java.time.LocalDate;

public abstract class Item {
    private int barcode;
    private String authorArtist;
    private String title;
    private String type;
    private int year;
    private String isbn;

    // constructor
    public Item(int barcode, String authorArtist, String title, String type, int year, String isbn) {
        this.barcode = barcode;
        this.authorArtist = authorArtist;
        this.title = title;
        this.type = type;
        this.year = year;
        this.isbn = isbn;
    }


    // toString method, used to get textual representation of item object
    @Override
    public String toString() {
        return "\nBarcode: " + barcode + "\nAuthor/Artist: " + authorArtist + "\nTitle: " + title +
                "\nType: " + type + "\nYear: " + year + "\nISBN: " + isbn;
    }

    // method to set items on loan
    public abstract void setOnLoan(boolean onLoan);

    // Method to get due date
    public abstract LocalDate getDueDate();

    // returns due date for item when its being renewed
    public abstract LocalDate getRenewalDueDate(LocalDate currentDueDate);

    // Method to check if a loan can be renewed
    public abstract boolean CanBeRenewed(int numOfRenews);

    // method to get max renews
    public abstract int getMaxNumOfRenews();

    // returns item type (book or multimedia)
    public String getType() {
        return type;
    }

    // returns barcode
    public int getBarcode() {
        return barcode;
    }

}
