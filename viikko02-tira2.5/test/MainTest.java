
import org.junit.Test;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import java.lang.reflect.Method;

@Points("2.5")
public class MainTest {

    public void testi(int n, int m, int k, int vast) {
        long ulos = Main.pieninMaara(n, m, k);
        assertTrue("Laatta, jonka mitat ovat " + n + "×" + m + ", voidaan jakaa osiin, joiden pinta-ala on korkeintaan " + k
                + " käyttäen vähintään " + vast + " leikkauksella, \nmutta algoritmisi antaa optimaaliseksi leikkausten määräksi " + ulos + ".", ulos == vast);
    }

    @Test(timeout = 1000)
    public void esimerkit() {
        testi(2,2,1,3);
        testi(3,3,4,2);
        testi(4,4,4,3);
        testi(3,5,2,7);
    }
    
    @Test(timeout = 1000)
    public void pienet() {
        testi(10,10,50,1);
        testi(8,8,16,3);
        testi(1,1,1,0);
        testi(4,7,11,2);
    }

    @Test(timeout = 1000)
    public void suuri1() {
        testi(1,50,5,9);
    }
    
    @Test(timeout = 1000)
    public void suuri2() {
        testi(50,50,17,147);
    }
    
    @Test(timeout = 1000)
    public void suuri3() {
        testi(50,50,1,2499);
    }
    
    @Test(timeout = 1000)
    public void suuri4() {
        testi(50,50,5,499);
    }
    
    @Test(timeout = 1000)
    public void suuri5() {
        testi(50,50,33,76);
    }
    
    @Test(timeout = 1000)
    public void suuri6() {
        testi(50,50,100,24);
    }
}

