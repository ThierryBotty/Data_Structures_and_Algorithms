import java.util.*;

public class Main {
    public static void main(String[] args) {
        Lentoreitit x = new Lentoreitit(4, new int[] {1, 1}, new int[] {2, 3});
        System.out.println(x.mahdollinen(1, 2, 1));
        System.out.println(x.mahdollinen(1, 1, 4));
        System.out.println(x.mahdollinen(1, 2, 2));
        System.out.println(x.mahdollinen(1, 4, 3));
    }        
}
