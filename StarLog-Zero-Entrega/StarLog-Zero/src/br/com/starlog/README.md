# Missão StarLog-Zero

Projeto Java desenvolvido conforme o caderno de requisitos da atividade.

## Estrutura

```text
StarLog-Zero/
└── src/
    └── br/com/starlog/
        ├── model/
        │   ├── Carga.java
        │   ├── ModuloCarga.java
        │   └── BaseLancamento.java
        ├── exception/
        │   └── CapacidadeExcedidaException.java
        └── main/
            └── MainResgate.java
```

## Requisitos implementados

- `Carga`: atributos privados, código de rastreio `final`, validação no construtor, getters, `equals`, `hashCode` e `toString`.
- `CapacidadeExcedidaException`: exceção verificada que herda diretamente de `Exception`.
- `ModuloCarga`: lista inicializada no construtor, controle de capacidade e três operações com Streams API.
- `BaseLancamento`: armazenamento em `HashMap`, cadastro e busca por identificador.
- `MainResgate`: quatro testes de estresse do protocolo Chaos Monkey.

## Como compilar

Abra o terminal na pasta `StarLog-Zero` e execute:

```bash
javac -d out src/br/com/starlog/model/*.java src/br/com/starlog/exception/*.java src/br/com/starlog/main/*.java
```

## Como executar

```bash
java -cp out br.com.starlog.main.MainResgate
```

É necessário ter o JDK instalado e configurado no PATH.

## Diagrama UML

Crie no StarUML as classes abaixo e conecte-as conforme indicado:

- `Carga`: codigoRastreio: String {readOnly}; categoria: String; pesoKg: double; valorSeguro: double.
- `ModuloCarga`: idModulo: String; capacidadeMaxima: int; cargas: List<Carga>.
- `BaseLancamento`: modulos: Map<String, ModuloCarga>.
- `CapacidadeExcedidaException`: herda de `Exception`.
- `MainResgate`: contém o método `main(String[] args)`.

Relacionamentos:
- `ModuloCarga` agrega várias `Carga` (1 para 0..*).
- `BaseLancamento` mantém vários `ModuloCarga` (1 para 0..*).
- `ModuloCarga` depende de `CapacidadeExcedidaException` no método `carregarCarga`.
- `MainResgate` utiliza as classes dos pacotes `model` e `exception`.

Observação: o método `getCargas()` devolve uma cópia da lista para evitar que código externo altere diretamente a lista interna.
