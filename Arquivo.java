import java.io.*;

public class Arquivo {
    private int linha;
    private int coluna;
    private String arquivo;
    private int colA;
    private int linA;
    private int colB;
    private int linB;

    private void contarLinhasColunas() throws IOException {
        BufferedReader reader;
        reader = new BufferedReader(new FileReader(arquivo));
        String line = "";
        while (line != null) {
            line = reader.readLine();
            if (line == null) {
                break;
            }
            this.linha++;
            if (linha == 1) {
                for (int i = 0; i < line.length(); i++) {
                    this.coluna++;
                }
            }
        }

        reader.close();
    }

    public Arquivo(String arquivo) throws IOException {
        this.arquivo = arquivo;
        this.colA = 0;
        this.linA = 0;
        contarLinhasColunas();
    }

    public String getArquivo() {
        return arquivo;
    }

    public int getColuna() {
        return coluna;
    }

    public int getLinha() {
        return linha;
    }

    public int getColA() {
        return colA;
    }

    public int getLinA() {
        return linA;
    }

    public int getColB() {
        return colB;
    }

    public int getLinB() {
        return linB;
    }

    public Character[][] lerArquivo() throws IOException {

        Character[][] matriz = new Character[this.linha][this.coluna];

        BufferedReader reader;
        reader = new BufferedReader(new FileReader(arquivo));
        int i = 0;
        int lin = 0;
        int col = 0;

        while ((i = reader.read()) != -1) {

            if ((char) i != '\n') {

                if ((char) i == 'A') {
                    this.colA = col;
                    this.linA = lin;
                }
                if ((char) i == 'B') {
                    this.colB = col;
                    this.linB = lin;
                }
                if ((char) i == '#') {
                    matriz[lin][col] = null;
                    col++;
                } else {
                    matriz[lin][col] = (char) i;
                    col++;
                }

            } else {
                lin++;
                col = 0;

            }
        }

        reader.close();

        return matriz;
    }
}