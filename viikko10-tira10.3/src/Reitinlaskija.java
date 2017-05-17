
import java.util.*;

public class Reitinlaskija {

    static int[] leveyshaku(int alku, int n, ArrayList<Integer>[] vl) {
        int[] abc = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            abc[i] = 1000000000;
        }

        Queue<Integer> jono = new ArrayDeque<>();
        abc[alku] = 0;
        jono.add(alku);

        while (jono.size() != 0) {
            int solmu = jono.poll();
            vl[solmu].stream().filter((seuraava) -> (abc[seuraava] == 1000000000)).map((seuraava) -> {
                abc[seuraava] = abc[solmu] + 1;
                return seuraava;
            }).forEach((seuraava) -> {
                jono.add(seuraava);
            });
        }

        return abc;
    }

    int[] abc;
    int[] abcd;

    Reitinlaskija(int n, int[] mista, int[] minne) {
        ArrayList<Integer>[] vl = new ArrayList[n + 1];
        ArrayList<Integer>[] vlT = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            vl[i] = new ArrayList();
            vlT[i] = new ArrayList();
        }

        for (int i = 0; i < mista.length; i++) {
            vl[mista[i]].add(minne[i]);
            vlT[minne[i]].add(mista[i]);
        }

        abc = leveyshaku(1, n, vl);
        abcd = leveyshaku(1, n, vlT);
    }

    int lyhinReitti(int a, int b) {
        if (abcd[a] == 1000000000 || abc[b] == 1000000000) {
            return -1;
        }
        return abcd[a] + abc[b];
    }
}
