import org.junit.Test;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;
import java.util.Arrays;

@Points("4.5")
public class MainTest {
    public void pieniTesti(int[] luvut, int[] tulos) {
        String sisalto1 = Arrays.toString(luvut);
        String sisalto2 = Arrays.toString(tulos);
        int[] uusi = Main.suuremmat(luvut);
        String sisalto3 = Arrays.toString(uusi);
        assertTrue("Taulukosta " + sisalto1 + " tulisi tulla " + sisalto2 +
                   ", mutta metodisi palauttaa " + sisalto3 + ".",
                   Arrays.equals(tulos, uusi));
    }

    public void suuriTesti(int[] luvut, int[] tulos) {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        int[] uusi = Main.suuremmat(luvut);
        assertTrue("Metodi toimii väärin suurella syötteellä.",
                   Arrays.equals(tulos, uusi));
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    @Test(timeout=5000)
    public void esimerkit() {
        pieniTesti(new int[] {1, 2, 3, 4, 5}, new int[] {2, 3, 4, 5, 0});
        pieniTesti(new int[] {5, 4, 3, 2, 1}, new int[] {0, 0, 0, 0, 0});
        pieniTesti(new int[] {4, 3, 2, 1, 5}, new int[] {5, 5, 5, 5, 0});
        pieniTesti(new int[] {1, 5, 2, 4, 3}, new int[] {5, 0, 4, 0, 0});
    }    

    @Test(timeout=5000)
    public void pienet() {
        pieniTesti(new int[] {1}, new int[] {0});

        pieniTesti(new int[] {1, 2}, new int[] {2, 0});
        pieniTesti(new int[] {2, 1}, new int[] {0, 0});
        pieniTesti(new int[] {1, 1}, new int[] {0, 0});
        
        pieniTesti(new int[] {1, 2, 3}, new int[] {2, 3, 0});
        pieniTesti(new int[] {1, 3, 2}, new int[] {3, 0, 0});
        pieniTesti(new int[] {2, 1, 3}, new int[] {3, 3, 0});
        pieniTesti(new int[] {2, 3, 1}, new int[] {3, 0, 0});
        pieniTesti(new int[] {3, 1, 2}, new int[] {0, 2, 0});
        pieniTesti(new int[] {3, 2, 1}, new int[] {0, 0, 0});        
        pieniTesti(new int[] {1, 2, 2}, new int[] {2, 0, 0});
        pieniTesti(new int[] {2, 1, 2}, new int[] {0, 2, 0});
        pieniTesti(new int[] {2, 2, 1}, new int[] {0, 0, 0});
        pieniTesti(new int[] {1, 1, 1}, new int[] {0, 0, 0});

        pieniTesti(new int[] {1, 999999999}, new int[] {999999999, 0});
        pieniTesti(new int[] {999999998, 999999999}, new int[] {999999999, 0});
        pieniTesti(new int[] {999999999, 999999999}, new int[] {0, 0});

        pieniTesti(new int[] {5, 2, 2, 4, 3, 5, 1, 2, 5, 3},
                   new int[] {0, 4, 4, 5, 5, 0, 2, 5, 0, 0});
        pieniTesti(new int[] {1, 5, 5, 2, 3, 2, 4, 2, 5, 1},
                   new int[] {5, 0, 0, 3, 4, 4, 5, 5, 0, 0});
        pieniTesti(new int[] {3, 3, 5, 2, 4, 3, 4, 4, 4, 2},
                   new int[] {5, 5, 0, 4, 0, 4, 0, 0, 0, 0});        
    }    
    
    @Test(timeout=5000)
    public void suuri1() {    
        int n = 100000;
        int[] luvut = new int[n];
        for (int i = 0; i < 50000; i++) luvut[i] = 100000-2*i;
        for (int i = 50000; i < 100000; i++) luvut[i] = 100001-2*(i-50000);
        int[] tulos = new int[n];
        for (int i = 0; i < 50000; i++) tulos[i] = 100001;
        for (int i = 50000; i < 100000; i++) tulos[i] = 0;
        suuriTesti(luvut, tulos);
    }

    @Test(timeout=5000)
    public void suuri2() {
        int n = 100000;
        int[] luvut = new int[n];
        for (int i = 0; i < n; i++) luvut[i] = i+1;
        int[] tulos = new int[n];
        for (int i = 0; i < n; i++) tulos[i] = i+2;
        tulos[n-1] = 0;
        suuriTesti(luvut, tulos);
    }    
    
    @Test(timeout=5000)
    public void suuri3() {
        int n = 100000;
        int[] luvut = new int[n];
        for (int i = 0; i < n; i++) luvut[i] = n-i;
        int[] tulos = new int[n];
        for (int i = 0; i < n; i++) tulos[i] = 0;
        tulos[n-1] = 0;
        suuriTesti(luvut, tulos);
    }     
    
    @Test(timeout=5000)
    public void suuri4() {
        int n = 100000;
        int[] luvut = new int[n];
        for (int i = 0; i < n; i++) luvut[i] = n-i-1;
        luvut[n-1] = n;
        int[] tulos = new int[n];
        for (int i = 0; i < n; i++) tulos[i] = n;
        tulos[n-1] = 0;
        suuriTesti(luvut, tulos);
    }
}
