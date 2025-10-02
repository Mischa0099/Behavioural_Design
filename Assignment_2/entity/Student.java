package entity;

import java.util.HashSet;
import java.util.Set;

public class Student {
    private final String id;
    private final Set<String> submittedAssignments = new HashSet<>();

    public Student(String id) { this.id = id; }
    public String getId() { return id; }

    public boolean hasSubmitted(String assignment) {
        return submittedAssignments.contains(assignment);
    }

    public void markSubmitted(String assignment) {
        submittedAssignments.add(assignment);
    }
}
