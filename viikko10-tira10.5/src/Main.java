
import java.util.*;

public class Main {

    static ArrayList<Integer>[] lista;
    static boolean[] kayty;
    static int[] jarj;
    static int x;
    static boolean loytyi;

    static void apu(int s) {
        kayty[s] = true;
        for (int seuraava : lista[s]) {
            if (!kayty[seuraava]) {
                apu(seuraava);
            }
        }
        jarj[x] = s;
        x--;
    }

    public static long kolikoita(int n, long[] k, int[] mista, int[] minne) {
        kayty = new boolean[n + 1];
        jarj = new int[n];
        x = n - 1;
        lista = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            lista[i] = new ArrayList();
        }
        for (int i = 0; i < mista.length; i++) {
            lista[mista[i]].add(minne[i]);
        }
        for (int s = 1; s <= n; s++) {
            if (!kayty[s]) {
                apu(s);
            }
        }
        long[] max_k = new long[n + 1];
        int i1 = 0;
        for (; i1 < jarj.length; i1++) {
            if (jarj[i1] == 1) {
                break;
            }
        }
        max_k[1] = k[1];
        long vast = 0;
        for (int i = i1; i < jarj.length; i++) {
            for (int seur : lista[jarj[i]]) {
                max_k[seur] = Math.max(max_k[seur], max_k[jarj[i]] + k[seur]);
            }
            vast = Math.max(vast, max_k[jarj[i]]);
        }
        return vast;
    }


public static void main(String[] args) {
        System.out.println(kolikoita(3, new long[] {0, 1, 1, 1}, new int[] {1, 2}, new int[] {2, 3})); //3
        System.out.println(kolikoita(3, new long[] {0, 1, 1, 1}, new int[] {2, 1}, new int[] {1, 3})); //2
        System.out.println(kolikoita(4, new long[] {0, 1, 1, 1, 10}, new int[] {1, 2, 1}, new int[] {2, 3, 4})); //11
        System.out.println(kolikoita(4, new long[] {0, 1, 1, 1, 1}, new int[] {1, 1, 1}, new int[] {2, 3, 4})); //2
    }        
}
