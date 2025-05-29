package edu.ufp.inf.lp2.project.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa uma turma associada a uma unidade curricular.
 * Contém o professor responsável, capacidade, alunos inscritos e sala atribuída.
 */
public class ClassGroup {

    private final String subjectCode;
    private final String professorId;
    private final int capacity;
    private final List<String> enrolledStudentIds;
    private String roomCode;

    /**
     * Construtor da turma.
     *
     * @param subjectCode  código da UC
     * @param professorId  ID do professor responsável
     * @param capacity     capacidade máxima da turma
     */
    public ClassGroup(String subjectCode, String professorId, int capacity) {
        this.subjectCode = subjectCode;
        this.professorId = professorId;
        this.capacity = capacity;
        this.enrolledStudentIds = new ArrayList<>();
    }

    /** @return código da UC associada à turma */
    public String getSubjectCode() {
        return subjectCode;
    }

    /** @return ID do professor responsável pela turma */
    public String getProfessorId() {
        return professorId;
    }

    /** @return capacidade máxima da turma */
    public int getCapacity() {
        return capacity;
    }

    /** @return lista de IDs dos alunos inscritos */
    public List<String> getEnrolledStudentIds() {
        return enrolledStudentIds;
    }

    /** @return true se a turma estiver cheia */
    public boolean isFull() {
        return enrolledStudentIds.size() >= capacity;
    }

    /**
     * Adiciona um aluno à turma, se houver vaga e ainda não estiver inscrito.
     *
     * @param studentId ID do aluno
     * @return true se o aluno foi adicionado com sucesso
     */
    public boolean addStudent(String studentId) {
        if (!isFull() && !enrolledStudentIds.contains(studentId)) {
            enrolledStudentIds.add(studentId);
            return true;
        }
        return false;
    }

    /**
     * Remove um aluno da turma.
     *
     * @param studentId ID do aluno
     */
    public void removeStudent(String studentId) {
        enrolledStudentIds.remove(studentId);
    }

    /** @return código da sala atribuída à turma */
    public String getRoomCode() {
        return roomCode;
    }

    /**
     * Define a sala atribuída à turma.
     *
     * @param roomCode código da sala
     */
    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    @Override
    public String toString() {
        return "Turma de " + subjectCode +
                " (professor: " + professorId +
                ", sala: " + roomCode +
                ", vagas: " + enrolledStudentIds.size() + "/" + capacity + ")";
    }
}
