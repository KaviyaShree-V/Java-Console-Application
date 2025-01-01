public class Transactions {
    private String name; //field to store the name
    private String TransType; //field to store the type of transaction (deposit  or withdraw)
    private double transAmount; // field to store the amount in the transaction

    // Constructor to initialize the Transactions object with name, transaction type & amount
    public Transactions(String name, String TransType, double transAmount) {
        this.name = name; // Assigns the name parameter to the name field
        this.TransType = TransType; // Assigns the TransType parameter to the TransType field
        this.transAmount = transAmount; // Assigns the transAmount parameter to the transAmount field
    }

    // getName method to get the person name
    public String getName() {
        return name; // Returns the value of the name
    }

    // getTransType method to get the type of the transaction
    public String getTransType() {
        return TransType; // Returns the value of the TransType
    }

    // getTransAmount method to get the amount of the transaction
    public double getTransAmount() {
        return transAmount; // Returns the value of the transAmount
    }
}
