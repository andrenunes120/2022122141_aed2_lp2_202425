package edu.ufp.inf.lp2.project.io;

import edu.ufp.inf.lp2.project.model.*;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.time.DayOfWeek;
import java.time.LocalTime;

/**
 * Utilitários para guardar e carregar dados de ficheiros .txt.
 */
public class FileUtils {

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


    public static List<Subject> loadSubjectsFromFile(String filename) {
        List<Subject> subjects = new ArrayList<>();
        Path path = Paths.get("DB", filename);
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] partes = line.split(";", 5);  // 5 partes: code, name, courseCode, horarios, profs

                if (partes.length >= 3) {
                    String code = partes[0];
                    String name = partes[1];
                    String courseCode = partes[2];

                    Subject subject = new Subject(code, name, courseCode);

                    // Ler horários
                    if (partes.length >= 4 && !partes[3].isEmpty()) {
                        String[] horarios = partes[3].split("\\|");
                        for (String h : horarios) {
                            String[] dados = h.split(",");
                            if (dados.length == 3) {
                                DayOfWeek dia = DayOfWeek.valueOf(dados[0]);
                                LocalTime inicio = LocalTime.parse(dados[1]);
                                LocalTime fim = LocalTime.parse(dados[2]);
                                subject.addTimeSlot(new TimeSlot(dia, inicio, fim));
                            }
                        }
                    }

                    // Ler professores
                    if (partes.length == 5 && !partes[4].isEmpty()) {
                        String[] profIds = partes[4].split(",");
                        for (String pid : profIds) {
                            subject.addProfessor(pid);
                        }
                    }

                    subjects.add(subject);
                }
            }
            System.out.println(subjects.size() + " UCs carregadas de '" + path + "'.");
        } catch (IOException e) {
            System.err.println("Erro ao ler o ficheiro: " + e.getMessage());
        }
        return subjects;
    }



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


    public static void saveClassGroupsToFile(String filename, Iterable<ClassGroup> turmas) {
        Path path = Paths.get("DB", filename);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (ClassGroup turma : turmas) {
                String alunos = String.join(",", turma.getEnrolledStudentIds());
                writer.write(turma.getSubjectCode() + ";" +
                        turma.getProfessorId() + ";" +
                        turma.getCapacity() + ";" +
                        alunos);
                writer.newLine();
            }
            System.out.println("Ficheiro '" + path + "' com turmas gravado com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao gravar turmas: " + e.getMessage());
        }
    }

    public static List<ClassGroup> loadClassGroupsFromFile(String filename) {
        List<ClassGroup> turmas = new ArrayList<>();
        Path path = Paths.get("DB", filename);
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] partes = line.split(";", 4);
                if (partes.length >= 3) {
                    String subjectCode = partes[0];
                    String professorId = partes[1];
                    int capacity = Integer.parseInt(partes[2]);

                    ClassGroup turma = new ClassGroup(subjectCode, professorId, capacity);

                    // Adicionar alunos, se existirem
                    if (partes.length == 4 && !partes[3].isEmpty()) {
                        String[] alunos = partes[3].split(",");
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
