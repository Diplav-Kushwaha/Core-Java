package Practice;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

class StudentOperation {
    Scanner sc = new Scanner(System.in);
    ArrayList<String> studentName = new ArrayList<>();

    public void AddStudent() {
        int i = 1;
        while (true) {
            System.out.print("Enter Student "+ i++ +" or 'stop' to end : ");
            String name = sc.nextLine().trim();

            if (name.equalsIgnoreCase("stop")) {
                break; // stop when user types "stop"
            }
            studentName.add(name);
        }
    }
    public void SearchStudent(){
        System.out.print("Enter student name : ");
        String name = sc.nextLine();
        if(studentName.contains(name)){
            System.out.println("'"+name+"' Student is available ");
        }else{
            System.out.println("'"+name+"' Student in not available ");
        }
    }
    public void UpdateStudent(){
        String name1;
        String name2;
        System.out.print("Enter old student name : ");
        name1=sc.nextLine();
        System.out.print("Enter new student name : ");
        name2=sc.nextLine();
        studentName.set(studentName.indexOf(name1), name2);
    }
    public void DeleteStudent(){
        String name;
        System.out.print("Enter student name to delete : ");
        name=sc.nextLine();
        studentName.remove(name);
    }
    public void DisplayStudent(){
        int i=1;
        Collections.sort(studentName);
        for(String std : studentName){
            System.out.println(i++ +". "+std);
        }
    }
}

public class Student_Management {

    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        StudentOperation studentManagement =new StudentOperation();
        System.out.println("\n_________|| Student Management System ||_________");

        while (true) {
            System.out.println("0. Exit");
            System.out.println("1. Add Student");
            System.out.println("2. Search Student");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Display All Students\n");

            System.out.print("Enter your operation: ");
            int input = sc.nextInt();

            switch (input) {
                case 0:
                    System.out.println("Exiting program.....");
                    return;
                case 1:
                    studentManagement.AddStudent();
                    break;
                case 2:
                    studentManagement.SearchStudent();
                    break;
                case 3:
                    studentManagement.UpdateStudent();
                    break;
                case 4:
                    studentManagement.DeleteStudent();
                    break;
                case 5:
                    studentManagement.DisplayStudent();
                    break;
                default:
                    System.out.println("Invalid input! Please try again.");
            }
        }
    }
}
