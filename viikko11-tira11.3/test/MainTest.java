import org.junit.Test;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;
import java.util.Arrays;

@Points("11.3")
public class MainTest {
    
    private String kaarilista(int[] mista, int[] minne) {
        String tulos = "";
        for (int i = 0; i < mista.length; i++) {
            if (tulos.equals("")) tulos = mista[i] + "-" + minne[i];
            else tulos += ", "  + mista[i] + "-" + minne[i];
        }
        return "[" + tulos + "]";
    }    
    
    public void pieniTesti(int n, int[] juna1, int[] juna2, int[] lento1, int[] lento2, int tulos) {
        String sisalto1 = kaarilista(juna1, juna2);
        String sisalto2 = kaarilista(lento1, lento2);
        int uusi = Main.lentomaara(n, juna1, juna2, lento1, lento2);
        assertTrue("Kun kaupunkeja on " + n + ", junat ovat " + sisalto1 +
                   " ja lennot ovat " + sisalto2 + ", lentoja tarvitaan " + tulos +
                   ", mutta metodisi antaa " + uusi + ".", tulos == uusi);
    }
    
    public void suuriTesti(int n, int[] juna1, int[] juna2, int[] lento1, int[] lento2, int tulos) {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        int uusi = Main.lentomaara(n, juna1, juna2, lento1, lento2);
        assertTrue("Metodisi toimii väärin suurella syötteellä.", tulos == uusi);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
        
    @Test(timeout=5000)
    public void esimerkit() {
        pieniTesti(3, new int[] {1}, new int[] {2}, new int[] {1, 2}, new int[] {2, 3}, 1);
        pieniTesti(3, new int[] {1}, new int[] {3}, new int[] {1, 2}, new int[] {2, 3}, 0);
        pieniTesti(3, new int[] {}, new int[] {}, new int[] {1, 2}, new int[] {2, 3}, 2);
        pieniTesti(3, new int[] {1, 2}, new int[] {2, 3}, new int[] {1, 2}, new int[] {2, 3}, 0);
    }
    
    @Test(timeout=5000)
    public void pienet() {
        pieniTesti(2, new int[] {}, new int[] {}, new int[] {}, new int[] {}, -1);
        pieniTesti(2, new int[] {1}, new int[] {2}, new int[] {}, new int[] {}, 0);
        pieniTesti(2, new int[] {}, new int[] {}, new int[] {1}, new int[] {2}, 1);
        pieniTesti(2, new int[] {1}, new int[] {2}, new int[] {1}, new int[] {2}, 0);
        
        pieniTesti(3, new int[] {1}, new int[] {2}, new int[] {2}, new int[] {3}, 1);
        pieniTesti(3, new int[] {1, 3}, new int[] {2, 1}, new int[] {2}, new int[] {3}, 0);
        pieniTesti(3, new int[] {1}, new int[] {2}, new int[] {1}, new int[] {2}, -1);

        pieniTesti(6, new int[] {1, 2, 3, 4, 5}, new int[] {2, 3, 4, 5, 6}, new int[] {}, new int[] {}, 0);
        pieniTesti(6, new int[] {1, 2, 3, 4, 5}, new int[] {2, 3, 4, 5, 6}, new int[] {1}, new int[] {6}, 0);
        pieniTesti(6, new int[] {1, 2, 3, 4}, new int[] {2, 3, 4, 5}, new int[] {}, new int[] {}, -1);
        pieniTesti(6, new int[] {1, 2, 3, 4}, new int[] {2, 3, 4, 5}, new int[] {5}, new int[] {6}, 1);
        
        pieniTesti(6, new int[] {}, new int[] {}, new int[] {1, 2, 3, 4, 5}, new int[] {2, 3, 4, 5, 6}, 5);
        pieniTesti(6, new int[] {3}, new int[] {4}, new int[] {1, 2, 3, 4, 5}, new int[] {2, 3, 4, 5, 6}, 4);
        pieniTesti(6, new int[] {3, 5}, new int[] {4, 6}, new int[] {1, 2, 3, 4, 5}, new int[] {2, 3, 4, 5, 6}, 3);    
    }
    
    @Test(timeout=5000)
    public void suuri1() {
        int n = 100000;
        int[] juna1 = new int[n-1];
        int[] juna2 = new int[n-1];
        int[] lento1 = {};
        int[] lento2 = {};
        for (int i = 0; i < n-1; i++) {
            juna1[i] = i+1;
            juna2[i] = i+2;
        }
        suuriTesti(n, juna1, juna2, lento1, lento2, 0);
    }
    
    @Test(timeout=5000)
    public void suuri2() {
        int n = 100000;
        int[] juna1 = {};
        int[] juna2 = {};
        int[] lento1 = new int[n-1];
        int[] lento2 = new int[n-1];
        for (int i = 0; i < n-1; i++) {
            lento1[i] = i+1;
            lento2[i] = i+2;
        }
        suuriTesti(n, juna1, juna2, lento1, lento2, n-1);
    }
    
    @Test(timeout=5000)
    public void suuri3() {
        int n = 100000;
        int[] juna1 = new int[n-1];
        int[] juna2 = new int[n-1];
        int[] lento1 = new int[n-1];
        int[] lento2 = new int[n-1];
        for (int i = 0; i < n-1; i++) {
            juna1[i] = i+1;
            juna2[i] = i+2;
            lento1[i] = i+1;
            lento2[i] = i+2;
        }
        suuriTesti(n, juna1, juna2, lento1, lento2, 0);
    }
    
    @Test(timeout=5000)
    public void suuri4() {
        int n = 444;
        int[] juna1 = new int[n*(n-1)/2-(n-1)];
        int[] juna2 = new int[n*(n-1)/2-(n-1)];
        int[] lento1 = {n-1};
        int[] lento2 = {n};
        int c = 0;
        for (int i = 1; i <= n-1; i++) {
            for (int j = i+1; j <= n-1; j++) {
                juna1[c] = i;
                juna2[c] = j;
                c++;
            }
        }
        suuriTesti(n, juna1, juna2, lento1, lento2, 1);
    }
}
