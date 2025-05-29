package edu.ufp.inf.lp2.project.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa um professor da instituição.
 * Herda de {@link Person}.
 * Cada professor pode lecionar várias UCs e tem uma lista de horários de atendimento.
 */
public class Professor extends Person {

    private List<String> teachingCourses;
    private List<TimeSlot> officeHours;

    /**
     * Construtor de professor.
     *
     * @param id    ID do professor
     * @param name  Nome do professor
     */
    public Professor(String id, String name) {
        super(id, name);
        this.teachingCourses = new ArrayList<>();
        this.officeHours = new ArrayList<>();
    }

    /**
     * Devolve a lista de códigos de UCs lecionadas pelo professor.
     *
     * @return lista de códigos de UCs
     */
    public List<String> getTeachingCourses() {
        return teachingCourses;
    }

    /**
     * Adiciona uma UC à lista de lecionadas pelo professor.
     *
     * @param courseCode código da UC
     */
    public void addTeachingCourse(String courseCode) {
        teachingCourses.add(courseCode);
    }

    /**
     * Devolve a lista de horários de atendimento do professor.
     *
     * @return lista de {@link TimeSlot}
     */
    public List<TimeSlot> getOfficeHours() {
        return officeHours;
    }

    /**
     * Adiciona um horário de atendimento.
     *
     * @param slot {@link TimeSlot} a adicionar
     */
    public void addOfficeHour(TimeSlot slot) {
        officeHours.add(slot);
    }

    /**
     * Representação textual do professor.
     *
     * @return ID, nome e número de UCs lecionadas
     */
    @Override
    public String toString() {
        return super.toString() + " | UCs: " + teachingCourses.size();
    }
}
