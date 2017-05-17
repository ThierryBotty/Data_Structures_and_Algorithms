import java.util.*;

public class Main {   
    public static void main(String[] args) {        
        Kokoelma k = new Kokoelma();
        System.out.println(k.suurinLehti()); // -1
        k.lisaaLehti(1);
        k.lisaaLehti(2);
        System.out.println(k.suurinLehti()); // 2
        k.poistaLehti(2);
        System.out.println(k.suurinLehti()); // 1
        k.lisaaLehti(3);
        k.lisaaLehti(3);
        System.out.println(k.suurinLehti()); // 3
        k.poistaLehti(3);
        System.out.println(k.suurinLehti()); // 3
        k.poistaLehti(3);
        System.out.println(k.suurinLehti()); // 1
        k.poistaLehti(1);
        System.out.println(k.suurinLehti()); // -1
    }        
}

