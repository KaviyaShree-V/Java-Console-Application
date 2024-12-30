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
        this.viewaTransHis = new ArrayList<>();
        this.viewaTransHis.add(new Transactions("Admin","Initial Amount",ATM.balance));
    }
    public String getId(){

        return adminName;
    }

    public String getPin(){

        return adminPin;
    }

    public  ArrayList<Transactions> getTransactionHistory() {
        return viewaTransHis;
    }

    public static void viewAllUsers() {
        AdminAction.printAllUsers();
    }
}


