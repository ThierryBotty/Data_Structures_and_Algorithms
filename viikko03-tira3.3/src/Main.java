
import java.util.*;

public class Main {

    public static boolean onkoSummaa(int[] luvut, int x) {
        Arrays.sort(luvut);
        boolean loytyi = false;
        int i;
        int erotus;

        for (i = 0; i < luvut.length; i++) {
            erotus = x - luvut[i];
            int loppu = luvut.length - 1;
            int alku = i;

            while (alku != loppu) {

                int keski = (alku + loppu + 1) / 2;
                if (keski == i) {
                    if ((i != luvut.length && luvut[i + 1] == erotus) || (i != 0 && luvut[i - 1] == erotus)) {
                        loytyi = true;
                        i = luvut.length - 1;
                    }
                    break;
                }
                if (luvut[keski] == erotus) {
                    loytyi = true;
                    i = luvut.length - 1;
                    break;
                } else if (luvut[keski] < erotus) {
                    alku = keski;
                } else {
                    loppu = keski - 1;
                }
            }
        }
        return loytyi;
    }

    public static void main(String[] args) {
        System.out.println(onkoSummaa(new int[]{1, 2}, 1));
        System.out.println(onkoSummaa(new int[]{1, 2, 3}, 6));
        System.out.println(onkoSummaa(new int[]{1, 3, 3}, 6));
        System.out.println(onkoSummaa(new int[]{1, 2, 3}, 6));
    }
}
