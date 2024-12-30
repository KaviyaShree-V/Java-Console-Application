public class Transactions {
    private String name;
    private String TransType;
    private double transAmount;

    public Transactions(String name, String TransType , double transAmount){
        this.name=name;
        this.TransType = TransType;
        this.transAmount=transAmount;
    }

    public String getName() {
        return name;
    }

    public String getTransType() {
        return TransType;
    }

    public double getTransAmount() {
        return transAmount;
    }
}
