
import java.util.*;

public class Main {

    public static ArrayList<Integer>[] lista;
    public static Stack<Integer> pino;
    public static int varit[];
    public static int laskuri;
    public static ArrayList<Integer>[] transpoosi;

    public static int komponentteja(int n, int[] mista, int[] minne) {
        varit = new int[n + 1];
        lista = new ArrayList[n + 1];
        laskuri = 0;
        pino = new Stack();
        transpoosi = new ArrayList[n + 1];

        for (int i = 0; i < lista.length; i++) {
            lista[i] = new ArrayList<>();
            transpoosi[i] = new ArrayList<>();
        }
        for (int i = 0; i < mista.length; i++) {
            lista[mista[i]].add(minne[i]);
            transpoosi[minne[i]].add(mista[i]);
        }

        tutki();
        tutki2();

        return laskuri - 1;
    }

    public static void visit(int solmu) {
        varit[solmu] = 1;
        ArrayList<Integer> vierus = lista[solmu];
        vierus.stream().filter((alkio) -> (varit[alkio] == 0)).forEach((alkio) -> {
            visit(alkio);
        });
        varit[solmu] = 2;
        pino.push(solmu);
    }

    public static void tutki() {
        for (int i = 0; i < varit.length; i++) {
            if (varit[i] == 0) {
                visit(i);
            } 
       }
    }

    public static void visit2(int solmu) {
        varit[solmu] = 1;
        ArrayList<Integer> vierus = transpoosi[solmu];
        vierus.stream().filter((alkio) -> (varit[alkio] == 0)).forEach((alkio) -> {
            visit2(alkio);
        });
        varit[solmu] = 2;
    }

    public static void tutki2() {
        for (int i = 0; i < varit.length; i++) {
            varit[i] = 0;
        }
        while (!pino.empty()) {
            int i = pino.pop();
            if (varit[i] == 0) {
                visit2(i);
                laskuri++;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(komponentteja(3, new int[]{1, 2, 3}, new int[]{2, 3, 1})); //1
        System.out.println(komponentteja(3, new int[]{1, 1}, new int[]{2, 3}));     //3
        System.out.println(komponentteja(3, new int[]{1, 1, 2}, new int[]{2, 3, 3})); //3
        System.out.println(komponentteja(3, new int[]{1, 1, 2}, new int[]{2, 3, 1})); //2          
    }
}
