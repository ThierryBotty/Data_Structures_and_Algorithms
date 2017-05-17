import org.junit.Test;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import java.util.Arrays;

@Points("BONUS2_4")
public class MainTest {
    private String kaarilista(int[] mista, int[] minne) {
        String tulos = "";
        for (int i = 0; i < mista.length; i++) {
            if (tulos.equals("")) tulos = mista[i] + "-" + minne[i];
            else tulos += ", "  + mista[i] + "-" + minne[i];
        }
        return "[" + tulos + "]";
    }    
    
    public void pieniTesti(int n, int[] mista, int[] minne, int tulos) {
        String sisalto = kaarilista(mista, minne);
        int uusi = Main.kaukaisimmat(n, mista, minne);
        assertTrue("Kun henkilöitä on " + n + " ja ystävyydet ovat " + sisalto +
                   ", kaukaisin henkilöpari on " + tulos +
                   " yhteyden päässä, mutta metodisi antaa " + uusi + ".",
                   tulos == uusi);
    }
    
    public void suuriTesti(int n, int[] mista, int[] minne, int tulos) {
        int uusi = Main.kaukaisimmat(n, mista, minne);
        assertTrue("Metodisi toimii väärin suurella syötteellä.",
                   tulos == uusi);
    }
        
    @Test(timeout=1000)
    public void esimerkit() {
        pieniTesti(3, new int[] {1, 2}, new int[] {2, 3}, 2);
        pieniTesti(3, new int[] {1, 1}, new int[] {2, 3}, 2);
        pieniTesti(3, new int[] {1, 2, 3}, new int[] {2, 3, 1}, 1);
        pieniTesti(4, new int[] {1, 2, 3}, new int[] {2, 3, 4}, 3);
    }
    
    @Test(timeout=1000)
    public void pienet() {
        pieniTesti(2, new int[] {1}, new int[] {2}, 1);
        
        pieniTesti(4, new int[] {1, 2, 3, 4}, new int[] {2, 3, 4, 1}, 2);
        pieniTesti(4, new int[] {1, 1, 1, 2, 2, 3}, new int[] {2, 3, 4, 3, 4, 4}, 1);
        pieniTesti(4, new int[] {1, 2, 3, 1}, new int[] {2, 3, 1, 4}, 2);

        pieniTesti(5, new int[] {1, 1, 1, 1}, new int[] {2, 3, 4, 5}, 2);
        pieniTesti(5, new int[] {1, 2, 3, 4}, new int[] {2, 3, 4, 5}, 4);
        pieniTesti(5, new int[] {1, 2, 3, 4, 5}, new int[] {2, 3, 4, 5, 1}, 2);        
        pieniTesti(5, new int[] {1, 2, 3, 1, 4}, new int[] {2, 3, 1, 4, 5}, 3);
        
        pieniTesti(6, new int[] {1, 2, 3, 4, 5, 6, 1}, new int[] {2, 3, 1, 5, 6, 4, 6}, 3);
        pieniTesti(7, new int[] {1, 2, 3, 4, 5, 6, 1, 7}, new int[] {2, 3, 1, 5, 6, 4, 7, 6}, 4);        
    }
    
    @Test(timeout=1000)
    public void suuri1() {
        int n = 100;
        int[] mista = new int[n-1];
        int[] minne = new int[n-1];
        for (int i = 0; i < n-1; i++) {
            mista[i] = i+1;
            minne[i] = i+2;
        }
        suuriTesti(n, mista, minne, n-1);
    }
    
    @Test(timeout=1000)
    public void suuri2() {
        int n = 100;
        int[] mista = new int[n-1];
        int[] minne = new int[n-1];
        for (int i = 0; i < n-1; i++) {
            mista[i] = i+1;
            minne[i] = n;
        }
        suuriTesti(n, mista, minne, 2);
    }
    
    @Test(timeout=1000)
    public void suuri3() {
        int n = 14;
        int[] mista = new int[n*(n-1)/2];
        int[] minne = new int[n*(n-1)/2];
        int c = 0;
        for (int i = 1; i <= n; i++)
            for (int j = i+1; j <= n; j++) {
                mista[c] = i;
                minne[c] = j;
                c++;
        }
        suuriTesti(n, mista, minne, 1);
    }
        
}
