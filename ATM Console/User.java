import java.util.ArrayList;

public class User {
    private String userName ;
    private  String userPin ;
    private  double balance ;
    private int count500;
    private int count200;
    private int count100;
    private static ArrayList<Transactions> transactionHistory;
    public User(String userName , String userPin){
        this.userName = userName;
        this.userPin = userPin;
        this.balance=1500.0;
        this.count500 = 0;
        this.count200 = 0;
        this.count100 = 0;
        this.transactionHistory = new ArrayList<>();
        this.transactionHistory.add(new Transactions(getId(),"Initial Amount",balance));
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

    public ArrayList<Transactions> getTransactionHistory() {
        return transactionHistory;
    }

//    public void addTransaction(String transaction) {
//        transactionHistory.add(transaction);
//    }

    public void addTransaction(String name, String transType, double transAmount) {
        Transactions transaction = new Transactions(name, transType, transAmount);
        transactionHistory.add(transaction);
    }

    public int getCount500() {
        return count500;
    }

    public void setCount500(int count500) {
        this.count500 = count500;
    }

    public int getCount200() {
        return count200;
    }

    public void setCount200(int count200) {
        this.count200 = count200;
    }

    public int getCount100() {
        return count100;
    }

    public void setCount100(int count100) {
        this.count100 = count100;
    }
}