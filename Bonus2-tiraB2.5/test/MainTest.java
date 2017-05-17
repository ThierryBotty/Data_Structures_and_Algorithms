import org.junit.Test;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import java.util.Arrays;

@Points("BONUS2_5")
public class MainTest {
    private String kaarilista(int[] mista, int[] minne) {
        String tulos = "";
        for (int i = 0; i < mista.length; i++) {
            if (tulos.equals("")) tulos = mista[i] + "-" + minne[i];
            else tulos += ", "  + mista[i] + "-" + minne[i];
        }
        return "[" + tulos + "]";
    }    
    
    public void pieniTesti(int n, int[] mista, int[] mihin, Lentoreitit x, int alku, int loppu, int lennot, boolean tulos) {
        String sisalto = kaarilista(mista, mihin);
        boolean uusi = x.mahdollinen(alku, loppu, lennot);
        if (tulos) {
            assertTrue("Kun kaupunkeja on " + n + " ja reitit ovat " + sisalto +
                       ", on mahdollista lentää kaupungista " + alku + 
                       " kaupunkiin " + loppu + " käyttäen " + lennot +
                       " lentoa, mutta metodisi palauttaa 'false'.", tulos == uusi);
        } else {
            assertTrue("Kun kaupunkeja on " + n + " ja reitit ovat " + sisalto +
                       ", ei ole mahdollista lentää kaupungista " + alku + 
                       " kaupunkiin " + loppu + " käyttäen " + lennot +
                       " lentoa, mutta metodisi palauttaa 'true'.", tulos == uusi);
        }
    }
    
    public void suuriTesti(int n, int[] mista, int[] mihin, Lentoreitit x, int alku, int loppu, int lennot, boolean tulos) {
        boolean uusi = x.mahdollinen(alku, loppu, lennot);
        assertTrue("Metodisi toimii väärin suurella syötteellä.", tulos == uusi);
    }
    
    @Test(timeout=1000)
    public void esimerkki() {
        int n = 4;
        int[] mista = {1, 1};
        int[] mihin = {2, 3};
        Lentoreitit x = new Lentoreitit(n, mista, mihin);
        pieniTesti(n, mista, mihin, x, 1, 2, 1, true);
        pieniTesti(n, mista, mihin, x, 1, 1, 4, true);
        pieniTesti(n, mista, mihin, x, 1, 2, 2, false);
        pieniTesti(n, mista, mihin, x, 1, 4, 3, false);
    }
    
    @Test(timeout=1000)
    public void pieni1() {
        int n = 4;
        int[] mista = {1, 2, 3, 4};
        int[] mihin = {2, 3, 4, 1};
        Lentoreitit x = new Lentoreitit(n, mista, mihin);
        pieniTesti(n, mista, mihin, x, 1, 2, 1, true);
        pieniTesti(n, mista, mihin, x, 1, 3, 1, false);
        pieniTesti(n, mista, mihin, x, 1, 4, 1, true);
        pieniTesti(n, mista, mihin, x, 1, 2, 2, false);
        pieniTesti(n, mista, mihin, x, 1, 3, 2, true);
        pieniTesti(n, mista, mihin, x, 1, 4, 2, false);
        pieniTesti(n, mista, mihin, x, 1, 2, 3, true);
        pieniTesti(n, mista, mihin, x, 1, 3, 3, false);
        pieniTesti(n, mista, mihin, x, 1, 4, 3, true);
        pieniTesti(n, mista, mihin, x, 1, 2, 4, false);
        pieniTesti(n, mista, mihin, x, 1, 3, 4, true);
        pieniTesti(n, mista, mihin, x, 1, 4, 4, false);
    }
    
    @Test(timeout=1000)
    public void pieni2() {
        int n = 4;
        int[] mista = {1, 1, 1};
        int[] mihin = {2, 3, 4};
        Lentoreitit x = new Lentoreitit(n, mista, mihin);
        pieniTesti(n, mista, mihin, x, 1, 2, 1, true);
        pieniTesti(n, mista, mihin, x, 1, 3, 1, true);
        pieniTesti(n, mista, mihin, x, 1, 4, 1, true);
        pieniTesti(n, mista, mihin, x, 1, 2, 2, false);
        pieniTesti(n, mista, mihin, x, 1, 3, 2, false);
        pieniTesti(n, mista, mihin, x, 1, 4, 2, false);
        pieniTesti(n, mista, mihin, x, 1, 2, 3, true);
        pieniTesti(n, mista, mihin, x, 1, 3, 3, true);
        pieniTesti(n, mista, mihin, x, 1, 4, 3, true);
        pieniTesti(n, mista, mihin, x, 1, 2, 4, false);
        pieniTesti(n, mista, mihin, x, 1, 3, 4, false);
        pieniTesti(n, mista, mihin, x, 1, 4, 4, false);
    }

    @Test(timeout=1000)
    public void pieni3() {
        int n = 8;
        int[] mista = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] mihin = {2, 3, 4, 5, 6, 7, 8, 1};
        Lentoreitit x = new Lentoreitit(n, mista, mihin);
        pieniTesti(n, mista, mihin, x, 1, 2, 1, true);
        pieniTesti(n, mista, mihin, x, 1, 3, 1, false);
        pieniTesti(n, mista, mihin, x, 1, 4, 1, false);
        pieniTesti(n, mista, mihin, x, 1, 5, 1, false);
        pieniTesti(n, mista, mihin, x, 1, 6, 1, false);
        pieniTesti(n, mista, mihin, x, 1, 7, 1, false);
        pieniTesti(n, mista, mihin, x, 1, 8, 1, true);

        pieniTesti(n, mista, mihin, x, 1, 2, 2, false);
        pieniTesti(n, mista, mihin, x, 1, 3, 2, true);
        pieniTesti(n, mista, mihin, x, 1, 4, 2, false);
        pieniTesti(n, mista, mihin, x, 1, 5, 2, false);
        pieniTesti(n, mista, mihin, x, 1, 6, 2, false);
        pieniTesti(n, mista, mihin, x, 1, 7, 2, true);
        pieniTesti(n, mista, mihin, x, 1, 8, 2, false);
        
        pieniTesti(n, mista, mihin, x, 1, 2, 3, true);
        pieniTesti(n, mista, mihin, x, 1, 3, 3, false);
        pieniTesti(n, mista, mihin, x, 1, 4, 3, true);
        pieniTesti(n, mista, mihin, x, 1, 5, 3, false);
        pieniTesti(n, mista, mihin, x, 1, 6, 3, true);
        pieniTesti(n, mista, mihin, x, 1, 7, 3, false);
        pieniTesti(n, mista, mihin, x, 1, 8, 3, true);
        
        pieniTesti(n, mista, mihin, x, 1, 2, 4, false);
        pieniTesti(n, mista, mihin, x, 1, 3, 4, true);
        pieniTesti(n, mista, mihin, x, 1, 4, 4, false);
        pieniTesti(n, mista, mihin, x, 1, 5, 4, true);
        pieniTesti(n, mista, mihin, x, 1, 6, 4, false);
        pieniTesti(n, mista, mihin, x, 1, 7, 4, true);
        pieniTesti(n, mista, mihin, x, 1, 8, 4, false);               
    }
    
    @Test(timeout=1000)
    public void pieni4() {
        int n = 3;
        int[] mista = {1};
        int[] mihin = {2};
        Lentoreitit x = new Lentoreitit(n, mista, mihin);
        pieniTesti(n, mista, mihin, x, 1, 2, 1, true);
        pieniTesti(n, mista, mihin, x, 1, 2, 2, false);
        pieniTesti(n, mista, mihin, x, 1, 2, 3, true);
        pieniTesti(n, mista, mihin, x, 1, 2, 4, false);
        pieniTesti(n, mista, mihin, x, 1, 2, 5, true);
        pieniTesti(n, mista, mihin, x, 1, 2, 6, false);
        pieniTesti(n, mista, mihin, x, 1, 2, 7, true);
        pieniTesti(n, mista, mihin, x, 1, 2, 8, false);
        
        pieniTesti(n, mista, mihin, x, 1, 3, 100, false);
    }
    
    @Test(timeout=1000)
    public void suuri1() {
        int n = 100;
        int[] mista = {};
        int[] mihin = {};
        Lentoreitit x = new Lentoreitit(n, mista, mihin);
        for (int i = 1; i <= n; i++) {
            for (int j = i+1; j <= n; j++) {
                suuriTesti(n, mista, mihin, x, i, j, 1, false);
            }
        }
    }
    
    @Test(timeout=1000)
    public void suuri2() {
        int n = 100;
        int[] mista = new int[n*(n-1)/2];
        int[] mihin = new int[n*(n-1)/2];
        int c = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = i+1; j <= n; j++) {
                mista[c] = i;
                mihin[c] = j;
                c++;
            }
        }
        Lentoreitit x = new Lentoreitit(n, mista, mihin);
        for (int i = 1; i <= 100; i++) {
            for (int j = i+1; j <= 100; j++) {
                suuriTesti(n, mista, mihin, x, i, j, 1, true);
            }
        }
    }
    
    @Test(timeout=1000)
    public void suuri3() {
        int n = 100;
        int[] mista = new int[n*(n-1)/2];
        int[] mihin = new int[n*(n-1)/2];
        int c = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = i+1; j <= n; j++) {
                mista[c] = i;
                mihin[c] = j;
                c++;
            }
        }
        Lentoreitit x = new Lentoreitit(n, mista, mihin);
        for (int i = 1; i <= 100; i++) {
            for (int j = i+1; j <= 100; j++) {
                suuriTesti(n, mista, mihin, x, i, j, 999999999, true);
            }
        }
    }
    
    @Test(timeout=1000)
    public void suuri4() {
        int n = 100;
        int q = 100000;
        int[] mista = new int[n*n/4];
        int[] mihin = new int[n*n/4];
        int c = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = i+1; j <= n; j++) {
                if (i%2 == j%2) continue;
                mista[c] = i;
                mihin[c] = j;
                c++;
            }
        }
        Lentoreitit x = new Lentoreitit(n, mista, mihin);
        for (int i = 0; i < q; i++) {
            int a = (int)(Math.random()*100)+1;
            int b = (int)(Math.random()*100)+1;
            int z = (int)(Math.random()*1000000000)+1;
            suuriTesti(n, mista, mihin, x, a, b, z, (a+b)%2 == z%2);
        }
    }
    
    
    
    
    
}
