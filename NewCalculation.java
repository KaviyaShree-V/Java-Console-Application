import java.util.Scanner;

public class NewCalculation {
    Scanner sc = new Scanner(System.in);
    public static double add(double a, double b){
        System.out.println("Answer is:"+(a+b));
        return (a+b);
    }
    public static double add(double a, double b,double c){
        System.out.println("Answer is:"+(a+b+c));
        return (a+b+c);
    }
    public static double sub(double a, double b){
        System.out.println("Answer is:"+(a-b));
        return (a-b);
    }
    public static double sub(double a, double b,double c){
        System.out.println("Answer is:"+(a-b-c));
        return (a-b-c);
    }
    public static double mul(double a, double b){
        System.out.println("Answer is:"+(a*b));
        return (a*b);
    }
    public static double mul(double a, double b,double c){
        System.out.println("Answer is:"+(a*b*c));
        return (a*b*c);
    }
    public static double div(double a, double b){
        System.out.println("Answer is:"+(a/b));
        return (a/b);
    }
    public static double div(double a, double b, double c){
        System.out.println("Answer is:"+(a/b/c));
        return (a/b/c);
    }
}
