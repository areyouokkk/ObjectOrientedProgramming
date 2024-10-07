import java.time.LocalDate;

public class Book extends Item {

    // tracks count of how many books are on loan
    private static int booksOnLoan;

    // Constructor
    public Book(int barcode, String authorArtist, String title, String type, int year, String isbn) {
        super(barcode, authorArtist, title, type, year, isbn);
    }

    // returns number of books on loan
    public static int getBooksOnLoan() {
        return booksOnLoan;
    }

    // sets or removes item from loan count
    @Override
    public void setOnLoan(boolean onLoan) {
        if (onLoan) {
            booksOnLoan++;
        } else {
            booksOnLoan--;
        }
    }


    // returns due date for Book
    @Override
    public LocalDate getDueDate() {
        return LocalDate.now().plusWeeks(4); // Books are loaned for maximum 4 weeks at the time
    }

    // returns due date for item when its being renewed
    @Override
    public LocalDate getRenewalDueDate(LocalDate currentDueDate) {
        return currentDueDate.plusWeeks(2); // Adds 2 weeks to the current due date
    }

    // Method to check if item can be renewed
    @Override
    public boolean CanBeRenewed(int numOfRenews) {
        return numOfRenews < 3; // maximum number of renews for Book is 3
    }


    // returns maximum number of renews
    @Override
    public int getMaxNumOfRenews() {
        return 3;
    } // maximum number of renews for book is 3
}