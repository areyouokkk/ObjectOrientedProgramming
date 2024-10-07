# Library Management System

## Description
This application is designed to manage the lending of library items, including books and multimedia. It allows librarians to issue, renew, and return items while keeping track of loan details such as barcode, user ID, issue date, due date, and renewals. The application also provides reporting features and can read from and write to CSV files to maintain persistent data.

## Functional Requirements
1. **Item Management**: The application can handle two types of lendable items: Books and Multimedia, predefined in the `ITEMS.csv` file.
2. **Loan Recording**: For each loan, the following details are recorded:
   - Barcode
   - User ID
   - Issue Date
   - Due Date
   - Number of Renewals
3. **User Management**: The set of library users is predefined and stored in `USERS.csv`.
4. **Loan Operations**:
   - Issue a loan by supplying the item barcode and user ID.
   - Renew an existing loan based on item barcode.
   - Return an item using its barcode.
   - View all items currently on loan.
   - Report on loan statistics, including the total number of each type of loan and renewal percentages.
   - Search for a loan item by barcode.
5. **Persistent Data**: On exit, the list of current loans is saved to `LOANS.csv`. On startup, data is loaded from these CSV files.

## Prerequisites
- Java Development Kit (JDK) version 17
- IntelliJ IDEA or any compatible IDE to run the program

## Getting Started
1. Clone the repository:
   ```bash
   git clone https://github.com/areyouokkk/ObjectOrientedProgramming.git
Navigate to the project directory:
bash
Copy code
cd ObjectOrientedProgramming
Open the project in IntelliJ IDEA.
Compile the program.
***********************************************************
Run the ManageLibrary class to start the console interface.
***********************************************************
Once the program is running, you will see the following menu:
***********************************************************
Welcome to MJ LIBRARY
Enter one of the following options:
(1) Issue Loan
(2) Renew Loan
(3) Return Item
(4) Display Loaned Items
(5) Display Report
(6) Search For Item
(7) Exit
Enter choice: 1
***********************************************************
Enter the number [1,2,3,4,5,6,7] corresponding to the function you want to perform:
***********************************************************
Follow the on-screen instructions based on your choice.
***********************************************************
(1) Return to Menu
Enter Barcode: 813844363
***********************************************************
(1) Return to Menu
Enter user ID: B00986808
***********************************************************
--------------------------
Loan issued successfully!
 Barcode: 813844363
 User ID: B00986808
 Issue Date: 2024-10-07
 Due Date: 2024-11-04
 Number of Renews: 0
--------------------------
Do you want to issue another loan?
(1) Yes
(2) No (back to menu)
Enter Choice: 2
***********************************************************

   
**CSV Files**
The following CSV files are used to store and manage data:

ITEMS.csv
Contains predefined library items, including books and multimedia. Each row includes:

Barcode: Unique identifier for the item.
Author/Artist: The author or artist of the item.
Title: The title of the item.
Type: The type of item (Book or Multimedia).
Year: The year of publication.
ISBN: The ISBN number of the book.

Example content:
Barcode,Author/Artist,Title,Type,Year,ISBN
25832497,Gabe Scain,enim in,Book,2004,621791531-6
240453126,Elka Glazebrook,non mattis pulvinar nulla pede,Book,2008,867041599-2
813844363,Filip Crossman,lacinia erat vestibulum sed magna,Book,1996,751789469-2


USERS.csv
Contains predefined library users, each represented by:

User_ID: Unique identifier for the user.
First_Name: The first name of the user.
Last_Name: The last name of the user.
Email: The email address of the user.

Example content:
User_ID,First_Name,Last_Name,Email
B00447489,Gray,Shingles,gshingles0@ucoz.ru
B00187440,Selby,Malafe,smalafe1@prweb.com
LOANS.csv
Stores current loans, including:

Barcode: The barcode of the loaned item.
User_ID: The ID of the user who loaned the item.
Issue_Date: The date the item was issued.
Due_Date: The date the item is due.
Renewed_Loan: The number of times the loan has been renewed.

Example content:
Barcode,User_ID,Issue_Date,Due_Date,Renewed_Loan
550999145,B00103171,2024-05-02,2024-07-11,2
577774437,B00000464,2024-05-02,2024-05-30,0

***********************************************************

Classes overview:

•  Item (abstract)
Base class representing objects that can be borrowed from the library. This class encapsulates common properties (Barcode, Author/Artist, Title, Type, Year, ISBN) and defines abstract methods shared by both types of items. It serves as a template for the Book and Multimedia classes.

•  Book (extends Item)
Inherits common attributes and methods from the Item class, allowing for specific implementations of abstract methods. This enables the specification of due dates, renewal dates, and maximum renewals according to the requirements for this type of item.

•  Multimedia (extends Item)
Inherits common attributes and methods from the Item class, allowing for specific implementations of abstract methods. This enables the specification of due dates, renewal dates, and maximum renewals according to the requirements for this type of item.

•  Library
Provides core methods for the program, such as issuing loans, renewing loans, returning items, viewing items on loan, displaying reports, and searching for items. It also manages collections of Items, Users, and Loans.

•  Loan
	Represents loan objects.
 
•  ManageFiles
	Handles file operations, including reading from and writing to CSV files (ITEMS.csv, USERS.csv, LOANS.csv), returning lists of items, users, and loans for use in the Library class.
 
•  ManageLibrary
Provides the user interface and gathers valid user input to perform operations using methods from the Library class. This class serves as the starting point for the program.

•  User
Represents a library user.

***********************************************************



************
License
************
This project is for educational use only. Please feel free to use it for learning purposes.

