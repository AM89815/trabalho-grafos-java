// Membros: Aurélio Miguel Bowens da Silva Filho

public class App {
    public static void main(String[] args) {
        // Exemplo de uso com uma matriz de adjacência
        int[][] matrizAdjacencia = {
            {0, 1, 1, 0},
            {1, 0, 1, 1},
            {1, 1, 0, 1},
            {0, 1, 1, 0}
        };
        System.out.println(tipoDoGrafo(matrizAdjacencia));
        System.out.println(arestasDoGrafo(matrizAdjacencia));
        System.out.println(grausDoVertice(matrizAdjacencia));
        System.out.println(buscaEmProfundidade(matrizAdjacencia));
    }

    // 1. Classifica o grafo
    public static String tipoDoGrafo(int[][] matriz) {
        int ordem = matriz.length;
        boolean dirigido = false;
        boolean simples = true;
        boolean regular = true;
        boolean completo = true;
        boolean nulo = true;
        int[] graus = new int[ordem];
        int grauEsperado = -1;
        // Verifica simetria (dirigido ou não)
        for (int i = 0; i < ordem; i++) {
            for (int j = 0; j < ordem; j++) {
                if (matriz[i][j] != matriz[j][i]) {
                    dirigido = true;
                }
                if (matriz[i][j] > 1 && i != j) {
                    simples = false; // Multiaresta
                }
                if (matriz[i][i] != 0) {
                    simples = false; // Laço
                }
                if (i != j && matriz[i][j] == 0) {
                    completo = false;
                }
                if (matriz[i][j] != 0) {
                    nulo = false;
                }
            }
        }
        // Verifica regularidade
        for (int i = 0; i < ordem; i++) {
            int grau = 0;
            if (dirigido) {
                // Grau total = entrada + saída
                for (int j = 0; j < ordem; j++) {
                    grau += matriz[i][j];
                    grau += matriz[j][i];
                }
            } else {
                for (int j = 0; j < ordem; j++) {
                    grau += matriz[i][j];
                }
            }
            graus[i] = grau;
            if (grauEsperado == -1) grauEsperado = grau;
            if (grau != grauEsperado) regular = false;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Grafo ");
        sb.append(dirigido ? "dirigido" : "não dirigido");
        sb.append(", ");
        sb.append(simples ? "simples" : "multigrafo");
        sb.append(", ");
        sb.append(regular ? "regular" : "não regular");
        sb.append(", ");
        sb.append(completo ? "completo" : "não completo");
        sb.append(", ");
        sb.append(nulo ? "nulo" : "não nulo");
        return sb.toString();
    }

    // 2. Conta e lista as arestas
    public static String arestasDoGrafo(int[][] matriz) {
        int ordem = matriz.length;
        boolean dirigido = false;
        for (int i = 0; i < ordem && !dirigido; i++) {
            for (int j = 0; j < ordem; j++) {
                if (matriz[i][j] != matriz[j][i]) {
                    dirigido = true;
                    break;
                }
            }
        }
        int quantidade = 0;
        StringBuilder arestas = new StringBuilder();
        arestas.append("{");
        if (dirigido) {
            for (int i = 0; i < ordem; i++) {
                for (int j = 0; j < ordem; j++) {
                    for (int k = 0; k < matriz[i][j]; k++) {
                        if (matriz[i][j] > 0) {
                            quantidade++;
                            arestas.append("(").append(i).append(",").append(j).append(") ");
                        }
                    }
                }
            }
        } else {
            for (int i = 0; i < ordem; i++) {
                for (int j = i; j < ordem; j++) {
                    for (int k = 0; k < matriz[i][j]; k++) {
                        if (matriz[i][j] > 0) {
                            quantidade++;
                            arestas.append("(").append(i).append(",").append(j).append(") ");
                        }
                    }
                }
            }
        }
        arestas.append("}");
        return "Arestas: " + quantidade + " " + arestas.toString();
    }

    // 3. Grau do grafo e de cada vértice
    public static String grausDoVertice(int[][] matriz) {
        int ordem = matriz.length;
        boolean dirigido = false;
        for (int i = 0; i < ordem && !dirigido; i++) {
            for (int j = 0; j < ordem; j++) {
                if (matriz[i][j] != matriz[j][i]) {
                    dirigido = true;
                    break;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        int[] graus = new int[ordem];
        int grauMax = Integer.MIN_VALUE, grauMin = Integer.MAX_VALUE;
        if (dirigido) {
            sb.append("Vértice: entrada/saída\n");
            for (int i = 0; i < ordem; i++) {
                int entrada = 0, saida = 0;
                for (int j = 0; j < ordem; j++) {
                    saida += matriz[i][j];
                    entrada += matriz[j][i];
                }
                graus[i] = entrada + saida;
                sb.append(i).append(": ").append(entrada).append("/").append(saida).append("\n");
                grauMax = Math.max(grauMax, graus[i]);
                grauMin = Math.min(grauMin, graus[i]);
            }
        } else {
            sb.append("Vértice: grau\n");
            for (int i = 0; i < ordem; i++) {
                int grau = 0;
                for (int j = 0; j < ordem; j++) {
                    grau += matriz[i][j];
                }
                graus[i] = grau;
                sb.append(i).append(": ").append(grau).append("\n");
                grauMax = Math.max(grauMax, grau);
                grauMin = Math.min(grauMin, grau);
            }
        }
        sb.append("Grau do grafo: max = ").append(grauMax).append(", min = ").append(grauMin).append("\n");
        sb.append("Sequência de graus: ");
        for (int i = 0; i < ordem; i++) {
            sb.append(graus[i]);
            if (i < ordem - 1) sb.append(", ");
        }
        return sb.toString();
    }

    // 4. Busca em profundidade (DFS)
    public static String buscaEmProfundidade(int[][] matriz) {
        int ordem = matriz.length;
        boolean[] visitado = new boolean[ordem];
        StringBuilder ordemExploracao = new StringBuilder();
        buscaProfundidadeRec(0, matriz, visitado, ordemExploracao);
        return "Ordem da busca em profundidade: " + ordemExploracao.toString().trim();
    }

    private static void buscaProfundidadeRec(int vertice, int[][] matriz, boolean[] visitado, StringBuilder ordem) {
        visitado[vertice] = true;
        ordem.append(vertice).append(" ");
        for (int i = 0; i < matriz.length; i++) {
            if (matriz[vertice][i] > 0 && !visitado[i]) {
                buscaProfundidadeRec(i, matriz, visitado, ordem);
            }
        }
    }
}
