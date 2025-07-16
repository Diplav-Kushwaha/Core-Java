package CoreJava;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

class Student{

    Scanner sc=new Scanner(System.in);
    private List<String> studentName;
    private Map<String, String> studentAttendance;

    public Student(){
        studentName=new ArrayList<>();
        studentAttendance= new HashMap<>();
    }
    public void AddStudent(String name){
        studentName.add(name);
        Collections.sort(studentName);
        System.out.println("Add successfully..........");
    }
    public void RemoveStudent(String name){
        studentName.remove(name);
        Collections.sort(studentName);
        System.out.println("Remove successfully..........");
    }
    public void TakeAttendance(){
        System.out.println("==============Student Attendance==============");
        System.out.println("If student present enter 'YES' otherwise 'No'");

        String available="Present";
        String notAvailable="Absent";
        for(String var:studentName){
            System.out.print(var+" Present : ");
            String present=sc.nextLine();
            if(present.equalsIgnoreCase("YES")){
                studentAttendance.put(var, available);
            }else{
                studentAttendance.put(var, notAvailable);
            }
        }
    }
    public void DisplayStudent(){
        if(studentAttendance.isEmpty()){
            System.out.println("Take Attendance First........");
        }else{
            System.out.println("================Present==============");
            int i=1;
            for(Map.Entry<String, String> entry : studentAttendance.entrySet()){
                System.out.println(i+". "+entry.getKey()+" : "+entry.getValue());
                i++;
            }
        }
    }
}

class Teacher{

    Scanner sc=new Scanner(System.in);
    private Map<String, String> teacher;
    private List<String> teacherName;

    public Teacher(){
        teacher=new HashMap<>();
        teacherName=new ArrayList<>();
    }
    public void AddTeacher(String name){
        sc.nextLine();
        teacherName.add(name);
        Collections.sort(teacherName);
        System.out.println("Add successfully.......");
    }
    public void RemoveTeacher(String name){
        teacherName.remove(name);
        Collections.sort(teacherName);
        System.out.println("Remove successfully.......");
    }
    public void TakeAttendance(){
        System.out.println("==============Teacher Attendance==============");
        System.out.println("If teacher present enter 'YES' otherwise 'No'");

        String available="Present";
        String notAvailable="Absent";
        for(String var:teacherName){
            System.out.print(var+" Present : ");
            String present=sc.nextLine();
            if(present.equalsIgnoreCase("YES")){
                teacher.put(var, available);
            }else{
                teacher.put(var, notAvailable);
            }
        }
    }
    public void DisplayTeacher(){
        if(teacher.isEmpty()){
            System.out.println("Take Attendance First........");
        }else{
            System.out.println("================Present==============");
            int i=1;
            for(Map.Entry<String, String> entry : teacher.entrySet()){
                System.out.println(i+". "+entry.getKey()+" : "+entry.getValue());
                i++;
            }
        }
    }
}
public class AttendanceRegister{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        Teacher teacher=new Teacher();
        Student student=new Student();

        boolean exit=true;
        while(exit){
            System.out.println("=============Attendance Register=================");
            System.out.println("1.Principle \n2.Teacher \n3.Exit program");
            System.out.print("Enter your position : ");
            int n=sc.nextInt();
            sc.nextLine();
            switch (n){
                case 1:
                    Principle(teacher, sc);
                    break;
                case 2:
                    Teacher(student, sc);
                    break;
                case 3:
                    System.out.println("Exiting Program...................");
                    exit=false;
                    break;
                default:
                    System.out.println("Invalid Input! Please enter valid input........");
            }
        }
    }
    public static void Principle(Teacher teacher, Scanner sc){

        boolean exit=true;
        while(exit){
            System.out.println("==============Principle DeskBoard=================");
            System.out.println("1.Add Teacher\n2.Remove Teacher\n3.Teacher Attendance\n4.Display All Teacher\n5.Back to Main Menu");
            System.out.print("Enter your choice : ");
            int n=sc.nextInt();
            sc.nextLine();
            switch(n){
                case 1:
                    System.out.print("Enter Teacher name : ");
                    String name=sc.nextLine();
                    teacher.AddTeacher(name);
                    break;
                case 2:
                    System.out.print("Enter Teacher name : ");
                    String teacherName=sc.nextLine();
                    teacher.RemoveTeacher(teacherName);
                    break;
                case 3:
                    teacher.TakeAttendance();
                    break;
                case 4:
                    teacher.DisplayTeacher();
                    break;
                case 5:
                    exit=false;
                    break;
                default:
                    System.out.println("Invalid Input! Please enter valid input........");
            }
        }
    }
    public static void Teacher(Student student, Scanner sc){

        boolean exit=true;
        while(exit){
            System.out.println("==============Teacher DeskBoard=================");
            System.out.println("1.Add Student\n2.Remove Student\n3.Student Attendance\n4.Display Student\n5.Back to Main Menu");
            System.out.print("Enter your choice : ");
            int n=sc.nextInt();
            sc.nextLine();
            switch (n){
                case 1:
                    System.out.print("Enter student name : ");
                    String name1=sc.nextLine();
                    student.AddStudent(name1);
                    break;
                case 2:
                    System.out.print("Enter student name : ");
                    String name2=sc.nextLine();
                    student.RemoveStudent(name2);
                    break;
                case 3:
                    student.TakeAttendance();
                    break;
                case 4:
                    student.DisplayStudent();
                    break;
                case 5:
                    exit=false;
                    break;
                default:
                    System.out.println("Invalid Input! Please enter valid input........");
            }
        }
    }
}
