package edu.ufp.inf.lp2.project.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa um curso académico (grau), como uma licenciatura ou mestrado.
 * Cada curso possui um código, nome e lista de códigos de UCs associadas.
 */
public class Course {

    private final String code;          // Ex: "LEI"
    private String name;                // Ex: "Licenciatura em Engenharia Informática"
    private final List<String> subjectCodes;  // Lista de UCs associadas (LP2, AED2, etc.)

    /**
     * Construtor para criar um curso com código e nome.
     *
     * @param code O código do curso (ex: "LEI").
     * @param name O nome do curso (ex: "Licenciatura em Engenharia Informática").
     */
    public Course(String code, String name) {
        this.code = code;
        this.name = name;
        this.subjectCodes = new ArrayList<>();
    }

    /**
     * Devolve o código do curso.
     *
     * @return Código do curso.
     */
    public String getCode() {
        return code;
    }

    /**
     * Devolve o nome do curso.
     *
     * @return Nome do curso.
     */
    public String getName() {
        return name;
    }

    /**
     * Altera o nome do curso.
     *
     * @param name Novo nome do curso.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Adiciona uma Unidade Curricular (UC) ao curso.
     *
     * @param subjectCode Código da UC a adicionar (ex: "LP2").
     */
    public void addSubject(String subjectCode) {
        subjectCodes.add(subjectCode);
    }

    /**
     * Devolve a lista de códigos de UCs associadas ao curso.
     *
     * @return Lista de códigos de UCs.
     */
    public List<String> getSubjectCodes() {
        return subjectCodes;
    }

    /**
     * Representação textual do curso.
     *
     * @return String com código, nome e número de UCs.
     */
    @Override
    public String toString() {
        return code + " - " + name + " (" + subjectCodes.size() + " UCs)";
    }
}
