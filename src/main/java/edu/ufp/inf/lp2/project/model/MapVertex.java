package edu.ufp.inf.lp2.project.model;

/**
 * Representa um vértice no mapa do edifício (grafo de navegação).
 * Cada vértice corresponde a um ponto significativo como uma sala, escada, entrada ou ponto de passagem.
 */
public class MapVertex {

    /** Identificador único do vértice. */
    private int id;

    /** Descrição do ponto (ex: "Sala FCHS-101", "Topo Escada", etc.). */
    private String description;

    /** Tipo do ponto (ex: "sala", "pdp", "escada", "entrada"). */
    private String type;

    /**
     * Construtor de um vértice do mapa.
     *
     * @param id Identificador único do ponto
     * @param description Descrição do ponto
     * @param type Tipo do ponto (ex: "sala", "pdp", "escada", "entrada")
     */
    public MapVertex(int id, String description, String type) {
        this.id = id;
        this.description = description;
        this.type = type;
    }

    /**
     * Devolve o identificador do ponto.
     *
     * @return id do ponto
     */
    public int getId() {
        return id;
    }

    /**
     * Devolve a descrição do ponto.
     *
     * @return descrição textual
     */
    public String getDescription() {
        return description;
    }

    /**
     * Devolve o tipo do ponto.
     *
     * @return tipo (sala, escada, entrada, pdp)
     */
    public String getType() {
        return type;
    }

    /**
     * Representação textual do ponto para debug ou visualização.
     *
     * @return String formatada com tipo, descrição e id
     */
    @Override
    public String toString() {
        return "[" + type + "] " + description + " (id=" + id + ")";
    }
}
