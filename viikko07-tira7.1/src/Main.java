import java.util.*;

public class Main {   
    public static void main(String[] args) {        
        Kokoelma k = new Kokoelma();
        k.lisaaLehti(3);
        System.out.println(k.valitseLehti(4));
        System.out.println(k.valitseLehti(8));
        k.lisaaLehti(9);
        System.out.println(k.valitseLehti(4));
        System.out.println(k.valitseLehti(8));
    }        
}
