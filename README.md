
# Projeto LP2/AED2 – Gestão e Navegação Indoor

Projeto académico para a gestão de dados académicos e navegação indoor em Java, recorrendo a estruturas de dados não lineares (`ST`, `RedBlackBST`, `Graph`) e arquitetura orientada a objetos.

---

## 📦 Estrutura de Packages

| Package                                  | Conteúdo                                                                 |
|------------------------------------------|--------------------------------------------------------------------------|
| `edu.ufp.inf.lp2.project.model`          | Entidades principais do domínio (alunos, professores, cursos, etc.)     |
| `edu.ufp.inf.lp2.project.manager`        | Gestores com estruturas de dados (`ST`, `RedBlackBST`)                  |
| `edu.ufp.inf.lp2.project.io`             | Leitura e escrita de dados em ficheiros `.txt`                          |
| `edu.ufp.inf.lp2.project.test`           | Testes `static` organizados por requisito e milestone                   |
| `edu.ufp.inf.lp2.project.app`            | Classe principal `MainApp` (a criar)                                    |

---

## ✅ Classes e Design

### `TimeSlot` (`model`)
> Representa um horário (dia da semana + hora de início/fim).  
**Usado por**: `Student`, `Professor`, `Room`.

- Usa `DayOfWeek` e `LocalTime` da API Java.
- Método `overlapsWith()` para verificar conflitos — essencial para R4.a/d.

---

### `Person` (`model`)
> Classe abstrata base para `Student` e `Professor`.

- Contém `id` e `name`.
- Evita duplicação de código nas subclasses.

---

### `Student` (`model`)
> Representa um estudante da instituição.

- Herda de `Person`.
- `List<String> enrolledSubjects` (códigos de UC).
- `List<SubjectSlot>` para o horário.

---

### `Professor` (`model`)
> Representa um professor.

- Herda de `Person`.
- Lista de UCs onde leciona.
- Horários de atendimento e associação a `Subject`.

---

### `Subject` (`model`)
> Representa uma Unidade Curricular.

- Código, nome, curso a que pertence
- Lista de `TimeSlot` para o horário
- Lista de `professorIds` e `studentIds` associados

---

### `Course` (`model`)
> Representa um curso superior (ex: LEI, MEI).

- Código e nome do curso
- Lista de códigos de `Subject` associadas

---

### `Room` (`model`)
> Representa uma sala com atributos físicos e horários ocupados.

- Código (único), piso, capacidade, número de tomadas
- Lista de `TimeSlot` com ocupações
- `isFreeAt()` verifica se está livre

---

### `SubjectSlot` (`model`)
- Combinação de `TimeSlot` + `subjectCode`
- Utilizado em `Student.getSchedule()` para listar horários com contexto

---

## 🧠 Lógica de Verificação de Conflitos

- `TimeSlot.overlapsWith()` permite verificar sobreposição
- `InscricaoManager.conflitoHorario(...)` é usado para validar horários:
    - Antes de inscrever aluno em UC
    - Antes de associar professor a UC

---

## 📂 Leitura e Escrita de Ficheiros `.txt`

| Ficheiro       | Descrição                        |
|----------------|----------------------------------|
| `alunos.txt`   | ID, nome e UCs inscritas         |
| `professores.txt` | ID e nome dos professores     |
| `ucs.txt`      | Código, nome, curso, horários, professores |
| `salas.txt`    | Código, piso, capacidade, tomadas |
| `cursos.txt`   | Cursos com UCs associadas        |
| `turmas.txt`   | Listagem de turmas e inscrições  |

---

## 💾 Exemplo de `ucs.txt`

```
LP2;Linguagens de Programação II;LEI;MONDAY,09:00,11:00|WEDNESDAY,14:00,16:00;P1001
```

---

## 🧱 `FileUtils` (`io`)
- Métodos estáticos para guardar/carregar todas as entidades em `.txt`
- Usa `Paths.get("DB", filename)` para estruturar os dados

---

## 📘 TURMAS (`ClassGroup`)

### `ClassGroup`
Cada turma representa uma instância de uma UC com:
- Código da UC
- Professor responsável
- Capacidade
- Lista de alunos inscritos

---

### `ClassGroupManager`
Gere as turmas com:
- `ST<String, List<ClassGroup>>` (agrupadas por UC)
- Permite:
    - Criar turmas
    - Verificar vagas
    - Adicionar alunos
    - Listar com nome do professor (acesso a `ProfessorManager`)

---

### Exemplo de linha em `turmas.txt`:

```
LP2;P1001;30;2022001,2022002
```

---

## 📋 Funcionalidades atuais no menu:

### Menu Estudante:
- Listar estudantes
- Adicionar/remover
- Ver horário (com reconstrução via UCs)
- Inscrever em UC com verificação de conflitos

### Menu Professor:
- Listar/adicionar/remover
- Criar turma com UC, professor e capacidade
- Listar turmas (com nome do professor)
- Persistência em `turmas.txt`

---

## 📝 Requisitos Cumpridos

| Requisito | Estado | Implementado em                        |
|-----------|--------|----------------------------------------|
| R1        | ✅     | Estrutura OO (`model`)                 |
| R2        | ✅     | `StudentManager`, `SubjectManager`     |
| R3        | ✅     | `RoomManager` com `RedBlackBST`        |
| R4        | ✅     | Conflitos, horários, associações       |
| R5        | ✅     | Testes `static`                        |
| R10       | ✅     | `FileUtils` e dados persistentes       |
| R6        | 🟡     | Javadoc em progresso                   |

---

## 📁 Estrutura de Diretórios

```
/DB
  alunos.txt
  professores.txt
  ucs.txt
  salas.txt
  cursos.txt
  turmas.txt

/src
├── main/java/edu/ufp/inf/lp2/project/
│   ├── model/
│   ├── manager/
│   ├── io/
│   └── app/
└── test/java/edu/ufp/inf/lp2/project/test/
```
