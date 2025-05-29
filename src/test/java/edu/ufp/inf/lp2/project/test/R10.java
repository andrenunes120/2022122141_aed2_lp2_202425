package edu.ufp.inf.lp2.project.test;

import edu.ufp.inf.lp2.project.io.FileUtils;
import edu.ufp.inf.lp2.project.model.*;
import edu.ufp.inf.lp2.project.manager.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class R10 {

    public static void testarGuardarAlunos() {
        List<Student> alunos = new ArrayList<>();
        alunos.add(new Student("2022001", "André Nunes"));
        alunos.add(new Student("2022002", "Joana Ribeiro"));
        alunos.add(new Student("2022003", "Carlos Silva"));
        FileUtils.saveStudentsToFile("alunos.txt", alunos);
    }

    public static void testarCarregarAlunos() {
        List<Student> alunos = FileUtils.loadStudentsFromFile("alunos.txt");
        System.out.println("Alunos carregados:");
        for (Student s : alunos) {
            System.out.println(s);
        }
    }

    public static void testarGuardarProfessores() {
        List<Professor> professores = new ArrayList<>();
        professores.add(new Professor("P1", "João Viana"));
        professores.add(new Professor("P2", "Rui Moreira"));
        professores.add(new Professor("P3", "Célio Carvalho"));
        FileUtils.saveProfessorsToFile("professores.txt", professores);
    }

    public static void testarCarregarProfessores() {
        List<Professor> professores = FileUtils.loadProfessorsFromFile("professores.txt");
        System.out.println("Professores carregados:");
        for (Professor p : professores) {
            System.out.println(p);
        }
    }

    public static void testarGuardarUCsComHorarioEProfessores() {
        List<Subject> ucs = new ArrayList<>();

        Subject lp2 = new Subject("LP2", "Linguagens de Programação II", "LEI");
        lp2.addTimeSlot(new TimeSlot(DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(12, 0)));
        lp2.addTimeSlot(new TimeSlot(DayOfWeek.WEDNESDAY, LocalTime.of(14, 0), LocalTime.of(16, 0)));
        lp2.addProfessor("P1");
        lp2.addProfessor("P2");

        Subject aed2 = new Subject("AED2", "Algoritmos e Estruturas de Dados II", "LEI");
        aed2.addTimeSlot(new TimeSlot(DayOfWeek.TUESDAY, LocalTime.of(9, 0), LocalTime.of(11, 0)));
        aed2.addProfessor("P1");

        Subject rc = new Subject("RC", "Redes de Computadores", "LEI");
        rc.addTimeSlot(new TimeSlot(DayOfWeek.THURSDAY, LocalTime.of(11, 0), LocalTime.of(13, 0)));
        rc.addProfessor("P3");

        ucs.add(lp2);
        ucs.add(aed2);
        ucs.add(rc);

        FileUtils.saveSubjectsToFile("ucs.txt", ucs);
    }

    public static void testarCarregarUCsComHorarioEProfessores() {
        List<Subject> ucs = FileUtils.loadSubjectsFromFile("ucs.txt");
        System.out.println("UCs carregadas:");
        for (Subject s : ucs) {
            System.out.println(s);
        }
    }

    public static void testarGuardarSalas() {
        List<Room> salas = new ArrayList<>();
        for (int i = 101; i <= 114; i++) {
            salas.add(new Room("FCHS-" + i, 1, 30, 6));
        }
        for (int i = 201; i <= 214; i++) {
            salas.add(new Room("FCHS-" + i, 2, 40, 8));
        }
        FileUtils.saveRoomsToFile("salas.txt", salas);
    }

    public static void testarCarregarSalas() {
        List<Room> salas = FileUtils.loadRoomsFromFile("salas.txt");
        System.out.println("Salas carregadas:");
        for (Room r : salas) {
            System.out.println(r);
        }
    }

    public static void testarGuardarCursos() {
        CourseManager cm = new CourseManager();
        Course lei = new Course("LEI", "Licenciatura em Engenharia Informática");
        lei.addSubject("LP1");
        lei.addSubject("LP2");
        lei.addSubject("AED1");
        lei.addSubject("AED2");
        lei.addSubject("RC");
        cm.addCourse(lei);
        FileUtils.saveCoursesToFile("cursos.txt", cm.getAllCourses());
    }

    public static void testarCarregarCursos() {
        List<Course> cursos = FileUtils.loadCoursesFromFile("cursos.txt");
        System.out.println("Cursos carregados:");
        for (Course c : cursos) {
            System.out.println("  - " + c.getCode() + ": " + c.getName());
            System.out.println("    UCs: " + String.join(", ", c.getSubjectCodes()));
        }
    }

    public static void testarGuardarTurmasComSala() {
        ClassGroupManager classGroupManager = new ClassGroupManager();

        ClassGroup turma1 = new ClassGroup("LP2", "P1", 30);
        turma1.setRoomCode("FCHS-202");
        turma1.addStudent("2022001");
        turma1.addStudent("2022002");

        ClassGroup turma2 = new ClassGroup("LP2", "P2", 30);
        turma2.setRoomCode("FCHS-203");
        turma2.addStudent("2022003");

        classGroupManager.addClassGroup(turma1);
        classGroupManager.addClassGroup(turma2);

        FileUtils.saveClassGroupsToFile("turmas.txt", classGroupManager.getAllClassGroups());
    }

    public static void testarCarregarTurmasComSala() {
        List<ClassGroup> turmas = FileUtils.loadClassGroupsFromFile("turmas.txt");
        System.out.println("Turmas carregadas:");
        for (ClassGroup t : turmas) {
            System.out.println(t);
        }
    }

    public static void main(String[] args) {
        testarGuardarAlunos();
        testarCarregarAlunos();
        System.out.println();
        testarGuardarProfessores();
        testarCarregarProfessores();
        System.out.println();
        testarGuardarUCsComHorarioEProfessores();
        testarCarregarUCsComHorarioEProfessores();
        System.out.println();
        testarGuardarSalas();
        testarCarregarSalas();
        System.out.println();
        testarGuardarCursos();
        testarCarregarCursos();
        System.out.println();
        testarGuardarTurmasComSala();
        testarCarregarTurmasComSala();
    }
}
