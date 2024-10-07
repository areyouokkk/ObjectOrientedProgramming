import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManageFiles {

    // Reads ITEMS.csv file and returns list of items
    public static List<Item> getItemList() {

        List<Item> itemList = new ArrayList<>();

        String fileName = "ITEMS.csv";

        try (Scanner readFile = new Scanner(new FileReader(fileName))) {

            // Check if the file is empty
            if (!readFile.hasNextLine()) {
                fileIsEmptyMessage(fileName); // prints message if file is empty
                return null; // Returns an empty list
            }

            readFile.nextLine(); // Skips the header line

            while (readFile.hasNextLine()) {

                String line = readFile.nextLine();
                String[] itemProperties = line.trim().split(",");

                int barcode = Integer.parseInt(itemProperties[0]);
                String authorArtist = itemProperties[1];
                String title = itemProperties[2];
                String type = itemProperties[3];
                int year = Integer.parseInt(itemProperties[4]);
                String isbn = itemProperties[5];

                // creates item objects based on the type (Book and Multimedia)
                Item item;
                if ("Book".equals(type)) {
                    item = new Book(barcode, authorArtist, title, type, year, isbn);
                } else if ("Multimedia".equals(type)) {
                    item = new Multimedia(barcode, authorArtist, title, type, year, isbn);
                } else {
                    // Handles unrecognized item types
                    System.err.println("Unrecognized item type: " + type);
                    continue; // Skips this item and continues to the next one
                }

                itemList.add(item);
            }
        } catch (FileNotFoundException e) {
            handleFileNotFoundException(fileName);
        } catch (Exception e) {
            handleFileExceptions(e,fileName); // handles errors and prints messages if they occur
        }

        return itemList;

    } // end of getItemsList() method



    // Reads USERS.csv file and returns list of users
    public static List<User> getUserList() {

        List<User> userList = new ArrayList<>();

        String fileName = "USERS.csv";

        try (Scanner readFile = new Scanner(new FileReader(fileName))) {

            // Checks if the file is empty
            if (!readFile.hasNextLine()) {
                fileIsEmptyMessage(fileName); // prints message if file is empty
                return null; // Returns an empty list
            }

            readFile.nextLine(); // Skips the header line

            while (readFile.hasNextLine()) {

                String line = readFile.nextLine();
                String[] userData = line.trim().split(",");

                String userId = userData[0];
                String userName = userData[1];
                String userLastName = userData[2];
                String userEmail = userData[3];

                User user = new User(userId, userName, userLastName, userEmail);

                userList.add(user);
            }
        } catch (FileNotFoundException e) {
            handleFileNotFoundException(fileName);
        } catch (Exception e) {
            handleFileExceptions(e,fileName); // handles errors and prints messages if they occur
        }

        return userList;
    } // end of getUserList() method


    // Reads LOANS.csv file and returns list of loans
    public static List<Loan> getLoanList() {

        List<Loan> loanList = new ArrayList<>();

        String fileName = "LOANS.csv";

        try (Scanner readFile = new Scanner(new FileReader(fileName))) {

            // Checks if the file is empty
            if (!readFile.hasNextLine()) {
                fileIsEmptyMessage(fileName); // prints message if file is empty
                return loanList; // Returns an empty list
            }

            // Skips the header line
            readFile.nextLine();

            while (readFile.hasNextLine()) {

                String line = readFile.nextLine();
                String[] loanData = line.trim().split(",");

                int barcode = Integer.parseInt(loanData[0]);
                String userId = loanData[1];
                LocalDate issueDate = LocalDate.parse(loanData[2]);
                LocalDate dueDate = LocalDate.parse(loanData[3]);
                int numOfRenews = Integer.parseInt(loanData[4]);

                Loan loan = new Loan(barcode, userId, issueDate, dueDate, numOfRenews);

                loanList.add(loan);
            }

            // if file is not present it will not display error message
        } catch (FileNotFoundException ignored) {

        } catch (Exception e) {
            handleFileExceptions(e,fileName); // handles errors and prints messages if they occur
        }
        return loanList;
    } // end of getLoanList method



    // Writes loans to LOANS.csv file by passing loanList
    public static void writeLoansToFile(List<Loan> loanList) {

        String fileName = "LOANS.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

            // writes header for LOANS.csv file
            writer.write("Barcode, User ID, Issue Date, Due Date, Renewed Loan\n");

            for (Loan loan : loanList) {
                writer.write(loan.getBarcode() + "," +
                        loan.getUserId() + "," +
                        loan.getIssueDate() + "," +
                        loan.getDueDate() + "," +
                        loan.getNumOfRenews() + "\n");
            }

        } catch (Exception e) {
            handleFileExceptions(e,fileName); // handles errors and prints messages if they occur
        }

    } // end of writeLoansToFile() method



    // message to inform the user if file is empty
    public static void fileIsEmptyMessage(String fileName) {
        System.out.println("------------------------");
        System.out.println(fileName + " file is empty!");
        System.out.println("------------------------");
    }

    // if ITEMS.csv or USERS.csv files are not found, program will terminate
    public static void handleFileNotFoundException(String fileName) {
        System.err.println("Error: File " + fileName + " not found.\nPlease locate the file and retry.");
        System.err.println("Exiting program...");
        System.exit(0);
    }


    // File exception handling with custom messages.
    public static void handleFileExceptions(Exception e, String fileName) {
        switch (e) {
            case IOException ioException ->
                    System.err.println("Error reading or writing to the file '" + fileName + "'.");
            case DateTimeParseException dateTimeParseException ->
                    System.err.println("Error: Date format in the file '" + fileName + "' is incorrect.\nPlease exit the program and check the file and ensure dates are in the correct format.");
            case NumberFormatException numberFormatException ->
                    System.err.println("Error: Numeric format in the file '" + fileName + "' is incorrect.\nPlease exit the program and check the file and ensure numeric values are correctly formatted.");
            case ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException ->
                    System.err.println("Error: File structure of '" + fileName + "' is incorrect.\nPlease exit the program and check the file and ensure it has the correct structure.");
            case null, default ->
                    System.err.println("An unexpected error occurred while processing file '" + fileName + "': " + e.getMessage());
        }
    } // end of handleFileExceptions() method

} // end of ManageFiles class

