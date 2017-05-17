
import java.util.*;

public class Main {

    public static boolean tyhjennys(String mjono) {
        Stack<Integer> pino = new Stack<Integer>();
        int luku;
        for (int i = 0; i < mjono.length(); i++) {

            if (mjono.charAt(i) == 'A') {
                luku = 1;
            } else {
                luku = 0;
            }

            if (!pino.empty() && pino.peek() == luku) {
                pino.pop();
            } else {
                pino.push(luku);
            }
        }
        return pino.size() <= 0;
    }

    public static void main(String[] args) {
        System.out.println(tyhjennys("ABBABB"));
        System.out.println(tyhjennys("ABBBBB"));
        System.out.println(tyhjennys("AAAAAA"));
        System.out.println(tyhjennys("ABABAB"));
    }
}
