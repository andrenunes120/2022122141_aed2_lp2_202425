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

    /** Construtor que inicializa a tabela de Unidades Curriculares. */
    public SubjectManager() {
        this.subjects = new ST<>();
    }

    /**
     * Adiciona uma nova UC à tabela.
     *
     * @param subject objeto {@link Subject} a adicionar
     */
    public void addSubject(Subject subject) {
        subjects.put(subject.getCode(), subject);
    }

    /**
     * Remove uma UC pelo código.
     *
     * @param code código da UC a remover
     */
    public void removeSubject(String code) {
        subjects.delete(code);
    }

    /**
     * Obtém uma UC pelo seu código.
     *
     * @param code código da UC
     * @return objeto {@link Subject}, ou null se não existir
     */
    public Subject getSubject(String code) {
        return subjects.get(code);
    }

    /**
     * Verifica se uma UC existe.
     *
     * @param code código da UC
     * @return true se existir, false caso contrário
     */
    public boolean subjectExists(String code) {
        return subjects.contains(code);
    }

    /**
     * Devolve todos os códigos de UC existentes.
     *
     * @return Iterable com os códigos de todas as UCs
     */
    public Iterable<String> getAllSubjectCodes() {
        return subjects.keys();
    }

    /**
     * Devolve todas as UCs existentes.
     *
     * @return lista de objetos {@link Subject}
     */
    public List<Subject> getAllSubjects() {
        List<Subject> list = new ArrayList<>();
        for (String code : subjects.keys()) {
            list.add(subjects.get(code));
        }
        return list;
    }

    /**
     * Lista todas as UCs para a consola.
     */
    public void listSubjects() {
        for (String code : subjects.keys()) {
            System.out.println(subjects.get(code));
        }
        System.out.println("\n");
    }
}
