package edu.ufp.inf.lp2.project.test;

import edu.ufp.inf.lp2.project.manager.*;
import edu.ufp.inf.lp2.project.model.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.time.Duration;


public class R4 {
    public static void testarRequisitoR4a() {
        RoomManager manager = new RoomManager();

        Room salaA = new Room("FCHS-101", 1, 30, 10);
        Room salaB = new Room("FCHS-202", 2, 50, 20);
        Room salaC = new Room("FCHS-204", 2, 20, 5);
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

    public static void testarRequisitoR4b() {
        // Criar professores e adicioná-los ao gestor
        ProfessorManager professorManager = new ProfessorManager();
        Professor p1 = new Professor("P1", "João Viana");
        Professor p2 = new Professor("P2", "Rui Moreira");

        professorManager.addProfessor(p1);
        professorManager.addProfessor(p2);

        // Criar UCs e associar professores
        SubjectManager subjectManager = new SubjectManager();

        Subject lp2 = new Subject("LP2", "Linguagens de Programação II", "LEI");
        lp2.addProfessor("P1");
        lp2.addProfessor("P2");

        Subject aed2 = new Subject("AED2", "Algoritmos e Estruturas de Dados II", "LEI");
        aed2.addProfessor("P1");

        Subject rc = new Subject("RC", "Redes de Computadores", "LEI");

        subjectManager.addSubject(lp2);
        subjectManager.addSubject(aed2);
        subjectManager.addSubject(rc);

        // Listar professores de cada UC
        for (Subject uc : subjectManager.getAllSubjects()) {
            System.out.println("Professores da UC " + uc.getCode() + ":");

            if (uc.getProfessors().isEmpty()) {
                System.out.println("  - Sem professor(es)");
            } else {
                for (String profId : uc.getProfessors()) {
                    Professor prof = professorManager.getProfessor(profId);
                    if (prof != null) {
                        System.out.println("  - " + prof.getName());
                    } else {
                        System.out.println("  - ID desconhecido: " + profId);
                    }
                }
            }
        }
    }


    public static void testarRequisitoR4c() {
        StudentManager sm = new StudentManager();
        ClassGroupManager classGroupManager = new ClassGroupManager();

        // Criar e adicionar alunos
        sm.addStudent(new Student("2022001", "André Nunes"));
        sm.addStudent(new Student("2022002", "Joana Ribeiro"));
        sm.addStudent(new Student("2022003", "Carlos Silva"));

        ClassGroup turma1 = new ClassGroup("LP2", "P1", 30);
        turma1.addStudent("2022001");
        turma1.addStudent("2022002");

        ClassGroup turma2 = new ClassGroup("AED2", "P2", 25);
        turma2.addStudent("2022003");

        ClassGroup turma3 = new ClassGroup("RC", "P1", 30);
        turma3.addStudent("2022004");

        classGroupManager.addClassGroup(turma1);
        classGroupManager.addClassGroup(turma2);
        classGroupManager.addClassGroup(turma3);

        System.out.println("Turmas do professor P1:");
        for (ClassGroup turma : classGroupManager.getClassGroupsByProfessor("P1")) {
            System.out.println("  - UC: " + turma.getSubjectCode());
            for (String studentId : turma.getEnrolledStudentIds()) {
                Student s = sm.getStudent(studentId);
                System.out.println("    - " + (s != null ? s.getName() : "ID " + studentId + " não encontrado"));
            }
        }
        System.out.println("Turmas do professor P2:");
        for (ClassGroup turma : classGroupManager.getClassGroupsByProfessor("P2")) {
            System.out.println("  - UC: " + turma.getSubjectCode());
            for (String studentId : turma.getEnrolledStudentIds()) {
                Student s = sm.getStudent(studentId);
                if (s != null) {
                    System.out.println("    - " + s.getName());
                } else {
                    System.out.println("    - ID desconhecido: " + studentId);
                }
            }
        }
    }




    public static void testarRequisitoR4d() {
        // Criar gestores
        StudentManager studentManager = new StudentManager();
        SubjectManager subjectManager = new SubjectManager();
        ProfessorManager professorManager = new ProfessorManager();
        ClassGroupManager classGroupManager = new ClassGroupManager();

        // Criar professor com horários de atendimento
        Professor prof = new Professor("P1", "João Viana");
        prof.addOfficeHour(new TimeSlot(DayOfWeek.FRIDAY, LocalTime.of(10, 0), LocalTime.of(12, 0)));// livre
        prof.addOfficeHour(new TimeSlot(DayOfWeek.TUESDAY, LocalTime.of(10, 0), LocalTime.of(12, 0))); // conflito horario aed2
        professorManager.addProfessor(prof);

        // Criar UC com horário e associar professor
        Subject lp2 = new Subject("LP2", "Linguagens de Programação II", "LEI");
        lp2.addTimeSlot(new TimeSlot(DayOfWeek.MONDAY, LocalTime.of(9, 0), LocalTime.of(11, 0)));
        lp2.addTimeSlot(new TimeSlot(DayOfWeek.THURSDAY, LocalTime.of(14, 0), LocalTime.of(16, 0)));
        lp2.addProfessor("P1");
        subjectManager.addSubject(lp2);

        //Criar outro professor e UC com horario incompativel para o aluno ir a atendimento na tuesday
        Professor prof2 = new Professor("P2", "Rui Silva Moreira");
        professorManager.addProfessor(prof2);
        Subject aed2 = new Subject("AED2", "Algoritmos e Estruturas de Dados II", "LEI");
        aed2.addTimeSlot(new TimeSlot(DayOfWeek.TUESDAY, LocalTime.of(10, 0), LocalTime.of(12, 0)));
        aed2.addProfessor("P2");
        subjectManager.addSubject(aed2);

        // Criar aluno e inscrever via InscricaoManager
        Student aluno = new Student("2022001", "André Nunes");
        studentManager.addStudent(aluno);
        InscricaoManager.inscreverAlunoNaUC("2022001", "LP2", studentManager, subjectManager);
        InscricaoManager.inscreverAlunoNaUC("2022001", "AED2", studentManager, subjectManager);


        // Criar turma e adicionar aluno
        ClassGroup turma = new ClassGroup("LP2", "P1", 30);
        turma.addStudent("2022001");
        classGroupManager.addClassGroup(turma);
        ClassGroup turma2 = new ClassGroup("AED2", "P2", 25);
        turma2.addStudent("2022001");
        classGroupManager.addClassGroup(turma2);

        // Verificar horários disponíveis para atendimento
        System.out.println("Horários de atendimento compatíveis entre aluno e professor:");
        for (TimeSlot atendimento : prof.getOfficeHours()) {
            boolean conflito = false;
            for (SubjectSlot sSlot : aluno.getSchedule()) {
                if (atendimento.overlapsWith(sSlot.getTimeSlot())) {
                    conflito = true;
                    break;
                }
            }
            if (!conflito) {
                System.out.println("  - " + atendimento);
            }
        }
    }


    public static void testarRequisitoR4e() {
        RoomManager roomManager = new RoomManager();

        // Criar sala e adicionar ocupações
        Room sala = new Room("FCHS-101", 1, 30, 6);
        sala.addOccupiedSlot(new TimeSlot(DayOfWeek.MONDAY, LocalTime.of(9, 0), LocalTime.of(10, 0)));
        sala.addOccupiedSlot(new TimeSlot(DayOfWeek.MONDAY, LocalTime.of(10, 30), LocalTime.of(11, 30)));
        sala.addOccupiedSlot(new TimeSlot(DayOfWeek.TUESDAY, LocalTime.of(14, 0), LocalTime.of(15, 0)));
        roomManager.addRoom(sala);

        // Definir intervalo a analisar
        DayOfWeek dia = DayOfWeek.MONDAY;
        LocalTime inicio = LocalTime.of(8, 0);
        LocalTime fim = LocalTime.of(12, 0);

        // Calcular tempo total analisado (em minutos)
        int totalMinutos = (int) Duration.between(inicio, fim).toMinutes();

        // Somar minutos ocupados
        int ocupados = 0;
        for (TimeSlot slot : sala.getOccupiedSlots()) {
            if (slot.getDay() == dia) {
                LocalTime ini = slot.getStartTime().isBefore(inicio) ? inicio : slot.getStartTime();
                LocalTime fimReal = slot.getEndTime().isAfter(fim) ? fim : slot.getEndTime();
                if (ini.isBefore(fimReal)) {
                    ocupados += (int) Duration.between(ini, fimReal).toMinutes();
                }
            }
        }

        double taxa = (ocupados * 100.0) / totalMinutos;

        System.out.println("Taxa de ocupação da sala FCHS-101 na segunda-feira entre 08:00 e 12:00:");
        System.out.printf("  %d minutos ocupados de %d (%.2f%%)%n", ocupados, totalMinutos, taxa);
    }


    public static void testarRequisitoR4f() {
        RoomManager roomManager = new RoomManager();

        // Criar várias salas com diferentes características
        roomManager.addRoom(new Room("FCHS-101", 1, 30, 6));
        roomManager.addRoom(new Room("FCHS-102", 1, 40, 8));
        roomManager.addRoom(new Room("FCHS-201", 2, 20, 4));
        roomManager.addRoom(new Room("FCHS-202", 2, 35, 10));
        roomManager.addRoom(new Room("FCHS-203", 2, 25, 2));

        // Definir critérios de pesquisa
        int capacidadeMinima = 30;
        int tomadasMinimas = 6;

        System.out.println("Salas que cumprem os critérios:");
        System.out.println("Capacidade mínima: " + capacidadeMinima + ", Tomadas mínimas: " + tomadasMinimas);

        for (String code : roomManager.getAllRoomCodes()) {
            Room sala = roomManager.getRoom(code);
            if (sala.getCapacity() >= capacidadeMinima && sala.getNumSockets() >= tomadasMinimas) {
                System.out.println("  - " + sala);
            }
        }
    }





    public static void main(String[] args) {
        testarRequisitoR4a();
        System.out.println();
        testarRequisitoR4b();
        System.out.println();
        testarRequisitoR4c();
        System.out.println();
        testarRequisitoR4d();
        System.out.println();
        testarRequisitoR4e();
        System.out.println();
        testarRequisitoR4f();
    }
}
