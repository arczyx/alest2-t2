import java.io.*;
import java.util.*;

public class Labirinto {

    private Character[][] matriz;
    private Queue<Integer> filaLinha = new LinkedList<Integer>();
    private Queue<Integer> filaColuna = new LinkedList<Integer>();

    public Labirinto(Arquivo arquivo) throws IOException {
        this.matriz = arquivo.lerArquivo();
        this.filaLinha.add(arquivo.getLinA());
        this.filaColuna.add(arquivo.getColA());
    }

    public void percorrerLabirinto() {

        System.out.println("entrei");

        boolean encontrou = false;

        int linha = 0;
        int coluna = 0;

        int numeroDeExploracoes = 0, numeroDeVoltas = 0, totalDistancia = 0, aux = 1;

        while (!encontrou) {
            if (!filaLinha.isEmpty()) {

                linha = filaLinha.peek();
                coluna = filaColuna.peek();
                numeroDeVoltas++;

                if (matriz[linha][coluna] != 'B') {

                    if (matriz[linha][coluna] != '*') {

                        // ANDANDO PARA DIREITA
                        if (matriz[linha][coluna - 1] != null) {
                            if (matriz[linha][coluna - 1] != '*') {
                                filaColuna.add(coluna - 1);
                                filaLinha.add(linha);
                                numeroDeExploracoes++;
                            }
                        }
                        // ANDANDO PARA BAIXO
                        if (matriz[linha - 1][coluna] != null) {
                            if (matriz[linha - 1][coluna] != '*') {
                                filaColuna.add(coluna);
                                filaLinha.add(linha - 1);
                                numeroDeExploracoes++;
                            }
                        }
                        // ANDANDO PARA ESQUERDA
                        if (matriz[linha][coluna + 1] != null) {
                            if (matriz[linha][coluna + 1] != '*') {
                                filaColuna.add(coluna + 1);
                                filaLinha.add(linha);
                                numeroDeExploracoes++;
                            }

                        }

                        // ANDANDO PARA CIMA
                        if (matriz[linha + 1][coluna] != null) {
                            if (matriz[linha + 1][coluna] != '*') {
                                filaColuna.add(coluna);
                                filaLinha.add(linha + 1);
                                numeroDeExploracoes++;
                            }
                        }

                        matriz[linha][coluna] = '*';

                    }

                    filaLinha.remove();
                    filaColuna.remove();

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