import java.time.LocalDate;
import java.util.List;

public class Library {
    private final String libraryName = "MJ LIBRARY";
    private List<Item> itemsList;
    private List<User> userList;
    private List<Loan> loanList;


    // constructor
    public Library() {
        this.itemsList = ManageFiles.getItemList(); // initialize itemList with data from ITEMS.csv using ManageFiles
        this.userList = ManageFiles.getUserList(); // initialize userList with data from USERS.csv using ManageFiles
        this.loanList = ManageFiles.getLoanList(); // initialize loanList with data from LOANS.csv using ManageFiles
    }



    // method to issue loan by supplying item barcode and user id
    public void issueLoan(int barcode, String userId) {

        Item item = getItem(barcode); // creates instance of item by barcode to access due date based on item type

        LocalDate issueDate = LocalDate.now(); // sets issue date
        LocalDate dueDate = item.getDueDate(); // sets due date based on item type

        int numOfRenews = 0; // sets number of renews to 0 when loan is created for first time

        Loan loan = new Loan(barcode,userId,issueDate,dueDate,numOfRenews); // creates new loan object

        item.setOnLoan(true); // sets item on loan to track count

        loanList.add(loan); // adds loan to the list of loans

        System.out.println("--------------------------");
        System.out.println("Loan issued successfully!\n" + loan);
        System.out.println("--------------------------");


    }

    // method to renew loan by supplying item barcode
    public void renewLoan(int barcode) {

        if (loanExists(barcode)) { // checks if loan exist by supplied barcode

            Loan loan = getLoan(barcode); // gets loan by barcode
            Item item = getItem(barcode); // gets item by barcode


            int numOfRenews = loan.getNumOfRenews();  // current number of renews
            int maxNumOfRenews = item.getMaxNumOfRenews(); // maximum number of renews for item

            if (item.CanBeRenewed(numOfRenews)) { // checks if item can be renewed by passing in current number of renews

                LocalDate currentDueDate = loan.getDueDate();
                LocalDate newDueDate = item.getRenewalDueDate(currentDueDate);

                loan.setDueDate(newDueDate);

                loan.setNumOfRenews(numOfRenews + 1); // increments number of renews by 1

                System.out.println("--------------------------"); // line to visually separate content
                System.out.println("Loan renewed successfully!\n" + loan);
                System.out.println("--------------------------"); // line to visually separate content

            } else {
                System.out.println("--------------------------"); // line to visually separate content
                System.out.println("Loan can't be renewed!\n" + loan);
                System.out.println("Maximum number of renews for " + item.getType() + " item is " + maxNumOfRenews);
                System.out.println("--------------------------"); // line to visually separate content
            }
        } else {
            System.out.println("Loan can't be renewed. No records for loan with barcode: " + barcode);
        }

    }


    // method to return loaned item by barcode
    public void returnLoanedItem(int barcode){
        if (loanExists(barcode)) { // checks if loan exists by barcode

            Loan loan = getLoan(barcode); // gets loan
            Item item = getItem(barcode); // gets item

            item.setOnLoan(false); // removes item from count on loan

            loanList.remove(loan); // removes loan from loanList

            System.out.println("Item has been returned successfully!");

        } else {
            System.out.println("Loan with barcode " + barcode + " doesn't exist!");
        }
    }



    // Displays a list of all items currently on loan.
    public void displayLoanedItems(){

        int loanNr = 1; // loan counter

        System.out.println("--------------Loaned Items--------------"); // line to visually separate content

        for (Loan loan : loanList) {
            int barcode = loan.getBarcode();
            Item item = getItem(barcode);
            System.out.println("#" + loanNr + item);
            loanNr++;
        }

        System.out.println("----------------------------------------"); // line to visually separate content
    }

    // sets all items on loan that are in loanList
    public void setItemsOnLoan() {
        for (Loan loan : loanList) {
            int barcode = loan.getBarcode();
            Item item = getItem(barcode);
            item.setOnLoan(true);
        }
    }

    // Returns number of items that have been renewed more than once
    public int countRenewedItemsMoreThanOnce() {

        int loansRenewedMoreThanOnceCount = 0;
        for (Loan loan : loanList) {
            if (loan.getNumOfRenews() > 1 ) { // checks if loan has been renewed more than once
                loansRenewedMoreThanOnceCount++;
            }
        }

        return loansRenewedMoreThanOnceCount;
    }

    /**
     * Method to display report
     * Library name
     * total books on loan
     * total multimedia on loan
     * percentage of loans renewed more than once
     */
    public void displayReport(){

        int totalRenewed = countRenewedItemsMoreThanOnce(); // number of items that have been renewed more than once
        int totalLoans = Loan.getCount();
        int booksOnLoan = Book.getBooksOnLoan();
        int multimediaOnLoan = Multimedia.getMultimediaOnLoan();

        double percentageRenewed = (double) totalRenewed / totalLoans * 100; // calculates percentage of items that are renewed more than once

        System.out.println("----------------------------"); // line to visually separate content
        System.out.println("Library Name: " + libraryName);
        System.out.println("Total books on loan: " + booksOnLoan);
        System.out.println("Total multimedia on loan: " + multimediaOnLoan);
        System.out.println("Percentage of loans renewed more than once: " + String.format("%.0f",percentageRenewed) + "%");
        System.out.println("----------------------------"); // line to visually separate content
    }


    // method to search for item
    public void searchForItem(int barcode){
        System.out.println("---------"); // line to visually separate content
        System.out.print("Item found: ");
        System.out.println(getItem(barcode)); // prints details of the sought item
        System.out.println("---------"); // line to visually separate content
    }


    // method to check if loan exists by barcode in loan list
    public boolean loanExists(int barcode) {
        for (Loan loan : loanList) {
            if (loan.getBarcode() == barcode) {
                return true;
            }
        }
        return false;
    }


    // method to check if item exists in items list
    public boolean itemExists(int barcode) {
        for (Item item : itemsList) {
            if (item.getBarcode() == barcode){
                return true;
            }
        }
        return false;
    }

    // method to check if user exists by user id in user list
    public boolean userExists(String userId) {
       for (User user : userList) {
           if (user.getUserId().equalsIgnoreCase(userId)) {
               return true;
           }
       }
       return false;
    }


    // method to get item from itemsList
    public Item getItem(int barcode){
        for (Item item : itemsList) {
            if (barcode == item.getBarcode()) {
                return item;
            }
        }
        return null;
    }

    // method to get loan from loansList
    public Loan getLoan(int barcode) {
       for (Loan loan : loanList) {
           if (loan.getBarcode() == barcode) {
               return loan;
           }
       }
       return null;
    }


    // returns loan list
    public List<Loan> getLoanList() {
        return loanList;
    }

    // returns library  name
    public String getLibraryName() {
        return libraryName;
    }

} // end of Class Library
