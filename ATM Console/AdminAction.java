import java.util.*;

public class AdminAction extends ATM {

    public static Admin admin() {
        int retry = 0;
        while (retry < 3) {
            System.out.println("Enter Admin Name:");
            String enteredName = scanner.nextLine();
            System.out.println("Enter the Pin:");
            String enteredPin = scanner.nextLine();
            Admin validAdmin = null;
            for (Admin admin : ATM.getAdmins()) {
                if (admin.getId().equals(enteredName) && admin.getPin().equals(enteredPin)) {
                    validAdmin = admin;
                    break;
                }
            }

            if (validAdmin != null) {
                return validAdmin;
            } else {
                retry++;
                System.out.println("No User Found.\n " + (3 - retry) + " attempts remaining...");
                if (retry == 3) {
                    System.out.println("Maximum login attempts exceeded.");
                    return null;
                }
            }
        }
        return null;
    }

    public static void enterChoice() {
        while (true) {
            System.out.println("Enter Choice:\n\t1. Add User \n\t2. Delete User \n\t3. Deposit to ATM \n\t4. Balance in ATM \n\t5. View All Users  \n\t6. View Transactions \n\t7. Exit \n\t8. Main Menu");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.println("Redirecting to Add User Page...");
                    addUser();
                    break;
                case 2:
                    System.out.println("Redirecting to Delete User Page...");
                    deleteUser();
                    break;
                case 3:
                    System.out.println("Redirecting to Deposit Page...");
                    depo();
                    break;
                case 4:
                    System.out.println("Balance in ATM is..");
                    checkBalance();
                    break;
                case 5:
                    System.out.println("View All User names");
                    printAllUsers();
                    break;
                case 6:
                    System.out.println("Redirecting to View Transaction Page....");
                    viewaTransHis();
                    break;
                case 7:
                    System.out.println("Exit...");
                    break;
                case 8:
                    System.out.println("Main Menu..");
                    ATM.start();
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option (1-6).");
                    System.exit(9);
            }
        }
    }

    public static void addUser() {
        System.out.print("Enter new user name: ");
        String newName = scanner.nextLine();
        System.out.print("Enter new user PIN: ");
        String newPin = scanner.nextLine();

        User newUser = new User(newName, newPin);
        ATM.getUsers().add(newUser);

        System.out.println("New user added successfully.");
        ATM.start();
    }

    public static void deleteUser() {
        System.out.print("Enter the username to delete: ");
        String name = scanner.nextLine();

        boolean userFound = false;
        for (User user : ATM.getUsers()) {
            if (user.getId().equals(name)) {
                ATM.getUsers().remove(user);
                System.out.println("User " + name + " deleted successfully.");
                userFound = true;
                break;
            }
        }
        if (!userFound) {
            System.out.println("User not found.");
        }
        ATM.start();
    }

    public static void checkBalance() {
        System.out.println("Your Balance is: Rs." + ATM.balance);  // Directly access ATM's balance
        System.out.println("Balance in notes:");
        System.out.println("Notes 500 - " + ATM.notesList.get(0).getCount());
        System.out.println("Notes 200 - " + ATM.notesList.get(1).getCount());
        System.out.println("Notes 100 - " + ATM.notesList.get(2).getCount());
    }


    public static void depo() {
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

        Notes fiveHundredNotes = new FiveHundred(500, 0);
        Notes twoHundredNotes = new TwoHundred(200, 0);
        Notes hundredNotes = new Hundred(100, 0);

        System.out.println("Denominations:\n1. 500 \n2. 200 \n3. 100");

        while (cashCount < amount) {
            System.out.println("Enter your denominations:");
            int g = Integer.parseInt(scanner.nextLine());
            if (g == 500 || g == 200 || g == 100) {
                System.out.println("Number of " + g + ":");
                int w = Integer.parseInt(scanner.nextLine());
                if (g == 500) {
                    fiveHundredNotes.setCount(fiveHundredNotes.getCount() + w);
                    cashCount += g * w;
                } else if (g == 200) {
                    twoHundredNotes.setCount(twoHundredNotes.getCount() + w);
                    cashCount += g * w;
                } else if (g == 100) {
                    hundredNotes.setCount(hundredNotes.getCount() + w);
                    cashCount += g * w;
                }
                System.out.println("Total amount in Account: " + cashCount);
            } else {
                System.out.println("Invalid denomination. Enter 500, 200, or 100.");
            }
        }

        if (cashCount == amount) {
            ATM atm = new ATM();
            Admin admin = new Admin();
            atm.setBalance(atm.getBalance() + cashCount);
            admin.addTransaction(admin.getId(), "Deposit", cashCount);
            System.out.println("Deposit Successful. Total Deposited Amount: Rs." + cashCount);
            System.out.println("Denominations deposited:");
            System.out.println("500 Notes: " + fiveHundredNotes.getCount());
            System.out.println("200 Notes: " + twoHundredNotes.getCount());
            System.out.println("100 Notes: " + hundredNotes.getCount());
        } else {
            System.out.println("Deposit Unsuccessful. The cash count does not match the specified amount.");
        }
    }


    public static void printAllUsers() {
        for (User user : ATM.getUsers()) {
            System.out.println("User ID: " + user.getId());
        }
    }


    public static void viewaTransHis() {
        Admin admin = new Admin();
        ArrayList<Transactions> transactionHistory = admin.getTransactionHistory();
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        System.out.println("Transaction History:");
        for (Transactions transaction : transactionHistory) {
            System.out.println("Name: " + transaction.getName());
            System.out.println("Type: " + transaction.getTransType());
            System.out.println("Amount: " + transaction.getTransAmount());
        }
    }
}