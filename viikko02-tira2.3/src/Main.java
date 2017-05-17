import java.util.*;

public class Main {
    public static int nopanheitot(int x) {
        int luku = 0;
        int noppa = 6;
        while (noppa > 0) {
            if (noppa < x) {
                luku += nopanheitot(x-noppa);
            } else if (noppa == x) {
                luku++;
            }
            noppa--;
        }
        return luku;
    }
    
    public static void main(String[] args) {
        System.out.println(nopanheitot(4));
        System.out.println(nopanheitot(2));
        System.out.println(nopanheitot(3));
        System.out.println(nopanheitot(1));
        System.out.println(nopanheitot(12));
    }    
}
