package manager;

import entity.*;
import factory.EntityFactory;
import observer.ClassroomObserver;

import java.util.*;

public class ClassroomManager {
    private static ClassroomManager instance;
    private final Map<String, Classroom> classrooms = new HashMap<>();
    private final List<ClassroomObserver> observers = new ArrayList<>();
    private final EntityFactory factory = new EntityFactory();

    private ClassroomManager() {}

    public static ClassroomManager getInstance() {
        if (instance == null) instance = new ClassroomManager();
        return instance;
    }

    public void addObserver(ClassroomObserver obs) { observers.add(obs); }
    private void notifyObservers(String msg) { observers.forEach(o -> o.notifyEvent(msg)); }

    public void addClassroom(String name) {
        if (classrooms.containsKey(name)) {
            System.out.println("❌ Error: Classroom already exists.");
            return;
        }
        Classroom c = factory.createClassroom(name);
        classrooms.put(name, c);
        System.out.println("✅ Classroom " + name + " created.");
        notifyObservers("Classroom created: " + name);
    }

    public void removeClassroom(String name) {
        Classroom removed = classrooms.remove(name);
        if (removed != null) {
            System.out.println("✅ Classroom " + name + " removed.");
            notifyObservers("Classroom removed: " + name);
        } else {
            System.out.println("❌ Error: Classroom not found.");
        }
    }

    public void addStudent(String studentId, String className) {
        Classroom c = classrooms.get(className);
        if (c == null) {
            System.out.println("❌ Error: Classroom not found.");
            return;
        }
        if (c.addStudent(factory.createStudent(studentId))) {
            notifyObservers("Student " + studentId + " added to " + className);
        }
    }

    public void removeStudent(String studentId, String className) {
        Classroom c = classrooms.get(className);
        if (c == null) {
            System.out.println("❌ Error: Classroom not found.");
            return;
        }
        if (c.removeStudent(studentId)) {
            notifyObservers("Student " + studentId + " removed from " + className);
        }
    }

    public void scheduleAssignment(String className, String details) {
        Classroom c = classrooms.get(className);
        if (c == null) {
            System.out.println("❌ Error: Classroom not found.");
            return;
        }
        c.scheduleAssignment(factory.createAssignment(details));
        notifyObservers("Assignment scheduled in " + className + ": " + details);
    }

    public void submitAssignment(String studentId, String className, String details) {
        Classroom c = classrooms.get(className);
        if (c == null) {
            System.out.println("❌ Error: Classroom not found.");
            return;
        }
        c.submitAssignment(studentId, details);
        notifyObservers("Student " + studentId + " attempted submission in " + className);
    }

    public void listClassrooms() {
        if (classrooms.isEmpty()) {
            System.out.println("No classrooms available.");
            return;
        }
        classrooms.keySet().forEach(System.out::println);
    }

    public Classroom getClassroom(String name) { return classrooms.get(name); }
}
