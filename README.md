# Project Documentation

## Thought Process Log

### Initial Setup and Class Creation (Current Session)

**Goal:** Recreate the `Person`, `Student`, and `Professor` classes with basic content and document the thought process.

**Steps Taken:**

1.  **Confirmed Project Status:** After previous discussions, it was established that the `project1` directory was renamed to `project`, and the `model` directory was empty. The core task is to recreate the `Person`, `Student`, and `Professor` classes within the `edu.ufp.inf.lp2.project.model` package.

2.  **Created `Person.java`:**
    *   **Reasoning:** This is the abstract base class for `Student` and `Professor`, encapsulating common attributes like ID, name, email, and contact. It provides a foundation for inheritance and promotes code reusability.
    *   **Implementation:** Defined `Person` as an `abstract class`. Included basic fields, a constructor, getters, setters (except for ID to maintain immutability), and overridden `equals`, `hashCode`, and `toString` methods for proper object comparison and representation. The `edu.princeton.cs.algs4.ST` import was included as it was part of the previous design pattern, even though it's not directly used in `Person` itself, it's a dependency for `Student` and `Professor`.

3.  **Created `Student.java`:**
    *   **Reasoning:** This class extends `Person` and adds student-specific attributes and functionalities. It needs to manage enrolled courses and a schedule.
    *   **Implementation:** Extended `Person`. Added `studentId`, `enrolledCourses` (using `ST<String, String>` for course code to course name mapping), and `schedule` fields. Implemented methods for `enrollCourse` and `dropCourse` with basic validation, and a `setSchedule` method. The constructor calls the superclass constructor and initializes student-specific fields. Overrode `toString()` to include student details and enrolled course count.

4.  **Created `Professor.java`:**
    *   **Reasoning:** This class also extends `Person` and includes professor-specific attributes and functionalities, such as office details and teaching courses.
    *   **Implementation:** Extended `Person`. Added `officeRoom`, `officeHours`, and `teachingCourses` (using `ST<String, String>` for course code to course name mapping) fields. Implemented methods for `assignCourse` and `removeCourse` with basic validation. The constructor calls the superclass constructor and initializes professor-specific fields. Overrode `toString()` to include professor details and teaching course count.

5.  **Documented Thought Process:** This `README.md` file is being updated to reflect the steps taken and the reasoning behind each decision, as requested by the user. This ensures transparency and provides a clear log of the development process.

**Next Steps:** Awaiting further instructions from the user regarding additional functionalities or refinements for these classes.

### Terminal Menu Implementation

**Goal:** Implement a terminal-based menu for interaction with the application.

**Steps Taken:**

1.  **Created `MainApp.java`:** A new class <mcfile name="MainApp.java" path="/Users/andrenunes120/Desktop/Projetos/2022122141_aed2_lp2_202425/src/main/java/edu/ufp/inf/lp2/project/MainApp.java"></mcfile> was created in the `edu.ufp.inf.lp2.project` package. This class serves as the entry point for the application and contains the main loop for the terminal menu.
    *   The menu currently provides options for adding/viewing Students and Professors, and exiting the application.
    *   Basic input handling and error checking for numerical input are included.
    *   The `addStudent` and `addProfessor` functionalities have been temporarily disabled in the menu, with corresponding messages displayed to the user.
    *   The `viewStudents` and `viewProfessors` methods are still active and allow viewing of registered entities.
    *   The `MainApp` now reflects the user's preference to not add new students or professors via the terminal for now.

### Current Project Status (After Recreation)

**Goal:** Recreate the `Person`, `Student`, and `Professor` classes after accidental deletion and directory renaming.

**Steps Taken:**

1.  **Recreated `Person.java`:** The abstract `Person` class was recreated in <mcfile name="Person.java" path="/Users/andrenunes120/Desktop/Projetos/2022122141_aed2_lp2_202425/src/main/java/edu/ufp/inf/lp2/project/model/Person.java"></mcfile> with its original fields (id, name, email, contact), constructor, getters, setters (except for ID), and overridden `equals`, `hashCode`, and `toString` methods.

2.  **Recreated `Student.java`:** The `Student` class was recreated in <mcfile name="Student.java" path="/Users/andrenunes120/Desktop/Projetos/2022122141_aed2_lp2_202425/src/main/java/edu/ufp/inf/lp2/project/model/Student.java"></mcfile>, extending `Person`. It includes `studentId`, `enrolledCourses` (using `ST`), and `schedule` fields, along with methods for course enrollment/dropping and schedule management.

3.  **Recreated `Professor.java`:** The `Professor` class was recreated in <mcfile name="Professor.java" path="/Users/andrenunes120/Desktop/Projetos/2022122141_aed2_lp2_202425/src/main/java/edu/ufp/inf/lp2/project/model/Professor.java"></mcfile>, extending `Person`. It includes `officeRoom`, `officeHours`, and `teachingCourses` (using `ST`) fields, along with methods for assigning/removing courses.

These classes are now present in the `edu.ufp.inf.lp2.project.model` package, ready for further development.

### Data Persistence Setup

To prepare for data persistence, the following text files have been created and populated with random data in the `DB` directory:

- `persons.txt`: Contains common `Person` data (ID, Name, Email, Contact).
- `students.txt`: Contains `Student`-specific data (Student ID, Course, Year).
- `professors.txt`: Contains `Professor`-specific data (Professor ID, Department, Office, Office Hours), with updated room names.

### Room Class Implementation

- **`Room.java`**: A new class has been created in the `edu.ufp.inf.lp2.project.model` package to represent classrooms. It includes fields for `roomNumber` (e.g., FCHS-101, FCHS-201) and `capacity`, along with a constructor, getters, setters, and overridden `toString`, `equals`, and `hashCode` methods.

### Room Data Persistence

- **`rooms.txt`**: A new text file has been created in the `DB` directory to store room data. It contains room numbers (FCHS-101 to FCHS-120 for the first floor and FCHS-201 to FCHS-220 for the second floor) and their capacities.

These files and classes will be used to save and load application data, ensuring that the system's state can be preserved across sessions.

### Recent Updates (Current Session)

**Goal:** Implement new functionalities and fix issues related to person and student management.

**Steps Taken:**

1.  **Expanded Main Menu in `MainApp.java`:**
    *   Added "Add Person" and "View All Persons" options to the main menu.
    *   Renumbered existing menu options to accommodate the new additions.

2.  **Implemented `addPerson` and `removePerson` in `MainApp.java`:**
    *   Created a new `addPerson` method to allow users to add generic `Person` objects.
    *   Implemented `removePerson` to allow removal of persons by ID.
    *   Integrated these methods into the `MainApp`'s switch statement.

3.  **Added `savePerson` and `saveAllPersons` to `DataLoader.java`:**
    *   Introduced `savePerson` to persist individual `Person` objects.
    *   Implemented `saveAllPersons` to write the entire collection of persons to `persons.txt`.

4.  **Fixed `DataLoader.java` Imports:**
    *   Resolved compilation errors by adding necessary `java.io` imports (`PrintWriter`, `FileWriter`, `BufferedWriter`) to `DataLoader.java`.

5.  **Improved `addStudentFromPerson` in `MainApp.java`:**
    *   Modified the `addStudentFromPerson` method to correctly associate selected courses with a new `Student` object.
    *   Ensured that the `Student` object's `courses` list is populated before saving.
    *   Removed redundant prompts for course options to streamline user input.

These updates enhance the application's functionality for managing persons and students, including proper data persistence for new entries and course enrollments.