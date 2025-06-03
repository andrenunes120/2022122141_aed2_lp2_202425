package edu.ufp.inf.lp2.project.manager;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.ufp.inf.lp2.project.model.MapVertex;
import edu.princeton.cs.algs4.DijkstraUndirectedSP;

import edu.princeton.cs.algs4.CC;
import edu.princeton.cs.algs4.Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe responsável por gerir o grafo do edifício, permitindo representar pontos (salas, entradas, etc.)
 * e ligações (corredores, escadas, caminhos) com pesos associados (distância, tempo, etc.).
 */
public class BuildingGraphManager {

    /** Grafo ponderado que representa a infraestrutura do edifício. */
    private EdgeWeightedGraph graph;

    /** Mapa de vértices (pontos do edifício), indexados pelo seu ID. */
    private Map<Integer, MapVertex> vertices;

    /**
     * Construtor que inicializa o grafo com o número máximo de vértices.
     *
     * @param numVertices número de vértices que o grafo poderá conter
     */
    public BuildingGraphManager(int numVertices) {
        graph = new EdgeWeightedGraph(numVertices);
        vertices = new HashMap<>();
    }

    /**
     * Adiciona um novo vértice ao mapa do edifício.
     *
     * @param vertex objeto MapVertex a ser inserido
     */
    public void addVertex(MapVertex vertex) {
        vertices.put(vertex.getId(), vertex);
    }

    /**
     * Adiciona uma aresta (ligação) entre dois vértices no grafo com um peso associado.
     *
     * @param v ID do vértice de origem
     * @param w ID do vértice de destino
     * @param weight peso da ligação (ex: distância ou tempo)
     */
    public void addEdge(int v, int w, double weight) {
        graph.addEdge(new Edge(v, w, weight));
    }

    /**
     * Obtém um vértice do mapa dado o seu ID.
     *
     * @param id identificador do vértice
     * @return objeto MapVertex correspondente ou null se não existir
     */
    public MapVertex getVertex(int id) {
        return vertices.get(id);
    }

    /**
     * Devolve o grafo completo que representa o edifício.
     *
     * @return instância de EdgeWeightedGraph
     */
    public EdgeWeightedGraph getGraph() {
        return graph;
    }

    /**
     * Imprime na consola todos os vértices e as respetivas ligações no grafo.
     */
    public void printGraph() {
        for (Edge e : graph.edges()) {
            int v = e.either();
            int w = e.other(v);
            System.out.println(vertices.get(v) + " <-> " + vertices.get(w) + " (peso: " + e.weight() + ")");
        }
    }

    /**
     * Calcula o caminho mais curto entre dois vértices do grafo usando o algoritmo de Dijkstra.
     *
     * @param origem ID do vértice de origem
     * @param destino ID do vértice de destino
     * @return lista de arestas que compõem o caminho mais curto, ou null se não houver caminho
     */
    public List<Edge> searchPath(int origem, int destino) {
        if (origem < 0 || destino < 0 || origem >= graph.V() || destino >= graph.V()) {
            System.out.println("IDs inválidos.");
            return null;
        }

        DijkstraUndirectedSP dijkstra = new DijkstraUndirectedSP(graph, origem);

        if (!dijkstra.hasPathTo(destino)) {
            System.out.println("Não existe caminho entre os vértices " + origem + " e " + destino);
            return null;
        }

        List<Edge> caminho = new ArrayList<>();
        for (Edge e : dijkstra.pathTo(destino)) {
            caminho.add(e);
        }

        return caminho;
    }

    /**
     * Verifica se o grafo completo é conexo, usando algs4.
     *
     * @return true se o grafo for conexo (apenas uma componente), false caso contrário
     */
    public boolean isConnected() {
        Graph simpleGraph = new Graph(graph.V());

        for (Edge e : graph.edges()) {
            int v = e.either();
            int w = e.other(v);
            simpleGraph.addEdge(v, w);
        }

        CC cc = new CC(simpleGraph);
        return cc.count() == 1;
    }
}
