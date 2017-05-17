
import java.util.*;

public class Kokoelma {

    TreeMap<Integer, Integer> mappi = new TreeMap();

    void lisaaLehti(int luku) {
        if (this.mappi.containsKey(luku)) {
            this.mappi.put(luku, mappi.get(luku) + 1);
        } else {
            this.mappi.put(luku, 1);
        }
    }

    void poistaLehti(int luku) {
        if (this.mappi.containsKey(luku)) {
            this.mappi.put(luku, mappi.get(luku) - 1);
            if (this.mappi.get(luku) == 0) {
                this.mappi.remove(luku);
            }
        }
    }

    int suurinLehti() {
        if (this.mappi.isEmpty()) {
            return -1;
        }
        return this.mappi.lastKey();
    }
}
