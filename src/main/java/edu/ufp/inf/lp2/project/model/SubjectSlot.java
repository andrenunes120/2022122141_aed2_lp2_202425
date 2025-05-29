package edu.ufp.inf.lp2.project.model;

/**
 * Representa um horário associado a uma Unidade Curricular.
 */
public class SubjectSlot {
    private TimeSlot timeSlot;
    private String subjectCode;

    public SubjectSlot(TimeSlot timeSlot, String subjectCode) {
        this.timeSlot = timeSlot;
        this.subjectCode = subjectCode;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    @Override
    public String toString() {
        return timeSlot + " [" + subjectCode + "]";
    }
}
