package Practice;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
class Students{
    String name;
    float mark;
    public Students(String name, float mark) {
        this.name = name;
        this.mark = mark;
    }
}
class MarkComparator implements Comparator<Students> {
    @Override
    public int compare(Students s1, Students s2) {
        return Float.compare(s2.mark, s1.mark );
    }
}
public class ReportCard {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        ArrayList<Students> students=new ArrayList<>();
        System.out.print("Enter number of students : ");
        int StudentStrength =sc.nextInt();
        int i=0;
        while(StudentStrength!=0){
            i=i+1;
            System.out.print("Enter name of student "+i+" : ");
            String name=sc.nextLine();
            sc.nextLine();
            System.out.print("Enter mark of student "+i+" : ");
            float mark=sc.nextFloat();;
            students.add(new Students(name, mark));
            StudentStrength--;
        }
        students.sort(new MarkComparator());
        System.out.println("\n_______Student Ranking______");
        int rank = 1;
        for (Students student : students) {
            System.out.println(rank +". "+student.name+" : "+student.mark +"marks");
            rank++;
        }
    }
}
