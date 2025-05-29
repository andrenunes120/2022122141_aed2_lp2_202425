package edu.ufp.inf.lp2.project.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa uma Unidade Curricular (UC) da instituição.
 * Contém código, nome, curso associado, professores, estudantes e horários.
 */
public class Subject {

    private final String code;
    private String name;
    private String courseCode;
    private final List<String> professors;
    private final List<String> studentIds;
    private final List<TimeSlot> schedule;

    /**
     * Construtor de UC com código e nome.
     *
     * @param code Código da UC
     * @param name Nome da UC
     */
    public Subject(String code, String name) {
        this.code = code;
        this.name = name;
        this.professors = new ArrayList<>();
        this.studentIds = new ArrayList<>();
        this.schedule = new ArrayList<>();
    }

    /**
     * Construtor de UC com curso associado.
     *
     * @param code       Código da UC
     * @param name       Nome da UC
     * @param courseCode Código do curso a que pertence
     */
    public Subject(String code, String name, String courseCode) {
        this(code, name);
        this.courseCode = courseCode;
    }

    /**
     * Devolve o código da UC.
     *
     * @return código da UC
     */
    public String getCode() {
        return code;
    }

    /**
     * Devolve o nome da UC.
     *
     * @return nome da UC
     */
    public String getName() {
        return name;
    }

    /**
     * Altera o nome da UC.
     *
     * @param name novo nome
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Devolve o código do curso associado.
     *
     * @return código do curso
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * Define o curso associado à UC.
     *
     * @param courseCode código do curso
     */
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    /**
     * Devolve a lista de IDs de professores associados.
     *
     * @return lista de IDs
     */
    public List<String> getProfessors() {
        return professors;
    }

    /**
     * Adiciona um professor à UC.
     *
     * @param professorId ID do professor
     */
    public void addProfessor(String professorId) {
        professors.add(professorId);
    }

    /**
     * Devolve os IDs dos alunos inscritos na UC.
     *
     * @return lista de IDs de alunos
     */
    public List<String> getStudentIds() {
        return studentIds;
    }

    /**
     * Adiciona um aluno à UC.
     *
     * @param studentId ID do aluno
     */
    public void addStudent(String studentId) {
        studentIds.add(studentId);
    }

    /**
     * Devolve a lista de horários da UC.
     *
     * @return lista de {@link TimeSlot}
     */
    public List<TimeSlot> getSchedule() {
        return schedule;
    }

    /**
     * Adiciona um horário à UC.
     *
     * @param slot horário a adicionar
     */
    public void addTimeSlot(TimeSlot slot) {
        schedule.add(slot);
    }

    /**
     * Adiciona vários horários à UC.
     *
     * @param slots lista de horários
     */
    public void addTimeSlotAll(List<TimeSlot> slots) {
        schedule.addAll(slots);
    }

    /**
     * Representação textual da UC.
     *
     * @return string com código, nome, curso e contagem de professores/alunos
     */
    @Override
    public String toString() {
        String curso = (courseCode != null) ? courseCode : "sem curso";
        String profCount = professors.isEmpty() ? "Sem professores" : "Professores: " + professors.size();
        String alunoCount = studentIds.isEmpty() ? "Sem alunos" : "Alunos: " + studentIds.size();

        return code + " - " + name + " [" + curso + "] | " + profCount + " | " + alunoCount;
    }
}
