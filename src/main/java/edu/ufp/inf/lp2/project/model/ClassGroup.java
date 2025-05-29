package edu.ufp.inf.lp2.project.model;

import java.util.ArrayList;
import java.util.List;

public class ClassGroup {

    private final String subjectCode;
    private final String professorId;
    private final int capacity;
    private final List<String> enrolledStudentIds;

    public ClassGroup(String subjectCode, String professorId, int capacity) {
        this.subjectCode = subjectCode;
        this.professorId = professorId;
        this.capacity = capacity;
        this.enrolledStudentIds = new ArrayList<>();
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public String getProfessorId() {
        return professorId;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<String> getEnrolledStudentIds() {
        return enrolledStudentIds;
    }

    public boolean isFull() {
        return enrolledStudentIds.size() >= capacity;
    }

    public boolean addStudent(String studentId) {
        if (!isFull() && !enrolledStudentIds.contains(studentId)) {
            enrolledStudentIds.add(studentId);
            return true;
        }
        return false;
    }

    public void removeStudent(String studentId) {
        enrolledStudentIds.remove(studentId);
    }

    @Override
    public String toString() {
        return "Turma de " + subjectCode +
                " (professor: " + professorId +
                ", vagas: " + enrolledStudentIds.size() + "/" + capacity + ")";
    }
}
