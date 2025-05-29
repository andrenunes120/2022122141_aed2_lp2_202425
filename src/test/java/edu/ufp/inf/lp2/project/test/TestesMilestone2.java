package edu.ufp.inf.lp2.project.test;

import edu.ufp.inf.lp2.project.io.FileUtils;
import edu.ufp.inf.lp2.project.model.*;
import edu.ufp.inf.lp2.project.manager.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TestesMilestone2 {

    // ==== ALUNOS ====

    public static void testarRequisitoR10_guardarAlunos() {
        List<Student> alunos = new ArrayList<>();
        alunos.add(new Student("2022001", "André Nunes"));
        alunos.add(new Student("2022002", "Joana Ribeiro"));
        alunos.add(new Student("2022003", "Carlos Silva"));

        FileUtils.saveStudentsToFile("alunos.txt", alunos);
    }

    public static void testarRequisitoR10_carregarAlunos() {
        List<Student> alunos = FileUtils.loadStudentsFromFile("alunos.txt");
        System.out.println("Alunos carregados:");
        for (Student s : alunos) {
            System.out.println(s);
        }
    }

    // ==== UCs ====

    public static void testarRequisitoR10_guardarUCs() {
        List<Subject> ucs = new ArrayList<>();
        ucs.add(new Subject("LP2", "Linguagens de Programação II"));
        ucs.add(new Subject("AED2", "Algoritmos e Estruturas de Dados II"));
        ucs.add(new Subject("RC", "Redes de Computadores"));

        FileUtils.saveSubjectsToFile("ucs.txt", ucs);
    }

    public static void testarRequisitoR10_carregarUCs() {
        List<Subject> ucs = FileUtils.loadSubjectsFromFile("ucs.txt");
        System.out.println("UCs carregadas:");
        for (Subject s : ucs) {
            System.out.println(s);
        }
    }

    // ==== SALAS ====

    public static void testarRequisitoR10_guardarSalas() {
        List<Room> salas = new ArrayList<>();

        for (int i = 101; i <= 114; i++) {
            salas.add(new Room("FCHS-" + i, 1, 30, 6));
        }

        for (int i = 201; i <= 214; i++) {
            salas.add(new Room("FCHS-" + i, 2, 40, 8));
        }

        FileUtils.saveRoomsToFile("salas.txt", salas);
    }

    public static void testarRequisitoR10_carregarSalas() {
        List<Room> salas = FileUtils.loadRoomsFromFile("salas.txt");

        System.out.println("Salas carregadas:");
        for (Room r : salas) {
            System.out.println(r);
        }
    }

    // ==== PROFESSORES ====

    public static void testarRequisitoR10_guardarProfessores() {
        List<Professor> professores = new ArrayList<>();
        professores.add(new Professor("P1", "João Viana"));
        professores.add(new Professor("P2", "Rui Silva Moreira"));
        professores.add(new Professor("P3", "José Torres"));
        professores.add(new Professor("P4", "Célio Carvalho"));

        FileUtils.saveProfessorsToFile("professores.txt", professores);
    }

    public static void testarRequisitoR10_carregarProfessores() {
        List<Professor> professores = FileUtils.loadProfessorsFromFile("professores.txt");

        System.out.println("Professores carregados:");
        for (Professor p : professores) {
            System.out.println(p);
        }
    }

    public static void testarRequisitoR10_guardarUCsComHorario() {
        List<Subject> ucs = new ArrayList<>();

        Subject lp2 = new Subject("LP2", "Linguagens de Programação II", "LEI");
        lp2.addTimeSlot(new TimeSlot(DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(12, 0)));
        lp2.addTimeSlot(new TimeSlot(DayOfWeek.WEDNESDAY, LocalTime.of(14, 0), LocalTime.of(16, 0)));

        Subject aed2 = new Subject("AED2", "Algoritmos e Estruturas de Dados II","LEI");
        aed2.addTimeSlot(new TimeSlot(DayOfWeek.TUESDAY, LocalTime.of(9, 0), LocalTime.of(11, 0)));

        Subject rc = new Subject("RC", "Redes de Computadores","LEI");
        rc.addTimeSlot(new TimeSlot(DayOfWeek.THURSDAY, LocalTime.of(11, 0), LocalTime.of(13, 0)));

        ucs.add(lp2);
        ucs.add(aed2);
        ucs.add(rc);

        FileUtils.saveSubjectsToFile("ucs.txt", ucs);
    }

    public static void testarInscreverAlunoEmUC() {
        StudentManager sm = new StudentManager();
        SubjectManager smgr = new SubjectManager();

        Student s = new Student("2022001", "André Nunes");
        sm.addStudent(s);

        Subject lp2 = new Subject("LP2", "Linguagens de Programação II");
        lp2.addTimeSlot(new TimeSlot(DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(12, 0)));
        lp2.addTimeSlot(new TimeSlot(DayOfWeek.WEDNESDAY, LocalTime.of(14, 0), LocalTime.of(16, 0)));
        smgr.addSubject(lp2);

        Subject aed2 = new Subject("AED2", "Algoritmos e Estruturas de Dados II");
        aed2.addTimeSlot(new TimeSlot(DayOfWeek.TUESDAY, LocalTime.of(9, 0), LocalTime.of(11, 0)));
        smgr.addSubject(aed2);

        InscricaoManager.inscreverAlunoNaUC("2022001", "LP2", sm, smgr);
        InscricaoManager.inscreverAlunoNaUC("2022001", "AED2", sm, smgr);

        System.out.println("Horário final do aluno:");
        for (SubjectSlot cs : s.getSchedule()) {
            System.out.println("  - " + cs);
        }

    }

    public static void testarGuardarECarregarCursos() {
        CourseManager cm = new CourseManager();

        // Criar um único curso: LEI
        Course lei = new Course("LEI", "Licenciatura em Engenharia Informática");
        lei.addSubject("LP1");
        lei.addSubject("LP2");
        lei.addSubject("AED1");
        lei.addSubject("AED2");
        lei.addSubject("RC");

        cm.addCourse(lei);

        // Guardar no ficheiro
        FileUtils.saveCoursesToFile("cursos.txt", cm.getAllCourses());

        // Carregar novamente do ficheiro
        List<Course> cursosCarregados = FileUtils.loadCoursesFromFile("cursos.txt");

        // Mostrar resultados
        System.out.println("Curso carregado:");
        for (Course c : cursosCarregados) {
            System.out.println("  - " + c.getCode() + ": " + c.getName());
            System.out.println("    UCs: " + String.join(", ", c.getSubjectCodes()));
        }
    }

    public static void testarAssociarProfessorAUc() {
        ProfessorManager pm = new ProfessorManager();
        SubjectManager sm = new SubjectManager();

        // Carregar professores e UCs do ficheiro
        List<Professor> professores = FileUtils.loadProfessorsFromFile("professores.txt");
        for (Professor p : professores) {
            pm.addProfessor(p);
        }

        List<Subject> ucs = FileUtils.loadSubjectsFromFile("ucs.txt");
        for (Subject s : ucs) {
            sm.addSubject(s);
        }

        // Associar professor à UC LP2
        InscricaoManager.associarProfessorAUc("P1001", "LP2", pm, sm);

        // Verificar resultado
        Subject lp2 = sm.getSubject("LP2");
        if (lp2 != null) {
            System.out.println("Professores da UC " + lp2.getCode() + ":");
            for (String profId : lp2.getProfessors()) {
                Professor prof = pm.getProfessor(profId);
                if (prof != null) {
                    System.out.println("  - " + prof.getName());
                }
            }
        }

        // Guardar UCs atualizadas sem apagar as outras
        FileUtils.saveSubjectsToFile("ucs.txt", sm.getAllSubjects());
    }


    public static void testarAssociarProfessorComConflito() {
        ProfessorManager pm = new ProfessorManager();
        SubjectManager sm = new SubjectManager();

        // Criar professor
        pm.addProfessor(new Professor("P1001", "João Viana"));

        // UC 1 - Segunda-feira 10h-12h
        Subject lp2 = new Subject("LP2", "Linguagens de Programação II", "LEI");
        lp2.addTimeSlot(new TimeSlot(DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(12, 0)));
        sm.addSubject(lp2);

        // UC 2 - Segunda-feira 11h-13h (sobreposição com LP2)
        Subject aed2 = new Subject("AED2", "Algoritmos e Estruturas de Dados II", "LEI");
        aed2.addTimeSlot(new TimeSlot(DayOfWeek.MONDAY, LocalTime.of(11, 0), LocalTime.of(13, 0)));
        sm.addSubject(aed2);

        // Associar professor à primeira UC (deve funcionar)
        InscricaoManager.associarProfessorAUc("P1001", "LP2", pm, sm);

        // Tentar associar à segunda (deve detetar conflito)
        InscricaoManager.associarProfessorAUc("P1001", "AED2", pm, sm);
    }


    public static void testarInscreverAlunoComConflito() {
        StudentManager sm = new StudentManager();
        SubjectManager smgr = new SubjectManager();

        // Criar aluno
        sm.addStudent(new Student("2022001", "André Nunes"));

        // UC 1 - terça 09h-11h
        Subject lp2 = new Subject("LP2", "Linguagens de Programação II");
        lp2.addTimeSlot(new TimeSlot(DayOfWeek.TUESDAY, LocalTime.of(9, 0), LocalTime.of(11, 0)));
        smgr.addSubject(lp2);

        // UC 2 - terça 10h-12h (conflito)
        Subject aed2 = new Subject("AED2", "Algoritmos e Estruturas de Dados II");
        aed2.addTimeSlot(new TimeSlot(DayOfWeek.TUESDAY, LocalTime.of(10, 0), LocalTime.of(12, 0)));
        smgr.addSubject(aed2);

        // Inscrever na primeira UC
        InscricaoManager.inscreverAlunoNaUC("2022001", "LP2", sm, smgr);

        // Tentar inscrever na segunda (deve dar conflito)
        InscricaoManager.inscreverAlunoNaUC("2022001", "AED2", sm, smgr);
    }



    public static void testarCriarTurma() {
        SubjectManager sm = new SubjectManager();
        ClassGroupManager classGroupManager = new ClassGroupManager();

        // Criar UC
        Subject lp2 = new Subject("LP2", "Linguagens de Programação II", "LEI");
        sm.addSubject(lp2);

        // Criar turma de LP2
        ClassGroup turma = new ClassGroup("LP2", "P1", 30);
        turma.addStudent("2022001");
        turma.addStudent("2022002");

        // Adicionar turma ao gestor
        classGroupManager.addClassGroup(turma);

        // Criar outra turma da mesma UC
        ClassGroup turma2 = new ClassGroup("LP2", "P2", 30);
        turma2.addStudent("2022003");
        classGroupManager.addClassGroup(turma2);

        // Listar todas as turmas de LP2
        System.out.println("Turmas de LP2:");
        for (ClassGroup t : classGroupManager.getClassGroupsBySubject("LP2")) {
            System.out.println(" - " + t);
        }

        // Guardar turmas
        FileUtils.saveClassGroupsToFile("turmas.txt", classGroupManager.getAllClassGroups());
    }



    public static void main(String[] args) {
        //testarRequisitoR10_guardarAlunos();
        //testarRequisitoR10_guardarUCs();
        //testarRequisitoR10_guardarSalas();
        //testarRequisitoR10_guardarProfessores();
        //testarRequisitoR10_guardarUCsComHorario();

        //testarRequisitoR10_carregarAlunos();
        //testarRequisitoR10_carregarUCs();
        //testarRequisitoR10_carregarSalas();
        //testarRequisitoR10_carregarProfessores();

        //testarInscreverAlunoEmUC();
        //testarGuardarECarregarCursos();
        //testarAssociarProfessorAUc();

        //testarAssociarProfessorComConflito();
        //testarInscreverAlunoComConflito();

        testarCriarTurma();
    }
}
