import java.util.*;

public class Main {
    public static int pisinPolku(Puu puu) {
        return 0;
    }
    
    public static void main(String[] args) {        
        Puu puu = new Puu(1,
                          new Puu(3,
                                  new Puu(2, null, null),
                                  null),
                          new Puu(3,
                                  new Puu(3, null, null),
                                  new Puu(2, null, null)));

        System.out.println(pisinPolku(puu));
    }        
}
