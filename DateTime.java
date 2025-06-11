package Practice;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.DayOfWeek;

public class DateTime {
    public static String findDay(int month, int day, int year) {
        LocalDate date = LocalDate.of(year, month, day);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek.toString();
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int month = sc.nextInt();
        int day = sc.nextInt();
        int year = sc.nextInt();

        System.out.println(findDay(month, day, year));
    }
}
