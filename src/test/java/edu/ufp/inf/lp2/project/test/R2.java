package edu.ufp.inf.lp2.project.test;
import edu.ufp.inf.lp2.project.model.*;
import edu.ufp.inf.lp2.project.manager.*;
import java.util.List;
import edu.ufp.inf.lp2.project.io.FileUtils;
import java.util.ArrayList;

public class R2 {

    public static void testarRequisitoR2() {
        StudentManager sm = new StudentManager();

        Student s1 = new Student("2022001", "André Nunes");
        Student s2 = new Student("2022002", "Joana Ribeiro");

        sm.addStudent(s1);
        sm.addStudent(s2);

        System.out.println("Lista de estudantes:");
        sm.listStudents();

        System.out.println("Remover estudante 2022001...");
        sm.removeStudent("2022001");

        System.out.println("Lista após remoção:");
        sm.listStudents();
    }

    public static void testarRequisitoR2_Subjects() {
        SubjectManager sm = new SubjectManager();

        Subject s1 = new Subject("LP2", "Linguagens de Programação II","LEI");
        Subject s2 = new Subject("AED2", "Algoritmos e Estruturas de Dados II","LEI");

        sm.addSubject(s1);
        sm.addSubject(s2);

        System.out.println("Lista de UCs:");
        sm.listSubjects();

        System.out.println("Remover UC AED2...");
        sm.removeSubject("AED2");

        System.out.println("Lista após remoção:");
        sm.listSubjects();
    }

    public static void testarEditarAluno() {
        StudentManager sm = new StudentManager();
        Student s = new Student("2022001", "Joana Silva");
        sm.addStudent(s);

        System.out.println("Antes da edição: " + sm.getStudent("2022001"));

        s.setName("Joana Ribeiro");
        System.out.println("Depois da edição: " + sm.getStudent("2022001"));
    }

    public static void testarEditarUC() {
        SubjectManager sm = new SubjectManager();
        Subject uc = new Subject("LP2", "Linguagens II", "LEI");
        sm.addSubject(uc);

        System.out.println("Antes da edição: " + sm.getSubject("LP2"));

        uc.setName("Linguagens de Programação II");
        System.out.println("Depois da edição: " + sm.getSubject("LP2"));
    }

    public static void testarValidacaoIntegridadeProfessorUC() {
        ProfessorManager pm = new ProfessorManager();
        SubjectManager sm = new SubjectManager();

        Professor p = new Professor("P1", "Rui Moreira");
        pm.addProfessor(p);

        Subject s = new Subject("LP2", "Linguagens de Programação II", "LEI");
        s.addProfessor(p.getId());
        sm.addSubject(s);

        System.out.println("Validação de integridade:");
        for (String profId : s.getProfessors()) {
            if (pm.getProfessor(profId) != null) {
                System.out.println("  Professor " + profId + " encontrado: OK");
            } else {
                System.out.println("  Professor " + profId + " inexistente: ERRO");
            }
        }
    }

    public static void testarArquivoAlunoRemovido() {
        StudentManager sm = new StudentManager();
        List<Student> arquivo = new ArrayList<>();

        Student s = new Student("2022001", "Carlos Silva");
        sm.addStudent(s);

        Student removido = sm.removeStudent("2022001");
        if (removido != null) {
            arquivo.add(removido);
            FileUtils.saveStudentsToFile("arquivo_alunos.txt", arquivo);
        }

        System.out.println("Alunos arquivados:");
        for (Student a : arquivo) {
            System.out.println(a);
        }
    }

    public static void main(String[] args) {
        testarRequisitoR2();
        System.out.println();
        testarRequisitoR2_Subjects();
        System.out.println();
        testarEditarAluno();
        System.out.println();
        testarEditarUC();
        System.out.println();
        testarValidacaoIntegridadeProfessorUC();
        System.out.println();
        testarArquivoAlunoRemovido();
    }
}