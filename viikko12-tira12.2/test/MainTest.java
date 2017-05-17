import org.junit.Test;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;
import java.util.Arrays;

@Points("12.2")
public class MainTest {
    private String kaarilista(int[] mista, int[] minne, int[] hinta) {
        String tulos = "";
        for (int i = 0; i < mista.length; i++) {
            if (tulos.equals("")) tulos = mista[i] + "-" + minne[i] + "(" + hinta[i] + ")";
            else tulos += ", "  + mista[i] + "-" + minne[i] + "(" + hinta[i] + ")";
        }
        return "[" + tulos + "]";
    }        
    
    public void pieniTesti(int n, int[] mista, int[] minne, int[] hinta, long tulos) {
        String sisalto = kaarilista(mista, minne, hinta);
        long uusi = Main.kunnostus(n, mista, minne, hinta);
        assertTrue("Kun kaupunkeja on " + n + " ja tiet ovat " + sisalto +
                   ", halvin ratkaisu on " + tulos +
                   ", mutta metodisi antaa " + uusi + ".",
                   tulos == uusi);
    }
    
    public void suuriTesti(int n, int[] mista, int[] minne, int[] hinta, long tulos) {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        long uusi = Main.kunnostus(n, mista, minne, hinta);
        assertTrue("Metodisi toimii väärin suurella syötteellä.",
                   tulos == uusi);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    @Test(timeout=1000)
    public void esimerkit() {
        pieniTesti(3, new int[] {1, 2}, new int[] {2, 3}, new int[] {7, 4}, 11);        
        pieniTesti(3, new int[] {1, 2, 1}, new int[] {2, 3, 3}, new int[] {7, 4, 5}, 9);
        pieniTesti(3, new int[] {1, 2, 1}, new int[] {2, 3, 3}, new int[] {2, 2, 2}, 4);
    }
    
    @Test(timeout=1000)
    public void pienet() {
        pieniTesti(2, new int[] {1}, new int[] {2}, new int[] {100}, 100);

        pieniTesti(3, new int[] {1, 2, 3}, new int[] {2, 3, 1}, new int[] {1, 2, 3}, 3);
        pieniTesti(3, new int[] {1, 2, 3}, new int[] {2, 3, 1}, new int[] {1, 3, 2}, 3);
        pieniTesti(3, new int[] {1, 2, 3}, new int[] {2, 3, 1}, new int[] {2, 1, 3}, 3);
        pieniTesti(3, new int[] {1, 2, 3}, new int[] {2, 3, 1}, new int[] {2, 3, 1}, 3);
        pieniTesti(3, new int[] {1, 2, 3}, new int[] {2, 3, 1}, new int[] {3, 1, 2}, 3);
        pieniTesti(3, new int[] {1, 2, 3}, new int[] {2, 3, 1}, new int[] {3, 2, 1}, 3);

        pieniTesti(4, new int[] {1, 2, 3}, new int[] {2, 3, 4}, new int[] {5, 5, 5}, 15);
        pieniTesti(4, new int[] {1, 2, 3}, new int[] {2, 3, 4}, new int[] {1, 2, 3}, 6);
        pieniTesti(4, new int[] {1, 2, 3, 4}, new int[] {2, 3, 4, 1}, new int[] {1, 2, 3, 4}, 6);
        pieniTesti(4, new int[] {1, 2, 3, 4}, new int[] {2, 3, 1, 1}, new int[] {1, 2, 3, 100}, 103);
        pieniTesti(4, new int[] {1, 2, 3, 4}, new int[] {2, 3, 1, 1}, new int[] {1, 2, 100, 1}, 4);

        pieniTesti(5, new int[] {1, 2, 3, 3, 4}, new int[] {2, 3, 1, 4, 5}, new int[] {1, 1, 1, 5, 5}, 12);
        pieniTesti(5, new int[] {1, 2, 3, 3, 4, 5}, new int[] {2, 3, 1, 4, 5, 1}, new int[] {1, 1, 1, 5, 5, 1}, 8);
        
        pieniTesti(4, new int[] {1, 2, 3}, new int[] {2, 3, 4}, new int[] {999999999, 999999999, 999999999}, (long)3*999999999);
    }
    
    @Test(timeout=5000)
    public void suuri1() {
        int n = 100000;
        int[] mista = new int[n-1];
        int[] minne = new int[n-1];
        int[] hinta = new int[n-1];
        for (int i = 0; i < n-1; i++) {
            mista[i] = i+1;
            minne[i] = i+2;
            hinta[i] = 1;
        }
        suuriTesti(n, mista, minne, hinta, n-1);
    }
    
    @Test(timeout=5000)
    public void suuri2() {
        int n = 100000;
        int[] mista = new int[n-1];
        int[] minne = new int[n-1];
        int[] hinta = new int[n-1];
        for (int i = 0; i < n-1; i++) {
            mista[i] = i+1;
            minne[i] = i+2;
            hinta[i] = 999999999;
        }
        suuriTesti(n, mista, minne, hinta, (long)(n-1)*999999999);
    }
    
    @Test(timeout=5000)
    public void suuri3() {
        int n = 444;
        int[] mista = new int[n*(n-1)/2];
        int[] minne = new int[n*(n-1)/2];
        int[] hinta = new int[n*(n-1)/2];
        int c = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = i+1; j <= n; j++) {
                mista[c] = i;
                minne[c] = j;
                hinta[c] = 1;
                c++;
            }
        }
        suuriTesti(n, mista, minne, hinta, n-1);
    }
    
    @Test(timeout=5000)
    public void suuri4() {
        int n = 444;
        int[] mista = new int[n*(n-1)/2];
        int[] minne = new int[n*(n-1)/2];
        int[] hinta = new int[n*(n-1)/2];
        int c = 0;
        long x = 12345;
        long a = 798732191;
        long b = 921339221;         
        for (int i = 1; i <= n; i++) {
            for (int j = i+1; j <= n; j++) {
                x = (x*a)%b;
                mista[c] = i;
                minne[c] = j;
                hinta[c] = (int)x;
                c++;
            }
        }
        suuriTesti(n, mista, minne, hinta, 1208824231);
    }

    @Test(timeout=5000)
    public void suuri5() {
        int n = 444;
        int[] mista = new int[n*(n-1)/2];
        int[] minne = new int[n*(n-1)/2];
        int[] hinta = new int[n*(n-1)/2];
        int c = 0;
        long x = 54321;
        long a = 798732191;
        long b = 921339221;         
        for (int i = 1; i <= n; i++) {
            for (int j = i+1; j <= n; j++) {
                x = (x*a)%b;
                mista[c] = i;
                minne[c] = j;
                hinta[c] = (int)x;
                c++;
            }
        }
        suuriTesti(n, mista, minne, hinta, 1138923510);
    }        
    
}

