
public class Main {
    public static int valienMaara(long[] taulukko, long k) {
        int a = 0;
        int l = 0;
        int lukumaara = 0;
        long summa = 0;
        while (true) {
            
            if (a == l) {
                summa = taulukko[a];
            }
            
            if (summa == k ) {
                lukumaara++;
                a++;
                summa -= taulukko[a-1];
            }
            
            if (summa <= k) {
                l++;
                if (l == taulukko.length) {
                    break;
                }
                summa += taulukko[l];
                
            } else {
                summa -= taulukko[a];
                a++;
            }
        }
        return lukumaara;
    }

    public static void main(String[] args) {
        System.out.println(valienMaara(new long[] {5,2,4,2,9,1,9,1,1,2,3,9,2,5,5,5,9,9,3,5}, 40));
        System.out.println(valienMaara(new long[] {1, 1, 1, 1}, 1));
        System.out.println(valienMaara(new long[] {1, 2, 3, 4}, 3));
        System.out.println(valienMaara(new long[] {1, 3, 1, 2}, 3));
    }
}
