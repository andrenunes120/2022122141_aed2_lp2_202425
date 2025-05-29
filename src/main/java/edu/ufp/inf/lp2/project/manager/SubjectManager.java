package edu.ufp.inf.lp2.project.manager;

import edu.princeton.cs.algs4.ST;
import edu.ufp.inf.lp2.project.model.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 * Gere as Unidades Curriculares (Subjects) da instituição, com base numa Symbol Table.
 */
public class SubjectManager {

    private final ST<String, Subject> subjects;

    public SubjectManager() {
        this.subjects = new ST<>();
    }

    public void addSubject(Subject subject) {
        subjects.put(subject.getCode(), subject);
    }

    public void removeSubject(String code) {
        subjects.delete(code);
    }

    public Subject getSubject(String code) {
        return subjects.get(code);
    }

    public boolean subjectExists(String code) {
        return subjects.contains(code);
    }

    public Iterable<String> getAllSubjectCodes() {
        return subjects.keys();
    }

    public List<Subject> getAllSubjects() {
        List<Subject> list = new ArrayList<>();
        for (String code : subjects.keys()) {
            list.add(subjects.get(code));
        }
        return list;
    }

    public void listSubjects() {
        for (String code : subjects.keys()) {
            System.out.println(subjects.get(code));
        }
    }
}
