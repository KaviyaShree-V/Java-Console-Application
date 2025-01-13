import java.util.ArrayList;

public class User extends Account {
    private  double balance ;

    //constructor for initializing User object with parameters
    public User(String userName , String userPin , double balance){
        super(userName,userPin);
        this.balance = balance;
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
}