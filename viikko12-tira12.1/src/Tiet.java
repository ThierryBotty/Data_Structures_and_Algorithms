
import java.util.*;

public class Tiet {

    int[] esisolmu;
    int[] koko;

    int apu(int a) {
        while (esisolmu[a] != a) {
            a = esisolmu[a];
        }
        return a;
    }

    Tiet(int n) {
        esisolmu = new int[n + 1];
        koko = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            esisolmu[i] = i;
            koko[i] = 1;
        }
    }

    void rakenna(int a, int b) {
        a = apu(a);
        b = apu(b);

        if (a == b) {
            return;
        }

        if (koko[a] > koko[b]) {
            esisolmu[b] = a;
            koko[a] += koko[b];
        } else {
            esisolmu[a] = b;
            koko[b] += koko[a];
        }
    }

    boolean yhteys(int a, int b) {
        return apu(a) == apu(b);
    }
}
