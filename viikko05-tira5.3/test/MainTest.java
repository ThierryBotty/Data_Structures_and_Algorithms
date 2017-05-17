import org.junit.Test;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;
import java.util.Arrays;

@Points("5.3")
public class MainTest {
    public void pieniTesti(String mjono, int tulos) {
        int uusi = Main.lyhinPuuttuva(mjono);
        assertTrue("Merkkijonon " + mjono + " lyhimmän puuttuvan osan " + 
                   "pituus on " + tulos + ", mutta metodisi antaa " + uusi + ".",
                   tulos == uusi);
    }
    
    public void suuriTesti(String mjono, int tulos) {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        int uusi = Main.lyhinPuuttuva(mjono);
        assertTrue("Metodisi toimii väärin suurella syötteellä.",
                   tulos == uusi);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    public void bonusTesti(int r) {
        int n = (1<<(2*r))+2*r-1;
        int[] x = new int[n];
        boolean[] s = new boolean[n];
        int q = 0;
        int m = (1<<(2*r))-1;
        for (int i = 0; i < n; i++) {
            if (i < 2*r) continue;
            q = (2*q+1)&m;
            if (s[q]) q--;
            else x[i] = 1;
            s[q] = true;
        }
        char[] mjono = new char[n-1];
        int c = 0;
        for (int i = 0; i < n-1; i += 2) {
            mjono[c++] = "ACGT".charAt(x[i]*2+x[i+1]);
        }
        for (int i = 1; i < n; i += 2) {
            mjono[c++] = "ACGT".charAt(x[i]*2+x[i+1]);
        }
        suuriTesti(new String(mjono), r+1);        
    }
    
    @Test(timeout=5000)
    public void esimerkki() {
        pieniTesti("CCCCCCCC", 1);
        pieniTesti("ACGTACGT", 2);
        pieniTesti("ACAAGCAG", 1);
        pieniTesti("ACACACGT", 2);
    }
    
    @Test(timeout=5000)
    public void testit() {
        pieniTesti("A", 1);
        pieniTesti("AA", 1);
        pieniTesti("AAA", 1);
        pieniTesti("AAAA", 1);
        pieniTesti("AAAAA", 1);
        
        pieniTesti("AC", 1);
        pieniTesti("ACG", 1);
        pieniTesti("ACGT", 2);

        pieniTesti("ACA", 1);
        pieniTesti("ACAC", 1);
        pieniTesti("ACACA", 1);
        pieniTesti("ACACAC", 1);
        pieniTesti("ACACACA", 1);
        
        pieniTesti("AACCGGTT", 2);
        pieniTesti("AAAAACGT", 2);
        pieniTesti("ACGACGACGT", 2);
        
        pieniTesti("AACAGATCCGCTGGTTA", 3);
    }

    @Test(timeout=5000)
    public void suuri1() {
        int n = 100000;
        char[] mjono = new char[n];
        for (int i = 0; i < n; i++) mjono[i] = 'A';
        suuriTesti(new String(mjono), 1);
    }
    
    @Test(timeout=5000)
    public void suuri2() {
        int n = 100000;
        char[] mjono = new char[n];
        for (int i = 0; i < n; i++) mjono[i] = "ACGT".charAt(i%4);
        suuriTesti(new String(mjono), 2);
    }
    
    @Test(timeout=5000)
    public void suuri3() {
        int n = 100000;
        char[] mjono = new char[n];
        long x = 12345;
        long a = 798732191;
        long b = 921339221;         
        for (int i = 0; i < n; i++) {
            x = (x*a)%b;
            mjono[i] = "ACGT".charAt((int)x%4);
        }
        suuriTesti(new String(mjono), 7);
    }
    
    @Test(timeout=5000)
    public void suuri4() {
        bonusTesti(4);
    }
    
    @Test(timeout=5000)
    public void suuri5() {
        bonusTesti(5);
    }

    @Test(timeout=5000)
    public void suuri6() {
        bonusTesti(6);
    }

    @Test(timeout=5000)
    public void suuri7() {
        bonusTesti(7);
    }

    @Test(timeout=5000)
    public void suuri8() {
        bonusTesti(8);
    }
    
}
