import java.util.*;

public class Main {   
    public static void main(String[] args) {        
        Kokoelma k = new Kokoelma();
        System.out.println(k.pieninPuuttuva());
        k.lisaaLehti(3);
        System.out.println(k.pieninPuuttuva());
        k.lisaaLehti(2);
        System.out.println(k.pieninPuuttuva());
        k.lisaaLehti(1);
        System.out.println(k.pieninPuuttuva());
    }        
}
