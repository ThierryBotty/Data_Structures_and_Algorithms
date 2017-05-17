
import java.util.*;

public class Main {

    public static int[] suuremmat(int[] luvut) {
        Stack<Integer> pino = new Stack<Integer>();
        int koko = luvut.length;
        int[] lista = new int[koko];
        for (int i = 0; i < koko; i++) {
            lista[i] = 0;
        }

        pino.add(luvut[0]);
        int seuraava = 0;
        for (int i = 1; i < koko; i++) {
            while (true) {
                if (pino.isEmpty() || pino.peek() >= luvut[i]) {
                    break;
                } else {
                    for (int j = i-1; j >= 0; j--) {
                        if (lista[j] == 0) {
                            if (pino.isEmpty() || pino.peek() >= luvut[i]) {
                                j=-1;
                                continue;
                            }
                            lista[j] = luvut[i];
                            pino.pop();
                            if (pino.isEmpty()) {
                                j=-1;
                            }
                        }
                    }
                }
            }
            pino.push(luvut[i]);
        }
        return lista;
    }

    public static void main(String[] args) {
        
        int[] tulos1 = suuremmat(new int[]{1, 2, 3, 4, 5});
        System.out.println(Arrays.toString(tulos1));
        int[] tulos2 = suuremmat(new int[]{5, 4, 3, 2, 1});
        System.out.println(Arrays.toString(tulos2));
        int[] tulos3 = suuremmat(new int[]{4, 3, 2, 1, 5});
        System.out.println(Arrays.toString(tulos3));
        int[] tulos4 = suuremmat(new int[]{1, 5, 2, 4, 3});
        System.out.println(Arrays.toString(tulos4));
    }
}
