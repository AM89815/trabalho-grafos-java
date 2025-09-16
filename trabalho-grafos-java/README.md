# Explicação do Funcionamento do Código

Este projeto implementa uma análise de grafos utilizando matriz de adjacência em Java.

## Passo a Passo

### 1. Estrutura Principal

- O programa está na classe `App`.
- O método `main` serve como ponto de entrada e executa exemplos de análise de grafos usando uma matriz de adjacência.

### 2. Matriz de Adjacência

- `int[][] matrizAdjacencia` define o grafo. Cada posição `[i][j]` indica o número de arestas do vértice `i` para o vértice `j`.
- Exemplo usado: um grafo de 4 vértices.

### 3. Métodos de Análise

#### a) `tipoDoGrafo(int[][] matriz)`

- **Objetivo:** Classifica o grafo (dirigido/não dirigido, simples/multigrafo, regular, completo, nulo).
- **Como faz:**
	- Verifica se a matriz é simétrica (não dirigido) ou não (dirigido).
	- Procura laços (valores na diagonal) e multiarestas (valores > 1 fora da diagonal).
	- Verifica se todos os vértices têm o mesmo grau (regularidade).
	- Verifica se todos os pares de vértices estão conectados (completo).
	- Verifica se não há arestas (nulo).
- **Retorno:** String com a classificação.

#### b) `arestasDoGrafo(int[][] matriz)`

- **Objetivo:** Conta e lista todas as arestas do grafo.
- **Como faz:**
	- Se for dirigido, percorre toda a matriz.
	- Se não for dirigido, percorre apenas metade superior (para não contar duas vezes).
	- Para cada aresta, incrementa o contador e adiciona à lista.
- **Retorno:** String com a quantidade e o conjunto de arestas.

#### c) `grausDoVertice(int[][] matriz)`

- **Objetivo:** Mostra o grau de cada vértice e o grau máximo/mínimo do grafo.
- **Como faz:**
	- Se for dirigido, calcula entrada e saída para cada vértice.
	- Se não for dirigido, soma as conexões de cada vértice.
	- Guarda o grau máximo e mínimo.
	- Monta a sequência de graus.
- **Retorno:** String detalhando os graus.

#### d) `buscaEmProfundidade(int[][] matriz)`

- **Objetivo:** Realiza busca em profundidade (DFS) a partir do vértice 0.
- **Como faz:**
	- Usa um vetor `visitado` para marcar vértices já explorados.
	- Chama recursivamente a função auxiliar para visitar todos os vértices conectados.
	- Registra a ordem de visita.
- **Retorno:** String com a ordem de exploração.

### 4. Saída

- O programa imprime, na ordem:
	1. Classificação do grafo.
	2. Lista de arestas.
	3. Grau dos vértices.
	4. Ordem da busca em profundidade.