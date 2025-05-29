package edu.ufp.inf.lp2.project.manager;

import edu.princeton.cs.algs4.ST;
import edu.ufp.inf.lp2.project.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Gere as turmas (ClassGroup) da instituição, agrupadas por código de UC.
 * Permite adicionar turmas, listar por UC ou professor, associar salas, etc.
 */
public class ClassGroupManager {

    /** Mapeia o código da UC para a lista de turmas dessa UC. */
    private final ST<String, List<ClassGroup>> turmas;

    /** Construtor padrão. */
    public ClassGroupManager() {
        this.turmas = new ST<>();
    }

    /**
     * Adiciona uma nova turma ao sistema.
     *
     * @param turma turma a adicionar
     */
    public void addClassGroup(ClassGroup turma) {
        List<ClassGroup> lista = turmas.get(turma.getSubjectCode());
        if (lista == null) {
            lista = new ArrayList<>();
            turmas.put(turma.getSubjectCode(), lista);
        }
        lista.add(turma);
    }

    /**
     * Obtém todas as turmas associadas a uma UC.
     *
     * @param subjectCode código da UC
     * @return lista de turmas da UC
     */
    public List<ClassGroup> getClassGroupsBySubject(String subjectCode) {
        List<ClassGroup> lista = turmas.get(subjectCode);
        return (lista != null) ? lista : new ArrayList<>();
    }

    /**
     * Obtém todas as turmas onde um professor está associado.
     *
     * @param professorId ID do professor
     * @return lista de turmas atribuídas ao professor
     */
    public List<ClassGroup> getClassGroupsByProfessor(String professorId) {
        List<ClassGroup> resultado = new ArrayList<>();

        for (String subjectCode : turmas.keys()) {
            for (ClassGroup turma : turmas.get(subjectCode)) {
                if (turma.getProfessorId().equals(professorId)) {
                    resultado.add(turma);
                }
            }
        }

        return resultado;
    }

    /**
     * Devolve todas as turmas registadas no sistema.
     *
     * @return lista com todas as turmas
     */
    public List<ClassGroup> getAllClassGroups() {
        List<ClassGroup> todas = new ArrayList<>();
        for (String uc : turmas.keys()) {
            todas.addAll(turmas.get(uc));
        }
        return todas;
    }

    /**
     * Lista todas as turmas na consola, com nome do professor e sala (se existir).
     *
     * @param pm gestor de professores (para obter nomes)
     */
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
                        ", sala: " + turma.getRoomCode() +
                        ", vagas: " + turma.getEnrolledStudentIds().size() + "/" + turma.getCapacity() + ")");
            }
        }
    }

    /**
     * Atribui uma sala a uma turma, com base na ordem (índice) dessa turma na lista da UC.
     *
     * @param subjectCode código da UC
     * @param indexTurma  índice da turma na lista
     * @param roomCode    código da sala
     */
    public void atribuirSalaATurma(String subjectCode, int indexTurma, String roomCode) {
        List<ClassGroup> turmasDaUc = turmas.get(subjectCode);
        if (turmasDaUc != null && indexTurma >= 0 && indexTurma < turmasDaUc.size()) {
            turmasDaUc.get(indexTurma).setRoomCode(roomCode);
        } else {
            System.out.println("Turma não encontrada para o código: " + subjectCode);
        }
    }

    /**
     * Remove todas as turmas do sistema.
     */
    public void clear() {
        for (String key : turmas.keys()) {
            turmas.delete(key);
        }
    }
}
