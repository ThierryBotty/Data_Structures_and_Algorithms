import java.util.*;

public class Main {
    public static int lyhinReitti(int n, int[] mista, int[] minne, boolean[] mutaa) {
        return 0;
    }
    
    public static void main(String[] args) {
        System.out.println(lyhinReitti(4, new int[]{1,2,3,1},
                                          new int[]{2,3,4,3},
                                          new boolean[]{false, false, false, true})); // 3
        
        System.out.println(lyhinReitti(3, new int[]{1,2},
                                          new int[]{2,3},
                                          new boolean[]{true, false})); // -1
        
        System.out.println(lyhinReitti(3, new int[]{1,2},
                                          new int[]{2,3},
                                          new boolean[]{false, true})); // 2
        
        System.out.println(lyhinReitti(4, new int[]{1,2,1,3},
                                          new int[]{2,4,3,2},
                                          new boolean[]{false, false, true, true})); // 2
        
        System.out.println(lyhinReitti(3, new int[]{1,1,2},
                                          new int[]{2,2,3},
                                          new boolean[]{false, true, false})); // 2
    }        
}



