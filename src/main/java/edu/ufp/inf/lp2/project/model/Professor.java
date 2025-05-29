package edu.ufp.inf.lp2.project.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa um professor da instituição.
 */
public class Professor extends Person {
    private List<String> teachingCourses;
    private List<TimeSlot> officeHours;

    public Professor(String id, String name) {
        super(id, name);
        this.teachingCourses = new ArrayList<>();
        this.officeHours = new ArrayList<>();
    }

    public List<String> getTeachingCourses() {
        return teachingCourses;
    }

    public void addTeachingCourse(String courseCode) {
        teachingCourses.add(courseCode);
    }

    public List<TimeSlot> getOfficeHours() {
        return officeHours;
    }

    public void addOfficeHour(TimeSlot slot) {
        officeHours.add(slot);
    }

    @Override
    public String toString() {
        return super.toString() + " | UCs: " + teachingCourses.size();
    }
}
