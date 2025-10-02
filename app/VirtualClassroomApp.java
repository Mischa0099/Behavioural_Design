package app;

import manager.ClassroomManager;
import observer.ConsoleNotifier;
import observer.FileLogger;
import entity.Classroom;

import java.util.Scanner;

public class VirtualClassroomApp {
    private static final Scanner sc = new Scanner(System.in);

    private static void printMenu() {
        System.out.println("\n=== Virtual Classroom Manager ===");
        System.out.println("1. Add Classroom");
        System.out.println("2. Remove Classroom");
        System.out.println("3. Add Student");
        System.out.println("4. Remove Student");
        System.out.println("5. Schedule Assignment");
        System.out.println("6. Submit Assignment");
        System.out.println("7. List Classrooms");
        System.out.println("8. List Students in a Classroom");
        System.out.println("9. List Assignments in a Classroom");
        System.out.println("10. Exit");
        System.out.print("Enter choice: ");
    }

    public static void main(String[] args) {
        ClassroomManager manager = ClassroomManager.getInstance();
        manager.addObserver(new ConsoleNotifier());
        manager.addObserver(new FileLogger());

        while (true) {
            printMenu();
            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("❌ Invalid input. Enter a number.");
                continue;
            }

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter classroom name: ");
                    manager.addClassroom(sc.nextLine());
                }
                case 2 -> {
                    System.out.print("Enter classroom name: ");
                    manager.removeClassroom(sc.nextLine());
                }
                case 3 -> {
                    System.out.print("Enter student ID: ");
                    String sid = sc.nextLine();
                    System.out.print("Enter classroom name: ");
                    manager.addStudent(sid, sc.nextLine());
                }
                case 4 -> {
                    System.out.print("Enter student ID: ");
                    String sid = sc.nextLine();
                    System.out.print("Enter classroom name: ");
                    manager.removeStudent(sid, sc.nextLine());
                }
                case 5 -> {
                    System.out.print("Enter classroom name: ");
                    String cname = sc.nextLine();
                    System.out.print("Enter assignment details: ");
                    manager.scheduleAssignment(cname, sc.nextLine());
                }
                case 6 -> {
                    System.out.print("Enter student ID: ");
                    String sid = sc.nextLine();
                    System.out.print("Enter classroom name: ");
                    String cname = sc.nextLine();
                    System.out.print("Enter assignment details: ");
                    manager.submitAssignment(sid, cname, sc.nextLine());
                }
                case 7 -> manager.listClassrooms();
                case 8 -> {
                    System.out.print("Enter classroom name: ");
                    Classroom c = manager.getClassroom(sc.nextLine());
                    if (c != null) c.listStudents(); else System.out.println("❌ Classroom not found.");
                }
                case 9 -> {
                    System.out.print("Enter classroom name: ");
                    Classroom c = manager.getClassroom(sc.nextLine());
                    if (c != null) c.listAssignments(); else System.out.println("❌ Classroom not found.");
                }
                case 10 -> {
                    System.out.println("👋 Exiting...");
                    return;
                }
                default -> System.out.println("❌ Invalid choice.");
            }
        }
    }
}
