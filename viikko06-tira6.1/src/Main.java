
import java.util.*;

public class Main {

    public static int luku = 999999999;

    public static int minimi(Puu puu) {
        luku = 999999999;
        int pienin = rekursio(puu);
        return pienin;
    }

    public static int rekursio(Puu puu) {
        if (luku > puu.arvo) {
            luku = puu.arvo;
        }

        if (!(puu.vasen == null)) {
            rekursio(puu.vasen);
        }
        
        if (!(puu.oikea == null)) {
            rekursio(puu.oikea);
        }
        return luku;
    }

    public static void main(String[] args) {
        Puu puu = new Puu(7,
                new Puu(5,
                        new Puu(2, null, null),
                        null),
                new Puu(6,
                        new Puu(3, null, null),
                        new Puu(1, null, null)));

        System.out.println(minimi(puu)); //1

    }
}
