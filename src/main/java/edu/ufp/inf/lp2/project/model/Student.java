package edu.ufp.inf.lp2.project.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa um estudante da instituição.
 * Herda de {@link Person}.
 * Cada estudante possui um conjunto de UCs inscritas e um horário.
 */
public class Student extends Person {

    private List<String> enrolledSubjects;
    private List<SubjectSlot> schedule;

    /**
     * Construtor do estudante.
     *
     * @param id   ID do estudante
     * @param name Nome do estudante
     */
    public Student(String id, String name) {
        super(id, name);
        this.enrolledSubjects = new ArrayList<>();
        this.schedule = new ArrayList<>();
    }

    /**
     * Devolve a lista de códigos de UCs em que o estudante está inscrito.
     *
     * @return lista de códigos de UCs
     */
    public List<String> getEnrolledSubjects() {
        return enrolledSubjects;
    }

    /**
     * Adiciona uma nova UC à lista de inscritas.
     *
     * @param subjectCode código da UC
     */
    public void addSubject(String subjectCode) {
        enrolledSubjects.add(subjectCode);
    }

    /**
     * Devolve o horário completo do estudante (com UCs associadas).
     *
     * @return lista de {@link SubjectSlot}
     */
    public List<SubjectSlot> getSchedule() {
        return schedule;
    }

    /**
     * Adiciona um slot de horário ao estudante.
     *
     * @param timeSlot    horário
     * @param subjectCode código da UC
     */
    public void addSubjectSlot(TimeSlot timeSlot, String subjectCode) {
        this.schedule.add(new SubjectSlot(timeSlot, subjectCode));
    }

    /**
     * Adiciona vários horários (slots) ao horário do estudante.
     *
     * @param timeSlots   lista de horários
     * @param subjectCode código da UC associada a todos os horários
     */
    public void addAllSlots(List<TimeSlot> timeSlots, String subjectCode) {
        for (TimeSlot slot : timeSlots) {
            this.schedule.add(new SubjectSlot(slot, subjectCode));
        }
    }

    /**
     * Representação textual do estudante.
     *
     * @return string com ID, nome e nº de UCs inscritas
     */
    @Override
    public String toString() {
        return super.toString() + " | UCs: " + enrolledSubjects.size();
    }
}
