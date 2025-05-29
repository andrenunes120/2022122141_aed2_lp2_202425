package edu.ufp.inf.lp2.project.app;

import edu.ufp.inf.lp2.project.io.FileUtils;
import edu.ufp.inf.lp2.project.manager.*;
import edu.ufp.inf.lp2.project.model.*;

import java.util.List;
import java.util.Scanner;

/**
 * Classe principal da aplicação.
 * Permite ler dados de ficheiros, consultar, adicionar e eliminar entidades.
 */
public class MainApp {

    private static final StudentManager studentManager = new StudentManager();
    private static final ProfessorManager professorManager = new ProfessorManager();
    private static final SubjectManager subjectManager = new SubjectManager();
    private static final RoomManager roomManager = new RoomManager();
    private static final CourseManager courseManager = new CourseManager();
    private static final ClassGroupManager classGroupManager = new ClassGroupManager();


    public static void main(String[] args) {
        carregarDadosIniciais();


        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n=== Menu Principal ===");
            System.out.println("1. Menu Estudante");
            System.out.println("2. Menu Professor");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            switch (opcao) {
                case 1:
                    menuEstudante(scanner);
                    break;
                case 2:
                    menuProfessor(scanner);
                    break;
                case 0:
                    System.out.println("A sair...");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        } while (opcao != 0);
    }

    private static void carregarDadosIniciais() {
        List<Student> alunos = FileUtils.loadStudentsFromFile("alunos.txt");
        for (Student s : alunos) {
            studentManager.addStudent(s);
        }

        List<Professor> profs = FileUtils.loadProfessorsFromFile("professores.txt");
        for (Professor p : profs) {
            professorManager.addProfessor(p);
        }

        List<Subject> ucs = FileUtils.loadSubjectsFromFile("ucs.txt");
        for (Subject s : ucs) {
            subjectManager.addSubject(s);
        }

        List<Room> salas = FileUtils.loadRoomsFromFile("salas.txt");
        for (Room r : salas) {
            roomManager.addRoom(r);
        }

        List<Course> cursos = FileUtils.loadCoursesFromFile("cursos.txt");
        for (Course c : cursos) {
            courseManager.addCourse(c);
        }

        for (Student s : studentManager.getAllStudents()) {
            System.out.println("Reconstruindo horários para: " + s.getName());
            System.out.println("  UCs inscritas: " + s.getEnrolledSubjects());

            for (String ucCode : s.getEnrolledSubjects()) {
                Subject uc = subjectManager.getSubject(ucCode);
                if (uc != null) {
                    System.out.println("  -> Adicionando horários de " + ucCode + ": " + uc.getSchedule());
                    s.addAllSlots(uc.getSchedule(), uc.getCode());
                } else {
                    System.out.println("  -> UC " + ucCode + " não encontrada!");
                }
            }

            System.out.println("  Horário final:");
            for (SubjectSlot ss : s.getSchedule()) {
                System.out.println("    - " + ss);
            }
        }

        List<ClassGroup> turmas = FileUtils.loadClassGroupsFromFile("turmas.txt");
        for (ClassGroup tg : turmas) {
            classGroupManager.addClassGroup(tg);
        }


        System.out.println("Dados carregados com sucesso.");
    }

    private static void menuEstudante(Scanner scanner) {
        int opcao;
        do {
            System.out.println("\n--- Menu Estudante ---");
            System.out.println("1. Listar estudantes");
            System.out.println("2. Adicionar estudante");
            System.out.println("3. Remover estudante");
            System.out.println("4. Ver horário do estudante");
            System.out.println("5. Inscrever estudante em UC");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    studentManager.listStudents();
                    break;
                case 2:
                    adicionarEstudante(scanner);
                    FileUtils.saveStudentsToFile("alunos.txt", studentManager.getAllStudents());
                    break;
                case 3:
                    removerEstudante(scanner);
                    FileUtils.saveStudentsToFile("alunos.txt", studentManager.getAllStudents());
                    break;
                case 4:
                    System.out.print("ID do estudante: ");
                    String id = scanner.nextLine();
                    Student aluno = studentManager.getStudent(id);
                    if (aluno != null) {
                        System.out.println("Horário do estudante:");
                        for (SubjectSlot cs : aluno.getSchedule()) {
                            System.out.println("  - " + cs);
                        }
                    } else {
                        System.out.println("Estudante não encontrado.");
                    }
                    break;
                case 5:
                    System.out.print("ID do estudante: ");
                    String studentId = scanner.nextLine();

                    System.out.println("UCs disponíveis:");
                    for (Course curso : courseManager.getAllCourses()) {
                        System.out.println(">> " + curso.getCode() + ": " + curso.getName());
                        for (String ucCode : curso.getSubjectCodes()) {
                            Subject s = subjectManager.getSubject(ucCode);
                            if (s != null) {
                                System.out.println("   - " + s.getCode() + ": " + s.getName());
                            }
                        }
                    }

                    System.out.print("Código da UC: ");
                    String subjectCode = scanner.nextLine();

                    InscricaoManager.inscreverAlunoNaUC(studentId, subjectCode, studentManager, subjectManager);
                    FileUtils.saveStudentsToFile("alunos.txt", studentManager.getAllStudents());
                    break;

                case 0:
                    System.out.println("A voltar ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        } while (opcao != 0);
    }

    private static void menuProfessor(Scanner scanner) {
        int opcao;
        do {
            System.out.println("\n--- Menu Professor ---");
            System.out.println("1. Listar professores");
            System.out.println("2. Adicionar professor");
            System.out.println("3. Remover professor");
            System.out.println("4. Associar professor a UC");
            System.out.println("5. Ver UCs associadas ao professor");
            System.out.println("6. Criar nova turma");
            System.out.println("7. Listar turmas");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    professorManager.listProfessors();
                    break;
                case 2:
                    adicionarProfessor(scanner);
                    FileUtils.saveProfessorsToFile("professores.txt", professorManager.getAllProfessors());
                    break;
                case 3:
                    removerProfessor(scanner);
                    FileUtils.saveProfessorsToFile("professores.txt", professorManager.getAllProfessors());
                    break;
                case 4:
                    System.out.print("ID do professor: ");
                    String profId = scanner.nextLine();

                    Professor prof = professorManager.getProfessor(profId);
                    if (prof == null) {
                        System.out.println("Professor não encontrado.");
                        break;
                    }

                    System.out.println("UCs disponíveis:");
                    for (Subject s : subjectManager.getAllSubjects()) {
                        System.out.println("  - " + s.getCode() + ": " + s.getName());
                    }

                    System.out.print("Código da UC: ");
                    String ucCode = scanner.nextLine();

                    if (subjectManager.getSubject(ucCode) == null) {
                        System.out.println("UC não encontrada.");
                        break;
                    }

                    InscricaoManager.associarProfessorAUc(profId, ucCode, professorManager, subjectManager);
                    FileUtils.saveSubjectsToFile("ucs.txt", subjectManager.getAllSubjects());
                    break;
                case 5:
                    System.out.print("ID do professor: ");
                    String idProf = scanner.nextLine();

                    Professor profRef = professorManager.getProfessor(idProf);
                    if (profRef == null) {
                        System.out.println("Professor não encontrado.");
                        break;
                    }

                    System.out.println("UCs atribuídas ao professor " + profRef.getName() + ":");
                    boolean encontrou = false;
                    for (Subject s : subjectManager.getAllSubjects()) {
                        if (s.getProfessors().contains(idProf)) {
                            System.out.println("  - " + s.getCode() + ": " + s.getName());
                            encontrou = true;
                        }
                    }

                    if (!encontrou) {
                        System.out.println("  (Nenhuma UC associada)");
                    }
                    break;
                case 6:
                    System.out.println("Criar nova turma:");

                    System.out.print("Código da UC: ");
                    String subjectCode = scanner.nextLine();
                    Subject uc = subjectManager.getSubject(subjectCode);
                    if (uc == null) {
                        System.out.println("UC não encontrada.");
                        break;
                    }

                    System.out.print("ID do professor responsável: ");
                    String professorId = scanner.nextLine();
                    Professor professor = professorManager.getProfessor(professorId);
                    if (professor == null) {
                        System.out.println("Professor não encontrado.");
                        break;
                    }

                    System.out.print("Capacidade da turma: ");
                    int capacidade = scanner.nextInt();
                    scanner.nextLine(); // limpar buffer

                    ClassGroup novaTurma = new ClassGroup(subjectCode, professorId, capacidade);
                    classGroupManager.addClassGroup(novaTurma);

                    FileUtils.saveClassGroupsToFile("turmas.txt", classGroupManager.getAllClassGroups());

                    System.out.println("Turma criada com sucesso.");
                    break;
                case 7:
                    System.out.println("Todas as turmas existentes:");
                    classGroupManager.listClassGroups(professorManager);
                    break;
                case 0:
                    System.out.println("A voltar ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        } while (opcao != 0);
    }

    private static void adicionarEstudante(Scanner scanner) {
        System.out.print("ID do estudante: ");
        String id = scanner.nextLine();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        studentManager.addStudent(new Student(id, nome));
        System.out.println("Estudante adicionado.");
    }

    private static void removerEstudante(Scanner scanner) {
        System.out.print("ID do estudante a remover: ");
        String id = scanner.nextLine();
        if (studentManager.existsStudent(id)) {
            studentManager.removeStudent(id);
            System.out.println("Estudante removido.");
        } else {
            System.out.println("Estudante não encontrado.");
        }
    }

    private static void adicionarProfessor(Scanner scanner) {
        System.out.print("ID do professor: ");
        String id = scanner.nextLine();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        professorManager.addProfessor(new Professor(id, nome));
        System.out.println("Professor adicionado.");
    }

    private static void removerProfessor(Scanner scanner) {
        System.out.print("ID do professor a remover: ");
        String id = scanner.nextLine();
        if (professorManager.professorExists(id)) {
            professorManager.removeProfessor(id);
            System.out.println("Professor removido.");
        } else {
            System.out.println("Professor não encontrado.");
        }
    }
}
