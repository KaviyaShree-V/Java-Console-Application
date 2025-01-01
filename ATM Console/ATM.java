import java.util.*;

public class ATM {
    static Scanner scanner = new Scanner(System.in);//creating scanner to get input
    //All arrayList is declared as static because the admins , users and the note list will be same in all ATM it doesn't differ for every ATM
    private static ArrayList<Admin> admins = new ArrayList<>();//creating arraylist for storing admin name and pins
    private static ArrayList<User> users = new ArrayList<>();//creating arraylist for storing user name and pins
    static ArrayList<Notes> notesList = new ArrayList<>();//creating arraylist for storing note count and value
    static ArrayList<Notes> withdrawalNotes = new ArrayList<>();//creating arrayList for storing withdraw note count in ATM
    static double balance = 0.0;//Initializing the ATM balance as 0.0

    static {//Initializing the default names which is preloaded or the static block is executed immediately and any data initialized in memory
        admins.add(new Admin("SRP", "2453"));
        admins.add(new Admin("Mickey", "1234"));

        notesList.add(new Notes(500, 0));
        notesList.add(new Notes(2000, 0));
        notesList.add(new Notes(200, 0));
        notesList.add(new Notes(100, 0));
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
                    Admin avalidation = AdminAction.admin();
                    aoptions(avalidation);//call the method aoptions with the return value of admin() in avalidation as a parameter
                    break;
                case 2:
                    System.out.println("Redirecting to User Login Page...");
                    //to validate the user login which return the object(null or validUser) to uvalidation
                    User uvalidation = UserAction.user(scanner);
                    options(uvalidation);//call the method options with the return value of user() in uvalidation as a parameter
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
            for (User user : users) {
                //iterates all elements in users array
                UserAction.enterChoice(scanner, user);//call the static method enterchoice in the UserAction class
                return;
            }
        } else {
            System.out.println("Sorry no Account is found!");
            return;
        }
    }

    public static void aoptions(Admin avalidation) {
        // validation method for admin login
        if (avalidation != null) {// Check if admin is valid
            System.out.println("Login Successful...");
            for (Admin admin : ATM.getAdmins()) {
                //iterates all elements in admins array
                AdminAction.enterChoice(admin);//call the static method enterchoice in the AdminAction class
                return;
            }
        } else {
            System.out.println("Sorry no Account is found!");
            return;
        }
    }
    //getAdmins() & getUsers() methods always stores and returns the admins and users, so it is declared as static
    public static ArrayList<Admin> getAdmins() {
        //method to get the admin name and pin list from array

        return admins;
    }

    public static ArrayList<User> getUsers() {
        //method to get the user name and pin list from array

        return users;
    }

    public static ArrayList<Notes> getwithdrawalNotes(){
        //method to get the user name and pin list from array
        return withdrawalNotes;
    }

    //balance will change for every deposit and withdraw in ATM so it is non-static
    public double getBalance() {
        //method to get the balance in a ATM
        return balance;
    }

    public void setBalance(double balance) {
        // to set the balance in ATM when deposited
        this.balance = balance;//to update the current balance
    }
}
