import java.util.Scanner;

public class NewCal {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int z;
        int n,u;
        double num1,num2;
        double num3;
        NewCalculation nc = new NewCalculation();
        while (true) {
            System.out.println("Enter choice:");
            System.out.println("1.Addition\n2.Subtraction \n3.Multiplication \n4.Division");
            System.out.println("5.Exit");
            z = s.nextInt();
            if (z == 5) {
                System.out.println("Choose 1-4");
            }
            if (z > 5 || z < 1) {
                System.out.println("Enter from given Choice:");
                System.out.println("1.Addition\n2.Subtraction \n3.Multiplication \n4.Division");
                n= s.nextInt();
            } else if (z == 1) {
                System.out.println("Choose: \n1.Add 2 values \n2.Add 3 values");
                u=s.nextInt();
                if(u==1){
                    System.out.println("enter 1st:");
                    num1=s.nextDouble();
                    System.out.println("enter 2st:");
                    num2=s.nextDouble();
                    NewCalculation.add(num1,num2);
                }
                if(u==2){
                    System.out.println("enter 1st:");
                    num1=s.nextDouble();
                    System.out.println("enter 2st:");
                    num2=s.nextDouble();
                    System.out.println("enter 3rd:");
                    num3=s.nextDouble();
                    NewCalculation.add(num1,num2,num3);
                }
            } else if (z == 2) {
                System.out.println("Choose: \n1.Sub 2 values \n2.Sub 3 values");
                u=s.nextInt();
                if(u==1){
                    System.out.println("enter 1st:");
                    num1=s.nextDouble();
                    System.out.println("enter 2st:");
                    num2=s.nextDouble();
                    NewCalculation.sub(num1,num2);
                }
                if(u==2){
                    System.out.println("enter 1st:");
                    num1=s.nextDouble();
                    System.out.println("enter 2st:");
                    num2=s.nextDouble();
                    System.out.println("enter 3rd:");
                    num3=s.nextDouble();
                    NewCalculation.sub(num1,num2,num3);
                }
            } else if (z == 3) {
                System.out.println("Choose: \n1.Mul 2 values \n2.Mul 3 values");
                u=s.nextInt();
                if(u==1){
                    System.out.println("enter 1st:");
                    num1=s.nextDouble();
                    System.out.println("enter 2st:");
                    num2=s.nextDouble();
                    NewCalculation.mul(num1,num2);
                }
                if(u==2){
                    System.out.println("enter 1st:");
                    num1=s.nextDouble();
                    System.out.println("enter 2st:");
                    num2=s.nextDouble();
                    System.out.println("enter 3rd:");
                    num3=s.nextDouble();
                    NewCalculation.mul(num1,num2,num3);
                }
            } else if (z == 4) {
                System.out.println("Choose: \n1.Div 2 values \n2.Div 3 values");
                u=s.nextInt();
                if(u==1){
                    System.out.println("enter 1st:");
                    num1=s.nextDouble();
                    System.out.println("enter 2st:");
                    num2=s.nextDouble();
                    NewCalculation.div(num1,num2);
                }
                if(u==2){
                    System.out.println("enter 1st:");
                    num1=s.nextDouble();
                    System.out.println("enter 2st:");
                    num2=s.nextDouble();
                    System.out.println("enter 3rd:");
                    num3=s.nextDouble();
                    NewCalculation.div(num1,num2,num3);
                }
            } else {
                System.out.println("Exiting...");
            }
        }
    }
}
