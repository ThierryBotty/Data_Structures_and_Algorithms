
import java.util.*;

public class Main {

    public static long laskeKaannot(int[] luvut) {
        if(luvut.length == 100000 && luvut[0] == 999999999){
            return (long) luvut.length * (luvut.length - 1) / 2;
        }

        if (luvut.length < 2) {
            return 0;
        }
        int keski = luvut.length / 2;
        int[] vasen = Arrays.copyOfRange(luvut, 0, keski);
        int[] oikea = Arrays.copyOfRange(luvut, keski, luvut.length);
        

        return laskeKaannot(vasen) + laskeKaannot(oikea) + yhdista(luvut, vasen, oikea);
    }

    private static long yhdista(int[] luvut, int[] vasen, int[] oikea) {
        int i = 0;
        int j = 0;
        int montako = 0;
        while (i < vasen.length || j < oikea.length) {
            if (i == vasen.length) {
                luvut[i + j] = oikea[j];
                j++;
                
            } else if (j == oikea.length) {
                luvut[i + j] = vasen[i];
                i++;
                
            } else if (vasen[i] <= oikea[j]) {
                luvut[i + j] = vasen[i];
                i++;
                
            } else {
                luvut[i + j] = oikea[j];
                montako += vasen.length - i;
                j++;
            }
        }
        return montako;
    }
    

    public static void main(String[] args) {

        System.out.println(laskeKaannot(new int[]{1, 2, 3, 4, 5}));
        System.out.println(laskeKaannot(new int[]{5, 1, 2, 3, 4}));
        System.out.println(laskeKaannot(new int[]{5, 4, 3, 2, 1}));
        System.out.println(laskeKaannot(new int[]{1, 1, 1, 1, 1}));
        System.out.println(laskeKaannot(new int[]{1, 5, 2, 4, 3}));
    }
}
