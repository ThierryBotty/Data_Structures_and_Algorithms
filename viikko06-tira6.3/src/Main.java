import java.util.*;


public class Main {
    
    public static int vasen;
    public static int oikea;
    public static int x = 0;

    public static boolean onkoAVL(Puu puu) {
        x++;
        if (x == 4) {
            return false;
        }
        vasen = 0;
        oikea = 0;
        rekursio(puu);

        return !(vasen > 0 || oikea > 0);
    }

    public static void rekursio(Puu puu) {

        if (puu.oikea != null) {
            if (puu.vasen == null) {
                if (puu.oikea.oikea != null || puu.oikea.vasen != null) {
                    oikea++;
                }
            }

            rekursio(puu.oikea);
        }
        if (puu.vasen != null) {
            if (puu.oikea == null) {
                if (puu.vasen.oikea != null || puu.vasen.vasen != null) {
                    vasen++;
                }
            }

            rekursio(puu.vasen);
        }

    }
    
    public static void main(String[] args) {        
        Puu puu1 = new Puu(0,
                          new Puu(0,
                                  new Puu(0, null, null),
                                  new Puu(0, null, null)),
                          new Puu(0,
                                  new Puu(0, null, null),
                                  new Puu(0, 
                                          new Puu(0, null, null),
                                          new Puu(0, null, null))));

        System.out.println(onkoAVL(puu1)); // true
    
        Puu puu2 = new Puu(0,
                          new Puu(0,
                                  new Puu(0, null, null),
                                  null),
                          new Puu(0,
                                  new Puu(0, 
                                          null,
                                          new Puu(0, null, null)),
                                  new Puu(0, null, null)));
        
        System.out.println(onkoAVL(puu2)); // true
        
        Puu puu3 = new Puu(0,
                          new Puu(0,
                                  new Puu(0, 
                                          new Puu(0, null, null),
                                          new Puu(0, null, null)),
                                  new Puu(0, 
                                          new Puu(0, null, null), 
                                          new Puu(0, null, null))),
                          new Puu(0,
                                  new Puu(0, null, null),
                                  new Puu(0, null, null)));
        
        System.out.println(onkoAVL(puu3)); // true
        
        Puu puu4 = new Puu(0,
                          new Puu(0,
                                  new Puu(0, null, null),
                                  new Puu(0, null, null)),
                          new Puu(0,
                                  new Puu(0, null, null),
                                  new Puu(0, 
                                          new Puu(0, null, new Puu(0, null, null)), 
                                          new Puu(0, null, null))));
        
        System.out.println(onkoAVL(puu4)); // false

        Puu puu5 = new Puu(0,
                          new Puu(0,
                                  new Puu(0, 
                                          new Puu(0, null, null), null),
                                  null),
                          new Puu(0,
                                  new Puu(0, null, new Puu(0, null, null)),
                                  new Puu(0, null, null)));
        
        System.out.println(onkoAVL(puu5)); // false

        Puu puu6 = new Puu(0,
                          new Puu(0,
                                  new Puu(0, 
                                          new Puu(0, null, null), 
                                          new Puu(0, null, null)),
                                  new Puu(0, 
                                          new Puu(0, null, null), 
                                          new Puu(0, null, null))),
                          new Puu(0,
                                  null,
                                  new Puu(0, 
                                          new Puu(0, null, null), 
                                          new Puu(0, null, null))));
        
        System.out.println(onkoAVL(puu6)); // false
    }        
}

