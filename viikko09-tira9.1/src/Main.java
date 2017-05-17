import java.util.*;

public class Main {
    private static ArrayList<Integer>[] lista;
    private static boolean[] jotain;
    
    public static boolean yhteys(int n, int[] mista, int[] minne) {
        lista = new ArrayList[n+1];
        jotain = new boolean[n+1];
        for (int i = 1; i <= n; i++) lista[i] = new ArrayList<>();
        for (int i = 0; i < mista.length; i++) {
            lista[mista[i]].add(minne[i]);
            lista[minne[i]].add(mista[i]);
        }
        hae(1);
        return jotain[n];
    }
    
        private static void hae(int s) {
        if (jotain[s]) return;
        jotain[s] = true;
        for (int i = 0; i < lista[s].size(); i++) {
            hae(lista[s].get(i));
        }
    }

    
    public static void main(String[] args) {
        System.out.println(yhteys(3, new int[] {1, 2}, new int[] {2, 3}));
        System.out.println(yhteys(4, new int[] {1, 2}, new int[] {2, 3}));
        System.out.println(yhteys(4, new int[] {1, 2}, new int[] {3, 4}));
        System.out.println(yhteys(4, new int[] {1, 2}, new int[] {4, 3}));
    }        
}
