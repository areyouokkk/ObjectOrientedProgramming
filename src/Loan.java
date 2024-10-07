import java.time.LocalDate;
import java.util.List;

public class Loan {
    private static int count;
    private int barcode;
    private String userId;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private int numOfRenews;


    // constructor
    public Loan(int barcode, String userId, LocalDate issueDate, LocalDate dueDate, int numOfRenews) {
        this.barcode = barcode;
        this.userId = userId;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.numOfRenews = numOfRenews;
        ++count;
    }

    // toString method, used to get textual representation of loan object
    @Override
    public String toString() {
        return " Barcode: " + barcode +
                "\n User ID: " + userId +
                "\n Issue Date: " + issueDate +
                "\n Due Date: " + dueDate +
                "\n Number of Renews: " + numOfRenews;
    }

    // returns total number of loans
    public static int getCount() {
        return count;
    }

    // GETTER METHODS
    public int getBarcode() {
        return barcode;
    }

    public String getUserId() {
        return userId;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public int getNumOfRenews() {
        return numOfRenews;
    }

    // SETTER METHODS

    public void setBarcode(int barcode) {
        this.barcode = barcode;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setNumOfRenews(int numOfRenews) {
        this.numOfRenews = numOfRenews;
    }
}
