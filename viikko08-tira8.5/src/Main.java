import java.util.*;

public class Main {
    public static long pieninAita(long[] a, long[] b, long[] h) {
        return 0l;
    }
    
    public static void main(String[] args) {
        long[] a1={0, 3, 6};
        long[] b1={2, 5, 7};
        long[] h1={1, 1, 1};
        System.out.println(pieninAita(a1, b1, h1)); //5
        
        long[] a2={0, 1, 4, 5};
        long[] b2={4, 3, 6, 7};
        long[] h2={1, 2, 3, 2};
        System.out.println(pieninAita(a2, b2, h2)); //14
        
        long[] a3={0, 1, 2, 4, 4, 4};
        long[] b3={3, 3, 3, 7, 6, 5};
        long[] h3={1, 2, 3, 1, 2, 3};
        System.out.println(pieninAita(a3, b3, h3)); //12
        
        long[] a4={900000000, 32323};
        long[] b4={1000000000, 32514};
        long[] h4={1, 7};
        System.out.println(pieninAita(a4, b4, h4)); //100001337
    }        
}


