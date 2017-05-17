
import java.util.*;

public class Main {

    public static boolean sulutus(String mjono) {
        Stack<Character> pino = new Stack<Character>();
        for (Character merkki : mjono.toCharArray()) {
            if (pino.isEmpty() && merkki.equals(')')) {
                return false;
            } else if (merkki.equals('(')) {
                pino.add(merkki);
            } else {
                pino.pop();
            }
        }
        return pino.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(sulutus("((()))")); //true
        System.out.println(sulutus("(())()")); //true
        System.out.println(sulutus("(()))(")); //false
        System.out.println(sulutus("())(()")); //false
    }
}
