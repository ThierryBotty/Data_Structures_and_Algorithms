import java.util.*;


public class Main {
    public static int montakoErilaista(String[] taulukko) {
        Arrays.sort(taulukko);
        int uniikit = taulukko.length;
        for (int i = 0; i < taulukko.length - 1; i++) {
            if (taulukko[i].equals(taulukko[i+1])) {
                uniikit--;
            }
        }
        return uniikit;
    }
    
    public static void main(String[] args) {
        System.out.println(montakoErilaista(new String[] {"apina", "banaani", "cembalo"}));
        System.out.println(montakoErilaista(new String[] {"a", "b", "c", "d", "a"}));
        System.out.println(montakoErilaista(new String[] {"abba", "abbb", "bbba", "babb", "bbab"}));
        System.out.println(montakoErilaista(new String[] {"babb", "abbb", "bbba", "babb", "bbab"}));
    }    
}

