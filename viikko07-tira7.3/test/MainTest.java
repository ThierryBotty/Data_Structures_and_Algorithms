import org.junit.Test;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;
import java.util.Arrays;

@Points("7.3")
public class MainTest {
    public void pieniTesti(Kokoelma k, int[] lehdet, int tulos) {
        int uusi = k.pieninPuuttuva();
        String sisalto = Arrays.toString(lehdet);
        assertTrue("Lehtien " + sisalto + " lisäyksen jälkeen pienin puuttuva " +
                   "tulisi olla " + tulos + ", mutta metodisi antaa " + uusi + ".",
                   tulos == uusi);
    }
    
    public void suuriTesti(Kokoelma k, int tulos) {
        int uusi = k.pieninPuuttuva();
        assertTrue("Metodisi toimii väärin suurella syötteellä.",
                   tulos == uusi);
    }    
    
    @Test(timeout=1000)
    public void esimerkki() {
        Kokoelma k = new Kokoelma();
        pieniTesti(k, new int[] {}, 1);
        k.lisaaLehti(3);
        pieniTesti(k, new int[] {3}, 1);
        k.lisaaLehti(2);
        pieniTesti(k, new int[] {3, 2}, 1);
        k.lisaaLehti(1);
        pieniTesti(k, new int[] {3, 2, 1}, 4);
    }
    
    @Test(timeout=1000)
    public void pieni1() {
        Kokoelma k = new Kokoelma();
        k.lisaaLehti(1);
        pieniTesti(k, new int[] {1}, 2);
        k.lisaaLehti(3);
        pieniTesti(k, new int[] {1, 3}, 2);
        k.lisaaLehti(5);
        pieniTesti(k, new int[] {1, 3, 5}, 2);
        k.lisaaLehti(2);
        pieniTesti(k, new int[] {1, 3, 5, 2}, 4);
        k.lisaaLehti(4);
        pieniTesti(k, new int[] {1, 3, 5, 2, 4}, 6);
        k.lisaaLehti(6);
        pieniTesti(k, new int[] {1, 3, 5, 2, 4, 6}, 7);
    }
    
    @Test(timeout=1000)
    public void pieni2() {
        Kokoelma k = new Kokoelma();
        k.lisaaLehti(5);
        pieniTesti(k, new int[] {5}, 1);
        k.lisaaLehti(5);
        pieniTesti(k, new int[] {5, 5}, 1);
        k.lisaaLehti(5);
        pieniTesti(k, new int[] {5, 5, 5}, 1);
        k.lisaaLehti(5);
        pieniTesti(k, new int[] {5, 5, 5, 5}, 1);
        k.lisaaLehti(5);
        pieniTesti(k, new int[] {5, 5, 5, 5, 5}, 1);
    }
    
    @Test(timeout=1000)
    public void pieni3() {
        Kokoelma k = new Kokoelma();
        k.lisaaLehti(999999999);
        pieniTesti(k, new int[] {999999999}, 1);
        k.lisaaLehti(999999998);
        pieniTesti(k, new int[] {999999999, 999999998}, 1);
        k.lisaaLehti(999999997);
        pieniTesti(k, new int[] {999999999, 999999998, 999999997}, 1);
        k.lisaaLehti(1);
        pieniTesti(k, new int[] {999999999, 999999998, 999999997, 1}, 2);
    }
    
    @Test(timeout=5000)
    public void suuri1() {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        Kokoelma k = new Kokoelma();
        for (int i = 1; i <= 50000; i++) {
            k.lisaaLehti(1);
            suuriTesti(k, 2);
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
            suuriTesti(k, i+1);
        }
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }        
    
    @Test(timeout=5000)
    public void suuri3() {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        Kokoelma k = new Kokoelma();
        for (int i = 1; i <= 33333; i++) {
            k.lisaaLehti(i);
            k.lisaaLehti(1000000000-i);
            suuriTesti(k, i+1);
        }
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }       
    
    @Test(timeout=5000)
    public void suuri4() {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        Kokoelma k = new Kokoelma();
        for (int i = 1; i <= 50000; i++) {
            k.lisaaLehti(1000000000-i);
            suuriTesti(k, 1);
        }
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }          
    
    
}
