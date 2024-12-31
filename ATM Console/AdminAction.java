import java.util.*;

public class AdminAction extends ATM {

    public static Admin admin() {
        System.out.println("Enter Admin Name:");
        String enteredName = scanner.nextLine();
        Admin validUser = null;
        for(Admin admin: ATM.getAdmins()) {
            if (admin.getId().equals(enteredName)) {
                validUser = admin;
                break;
            }
        }
        if (validUser==null){
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



    public static void enterChoice(Admin admin) {
        int choice = 0;
        while (choice!=7) {
            System.out.println("Enter Choice:\n\t1. Add User \n\t2. Delete User \n\t3. Deposit to ATM \n\t4. Balance in ATM \n\t5. View All Users  \n\t6. View Transactions \n\t7. Exit \n\t8. Finish Process");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.println("Redirecting to Add User Page...");
                    addUser(admin);
                    break;
                case 2:
                    System.out.println("Redirecting to Delete User Page...");
                    deleteUser();
                    break;
                case 3:
                    System.out.println("Redirecting to Deposit Page...");
                    depo(admin);
                    break;
                case 4:
                    System.out.println("Balance in ATM is..");
                    checkBalance();
                    break;
                case 5:
                    System.out.println("View All User names");
                    Admin.viewAllUsers();
                    //printAllUsers();
                    break;
                case 6:
                    System.out.println("Redirecting to View Transaction Page....");
                    viewaTransHis(admin);
                    break;
                case 7:
                    System.out.println("Exit Current Page...");
                    return;
                default:
                    System.out.println("Process Completed.....");
                    System.exit(8);
            }
        }
    }

    public static void addUser(Admin admin) {
        System.out.print("Enter new user name: ");
        String newName = scanner.nextLine();
        System.out.print("Enter new user PIN: ");
        String newPin = scanner.nextLine();
        System.out.print("Enter Initial Deposit Amount: ");
        double newBalance = Double.parseDouble(scanner.nextLine());
        User newUser = new User(newName, newPin, newBalance);
        ATM.getUsers().add(newUser);
        System.out.println("New user added successfully.");
        addNTransaction(newName, "Deposit", newBalance,admin);
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
        System.out.println("Your Balance is: Rs." + ATM.balance);
        System.out.println("Balance in notes:");
        System.out.println("Notes 500 - " + ATM.notesList.get(0).getCount());
        System.out.println("Notes 200 - " + ATM.notesList.get(2).getCount());
        System.out.println("Notes 2000 - " + ATM.notesList.get(1).getCount());
        System.out.println("Notes 100 - " + ATM.notesList.get(3).getCount());
    }


    public static void depo(Admin admin) {
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
        for (Notes note : notesList) {
            if (note.getNote() == 2000) {
                twoThousandNotes = note;
            }else if (note.getNote() == 500) {
                fiveHundredNotes = note;
            } else if (note.getNote() == 200) {
                twoHundredNotes = note;
            }  else if (note.getNote() == 100) {
                hundredNotes = note;
            }
        }


        System.out.println("Denominations:\n1. 2000 \n2. 500 \n3. 200 \n4. 100");

        while (cashCount < amount) {
            System.out.println("Enter your denominations:");
            int g = Integer.parseInt(scanner.nextLine());
            if (g == 500 || g == 2000 || g==200 || g == 100) {
                System.out.println("Number of " + g + ":");
                int w = Integer.parseInt(scanner.nextLine());
                if (g == 500) {
                    fiveHundredNotes.setCount(fiveHundredNotes.getCount() + w);
                    cashCount += g * w;
                }else if (g == 2000) {
                    twoThousandNotes.setCount(twoThousandNotes.getCount() + w);
                    cashCount += g * w;
                }else if (g == 200) {
                    twoHundredNotes.setCount(twoHundredNotes.getCount() + w);
                    cashCount += g * w;
                } else if (g == 100) {
                    hundredNotes.setCount(hundredNotes.getCount() + w);
                    cashCount += g * w;
                }
                System.out.println("Total amount in Account: " + cashCount);
            } else {
                System.out.println("Invalid denomination. Enter 2000 , 500, 200, or 100.");
            }
        }
        if (cashCount == amount) {
            ATM atm = new ATM();
            atm.setBalance(atm.getBalance() + cashCount);
            addNTransaction("ATM", "Deposit", cashCount, admin);
            System.out.println("Deposit Successful. Total Deposited Amount: Rs." + cashCount);
            System.out.println("Amount deposited:");
        } else {
            System.out.println("Deposit Unsuccessful. The cash count does not match the specified amount.");
        }
    }


    public static void printAllUsers() {
        for (User user : ATM.getUsers()) {
            System.out.println("User ID: " + user.getId());
        }
    }


    public static void viewaTransHis(Admin admin) {
        ArrayList<Transactions> viewaTransHis = admin.getTransactionHistory();
        if (viewaTransHis.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        System.out.println("Transaction History:");
        for (Transactions transaction : viewaTransHis) {
            System.out.println("Name: " + transaction.getName());
            System.out.println("Type: " + transaction.getTransType());
            System.out.println("Amount: " + transaction.getTransAmount());
        }
    }

    public static void addNTransaction(String name, String transType, double transAmount,Admin admin) {
        Transactions transaction = new Transactions(name, transType, transAmount);
        admin.getTransactionHistory().add(transaction);
        }

    }
