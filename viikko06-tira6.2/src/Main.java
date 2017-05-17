
import java.util.*;

public class Main {

    public static boolean samanlaiset(Puu a, Puu b) {
        if (a == null || b == null) {
            return (a == null && b == null);
        }
        if (a.arvo != b.arvo) {
            return false;
        }
        return samanlaiset(a.vasen, b.vasen) && samanlaiset(a.oikea, b.oikea);
    }

    public static void main(String[] args) {
        Puu puu1 = new Puu(1,
                new Puu(3,
                        new Puu(2, null, null),
                        new Puu(1, null, null)),
                new Puu(3,
                        new Puu(3, null, null),
                        new Puu(2, null, null)));

        Puu puu2 = new Puu(1,
                new Puu(3,
                        new Puu(2, null, null),
                        new Puu(1, null, null)),
                new Puu(3,
                        new Puu(3, null, null),
                        new Puu(2, null, null)));

        System.out.println(samanlaiset(puu1, puu2));
    }
}
