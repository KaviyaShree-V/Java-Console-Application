import ListOfNotes.FiveHundred;
import ListOfNotes.Hundred;
import ListOfNotes.TwoHundred;
import ListOfNotes.TwoThousand;
import Notes.*;
import java.util.*;

public class ATM {
    static Scanner scanner = new Scanner(System.in);//creating scanner to get input

    public static ArrayList<Account> getAccountUser() {
        return accountUser;
    }

    private static ArrayList<Account> accountUser = new ArrayList<>();
    //All arrayList is declared as static because the admins , users and the note list will be same in all ATM it doesn't differ for every ATM
    protected static Note<Notes> notesList = new Note<Notes>();//creating arraylist for storing note count and value
    //static ArrayList<Notes> withdrawalNotes = new ArrayList<>();
    static double balance = 0.0;//Initializing the ATM balance as 0.0

    static {//Initializing the default names which is preloaded or the static block is executed immediately and any data initialized in memory
        accountUser.add(new Admin("SRP", "2453"));
//        accountUser.add(new Admin("Mickey", "1234"));

        notesList.addNotes(new FiveHundred(500, 0){});
        notesList.addNotes(new TwoThousand(2000, 0){});
        notesList.addNotes(new TwoHundred(200, 0){});
        notesList.addNotes(new Hundred(100, 0){});
    }

    public static void start() {//Start method to login
        while (true) {
            // keeps the program to run continuously & the login options shows repeatedly until the admin or user chooses to exit
            System.out.println("Login: \n\t1. Admin Login \n\t2. User Login \n\t3. Exit");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {//switch case choices to login
                case 1:
                    System.out.println("Redirecting to Admin Login Page...");
                    //to validate the admin login which return the object(null or validAdmin) to avalidation
                    Account avalidation = AdminAction.admin(scanner);
                    if(avalidation!=null) {
                        aoptions((Admin) avalidation);//call the method aoptions with the return value of admin() in avalidation as a parameter
                    }
                    break;
                case 2:
                    System.out.println("Redirecting to User Login Page...");
                    //to validate the user login which return the object(null or validUser) to uvalidation
                    Account uvalidation = UserAction.user(scanner);
                    options((User) uvalidation);//call the method options with the return value of user() in uvalidation as a parameter
                    break;
                case 3:
                    System.out.println("Exit...");
                    //to exit from current view or process
                    System.exit(3);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    public static void options(User uvalidation) {
        // validation method for user login
        if (uvalidation != null) { // Check if user is valid
            System.out.println("Login Successful...");
                //iterates all elements in users array
                enterChoice(scanner, uvalidation);//call the static method enterchoice in the UserAction class
        } else {
            System.out.println("Sorry no Account is found!");
        }
    }

    public static void aoptions(Admin avalidation) {
        // validation method for admin login
        if (avalidation != null) {// Check if admin is valid
            System.out.println("Login Successful...");
                enterChoice(avalidation , scanner);//call the static method enterchoice in the AdminAction class
        } else {
            System.out.println("Sorry no Account is found!");
        }
    }



    public static void enterChoice(Admin admin ,Scanner scanner) {
        int choice = 0; // Initialize 'choice' variable to store the admin's menu choice.
        //Loop starts and continues & if choice is 7 the will be exited
        while (choice != 7) {
            System.out.println("Enter Choice:\n\t1. Add User \n\t2. Delete User \n\t3. Deposit to ATM \n\t4. Balance in ATM \n\t5. View All Users  \n\t6. View Transactions \n\t7. Specific Users Trans History \n\t8. Exit \n\t9. Finish Process");
            choice = Integer.parseInt(scanner.nextLine());//to get the choice from the admin to visit the admin required page

            //case for the admin choice using switch
            switch (choice) {
                case 1:
                    System.out.println("Redirecting to Add User Page...");
                    AdminAction.addUser(admin , scanner);// Call the method to addUser, by passing the current admin object
                    break;
                case 2:
                    System.out.println("Redirecting to Delete User Page...");
                    AdminAction.deleteUser(scanner);// Call the method to deleteUser,
                    break;
                case 3:
                    System.out.println("Redirecting to Deposit Page...");
                    AdminAction.depo(admin , scanner);// Call the method to depo, by passing the current admin object
                    break;
                case 4:
                    System.out.println("Balance in ATM is..");
                    AdminAction.checkBalance();// Call the method to checkBalance
                    break;
                case 5:
                    System.out.println("View All User names");
                    Admin.viewAllUsers();//call the static method to ViewAllUsers
                    //printAllUsers();
                    break;
                case 6:
                    System.out.println("Redirecting to View Transaction Page....");
                    AdminAction.viewaTransHis(admin);// Call the method to viewTransHis, passing the current admin object
                    break;
                case 7:
                    System.out.println("Redirecting to Specific User Transaction Page....");
                    AdminAction.specificUser(scanner);// Call the method to viewTransHis, passing the current scanner object
                    break;
                case 8:
                    System.out.println("Exit Current Page...");
                    return;// Exit the loop and return to the previous page
                default:
                    System.out.println("Process Completed.....");
                    System.exit(9);// // Exit the program if invalid choice
            }
        }
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
                    UserAction.depo(scanner, user);// Calling Method for deposit
                    break;
                case 2:
                    System.out.println("Redirecting to Withdraw Page...");
                    UserAction.withDraw(scanner, user);// Calling Method for withdraw
                    break;
                case 3:
                    System.out.println("Redirecting to Check Balance Page....");
                    UserAction.checkBalance( user);// Calling Method for checking balance
                    break;
                case 4:
                    System.out.println("Redirecting to Change Pin Page....");
                    UserAction.changePin(scanner, user);// Calling Method for changing pin
                    break;
                case 5:
                    System.out.println("Redirecting to View Transactions Page...");
                    UserAction.viewTransHis();// Calling Method for viewing current users transactions
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

    static Account findUserByUsername(String username) {
        // Method to find a user by their username

        for (Account account : accountUser) {
            // Loop through the list of users

            if (account.getName().equals(username)) {
                // Checks if the username matches

                return account;  // Returns the matched user
            }
        }

        return null;  // Returns null if no matching user is found
    }

    public static Note<Notes> getNotesList(){
        //method to get the note count from array
        return notesList;
    }

    //balance will change for every deposit and withdraw in ATM so it is non-static
    public double getBalance() {
        //method to get the balance in a ATM
        return balance;
    }

    public void setBalance(double balance) {
        // to set the balance in ATM when deposited
        ATM.balance = balance;//to update the current balance
    }
}