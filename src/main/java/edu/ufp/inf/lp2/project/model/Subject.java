package edu.ufp.inf.lp2.project.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa uma Unidade Curricular (UC).
 */
public class Subject {

    private final String code;
    private String name;
    private String courseCode;  // NOVO: código do curso académico (ex: LEI)
    private final List<String> professors;
    private final List<String> studentIds;
    private final List<TimeSlot> schedule;

    public Subject(String code, String name) {
        this.code = code;
        this.name = name;
        this.professors = new ArrayList<>();
        this.studentIds = new ArrayList<>();
        this.schedule = new ArrayList<>();
    }

    // NOVO construtor com curso
    public Subject(String code, String name, String courseCode) {
        this(code, name);
        this.courseCode = courseCode;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public List<String> getProfessors() {
        return professors;
    }

    public void addProfessor(String professorId) {
        professors.add(professorId);
    }

    public List<String> getStudentIds() {
        return studentIds;
    }

    public void addStudent(String studentId) {
        studentIds.add(studentId);
    }

    public List<TimeSlot> getSchedule() {
        return schedule;
    }

    public void addTimeSlot(TimeSlot slot) {
        schedule.add(slot);
    }

    public void addTimeSlotAll(List<TimeSlot> slots) {
        schedule.addAll(slots);
    }

    @Override
    public String toString() {
        return code + " - " + name + " [" + courseCode + "] | Professores: " + professors.size() + " | Alunos: " + studentIds.size();
    }
}
