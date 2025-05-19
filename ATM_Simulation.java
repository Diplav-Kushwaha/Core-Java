package Practice;

//_________Exception Handling__________

import java.util.Scanner;
class InsufficientBalanceException extends Exception{
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
public class ATM_Simulation {
    public static double Balance = 0.0;
    public static void Deposit(double amount) throws Exception{
        if(amount<=0){
            throw new Exception("Amount must be greater than 0 : ");
        }
        Balance=Balance+amount;
        System.out.println("Successfully deposit : "+amount);
    }
    public static void Withdraw(double amount) throws InsufficientBalanceException, Exception{
        if(amount<=0){
            throw new Exception("Amount must be greater than 0 :");
        }else if(amount>Balance){
            throw new InsufficientBalanceException("Insufficient Balance : "+Balance);
        }
        Balance=Balance-amount;
        System.out.println("Successfully withdraw : "+amount);
    }
    public static void CheckBalance(){
        System.out.println("Available Balance : "+Balance);
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int x;
        System.out.println("========Welcome to the ATM Simulation========");
        do{
            System.out.println("1. Check Balance\n2. Deposit\n3. Withdraw\n4. Exit");
            System.out.print("Choose an option : ");
            while(!sc.hasNextInt()){
                System.out.println("Invalid Input ! Please enter valid input(1 to 4) ");
                sc.next();
            }
            x= sc.nextInt();
            switch (x){
                case 1:
                    CheckBalance();
                    break;
                case 2:
                    System.out.print("Deposit your amount : ");
                    try{
                        double amount = sc.nextDouble();
                        Deposit(amount);
                    }catch (Exception exc){
                        System.out.println("Invalid Input "+exc.getMessage());
                        sc.nextLine();
                    }
                    break;
                case 3:
                    System.out.print("Withdraw your amount : ");
                    try{
                        double amount = sc.nextDouble();
                        Withdraw(amount);
                    }catch (InsufficientBalanceException exc){
                        System.out.println("Error ! "+exc.getMessage());
                    }catch (Exception exc){
                        System.out.println("Invalid Input "+exc.getMessage());
                        sc.nextLine();
                    }
                    break;
                case 4:
                    System.out.println("______Thank You so much_____");
                    break;
                default:
                    System.out.println("Invalid Choice : ");
            }
        }while(x!=4);
    }
}
