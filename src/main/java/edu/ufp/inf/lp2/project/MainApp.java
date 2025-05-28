package edu.ufp.inf.lp2.project;

import edu.ufp.inf.lp2.project.model.Person;
import edu.ufp.inf.lp2.project.model.Student;
import edu.ufp.inf.lp2.project.model.Professor;
import edu.ufp.inf.lp2.project.model.Course;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class MainApp {

    private static Map<String, Person> persons;
    private static Map<String, Course> courses;

    public static Map<String, Person> getPersons() {
        return persons;
    }

    public static void main(String[] args) {
        // Load data at startup
        persons = DataLoader.loadPersons();
        Map<String, Student> students = DataLoader.loadStudents();
        Map<String, Professor> professors = DataLoader.loadProfessors();
        courses = DataLoader.loadCourses();

        System.out.println("Loaded " + persons.size() + " persons.");



        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            printMenu();
            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        addStudentFromPerson(scanner, students, professors);
                        break;
                    case 2:
                        System.out.println("Add Professor selected.");
                        // TODO: Implement Add Professor logic
                        break;
                    case 3:
                        addPerson(scanner);
                        break;
                    case 4:
                        System.out.println("--- Students ---");
                        if (students.isEmpty()) {
                            System.out.println("No students to display.");
                        } else {
                            for (Student student : students.values()) {
                                System.out.println(student);
                            }
                        }
                        break;
                    case 6:
                        System.out.println("--- All Persons ---");
                        if (persons.isEmpty()) {
                            System.out.println("No persons to display.");
                        } else {
                            for (Person person : persons.values()) {
                                System.out.println(person);
                            }
                        }
                        break;
                    case 5:
                        System.out.println("--- Professors ---");
                        if (professors.isEmpty()) {
                            System.out.println("No professors to display.");
                        } else {
                            for (Professor professor : professors.values()) {
                                System.out.println(professor);
                            }
                        }
                        break;
                    case 7:
                        removePerson(scanner);
                        break;
                    case 0:
                        System.out.println("Exiting application.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
            System.out.println(); // Add a newline for better readability
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("--- Main Menu ---");
        System.out.println("1. Add Student");
        System.out.println("2. Add Professor");
        System.out.println("3. Add Person");
        System.out.println("4. View Students");
        System.out.println("5. View Professors");
        System.out.println("6. View All Persons");
        System.out.println("7. Remove Person");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addPerson(Scanner scanner) {
        System.out.println("--- Add New Person ---");
        int id = generateNextPersonId();
        System.out.print("Enter person name: ");
        String name = scanner.nextLine();
        System.out.print("Enter person email: ");
        String email = scanner.nextLine();
        System.out.print("Enter person contact: ");
        String contact = scanner.nextLine();

        Person newPerson = new Person(String.valueOf(id), name, email, contact);
        persons.put(String.valueOf(id), newPerson);
        DataLoader.saveAllPersons(persons);
        System.out.println("Person added successfully!");
    }

    private static int generateNextPersonId() {
        int maxId = 0;
        for (String idStr : persons.keySet()) {
            try {
                int id = Integer.parseInt(idStr);
                if (id > maxId) {
                    maxId = id;
                }
            } catch (NumberFormatException e) {
                // Ignore non-numeric keys
            }
        }
        return maxId + 1;
    }

    private static void removePerson(Scanner scanner) {
        System.out.println("--- Remove Person ---");
        System.out.print("Enter person ID to remove: ");
        String idToRemove = scanner.nextLine();

        if (persons.containsKey(idToRemove)) {
            persons.remove(idToRemove);
            DataLoader.saveAllPersons(persons);
            System.out.println("Person with ID " + idToRemove + " removed successfully!");
        } else {
            System.out.println("Person with ID " + idToRemove + " not found.");
        }
    }

    private static void addStudentFromPerson(Scanner scanner, Map<String, Student> students, Map<String, Professor> professors) {
        System.out.println("--- Add New Student ---");
        System.out.println("Available persons (not students or professors):");

        Map<String, Person> availablePersons = new HashMap<>();
        for (Person p : persons.values()) {
            if (!students.containsKey(p.getId()) && !professors.containsKey(p.getId())) {
                availablePersons.put(p.getId(), p);
            }
        }

        if (availablePersons.isEmpty()) {
            System.out.println("No persons available to be added as students.");
            return;
        }

        for (Person p : availablePersons.values()) {
            System.out.println("ID: " + p.getId() + ", Name: " + p.getName());
        }

        System.out.print("Enter the ID of the person to make a student: ");
        String personId = scanner.nextLine();

        Person selectedPerson = availablePersons.get(personId);
        if (selectedPerson == null) {
            System.out.println("Invalid person ID.");
            return;
        }



        System.out.println("Available courses:");
        List<Course> courseList = new ArrayList<>(courses.values());
        for (int i = 0; i < courseList.size(); i++) {
            System.out.println((i + 1) + ". " + courseList.get(i).getName() + " (" + courseList.get(i).getCode() + ")");
        }
        System.out.print("Enter the numbers of the courses to enroll (comma-separated): ");
        String courseChoices = scanner.nextLine();
        String[] choiceNumbers = courseChoices.split(",");
        
        Student newStudent = new Student(selectedPerson.getId(), selectedPerson.getName(), selectedPerson.getEmail(), selectedPerson.getContact());

        for (String choice : choiceNumbers) {
            try {
                int index = Integer.parseInt(choice.trim()) - 1;
                if (index >= 0 && index < courseList.size()) {
                    newStudent.addCourse(courseList.get(index));
                } else {
                    System.out.println("Invalid course number: " + (index + 1));
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for course number: " + choice);
            }
        }

        students.put(newStudent.getId(), newStudent);
        persons.put(newStudent.getId(), newStudent);
        DataLoader.saveAllStudents(students);
        DataLoader.saveAllPersons(persons);
        System.out.println("Student added successfully!");


    }
}