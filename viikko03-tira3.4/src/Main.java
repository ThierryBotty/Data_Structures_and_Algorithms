
import java.util.*;

public class Main {

    public static int keraaLuvut(int[] luvut) {

        int[] uusi = new int[luvut.length + 1];
        
        for (int i = 0; i < luvut.length; i++) {
            uusi[luvut[i]] = i;
        }

        int tarkastettu = -1;
        int kierroksia = 1;

        for (int i = 1; i <= luvut.length; i++) {
            int k = uusi[i];
            
            if(k < tarkastettu) {
                kierroksia++;
            }
            tarkastettu = k;
        }

        return kierroksia;
    }

    public static void main(String[] args) {

        System.out.println(keraaLuvut(new int[]{1, 4, 2, 5, 3}));
        System.out.println(keraaLuvut(new int[]{5, 1, 2, 3, 4}));
        System.out.println(keraaLuvut(new int[]{5, 4, 3, 2, 1}));
        System.out.println(keraaLuvut(new int[]{1, 5, 2, 4, 3}));
    }
}
