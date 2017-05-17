import org.junit.Test;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;
import java.util.Arrays;

@Points("8.4")
public class MainTest {
    public void pieniTesti(int[] koneet, int maara, long tulos) {
        String sisalto = Arrays.toString(koneet);
        long uusi = Main.lyhinAika(koneet, maara);
        assertTrue("Kun koneet ovat " + sisalto + " ja tuotettava määrä on " +
                   maara + ", lyhin aika on " + tulos +
                   ", mutta metodisi antaa " +  uusi, tulos == uusi);
    }
    
    public void suuriTesti(int[] koneet, int maara, long tulos) {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        long uusi = Main.lyhinAika(koneet, maara);
        assertTrue("Metodisi toimii väärin suurella syötteellä.", tulos == uusi);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    @Test(timeout=1000)
    public void esimerkit() {  
        pieniTesti(new int[] {1}, 5, 5);
        pieniTesti(new int[] {1, 1, 1}, 6, 2);
        pieniTesti(new int[] {5, 1, 1}, 6, 3);
        pieniTesti(new int[] {1, 2, 3, 4}, 10, 6);
    }
    
    @Test(timeout=1000)
    public void pienet() {  
        pieniTesti(new int[] {1, 2}, 1, 1);
        pieniTesti(new int[] {1, 2}, 2, 2);
        pieniTesti(new int[] {1, 2}, 3, 2);
        pieniTesti(new int[] {1, 2}, 4, 3);
        
        pieniTesti(new int[] {1, 3}, 1, 1);
        pieniTesti(new int[] {1, 3}, 2, 2);
        pieniTesti(new int[] {1, 3}, 3, 3);
        pieniTesti(new int[] {1, 3}, 4, 3);
        
        pieniTesti(new int[] {1, 2, 3}, 1, 1);
        pieniTesti(new int[] {1, 2, 3}, 2, 2);
        pieniTesti(new int[] {1, 2, 3}, 3, 2);
        pieniTesti(new int[] {1, 2, 3}, 4, 3);
        pieniTesti(new int[] {1, 2, 3}, 5, 3);
        pieniTesti(new int[] {1, 2, 3}, 6, 4);
        pieniTesti(new int[] {1, 2, 3}, 7, 4);
        pieniTesti(new int[] {1, 2, 3}, 8, 5);
        pieniTesti(new int[] {1, 2, 3}, 9, 6);
        pieniTesti(new int[] {1, 2, 3}, 10, 6);
        pieniTesti(new int[] {1, 2, 3}, 11, 6);
        pieniTesti(new int[] {1, 2, 3}, 12, 7);
        
        pieniTesti(new int[] {1, 5, 5}, 1, 1);
        pieniTesti(new int[] {1, 5, 5}, 2, 2);
        pieniTesti(new int[] {1, 5, 5}, 3, 3);
        pieniTesti(new int[] {1, 5, 5}, 4, 4);
        pieniTesti(new int[] {1, 5, 5}, 5, 5);
        pieniTesti(new int[] {1, 5, 5}, 6, 5);
        pieniTesti(new int[] {1, 5, 5}, 7, 5);
        pieniTesti(new int[] {1, 5, 5}, 8, 6);
        pieniTesti(new int[] {1, 5, 5}, 9, 7);
        pieniTesti(new int[] {1, 5, 5}, 10, 8);
        pieniTesti(new int[] {1, 5, 5}, 11, 9);
        pieniTesti(new int[] {1, 5, 5}, 12, 10);
        pieniTesti(new int[] {1, 5, 5}, 13, 10);
        pieniTesti(new int[] {1, 5, 5}, 14, 10);
        pieniTesti(new int[] {1, 5, 5}, 15, 11);
    }
    
    @Test(timeout=5000)
    public void suuri1() {
        int n = 100000;
        int[] koneet = new int[n];
        for (int i = 0; i < n; i++) koneet[i] = 1;
        suuriTesti(koneet, n, 1);
    }

    @Test(timeout=5000)
    public void suuri2() {
        int n = 99999;
        int[] koneet = new int[n];
        for (int i = 0; i < n; i++) koneet[i] = 1;
        suuriTesti(koneet, n+1, 2);
    }
    
    @Test(timeout=5000)
    public void suuri3() {
        int n = 100000;
        int[] koneet = new int[n];
        for (int i = 0; i < n; i++) koneet[i] = i+1;
        suuriTesti(koneet, 100000, 10610);
    }
    
    @Test(timeout=5000)
    public void suuri4() {
        int n = 100000;
        int[] koneet = new int[n];
        for (int i = 0; i < n; i++) koneet[i] = 1000000000;
        suuriTesti(koneet, 100000, 1000000000);
    }
    
    @Test(timeout=5000)
    public void suuri5() {
        int[] koneet = {1000000000};
        suuriTesti(koneet, 100000, 100000000000000L);
    }
    
    @Test(timeout=5000)
    public void suuri6() {
        int n = 100000;
        int[] koneet = new int[n];
        long x = 12345;
        long a = 798732191;
        long b = 921339221;             
        for (int i = 0; i < n; i++) {
            x = (x*a)%b;
            koneet[i] = (int)x;
        }
        suuriTesti(koneet, n, 70105652);
    }
}
