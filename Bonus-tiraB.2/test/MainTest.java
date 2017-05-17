import org.junit.Test;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;
import java.util.Arrays;

@Points("B.2")
public class MainTest {
    public void pieniTesti(String mjono, boolean tulos) {
        boolean uusi = Main.tyhjennys(mjono);
        if (tulos) {
            assertTrue("Merkkijono " + mjono + " on mahdollista tyhjentää, " +
                       "mutta metodisi palauttaa 'false'.", uusi == tulos);
        } else {
            assertTrue("Merkkijono " + mjono + " ei ole mahdollista tyhjentää, " +
                       "mutta metodisi palauttaa 'true'.", uusi == tulos);            
        }
    }

    public void suuriTesti(String mjono, boolean tulos) {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        boolean uusi = Main.tyhjennys(mjono);
        assertTrue("Metodi toimii väärin suurella syötteellä.", uusi == tulos);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    
    @Test(timeout=1000)
    public void esimerkit() {
        pieniTesti("ABBABB", true);
        pieniTesti("ABBBBB", false);
        pieniTesti("AAAAAA", true);
        pieniTesti("ABABAB", false);
    }
    
    @Test(timeout=1000)
    public void pienet() {
        pieniTesti("A", false);
        pieniTesti("B", false);
        
        pieniTesti("AA", true);
        pieniTesti("AB", false);
        pieniTesti("BA", false);
        pieniTesti("BB", true);
        
        pieniTesti("AAA", false);
        pieniTesti("AAB", false);
        pieniTesti("ABA", false);
        pieniTesti("ABB", false);
        pieniTesti("BAA", false);
        pieniTesti("BAB", false);
        pieniTesti("BBA", false);
        pieniTesti("BBB", false);
        
        pieniTesti("AAAA", true);
        pieniTesti("AAAB", false);
        pieniTesti("AABA", false);
        pieniTesti("AABB", true);
        pieniTesti("ABAA", false);
        pieniTesti("ABAB", false);
        pieniTesti("ABBA", true);
        pieniTesti("ABBB", false);
        pieniTesti("BAAA", false);
        pieniTesti("BAAB", true);
        pieniTesti("BABA", false);
        pieniTesti("BABB", false);
        pieniTesti("BBAA", true);
        pieniTesti("BBAB", false);
        pieniTesti("BBBA", false);
        pieniTesti("BBBB", true);
        
        pieniTesti("ABAABA", true);
        pieniTesti("ABAAAB", false);
        pieniTesti("BAAAAB", true);
        pieniTesti("BABAAA", false);
        
        pieniTesti("ABABABBABABA", true);
        pieniTesti("ABABABABABABA", false);
        pieniTesti("BBABAABABB", true);
        pieniTesti("BABABAABABAB", true);
        pieniTesti("BBBABAABABBB", true);
        pieniTesti("BBBABAAABABBB", false);
    }
    
    @Test(timeout=5000)
    public void suuri1() {    
        int n = 100000;
        char[] mjono = new char[n];
        for (int i = 0; i < n; i++) {
            if (i%2 == 0) mjono[i] = 'A';
            else mjono[i] = 'B';
        }
        suuriTesti(new String(mjono), false);
    }    
    
    @Test(timeout=5000)
    public void suuri2() {
        int n = 100000;
        char[] mjono = new char[n];
        for (int i = 0; i < n; i++) {
            mjono[i] = 'A';
        }
        suuriTesti(new String(mjono), true);
    }    

    @Test(timeout=5000)
    public void suuri3() {    
        int n = 100000;
        char[] mjono = new char[n];
        for (int i = 0; i < n/2; i++) {
            if (i%2 == 0) mjono[i] = 'A';
            else mjono[i] = 'B';
        }
        for (int i = n/2; i < n; i++) {
            if (i%2 == 0) mjono[i] = 'B';
            else mjono[i] = 'A';
        }
        suuriTesti(new String(mjono), true);
    }    
    
    @Test(timeout=5000)
    public void suuri4() {
        int n = 100000;
        char[] mjono = new char[n];
        for (int i = 0; i < n; i++) {
            if (i%4 == 0 || i%4 == 1) mjono[i] = 'A';
            else mjono[i] = 'B';
        }
        suuriTesti(new String(mjono), true);
    }      
    
    @Test(timeout=5000)
    public void suuri5() {
        int n = 100000;
        char[] mjono = new char[n];
        for (int i = 0; i < n; i++) {
            if (i == 12345) mjono[i] = 'B';
            else if (i%4 == 0 || i%4 == 1) mjono[i] = 'A';
            else mjono[i] = 'B';
        }
        suuriTesti(new String(mjono), false);
    }          
    
}
