package edu.ufp.inf.lp2.project.model;

/**
 * Representa um horário associado a uma Unidade Curricular (UC).
 * Combina um {@link TimeSlot} com o código da UC.
 */
public class SubjectSlot {

    private TimeSlot timeSlot;
    private String subjectCode;

    /**
     * Construtor de SubjectSlot.
     *
     * @param timeSlot     horário do slot
     * @param subjectCode  código da UC associada
     */
    public SubjectSlot(TimeSlot timeSlot, String subjectCode) {
        this.timeSlot = timeSlot;
        this.subjectCode = subjectCode;
    }

    /**
     * Devolve o {@link TimeSlot} deste slot.
     *
     * @return horário
     */
    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    /**
     * Devolve o código da UC associada.
     *
     * @return código da UC
     */
    public String getSubjectCode() {
        return subjectCode;
    }

    /**
     * Representação textual do slot (horário e código da UC).
     *
     * @return string com horário e código da UC
     */
    @Override
    public String toString() {
        return timeSlot + " [" + subjectCode + "]";
    }
}
