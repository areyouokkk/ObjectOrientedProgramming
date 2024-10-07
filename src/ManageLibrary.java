import java.util.InputMismatchException;
import java.util.Scanner;

public class ManageLibrary {

    private static Scanner keyboard = new Scanner(System.in);
    private Library library;


    // constructor
    public ManageLibrary() {
        this.library = new Library();
    }

    // main method
    public static void main(String[] args) {

        ManageLibrary useLibrary = new ManageLibrary(); // instance of ManageLibrary

        useLibrary.runProgram(); // program starting point

        keyboard.close();

    } // end of main


    // Main menu
    private void displayMenu() {
        System.out.println("Welcome to " + library.getLibraryName());
        System.out.println("Enter one of the following options:");
        System.out.println("(1) Issue Loan"); // use case
        System.out.println("(2) Renew Loan");
        System.out.println("(3) Return Item");
        System.out.println("(4) Display Loaned Items");
        System.out.println("(5) Display Report");
        System.out.println("(6) Search For Item");
        System.out.println("(7) Exit"); // use case
    }

    // Method to get user choice for main menu
    public int getUserInputForMenu() {
        System.out.print("Enter choice: ");
        int userChoice = keyboard.nextInt();
        keyboard.nextLine();
        return userChoice;
    }


    //  Method to get VALID barcode from user
    public int getValidBarcodeFromUser() {
        while (true) {
            try {

                System.out.println("(1) Return to Menu");
                System.out.print("Enter Barcode: ");
                int barcode = keyboard.nextInt();
                keyboard.nextLine();

                // if barcode equals to 1 will return to main menu
                if (returnToMenu("",barcode)) {
                    return barcode;
                }

                // if barcode is not valid will return to start of the loop and prompt user for barcode again
                if (!library.itemExists(barcode)) {
                    System.out.println("Item with barcode " + barcode + " doesn't exist!");
                    continue;
                }
                return barcode;

            } catch (InputMismatchException e) {
                InputExceptionMessage();
                keyboard.nextLine(); // Clears the input buffer after exception is caught
            }
        } // end of while loop
    } // end of get validBarcodeFromUser()


    //  Method to get VALID user id from user
    public String getValidUserIdFromUser() {

        while (true) {
            System.out.println("(1) Return to Menu");
            System.out.print("Enter user ID: ");
            String userId = keyboard.nextLine();

            // if userId value equals to 1, will return to menu
            if (returnToMenu(userId,0)) {
                return userId;
            }

            // if user ID is not valid, starts new iteration by prompting user to enter ID again
            if (!library.userExists(userId)) {
                System.out.println("User with ID: " + userId + " doesn't exist!");
                continue;
            }

            return userId;

        } // end of while loop
    } // end of getUserIdFromUser() method


    // method to check if user wants to return to main menu
    public boolean returnToMenu(String user, int userAtInt) {
        return user.equals("1") || userAtInt == 1;
    }

    // handles exception where user enters invalid input
    public void InputExceptionMessage() {
        System.out.println("Error: Please enter integers only! e.g. 1234567890");
    }

    // method to ask if user wants to perform same operation again
    public boolean askToRepeatProcess() {
        int userAnswer = 0;

            System.out.println("(1) Yes");
            System.out.println("(2) No (back to menu)");
            System.out.print("Enter Choice: ");

        try {
            userAnswer = keyboard.nextInt();
            keyboard.nextLine();
        } catch (InputMismatchException inputMismatchException) {
            InputExceptionMessage(); // method prints message that user has entered invalid input
            keyboard.nextLine();
        }

        return userAnswer == 1;
    }

    // method to process loan issue
    public void processLoanIssue() {

        boolean repeatProcess = true; // boolean flag

        while (repeatProcess) {

            // will prompt user to input barcode
            int barcode = getValidBarcodeFromUser();

            // if barcode == 1, will return to menu
            if (returnToMenu("",barcode)) {
                return;
            }

            // if loan exists, will skip the rest of the code and start new iteration prompting user for barcode again
            if (library.loanExists(barcode)) {
                System.out.println("Loan with barcode: " + barcode + " already exists!");
                System.out.println("Please try again!");
                continue;
            }

            // will prompt user to input user id
            String userId = getValidUserIdFromUser();

            // if user ID equals to 1, will return to main menu
            if (returnToMenu(userId, 0)) {
                return;
            }

            // creates loan and adds it to the list of loans
            library.issueLoan(barcode, userId);


            System.out.println("Do you want to issue another loan?");
            // will ask user if he wants to issue another loan or return to menu
            repeatProcess = askToRepeatProcess();

        } // end of while loop
    } // end of processLoanIssue()


    // method to process loan renewal
    public void processLoanRenewal() {

        boolean repeatProcess = true; // boolean flag

        while (repeatProcess) {

            // will prompt user to input barcode
            int barcode = getValidBarcodeFromUser();

            // if barcode == 1, will return to menu
            if (returnToMenu("",barcode)) {
                return;
            }

            // will renew loan
            library.renewLoan(barcode);

            System.out.println("Do you want to renew another Item?");
            // asks user if he wants to renew another item or return to menu
            repeatProcess = askToRepeatProcess();

        } // end of while loop
    } // end of processLoanRenewal()

    // method to process return of an item
    public void processReturns() {

        boolean repeatProcess = true; // boolean flag

        while (repeatProcess) {

            // prompts user to enter barcode
            int barcode = getValidBarcodeFromUser();

            // if barcode == 1, will return to menu
            if (returnToMenu("",barcode)) {
                return;
            }

            // removes item from loan list
           library.returnLoanedItem(barcode);

            System.out.println("Do you want to return another Item?");

            // asks if user wants to return another or return to menu
            repeatProcess = askToRepeatProcess();

        } // end of while loop
    } // end of returnLoanedItem method


    // method to search for item
    public void processItemSearch() {

        boolean repeatProcess = true; // boolean flag

        while (repeatProcess) {

            // prompts user to input barcode
            int barcode = getValidBarcodeFromUser();

            // if barcode == 1 , returns to menu
            if (returnToMenu("",barcode)) {
                return;
            }

            // displays item
            library.searchForItem(barcode);

            System.out.println("Do you want to search for another item?");
            // asks if user wants to search for another item or return to menu
            repeatProcess = askToRepeatProcess();
        } // end of while loop
    } // end of processItemSearch()

    // method to run program, contains all methods to perform required operations
    public void runProgram() {

        // sets all items on loan that are in loan list
        library.setItemsOnLoan();

        int choice = 0;

        do {
            // displays menu to user
            displayMenu();

            try {
                // prompts user to input the choice for desired operation
                choice = getUserInputForMenu();
            } catch (InputMismatchException e) {
                InputExceptionMessage(); // displays message to user if invalid input is received
                keyboard.nextLine();
            }

            switch (choice) {
                case 1:
                    // issue a loan
                    processLoanIssue();
                    break;
                case 2:
                    // Renew a loan
                    processLoanRenewal();

                    break;
                case 3:
                    // Return item
                    processReturns();
                    break;
                case 4:
                    // Display Loaned items
                    library.displayLoanedItems();

                    // ask user if he wants to continue
                    askToContinue();
                    break;
                case 5:
                    // Display report
                    library.displayReport();

                    // ask user if he wants to continue
                    askToContinue();
                    break;
                case 6:
                    // Search for item
                    processItemSearch();
                    break;
                case 7:
                    // Exit the program

                    // writes loan list to the LOANS.csv
                    ManageFiles.writeLoansToFile(library.getLoanList());

                    System.out.println("\t\t\tGoodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 7);
    }


    // methods to ask if user wants to continue
    public void askToContinue() throws InputMismatchException {
        System.out.println("Do you want to continue?");
        System.out.println("(1) Back to Menu");
        System.out.println("(2) Exit program");
        System.out.print("Enter Choice: ");
        int choice = keyboard.nextInt();
        keyboard.nextLine();

        if (choice == 2) {

            // writes loan list to the LOANS.csv
            ManageFiles.writeLoansToFile(library.getLoanList());

            // exits program
            System.exit(0);
        }
    } // end of askToContinue()

} // end of ManageLibrary class