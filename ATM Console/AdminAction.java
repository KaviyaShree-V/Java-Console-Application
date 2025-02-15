import ListOfNotes.FiveHundred;
import ListOfNotes.Hundred;
import ListOfNotes.TwoHundred;
import ListOfNotes.TwoThousand;
import Notes.Notes;

import java.util.*;

public class AdminAction {
    //Admin action is inherited from ATM class which means it inherits all members from ATM class

    public static Account admin(Scanner scanner) {
        System.out.println("Enter Admin Name:");
        String enteredName = scanner.nextLine();//to get the admin name and store in the entered name
        System.out.println("Enter the Pin:");
        String enteredPin = scanner.nextLine();
        Admin validAdmin = null;//store the reference to a valid Admin object or variable to store the valid Admin - if found
        // Loop for list of admins to find a exact admin for the entered name
        for (Account admin : ATM.getAccountUser()) {
            if (admin instanceof Admin) {
                if (admin.getName().equals(enteredName) && admin.getPin().equals(enteredPin)) {
                    //to check admin id equals to enteredname
                    validAdmin = (Admin) admin;//reassigning the correct admin to the validAdmin
                    return validAdmin;
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
//            System.out.println("Enter the Pin:");
//            String enteredPin = scanner.nextLine();//to get the pin from the admin

                if (validAdmin.getName().equals(enteredName) && !validAdmin.getPin().equals(enteredPin)) {
                    //validates the adminPin is equal to the validate pin
                    return new Admin(null, null);//if correct it return the validAdmin
                } else {
                    retry++;//increment for attempts
                    System.out.println("Incorrect PIN. " + (3 - retry) + " attempts remaining...");
                }
            }
        }
        System.out.println("Maximum login attempts exceeded. Returning to login.");
        return null;//if name and pin is mismatched then it returns null
    }

    //admin add the user - so Adduser method in AdminAction
    public static void addUser(Admin admin ,Scanner scanner) {
        System.out.print("Enter new user name: ");
        String newName = scanner.nextLine();//to get the new username
        System.out.print("Enter new user PIN: ");
        String newPin = scanner.nextLine();//to get the new user pin
        System.out.print("Enter Initial Deposit Amount: ");
        double newBalance = Double.parseDouble(scanner.nextLine());//to get the user's initial deposit amount
        // Create a new User object with the details
        User newUser = new User(newName, newPin, newBalance);
        ATM.getAccountUser().add(newUser);// Add the new user to the ATM's user list
        System.out.println("New user added successfully.");
        admin.getTransactionHistory().add(new Transactions(newName, "Deposit", newBalance));

//        addNTransaction(newName, "Deposit", newBalance);// Add a transaction for the new user's initial deposit
    }

    //Admin Action has delete user = deleteUser method
    public static void deleteUser(Scanner scanner) {
        System.out.print("Enter the username to delete: ");
        String name = scanner.nextLine();//to get the username to remove

        boolean userFound = false;//to check if user is found
        for (Account user : ATM.getAccountUser()) {
            if (user.getName().equals(name)) {//if admin entered name is equal to the users name in arrayList
                ATM.getAccountUser().remove(user);// Remove user from the ATM's user list
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
        System.out.println("Notes 500 - " + ATM.getNotesList().getNotes(0).toString());
        System.out.println("Notes 200 - " + ATM.getNotesList().getNotes(2).toString());
        System.out.println("Notes 2000 - " + ATM.getNotesList().getNotes(1).toString());
        System.out.println("Notes 100 - " + ATM.getNotesList().getNotes(3).toString());
    }


    public static void depo(Admin admin , Scanner scanner) {
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
        for (Notes note : ATM.getNotesList().getALLNotes()) {
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
            admin.getTransactionHistory().add(new Transactions(admin.getName(), "Deposit", amount));            System.out.println("Deposit Successful. Total Deposited Amount: Rs." + cashCount);
            System.out.println("Amount deposited:");
        } else {
            System.out.println("Deposit Unsuccessful. The cash count does not match the specified amount.");
        }
    }


    public static void specificUser(Scanner scanner) {
        System.out.print("Enter Username to view transactions: ");
        String username = scanner.nextLine(); // Get the username from the admin

        // Find the user account with the given username
        Account user = ATM.findUserByUsername(username);

        // Check if the user exists and is an instance of User
        if (user != null && user instanceof User) {
            User specificUser = (User) user; // Cast the account to User
            System.out.println("Transaction History for User: " + specificUser.getName());

            // Get the user's transaction history
            ArrayList<Transactions> transactions = specificUser.getTransactionHistory();

            // Display ttransactions if any exist
            if (transactions.isEmpty()) {
                System.out.println("No transactions found for this user.");
            } else {
                boolean hasUserTransactions = false; // Flag to check if there are any ttransactions by the user
                for (Transactions transaction : transactions) {
                    // Display only ttransactions performed by this user
                    if (transaction.getName().equals(specificUser.getName())) {
                        System.out.println("Performed By: " + transaction.getName());
                        System.out.println("Type: " + transaction.getTransType());
                        System.out.println("Amount: " + transaction.getTransAmount());
                        System.out.println("-------------------------------");
                        hasUserTransactions = true;
                    }
                }

                // If no ttransactions were performed by the user, print a message
                if (!hasUserTransactions) {
                    System.out.println("No ttransactions performed by this user.");
                }
            }
        } else {
            // User not found or invalid type
            System.out.println("User not found.");
        }
    }
    public static void printAllUsers() {
        // Loop for the list of users and print userNAme
        for (Account account : ATM.getAccountUser()) {
            if (account instanceof User) {
                User user = (User) account;
                System.out.println("User ID: " + user.getName());
            }
        }
    }


    public static void viewaTransHis(Admin admin) {
        //ArrayList<Transactions> transactions = admin.getTransactionHistory();
         for (Account ac : ATM.getAccountUser()) {
            if (ac instanceof Admin) {
                //to check the transaction history contains anything or not..
                if (ac.getTransactionHistory().isEmpty()) {
                    System.out.println("No transactions found.");
                    return;// Exit if no transactions exist
                } else {
                    System.out.println("Transaction History:");
                    // Loop for transaction history and print the respected transaction details
                    for (Transactions transaction : ac.getTransactionHistory()) {
                        System.out.println("Name: " + transaction.getName());//get the current username and display it
                        System.out.println("Type: " + transaction.getTransType());//get the current user type(withdraw or deposit) and display it
                        System.out.println("Amount: " + transaction.getTransAmount());                        //get the current user transaction amount and display it
                    }
                }
            }
        }
    }

//    public static void addNTransaction(String name, String transType, double transAmount) {
//        // Create a new transaction object with the given details
//        Transactions transaction = new Transactions(name, transType, transAmount);
//        Admin.getTransactionHistory().add(transaction);// Adding the transaction to the admin's transaction history
//    }
}


