import org.junit.Test;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@Points("5.2")
public class MainTest {
    public void testaa(Main.Reppu r, int x, boolean tulos) {
        boolean ulos = r.sisaltaako(x);
        assertTrue("Luokkasi toimii väärin. Kun kutsutaan 'sisaltaako' -metodia, tulisi palauttaa '"+tulos+"', mutta palautusarvo on '"+ulos+"'.",
                   tulos == ulos);
    }

    @Test(timeout=5000)
    public void esimerkit() {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        Main.Reppu r = new Main.Reppu();
        testaa(r, 1, false); 
        r.lisaa(1);
        testaa(r, 1, true);
        testaa(r, 2, false);
        r.heitaPois(1);
        testaa(r, 1, false);
        r.lisaa(1000);
        r.lisaa(1000);
        testaa(r, 1000, true);
        r.heitaPois(1000);
        testaa(r, 1000, true); 
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    @Test(timeout=5000)
    public void pieni1() {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        Main.Reppu r = new Main.Reppu();
        r.lisaa(1);
        testaa(r,1,true);
        r.heitaPois(1);
        testaa(r,1, false);
        r.heitaPois(1);
        testaa(r,1, false);
        r.lisaa(1);
        testaa(r,1, true);
        r.lisaa(1);
        testaa(r,1, true);
        r.lisaa(1);
        testaa(r,1, true);
        r.heitaPois(1);
        testaa(r,1, true);
        r.heitaPois(1);
        testaa(r,1, true);
        r.heitaPois(1);
        testaa(r,1, false);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    @Test(timeout=5000)
    public void pieni2() {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        Main.Reppu r = new Main.Reppu();
        r.lisaa(1);
        r.lisaa(3);
        r.lisaa(5);
        r.lisaa(7);
        
        testaa(r, 1, true);
        testaa(r, 2, false);
        testaa(r, 3, true);
        testaa(r, 4, false);
        testaa(r, 5, true);
        testaa(r, 6, false);
        testaa(r, 7, true);
        testaa(r, 8, false);
        testaa(r, 9, false);
        
        r.lisaa(8);
        r.heitaPois(7);
        
        testaa(r, 1, true);
        testaa(r, 2, false);
        testaa(r, 3, true);
        testaa(r, 4, false);
        testaa(r, 5, true);
        testaa(r, 6, false);
        testaa(r, 7, false);
        testaa(r, 8, true);
        testaa(r, 9, false);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    @Test(timeout=5000)
    public void suuri1() {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        ArrayList<Integer> lisaykset = new ArrayList();
        ArrayList<Integer> poistot = new ArrayList();
        
        for(int i = 0; i<100000; i++)
            lisaykset.add((i%1000)*1000);
        for(int i = 0; i<100000; i++)
            poistot.add((i%1000)*1000);
        
        Collections.shuffle(lisaykset);
        Collections.shuffle(poistot);
        
        Main.Reppu r = new Main.Reppu();
        
        for(int i:lisaykset)
            r.lisaa(i);
        for(int i=0; i<100000; i++)
            testaa(r, i, (i%1000 == 0));
        for(int i:poistot)
            r.heitaPois(i);
        for(int i=0; i<100000; i++)
            testaa(r, i, false);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    @Test(timeout=5000)
    public void suuri2() {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        ArrayList<Integer> lisaykset = new ArrayList();
        ArrayList<Integer> poistot = new ArrayList();
        
        for(int i = 0; i<100000; i++)
            lisaykset.add(i);
        for(int i = 0; i<100000; i++)
            poistot.add(i%50000);
        
        Collections.shuffle(lisaykset);
        Collections.shuffle(poistot);
        
        Main.Reppu r = new Main.Reppu();
        
        for(int i:lisaykset)
            r.lisaa(i);
        for(int i=0; i<100000; i++)
            testaa(r, i, true);
        for(int i:poistot)
            r.heitaPois(i);
        for(int i=0; i<100000; i++)
            testaa(r, i, i>=50000);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    @Test(timeout=5000)
    public void suuri3() {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        ArrayList<Integer> lisaykset = new ArrayList();
        ArrayList<Integer> poistot = new ArrayList();
        
        for(int i = 0; i<100000; i++)
            lisaykset.add(2*i);
        for(int i = 0; i<100000; i++)
            poistot.add(3*i);
        
        Collections.shuffle(lisaykset);
        Collections.shuffle(poistot);
        
        Main.Reppu r = new Main.Reppu();
        
        for(int i:lisaykset)
            r.lisaa(i);
        for(int i:poistot)
            r.heitaPois(i);
        for(int i=0; i<100000; i++)
            testaa(r, i, (i%2==0 && i%3!=0));
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    @Test(timeout=5000)
    public void suuri4() {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        ArrayList<Integer> lisaykset = new ArrayList();
        ArrayList<Integer> poistot = new ArrayList();
        
        for(int i = 0; i<100000; i++)
            lisaykset.add(i%1000);
        for(int i = 0; i<100000-500; i++)
            poistot.add(i%1000);
        
        Collections.shuffle(lisaykset);
        Collections.shuffle(poistot);
        
        Main.Reppu r = new Main.Reppu();
        
        for(int i:lisaykset)
            r.lisaa(i);
        for(int i:poistot)
            r.heitaPois(i);
        for(int i=0; i<1000; i++)
            testaa(r, i, i>=500);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
        

}

