import java.util.*;

import org.junit.Test;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;
import java.util.Arrays;

@Points("6.4")
public class MainTest {
    public void pieniTesti(Puu puu, int tulos) {
        int uusi = Main.pisinPolku(puu);
        assertTrue("Puussa " + puu +  " pisin polku on " +
                   tulos + ", mutta metodisi antaa " + uusi + ".",
                   tulos == uusi);
    }

    public void suuriTesti(Puu puu, int tulos) {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        int uusi = Main.pisinPolku(puu);
        assertTrue("Metodisi toimii väärin suurella syötteellä.",
                   tulos == uusi);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    
    @Test(timeout=1000)
    public void esimerkki() {
        Puu puu = new Puu(1,
                          new Puu(3,
                                  new Puu(2, null, null),
                                  null),
                          new Puu(3,
                                  new Puu(3, null, null),
                                  new Puu(2, null, null)));

        pieniTesti(puu, 4);
    }
    
    @Test(timeout=1000)
    public void pieni1() {
        Puu puu = new Puu(1, null, null);

        pieniTesti(puu, 0);
    }    
    
    @Test(timeout=1000)
    public void pieni2() {
        Puu puu = new Puu(1,
                          new Puu(2, null, null),
                          new Puu(3, null, null));

        pieniTesti(puu, 2);
    } 
    
    @Test(timeout=1000)
    public void pieni3() {
        Puu puu = new Puu(3,
                          new Puu(2,
                                  new Puu(1, null, null),
                                  new Puu(1, null, null)),
                          new Puu(2,
                                  new Puu(1, null, null),
                                  new Puu(1, null, null)));

        pieniTesti(puu, 4);
    }
    
    @Test(timeout=1000)
    public void pieni4() {
        Puu puu = new Puu(4,
                          new Puu(3,
                                  new Puu(2,
                                          new Puu(1, null, null),
                                          null),
                                  null),
                          new Puu(3,
                                  null,
                                  new Puu(2,
                                          null,
                                          new Puu(1, null, null))));

        pieniTesti(puu, 6);
    }
    
    @Test(timeout=1000)
    public void pieni5() {
        Puu osa = new Puu(4,
                          new Puu(3,
                                  new Puu(2,
                                          new Puu(1, null, null),
                                          null),
                                  null),
                          new Puu(3,
                                  null,
                                  new Puu(2,
                                          null,
                                          new Puu(1, null, null))));
        
        Puu puu = new Puu(5, null, osa);

        pieniTesti(puu, 6);
    }    
    
    @Test(timeout=5000)
    public void suuri1() {
        int n = 100000;
        Puu puu = new Puu(1, null, null);
        Puu solmu = puu;
        for (int i = 0; i < n-1; i++) {
            Puu uusi = new Puu(1, null, null);
            solmu.vasen = uusi;
            solmu = uusi;
        }
        suuriTesti(puu, n-1);
    }

    @Test(timeout=5000)
    public void suuri2() {
        int n = 100000;
        Puu puu = new Puu(1, null, null);
        Puu solmu = puu;
        for (int i = 0; i < n-1; i++) {
            Puu uusi = new Puu(1, null, null);
            solmu.oikea = uusi;
            solmu = uusi;
        }
        suuriTesti(puu, n-1);
    }

    @Test(timeout=5000)
    public void suuri3() {
        int n = 100000;
        ArrayList<Puu> puut = new ArrayList<Puu>();
        Puu puu = new Puu(1, null, null);
        puut.add(puu);
        int k = 0;
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
        suuriTesti(puu, 32);
    }
    
    @Test(timeout=5000)
    public void suuri4() {
        int n = 100000;
        ArrayList<Puu> puut = new ArrayList<Puu>();
        Puu puu = new Puu(1, null, null);
        puut.add(puu);
        long x = 12345;
        long a = 798732191;
        long b = 921339221;
        for (int i = 0; i < n-1; i++) {
            Puu uusi = new Puu(1, null, null);
            while (true) {
                x = (x*a)%b;
                int k = (int)(x%puut.size());
                if (x%2 == 0 && puut.get(k).vasen == null) {
                    puut.get(k).vasen = uusi;
                } else if (x%2 == 1 && puut.get(k).oikea == null) {
                    puut.get(k).oikea = uusi;             
                } else {
                    continue;
                }
                break;
            }
            puut.add(uusi);
        }
        suuriTesti(puu, 86);
    }    
}
