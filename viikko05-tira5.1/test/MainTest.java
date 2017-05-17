import org.junit.Test;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;
import java.util.Arrays;

@Points("5.1")
public class MainTest {
    public void pieniTesti(String reitti, int tulos) {
        int uusi = Main.reitinPituus(reitti);
        assertTrue("Reitin " + reitti + " pituuden tulisi olla " + tulos +
                   ", mutta metodisi antaa " + uusi + ".",
                   tulos == uusi);
    }

    public void suuriTesti(String reitti, int tulos) {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        int uusi = Main.reitinPituus(reitti);
        assertTrue("Metodisi toimii väärin suurella syötteellä.",
                   tulos == uusi);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    @Test(timeout=5000)
    public void esimerkit() {
        pieniTesti("YYVVAAOO", 8);
        pieniTesti("YVAOYVAO", 4);
        pieniTesti("YYYYYYYY", 0);
        pieniTesti("OYVVAOOO", 6);
    }
    
    @Test(timeout=5000)
    public void pienet() {
        pieniTesti("Y", 0);
        pieniTesti("A", 0);
        pieniTesti("V", 0);
        pieniTesti("O", 0);
        
        pieniTesti("YY", 0);
        pieniTesti("AA", 0);
        pieniTesti("VV", 0);
        pieniTesti("OO", 0);

        pieniTesti("YA", 2);
        pieniTesti("AY", 2);
        pieniTesti("VO", 2);
        pieniTesti("OV", 2);
        
        pieniTesti("YV", 0);
        pieniTesti("AV", 0);
        pieniTesti("OY", 0);
        pieniTesti("OA", 0);
        
        pieniTesti("VYOA", 4);
        pieniTesti("OAVY", 4);
        pieniTesti("AOYV", 4);
        pieniTesti("AVYO", 4);
        
        pieniTesti("YYYAAA", 4);
        pieniTesti("YYYVVV", 0);
        
        pieniTesti("YYOOAVVVAO", 7);
        pieniTesti("OOYYOAOYYY", 0);
        pieniTesti("OOYYOAAVVV", 8);        
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
        for (int i = 0; i < n/2; i++) mjono[i] = 'A';
        for (int i = n/2; i < n; i++) mjono[i] = 'Y';
        suuriTesti(new String(mjono), n/2+1);
    }    
    
    @Test(timeout=5000)
    public void suuri3() {
        int n = 100000;
        char[] mjono = new char[n];
        for (int i = 0; i < n/4; i++) mjono[i] = 'A';
        for (int i = n/4; i < n/2; i++) mjono[i] = 'O';
        for (int i = n/2; i < n/2+n/4; i++) mjono[i] = 'Y';
        for (int i = n/2+n/4; i < n; i++) mjono[i] = 'V';
        suuriTesti(new String(mjono), n);
    }    
    
    @Test(timeout=5000)
    public void suuri4() {
        int n = 100000;
        char[] mjono = new char[n];
        long x = 12345;
        long a = 798732191;
        long b = 921339221;
        for (int i = 0; i < n/3; i++) {
            x = (x*a)%b;
            if (x%2 == 0) mjono[i] = 'O';
            if (x%2 == 1) mjono[i] = 'Y';
        }
        mjono[n/3] = 'O';
        for (int i = n/3+1; i < n/2; i++) {
            mjono[i] = 'A';
        }
        mjono[n/2] = 'V';
        for (int i = n/2+1; i < n; i++) {
            x = (x*a)%b;
            if (x%2 == 0) mjono[i] = 'V';
            if (x%2 == 1) mjono[i] = 'Y';
        }        
        suuriTesti(new String(mjono), 66746);
    }        

}
