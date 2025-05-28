package edu.ufp.inf.lp2.project.model;

import java.util.ArrayList;
import java.util.List;

public class Student extends Person {

    private List<Course> courses;
    private String schedule;

    public Student(String id, String name, String email, String contact) {
        super(id, name, email, contact);
        this.courses = new ArrayList<>();
        this.schedule = "";
    }

    public Student(String id, String name, String email, String contact, String courseOptions) {
        super(id, name, email, contact);
        this.courses = new ArrayList<>();
        this.schedule = "";
        // A lógica de parsing de courseOptions precisará ser adaptada para objetos Course
        // Por enquanto, vamos deixar como está, mas será necessário um DataLoader para Course
    }



    public List<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course) {
        if (!this.courses.contains(course)) {
            this.courses.add(course);
            course.addStudent(this); // Ensure bidirectionality
        }
    }

    public void removeCourse(Course course) {
        if (this.courses.contains(course)) {
            this.courses.remove(course);
            course.removeStudent(this); // Ensure bidirectionality
        }
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getCoursesAsString() {
        StringBuilder sb = new StringBuilder();
        for (Course course : courses) {
            sb.append(course.getCode()).append(":").append(course.getName()).append(";");
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1); // Remove trailing semicolon
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Student{" +
               "id='" + getId() + '\'' +
               ", name='" + getName() + '\'' +
               ", enrolledCoursesCount=" + courses.size() +
               '}';
    }
}