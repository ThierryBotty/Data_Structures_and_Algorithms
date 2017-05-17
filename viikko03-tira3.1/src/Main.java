import java.util.*;

public class Main {
    static int montakoKirjaa(int[] kirjat, int k) {
        Arrays.sort(kirjat);
        int summa = 0;
        int i;
        for (i = 0; i < kirjat.length; i++) {
            summa += kirjat[i];
            if (summa > k) {
                break;
            }
        }
        return i;
    }
    
    public static void main(String[] args) {
        System.out.println(montakoKirjaa(new int[] {2, 1, 4, 3}, 3));
        System.out.println(montakoKirjaa(new int[] {1, 15, 5, 1}, 6));
        System.out.println(montakoKirjaa(new int[] {3, 1, 2, 1}, 4));
    }    
}

