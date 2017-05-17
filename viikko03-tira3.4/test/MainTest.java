import org.junit.Test;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;
import java.util.Arrays;

@Points("3.4")
public class MainTest {
    public void pieniTesti(int[] luvut, int tulos) {
        String sisalto = Arrays.toString(luvut);
        int uusi = Main.keraaLuvut(luvut);
        assertTrue("Lukujen " + sisalto + " keräämiseen tarvitsee " + tulos +
                   "läpikäyntiä, mutta metodisi palauttaa " + uusi + ".",
                   tulos == uusi);
    }

    public void suuriTesti(int[] luvut, int tulos) {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        int uusi = Main.keraaLuvut(luvut);
        assertTrue("Metodisi toimii väärin suurella syötteellä.",
                   tulos == uusi);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    @Test(timeout=5000)
    public void esimerkit() {
        pieniTesti(new int[] {1, 2, 3, 4, 5}, 1);
        pieniTesti(new int[] {5, 1, 2, 3, 4}, 2);
        pieniTesti(new int[] {5, 4, 3, 2, 1}, 5);
        pieniTesti(new int[] {1, 5, 2, 4, 3}, 3);
    }

    @Test(timeout=5000)
    public void pienet() {
        pieniTesti(new int[] {1}, 1);
        pieniTesti(new int[] {1, 2}, 1);
        pieniTesti(new int[] {2, 1}, 2);
        pieniTesti(new int[] {1, 2, 3}, 1);
        pieniTesti(new int[] {1, 3, 2}, 2);
        pieniTesti(new int[] {2, 1, 3}, 2);
        pieniTesti(new int[] {2, 3, 1}, 2);
        pieniTesti(new int[] {3, 1, 2}, 2);
        pieniTesti(new int[] {3, 2, 1}, 3);
        
        pieniTesti(new int[] {1, 2, 5, 6, 3, 4}, 2);
        pieniTesti(new int[] {1, 4, 5, 2, 6, 3}, 2);
        pieniTesti(new int[] {6, 2, 5, 3, 4, 1}, 4);
        pieniTesti(new int[] {6, 1, 4, 2, 5, 3}, 3);
        pieniTesti(new int[] {6, 5, 4, 1, 2, 3}, 4);
        pieniTesti(new int[] {6, 1, 2, 4, 5, 3}, 3);
        pieniTesti(new int[] {3, 6, 2, 4, 5, 1}, 4);
        pieniTesti(new int[] {3, 1, 5, 2, 4, 6}, 3);
    }
    
    @Test(timeout=5000)
    public void suuri1() {
        int n = 100000;
        int[] luvut = new int[n];
        for (int i = 0; i < n; i++) luvut[i] = i+1;
        suuriTesti(luvut, 1);
    }

    @Test(timeout=5000)
    public void suuri2() {
        int n = 100000;
        int[] luvut = new int[n];
        for (int i = 0; i < n; i++) luvut[i] = n-i;
        suuriTesti(luvut, n);
    }

    @Test(timeout=5000)
    public void suuri3() {
        int n = 100000;
        int[] luvut = new int[n];
        for (int i = 0; i < n/2; i++) {
            luvut[i] = 2*i+1;
            luvut[n-i-1] = 2*i+2;
        }
        suuriTesti(luvut, n/2);
    }

    @Test(timeout=5000)
    public void suuri4() {
        int n = 100000;
        int[] luvut = new int[n];
        for (int i = 0; i < n; i++) {
            luvut[i] = i+1;
        }
        long x = 12345;
        long a = 798732191;
        long b = 921339221;
        for (int i = 0; i < n; i++) {
            x = (x*a)%b;
            int k = (int)x%(n-i);
            int t = luvut[i];
            luvut[i] = luvut[i+k];
            luvut[i+k] = t;
        }
        suuriTesti(luvut, 49993);
    }

    @Test(timeout=5000)
    public void suuri5() {
        int n = 100000;
        int[] luvut = new int[n];
        for (int i = 0; i < n; i++) {
            luvut[i] = i+1;
        }
        long x = 54321;
        long a = 798732191;
        long b = 921339221;
        for (int i = 0; i < n; i++) {
            x = (x*a)%b;
            int k = (int)x%(n-i);
            int t = luvut[i];
            luvut[i] = luvut[i+k];
            luvut[i+k] = t;
        }
        suuriTesti(luvut, 49969);
    }
    
    
    
}
