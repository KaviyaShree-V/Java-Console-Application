import java.util.ArrayList;
import java.util.Scanner;

public class ATM {
    static Scanner scanner = new Scanner(System.in);//creating scanner to get input
    private static ArrayList<Admin> admins = new ArrayList<>();//creating arraylist for storing admin name and pins
    private static ArrayList<User> users = new ArrayList<>();//creating arraylist for storing admin name and pins
    static ArrayList<Notes> notesList = new ArrayList<>();
    static double balance = 10000.0;//Initializing the ATM balance as 10000
    private static int counts500 = 10;//Declaring the cash count for notes 500
    private static int counts200= 20;//Declaring the cash count for notes 200
    private static int counts100=10;//Declaring the cash count for notes 100

    static {//Initializing the default names
        admins.add(new Admin("SRP", "2453"));
        admins.add(new Admin("Mickey", "1234"));

        users.add(new User("kpr", "1243"));
        users.add(new User("uss", "1223"));
        users.add(new User("blue", "1232"));

        notesList.add(new Notes(500, 10));
        notesList.add(new Notes(200, 20));
        notesList.add(new Notes(100, 10));
    }

    public static void start() {//Start mathod to login
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
        if (avalidation != null) { // Check if user is valid
            System.out.println("Login Successful...");
            for (Admin admin : ATM.getAdmins()) {
                AdminAction.enterChoice();
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

//    public static ArrayList<Notes> getNotesList() {
//        return notesList;
//    }

//    public static void setNotesList(ArrayList<Notes> notesList) {
//        ATM.notesList = notesList;
//    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getCount500() {
        return counts500;
    }

    public void setCount500(int counts500) {
        this.counts500 = counts500;
    }

    public int getCount200() {
        return counts200;
    }

    public void setCount200(int counts200) {
        this.counts200 = counts200;
    }

    public int getCount100() {
        return counts100;
    }

    public void setCount100(int counts100) {
        this.counts100 = counts100;
    }

//    public static void withdraw(int amount) {
//        int withdraw500 = amount / 500;
//        int withdraw200 = (amount % 500) / 200;
//        int withdraw100 = (amount % 500 % 200) / 100;
//
//        if (withdraw500 <= count500 && withdraw200 <= count200 && withdraw100 <= count100) {
//            count500 -= withdraw500;
//            count200 -= withdraw200;
//            count100 -= withdraw100;
//            balance -= amount;
//
//            System.out.println("Withdrawal Successful. Amount: " + amount);
//            System.out.println("Notes dispensed: " + withdraw500 + " x 500, " + withdraw200 + " x 200, " + withdraw100 + " x 100.");
//        } else {
//            System.out.println("Not enough Amount in ATM for this withdrawal.");
//        }
//    }
}