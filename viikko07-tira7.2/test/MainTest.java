import org.junit.Test;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@Points("7.2")
public class MainTest {
    public void testaa(Kokoelma k, int tulos) {
        int ulos = k.suurinLehti();
        assertTrue("Metodisi toimii väärin: se palauttaa "+ulos+" vaikka odotettu"
                + " vastaus olisi "+ tulos +".",
                   tulos == ulos);
    }
    
    @Test(timeout=1000)
    public void esimerkki() {
        Kokoelma k = new Kokoelma();
        testaa(k, -1);
        k.lisaaLehti(1);
        k.lisaaLehti(2);
        testaa(k, 2);
        k.poistaLehti(2);
        testaa(k, 1);
        k.lisaaLehti(3);
        k.lisaaLehti(3);
        testaa(k, 3);
        k.poistaLehti(3);
        testaa(k, 3); 
        k.poistaLehti(3);
        testaa(k, 1); 
        k.poistaLehti(1);
        testaa(k, -1);
    }
    
    @Test(timeout=1000)
    public void pieni1() {
        Kokoelma k = new Kokoelma();
        k.lisaaLehti(5);
        testaa(k,5);
        k.lisaaLehti(4);
        testaa(k,5);
        k.lisaaLehti(3);
        testaa(k,5);
        k.lisaaLehti(2);
        testaa(k,5);
        k.lisaaLehti(1);
        testaa(k,5);
        
        k.poistaLehti(4);
        testaa(k,5);
        k.poistaLehti(3);
        testaa(k,5);
        k.poistaLehti(2);
        testaa(k,5);
        k.poistaLehti(5);
        testaa(k,1);
        k.poistaLehti(1);
        testaa(k, -1); 
    }
    
    @Test(timeout=1000)
    public void pieni2() {
        Kokoelma k = new Kokoelma();
        k.lisaaLehti(1);
        testaa(k,1);
        k.lisaaLehti(2);
        testaa(k,2);
        k.lisaaLehti(3);
        testaa(k,3);
        k.lisaaLehti(3);
        testaa(k,3);
        k.lisaaLehti(3);
        testaa(k,3);
        
        k.poistaLehti(3);
        testaa(k,3);
        k.poistaLehti(3);
        testaa(k,3);
        k.poistaLehti(3);
        testaa(k,2);
        k.poistaLehti(2);
        testaa(k,1);
        k.poistaLehti(1);
        testaa(k,-1);
    }
    
    @Test(timeout=5000)
    public void suuri1() {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        Kokoelma k = new Kokoelma();
        for (int i = 1; i <= 100000; i++) {
            k.lisaaLehti(i);
            testaa(k, i);
        }
        
        for (int i = 100000; i > 1; i--) {
            k.poistaLehti(i);
            testaa(k, i-1);
        }
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    @Test(timeout=5000)
    public void suuri2() {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        ArrayList<Integer> lisaykset = new ArrayList();
        ArrayList<Integer> poistot = new ArrayList();
        
        for(int i=0; i<100000; i++)
            lisaykset.add(i%1000+1);
        for(int i=0; i<100000; i++)
            poistot.add(i%1000+1);
        
        Collections.shuffle(lisaykset);
        Collections.shuffle(poistot);
        
        Kokoelma k = new Kokoelma();
        for(int i:lisaykset)
            k.lisaaLehti(i);
        testaa(k, 1000);
        for(int i:poistot)
            k.poistaLehti(i);
        testaa(k, -1);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }    
    
    @Test(timeout=5000)
    public void suuri3() {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        ArrayList<Integer> lisaykset = new ArrayList();
        ArrayList<Integer> poistot = new ArrayList();
        
        for(int i=0; i<100000; i++)
            lisaykset.add(i%1000+1);
        for(int i=0; i<100000; i++)
            poistot.add(i%1000+2);
        
        Collections.shuffle(lisaykset);
        Collections.shuffle(poistot);
        
        Kokoelma k = new Kokoelma();
        for(int i:lisaykset)
            k.lisaaLehti(i);
        testaa(k, 1000);
        for(int i:poistot)
            k.poistaLehti(i);
        testaa(k, 1);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }    
    
    @Test(timeout=5000)
    public void suuri4() {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        Kokoelma k = new Kokoelma();
        for (int i = 1; i <= 100000; i++) {
            k.lisaaLehti(i);
            testaa(k, i);
        }
        
        for (int i = 100000; i > 2; i--) {
            k.poistaLehti(i-1);
            testaa(k, 100000);
        }
        k.poistaLehti(100000);
        testaa(k, 1);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    @Test(timeout=5000)
    public void suuri5() {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        Kokoelma k = new Kokoelma();
        
        for(int i=1; i<=500; i++)
            for(int j=1; j<=i; j++){
                k.lisaaLehti(j);
                testaa(k, Math.max(i-1, j));
            }
        
        for(int asd=0; asd<500-1; asd++){
            for(int i=1; i<=500; i++)
                k.poistaLehti(i);
            testaa(k,500-1-asd);
        }
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
}
