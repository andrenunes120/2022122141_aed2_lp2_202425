package edu.ufp.inf.lp2.project.test;

import edu.ufp.inf.lp2.project.model.*;
import edu.ufp.inf.lp2.project.manager.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public class TestesMilestone1 {

    public static void testarRequisitoR2() {
        StudentManager sm = new StudentManager();

        Student s1 = new Student("2022001", "André Nunes");
        Student s2 = new Student("2022002", "Joana Ribeiro");

        sm.addStudent(s1);
        sm.addStudent(s2);

        System.out.println("Lista de estudantes:");
        sm.listStudents();

        System.out.println("\nRemover estudante 2022001...");
        sm.removeStudent("2022001");

        System.out.println("Lista após remoção:");
        sm.listStudents();
    }

    public static void testarRequisitoR2_Subjects() {
        SubjectManager sm = new SubjectManager();

        Subject s1 = new Subject("LP2", "Linguagens de Programação II");
        Subject s2 = new Subject("AED2", "Algoritmos e Estruturas de Dados II");

        sm.addSubject(s1);
        sm.addSubject(s2);

        System.out.println("Lista de UCs:");
        sm.listSubjects();

        System.out.println("\nRemover UC AED2...");
        sm.removeSubject("AED2");

        System.out.println("Lista após remoção:");
        sm.listSubjects();
    }

    public static void testarRequisitoR4a() {
        RoomManager manager = new RoomManager();

        Room salaA = new Room("A101", 1, 30, 10);
        Room salaB = new Room("B202", 2, 50, 20);
        Room salaC = new Room("C303", 3, 20, 5);

        // Ocupa a sala B numa terça-feira de manhã
        TimeSlot ocupado = new TimeSlot(DayOfWeek.TUESDAY, LocalTime.of(10, 0), LocalTime.of(12, 0));
        salaB.addOccupiedSlot(ocupado);

        manager.addRoom(salaA);
        manager.addRoom(salaB);
        manager.addRoom(salaC);

        // Criar um horário para pesquisar (terça-feira às 11h)
        TimeSlot pesquisa = new TimeSlot(DayOfWeek.TUESDAY, LocalTime.of(11, 0), LocalTime.of(12, 0));
        List<Room> livres = manager.searchFreeRoomsAtSlot(pesquisa);

        System.out.println("Salas livres na terça-feira às 11h:");
        for (Room r : livres) {
            System.out.println(r);
        }
    }

    public static void testarInscricaoAlunoComHorario() {
        StudentManager sm = new StudentManager();
        SubjectManager smgr = new SubjectManager();

        // Criar aluno
        Student s = new Student("2022122141", "André Nunes");
        sm.addStudent(s);

        // Criar UC com horário
        Subject lp2 = new Subject("LP2", "Linguagens de Programação II");
        lp2.addTimeSlot(new TimeSlot(DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(12, 0)));
        lp2.addTimeSlot(new TimeSlot(DayOfWeek.WEDNESDAY, LocalTime.of(14, 0), LocalTime.of(16, 0)));
        smgr.addSubject(lp2);

        // Inscrever aluno
        s.addSubject(lp2.getCode());
        s.addAllSlots(lp2.getSchedule(), lp2.getCode());
        lp2.addStudent(s.getId());

        // Imprimir resultado
        System.out.println("Aluno inscrito em: " + s.getEnrolledSubjects());
        System.out.println("Horário do estudante:");
        for (SubjectSlot cs : s.getSchedule()) {
            System.out.println("  - " + cs);
        }
    }

    public static void main(String[] args) {
        //testarRequisitoR2();
        //testarRequisitoR2_Subjects();
        //testarRequisitoR4a();
        testarInscricaoAlunoComHorario();
    }
}
