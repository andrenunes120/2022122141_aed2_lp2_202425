package edu.ufp.inf.lp2.project.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa um estudante da instituição.
 */
public class Student extends Person {

    private List<String> enrolledSubjects;  // era enrolledCourses
    private List<SubjectSlot> schedule;     // cada slot com TimeSlot + subjectCode

    public Student(String id, String name) {
        super(id, name);
        this.enrolledSubjects = new ArrayList<>();
        this.schedule = new ArrayList<>();
    }

    public List<String> getEnrolledSubjects() {
        return enrolledSubjects;
    }

    public void addSubject(String subjectCode) {
        enrolledSubjects.add(subjectCode);
    }

    public List<SubjectSlot> getSchedule() {
        return schedule;
    }

    public void addSubjectSlot(TimeSlot timeSlot, String subjectCode) {
        this.schedule.add(new SubjectSlot(timeSlot, subjectCode));
    }

    public void addAllSlots(List<TimeSlot> timeSlots, String subjectCode) {
        for (TimeSlot slot : timeSlots) {
            this.schedule.add(new SubjectSlot(slot, subjectCode));
        }
    }

    @Override
    public String toString() {
        return super.toString() + " | UCs: " + enrolledSubjects.size();
    }
}
