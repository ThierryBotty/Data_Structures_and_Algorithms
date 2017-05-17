
import java.util.*;

public class Main {

    static boolean[] kayty;
    static ArrayList<Integer>[] lista;
    static int[] luvut;

    static int komponentinSumma(int i) {
        kayty[i] = true;
        int ret = luvut[i];

        for (int e : lista[i]) {
            if (!kayty[e]) {
                ret += komponentinSumma(e);
            }
        }
        return ret;
    }

    public static boolean velat(int n, int[] saatavia, int[] mista, int[] minne) {
        kayty = new boolean[n + 1];
        lista = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            lista[i] = new ArrayList();
        }

        for (int i = 0; i < mista.length; i++) {
            lista[mista[i]].add(minne[i]);
            lista[minne[i]].add(mista[i]);
        }

        luvut = saatavia;

        for (int i = 1; i <= n; i++) {
            if (!kayty[i]) {
                if (komponentinSumma(i) != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] saatavia1 = {0, 1, 3, -4};
        int[] mista1 = {1, 3};
        int[] mihin1 = {2, 1};
        System.out.println(velat(3, saatavia1, mista1, mihin1)); //true

        int[] saatavia2 = {0, 1, 1, 1, -3};
        int[] mista2 = {1, 2, 3};
        int[] mihin2 = {2, 3, 4};
        System.out.println(velat(4, saatavia2, mista2, mihin2)); //true

        int[] saatavia3 = {0, 1, 1, 1, -3};
        int[] mista3 = {1, 2};
        int[] mihin3 = {2, 3};
        System.out.println(velat(4, saatavia3, mista3, mihin3)); //false
    }
}
