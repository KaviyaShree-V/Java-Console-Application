import java.util.ArrayList;

public class User {
    //creating fields for user Name, Pin, balance and arrayList for transaction
    private String userName ;
    private  String userPin ;
    private  double balance ;
    private static ArrayList<Transactions> transactionHistory = new ArrayList<>();

    //constructor for initializing User object with parameters
    public User(String userName , String userPin , double balance){
        this.userName = userName;
        this.userPin = userPin;
        this.balance = balance;
        this.transactionHistory.add(new Transactions(userName,"Initial Amount", balance));
    }

    //non-static method because at every login the login admin may change or it holds different values

    public String getId(){
        //method to get and return the user's name

        return userName;
    }

    public  String getPin(){
        //method to get and return the user's pin
        return userPin;
    }

    //balance will change for every deposit and withdraw in account so it is non-static

    public double getBalance() {
        //method to get and return the balance of a user
        return balance;
    }

    public  void setBalance(double balance) {
        //method to set the balance in user's account when deposit or withdraw
        this.balance = balance;
    }
//non-static method because this method changes the user's pin
    public  void changePin(String newPin) {
        //method to set the user's pin when the user pin change
        this.userPin = newPin;
    }

    public static ArrayList<Transactions> getTransactionHistory() {
        //method to get and return the user's transaction
        return transactionHistory;
    }

}
