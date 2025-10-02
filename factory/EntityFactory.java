package factory;

import entity.*;

public class EntityFactory {
    public Student createStudent(String id) { return new Student(id); }
    public Classroom createClassroom(String name) { return new Classroom(name); }
    public Assignment createAssignment(String details) { return new Assignment(details); }
}
