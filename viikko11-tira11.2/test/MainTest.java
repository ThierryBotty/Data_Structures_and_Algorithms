import org.junit.Test;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;
import java.util.Arrays;

@Points("11.2")
public class MainTest {
    
    private String kaarilista(int[] mista, int[] minne, long[] matka) {
        String tulos = "";
        for (int i = 0; i < mista.length; i++) {
            if (tulos.equals("")) tulos = mista[i] + "-" + minne[i] + "(" + matka[i] + ")";
            else tulos += ", "  + mista[i] + "-" + minne[i] + "(" + matka[i] + ")";
        }
        return "[" + tulos + "]";
    }    
    
    public void pieniTesti(int n, int[] mista, int[] minne, long[] matka, long tulos) {
        String sisalto = kaarilista(mista, minne, matka);
        long uusi = Main.lyhinReitti(n, mista, minne, matka);
        assertTrue("Kun kaupunkeja on " + n + " ja tiet ovat " + sisalto +
                   ", lyhin matka kaupungista 1 kaupunkiin " + n +
                   " on " + tulos + ", mutta metodisi antaa " + uusi + ".",
                   tulos == uusi);
    }
    
    public void suuriTesti(int n, int[] mista, int[] minne, long[] matka, long tulos) {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        long uusi = Main.lyhinReitti(n, mista, minne, matka);
        assertTrue("Metodisi toimii väärin suurella syötteellä.",
                   tulos == uusi);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    
    @Test(timeout=5000)
    public void esimerkit() {
        pieniTesti(3, new int[] {1, 2}, new int[] {2, 3}, new long[] {5, 3}, 8);
        pieniTesti(3, new int[] {1, 1}, new int[] {2, 3}, new long[] {2, 3}, 3);
        pieniTesti(3, new int[] {1, 2, 1}, new int[] {3, 3, 2}, new long[] {9, 1, 1}, 2);
        pieniTesti(3, new int[] {1, 2, 1}, new int[] {3, 3, 2}, new long[] {1, 9, 9}, 1);
    }
    
    @Test(timeout=5000)
    public void pienet() {
        pieniTesti(2, new int[] {}, new int[] {}, new long[] {}, -1);
        pieniTesti(2, new int[] {1}, new int[] {2}, new long[] {5}, 5);
        pieniTesti(2, new int[] {2}, new int[] {1}, new long[] {5}, 5);

        pieniTesti(3, new int[] {}, new int[] {}, new long[] {}, -1);
        pieniTesti(3, new int[] {1}, new int[] {2}, new long[] {5}, -1);
        pieniTesti(3, new int[] {1}, new int[] {3}, new long[] {5}, 5);
        pieniTesti(3, new int[] {2}, new int[] {3}, new long[] {5}, -1);

        pieniTesti(3, new int[] {2, 2}, new int[] {1, 3}, new long[] {1, 1}, 2);

        pieniTesti(4, new int[] {1, 1, 2, 3}, new int[] {2, 3, 4, 4}, new long[] {3, 4, 4, 3}, 7);
        pieniTesti(4, new int[] {1, 1, 2, 3}, new int[] {2, 3, 4, 4}, new long[] {3, 4, 3, 4}, 6);
        pieniTesti(4, new int[] {1, 1, 2, 3}, new int[] {2, 3, 4, 4}, new long[] {3, 4, 1, 4}, 4);

        pieniTesti(5, new int[] {}, new int[] {}, new long[] {}, -1);
        pieniTesti(5, new int[] {1}, new int[] {5}, new long[] {10}, 10);
        pieniTesti(5, new int[] {1, 1, 2, 3, 4}, new int[] {5, 2, 3, 4, 5}, new long[] {10, 1, 1, 1, 1}, 4);
        pieniTesti(5, new int[] {1, 1, 2, 3, 4}, new int[] {5, 2, 3, 4, 5}, new long[] {10, 2, 2, 2, 2}, 8);
        pieniTesti(5, new int[] {1, 1, 2, 3, 4}, new int[] {5, 2, 3, 4, 5}, new long[] {10, 3, 3, 3, 3}, 10);        
        
        pieniTesti(4, new int[] {1, 2, 3}, new int[] {2, 3, 4}, new long[] {999999999, 999999999, 999999999}, (long)3*999999999);
    }
    
    @Test(timeout=5000)
    public void suuri1() {
        int n = 100000;
        int[] mista = new int[n-1];
        int[] minne = new int[n-1];
        long[] matka = new long[n-1];
        for (int i = 0; i < n-1; i++) {
            mista[i] = i+1;
            minne[i] = i+2;
            matka[i] = 1;
        }
        suuriTesti(n, mista, minne, matka, n-1);
    }
    
    @Test(timeout=5000)
    public void suuri2() {
        int n = 100000;
        int[] mista = new int[n-1];
        int[] minne = new int[n-1];
        long[] matka = new long[n-1];
        for (int i = 0; i < n-1; i++) {
            mista[i] = i+1;
            minne[i] = i+2;
            matka[i] = 999999999;
        }
        suuriTesti(n, mista, minne, matka, (long)999999999*(n-1));
    }
    
    @Test(timeout=5000)
    public void suuri3() {
        int n = 100000;
        int[] mista = new int[(n-1)/3*4];
        int[] minne = new int[(n-1)/3*4];
        long[] matka = new long[(n-1)/3*4];
        int c = 0;
        for (int i = 0; i < (n-1)/3*4; i += 4) {
            mista[i] = i+1-c;
            minne[i] = i+2-c;
            mista[i+1] = i+1-c;
            minne[i+1] = i+3-c;
            mista[i+2] = i+2-c;
            minne[i+2] = i+4-c;
            mista[i+3] = i+3-c;
            minne[i+3] = i+4-c;
            if (c%2 == 0) {
                matka[i] = 1;
                matka[i+1] = 2;
                matka[i+2] = 1;
                matka[i+3] = 2;
            } else {
                matka[i] = 2;
                matka[i+1] = 1;
                matka[i+2] = 2;
                matka[i+3] = 1;                
            }
            c++;
        }
        suuriTesti(n, mista, minne, matka, (n-1)/3*2);
    }
}

