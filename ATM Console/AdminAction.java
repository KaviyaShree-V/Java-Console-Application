import java.util.*;

public class AdminAction extends ATM {
    //Admin action is inherited from ATM class which means it inherits all members from ATM class
    public static Admin admin() {
        System.out.println("Enter Admin Name:");
        String enteredName = scanner.nextLine();//to get the admin name and store in the entered name
        Admin validAdmin = null;//store the reference to a valid Admin object or variable to store the valid Admin - if found
        // Loop for list of admins to find a exact admin for the entered name
        for (Admin admin : ATM.getAdmins()) {
            if (admin.getId().equals(enteredName)) {
                //to check admin id equals to enteredname
                validAdmin = admin;//reassigning the correct admin to the validAdmin
                break;
            }
        }
        // Check if no admin was found with the entered name
        if (validAdmin == null) {
            System.out.println("User not found. Returning to login.");
            return null;
        }
        int retry = 0;//variable to check the attempts for admin pin
        //to make the admin only for 3 login attempts
        while (retry < 3) {
            System.out.println("Enter the Pin:");
            String enteredPin = scanner.nextLine();//to get the pin from the admin

            if (validAdmin.getPin().equals(enteredPin)) {
                //validates the adminPin is equal to the validate pin
                return validAdmin;//if correct it return the validAdmin
            } else {
                retry++;//increment for attempts
                System.out.println("Incorrect PIN. " + (3 - retry) + " attempts remaining...");
            }
        }

        System.out.println("Maximum login attempts exceeded. Returning to login.");
        return null;//if name and pin is mismatched then it returns null
    }


    public static void enterChoice(Admin admin) {
        int choice = 0; // Initialize 'choice' variable to store the admin's menu choice.
        //Loop starts and continues & if choice is 7 the will be exited
        while (choice != 7) {
            System.out.println("Enter Choice:\n\t1. Add User \n\t2. Delete User \n\t3. Deposit to ATM \n\t4. Balance in ATM \n\t5. View All Users  \n\t6. View Transactions \n\t7. Exit \n\t8. Finish Process");
            choice = Integer.parseInt(scanner.nextLine());//to get the choice from the admin to visit the admin required page

            //case for the admin choice using switch
            switch (choice) {
                case 1:
                    System.out.println("Redirecting to Add User Page...");
                    addUser(admin);// Call the method to addUser, by passing the current admin object
                    break;
                case 2:
                    System.out.println("Redirecting to Delete User Page...");
                    deleteUser();// Call the method to deleteUser,
                    break;
                case 3:
                    System.out.println("Redirecting to Deposit Page...");
                    depo(admin);// Call the method to depo, by passing the current admin object
                    break;
                case 4:
                    System.out.println("Balance in ATM is..");
                    checkBalance();// Call the method to checkBalance
                    break;
                case 5:
                    System.out.println("View All User names");
                    Admin.viewAllUsers();//call the static method to ViewAllUsers
                    //printAllUsers();
                    break;
                case 6:
                    System.out.println("Redirecting to View Transaction Page....");
                    viewaTransHis(admin);// Call the method to viewTransHis, passing the current admin object
                    break;
                case 7:
                    System.out.println("Exit Current Page...");
                    return;// Exit the loop and return to the previous page
                default:
                    System.out.println("Process Completed.....");
                    System.exit(8);// // Exit the program if invalid choice
            }
        }
    }

    //admin add the user - so Adduser method in AdminAction
    public static void addUser(Admin admin) {
        System.out.print("Enter new user name: ");
        String newName = scanner.nextLine();//to get the new username
        System.out.print("Enter new user PIN: ");
        String newPin = scanner.nextLine();//to get the new user pin
        System.out.print("Enter Initial Deposit Amount: ");
        double newBalance = Double.parseDouble(scanner.nextLine());//to get the user's initial deposit amount
        // Create a new User object with the details
        User newUser = new User(newName, newPin, newBalance);
        ATM.getUsers().add(newUser);// Add the new user to the ATM's user list
        System.out.println("New user added successfully.");
        addNTransaction(newName, "Deposit", newBalance, admin);// Add a transaction for the new user's initial deposit

    }

    //Admin Action has delete user = deleteUser method
    public static void deleteUser() {
        System.out.print("Enter the username to delete: ");
        String name = scanner.nextLine();//to get the username to remove

        boolean userFound = false;//to check if user is found
        for (User user : ATM.getUsers()) {
            if (user.getId().equals(name)) {//if admin entered name is equal to the users name in arrayList
                ATM.getUsers().remove(user);// Remove user from the ATM's user list
                System.out.println("User " + name + " deleted successfully.");
                userFound = true;// user was found and deleted
                break;
            }
        }
        // If user is not found, display a message
        if (!userFound) {
            System.out.println("User not found.");
        }
    }

    public static void checkBalance() {
        // Display the current balance in the ATM
        System.out.println("Your Balance is: Rs." + ATM.balance);
        // Display the count of various denominations of notes in the ATM
        System.out.println("Balance in notes:");
        System.out.println("Notes 500 - " + ATM.notesList.get(0).getCount());
        System.out.println("Notes 200 - " + ATM.notesList.get(2).getCount());
        System.out.println("Notes 2000 - " + ATM.notesList.get(1).getCount());
        System.out.println("Notes 100 - " + ATM.notesList.get(3).getCount());
    }


    public static void depo(Admin admin) {
        System.out.println("Enter the amount to deposit in your Account:");
        double amount;
        //check for admin enter amount can be deposited or not
        while (true) {
            amount = Double.parseDouble(scanner.nextLine());
            if (amount > 0 && amount % 100 == 0) {// if Valid amount then, exit the loop
                break;
            } else {
                System.out.println("Enter a valid amount to deposit:");
            }
        }
        System.out.println("The Total Amount=" + amount);
        // Initialize note objects for each denomination
        double cashCount = 0.0;
        Notes twoThousandNotes = new TwoThousand(2000, 0);
        Notes fiveHundredNotes = new FiveHundred(500, 0);
        Notes twoHundredNotes = new TwoHundred(200, 0);
        Notes hundredNotes = new Hundred(100, 0);
        //Loop for notes in ATM and assigning the variables for respective notes
        for (Notes note : notesList) {
            if (note.getNote() == 2000) {
                twoThousandNotes = note;
            } else if (note.getNote() == 500) {
                fiveHundredNotes = note;
            } else if (note.getNote() == 200) {
                twoHundredNotes = note;
            } else if (note.getNote() == 100) {
                hundredNotes = note;
            }
        }


        System.out.println("Denominations:\n1. 2000 \n2. 500 \n3. 200 \n4. 100");
        // Validate and calculate the cash count based on user input
        while (cashCount < amount) {
            System.out.println("Enter your denominations:");
            int g = Integer.parseInt(scanner.nextLine());
            if (g == 500 || g == 2000 || g == 200 || g == 100) {// Check if the entered denomination is valid (2000, 500, 200, or 100)
                System.out.println("Number of " + g + ":");
                int w = Integer.parseInt(scanner.nextLine());
                if (g == 500) {
                    fiveHundredNotes.setCount(fiveHundredNotes.getCount() + w);// Update the count of 500 denomination note
                    cashCount += g * w; // Add the total value of 500 denomination notes to cashCount
                } else if (g == 2000) {
                    twoThousandNotes.setCount(twoThousandNotes.getCount() + w);// Update the count of 2000 denomination note
                    cashCount += g * w; // Add the total value of 2000 denomination notes to cashCount
                } else if (g == 200) {
                    twoHundredNotes.setCount(twoHundredNotes.getCount() + w);// Update the count of 200 denomination note
                    cashCount += g * w; // Add the total value of 200 denomination notes to cashCount
                } else if (g == 100) {
                    hundredNotes.setCount(hundredNotes.getCount() + w);// Update the count of 100 denomination note
                    cashCount += g * w; // Add the total value of 100 denomination notes to cashCount
                }
                System.out.println("Total amount in Account: " + cashCount);
            } else {
                // Display the message to the user if the entered denomination is invalid
                System.out.println("Invalid denomination. Enter 2000 , 500, 200, or 100.");
            }
        }
        // Verify if the deposited amount equal to the entered amount
        if (cashCount == amount) {
            ATM atm = new ATM();//creating instance for the ATM class
            atm.setBalance(atm.getBalance() + cashCount); // Update the ATM's balance by adding the deposited cash count
            addNTransaction("ATM", "Deposit", cashCount, admin);// the deposit transaction will add in arrayList
            System.out.println("Deposit Successful. Total Deposited Amount: Rs." + cashCount);
            System.out.println("Amount deposited:");
        } else {
            System.out.println("Deposit Unsuccessful. The cash count does not match the specified amount.");
        }
    }


    public static void printAllUsers() {
        // Loop for the list of users and print userNAme
        for (User user : ATM.getUsers()) {
            System.out.println("User ID: " + user.getId());
        }
    }


    public static void viewaTransHis(Admin admin) {
        ArrayList<Transactions> viewaTransHis = admin.getTransactionHistory();
        //to check the transaction history contains anything or not..
        if (viewaTransHis.isEmpty()) {
            System.out.println("No transactions found.");
            return;// Exit if no transactions exist
        }

        System.out.println("Transaction History:");
        // Loop for transaction history and print the respected transaction details
        for (Transactions transaction : viewaTransHis) {
            System.out.println("Name: " + transaction.getName());//get the current username and display it
            System.out.println("Type: " + transaction.getTransType());//get the current user type(withdraw or deposit) and display it
            System.out.println("Amount: " + transaction.getTransAmount());//get the current user transaction amount and display it
        }
    }

    public static void addNTransaction(String name, String transType, double transAmount, Admin admin) {
        // Create a new transaction object with the given details
        Transactions transaction = new Transactions(name, transType, transAmount);
        admin.getTransactionHistory().add(transaction);// Adding the transaction to the admin's transaction history
    }
}


