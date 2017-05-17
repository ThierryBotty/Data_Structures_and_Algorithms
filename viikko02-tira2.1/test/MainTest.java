import org.junit.Test;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import java.io.IOException;
import java.util.Arrays;

@Points("2.1")
public class MainTest {
    public void testi(String haku, int tulos) throws IOException {
        int uusi = Main.laske(haku);
        assertTrue("Kun hakusana on " + haku + ", tiedostoja tulisi löytyä " +
                   tulos + ", mutta metodisi palauttaa " + uusi + ".",
                   uusi == tulos);
    }

    @Test(timeout=1000)
    public void esimerkit() throws IOException {
        testi("txt", 11);
        testi("asd", 0);
        testi("rekursio", 1);

    }    
    
    @Test(timeout=1000)
    public void testi2() throws IOException {
        testi("hassu", 1);
        testi("level", 5);
        testi("sivuhaara", 1);
        testi("a", 6);
        testi("t", 11);
    }    
    
    
}

