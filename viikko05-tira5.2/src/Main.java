
import java.util.*;

public class Main {

    public static class Reppu {
        private HashMap<Integer, Integer> reppu;

        Reppu() {
            this.reppu = new HashMap<Integer, Integer>();
        }

        void lisaa(int x) {
            if (reppu.containsKey(x)) {
                this.reppu.put(x, this.reppu.get(x) + 1);
            } else {
                this.reppu.put(x, 1);
            }
        }

        void heitaPois(int x) {
            if (reppu.containsKey(x)) {
                this.reppu.put(x, this.reppu.get(x) - 1);
                
                if (this.reppu.get(x) == 0) {
                    this.reppu.remove(x);
                }
            }
        }

        boolean sisaltaako(int x) {
            return this.reppu.containsKey(x);
        }
    }

    public static void main(String[] args) {
        Reppu r = new Reppu();
        System.out.println(r.sisaltaako(1)); //false
        r.lisaa(1);
        System.out.println(r.sisaltaako(1)); //true
        System.out.println(r.sisaltaako(2)); //false
        r.heitaPois(1);
        System.out.println(r.sisaltaako(1)); //false
        r.lisaa(1000);
        r.lisaa(1000);
        System.out.println(r.sisaltaako(1000)); //true
        r.heitaPois(1000);
        System.out.println(r.sisaltaako(1000)); //true
    }
}
