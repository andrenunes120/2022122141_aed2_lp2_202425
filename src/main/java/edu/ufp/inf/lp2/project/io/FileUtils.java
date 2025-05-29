package edu.ufp.inf.lp2.project.io;

import edu.ufp.inf.lp2.project.model.*;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.time.DayOfWeek;
import java.time.LocalTime;

/**
 * Classe utilitária com métodos estáticos para guardar e carregar entidades do projeto
 * (alunos, professores, UCs, cursos, salas, turmas) em ficheiros `.txt`.
 */
public class FileUtils {

    /**
     * Guarda alunos num ficheiro `.txt`.
     * Formato: ID;Nome;UC1,UC2,...
     *
     * @param filename nome do ficheiro
     * @param students lista de estudantes
     */
    public static void saveStudentsToFile(String filename, Iterable<Student> students) {
        Path path = Paths.get("DB", filename);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (Student s : students) {
                String ucs = String.join(",", s.getEnrolledSubjects());
                writer.write(s.getId() + ";" + s.getName() + ";" + ucs);
                writer.newLine();
            }
            System.out.println("Ficheiro '" + path + "' gravado com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao escrever o ficheiro: " + e.getMessage());
        }
    }

    /**
     * Carrega alunos de um ficheiro `.txt`.
     *
     * @param filename nome do ficheiro
     * @return lista de estudantes carregados
     */
    public static List<Student> loadStudentsFromFile(String filename) {
        List<Student> students = new ArrayList<>();
        Path path = Paths.get("DB", filename);
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] partes = line.split(";", 3);
                if (partes.length >= 2) {
                    Student s = new Student(partes[0], partes[1]);

                    if (partes.length == 3 && !partes[2].isEmpty()) {
                        String[] ucs = partes[2].split(",");
                        for (String cod : ucs) {
                            s.addSubject(cod);
                        }
                    }

                    students.add(s);
                }
            }
            System.out.println(students.size() + " alunos carregados de '" + path + "'.");
        } catch (IOException e) {
            System.err.println("Erro ao ler o ficheiro: " + e.getMessage());
        }
        return students;
    }

    /**
     * Guarda UCs num ficheiro `.txt`, com horários e professores.
     * Formato: Código;Nome;Curso;Prof1,Prof2,...;DIA,HH:mm,HH:mm|...
     *
     * @param filename nome do ficheiro
     * @param subjects lista de UCs
     */
    public static void saveSubjectsToFile(String filename, Iterable<Subject> subjects) {
        Path path = Paths.get("DB", filename);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (Subject s : subjects) {
                String professores = String.join(",", s.getProfessors());
                StringBuilder horarios = new StringBuilder();
                for (TimeSlot t : s.getSchedule()) {
                    if (horarios.length() > 0) horarios.append("|");
                    horarios.append(t.getDay()).append(",")
                            .append(t.getStartTime()).append(",")
                            .append(t.getEndTime());
                }

                writer.write(s.getCode() + ";" + s.getName() + ";" + s.getCourseCode() + ";" +
                        professores + ";" + horarios);
                writer.newLine();
            }
            System.out.println("Ficheiro '" + path + "' gravado com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao escrever o ficheiro: " + e.getMessage());
        }
    }

    /**
     * Carrega UCs de um ficheiro `.txt`, incluindo horários e professores.
     *
     * @param filename nome do ficheiro
     * @return lista de UCs
     */
    public static List<Subject> loadSubjectsFromFile(String filename) {
        List<Subject> subjects = new ArrayList<>();
        Path path = Paths.get("DB", filename);

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] partes = line.split(";", 5);
                if (partes.length >= 3) {
                    String code = partes[0];
                    String name = partes[1];
                    String course = partes[2];

                    Subject subject = new Subject(code, name, course);

                    // Carregar professores (partes[3])
                    if (partes.length >= 4 && !partes[3].isEmpty()) {
                        for (String pid : partes[3].split(",")) {
                            if (!pid.isBlank()) {
                                subject.addProfessor(pid.trim());
                            }
                        }
                    }

                    // Carregar horários (partes[4])
                    if (partes.length == 5 && !partes[4].isEmpty()) {
                        for (String slotStr : partes[4].split("\\|")) {
                            String[] dados = slotStr.split(",");
                            if (dados.length == 3) {
                                DayOfWeek dia = DayOfWeek.valueOf(dados[0].trim());
                                LocalTime inicio = LocalTime.parse(dados[1].trim());
                                LocalTime fim = LocalTime.parse(dados[2].trim());
                                subject.addTimeSlot(new TimeSlot(dia, inicio, fim));
                            }
                        }
                    }

                    subjects.add(subject);
                }
            }

            System.out.println(subjects.size() + " UCs carregadas de 'DB/" + filename + "'.");

        } catch (IOException e) {
            System.err.println("Erro ao carregar UCs: " + e.getMessage());
        }

        return subjects;
    }

    /**
     * Guarda salas num ficheiro `.txt`.
     * Formato: Código;Piso;Capacidade;Número de tomadas
     *
     * @param filename nome do ficheiro
     * @param rooms lista de salas
     */
    public static void saveRoomsToFile(String filename, Iterable<Room> rooms) {
        Path path = Paths.get("DB", filename);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (Room r : rooms) {
                writer.write(r.getCode() + ";" + r.getFloor() + ";" + r.getCapacity() + ";" + r.getNumSockets());
                writer.newLine();
            }
            System.out.println("Ficheiro '" + path + "' gravado com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao escrever o ficheiro: " + e.getMessage());
        }
    }

    /**
     * Carrega salas de um ficheiro `.txt`.
     *
     * @param filename nome do ficheiro
     * @return lista de salas carregadas
     */
    public static List<Room> loadRoomsFromFile(String filename) {
        List<Room> rooms = new ArrayList<>();
        Path path = Paths.get("DB", filename);
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] partes = line.split(";");
                if (partes.length >= 4) {
                    String code = partes[0];
                    int floor = Integer.parseInt(partes[1]);
                    int capacity = Integer.parseInt(partes[2]);
                    int sockets = Integer.parseInt(partes[3]);
                    rooms.add(new Room(code, floor, capacity, sockets));
                }
            }
            System.out.println(rooms.size() + " salas carregadas de '" + path + "'.");
        } catch (IOException e) {
            System.err.println("Erro ao ler o ficheiro: " + e.getMessage());
        }
        return rooms;
    }

    /**
     * Guarda professores num ficheiro `.txt`.
     * Formato: ID;Nome
     *
     * @param filename nome do ficheiro
     * @param professors lista de professores
     */
    public static void saveProfessorsToFile(String filename, Iterable<Professor> professors) {
        Path path = Paths.get("DB", filename);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (Professor p : professors) {
                writer.write(p.getId() + ";" + p.getName());
                writer.newLine();
            }
            System.out.println("Ficheiro '" + path + "' gravado com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao escrever o ficheiro: " + e.getMessage());
        }
    }

    public static List<Professor> loadProfessorsFromFile(String filename) {
        List<Professor> professors = new ArrayList<>();
        Path path = Paths.get("DB", filename);
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] partes = line.split(";");
                if (partes.length >= 2) {
                    professors.add(new Professor(partes[0], partes[1]));
                }
            }
            System.out.println(professors.size() + " professores carregados de '" + path + "'.");
        } catch (IOException e) {
            System.err.println("Erro ao ler o ficheiro: " + e.getMessage());
        }
        return professors;
    }

    /**
     * Carrega professores de um ficheiro `.txt`.
     *
     * @param filename nome do ficheiro
     * @return lista de professores
     */
    public static void saveCoursesToFile(String filename, Iterable<Course> courses) {
        Path path = Paths.get("DB", filename);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (Course c : courses) {
                String ucs = String.join(",", c.getSubjectCodes());
                writer.write(c.getCode() + ";" + c.getName() + ";" + ucs);
                writer.newLine();
            }
            System.out.println("Ficheiro '" + path + "' gravado com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao escrever o ficheiro: " + e.getMessage());
        }
    }

    /**
     * Carrega cursos de um ficheiro `.txt`.
     * Formato: Código;Nome;UC1,UC2,...
     *
     * @param filename nome do ficheiro
     * @return lista de cursos carregados
     */
    public static List<Course> loadCoursesFromFile(String filename) {
        List<Course> cursos = new ArrayList<>();
        Path path = Paths.get("DB", filename);
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] partes = line.split(";", 3);
                if (partes.length >= 2) {
                    Course curso = new Course(partes[0], partes[1]);

                    if (partes.length == 3 && !partes[2].isEmpty()) {
                        String[] subjectCodes = partes[2].split(",");
                        for (String code : subjectCodes) {
                            curso.addSubject(code);
                        }
                    }

                    cursos.add(curso);
                }
            }
            System.out.println(cursos.size() + " cursos carregados de '" + path + "'.");
        } catch (IOException e) {
            System.err.println("Erro ao ler o ficheiro: " + e.getMessage());
        }
        return cursos;
    }


    /**
     * Guarda turmas (ClassGroups) num ficheiro `.txt`.
     * Formato: Código da UC;ID do professor;Capacidade;Código da sala;ID1,ID2,...
     *
     * @param filename nome do ficheiro
     * @param turmas lista de turmas
     */
    public static void saveClassGroupsToFile(String filename, Iterable<ClassGroup> turmas) {
        Path path = Paths.get("DB", filename);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (ClassGroup turma : turmas) {
                String alunos = String.join(",", turma.getEnrolledStudentIds());
                writer.write(turma.getSubjectCode() + ";" +
                        turma.getProfessorId() + ";" +
                        turma.getCapacity() + ";" +
                        turma.getRoomCode() + ";" +
                        alunos);
                writer.newLine();
            }
            System.out.println("Ficheiro '" + path + "' com turmas gravado com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao gravar turmas: " + e.getMessage());
        }
    }

    /**
     * Carrega turmas (ClassGroups) de um ficheiro `.txt`.
     * Formato: Código da UC;ID do professor;Capacidade;Código da sala;ID1,ID2,...
     *
     * @param filename nome do ficheiro
     * @return lista de turmas carregadas
     */
    public static List<ClassGroup> loadClassGroupsFromFile(String filename) {
        List<ClassGroup> turmas = new ArrayList<>();
        Path path = Paths.get("DB", filename);
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] partes = line.split(";", 5);  // ← agora lês 5 campos

                if (partes.length >= 4) {
                    String subjectCode = partes[0];
                    String professorId = partes[1];
                    int capacity = Integer.parseInt(partes[2]);
                    String roomCode = partes[3];

                    ClassGroup turma = new ClassGroup(subjectCode, professorId, capacity);
                    turma.setRoomCode(roomCode);

                    if (partes.length == 5 && !partes[4].isEmpty()) {
                        String[] alunos = partes[4].split(",");
                        for (String a : alunos) {
                            turma.addStudent(a);
                        }
                    }

                    turmas.add(turma);
                }
            }
            System.out.println(turmas.size() + " turmas carregadas de '" + path + "'.");
        } catch (IOException e) {
            System.err.println("Erro ao ler turmas: " + e.getMessage());
        }
        return turmas;
    }

}
