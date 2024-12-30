import java.util.*;

public class Admin {
    String adminName ;
    String adminPin;
    static ArrayList<Transactions> viewaTransHis = new ArrayList<>();

    public Admin(String adminName , String adminPin){
        this.adminName = adminName;
        this.adminPin = adminPin;
    }
    public Admin(){
        viewaTransHis = new ArrayList<>();
    }
    public String getId(){

        return adminName;
    }

    public String getPin(){

        return adminPin;
    }

    public ArrayList<Transactions> getTransactionHistory() {
        return viewaTransHis;
    }
    public void addTransaction(String name, String transType, double transAmount) {
        Transactions transaction = new Transactions(name, transType, transAmount);
        viewaTransHis.add(transaction);
    }

    public static void viewAllUsers() {
        AdminAction.printAllUsers();
    }
}
