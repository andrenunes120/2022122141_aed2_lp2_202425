# Projeto SchoolAir — LP2/AED2

Trabalho realizado no âmbito das unidades curriculares de LP2/AED2 — Navegação Indoor e Gestão de Informação Escolar (2024/2025).

## 🗂️ Estrutura do Projeto

- `src/main/java/edu/ufp/inf/lp2/project/`
  - `model/` — Modelos de domínio (Aluno, Professor, Sala, UC, Horário, Grafo)
  - `manager/` — Gestores de dados e lógica (StudentManager, ProfessorManager, RoomManager, etc.)
  - `app/` — Aplicação principal (MainApp)
  - `io/` — Funções de leitura e escrita de ficheiros (FileUtils)
- `src/test/java/edu/ufp/inf/lp2/project/test/`
  - Testes automáticos por requisito (`R2.java`, `R3.java`, `R4.java`, `R7.java`, `R8.java`, `R10.java`)
- `DB/` — Ficheiros de dados de exemplo (.txt)
- `uml/` — Diagramas UML do projeto (`project.drawio`, `project.png`)
- `src/docs/` — Documentação Javadoc gerada
- `lib/` — Bibliotecas externas (algs4.jar)

## 📦 Tecnologias e Estruturas Usadas

- **Java 17+**
- **Estruturas de Dados:** HashMap, Red-Black BST (algs4), grafos orientados e ponderados
- **Leitura/escrita de ficheiros:** texto (.txt)
- **Javadoc** para documentação
- **Testes automáticos** em Java

## 📋 Implementação dos Requisitos

### **Milestone 1**

- **R1. Modelação de entidades:**  
  Todas as entidades escolares estão modeladas em Java e presentes no diagrama UML (`uml/project.png`).  
  Classes: `Student`, `Professor`, `Course`, `Subject`, `ClassGroup`, `Room`, `TimeSlot`, `SubjectSlot`, `MapVertex`, `Person`.

- **R2. Symbol Table (dados não ordenáveis):**  
  Utilização de `HashMap` para gerir alunos, professores, turmas, UCs, etc.  
  Gestores: `StudentManager`, `ProfessorManager`, ...  
  Testes em: `R2.java`

- **R3. BST balanceada (dados ordenáveis):**  
  Uso de árvores Red-Black para dados ordenáveis (ex: horários, slots de disciplinas).  
  Testes em: `R3.java`

- **R4. Pesquisas sobre a informação:**  
  Funções para listar salas livres, professores de UC, horários de atendimento, etc.  
  Testes em: `R4.java`

- **R5. Funções de teste:**  
  Cada requisito tem funções de teste dedicadas em ficheiros separados.

- **R6. Comentários/Javadoc:**  
  Todas as classes/métodos documentados; documentação gerada em `src/docs`.

### **Milestone 2**

- **R7. Grafo do edifício:**  
  Implementação do grafo indoor do edifício com vértices e arestas ponderadas (distância/tempo).  
  Classes: `MapVertex`, `BuildingGraphManager`  
  Testes em: `R7.java`

- **R8. Funcionalidades do grafo:**  
  Caminho mais curto, operações sobre subgrafos e conectividade.  
  Testes em: `R8.java`

- **R10. IO em ficheiros texto:**  
  Carregamento e gravação de entidades/mapa para ficheiros `.txt`.  
  Funções em: `FileUtils.java`  
  Testes em: `R10.java`

### **Funcionalidades em falta/parciais**
- **R9 (GUI):** Ainda não implementada.
- **R11 (ficheiros binários):** Não está implementado (apenas IO em texto).

## 🧪 Testes realizados

- Cada requisito tem ficheiro de teste próprio em `src/test/java/edu/ufp/inf/lp2/project/test/`.
- Os testes inserem, removem, editam e listam entidades e validações específicas (por ex: procura de salas livres, caminhos no grafo, etc).
- Para correr os testes, compilar o projeto e executar os ficheiros de teste individualmente (ou integrar num runner JUnit, se necessário).

## 📝 Documentação

- Diagrama UML disponível em `uml/project.png` e editável em `uml/project.drawio`.
- Documentação Javadoc gerada em `src/docs`.

## 🚩 Pontos de melhoria / próximos passos

- Implementar interface gráfica (GUI) para edição e visualização dos dados.
- Adicionar suporte para ficheiros binários (R11).
- Reforçar testes automáticos (ex: testes para exceções, casos-limite).
- Garantir Javadoc em todos os métodos públicos (revisão final).

---

**Projeto realizado por:**  
*André Nunes, nº 2022122141 — Universidade Fernando Pessoa*

