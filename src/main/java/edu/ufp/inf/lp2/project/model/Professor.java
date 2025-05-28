package edu.ufp.inf.lp2.project.model;

import edu.princeton.cs.algs4.ST;

public class Professor extends Person {
    private String officeRoom;
    private String officeHours;
    private ST<String, String> teachingCourses; // Course code -> Course name

    public Professor(String id, String name, String email, String contact, String officeRoom, String officeHours) {
        super(id, name, email, contact);
        this.officeRoom = officeRoom;
        this.officeHours = officeHours;
        this.teachingCourses = new ST<>();
    }

    public String getOfficeRoom() {
        return officeRoom;
    }

    public void setOfficeRoom(String officeRoom) {
        this.officeRoom = officeRoom;
    }

    public String getOfficeHours() {
        return officeHours;
    }

    public void setOfficeHours(String officeHours) {
        this.officeHours = officeHours;
    }

    public ST<String, String> getTeachingCourses() {
        return teachingCourses;
    }

    public void assignCourse(String courseCode, String courseName) {
        if (!teachingCourses.contains(courseCode)) {
            teachingCourses.put(courseCode, courseName);

        }
        
    }

    public void removeCourse(String courseCode) {
        if (teachingCourses.contains(courseCode)) {
            String courseName = teachingCourses.get(courseCode);
            teachingCourses.delete(courseCode);
            System.out.println("Professor " + getName() + " removed from course " + courseName);
        } else {
            System.out.println("Professor " + getName() + " is not assigned to course with code " + courseCode);
        }
    }

    @Override
    public String toString() {
        return "Professor{" +
               "id='" + getId() + '\'' +
               ", name='" + getName() + '\'' +
               ", officeRoom='" + officeRoom + '\'' +
               ", officeHours='" + officeHours + '\'' +
               ", teachingCoursesCount=" + teachingCourses.size() +
               '}';
    }
}