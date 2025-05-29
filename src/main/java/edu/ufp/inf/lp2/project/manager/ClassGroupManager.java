package edu.ufp.inf.lp2.project.manager;

import edu.princeton.cs.algs4.ST;
import edu.ufp.inf.lp2.project.model.*;

import java.util.ArrayList;
import java.util.List;

public class ClassGroupManager {

    // Mapeia o código da UC para a lista de turmas dessa UC
    private final ST<String, List<ClassGroup>> turmas;

    public ClassGroupManager() {
        this.turmas = new ST<>();
    }

    public void addClassGroup(ClassGroup turma) {
        List<ClassGroup> lista = turmas.get(turma.getSubjectCode());
        if (lista == null) {
            lista = new ArrayList<>();
            turmas.put(turma.getSubjectCode(), lista);
        }
        lista.add(turma);
    }

    public List<ClassGroup> getClassGroupsBySubject(String subjectCode) {
        List<ClassGroup> lista = turmas.get(subjectCode);
        return (lista != null) ? lista : new ArrayList<>();
    }

    public List<ClassGroup> getAllClassGroups() {
        List<ClassGroup> todas = new ArrayList<>();
        for (String uc : turmas.keys()) {
            todas.addAll(turmas.get(uc));
        }
        return todas;
    }

    public void listClassGroups(ProfessorManager pm) {
        for (String uc : turmas.keys()) {
            for (ClassGroup turma : turmas.get(uc)) {
                String profName = turma.getProfessorId();
                Professor prof = pm.getProfessor(turma.getProfessorId());
                if (prof != null) {
                    profName = prof.getName();
                }

                System.out.println("Turma de " + turma.getSubjectCode() +
                        " (professor: " + profName +
                        ", vagas: " + turma.getEnrolledStudentIds().size() + "/" + turma.getCapacity() + ")");
            }
        }
    }

    public void clear() {
        for (String key : turmas.keys()) {
            turmas.delete(key);
        }
    }
}
