
import java.util.*;

public class Main {

    public static int vahvistuksia(int n, int[] mista, int[] minne, int[] raja, int W) {
        triple[] kaaret = new triple[mista.length];
        for (int i = 0; i < mista.length; i++) {
            kaaret[i] = new triple(mista[i], minne[i], raja[i]);
        }

        Arrays.sort(kaaret);

        etsiYhdiste yhdistetty = new etsiYhdiste(n);

        for (triple t : kaaret) {
            if (t.w < W) {
                break;
            }
            if (yhdistetty.etsi(t.a) != yhdistetty.etsi(t.l)) {
                yhdistetty.union(t.a, t.l);
            }
        }

        return yhdistetty.hajota() - 1;
    }

    private static class etsiYhdiste {

        int[] parent;
        int[] koko;
        int osat;

        etsiYhdiste(int n) {
            osat = n;
            parent = new int[n + 1];
            koko = new int[n + 1];

            for (int i = 1; i <= n; i++) {
                parent[i] = i;
                koko[i] = 1;
            }
        }

        int etsi(int a) {
            while (parent[a] != a) {
                a = parent[a];
            }
            return a;
        }

        void union(int a, int b) {
            a = etsi(a);
            b = etsi(b);

            if (a == b) {
                return;
            }

            osat--;

            if (koko[a] > koko[b]) {
                parent[b] = a;
                koko[a] += koko[b];
            } else {
                parent[a] = b;
                koko[b] += koko[a];
            }
        }

        int hajota() {
            return osat;
        }
    }

    private static class triple implements Comparable<triple> {

        Integer a, l;
        Long w;

        triple(int a, int l, long w) {
            this.a = a;
            this.l = l;
            this.w = w;
        }

        @Override
        public int compareTo(triple t) {
            return -w.compareTo(t.w);
        }
    }

    public static void main(String[] args) {
        System.out.println(vahvistuksia(2, new int[]{1}, new int[]{2}, new int[]{9}, 10)); // 1
        System.out.println(vahvistuksia(3, new int[]{1, 2}, new int[]{2, 3}, new int[]{9, 1}, 10)); // 2
        System.out.println(vahvistuksia(3, new int[]{1, 2, 1}, new int[]{2, 3, 3}, new int[]{9, 1, 10}, 10)); // 1
        System.out.println(vahvistuksia(4, new int[]{1, 1, 2, 3}, new int[]{2, 3, 3, 4}, new int[]{10, 9, 9, 8}, 10)); // 2
    }
}
