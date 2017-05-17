import java.util.*; 

public class Main {
    public static long halvinReitti(int n, int[] mista, int[] minne, long[] hinta) {
        return 0l;
    }
    
    public static void main(String[] args) {
        System.out.println(halvinReitti(3, new int[] {1,2,1,2}, new int[] {2,3,3,1}, new long[] {3,1,7,5})); // 2
        System.out.println(halvinReitti(4, new int[] {1,1,2,3}, new int[] {2,3,4,4}, new long[] {1,4,8,4})); // 5
        System.out.println(halvinReitti(4, new int[] {1,1,2,3}, new int[] {4,2,3,4}, new long[] {10,2,3,1}));// 4
        System.out.println(halvinReitti(4, new int[] {1,1,2,3}, new int[] {4,2,3,4}, new long[] {10,3,3,3}));// 5
    }        
}

