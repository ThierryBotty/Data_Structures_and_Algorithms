
public class Main {

    public static int harvinVali(int[] polku) {
        int alku = 1;
        int loppu = polku.length - 1;
        while (alku != loppu) {
            int k = (alku + loppu + 1) / 2;
            if (toimiikoPolku(polku, k)) {
                alku = k;
            } else {
                loppu = k-1;
            }
        }
        return alku;
    }

    private static boolean toimiikoPolku(int[] polku, int k) {
        int summa = 1000;
        for (int i = 0; i < polku.length; i++) {
            summa += polku[i];

            if (summa < 0) {
                return false;
            }
            if ((i + 1) % k == 0) {
                summa += 1000;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(harvinVali(new int[]{-500, -500, -100, -900, -600, -500}));
        System.out.println(harvinVali(new int[]{-300, -300, 200, -300, -300, -1}));
        System.out.println(harvinVali(new int[]{-500, -500, -100, -200, -600, -500}));
        System.out.println(harvinVali(new int[]{-300, -300, -300, -300, -300, -300}));
        System.out.println(harvinVali(new int[]{1000, -1000, -1000, 0, -100, -100}));
    }
}
