import org.junit.Test;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;
import java.util.Arrays;

@Points("B.3")
public class MainTest {
    public void pieniTesti(String[] sanat, int tulos) {
        String sisalto = Arrays.toString(sanat);
        int uusi = Main.suurinRyhma(sanat);
        assertTrue("Suurin ryhmä sanoista " + sisalto + " tulisi sisältää " +
                   tulos + " sanaa, mutta metodisi antaa " + uusi + ".",
                   tulos == uusi);
    }

    public void suuriTesti(String[] sanat, int tulos) {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        int uusi = Main.suurinRyhma(sanat);
        assertTrue("Metodisi toimii väärin suurella syötteellä.",
                   tulos == uusi);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    @Test(timeout=1000)
    public void esimerkit() {
        pieniTesti(new String[] {"apina", "banaani", "cembalo"}, 1);
        pieniTesti(new String[] {"talo", "katu", "lato"}, 2);
        pieniTesti(new String[] {"ab", "ab", "ba", "ba"}, 4);
        pieniTesti(new String[] {"iines", "otto", "sieni", "toto"}, 2);
    }
    
    @Test(timeout=1000)
    public void pienet() {
        pieniTesti(new String[] {"a"}, 1);
        pieniTesti(new String[] {"a", "a"}, 2);
        pieniTesti(new String[] {"a", "a", "a"}, 3);
        pieniTesti(new String[] {"a", "a", "a", "a"}, 4);
        pieniTesti(new String[] {"a", "a", "a", "a", "a"}, 5);

        pieniTesti(new String[] {"a"}, 1);
        pieniTesti(new String[] {"a", "b"}, 1);
        pieniTesti(new String[] {"a", "b", "a"}, 2);
        pieniTesti(new String[] {"a", "b", "a", "b"}, 2);
        pieniTesti(new String[] {"a", "b", "a", "b", "a"}, 3);
        
        pieniTesti(new String[] {"abcde", "edcba"}, 2);
        pieniTesti(new String[] {"abcbe", "edcba"}, 1);
        pieniTesti(new String[] {"abcde", "edcbaa"}, 1);

        pieniTesti(new String[] {"abc", "acb", "bac", "bca", "cab", "cba"}, 6);
        pieniTesti(new String[] {"abc", "acb", "bac", "bcb", "cab", "cba"}, 5);

        pieniTesti(new String[] {"abc", "bca", "cab", "xyz", "yzx", "zxy"}, 3);
        pieniTesti(new String[] {"abc", "bca", "cab", "xyz", "yzx", "qqq"}, 3);
    }
    
    @Test(timeout=5000)
    public void suuri1() {
        int n = 100000;
        String[] sanat = new String[n];
        for (int i = 0; i < n; i++) sanat[i] = "ababababab";
        suuriTesti(sanat, n);
    }
    
    @Test(timeout=5000)
    public void suuri2() {
        int n = 100000;
        String[] sanat = new String[n];
        char[] c = new char[10];
        for (int j = 0; j < 10; j++) c[j] = 'a';
        for (int i = 0; i < n; i++) {
            sanat[i] = new String(c);
            c[0]++;
            for (int j = 1; j < 10; j++) {
                if (c[j-1] == 'e') {
                    c[j-1] = 'a';
                    c[j]++;
                } else {
                    break;
                }
            }
        }
        suuriTesti(sanat, 3435);
    }    

    @Test(timeout=5000)
    public void suuri4() {
        int n = 100000;
        String[] sanat = new String[n];
        long x = 12345;
        long a = 798732191;
        long b = 921339221;        
        for (int i = 0; i < n; i++) {
            char[] c = new char[10];
            for (int j = 0; j < 10; j++) {
                x = (x*a)%b;
                c[j] = (char)('a'+(x%2));
            }
            sanat[i] = new String(c);
        }
        suuriTesti(sanat, 24576);
    }        
    
    
    @Test(timeout=5000)
    public void suuri5() {
        int n = 100000;
        String[] sanat = new String[n];
        long x = 12345;
        long a = 798732191;
        long b = 921339221;        
        for (int i = 0; i < n; i++) {
            char[] c = new char[10];
            for (int j = 0; j < 10; j++) {
                x = (x*a)%b;
                c[j] = (char)('a'+(x%26));
            }
            sanat[i] = new String(c);
        }
        suuriTesti(sanat, 2);
    }        
}
