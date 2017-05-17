
import java.util.*;

public class Main {

    public static int ennatys(int[] tulot, int[] lahdot) {

        ArrayList<Integer> a = new ArrayList();
        for (Integer luku : tulot) {
            a.add(luku);
        }
        ArrayList<Integer> b = new ArrayList();
        for (Integer luku : lahdot) {
            b.add(luku);
        }
        Collections.sort(a);
        Collections.sort(b);
        int ihmisia = 0;
        int suurin = 1;
        int j = 0;
        for (int i = 0; i < a.size(); i++) {
            ihmisia++;
            
            if (ihmisia > suurin) {
                suurin = ihmisia;
            }         
            while (i < lahdot.length - 1 && a.get(i + 1) > b.get(j)) {
                ihmisia--;
                j++;              
            }
        }
        return suurin;
    }

    public static void main(String[] args) {
        System.out.println(ennatys(new int[]{3, 4, 5, 9},
                new int[]{8, 6, 10, 12}));
        System.out.println(ennatys(new int[]{3, 2, 10, 1},
                new int[]{8, 9, 20, 5}));
        System.out.println(ennatys(new int[]{1, 3, 5},
                new int[]{2, 4, 6}));
        System.out.println(ennatys(new int[]{100, 999},
                new int[]{1000, 1001}));
    }
}
