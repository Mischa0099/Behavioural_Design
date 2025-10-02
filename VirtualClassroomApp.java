import java.util.*;
// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
import java.util.*;

public class VirtualClassroomApp {
   private static final Scanner sc;

   public VirtualClassroomApp() {
   }

   private static void printMenu() {
      System.out.println("\n=== Virtual Classroom Manager ===");
      System.out.println("1. Add Classroom");
      System.out.println("2. Add Student");
      System.out.println("3. Schedule Assignment");
      System.out.println("4. Submit Assignment");
      System.out.println("5. List Classrooms");
      System.out.println("6. List Students in a Classroom");
      System.out.println("7. List Assignments in a Classroom");
      System.out.println("8. Exit");
      System.out.print("Enter choice: ");
   }

   public static void main(String[] var0) {
   ClassroomManager var1 = ClassroomManager.getInstance();
   var1.addObserver(new ConsoleNotifier());

      while(true) {
         printMenu();

         int var2;
         try {
            var2 = Integer.parseInt(sc.nextLine());
         } catch (Exception var6) {
            System.out.println("❌ Invalid input. Enter a number.");
            continue;
         }

         Classroom var3;
         String var4;
         String var7;
         switch (var2) {
            case 1:
               System.out.print("Enter classroom name: ");
               var1.addClassroom(sc.nextLine());
               break;
            case 2:
               System.out.print("Enter student ID: ");
               var7 = sc.nextLine();
               System.out.print("Enter classroom name: ");
               var4 = sc.nextLine();
               var1.addStudent(var7, var4);
               break;
            case 3:
               System.out.print("Enter classroom name: ");
               var7 = sc.nextLine();
               System.out.print("Enter assignment details: ");
               var4 = sc.nextLine();
               var1.scheduleAssignment(var7, var4);
               break;
            case 4:
               System.out.print("Enter student ID: ");
               var7 = sc.nextLine();
               System.out.print("Enter classroom name: ");
               var4 = sc.nextLine();
               System.out.print("Enter assignment details: ");
               String var5 = sc.nextLine();
               var1.submitAssignment(var7, var4, var5);
               break;
            case 5:
               var1.listClassrooms();
               break;
            case 6:
               System.out.print("Enter classroom name: ");
               var3 = var1.getClassroom(sc.nextLine());
               if (var3 != null) {
                  var3.listStudents();
               } else {
                  System.out.println("Classroom not found.");
               }
               break;
            case 7:
               System.out.print("Enter classroom name: ");
               var3 = var1.getClassroom(sc.nextLine());
               if (var3 != null) {
                  var3.listAssignments();
               } else {
                  System.out.println("Classroom not found.");
               }
               break;
            case 8:
               System.out.println("\ud83d\udc4b Exiting...");
               return;
            default:
               System.out.println("❌ Invalid choice.");
         }
      }
   }

   static {
     sc = new Scanner(System.in);
   }
}

// --------- PLACEHOLDER CLASSES ---------
class ClassroomManager {
   private static ClassroomManager instance = new ClassroomManager();
   private List<Classroom> classrooms = new ArrayList<>();
   private List<Observer> observers = new ArrayList<>();

   public static ClassroomManager getInstance() {
      return instance;
   }

   public void addObserver(Observer o) {
      observers.add(o);
   }

   public void addClassroom(String name) {
      classrooms.add(new Classroom(name));
      notifyObservers("Classroom added: " + name);
   }

   public void addStudent(String studentId, String classroomName) {
      Classroom c = getClassroom(classroomName);
      if (c != null) {
         c.addStudent(new Student(studentId));
         notifyObservers("Student " + studentId + " added to " + classroomName);
      }
   }

   public void scheduleAssignment(String classroomName, String details) {
      Classroom c = getClassroom(classroomName);
      if (c != null) {
         c.scheduleAssignment(new Assignment(details));
         notifyObservers("Assignment scheduled in " + classroomName);
      }
   }

   public void submitAssignment(String studentId, String classroomName, String details) {
      Classroom c = getClassroom(classroomName);
      if (c != null) {
         c.submitAssignment(studentId, details);
         notifyObservers("Assignment submitted by " + studentId + " in " + classroomName);
      }
   }

   public void listClassrooms() {
      for (Classroom c : classrooms) {
         System.out.println(c.getName());
      }
   }

   public Classroom getClassroom(String name) {
      for (Classroom c : classrooms) {
         if (c.getName().equals(name)) return c;
      }
      return null;
   }

   private void notifyObservers(String message) {
      for (Observer o : observers) {
         o.update(message);
      }
   }
}

interface Observer {
   void update(String message);
}

class ConsoleNotifier implements Observer {
   public void update(String message) {
      System.out.println("[Notification] " + message);
   }
}

class Student {
   private final String id;
   public Student(String id) { this.id = id; }
   public String getId() { return id; }
}

class Assignment {
   private final String details;
   public Assignment(String details) { this.details = details; }
   public String getDetails() { return details; }
}

class Classroom {
   private final String name;
   private final List<Student> students = new ArrayList<>();
   private final List<Assignment> assignments = new ArrayList<>();

   public Classroom(String name) { this.name = name; }
   public String getName() { return name; }
   public void addStudent(Student s) { students.add(s); }
   public void scheduleAssignment(Assignment a) { assignments.add(a); }
   public void submitAssignment(String studentId, String details) {
      System.out.println("Assignment submitted by " + studentId + ": " + details);
   }
   public void listStudents() {
      for (Student s : students) {
         System.out.println(s.getId());
      }
   }
   public void listAssignments() {
      for (Assignment a : assignments) {
         System.out.println(a.getDetails());
      }
   }
}

class FileLogger implements Observer {
   public void update(String message) {
      // Placeholder: log to file (not implemented)
      System.out.println("[FileLogger] " + message);
   }
}

