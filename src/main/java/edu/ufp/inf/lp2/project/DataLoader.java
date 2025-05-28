package edu.ufp.inf.lp2.project;

import edu.ufp.inf.lp2.project.model.Person;
import edu.ufp.inf.lp2.project.model.Student;
import edu.ufp.inf.lp2.project.model.Professor;
import edu.ufp.inf.lp2.project.model.Course;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class DataLoader {

    private static final String DB_PATH = "./DB/";

    public static Map<String, Person> loadPersons() {
        Map<String, Person> persons = new HashMap<>();
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(DB_PATH + "persons.txt"))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    persons.put(data[0], new Person(data[0], data[1], data[2], data[3]) {}); // Anonymous inner class for abstract Person
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading persons: " + e.getMessage());
        }
        return persons;
    }

    public static Map<String, Student> loadStudents() {
        Map<String, Student> students = new HashMap<>();
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(DB_PATH + "students.txt"))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                // data[0] is personId, data[1] is courses (e.g., INF001:Engenharia Informatica;MAT002:Matematica Discreta)
                if (data.length >= 1) {
                    String personId = data[0];
                    Person person = MainApp.getPersons().get(personId);
                    if (person != null) {
                        Student student = new Student(person.getId(), person.getName(), person.getEmail(), person.getContact());
                        if (data.length > 1 && !data[1].isEmpty()) {
                            String[] courses = data[1].split(";");
                            for (String course : courses) {
                                String[] courseData = course.split(":");
                                if (courseData.length == 2) {
                                    student.addCourse(new Course(courseData[0], courseData[1]));
                                }
                            }
                        }
                        students.put(personId, student);
                    } else {
                        System.err.println("Person with ID " + personId + " not found for student.");
                    }
                }
                }
        } catch (IOException e) {
            System.err.println("Error loading students: " + e.getMessage());
        }
        return students;
    }

    public static Map<String, Professor> loadProfessors() {
        Map<String, Professor> professors = new HashMap<>();
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(DB_PATH + "professors.txt"))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                // data[0] is personId, data[1] is officeRoom, data[2] is officeHours, data[3] is courses
                if (data.length >= 3) {
                    String personId = data[0];
                    Person person = MainApp.getPersons().get(personId);
                    if (person != null) {
                        Professor professor = new Professor(person.getId(), person.getName(), person.getEmail(), person.getContact(), data[1], data[2]);
                        if (data.length > 3 && !data[3].isEmpty()) {
                            String[] courses = data[3].split(";");
                            for (String course : courses) {
                                String[] courseData = course.split(":");
                                if (courseData.length == 2) {
                                    professor.assignCourse(courseData[0], courseData[1]);
                                }
                            }
                        }
                        professors.put(personId, professor);
                    } else {
                        System.err.println("Person with ID " + personId + " not found for professor.");
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading professors: " + e.getMessage());
        }
        return professors;
    }

    public static Map<String, Course> loadCourses() {
        Map<String, Course> courses = new HashMap<>();
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(DB_PATH + "courses.txt"))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(":");
                if (data.length == 2) {
                    courses.put(data[1], new Course(data[1], data[0]));
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading courses: " + e.getMessage());
        }
        return courses;
    }

    public static void saveAllPersons(Map<String, Person> persons) {
        try (FileWriter fw = new FileWriter(DB_PATH + "persons.txt", false); // Overwrite the file
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            for (Person person : persons.values()) {
                out.println(person.getId() + "," + person.getName() + "," + person.getEmail() + "," + person.getContact());
            }
        } catch (IOException e) {
            System.err.println("Error saving persons: " + e.getMessage());
        }
    }

    public static void saveAllStudents(Map<String, Student> students) {
        try (FileWriter fw = new FileWriter(DB_PATH + "students.txt", false); // Overwrite the file
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            for (Student student : students.values()) {
                // Assuming Student has a method to get courses in a savable format
                out.println(student.getId() + "," + student.getCoursesAsString());
            }
        } catch (IOException e) {
            System.err.println("Error saving students: " + e.getMessage());
        }
    }
}