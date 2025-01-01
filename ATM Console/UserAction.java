import java.util.*;

public class UserAction {

    public static User user(Scanner scanner) {
        System.out.println("Enter User Name:");
        String enteredName = scanner.nextLine();
        User validUser = null;//variable to store the valid User if found
        // Loop for list of users to find a exact user for the entered name
        for (User user : ATM.getUsers()) {
            if (user.getId().equals(enteredName)) {//to check user id equals to enteredname
                validUser = user;//reassigning the correct user to the validUser
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

    public static void enterChoice(Scanner scanner, User user) {
        int choice = 0;// Initialize choice variable to store the user's menu choice.
        //Loop starts and continues & if choice is 7 the will be exited
        while (choice!=7)  {
            System.out.println("Enter Choice:\n\t1. Deposit \n\t2. Withdraw \n\t3. Check Balance \n\t4. Change pin \n\t5. View Transaction \n\t6. Exit \n\t7. Finish Process");
            choice = Integer.parseInt(scanner.nextLine());//to get the choice from the admin to visit the admin required page
            //scanner.nextLine();

            //case for the admin choice using switch
            switch (choice) {
                case 1:
                    System.out.println("Redirecting to Deposit Page...");
                    depo(scanner, user);// Calling Method for deposit
                    break;
                case 2:
                    System.out.println("Redirecting to Withdraw Page...");
                    withDraw(scanner, user);// Calling Method for withdraw
                    break;
                case 3:
                    System.out.println("Redirecting to Check Balance Page....");
                    checkBalance(scanner, user);// Calling Method for checking balance
                    break;
                case 4:
                    System.out.println("Redirecting to Change Pin Page....");
                    changePin(scanner, user);// Calling Method for changing pin
                    break;
                case 5:
                    System.out.println("Redirecting to View Transactions Page...");
                    viewTransHis(scanner, user);// Calling Method for viewing current users transactions
                    break;
                case 6:
                    System.out.println("Exit from current Page...");
                    return;// Exit the loop and return to the previous page
                default:
                    System.out.println("Invalid choice.Exiting....");
                    System.exit(7);// Exit the program if invalid choice
            }
        }
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
            addTransaction(user.getId(), "Deposit", cashCount);
            System.out.println("Deposit Successful. Total Deposited Amount: Rs." + cashCount);
            //to display the notes count for entered denominations
            System.out.println("Denominations deposited:");
            System.out.println("2000 Notes: " + twoThousandNotes.getCount());
            System.out.println("500 Notes: " + fiveHundredNotes.getCount());
            System.out.println("200 Notes: " + twoHundredNotes.getCount());
            System.out.println("100 Notes: " + hundredNotes.getCount());
            System.out.println();
        } else {
            System.out.println("Deposit Unsuccessful. The cash count does not match the specified amount.");
        }
    }

//    public static void withDraw(Scanner scanner, User user) {
//        System.out.println("Enter the amount to withdraw:");
//        double amount = Double.parseDouble(scanner.nextLine());//enter the amount to withdraw
//        double userBalance = user.getBalance();//getting the user current balance
//
//        if (amount <= 0 || amount > userBalance || amount % 100 != 0) {//condition for validating the withdraw amount
//            if (amount <= 0) {//if given amount is negative or zero
//                System.out.println("Invalid amount. Please enter a positive number.");
//            } else if (amount > userBalance) {//if given withdraw amount is greater than current users balance
//                System.out.println("Insufficient balance. Available balance: Rs." + userBalance);
//            } else {
//                System.out.println("Invalid amount try Again.");
//            }
//            return;
//        }
//
//        double remainingAmount = amount;// Initialize remaining amount to withdraw
//        double totalATMBalance = 0;// field to store the ATM balance
//        // Calculating the total balance available in the ATM
//        for (Notes note : ATM.notesList) {
//            totalATMBalance += note.getNote() * note.getCount();//add the note value and count in ATM
//        }
//
//        if (totalATMBalance < amount) {//if ATM does not have required amount
//            System.out.println("ATM does not have enough cash for this withdrawal.");
//            return;
//        }
//        // Notes will be dispensed for the required amount
//        for (Notes note : ATM.notesList) {
//            int noteValue = note.getNote(); // to get the note value
//            if (noteValue == 500 || noteValue == 200 || noteValue == 2000 || noteValue == 100) { // validate the note value
//                int notesToDispense = (int) Math.min(remainingAmount / noteValue, note.getCount()); // calculate how many notes to dispense
//                if (notesToDispense > 0) {
//                    note.setCount(note.getCount() - notesToDispense); // Update the count of notes in the ATM
//
//                    boolean noteExists = false; // Check if the note has already been stored as dispensed
//                    for (Notes dispensedNote : ATM.getNotesLists()) { // Loop through dispensed notes
//                        if (dispensedNote.getNote() == noteValue) { // When the note exists, update the count
//                            dispensedNote.setCount(dispensedNote.getCount() + notesToDispense);
//                            noteExists = true;
//                            break;
//                        }
//                    }
//                    if (!noteExists) { // If the note does not already exist in the dispensed list
//                        ATM.getNotesLists().add(new Notes(noteValue, notesToDispense));
//                    }
//
//                    remainingAmount -= noteValue * notesToDispense; // Update the remaining amount to dispense
//                    System.out.println("Rs. " + noteValue + " = " + notesToDispense + " notes Dispensed");
//                    User.getTransactionHistory().add(new Transactions(user.getId(), "Withdraw", amount));//to store the transaction history of a user
//                }
//            } else {
//                System.out.println("Skipping notes of value: Rs." + noteValue);
//            }
//        }
//
//        if (remainingAmount > 0) {
//            System.out.println("Not enough suitable notes in the ATM for this withdrawal. Rolling back transaction.");
//
//            for (Notes dispensedNote : ATM.getNotesLists()) { // store dispensed notes to ATM
//                for (Notes note : ATM.notesList) {
//                    if (note.getNote() == dispensedNote.getNote()) { // Check if the note matches the ATM note
//                        note.setCount(note.getCount() + dispensedNote.getCount()); // store the count of a note
//                    }
//                }
//            }
//            ATM.getNotesLists().clear(); // Clear dispensed notes list
//        }
//    }

    public static void withDraw(Scanner scanner, User user) {
        System.out.println("Enter the amount to withdraw:");
        double amount = Double.parseDouble(scanner.nextLine());//the get the amount to withdraw
        double userBalance = user.getBalance();//to get the balance of a user

        if (amount <= 0 || amount > userBalance || amount % 100 != 0) {//to check the given withdraw amount can be withdraw or not
            if (amount <= 0) {
                System.out.println("Invalid amount. Please enter a positive number.");
            } else if (amount > userBalance) {
                System.out.println("Insufficient balance. Available balance: Rs." + userBalance);
            } else {
                System.out.println("Invalid amount.");
            }
            return;
        }

        double remainingAmount = amount;//assigning amount to remaining amount for withdraw
        //note will be dispensed for the required amount from the notesList
        for (Notes note : ATM.notesList) {
            int noteValue = note.getNote();//get the note value from notesList
            if (noteValue == 500 || noteValue == 200 ||noteValue == 2000 || noteValue == 100) {//check which note value is given for denominations
                int notesToDispense = (int) Math.min(remainingAmount / noteValue, note.getCount());//dispensing method using math.min method
                if (notesToDispense > 0) {
                    note.setCount(note.getCount() - notesToDispense);//to set the note count by reducing the note count with dispensing notes
                    ATM.getwithdrawalNotes().add(new Notes(noteValue, notesToDispense));//adding withdraw notes to the Notes array list
                    remainingAmount -= noteValue * notesToDispense;//balance amount to withdraw
                    System.out.println("Rs. " +noteValue + " = " + notesToDispense +"\t notes Dispensed");
                } else {
                    System.out.println("Rs." + noteValue + " notes are not dispensed");
                }
            } else {
                System.out.println("Skipping notes of value: Rs." + noteValue);
            }
        }
        if (remainingAmount == 0) {//when amount is completely dispensed
            userBalance -= amount;//users balance should be reduced
            user.setBalance(userBalance);//to update the user balance

            ATM.balance -= amount;//to reduce the ATM balance from users withdraw amount
            System.out.println("Withdrawal Successful...\n Amount: Rs." + amount);
            User.getTransactionHistory().add(new Transactions(user.getId(), "Withdraw", amount));//withdraw transaction history of a user
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

    public static void viewTransHis(Scanner scanner, User user) {
        //ArrayList<Transactions> transactionHistory = user.getTransactionHistory();
        if (User.getTransactionHistory().isEmpty()) {//to check if the current user transaction is empty
            System.out.println("No transactions found.");
            return;
        }

        System.out.println("Transaction History:\n");
        // Loop for transaction history and print the respected transaction details
        for (Transactions transaction : User.getTransactionHistory()) {
            System.out.println("Name: " + transaction.getName());//get the current username and display it
            System.out.println("Type: " + transaction.getTransType());//get the current user type(withdraw or deposit) and display it
            System.out.println("Amount: " + transaction.getTransAmount());//get the current user transaction amount and display it
        }
    }
    public static void addTransaction(String name, String transType, double transAmount) {
        // Create a new transaction object with the given details
        Transactions transaction = new Transactions(name, transType, transAmount);
        User.getTransactionHistory().add(transaction);// Adding the transaction to the admin's transaction history
    }
}
