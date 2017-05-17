
import java.util.*;

public class Main {

    public static boolean onkoOsajono(String a, String b) {

        if (a.length() > b.length()) {
            return false;
        }
        
        long isoluku1 = 387947779;
        long isoluku2 = 5521819;
        int eka = a.length();
        int toka = b.length();

        long hajautus1 = 1;

        for (int i = 0; i < eka - 1; i++) {
            hajautus1 = (isoluku2 * hajautus1) % isoluku1;
        }

        long hajautus2 = 0;

        for (int i = 0; i < eka; i++) {
            hajautus2 = (isoluku2 * hajautus2 + a.charAt(i)) % isoluku1;
        }

        long hajautus3 = 0;

        for (int i = 0; i < eka; i++) {
            hajautus3 = (isoluku2 * hajautus3 + b.charAt(i)) % isoluku1;
        }

        if (hajautus2 == hajautus3) {
            return true;
        }

        for (int i = 0; i + eka < toka; i++) {
            
            hajautus3 = ((hajautus3 - hajautus1 * b.charAt(i)) % isoluku1 + isoluku1) % isoluku1;
            hajautus3 = (isoluku2 * hajautus3 + b.charAt(i + eka)) % isoluku1;
            
            if (hajautus2 == hajautus3) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(onkoOsajono("AB", "ACACACAB")); //true
        System.out.println(onkoOsajono("AA", "ABBA")); //false
        System.out.println(onkoOsajono("AAAA", "AAAA")); //true
        System.out.println(onkoOsajono("ABABAC", "ABABABAB")); //false
    }
}
