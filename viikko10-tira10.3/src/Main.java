import java.util.*;


public class Main {
    public static void main(String[] args) {
        Reitinlaskija r1 = new Reitinlaskija(4, new int[]{1,2,2,3,3,4,4,1}, 
                                                new int[]{2,1,3,2,4,3,1,4});
        
        System.out.println(r1.lyhinReitti(1, 3)); // 2
        System.out.println(r1.lyhinReitti(4, 2)); // 2
        System.out.println(r1.lyhinReitti(4, 1)); // 1
        System.out.println(r1.lyhinReitti(1, 4)); // 1
        System.out.println(r1.lyhinReitti(2, 3)); // 3
        
        System.out.println("");
        
        Reitinlaskija r2 = new Reitinlaskija(4, new int[]{1,2,1,3,1,4}, 
                                                new int[]{2,1,3,1,4,1});
        System.out.println(r2.lyhinReitti(2, 3)); // 2
        System.out.println(r2.lyhinReitti(3, 4)); // 2
        System.out.println(r2.lyhinReitti(4, 2)); // 2
        System.out.println(r2.lyhinReitti(1, 1)); // 0
        
        System.out.println("");
        
        Reitinlaskija r3 = new Reitinlaskija(6, new int[]{1,2,3,4,5}, 
                                                new int[]{2,3,4,5,1});
        System.out.println(r3.lyhinReitti(1, 6)); //-1
        System.out.println(r3.lyhinReitti(2, 3)); // 6
        System.out.println(r3.lyhinReitti(5, 2)); // 2
        System.out.println(r3.lyhinReitti(2, 5)); // 8

    }        
}



