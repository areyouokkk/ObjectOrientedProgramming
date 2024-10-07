import java.time.LocalDate;

public class Multimedia extends Item {

    // tracks count of how many multimedia are on loan
    private static int multimediaOnLoan;

    // constructor
    public Multimedia(int barcode, String authorArtist, String title, String type, int year, String isbn) {
        super(barcode, authorArtist, title, type, year, isbn);
    }

    // returns number of multimedia on loan
    public static int getMultimediaOnLoan() {
        return multimediaOnLoan;
    }

    // sets or removes item from loan count
    @Override
    public void setOnLoan(boolean onLoan) {
        if (onLoan) {
            multimediaOnLoan++;
        } else {
            multimediaOnLoan--;
        }
    }

    // returns due date for Multimedia
    @Override
    public LocalDate getDueDate() {
        return LocalDate.now().plusWeeks(1); // Multimedia are loaned for maximum 1 week at the time
    }

    // returns due date for item when its being renewed
    @Override
    public LocalDate getRenewalDueDate(LocalDate currentDate) {
        return currentDate.plusWeeks(1); // Max renewal period for multimedia is 1 week
    }

    // Method to check if item can be renewed
    @Override
    public boolean CanBeRenewed(int numOfRenews){
        return numOfRenews < 2; // Maximum number of renews for multimedia is 2
    }

    // returns maximum number of renews
    @Override
    public int getMaxNumOfRenews() {
        return 2;
    } // Maximum number of renews for multimedia is 2

}
