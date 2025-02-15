import java.util.ArrayList;

public class Account {
    private String name;
    private String pin;
    private  ArrayList<Transactions> transactions = new ArrayList<>();

    public Account(String name, String pin){
        this.name=name;
        this.pin=pin;
    }
    public String getName(){
        return name;
    }
    public String getPin(){
        return pin;
    }
    protected void changePin(String pin) {
        this.pin = pin;
    }
    public  ArrayList<Transactions> getTransactionHistory() {
        return this.transactions;
    }
}