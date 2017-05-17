
import java.util.*;

public class Main {

    public static class Yhteentormays {

        public String s1, s2;

        public Yhteentormays(String s1, String s2) {
            this.s1 = s1;
            this.s2 = s2;
        }
    }

    public static int Hash(int a, int b, String s) {
        long H = 0;
        for (int i = 0; i < s.length(); i++) {
            H = (a * H + s.charAt(i)) % b;
        }
        return (int) H;
    }

    //ÄLÄ MUOKKAA YLLÄOLEVIA
    public static Yhteentormays generoi(int a, int b) {
        String aakk = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
        char[] asdf = aakk.toCharArray();
        for (char c : asdf) {
            for (char d : asdf) {
                for (char e : asdf) {
                    for (char f : asdf) {
                        if (c == e && d == f) {
                            continue;
                        }
                        StringBuilder sb = new StringBuilder();
                        sb.append(c);
                        sb.append(d);
                        StringBuilder sb2 = new StringBuilder();
                        sb.append(e);
                        sb.append(f);

                        if (Hash(a, b, sb.toString()) == Hash(a, b, sb2.toString())) {
                            return new Yhteentormays(sb.toString(), sb2.toString());
                        }
                    }
                }  
            }
        }
        return new Yhteentormays("", "");
    }

    public static void main(String[] args) {
        Yhteentormays y = generoi(428374921, 1000000007);
        System.out.println(y.s1 + " " + y.s2);
        System.out.println(Hash(428374921, 1000000007, y.s1) + " " + Hash(428374921, 1000000007, y.s2));
    }
}
