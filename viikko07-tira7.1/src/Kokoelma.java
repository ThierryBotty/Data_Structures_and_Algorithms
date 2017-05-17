import java.util.*;


public class Kokoelma {
    
    TreeSet<Integer> mappi;

    public Kokoelma() {
        this.mappi = new TreeSet();
    }
    void lisaaLehti(int luku) {
        this.mappi.add(luku);
    }
    
    int valitseLehti(int luku) {
        
        if (this.mappi.floor(luku) == null) {
            return this.mappi.ceiling(luku);
        } 
        int pienempi = this.mappi.floor(luku);
        
        if (this.mappi.ceiling(luku) == null) {
            return pienempi;
        }
        int suurempi = this.mappi.ceiling(luku);
        
        if (suurempi - luku < luku - pienempi) {
            return suurempi;
        }
        return pienempi;
    }
}

