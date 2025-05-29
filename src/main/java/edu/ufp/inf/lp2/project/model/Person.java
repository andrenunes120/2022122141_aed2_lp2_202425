package edu.ufp.inf.lp2.project.model;

/**
 * Classe abstrata base para pessoas da instituição (ex: estudantes e professores).
 * Fornece os atributos comuns: ID e nome.
 */
public abstract class Person {
    private String id;
    private String name;

    /**
     * Construtor de uma pessoa.
     *
     * @param id    Identificador único da pessoa (ex: número de estudante ou professor).
     * @param name  Nome da pessoa.
     */
    public Person(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Devolve o ID da pessoa.
     *
     * @return ID da pessoa.
     */
    public String getId() {
        return id;
    }

    /**
     * Devolve o nome da pessoa.
     *
     * @return Nome da pessoa.
     */
    public String getName() {
        return name;
    }

    /**
     * Altera o nome da pessoa.
     *
     * @param name Novo nome a definir.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Representação textual da pessoa (ID e nome).
     *
     * @return String com ID e nome.
     */
    @Override
    public String toString() {
        return id + " - " + name;
    }
}
