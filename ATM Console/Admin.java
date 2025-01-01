import java.util.*;

public class Admin {
    //creating fields for admin Name and Pin
     private String adminName ;
     private String adminPin;
    //ArrayList  to store the transaction of a admin
    private static ArrayList<Transactions> viewaTransHis = new ArrayList<>();//it always stores only the transaction details

    //constructor for initializing Admin object with parameters
    public Admin(String adminName , String adminPin){
        this.adminName = adminName;
        this.adminPin = adminPin;
        this.viewaTransHis.add(new Transactions("Admin","Initial Amount",ATM.balance));//for adding initial transaction in ArrayList
    }


    //non-static method because at every login the login admin may change or it holds different values
    public String getId(){
        //method to get and return the admin's name

        return adminName;
    }

    public String getPin(){
        //method to get and return the admin's pin

        return adminPin;
    }

    public  ArrayList<Transactions> getTransactionHistory() {
        //method to return the transaction history of Admin object
        return viewaTransHis;
    }

    public static void viewAllUsers() {
        //static method because it always shows all the users stored in a list
       //Method call to print all users using AdminAction class
        AdminAction.printAllUsers();
    }
}


