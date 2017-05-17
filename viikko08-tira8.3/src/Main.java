import java.util.*;

public class Main {   
    public static void main(String[] args) {        
        Toimisto t = new Toimisto();
        t.lisaaJonoon("Uolevi", 8);
        t.lisaaJonoon("Maija", 4);
        System.out.println(t.annaAsunto());
        t.lisaaJonoon("Liisa", 5);
        System.out.println(t.annaAsunto());
        System.out.println(t.annaAsunto());
    }        
}
