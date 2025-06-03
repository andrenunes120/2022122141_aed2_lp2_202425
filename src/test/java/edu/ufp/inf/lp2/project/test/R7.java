package edu.ufp.inf.lp2.project.test;

import edu.ufp.inf.lp2.project.model.MapVertex;
import edu.ufp.inf.lp2.project.manager.BuildingGraphManager;

public class R7{

     public static BuildingGraphManager manager;

    public static BuildingGraphManager definirEdificio() {
        // --- IDs para cada vértice ---
        int id = 0;
        int UFP = id++; // nó raiz (opcional, podes ignorar nas pesquisas)

        // Piso 0
        int E0A = id++; // Entrada 0A
        int E0B = id++; // Entrada 0B
        int S0_1 = id++; // Escada Piso 0 para 1
        int C0_A = id++;
        int C0_B = id++;
        int C0_C = id++;
        int C0_D = id++;

        // Piso 1
        int E1A = id++; // Entrada piso 1
        int S1_2 = id++; // Escada piso 1 para 2
        int C1_A = id++;
        int C1_B = id++;
        int C1_C = id++;
        int C1_D = id++;
        int[] S1_SALAS = new int[14];
        for (int i = 0; i < 14; i++) S1_SALAS[i] = id++;

        // Piso 2
        int E2A = id++; // Entrada piso 2
        int C2_A = id++;
        int C2_B = id++;
        int C2_C = id++;
        int C2_D = id++;
        int[] S2_SALAS = new int[14];
        for (int i = 0; i < 14; i++) S2_SALAS[i] = id++;



        // --- Criar manager e adicionar vértices ---
        manager = new BuildingGraphManager(id);

        manager.addVertex(new MapVertex(UFP, "UFP", "root"));

        // Piso 0
        manager.addVertex(new MapVertex(E0A, "Entrada_0A", "entrada"));
        manager.addVertex(new MapVertex(E0B, "Entrada_0B", "entrada"));
        manager.addVertex(new MapVertex(S0_1, "Escada_0_1", "escada"));
        manager.addVertex(new MapVertex(C0_A, "C0_A", "corredor"));
        manager.addVertex(new MapVertex(C0_B, "C0_B", "corredor"));
        manager.addVertex(new MapVertex(C0_C, "C0_C", "corredor"));
        manager.addVertex(new MapVertex(C0_D, "C0_D", "corredor"));

        // Piso 1
        manager.addVertex(new MapVertex(E1A, "Entrada_1A", "entrada"));
        manager.addVertex(new MapVertex(S1_2, "Escada_1_2", "escada"));
        manager.addVertex(new MapVertex(C1_A, "C1_A", "corredor"));
        manager.addVertex(new MapVertex(C1_B, "C1_B", "corredor"));
        manager.addVertex(new MapVertex(C1_C, "C1_C", "corredor"));
        manager.addVertex(new MapVertex(C1_D, "C1_D", "corredor"));
        for (int i = 0; i < 14; i++) {
            String nomeSala = (i < 9) ? "FCHS-10" + (i + 1) : "FCHS-1" + (i + 1);
            manager.addVertex(new MapVertex(S1_SALAS[i], nomeSala, "sala"));
        }


        // Piso 2
        manager.addVertex(new MapVertex(E2A, "Entrada_2A", "entrada"));
        manager.addVertex(new MapVertex(C2_A, "C2_A", "corredor"));
        manager.addVertex(new MapVertex(C2_B, "C2_B", "corredor"));
        manager.addVertex(new MapVertex(C2_C, "C2_C", "corredor"));
        manager.addVertex(new MapVertex(C2_D, "C2_D", "corredor"));
        for (int i = 0; i < 14; i++) {
            String nomeSala = (i < 9) ? "FCHS-20" + (i + 1) : "FCHS-2" + (i + 1);
            manager.addVertex(new MapVertex(S2_SALAS[i], nomeSala, "sala"));
        }

        // --- Arestas ---

        // UFP (raiz) liga a todas as entradas (opcional, útil para pesquisas globais)
        manager.addEdge(UFP, E0A, 0.1);
        manager.addEdge(UFP, E0B, 0.1);
        manager.addEdge(UFP, E1A, 3.0);
        manager.addEdge(UFP, E2A, 3.0);

        // Piso 0: Entradas ligam a corredores
        manager.addEdge(E0A, C0_A, 1.0);
        manager.addEdge(E0B, C0_C, 1.0);

        // Piso 0: Corredores formam quadrado
        manager.addEdge(C0_A, C0_B, 1.0);
        manager.addEdge(C0_B, C0_C, 1.0);
        manager.addEdge(C0_C, C0_D, 1.0);
        manager.addEdge(C0_D, C0_A, 1.0);

        // Piso 0: Corredor A liga à escada para Piso 1
        manager.addEdge(C0_A, S0_1, 0.5);

        // Escada S0_1 liga corredor do piso 1 (B)
        manager.addEdge(S0_1, C1_B, 0.5);

        // Piso 1: Entrada liga a corredor (C1_C)
        manager.addEdge(E1A, C1_C, 1.0);

        // Piso 1: Corredores formam quadrado
        manager.addEdge(C1_A, C1_B, 1.0);
        manager.addEdge(C1_A, C1_D, 1.0);
        manager.addEdge(C1_B, C1_C, 1.0);
        manager.addEdge(C1_D, C1_C, 1.0);



        // Piso 1: C1_A e C1_C têm 7 salas cada
        for (int i = 0; i < 7; i++) manager.addEdge(C1_A, S1_SALAS[i], 0.2);
        for (int i = 7; i < 14; i++) manager.addEdge(C1_C, S1_SALAS[i], 0.2);

        // Piso 1: Escada para Piso 2 (S1_2 liga C1_B a C2_B)
        manager.addEdge(C1_B, S1_2, 0.5);
        manager.addEdge(S1_2, C2_B, 0.5);

        // Piso 2: Entrada liga a corredor (C2_C)
        manager.addEdge(E2A, C2_C, 1.0);

        // Piso 2: Corredores formam quadrado
        manager.addEdge(C2_A, C2_B, 1.0);
        manager.addEdge(C2_B, C2_C, 1.0);
        manager.addEdge(C2_C, C2_D, 1.0);
        manager.addEdge(C2_A, C2_D, 1.0);

        // Piso 2: C2_A e C2_C têm 7 salas cada
        for (int i = 0; i < 7; i++) manager.addEdge(C2_A, S2_SALAS[i], 0.2);
        for (int i = 7; i < 14; i++) manager.addEdge(C2_C, S2_SALAS[i], 0.2);

        return manager;

    }
    public static void imprimirgrafo(BuildingGraphManager manager){
        // --- Imprimir o grafo ---
        System.out.println("=== Grafo Multinível UFP (R7) ===");
        manager.printGraph();
        System.out.println("\nNº de vértices: " + manager.getGraph().V());
        System.out.println("Nº de arestas: " + manager.getGraph().E());
    }


    public static void main(String[] args) {
        definirEdificio();
        imprimirgrafo(manager);
    }
}
