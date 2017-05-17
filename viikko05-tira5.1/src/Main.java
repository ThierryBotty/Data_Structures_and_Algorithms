import java.util.*;

public class Main {   
    public static int reitinPituus(String reitti) {
        HashSet setti = new HashSet();
        int x = 0;
        int y = 0;
        int askelia = 0;
        String koord = "0,0";
        setti.add(koord);
        char[] merkit = reitti.toCharArray();
        for (char n : merkit) {
            if (n == 'Y') {
                y++;
            } else if (n == 'A') {
                y--;
            } else if (n == 'O') {
                x++;
            } else if (n == 'V') {
                x--;
            }
            askelia++;
            koord = x + "," + y;
            
            if (setti.contains(koord)) {
                return askelia;
            }
            setti.add(koord);
            
        }
        return 0;
    }
    
    public static void main(String[] args) {        
        System.out.println(reitinPituus("YYVVAAOO"));
        System.out.println(reitinPituus("YVAOYVAO"));
        System.out.println(reitinPituus("YYYYYYYY"));
        System.out.println(reitinPituus("OYVVAOOO"));
    }        
}
