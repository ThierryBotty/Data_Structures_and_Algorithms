
import java.util.*;

public class Main {

    public static int laskin(String ohjelma) {
        Stack<Integer> pino = new Stack<Integer>();
        pino.add(1);
        for (Character merkki : ohjelma.toCharArray()) {
            if (merkki.equals('@')) {
                pino.add(pino.peek());
            } else {
                int luku1 = pino.pop();
                int luku2 = pino.pop();
                
                if (merkki.equals('+')) {
                    pino.add(luku2 + luku1);
                } else {
                    pino.add(luku2 * luku1);
                }
            }
        }
        return pino.peek();
    }

    public static void main(String[] args) {
        System.out.println(laskin("@@+@*"));    // 4
        System.out.println(laskin("@+"));       // 2
        System.out.println(laskin("@@**"));     // 1
        System.out.println(laskin("@+@+@+"));   // 8
        System.out.println(laskin("@@@+++"));   // 4
    }
}
