import java.util.*;

public class Admin extends Account{
    //constructor for initializing Admin object with parameters
    public Admin(String adminName , String adminPin){
        super(adminName,adminPin);
    }

    public static void viewAllUsers() {
        //static method because it always shows all the users stored in a list
       //Method call to print all users using AdminAction class
        AdminAction.printAllUsers();
    }
}


