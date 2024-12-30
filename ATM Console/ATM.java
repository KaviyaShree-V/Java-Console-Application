import java.util.ArrayList;
import java.util.Scanner;

public class ATM {
    static Scanner scanner = new Scanner(System.in);//creating scanner to get input
    private static ArrayList<Admin> admins = new ArrayList<>();//creating arraylist for storing admin name and pins
    private static ArrayList<User> users = new ArrayList<>();//creating arraylist for storing admin name and pins
    static ArrayList<Notes> notesList = new ArrayList<>();
    static double balance = 0.0;//Initializing the ATM balance as 10000

    static {//Initializing the default names
        admins.add(new Admin("SRP", "2453"));
        admins.add(new Admin("Mickey", "1234"));

        notesList.add(new Notes(500, 0));
        notesList.add(new Notes(2000, 0));
        notesList.add(new Notes(200, 0));
        notesList.add(new Notes(100, 0));
    }

    public static void start() {//Start method to login
        while (true) {
            System.out.println("Login: \n\t1. Admin Login \n\t2. User Login \n\t3. Exit");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {//switch case choices to login
                case 1:
                    System.out.println("Redirecting to Admin Login Page...");
                    Admin avalidation = AdminAction.admin();
                    aoptions(avalidation);
                    break;
                case 2:
                    System.out.println("Redirecting to User Login Page...");
                    User uvalidation = UserAction.user(scanner);//Calling the UserAction class with user() method
                    options(uvalidation);//validating the admin options method for validation
                    break;
                case 3:
                    System.out.println("Exit...");
                    System.exit(3);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    public static void options(User uvalidation) {
        if (uvalidation != null) { // Check if user is valid
            System.out.println("Login Successful...");
            for (User user : users) {
                UserAction.enterChoice(scanner, user);
                return;
            }
        } else {
            System.out.println("Sorry no Account is found!");
            start();
        }
    }

    public static void aoptions(Admin avalidation) {
        if (avalidation != null) {
            System.out.println("Login Successful...");
            for (Admin admin : ATM.getAdmins()) {
                AdminAction.enterChoice(admin);
                return;
            }
        } else {
            System.out.println("Sorry no Account is found!");
            start();
        }
    }

    public static ArrayList<Admin> getAdmins() {

        return admins;
    }

    public static ArrayList<User> getUsers() {

        return users;
    }
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
