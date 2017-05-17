import java.util.*;
public class Main {   
    public static int[] komponentteja(int n, int[] mista, int[] minne, int[] poistot) {
        return new int[0];
    }
    
    public static void main(String[] args) {
        System.out.println(Arrays.toString(komponentteja(3, new int[] {1,2,3}, new int[] {2,3,1}, new int[] {0,1,2}))); //[1,2,3]
        System.out.println(Arrays.toString(komponentteja(5, new int[] {1,1,1,2,2,3,4}, new int[] {2,3,4,3,4,4,5}, new int[] {2,3,6,1,4}))); //[1,1,2,2,3]
        System.out.println(Arrays.toString(komponentteja(5, new int[] {1,2,3,4}, new int[] {2,3,4,5}, new int[] {2,0,3,1}))); //[2,3,4,5] 
        System.out.println(Arrays.toString(komponentteja(5, new int[] {1,2,3,4,5}, new int[] {2,3,4,5,1}, new int[] {2,0,3,1}))); //[1,2,3,4] 
    }        
}

