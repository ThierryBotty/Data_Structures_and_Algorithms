import java.util.*;
import org.junit.Test;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;

@Points("2.2")
public class MainTest {
    public void testi(int n, String s, int vast) {
        int ulos = Main.laske(n, s);
        assertTrue("Erilaisia binäärijonoja, joiden pituus on " + n + " ja jotka eivät sisällä osajononaan merkkijonoa \"" +
                   s + "\" on "+ vast + " kappaletta, mutta metodisi palauttaa " + ulos + ".",
                   ulos == vast);
    }
    
    @Test(timeout=1000)
    public void esimerkit() {
        testi(2,"11", 3);
        testi(2,"1", 1);
        testi(3,"10", 4);
        testi(15,"10", 16);
    }  
    
    @Test(timeout=1000)
    public void pieni1(){
        testi(10, "100101", 944);
        testi(10, "000000", 976);
        testi(10, "101", 351);
        testi(10, "1111111111", 1023);
        testi(10, "0001000", 992);
        testi(10, "1111", 773);
    }
    
    @Test(timeout=1000)
    public void pieni2(){
        testi(10, "1", 1);
        testi(10, "0", 1);
        testi(10, "01", 11);
        testi(10, "111111111", 1021);
        testi(10, "", 0);
    }
    
    
    @Test(timeout=1000)
    public void suuri1(){
        testi(20, "10101010101010101010", 1024*1024-1);
    }
    
    @Test(timeout=1000)
    public void suuri2(){
        testi(20, "1001010", 937940);
    }
    
    @Test(timeout=1000)
    public void suuri3(){
        testi(20, "00", 17711);
    }
    
    @Test(timeout=1000)
    public void suuri4(){
        testi(20, "11", 17711);
    }
    
    @Test(timeout=1000)
    public void suuri5(){
        testi(20, "1010101101", 1037349);
    }
}

