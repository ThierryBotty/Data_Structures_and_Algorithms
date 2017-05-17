import org.junit.Test;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;
import java.util.Arrays;

@Points("5.5")
public class MainTest {
    public void pieniTesti(String mjono, int tulos) {
        int uusi = Main.pisinTasainen(mjono);
        assertTrue("Merkkijonon " + mjono + " tulisi tuottaa tulos " + tulos +
                   ", mutta metodisi antaa " + uusi + ".",
                   tulos == uusi);
    }
    
    public void suuriTesti(String mjono, int tulos) {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        int uusi = Main.pisinTasainen(mjono);
        assertTrue("Metodisi toimii väärin suurella syötteellä.",
                   tulos == uusi);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    @Test(timeout=5000)
    public void esimerkki() {
        pieniTesti("ACGTACGT", 8);
        pieniTesti("CAATGTCG", 8);
        pieniTesti("TTATCGTT", 4);
        pieniTesti("AAAAAAAA", 0);
    }
    
    @Test(timeout=5000)
    public void pienet() {
        pieniTesti("A", 0);
        pieniTesti("AA", 0);
        pieniTesti("AAA", 0);
        pieniTesti("AAAA", 0);
        pieniTesti("AAAAA", 0);
        
        pieniTesti("AC", 0);
        pieniTesti("ACA", 0);
        pieniTesti("ACAC", 0);
        pieniTesti("ACACA", 0);
        pieniTesti("ACACAC", 0);
        
        pieniTesti("ACG", 0);
        pieniTesti("ACGACG", 0);
        pieniTesti("ACGACGACG", 0);
        
        pieniTesti("ACGT", 4);
        pieniTesti("ATCG", 4);
        pieniTesti("TACG", 4);
        pieniTesti("TCAG", 4);
        
        pieniTesti("ACAGATA", 0);        
        pieniTesti("AAAAACGTAAAA", 4);
        pieniTesti("ACGCGTTA", 8);
        pieniTesti("ACGCGTTAC", 8);
        pieniTesti("CACGCGTTA", 8);
        pieniTesti("ACTCGTTA", 0);
        
        pieniTesti("ACGTACGTACGT", 12);
        pieniTesti("ACGTACGTACGTA", 12);
        pieniTesti("AACGTACGTACGT", 12);
    }
    
    @Test(timeout=5000)
    public void suuri1() {
        int n = 100000;
        char[] mjono = new char[n];
        for (int i = 0; i < n; i++) mjono[i] = 'A';
        suuriTesti(new String(mjono), 0);
    }
    
    @Test(timeout=5000)
    public void suuri2() {
        int n = 100000;
        char[] mjono = new char[n];
        for (int i = 0; i < n; i++) mjono[i] = "ACGT".charAt(i%4);
        suuriTesti(new String(mjono), n);
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
        suuriTesti(new String(mjono), 24024);
    }
    
    @Test(timeout=5000)
    public void suuri4() {
        int n = 100000;
        char[] mjono = new char[n];
        long x = 54321;
        long a = 798732191;
        long b = 921339221;          
        for (int i = 0; i < n; i++) {
            x = (x*a)%b;
            mjono[i] = "ACGT".charAt((int)x%4);
        }
        suuriTesti(new String(mjono), 11816);
    }    
    
}
