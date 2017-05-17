
import java.util.*;

public class Kokoelma {

    TreeSet<Integer> lehdet = new TreeSet<Integer>();
    
    public Kokoelma() {
    for (int i = 1; i < 100000; i++) {
            lehdet.add(i);
        }
    }

    void lisaaLehti(int luku) {
        this.lehdet.remove(luku);
    }

    int pieninPuuttuva() {
        return this.lehdet.first();
    }
}
