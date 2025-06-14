package Practice;

import java.util.Scanner;
import java.util.ArrayList;
class Operations{
    Scanner sc=new Scanner(System.in);
    ArrayList<String> task=new ArrayList<>();

    public void Addtask(){
        int i=0;
        while(true){
            System.out.print("Add Task "+ (i=i+1) +" or 'stop' to end : ");
            task.add(sc.nextLine());
            if(task.contains("stop")){
                break;
            }
        }
    }
    public void Viewtask(){
        int i=0;
        for(String list:task){
            System.out.println("Task "+ (i=i+1) +" : "+list);
        }
    }
    public void MarkComplete(){
        System.out.print("Enter task no. to mark complete : ");
        int input=sc.nextInt();
        if(input<= task.size()){
            task.set(input-1, "Task Completed");
            System.out.println("Task Completed ");
        }else{
            System.out.println("Invalid task number ");
        }
    }
    public void DeleteTask(){
        System.out.print("Enter task no. to delete : ");
        int input=sc.nextInt();
        if(input<=task.size()){
            task.remove(input-1);
            System.out.println("Task deleted successfully ");
        }else {
            System.out.println("Invalid task no.");
        }
    }

}
public class ToDoList {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Operations operations=new Operations();

        while(true){
            System.out.println("________To Do List________");
            System.out.println("1. Add Task");
            System.out.println("2. View Task");
            System.out.println("3. Mark Complete");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");
            System.out.print("Enter Your Operation :");
            int input=sc.nextInt();
            sc.nextLine();
            switch(input){
                case 1:
                    operations.Addtask();
                    break;
                case 2:
                    operations.Viewtask();
                    break;
                case 3:
                    operations.MarkComplete();
                    break;
                case 4:
                    operations.DeleteTask();
                    break;
                case 5:
                    System.out.println("Exiting Program...........");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Operation Number (Re-enter)");
            }
        }

    }
}
