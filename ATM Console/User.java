import java.util.ArrayList;

public class User {
    private String userName ;
    private  String userPin ;
    private  double balance ;
    private static ArrayList<Transactions> transactionHistory;
    public User(String userName , String userPin , double balance){
        this.userName = userName;
        this.userPin = userPin;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
        this.transactionHistory.add(new Transactions(userName,"Initial Amount", balance));
    }


    public String getId(){

        return userName;
    }

    public  String getPin(){

        return userPin;
    }

    public double getBalance() {

        return balance;
    }

    public  void setBalance(double balance) {

        this.balance = balance;
    }

    public  void changePin(String newPin) {
        this.userPin = newPin;
    }

    public static ArrayList<Transactions> getTransactionHistory() {
        return transactionHistory;
    }
    
}
