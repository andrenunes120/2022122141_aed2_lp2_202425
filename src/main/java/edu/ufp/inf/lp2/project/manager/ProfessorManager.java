package edu.ufp.inf.lp2.project.manager;

import edu.princeton.cs.algs4.ST;
import edu.ufp.inf.lp2.project.model.Professor;

import java.util.ArrayList;
import java.util.List;

/**
 * Gere os professores da instituição, usando uma Symbol Table com o ID como chave.
 */
public class ProfessorManager {

    private final ST<String, Professor> professors;

    /** Construtor que inicializa a estrutura de dados dos professores. */
    public ProfessorManager() {
        this.professors = new ST<>();
    }

    /**
     * Adiciona um novo professor.
     *
     * @param p professor a adicionar
     */
    public void addProfessor(Professor p) {
        professors.put(p.getId(), p);
    }

    /**
     * Remove um professor pelo seu ID.
     *
     * @param id ID do professor
     */
    public void removeProfessor(String id) {
        professors.delete(id);
    }

    /**
     * Obtém um professor pelo ID.
     *
     * @param id ID do professor
     * @return objeto {@link Professor}, ou null se não existir
     */
    public Professor getProfessor(String id) {
        return professors.get(id);
    }

    /**
     * Verifica se existe um professor com o ID especificado.
     *
     * @param id ID do professor
     * @return true se existir, false caso contrário
     */
    public boolean professorExists(String id) {
        return professors.contains(id);
    }

    /**
     * Devolve todos os IDs dos professores.
     *
     * @return Iterable com os IDs dos professores
     */
    public Iterable<String> getAllProfessorIds() {
        return professors.keys();
    }

    /**
     * Devolve uma lista com todos os professores.
     *
     * @return lista de {@link Professor}
     */
    public List<Professor> getAllProfessors() {
        List<Professor> list = new ArrayList<>();
        for (String id : professors.keys()) {
            list.add(professors.get(id));
        }
        return list;
    }

    /**
     * Imprime todos os professores na consola.
     */
    public void listProfessors() {
        for (String id : professors.keys()) {
            System.out.println(professors.get(id));
        }
    }
}
