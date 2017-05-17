
import java.util.*;

public class Main {

    public static long reittimaara(int n, int[] mista, int[] minne) {
        ArrayList<Integer>[] lista = new ArrayList[n + 1];
        int[] etaisyys = new int[n + 1];
        long[] maara = new long[n + 1];

        for (int i = 0; i < n + 1; i++) {
            lista[i] = new ArrayList();
            etaisyys[i] = -1;
            maara[i] = 0;
        }

        for (int i = 0; i < mista.length; i++) {
            lista[mista[i]].add(minne[i]);
            lista[minne[i]].add(mista[i]);
        }

        etaisyys[1] = 0;
        maara[1] = 1;
        Queue<Integer> jono = new ArrayDeque();
        jono.add(1);

        while (!jono.isEmpty()) {
            int nyt = jono.poll();

            lista[nyt].stream().map((seur) -> {
                if (etaisyys[seur] == -1) {
                    etaisyys[seur] = etaisyys[nyt] + 1;
                    jono.add(seur);
                }
                return seur;
            }).filter((seur) -> (etaisyys[seur] == etaisyys[nyt] + 1)).forEach((seur) -> {
                maara[seur] += maara[nyt];
            });
        }
        return maara[n];
    }

    public static void main(String[] args) {
        System.out.println(reittimaara(2, new int[]{1}, new int[]{2}));                               //1
        System.out.println(reittimaara(5, new int[]{1, 1, 2, 3, 4}, new int[]{2, 3, 4, 4, 5}));               //2
        System.out.println(reittimaara(5, new int[]{1, 1, 1, 2, 3, 4}, new int[]{2, 3, 4, 5, 5, 5})); //3
        System.out.println(reittimaara(7, new int[]{1, 2, 3, 1, 1, 4, 5, 6}, new int[]{2, 3, 7, 4, 5, 6, 6, 7}));   //3

    }
}
