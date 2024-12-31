import java.util.*;

public class UserAction {

    public static User user(Scanner scanner) {
        System.out.println("Enter User Name:");
        String enteredName = scanner.nextLine();
        User validUser = null;
        for (User user : ATM.getUsers()) {
            if (user.getId().equals(enteredName)) {
                validUser = user;
                break;
            }
        }
        if (validUser == null) {
            System.out.println("User not found. Returning to login.");
            return null;
        }
        int retry = 0;
        while (retry < 3) {
            System.out.println("Enter the Pin:");
            String enteredPin = scanner.nextLine();

            if (validUser.getPin().equals(enteredPin)) {
                return validUser;
            } else {
                retry++;
                System.out.println("Incorrect PIN. " + (3 - retry) + " attempts remaining...");
            }
        }

        System.out.println("Maximum login attempts exceeded. Returning to login.");
        return null;
    }

    public static void enterChoice(Scanner scanner, User user) {
        int choice = 0;
        while (choice!=7)  {
            System.out.println("Enter Choice:\n\t1. Deposit \n\t2. Withdraw \n\t3. Check Balance \n\t4. Change pin \n\t5. View Transaction \n\t6. Exit \n\t7. Finish Process");
            choice = Integer.parseInt(scanner.nextLine());
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Redirecting to Deposit Page...");
                    depo(scanner, user);
                    break;
                case 2:
                    System.out.println("Redirecting to Withdraw Page...");
                    withDraw(scanner, user);
                    break;
                case 3:
                    System.out.println("Redirecting to Check Balance Page....");
                    checkBalance(scanner, user);
                    break;
                case 4:
                    System.out.println("Redirecting to Change Pin Page....");
                    changePin(scanner, user);
                    break;
                case 5:
                    System.out.println("Redirecting to View Transactions Page...");
                    viewTransHis(scanner, user);
                    break;
                case 6:
                    System.out.println("Exit from current Page...");
                    return;
                default:
                    System.out.println("Invalid choice.Exiting....");
                    System.exit(7);
            }
        }
    }

    public static void checkBalance(Scanner scanner, User user) {

        System.out.println("Your Balance is: Rs." + user.getBalance());

    }


    public static void depo(Scanner scanner, User user) {
        System.out.println("Enter the amount to deposit in your Account:");
        double amount;
        while (true) {
            amount = Double.parseDouble(scanner.nextLine());
            if (amount > 0 && amount % 100 == 0) {
                break;
            } else {
                System.out.println("Enter a valid amount to deposit:");
            }
        }

        System.out.println("The Total Amount=" + amount);
        double cashCount = 0.0;
        Notes twoThousandNotes = new TwoThousand(2000, 0);
        Notes fiveHundredNotes = new FiveHundred(500, 0);
        Notes twoHundredNotes = new TwoHundred(200, 0);
        Notes hundredNotes = new Hundred(100, 0);

        System.out.println("Denominations:\n1. 2000 \n2. 500 \n3. 200 \n3. 100");

        while (cashCount < amount) {
            System.out.println("Enter your denominations:");
            int g = Integer.parseInt(scanner.nextLine());
            if (g == 500 || g == 200 || g==2000|| g == 100) {
                System.out.println("Number of " + g + ":");
                int w = Integer.parseInt(scanner.nextLine());
                if (g == 500) {
                    fiveHundredNotes.setCount(fiveHundredNotes.getCount() + w);
                    cashCount += g * w;
                } else if (g == 2000) {
                    twoThousandNotes.setCount(twoThousandNotes.getCount() + w);
                    cashCount += g * w;
                } else if (g == 200) {
                    twoHundredNotes.setCount(twoHundredNotes.getCount() + w);
                    cashCount += g * w;
                }else if (g == 100) {
                    hundredNotes.setCount(hundredNotes.getCount() + w);
                    cashCount += g * w;
                }
                System.out.println("Total amount in Account: " + cashCount);
            } else {
                System.out.println("Invalid denomination. Enter 2000 ,500, 200, or 100.");
            }
        }

        if (cashCount == amount) {
            user.setBalance(user.getBalance() + cashCount);
            addTransaction(user.getId(), "Deposit", cashCount);
            System.out.println("Deposit Successful. Total Deposited Amount: Rs." + cashCount);
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

    public static void withDraw(Scanner scanner, User user) {
        System.out.println("Enter the amount to withdraw:");
        double amount = Double.parseDouble(scanner.nextLine());
        double userBalance = user.getBalance();

        if (amount <= 0 || amount > userBalance || amount % 100 != 0) {
            if (amount <= 0) {
                System.out.println("Invalid amount. Please enter a positive number.");
            } else if (amount > userBalance) {
                System.out.println("Insufficient balance. Available balance: Rs." + userBalance);
            } else {
                System.out.println("Invalid amount.");
            }
            return;
        }

        ArrayList<Notes> withdrawalNotes = new ArrayList<>();
        double remainingAmount = amount;
        for (Notes note : ATM.notesList) {
            int noteValue = note.getNote();
            if (noteValue == 500 || noteValue == 200 ||noteValue == 2000 || noteValue == 100) {
                int notesToDispense = (int) Math.min(remainingAmount / noteValue, note.getCount());
                if (notesToDispense > 0) {
                    note.setCount(note.getCount() - notesToDispense);
                    withdrawalNotes.add(new Notes(noteValue, notesToDispense));
                    remainingAmount -= noteValue * notesToDispense;
                    System.out.println("Rs. " +noteValue + " = " + notesToDispense +"\t notes Dispensed");
                } else {
                    System.out.println("Rs." + noteValue + " notes are not dispensed");
                }
            } else {
                System.out.println("Skipping notes of value: Rs." + noteValue);
            }
        }
        if (remainingAmount == 0) {
            userBalance -= amount;
            user.setBalance(userBalance);

            ATM.balance -= amount;
            System.out.println("Withdrawal Successful...\n Amount: Rs." + amount);
            User.getTransactionHistory().add(new Transactions(user.getId(), "Withdraw", amount));
            System.out.println("Your Balance : Rs." + userBalance);
        } else {
            System.out.println("Not enough cash in the ATM for this withdrawal.");
        }
    }


    public static void changePin(Scanner scanner, User user) {
        System.out.println("Enter current pin");
        String currentPin = scanner.nextLine();
        if (currentPin.equals(user.getPin())) {
            System.out.println("Enter new PIN:");
            String newPin = scanner.nextLine();
            System.out.println("Confirm new Pin:");
            String confirmPin = scanner.nextLine();
            if (confirmPin.equals(newPin)) {
                user.changePin(newPin);
                System.out.println("PIN changed successfully.");
            }
        } else {
            System.out.println("Pin doesn't valid. Retry");
        }
    }

    public static void viewTransHis(Scanner scanner, User user) {
        ArrayList<Transactions> transactionHistory = user.getTransactionHistory();
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        System.out.println("Transaction History:\n");
        for (Transactions transaction : transactionHistory) {
            System.out.println("Name: " + transaction.getName());
            System.out.println("Type: " + transaction.getTransType());
            System.out.println("Amount: " + transaction.getTransAmount());
            System.out.println();
        }
    }
    public static void addTransaction(String name, String transType, double transAmount) {
        Transactions transaction = new Transactions(name, transType, transAmount);
        User.getTransactionHistory().add(transaction);
    }
}
