package edu.ufp.inf.lp2.project.manager;

import edu.princeton.cs.algs4.ST;
import edu.ufp.inf.lp2.project.model.Course;

import java.util.ArrayList;
import java.util.List;

/**
 * Gere os cursos académicos da instituição, como licenciaturas e mestrados.
 * Utiliza uma Symbol Table para associar códigos de curso a objetos {@link Course}.
 */
public class CourseManager {

    private final ST<String, Course> courses;

    /** Construtor que inicializa a tabela de cursos. */
    public CourseManager() {
        this.courses = new ST<>();
    }

    /**
     * Adiciona um curso à tabela.
     *
     * @param course curso a adicionar
     */
    public void addCourse(Course course) {
        courses.put(course.getCode(), course);
    }

    /**
     * Remove um curso pelo seu código.
     *
     * @param code código do curso a remover
     */
    public void removeCourse(String code) {
        courses.delete(code);
    }

    /**
     * Obtém um curso dado o seu código.
     *
     * @param code código do curso
     * @return o objeto {@link Course}, ou null se não existir
     */
    public Course getCourse(String code) {
        return courses.get(code);
    }

    /**
     * Verifica se existe um curso com o código dado.
     *
     * @param code código do curso
     * @return true se existir, false caso contrário
     */
    public boolean existsCourse(String code) {
        return courses.contains(code);
    }

    /**
     * @return todos os códigos dos cursos existentes
     */
    public Iterable<String> getAllCourseCodes() {
        return courses.keys();
    }

    /**
     * @return lista de todos os cursos armazenados
     */
    public List<Course> getAllCourses() {
        List<Course> list = new ArrayList<>();
        for (String code : courses.keys()) {
            list.add(courses.get(code));
        }
        return list;
    }

    /**
     * Lista todos os cursos na consola.
     */
    public void listCourses() {
        for (String code : courses.keys()) {
            System.out.println(courses.get(code));
        }
    }
}
