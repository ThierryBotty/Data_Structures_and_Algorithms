import java.util.*;


public class Main {   
    public static void main(String[] args) {
        Lauta l = new Lauta(10);
        l.leikkaa(5);
        System.out.println(l.pisinPatka()); //5
        l.leikkaa(5);
        System.out.println(l.pisinPatka()); //5
        l.leikkaa(6);
        System.out.println(l.pisinPatka()); //5
        l.leikkaa(3);
        System.out.println(l.pisinPatka()); //4
        l.leikkaa(8);
        System.out.println(l.pisinPatka()); //3
        l.leikkaa(1);
        System.out.println(l.pisinPatka()); //2
    }        
}
