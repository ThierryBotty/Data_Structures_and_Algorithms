import org.junit.Test;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;
import java.util.Arrays;

@Points("7.1")
public class MainTest {
    public void pieniTesti(Kokoelma k, int[] lehdet, int toive, int tulos) {
        int uusi = k.valitseLehti(toive);
        String sisalto = Arrays.toString(lehdet);
        assertTrue("Lehtien " + sisalto + " lisäyksen jälkeen lehteä " + toive +
                   "lähin lehti on " + tulos + ", mutta metodisi antaa " + uusi + ".",
                   tulos == uusi);
    }
    
    public void suuriTesti(Kokoelma k, int toive, int tulos) {
        int uusi = k.valitseLehti(toive);
        assertTrue("Metodisi toimii väärin suurella syötteellä.",
                   tulos == uusi);
    }    
    
    @Test(timeout=1000)
    public void esimerkki() {
        Kokoelma k = new Kokoelma();
        k.lisaaLehti(3);
        pieniTesti(k, new int[] {3}, 4, 3);
        pieniTesti(k, new int[] {3}, 8, 3);
        k.lisaaLehti(9);
        pieniTesti(k, new int[] {3, 9}, 4, 3);
        pieniTesti(k, new int[] {3, 9}, 8, 9);
    }
    
    @Test(timeout=1000)
    public void pieni1() {
        Kokoelma k = new Kokoelma();
        k.lisaaLehti(1);
        pieniTesti(k, new int[] {1}, 1, 1);
        pieniTesti(k, new int[] {1}, 2, 1);
        pieniTesti(k, new int[] {1}, 3, 1);
        pieniTesti(k, new int[] {1}, 4, 1);
        pieniTesti(k, new int[] {1}, 5, 1);
        k.lisaaLehti(5);
        pieniTesti(k, new int[] {1, 5}, 1, 1);
        pieniTesti(k, new int[] {1, 5}, 2, 1);
        pieniTesti(k, new int[] {1, 5}, 3, 1);
        pieniTesti(k, new int[] {1, 5}, 4, 5);
        pieniTesti(k, new int[] {1, 5}, 5, 5);
    }
    
    @Test(timeout=1000)
    public void pieni2() {
        Kokoelma k = new Kokoelma();
        k.lisaaLehti(999999999);
        pieniTesti(k, new int[] {999999999}, 1, 999999999);
        pieniTesti(k, new int[] {999999999}, 2, 999999999);
        pieniTesti(k, new int[] {999999999}, 3, 999999999);
        pieniTesti(k, new int[] {999999999}, 4, 999999999);
        pieniTesti(k, new int[] {999999999}, 5, 999999999);
        k.lisaaLehti(999999998);
        pieniTesti(k, new int[] {999999999, 999999998}, 1, 999999998);
        pieniTesti(k, new int[] {999999999, 999999998}, 2, 999999998);
        pieniTesti(k, new int[] {999999999, 999999998}, 3, 999999998);
        pieniTesti(k, new int[] {999999999, 999999998}, 4, 999999998);
        pieniTesti(k, new int[] {999999999, 999999998}, 5, 999999998);
    }
    
    @Test(timeout=1000)
    public void pieni3() {
        Kokoelma k = new Kokoelma();
        k.lisaaLehti(4);
        k.lisaaLehti(9);
        k.lisaaLehti(2);
        k.lisaaLehti(8);
        pieniTesti(k, new int[] {4, 9, 2, 8}, 1, 2);
        pieniTesti(k, new int[] {4, 9, 2, 8}, 2, 2);
        pieniTesti(k, new int[] {4, 9, 2, 8}, 3, 2);
        pieniTesti(k, new int[] {4, 9, 2, 8}, 4, 4);
        pieniTesti(k, new int[] {4, 9, 2, 8}, 5, 4);
        pieniTesti(k, new int[] {4, 9, 2, 8}, 6, 4);
        pieniTesti(k, new int[] {4, 9, 2, 8}, 7, 8);
        pieniTesti(k, new int[] {4, 9, 2, 8}, 8, 8);
        pieniTesti(k, new int[] {4, 9, 2, 8}, 9, 9);
    }
    
    @Test(timeout=5000)
    public void suuri1() {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        Kokoelma k = new Kokoelma();
        for (int i = 1; i <= 50000; i++) {
            k.lisaaLehti(i);
            suuriTesti(k, i, i);
        }
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }        

    @Test(timeout=5000)
    public void suuri2() {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        Kokoelma k = new Kokoelma();
        for (int i = 1; i <= 50000; i++) {
            k.lisaaLehti(i);
            suuriTesti(k, 1, 1);
        }
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }        

    
    @Test(timeout=5000)
    public void suuri3() {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        Kokoelma k = new Kokoelma();
        for (int i = 1; i <= 50000; i++) {
            k.lisaaLehti(i);
            suuriTesti(k, i+1, i);
        }
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    @Test(timeout=5000)
    public void suuri4() {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        Kokoelma k = new Kokoelma();
        for (int i = 1; i <= 25000; i++) {
            k.lisaaLehti(1000000000-i);
            suuriTesti(k, 500000000, 1000000000-i);
            k.lisaaLehti(i);
            suuriTesti(k, 500000000, i);
        }
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }           
    
    
}
