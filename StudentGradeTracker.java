import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class StudentGradeTracker {

    static ArrayList<String> names = new ArrayList<>();
    static ArrayList<Integer> rollNumbers = new ArrayList<>();
    static ArrayList<Double> marks = new ArrayList<>();

    static char getGrade(double mark) {
        if (mark >= 90) return 'A';
        else if (mark >= 80) return 'B';
        else if (mark >= 70) return 'C';
        else if (mark >= 60) return 'D';
        else return 'F';
    }

    static void addStudent(Scanner scanner) {
        System.out.print("Enter student name: ");
        names.add(scanner.nextLine());

        System.out.print("Enter roll number: ");
        rollNumbers.add(scanner.nextInt());

        System.out.print("Enter marks: ");
        marks.add(scanner.nextDouble());
        scanner.nextLine();

        System.out.println("Student added successfully!");
    }

    static void viewStudents() {
        if (names.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        System.out.println("\n========== STUDENTS ==========");

        for (int i = 0; i < names.size(); i++) {
            System.out.println("\nStudent " + (i + 1));
            System.out.println("Name  : " + names.get(i));
            System.out.println("Roll  : " + rollNumbers.get(i));
            System.out.println("Marks : " + marks.get(i));
            System.out.println("Grade : " + getGrade(marks.get(i)));
        }
    }

    static void analytics() {
        if (marks.isEmpty()) {
            System.out.println("No student data available.");
            return;
        }

        double total = 0;
        double highest = marks.get(0);
        double lowest = marks.get(0);

        for (double mark : marks) {
            total += mark;
            if (mark > highest) highest = mark;
            if (mark < lowest) lowest = mark;
        }

        System.out.println("\n========== CLASS ANALYTICS ==========");
        System.out.println("Average : " + (total / marks.size()));
        System.out.println("Highest : " + highest);
        System.out.println("Lowest  : " + lowest);
    }

    static void saveToFile() {
        try {
            FileWriter writer = new FileWriter("students.txt");

            for (int i = 0; i < names.size(); i++) {
                writer.write(
                    names.get(i) + " | " +
                    rollNumbers.get(i) + " | " +
                    marks.get(i) + " | Grade: " +
                    getGrade(marks.get(i)) + "\n"
                );
            }

            writer.close();
            System.out.println("Student data saved to students.txt");

        } catch (IOException e) {
            System.out.println("Error saving data.");
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=================================");
            System.out.println("     STUDENT GRADE TRACKER");
            System.out.println("=================================");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. View Class Analytics");
            System.out.println("4. Save Data");
            System.out.println("5. Exit");
            System.out.println("=================================");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addStudent(scanner);
                    break;

                case 2:
                    viewStudents();
                    break;

                case 3:
                    analytics();
                    break;

                case 4:
                    saveToFile();
                    break;

                case 5:
                    System.out.println("Thank you for using Student Grade Tracker!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 5);

        scanner.close();
    }
}