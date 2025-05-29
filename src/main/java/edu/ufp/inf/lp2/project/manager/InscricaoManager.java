package edu.ufp.inf.lp2.project.manager;

import edu.ufp.inf.lp2.project.model.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Classe responsável pela gestão de inscrições de alunos e associações de professores a UCs.
 */
public class InscricaoManager {

    /**
     * Verifica se há conflito de horários entre os horários existentes e os novos.
     *
     * @param existentes Lista de horários já atribuídos
     * @param novos      Lista de novos horários a verificar
     * @return true se houver conflito, false caso contrário
     */
    public static boolean conflitoHorario(List<TimeSlot> existentes, List<TimeSlot> novos) {
        for (TimeSlot novo : novos) {
            for (TimeSlot existente : existentes) {
                if (novo.overlapsWith(existente)) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * Inscreve um aluno numa UC e copia os horários da UC para o horário do aluno.
     *
     * @param studentId   ID do aluno
     * @param subjectCode Código da UC
     * @param sm          StudentManager
     * @param smgr        SubjectManager
     */
    public static void inscreverAlunoNaUC(String studentId, String subjectCode, StudentManager sm, SubjectManager smgr) {

        Student aluno = sm.getStudent(studentId);
        Subject subject = smgr.getSubject(subjectCode);

        if (aluno == null) {
            System.out.println("Aluno com ID " + studentId + " não encontrado.");
            return;
        }

        if (subject == null) {
            System.out.println("UC com código " + subjectCode + " não encontrada.");
            return;
        }

        if (aluno.getEnrolledSubjects().contains(subjectCode)) {
            System.out.println("Aluno já está inscrito nesta UC.");
            return;
        }

        // Obter horários já atribuídos ao aluno
        List<TimeSlot> horarios = aluno.getSchedule().stream().map(SubjectSlot::getTimeSlot).toList();
        // Verificar conflito
        if (conflitoHorario(horarios, subject.getSchedule())) {
            System.out.println("Conflito de horário: o aluno já tem outra aula nesse horário.");
            return;
        }

        aluno.addSubject(subjectCode);
        aluno.addAllSlots(subject.getSchedule(), subjectCode);
        subject.addStudent(studentId);

        System.out.println("Aluno '" + aluno.getName() + "' inscrito na UC '" + subject.getName() + "'.");
    }

    /**
     * Associa um professor a uma UC, verificando conflitos de horário.
     *
     * @param professorId ID do professor
     * @param subjectCode Código da UC
     * @param pm          ProfessorManager
     * @param sm          SubjectManager
     */
    public static void associarProfessorAUc(String professorId, String subjectCode, ProfessorManager pm, SubjectManager sm) {
        Professor prof = pm.getProfessor(professorId);
        Subject uc = sm.getSubject(subjectCode);

        if (prof == null) {
            System.out.println("Professor com ID " + professorId + " não encontrado.");
            return;
        }

        if (uc == null) {
            System.out.println("UC com código " + subjectCode + " não encontrada.");
            return;
        }

        if (uc.getProfessors().contains(professorId)) {
            System.out.println("O professor já está associado a esta UC.");
            return;
        }

        // Obter todas as UCs onde o professor já está associado
        List<Subject> ucsAtuais = sm.getAllSubjects().stream()
                .filter(s -> s.getProfessors().contains(professorId))
                .toList();

        // Extrair os horários dessas UCs
        List<TimeSlot> horarios = ucsAtuais.stream()
                .flatMap(s -> s.getSchedule().stream())
                .toList();

        // Verificar conflito com a nova UC
        if (conflitoHorario(horarios, uc.getSchedule())) {
            System.out.println("Conflito de horário: o professor já tem outra aula nesse horário.");
            return;
        }


        uc.addProfessor(professorId);
        System.out.println("Professor " + prof.getName() + " atribuído à UC " + uc.getName() + ".");
    }

}
