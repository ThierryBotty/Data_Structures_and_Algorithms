import org.junit.Test;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import java.util.Arrays;

@Points("BONUS2_2")
public class MainTest {
    public void testi(int n, long tulos) {
        long uusi = Main.etsiTulo(n);
        assertTrue(n + ". tulo tulisi olla " + tulos +
                   ", mutta metodisi antaa " + uusi + ".", tulos == uusi);
    }
    
    @Test(timeout=1000)
    public void esimerkit() {
        testi(2, 3);
        testi(20, 108);
        testi(49, 2304);
        testi(200, 15925248);
    }
    
    @Test(timeout=1000)
    public void pienet() {
        testi(1, 2);
        testi(2, 3);
        testi(3, 4);
        testi(4, 6);
        testi(5, 8);
        testi(6, 9);
        testi(7, 12);
        testi(8, 16);
        testi(9, 18);
        testi(10, 24);

        testi(10, 24);
        testi(20, 108);
        testi(30, 384);
        testi(40, 1024);
        testi(50, 2592);
    }
    
    @Test(timeout=1000)
    public void suuret() {
        testi(500, 396718580736L);
        testi(600, 5566277615616L);
        testi(700, 63403380965376L);
        testi(800, 601157982486528L);
        testi(900, 4941387170271576L);
        testi(1000, 36520347436056576L);
    }
    
}
