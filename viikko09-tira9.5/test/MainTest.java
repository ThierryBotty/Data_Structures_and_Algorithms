import org.junit.Test;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;
import java.util.ArrayList;
import java.util.Arrays;

@Points("9.5")
public class MainTest {

    private String kaarilista(int[] mista, int[] minne) {
        String tulos = "";
        for (int i = 0; i < mista.length; i++) {
            if (tulos.equals("")) tulos = mista[i] + "-" + minne[i];
            else tulos += ", "  + mista[i] + "-" + minne[i];
        }
        return "[" + tulos + "]";
    } 
    
    public void pieniTesti(int n, int[] mista, int[] minne, long tulos) {
        String sisalto = kaarilista(mista, minne);
        long uusi = Main.reittimaara(n, mista, minne);
        assertTrue("Kun kaupunkeja on " + n + " ja tiet ovat " + sisalto +
                   ", lyhimpiä reittejä on " + tulos +
                   ", mutta metodisi antaa " + uusi + ".",
                   tulos == uusi);
    }
    
    public void suuriTesti(int n, int[] mista, int[] minne, long tulos) {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        long uusi = Main.reittimaara(n, mista, minne);
        assertTrue("Metodisi toimii väärin suurella syötteellä. Se palauttaa "
                + uusi + " vaikka oikea vastaus on " + tulos +".",
                   tulos == uusi);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
        
    @Test(timeout=5000)
    public void esimerkit() {
        pieniTesti(2, new int[] {1}, new int[] {2}, 1);
        pieniTesti(5, new int[] {1,1,2,3,4}, new int[] {2,3,4,4,5}, 2);
        pieniTesti(5, new int[] {1, 1, 1, 2, 3, 4}, new int[] {2, 3, 4, 5, 5, 5}, 3);
        pieniTesti(7, new int[] {1,2,3,1,1,4,5,6}, new int[] {2,3,7,4,5,6,6,7}, 3);      
    }
    
    @Test(timeout=5000)
    public void pienet() {
        pieniTesti(2, new int[] {}, new int[] {}, 0);
        pieniTesti(2, new int[] {1}, new int[] {2}, 1);
        
        pieniTesti(3, new int[] {1, 2}, new int[] {2, 3}, 1);
        
        pieniTesti(4, new int[] {1, 1, 2, 3}, new int[] {2, 3, 4, 4}, 2);
        
        pieniTesti(6, new int[] {1, 2, 3, 4, 5}, new int[] {2, 3, 4, 5, 6}, 1);
        pieniTesti(4, new int[] {1, 2, 3}, new int[] {2, 3, 4}, 1);
        
        pieniTesti(6, new int[] {1, 2, 2, 3, 4, 5}, new int[] {2, 3, 4, 5, 5, 6}, 2);
        pieniTesti(9, new int[] {1, 2, 2, 3, 4, 5, 5, 6, 7, 8}, new int[] {2, 3, 4, 5, 5, 6, 7, 8, 8, 9}, 4);
    }
    
    @Test(timeout=5000)
    public void suuri1() {
        int n = 100000;
        int[] mista = new int[n-1];
        int[] minne = new int[n-1];
        for (int i = 0; i < n-1; i++) {
            mista[i] = i+1;
            minne[i] = i+2;
        }
        suuriTesti(n, mista, minne, 1);
    }
    
    @Test(timeout=5000)
    public void suuri2() {
        int n = 100000;
        int[] mista = new int[n-1];
        int[] minne = new int[n-1];
        for (int i = 0; i < n-1; i++) {
            mista[i] = i+1;
            minne[i] = i+2;
        }
        suuriTesti(n, mista, minne, 1);
    }

    public void hassuTesti(int n) {
        int[] mista = new int[(n-1)/3*4];
        int[] minne = new int[(n-1)/3*4];
        int c = 0;
        long maara = 1;
        for (int i = 0; i < (n-1)/3*4; i += 4) {
            mista[i] = i+1-c;
            minne[i] = i+2-c;
            mista[i+1] = i+1-c;
            minne[i+1] = i+3-c;
            mista[i+2] = i+2-c;
            minne[i+2] = i+4-c;
            mista[i+3] = i+3-c;
            minne[i+3] = i+4-c;
            c++;
            maara *= 2;
        }
        suuriTesti(n, mista, minne, maara);        
    }
    
    @Test(timeout=5000)
    public void suuri3() {
        hassuTesti(40);
    }

    @Test(timeout=5000)
    public void suuri4() {
        hassuTesti(100);
    }
    
    @Test(timeout=5000)
    public void suuri5() {
        hassuTesti(160);
    }
    
    int[] lol(ArrayList<Integer> asd){
        int[] ret=new int[asd.size()];
        for(int i=0; i<asd.size(); i++)
            ret[i]=asd.get(i);
        return ret;
    }
    
    @Test(timeout=5000)
    public void suuri6() {
        int n = 100000;
        ArrayList<Integer> mista = new ArrayList();
        ArrayList<Integer> minne = new ArrayList();

        for (int i = 1; i <= (1<<15); i++) {
            mista.add(i);
            minne.add(2*i);
            mista.add(i);
            minne.add(2*i+1);
        }
        
        for (int i = (1<<15)+1; i <= (1<<16); i++) {
            mista.add(i);
            minne.add(100000);
        }

        suuriTesti(n, lol(mista), lol(minne), 32767);
    }
    
}

