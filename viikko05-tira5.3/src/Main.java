
import java.util.*;

public class Main {

    public static int lyhinPuuttuva(String mjono) {

        HashSet<String> osia = new HashSet<String>();

        for (int n = 1; n < 10; n++) {
            osia.removeAll(osia);
            for (int i = 0; i + n <= mjono.length(); i++) {
                osia.add(mjono.substring(i, i + n));
            }
            if (osia.size() != Math.pow(4, n)) {
                return n;
            }
        }
        return 10;

    }

    public static void main(String[] args) {
        System.out.println(lyhinPuuttuva("CCCCCCCC"));
        System.out.println(lyhinPuuttuva("ACGTACGT"));
        System.out.println(lyhinPuuttuva("ACAAGCAG"));
        System.out.println(lyhinPuuttuva("ACACACGT"));
    }
}
