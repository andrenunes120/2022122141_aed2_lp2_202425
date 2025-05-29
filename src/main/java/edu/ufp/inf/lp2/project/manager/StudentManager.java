package edu.ufp.inf.lp2.project.manager;

import edu.princeton.cs.algs4.ST;
import edu.ufp.inf.lp2.project.model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Gere os estudantes da instituição, usando uma Symbol Table.
 */
public class StudentManager {

    private final ST<String, Student> students;

    public StudentManager() {
        this.students = new ST<>();
    }

    public void addStudent(Student student) {
        students.put(student.getId(), student);
    }

    public void removeStudent(String id) {
        students.delete(id);
    }

    public Student getStudent(String id) {
        return students.get(id);
    }

    public boolean existsStudent(String id) {
        return students.contains(id);
    }

    public Iterable<String> getAllStudentIds() {
        return students.keys();
    }

    public List<Student> getAllStudents() {
        List<Student> all = new ArrayList<>();
        for (String id : students.keys()) {
            all.add(students.get(id));
        }
        return all;
    }

    public void listStudents() {
        for (String id : students.keys()) {
            System.out.println(students.get(id));
        }
    }
}
