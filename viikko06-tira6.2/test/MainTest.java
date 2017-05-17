import java.util.*;
import org.junit.Test;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;
import java.util.Arrays;

@Points("6.2")
public class MainTest {
    public void pieniTesti(Puu puu1, Puu puu2, boolean tulos) {
        boolean uusi = Main.samanlaiset(puu1, puu2);
        if (tulos) {
            assertTrue("Puut " + puu1 + " ja " + puu2 + " ovat samanlaiset, " +
                       "mutta metodisi palauttaa 'false'.", uusi == tulos);
        } else {
            assertTrue("Puut " + puu1 + " ja " + puu2 + " ovat erilaiset, " +
                       "mutta metodisi palauttaa 'true'.", uusi == tulos);
        }
    }

    public void suuriTesti(Puu puu1, Puu puu2, boolean tulos) {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        boolean uusi = Main.samanlaiset(puu1, puu2);
        assertTrue("Metodisi toimii väärin suurella syötteellä.", uusi == tulos);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    
    @Test(timeout=5000)
    public void esimerkki() {
        Puu puu1 = new Puu(1,
                           new Puu(3,
                                   new Puu(2, null, null),
                                   new Puu(1, null, null)),
                           new Puu(3,
                                   new Puu(3, null, null),
                                   new Puu(2, null, null)));

        Puu puu2 = new Puu(1,
                           new Puu(3,
                                   new Puu(2, null, null),
                                   new Puu(1, null, null)),
                           new Puu(3,
                                   new Puu(3, null, null),
                                   new Puu(2, null, null)));
        pieniTesti(puu1, puu2, true);
    }
    
    @Test(timeout=5000)
    public void pieni1() {
        Puu puu1 = new Puu(1, null, null);

        Puu puu2 = new Puu(1, null, null);

        pieniTesti(puu1, puu2, true);
    }    

    @Test(timeout=5000)
    public void pieni2() {
        Puu puu1 = new Puu(1, null, null);

        Puu puu2 = new Puu(2, null, null);

        pieniTesti(puu1, puu2, false);
    }    

    @Test(timeout=5000)
    public void pieni3() {
        Puu puu1 = new Puu(1, new Puu(2, null, null), null);

        Puu puu2 = new Puu(1, new Puu(2, null, null), null);

        pieniTesti(puu1, puu2, true);
    }    

    @Test(timeout=5000)
    public void pieni4() {
        Puu puu1 = new Puu(1, new Puu(2, null, null), null);

        Puu puu2 = new Puu(1, null, new Puu(2, null, null));

        pieniTesti(puu1, puu2, false);
    }    

    @Test(timeout=5000)
    public void pieni5() {
        Puu puu1 = new Puu(1,
                           new Puu(2, null, null),
                           new Puu(3, null, null));

        Puu puu2 = new Puu(1,
                           new Puu(3, null, null),
                           new Puu(2, null, null));

        pieniTesti(puu1, puu2, false);
    }
    
    
    
    @Test(timeout=5000)
    public void suuri1() {
        int n = 100000;
        Puu puu1, puu2, solmu;

        puu1 = new Puu(1, null, null);
        solmu = puu1;
        for (int i = 0; i < n-1; i++) {
            Puu uusi = new Puu(1, null, null);
            solmu.vasen = uusi;
            solmu = uusi;
        }

        puu2 = new Puu(1, null, null);
        solmu = puu2;
        for (int i = 0; i < n-1; i++) {
            Puu uusi = new Puu(1, null, null);
            solmu.vasen = uusi;
            solmu = uusi;
        }
                
        suuriTesti(puu1, puu2, true);
    }

    @Test(timeout=5000)
    public void suuri2() {
        int n = 100000;
        Puu puu1, puu2, solmu;

        puu1 = new Puu(1, null, null);
        solmu = puu1;
        for (int i = 0; i < n-1; i++) {
            Puu uusi = new Puu(1, null, null);
            solmu.vasen = uusi;
            solmu = uusi;
        }

        puu2 = new Puu(1, null, null);
        solmu = puu2;
        for (int i = 0; i < n-1; i++) {
            Puu uusi = new Puu(1, null, null);
            solmu.oikea = uusi;
            solmu = uusi;
        }
                
        suuriTesti(puu1, puu2, false);
    }

    
    @Test(timeout=5000)
    public void suuri4() {
        int n = 100000;
        Puu puu1, puu2;
        ArrayList<Puu> puut = new ArrayList<Puu>();
        int k;
        
        puut = new ArrayList<Puu>();
        puu1 = new Puu(1, null, null);
        puut.add(puu1);
        k = 0;
        for (int i = 0; i < n-1; i++) {
            Puu uusi = new Puu(1, null, null);
            if (puut.get(k).vasen == null) {
                puut.get(k).vasen = uusi;
            } else if (puut.get(k).oikea == null) {
                puut.get(k).oikea = uusi;                
                k++;
            }
            puut.add(uusi);
        }

        puut = new ArrayList<Puu>();
        puu2 = new Puu(1, null, null);
        puut.add(puu2);
        k = 0;
        for (int i = 0; i < n-1; i++) {
            Puu uusi = new Puu(1, null, null);
            if (puut.get(k).vasen == null) {
                puut.get(k).vasen = uusi;
            } else if (puut.get(k).oikea == null) {
                puut.get(k).oikea = uusi;                
                k++;
            }
            puut.add(uusi);
        }

        suuriTesti(puu1, puu2, true);
    }
    
    @Test(timeout=5000)
    public void suuri5() {
        int n = 100000;
        Puu puu1, puu2;
        ArrayList<Puu> puut = new ArrayList<Puu>();
        int k;
        
        puut = new ArrayList<Puu>();
        puu1 = new Puu(1, null, null);
        puut.add(puu1);
        k = 0;
        for (int i = 0; i < n-1; i++) {
            Puu uusi = new Puu(1, null, null);
            if (puut.get(k).vasen == null) {
                puut.get(k).vasen = uusi;
            } else if (puut.get(k).oikea == null) {
                puut.get(k).oikea = uusi;                
                k++;
            }
            puut.add(uusi);
        }

        puut = new ArrayList<Puu>();
        puu2 = new Puu(1, null, null);
        puut.add(puu2);
        k = 0;
        for (int i = 0; i < n-1; i++) {
            Puu uusi = new Puu(1, null, null);
            if (puut.get(k).vasen == null) {
                puut.get(k).vasen = uusi;
            } else if (puut.get(k).oikea == null) {
                puut.get(k).oikea = uusi;                
                k++;
            }
            if (i != 12345) puut.add(uusi);
        }

        suuriTesti(puu1, puu2, false);
    }
    
}
