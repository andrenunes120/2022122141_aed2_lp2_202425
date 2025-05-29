package edu.ufp.inf.lp2.project.manager;

import edu.princeton.cs.algs4.ST;
import edu.ufp.inf.lp2.project.model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Gere os estudantes da instituição, usando uma Symbol Table (ST) com o ID como chave.
 */
public class StudentManager {

    private final ST<String, Student> students;

    /** Construtor que inicializa a tabela de estudantes. */
    public StudentManager() {
        this.students = new ST<>();
    }

    /**
     * Adiciona um novo estudante.
     *
     * @param student o estudante a adicionar
     */
    public void addStudent(Student student) {
        students.put(student.getId(), student);
    }

    /**
     * Remove um estudante pelo ID.
     *
     * @param id ID do estudante a remover
     * @return o estudante removido, ou null se não existir
     */
    public Student removeStudent(String id) {
        Student removido = students.get(id);
        students.delete(id);
        return removido;
    }

    /**
     * Obtém um estudante pelo ID.
     *
     * @param id ID do estudante
     * @return objeto {@link Student} correspondente, ou null
     */
    public Student getStudent(String id) {
        return students.get(id);
    }

    /**
     * Verifica se existe um estudante com o ID dado.
     *
     * @param id ID do estudante
     * @return true se existir, false caso contrário
     */
    public boolean existsStudent(String id) {
        return students.contains(id);
    }

    /**
     * @return Iterable com todos os IDs de estudantes existentes
     */
    public Iterable<String> getAllStudentIds() {
        return students.keys();
    }

    /**
     * @return lista com todos os estudantes da tabela
     */
    public List<Student> getAllStudents() {
        List<Student> all = new ArrayList<>();
        for (String id : students.keys()) {
            all.add(students.get(id));
        }
        return all;
    }

    /**
     * Lista todos os estudantes na consola.
     */
    public void listStudents() {
        for (String id : students.keys()) {
            System.out.println(students.get(id));
        }
    }
}
