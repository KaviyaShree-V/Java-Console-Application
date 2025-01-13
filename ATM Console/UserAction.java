import ListOfNotes.FiveHundred;
import ListOfNotes.Hundred;
import ListOfNotes.TwoHundred;
import ListOfNotes.TwoThousand;
import Notes.Notes;

import java.util.*;

public class UserAction {
    public static Account user(Scanner scanner) {
        System.out.println("Enter User Name:");
        String enteredName = scanner.nextLine();
        User validUser = null;//variable to store the valid User if found
        // Loop for list of users to find a exact user for the entered name
        for (Account user : ATM.getAccountUser()) {
            if (user.getName().equals(enteredName)) {//to check user id equals to enteredname
                validUser = (User) user;//reassigning the correct user to the validUser
            }
        }
        // Check if no user was found with the entered name
        if (validUser == null) {
            System.out.println("User not found. Returning to login.");
            return null;
        }
        int retry = 0;//variable to check the attempts for user pin
        //to make the user only for 3 login attempts
        while (retry < 3) {
            System.out.println("Enter the Pin:");
            String enteredPin = scanner.nextLine();//get the pin from the user

            if (validUser.getPin().equals(enteredPin)) {//check the user pin is equal to the entered pin
                return validUser;
            } else {
                retry++;//increment for attempts
                System.out.println("Incorrect PIN. " + (3 - retry) + " attempts remaining...");
            }
        }

        System.out.println("Maximum login attempts exceeded. Returning to login.");
        return null;//if name and pin is mismatched then it returns null
    }


    public static void checkBalance(Scanner scanner, User user) {
        // to display the current balance of the user
        System.out.println("Your Balance is: Rs." + user.getBalance());

    }


    public static void depo(Scanner scanner, User user) {
        System.out.println("Enter the amount to deposit in your Account:");
        double amount;
        // Check for user enter amount can be deposited or not
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

        System.out.println("Denominations:\n1. 2000 \n2. 500 \n3. 200 \n3. 100");
        // Validate and calculate the cash count based on user input
        while (cashCount < amount) {
            System.out.println("Enter your denominations:");
            int g = Integer.parseInt(scanner.nextLine());//to get denominations from user and store in g
            if (g == 500 || g == 200 || g==2000|| g == 100) {
                System.out.println("Number of " + g + ":");
                int w = Integer.parseInt(scanner.nextLine());//to get how many note count for given denomination and store it in w
                //to validating and adding given denominations and count
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
        // Validate if the entered total amount equals the required amount
        if (cashCount == amount) {
            user.setBalance(user.getBalance() + cashCount);
            user.getTransactionHistory().add(new Transactions(user.getName(), "Deposit", cashCount));
            System.out.println("Deposit Successful. Total Deposited Amount: Rs." + cashCount);
            //to display the notes count for entered denominations
            System.out.println("Denominations deposited:");
            System.out.println("2000 Notes.Notes: " + twoThousandNotes.getCount());
            System.out.println("500 Notes.Notes: " + fiveHundredNotes.getCount());
            System.out.println("200 Notes.Notes: " + twoHundredNotes.getCount());
            System.out.println("100 Notes.Notes: " + hundredNotes.getCount());
            System.out.println();
        } else {
            System.out.println("Deposit Unsuccessful. The cash count does not match the specified amount.");
        }
    }


    public static void withDraw(Scanner scanner, User user) {
        System.out.println("Enter the amount to withdraw:");
        double amount = Double.parseDouble(scanner.nextLine()); // Get the amount to withdraw
        double userBalance = user.getBalance(); // Get the balance of a user

       // Validate the withdrawal amount
        if (amount <= 0 || amount > userBalance || amount % 100 != 0) {
            if (amount <= 0) {
                System.out.println("Invalid amount. Please enter a positive number.");
            } else if (amount > userBalance) {
                System.out.println("Insufficient balance. Available balance: Rs." + userBalance);
            } else {
                System.out.println("Invalid amount...");
            }
            return;
        }

        double remainingAmount = amount; // Initialize remaining amount

        // Variables to track notes dispensed for each denomination
        int count2000 = 0, count500=0 ,count200 = 0,count100 = 0;

        for (Notes note : ATM.getNotesList()) {
            int noteValue = note.getNote(); // Get the note value
            int dispenseNotes = 0; // to check the withdraw notes

            //validate the given dispense notes and give the amount which note should be withdraw
            if (noteValue == 2000 && remainingAmount >= 2000) {
                dispenseNotes = (int) Math.min(remainingAmount / 2000, note.getCount());
                count2000 += dispenseNotes;
            } else if (noteValue == 500 && remainingAmount >= 500) {
                dispenseNotes = (int) Math.min(remainingAmount / 500, note.getCount());
                count500 += dispenseNotes;
            } else if (noteValue == 200 && remainingAmount >= 200) {
                dispenseNotes = (int) Math.min(remainingAmount / 200, note.getCount());
                count200 += dispenseNotes;
            } else if (noteValue == 100 && remainingAmount >= 100) {
                dispenseNotes = (int) Math.min(remainingAmount / 100, note.getCount());
                count100 += dispenseNotes;
            }

            if (dispenseNotes > 0) {
                note.setCount(note.getCount() - dispenseNotes); // Update note count in ATM
                remainingAmount -= noteValue * dispenseNotes; // Update remaining amount
            }
        }

        if (remainingAmount == 0) {
            userBalance -= amount; //balance from user balance
            user.setBalance(userBalance); // to update the user balance
            ATM.balance -= amount; // balance in atm
            System.out.println("Withdrawal Successful...\n Amount: Rs." + amount);
            //to display which notes are dispensed
            if (count2000 > 0) {
                System.out.println("Rs. 2000 = " + count2000 + " notes dispensed");
            }
            if (count500 > 0) {
                System.out.println("Rs. 500 = " + count500 + " notes dispensed");
            }
            if (count200 > 0) {
                System.out.println("Rs. 200 = " + count200 + " notes dispensed");
            }
            if (count100 > 0) {
                System.out.println("Rs. 100 = " + count100 + " notes dispensed");
            }

            user.getTransactionHistory().add(new Transactions(user.getName(), "Withdraw", amount)); // Add to transaction history
            System.out.println("Your Balance : Rs." + userBalance);
        } else {
            System.out.println("Not enough cash in the ATM for this withdrawal.");
        }
    }



    public static void changePin(Scanner scanner, User user) {
        System.out.println("Enter current pin");
        String currentPin = scanner.nextLine();//to get the current pin
        if (currentPin.equals(user.getPin())) {
            System.out.println("Enter new PIN:");//to get the new pin
            String newPin = scanner.nextLine();
            System.out.println("Confirm new Pin:");
            String confirmPin = scanner.nextLine();//to get and confirm the new pin
            if (confirmPin.equals(newPin)) {//if confirm pin match or equal to the new pin
                user.changePin(newPin);//pin will be changed for the current user
                System.out.println("PIN changed successfully.");
            }
        } else {
            System.out.println("Pin doesn't valid. Retry");
        }
    }

    public static void viewTransHis() {
//        //ArrayList<Transactions> transactionHistory = user.getTransactionHistory();
//        if (User.getTransactionHistory().isEmpty()) {//to check if the current user transaction is empty
//            System.out.println("No transactions found.");
//            return;
//        }
//
//        System.out.println("Transaction History:\n");
//        // Loop for transaction history and print the respected transaction details
//        for (Transactions transaction : User.getTransactionHistory()) {
//            System.out.println("Name: " + transaction.getName());//get the current username and display it
//            System.out.println("Type: " + transaction.getTransType());//get the current user type(withdraw or deposit) and display it
//            System.out.println("Amount: " + transaction.getTransAmount());//get the current user transaction amount and display it
//        }
        for (Account ac : ATM.getAccountUser()) {
            if (ac instanceof User) {
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
                        System.out.println("Amount: " + transaction.getTransAmount());//get the current user transaction amount and display it
                    }
                }
            }
        }
    }
    public static void addTransaction(String name, String transType, double transAmount,Account account) {
        // Create a new transaction object with the given details
        Transactions transaction = new Transactions(name, transType, transAmount);
        account.getTransactionHistory().add(transaction);// Adding the transaction to the admin's transaction history
    }
}
