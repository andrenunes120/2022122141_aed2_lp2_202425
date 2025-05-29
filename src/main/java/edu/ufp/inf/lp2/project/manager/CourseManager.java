package edu.ufp.inf.lp2.project.manager;

import edu.princeton.cs.algs4.ST;
import edu.ufp.inf.lp2.project.model.Course;

import java.util.ArrayList;
import java.util.List;

/**
 * Gere os cursos académicos (ex: LEI, MEI) da instituição.
 */
public class CourseManager {

    private final ST<String, Course> courses;

    public CourseManager() {
        this.courses = new ST<>();
    }

    public void addCourse(Course course) {
        courses.put(course.getCode(), course);
    }

    public void removeCourse(String code) {
        courses.delete(code);
    }

    public Course getCourse(String code) {
        return courses.get(code);
    }

    public boolean existsCourse(String code) {
        return courses.contains(code);
    }

    public Iterable<String> getAllCourseCodes() {
        return courses.keys();
    }

    public List<Course> getAllCourses() {
        List<Course> list = new ArrayList<>();
        for (String code : courses.keys()) {
            list.add(courses.get(code));
        }
        return list;
    }

    public void listCourses() {
        for (String code : courses.keys()) {
            System.out.println(courses.get(code));
        }
    }
}
