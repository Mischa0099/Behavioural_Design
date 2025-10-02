package entity;

import java.util.*;

public class Classroom {
    private final String name;
    private final Map<String, Student> students = new HashMap<>();
    private final Map<String, Assignment> assignments = new HashMap<>();

    public Classroom(String name) { this.name = name; }
    public String getName() { return name; }

    public boolean addStudent(Student s) {
        if (students.containsKey(s.getId())) {
            System.out.println("❌ Error: Student " + s.getId() + " already enrolled in " + name);
            return false;
        }
        students.put(s.getId(), s);
        System.out.println("✅ Student " + s.getId() + " enrolled in " + name);
        return true;
    }

    public boolean removeStudent(String studentId) {
        if (students.remove(studentId) != null) {
            System.out.println("✅ Student " + studentId + " removed from " + name);
            return true;
        }
        System.out.println("❌ Error: Student not found in " + name);
        return false;
    }

    public void scheduleAssignment(Assignment a) {
        if (assignments.containsKey(a.getDetails())) {
            System.out.println("❌ Error: Assignment already exists in " + name);
            return;
        }
        assignments.put(a.getDetails(), a);
        System.out.println("✅ Assignment scheduled in " + name + ": " + a.getDetails());
    }

    public void submitAssignment(String studentId, String details) {
        Student student = students.get(studentId);
        if (student == null) {
            System.out.println("❌ Error: Student " + studentId + " not found in " + name);
            return;
        }
        Assignment assignment = assignments.get(details);
        if (assignment == null) {
            System.out.println("❌ Error: Assignment not found in " + name);
            return;
        }
        if (student.hasSubmitted(details)) {
            System.out.println("⚠️ Warning: Student " + studentId + " has already submitted " + details);
            return;
        }
        student.markSubmitted(details);
        System.out.println("✅ Student " + studentId + " submitted assignment '" + details + "' in " + name);
    }

    public void listStudents() {
        if (students.isEmpty()) System.out.println("No students in " + name);
        else students.values().forEach(st -> System.out.println("- " + st.getId()));
    }

    public void listAssignments() {
        if (assignments.isEmpty()) System.out.println("No assignments in " + name);
        else assignments.values().forEach(a -> System.out.println("- " + a.getDetails()));
    }
}
