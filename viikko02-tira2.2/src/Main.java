
public class Main {

    public static int laske(int n, String s) {

        int luku = 0;
        for (int i = 0; i < Math.pow(2, n); i++) {
            String binaari = Integer.toBinaryString(i);
            if (binaari.length() < n) {
                String apu = "";
                for (int j = 0; j < (n - binaari.length()); j++) {
                    apu = "0" + apu;

                }
                binaari = apu + binaari;
            }
            
            if (!binaari.contains(s)) {
                //System.out.println(binaari);
                luku++;
            }
        }
        return luku;
    }

    public static void main(String[] args) {
        System.out.println(laske(2, "11"));
        System.out.println(laske(2, "1"));
        System.out.println(laske(3, "10"));
        System.out.println(laske(15, "10"));
    }
}
