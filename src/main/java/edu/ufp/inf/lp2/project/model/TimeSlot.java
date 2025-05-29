package edu.ufp.inf.lp2.project.model;

import java.time.DayOfWeek;
import java.time.LocalTime;

/**
 * Representa um horário com dia da semana, hora de início e fim.
 */
public class TimeSlot {
    private DayOfWeek day;
    private LocalTime startTime;
    private LocalTime endTime;

    public TimeSlot(DayOfWeek day, LocalTime startTime, LocalTime endTime) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public boolean overlapsWith(TimeSlot other) {
        return this.day == other.day &&
                (this.startTime.isBefore(other.endTime) && this.endTime.isAfter(other.startTime));
    }

    @Override
    public String toString() {
        return day + " " + startTime + "-" + endTime;
    }
}
