import org.junit.Test;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;
import java.util.Arrays;

@Points("9.6")
public class MainTest {

    public void testaaOikeaaMuotoa(int n) {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        Main.Verkko v=Main.rakenna(n);
        
        assertTrue("Verkkosi ei ole oikeaa muotoa: taulukoissa 'mista' ja 'minne'"
                + " on eri määrä alkioita.", v.minne.length==v.mista.length);
        
        for(int i=0; i<v.mista.length; i++){
            assertTrue("Verkkosi ei ole oikeaa muotoa: sinulla on käytössä solmut 1..100000,\n"
                    + " mutta verkossasi esiintyy solmu "+v.mista[i]+".", 1<=v.mista[i] && v.mista[i]<=100000); 
            assertTrue("Verkkosi ei ole oikeaa muotoa: sinulla on käytössä solmut 1..100000,\n"
                    + " mutta verkossasi esiintyy solmu "+v.minne[i]+".", 1<=v.minne[i] && v.minne[i]<=100000);
            assertTrue("Verkkosi ei ole oikeaa muotoa: verkossa ei saa olla kaarta solmusta itseensä.", v.mista[i]!=v.minne[i]);
        }
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    @Test(timeout=5000)
    public void lokaaliTesti1() {
        testaaOikeaaMuotoa(1);
    }
    
    @Test(timeout=5000)
    public void lokaaliTesti2() {
        testaaOikeaaMuotoa(1337);
    }
        
    @Test(timeout=5000)
    public void lokaaliTesti3() {
        testaaOikeaaMuotoa(1000101001);
    }
    
    @Test(timeout=5000)
    public void lokaaliTesti4() {
        testaaOikeaaMuotoa(1000000000);
    }
    
    @Test(timeout=5000)
    public void lokaaliTesti5() {
        testaaOikeaaMuotoa(536870911);
    }
    
    @Test(timeout=5000)
    public void lokaaliTesti6() {
        testaaOikeaaMuotoa(0);
    }
    
    @Test(timeout=5000)
    public void lokaaliTesti7() {
        testaaOikeaaMuotoa(34156661);
    }
    
    @Test(timeout=5000)
    public void lokaaliTesti8() {
        testaaOikeaaMuotoa(77717771);
    }
    
    @Test(timeout=5000)
    public void lokaaliTesti9() {
        testaaOikeaaMuotoa(99110000);
    }
    
    @Test(timeout=5000)
    public void lokaaliTesti10() {
        testaaOikeaaMuotoa(536870912);
    }
}

