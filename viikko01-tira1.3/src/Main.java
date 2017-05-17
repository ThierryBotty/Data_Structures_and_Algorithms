import java.util.*;

public class Main {
    public static boolean ovatkoSamat(int[] a, int[] b) {
        
        int pituus = b.length;
        int i = 0;
        while(i < pituus) {
            if (a[i] == b[0]) {
                break;
            }
            i++;
        }
        for (int j = 0; j < pituus; j++) {
            if (a[i % pituus] != b[j]) {
                return false;
            }
            i++;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(ovatkoSamat(new int[]{1, 2, 3}, new int[]{3, 1, 2}));
        System.out.println(ovatkoSamat(new int[]{1, 2, 3}, new int[]{3, 2, 1}));
        System.out.println(ovatkoSamat(new int[]{2, 5, 3, 1, 4}, new int[]{3, 1, 4, 2, 5}));
        System.out.println(ovatkoSamat(new int[]{1, 2, 3, 4, 5}, new int[]{1, 2, 3, 5, 4}));
    }
}
