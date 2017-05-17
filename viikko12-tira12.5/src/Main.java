import java.util.*;

public class Main {   
    public static long suurinAla(long[] aita) {
        return 0;
    }
    
    public static void main(String[] args) {
        System.out.println(suurinAla(new long[]{1, 2, 3, 4, 5}));       //9
        System.out.println(suurinAla(new long[]{5, 4, 3, 2, 1}));       //9
        System.out.println(suurinAla(new long[]{5, 5, 1, 5, 5, 4}));    //12
        System.out.println(suurinAla(new long[]{1, 1, 1}));             //3
    }        
}

