import java.util.*;

public class Main {   
    public static void main(String[] args) {
        Tiet t=new Tiet(5);
        System.out.println(t.yhteys(1,2)); //false
        System.out.println(t.yhteys(1,1)); //true
        t.rakenna(1, 2);
        System.out.println(t.yhteys(1,2)); //true
        System.out.println(t.yhteys(1,3)); //false
        t.rakenna(2, 3);
        System.out.println(t.yhteys(1,3)); //true
        t.rakenna(1, 4);
        System.out.println(t.yhteys(4,3)); //true
    }        
}
