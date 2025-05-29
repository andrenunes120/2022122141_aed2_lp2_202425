package edu.ufp.inf.lp2.project.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa um curso académico (grau), como uma licenciatura ou mestrado.
 */
public class Course {

    private final String code;          // Ex: "LEI"
    private String name;                // Ex: "Licenciatura em Engenharia Informática"
    private final List<String> subjectCodes;  // Lista de UCs associadas (LP2, AED2, etc.)

    public Course(String code, String name) {
        this.code = code;
        this.name = name;
        this.subjectCodes = new ArrayList<>();
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

    public void addSubject(String subjectCode) {
        subjectCodes.add(subjectCode);
    }

    public List<String> getSubjectCodes() {
        return subjectCodes;
    }

    @Override
    public String toString() {
        return code + " - " + name + " (" + subjectCodes.size() + " UCs)";
    }
}
