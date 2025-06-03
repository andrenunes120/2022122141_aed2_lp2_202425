package edu.ufp.inf.lp2.project.test;

import edu.ufp.inf.lp2.project.test.R7.*;
import edu.ufp.inf.lp2.project.manager.BuildingGraphManager;
import edu.princeton.cs.algs4.Edge;
import java.util.List;


public class R8 {
    public static  BuildingGraphManager manager = R7.definirEdificio();

    public static void testarSearchPathDijkstra() {

        int origem = 14;   // ex: Entrada 0A
        int destino = 33; // ex: Sala FCHS-114

        List<Edge> caminho = manager.searchPath(origem, destino);

        if (caminho != null) {
            double pesoTotal = 0.0;

            System.out.println("Caminho encontrado:");
            for (Edge e : caminho) {
                int v = e.either();
                int w = e.other(v);
                System.out.println(manager.getVertex(v) + " <-> " + manager.getVertex(w) + " (peso: " + e.weight() + ")");
                pesoTotal += e.weight();
            }

            System.out.printf("Peso total do caminho: %.2f\n", pesoTotal);
        } else {
            System.out.println("Não foi possível encontrar caminho entre os pontos.");
        }
    }

    public static void testarConectividade() {
        boolean conexo = manager.isConnected();
        System.out.println("O grafo completo é conexo? " + conexo);
    }

    public static void main(String[] args) {
        testarSearchPathDijkstra();
        System.out.println();
        testarConectividade();
        System.out.println();
    }
}

