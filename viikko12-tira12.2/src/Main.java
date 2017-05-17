
import java.util.*;

public class Main {

    public static long kunnostus(int n, int[] mista, int[] minne, int[] hinta) {
        tripla[] abc = new tripla[mista.length];
        for (int i = 0; i < mista.length; i++) {
            abc[i] = new tripla(mista[i], minne[i], hinta[i]);
        }

        Arrays.sort(abc);

        EtsiYhdiste yhdistetty = new EtsiYhdiste(n);

        long v = 0;

        for (tripla t : abc) {
            if (yhdistetty.apu(t.a) != yhdistetty.apu(t.l)) {
                yhdistetty.yhdiste(t.a, t.l);
                v += t.w;
            }
        }

        return v;
    }

    private static class EtsiYhdiste {

        int[] p;
        int[] s;

        EtsiYhdiste(int n) {
            p = new int[n + 1];
            s = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                s[i] = 1;
            }
        }

        int apu(int a) {
            while (p[a] != 0) {
                a = p[a];
            }
            return a;
        }

        void yhdiste(int a, int b) {
            a = apu(a);
            b = apu(b);

            if (s[a] > s[b]) {
                p[b] = a;
                s[a] += s[b];
            } else {
                p[a] = b;
                s[b] += s[a];
            }
        }
    }

    private static class tripla implements Comparable<tripla> {

        Integer a, l;
        Long w;

        tripla(int a, int l, long w) {
            this.a = a;
            this.l = l;
            this.w = w;
        }

        @Override
        public int compareTo(tripla t) {
            return w.compareTo(t.w);
        }
    }

    public static void main(String[] args) {
        System.out.println(kunnostus(3, new int[]{1, 2}, new int[]{2, 3}, new int[]{7, 4}));
        System.out.println(kunnostus(3, new int[]{1, 2, 1}, new int[]{2, 3, 3}, new int[]{7, 4, 5}));
        System.out.println(kunnostus(3, new int[]{1, 2, 1}, new int[]{2, 3, 3}, new int[]{2, 2, 2}));
    }
}
