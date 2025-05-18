package Practice;

import java.util.InputMismatchException;
import java.util.Scanner;
class InvalidMarksException extends Exception{
    public InvalidMarksException(String message) { // This is a Constructor
        super(message);
    }
}
public class StudentGradeCalculator {
    public static int GetValidMarks(Scanner sc, int SubjectMarks) throws InvalidMarksException{
        System.out.print("Enter marks for subject "+SubjectMarks +" : ");
        try {
            int mark =sc.nextInt();
            if(mark<0 || mark>100){
                throw new InvalidMarksException("Valid marks between 0 to 100");
            }
            return mark;
        }catch (InputMismatchException exc){
            sc.nextLine();
            throw new InvalidMarksException("Invalid Input !! Please enter valid input : ");
        }
    }

    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        int[] marks = new int[5];
        int totalMarks=0;
        double avg;
        System.out.println("____________Student Grade Calculator_____________");

        for(int i=0; i<5; i++){
            boolean valid =  false;
            while(!valid){
                try{
                    marks[i]=GetValidMarks(sc, i+1);
                    totalMarks=totalMarks+marks[i];
                    valid = true;
                }catch (InvalidMarksException exc){
                    System.out.println("Error ! Enter "+exc.getMessage());
                }
            }
        }

        System.out.println("______Your Result Card______");
        avg=totalMarks/5.0;
        System.out.println("Total   Marks : "+totalMarks);
        System.out.println("Average Marks : "+avg);

        if(avg>=90){
            System.out.println("Your grade is A+");
        }else if(avg>=80){
            System.out.println("Your grade is A");
        }else if(avg >=70){
            System.out.println("Your grade is B+");
        }else if(avg>=60){
            System.out.println("Your grade is B");
        }else if(avg>=40){
            System.out.println("Your grade is C");
        }else{
            System.out.println("Your are fail !!!!!");
        }
        sc.close();
    }
}
