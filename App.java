public class App {
    static String path = "casop.txt";

    public static void main(String[] args) throws Exception {

        long start = System.currentTimeMillis();

        Arquivo arquivo = new Arquivo(path);

        Labirinto l1 = new Labirinto(arquivo);

        l1.percorrerLabirinto();

        //l1.mostrarLabirinto();

        System.out.println("ACABOU");

        long elapsed = ((System.currentTimeMillis() - start));
        System.out.println(elapsed);

    }
}