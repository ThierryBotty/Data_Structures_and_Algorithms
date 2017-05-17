
import java.util.*;

public class Main {

    public static int jaaOmenat(int[] omenat) {
        int pino1 = 0;
        int pino2 = 0;
        int indeksi = 0;
        return hajauta(omenat, pino1, pino2, indeksi);
    }

    private static int hajauta(int[] omenat, int pino1, int pino2, int indeksi) {
        if (indeksi == omenat.length) {
            return Math.abs(pino2 - pino1);
        }

        int pienin = hajauta(omenat, pino1 + omenat[indeksi], pino2, indeksi + 1);
        int pienin2 = hajauta(omenat, pino1, pino2 + omenat[indeksi], indeksi + 1);
        return (Math.min(pienin2, pienin));
    }

    public static void main(String[] args) {
        System.out.println(jaaOmenat(new int[]{5, 3, 2, 9}));
        System.out.println(jaaOmenat(new int[]{2, 2}));
        System.out.println(jaaOmenat(new int[]{999}));
        System.out.println(jaaOmenat(new int[]{999, 1, 1, 1}));
    }
}
