package edu.ufp.inf.lp2.project.manager;

import edu.princeton.cs.algs4.ST;
import edu.ufp.inf.lp2.project.model.Professor;

import java.util.ArrayList;
import java.util.List;

/**
 * Gere os professores da instituição, usando uma Symbol Table.
 */
public class ProfessorManager {

    private final ST<String, Professor> professors;

    public ProfessorManager() {
        this.professors = new ST<>();
    }

    public void addProfessor(Professor p) {
        professors.put(p.getId(), p);
    }

    public void removeProfessor(String id) {
        professors.delete(id);
    }

    public Professor getProfessor(String id) {
        return professors.get(id);
    }

    public boolean professorExists(String id) {
        return professors.contains(id);
    }

    public Iterable<String> getAllProfessorIds() {
        return professors.keys();
    }

    public List<Professor> getAllProfessors() {
        List<Professor> list = new ArrayList<>();
        for (String id : professors.keys()) {
            list.add(professors.get(id));
        }
        return list;
    }

    public void listProfessors() {
        for (String id : professors.keys()) {
            System.out.println(professors.get(id));
        }
    }
}
