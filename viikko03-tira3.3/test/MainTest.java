import org.junit.Test;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;
import java.util.Arrays;

@Points("3.3")
public class MainTest {
    public void pieniTesti(int[] luvut, int x, boolean tulos) {
        String sisalto = Arrays.toString(luvut);
        boolean uusi = Main.onkoSummaa(luvut, x);
        if (tulos) {
            assertTrue("Taulukosta " + sisalto + " saa summan " + x +
                       ", mutta metodisi palauttaa 'false'.", tulos == uusi);
        } else {
            assertTrue("Taulukosta " + sisalto + " ei saa summaa " + x +
                       ", mutta metodisi palauttaa 'true'.", tulos == uusi);           
        }
    }
    
    public void suuriTesti(int[] luvut, int x, boolean tulos) {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        boolean uusi = Main.onkoSummaa(luvut, x);
        assertTrue("Metodisi toimii väärin suurella syötteellä.", tulos == uusi);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    @Test(timeout=5000)
    public void esimerkki() {
        pieniTesti(new int[] {1, 2, 3}, 5, true);
        pieniTesti(new int[] {1, 2, 3}, 6, false);
        pieniTesti(new int[] {1, 3, 3}, 6, true);
        pieniTesti(new int[] {1, 2, 3}, 6, false);
    }
    
    @Test(timeout=5000)
    public void pienet() {
        pieniTesti(new int[] {2}, 3, false);
        pieniTesti(new int[] {2}, 4, false);

        pieniTesti(new int[] {1, 2}, 1, false);
        pieniTesti(new int[] {1, 2}, 2, false);
        pieniTesti(new int[] {1, 2}, 3, true);
        pieniTesti(new int[] {1, 2}, 4, false);
        pieniTesti(new int[] {2, 2}, 1, false);
        pieniTesti(new int[] {2, 2}, 2, false);
        pieniTesti(new int[] {2, 2}, 3, false);
        pieniTesti(new int[] {2, 2}, 4, true);
        
        pieniTesti(new int[] {1, 1, 1, 1}, 1, false);
        pieniTesti(new int[] {1, 1, 1, 1}, 2, true);
        pieniTesti(new int[] {1, 1, 1, 1}, 3, false);
        pieniTesti(new int[] {1, 1, 1, 1}, 4, false);
        
        pieniTesti(new int[] {-1, 1}, 0, true);
        pieniTesti(new int[] {-999999999, 999999999}, 0, true);
        pieniTesti(new int[] {-2, -2}, -4, true);
        
        pieniTesti(new int[] {5, 4, 3, 2, 1}, 1, false);
        pieniTesti(new int[] {5, 4, 3, 2, 1}, 2, false);
        pieniTesti(new int[] {5, 4, 3, 2, 1}, 3, true);
        pieniTesti(new int[] {5, 4, 3, 2, 1}, 4, true);
        pieniTesti(new int[] {5, 4, 3, 2, 1}, 5, true);
        pieniTesti(new int[] {5, 4, 3, 2, 1}, 6, true);
        pieniTesti(new int[] {5, 4, 3, 2, 1}, 7, true);
        pieniTesti(new int[] {5, 4, 3, 2, 1}, 8, true);
        pieniTesti(new int[] {5, 4, 3, 2, 1}, 9, true);
        pieniTesti(new int[] {5, 4, 3, 2, 1}, 10, false);
        pieniTesti(new int[] {5, 4, 3, 2, 1}, 11, false);
        pieniTesti(new int[] {5, 4, 3, 2, 1}, 12, false);
    }    
    
    @Test(timeout=5000)
    public void suuri1() {    
        int n = 100000;
        int[] luvut = new int[n];
        for (int i = 0; i < n; i++) luvut[i] = 1;
        suuriTesti(luvut, 1, false);
    }
    
    @Test(timeout=5000)
    public void suuri2() {    
        int n = 100000;
        int[] luvut = new int[n];
        for (int i = 0; i < n; i++) luvut[i] = 1;
        suuriTesti(luvut, 2, true);
    }

    @Test(timeout=5000)
    public void suuri3() {
        int n = 100000;
        int[] luvut = new int[n];
        for (int i = 0; i < n; i++) luvut[i] = 1;
        suuriTesti(luvut, 4, false);
    }
    
    @Test(timeout=5000)
    public void suuri4() {    
        int n = 100000;
        int[] luvut = new int[n];
        for (int i = 0; i < n; i++) luvut[i] = i+1;
        suuriTesti(luvut, 2, false);
    }    
    
    @Test(timeout=5000)
    public void suuri5() {    
        int n = 100000;
        int[] luvut = new int[n];
        for (int i = 0; i < n; i++) luvut[i] = i+1;
        suuriTesti(luvut, 3, true);
    }    

    @Test(timeout=5000)
    public void suuri6() {    
        int n = 100000;
        int[] luvut = new int[n];
        for (int i = 0; i < n; i++) luvut[i] = i+1;
        suuriTesti(luvut, 199999, true);
    }    

    @Test(timeout=5000)
    public void suuri7() {    
        int n = 100000;
        int[] luvut = new int[n];
        for (int i = 0; i < n; i++) luvut[i] = i+1;
        suuriTesti(luvut, 200000, false);
    }    
    
    @Test(timeout=5000)
    public void suuri8() {    
        int n = 100000;
        int[] luvut = new int[n];
        long x = 12345;
        long a = 798732191;
        long b = 921339221;        
        for (int i = 0; i < n; i++) {
            x = (x*a)%b;
            luvut[i] = (int)x;
        }
        suuriTesti(luvut, 12345, false);
    }    

    @Test(timeout=5000)
    public void suuri9() {    
        int n = 100000;
        int[] luvut = new int[n];
        long x = 12345;
        long a = 798732191;
        long b = 921339221;        
        for (int i = 0; i < n; i++) {
            x = (x*a)%b;
            luvut[i] = (int)x;
        }
        suuriTesti(luvut, 1388211566, true);
    }    
}

