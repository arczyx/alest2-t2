import java.io.*;
import java.util.*;

public class Labirinto {

    private Character[][] matriz;
    private Queue<Integer> filaLinha = new LinkedList<Integer>();
    private Queue<Integer> filaColuna = new LinkedList<Integer>();
    private int linha = 0;
    private int coluna = 0;

    public enum Direcao {
        DIREITA, ESQUERDA, BAIXO, CIMA
    }

    public Labirinto(Arquivo arquivo) throws IOException {
        this.matriz = arquivo.lerArquivo();
        this.filaLinha.add(arquivo.getLinA());
        this.filaColuna.add(arquivo.getColA());
    }

    public boolean podeAndar(Direcao direcao) {
        int linOffset = linha;
        int colOffset = coluna;
        switch (direcao) {
            case ESQUERDA:
                colOffset = colOffset - 1;
                break;
            case DIREITA:
                colOffset = colOffset + 1;
                break;
            case BAIXO:
                linOffset = linOffset - 1;
                break;
            case CIMA:
                linOffset = linOffset + 1;
                break;
        }
        if (matriz[linOffset][colOffset] != null && matriz[linOffset][colOffset] != '*') { 
            filaColuna.add(colOffset);
            filaLinha.add(linOffset);
            return true;
        } else {
            return false;
        }
    }

    public void percorrerLabirinto() {

        System.out.println("entrei");

        boolean encontrou = false;

        int numeroDeExploracoes = 0, numeroDeVoltas = 0, totalDistancia = 0, aux = 1;

        while (!encontrou) {
            if (!filaLinha.isEmpty()) {

                linha = filaLinha.peek();
                coluna = filaColuna.peek();
                numeroDeVoltas++;

                if (matriz[linha][coluna] != 'B') {

                    if (matriz[linha][coluna] != '*') {

                        // ADICIONA NA FILA OS LUGARES QUE ELE TEM QUE EXPLORAR

                        if (podeAndar(Direcao.ESQUERDA)) {
                            numeroDeExploracoes++;
                        }

                        if (podeAndar(Direcao.DIREITA)) {
                            numeroDeExploracoes++;
                        }

                        if (podeAndar(Direcao.CIMA)) {
                            numeroDeExploracoes++;
                        }

                        if (podeAndar(Direcao.BAIXO)) {
                            numeroDeExploracoes++;
                        }

                        matriz[linha][coluna] = '*';

                    }

                    filaLinha.remove();
                    filaColuna.remove();
                    // numeroDeExploracoes é o contador de novas exploracoes a ser feitas, sera
                    // transferido para o aux
                    // aux sao exploracoes pendentes, o total que tem que ser feitas
                    // numeroDeVoltas é o numero de exploracoes que estao sendo feitas
                    if (numeroDeVoltas >= aux) {
                        totalDistancia++;
                        aux = numeroDeExploracoes;
                        numeroDeExploracoes = 0;
                        numeroDeVoltas = 0;
                    }

                } else {
                    encontrou = true;
                    System.out.println("Encontrou");
                    System.out.println(totalDistancia);

                }
            } else {
                encontrou = true;
                System.out.println("Erro");
            }
        }

    }

    public void mostrarLabirinto() {
        for (int k = 0; k < matriz.length; k++) {
            for (int j = 0; j < matriz[k].length; j++) {
                System.out.print(matriz[k][j]);
            }
            System.out.println("\n");
        }
    }
}