package edu.ufp.inf.lp2.project.model;

import java.time.DayOfWeek;
import java.time.LocalTime;

/**
 * Representa um horário com dia da semana, hora de início e hora de fim.
 * Utilizado para modelar horários de aulas e atendimentos.
 */
public class TimeSlot {
    private DayOfWeek day;
    private LocalTime startTime;
    private LocalTime endTime;

    /**
     * Construtor de TimeSlot.
     *
     * @param day        Dia da semana (ex: MONDAY)
     * @param startTime  Hora de início
     * @param endTime    Hora de fim
     */
    public TimeSlot(DayOfWeek day, LocalTime startTime, LocalTime endTime) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Devolve o dia da semana deste slot.
     *
     * @return dia da semana
     */
    public DayOfWeek getDay() {
        return day;
    }

    /**
     * Devolve a hora de início.
     *
     * @return hora de início
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * Devolve a hora de fim.
     *
     * @return hora de fim
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * Verifica se este TimeSlot se sobrepõe com outro.
     *
     * @param other outro TimeSlot a comparar
     * @return true se houver sobreposição
     */
    public boolean overlapsWith(TimeSlot other) {
        return this.day == other.day &&
                (this.startTime.isBefore(other.endTime) && this.endTime.isAfter(other.startTime));
    }

    /**
     * Representação textual do TimeSlot.
     *
     * @return string com dia e intervalo de horas
     */
    @Override
    public String toString() {
        return day + " " + startTime + "-" + endTime;
    }
}
