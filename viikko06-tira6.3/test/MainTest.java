import java.util.*;
import org.junit.Test;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;
import java.util.Arrays;

@Points("6.3")
public class MainTest {
    public void pieniTesti(Puu puu, boolean tulos) {
        boolean ulos = Main.onkoAVL(puu);
        if (tulos) {
            assertTrue("Puu " + puu + " toteuttaa AVL-ehdon, " +
                       "mutta metodisi palauttaa 'false'", ulos == tulos);
        } else {
            assertTrue("Puu " + puu + " ei toteuta AVL-ehtoa, " +
                       "mutta metodisi palauttaa 'true'", ulos == tulos);            
        }
    }

    public void suuriTesti(Puu puu, boolean tulos) {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        boolean ulos = Main.onkoAVL(puu);
        assertTrue("Metodisi toimii väärin suurella syötteellä. Se palauttaa '"+ulos+"' kun pitäisi palauttaa '"+tulos+"'.", ulos == tulos);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    
    @Test(timeout=1000)
    public void esimerkki() {
        Puu puu1 = new Puu(0,
                          new Puu(0,
                                  new Puu(0, null, null),
                                  new Puu(0, null, null)),
                          new Puu(0,
                                  new Puu(0, null, null),
                                  new Puu(0, 
                                          new Puu(0, null, null),
                                          new Puu(0, null, null))));
        
        pieniTesti(puu1, true);
    
        Puu puu2 = new Puu(0,
                          new Puu(0,
                                  new Puu(0, null, null),
                                  null),
                          new Puu(0,
                                  new Puu(0, 
                                          null,
                                          new Puu(0, null, null)),
                                  new Puu(0, null, null)));
        
        pieniTesti(puu2, true);
        
        Puu puu3 = new Puu(0,
                          new Puu(0,
                                  new Puu(0, 
                                          new Puu(0, null, null),
                                          new Puu(0, null, null)),
                                  new Puu(0, 
                                          new Puu(0, null, null), 
                                          new Puu(0, null, null))),
                          new Puu(0,
                                  new Puu(0, null, null),
                                  new Puu(0, null, null)));
        
        pieniTesti(puu3, true);
        
        Puu puu4 = new Puu(0,
                          new Puu(0,
                                  new Puu(0, null, null),
                                  new Puu(0, null, null)),
                          new Puu(0,
                                  new Puu(0, null, null),
                                  new Puu(0, 
                                          new Puu(0, null, new Puu(0, null, null)), 
                                          new Puu(0, null, null))));
        
        pieniTesti(puu4, false);

        Puu puu5 = new Puu(0,
                          new Puu(0,
                                  new Puu(0, 
                                          new Puu(0, null, null), null),
                                  null),
                          new Puu(0,
                                  new Puu(0, null, new Puu(0, null, null)),
                                  new Puu(0, null, null)));
        
        pieniTesti(puu5, false);

        Puu puu6 = new Puu(0,
                          new Puu(0,
                                  new Puu(0, 
                                          new Puu(0, null, null), 
                                          new Puu(0, null, null)),
                                  new Puu(0, 
                                          new Puu(0, null, null), 
                                          new Puu(0, null, null))),
                          new Puu(0,
                                  null,
                                  new Puu(0, 
                                          new Puu(0, null, null), 
                                          new Puu(0, null, null))));
        
        pieniTesti(puu6, false);
    }
    
    @Test(timeout=1000)
    public void pieni1() {
        Puu puu = new Puu(1, null, null);

        pieniTesti(puu, true);
    } 
    
    @Test(timeout=1000)
    public void pieni2() {
        Puu puu = new Puu(1, new Puu(1, null, null), null);

        pieniTesti(puu, true);
    } 
    
    @Test(timeout=1000)
    public void pieni3() {
        Puu puu = new Puu(1, null, new Puu(1, null, null));

        pieniTesti(puu, true);
    } 
    
    @Test(timeout=1000)
    public void pieni4() {
        Puu puu = new Puu(1, new Puu(1, null, new Puu(1, null, null)), null);

        pieniTesti(puu, false);
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
        suuriTesti(puu, false);
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
        suuriTesti(puu, false);
    }
    
    @Test(timeout=5000)
    public void suuri3() {
        int n = (1<<16)-1;
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
        suuriTesti(puu, true);
    }
    
    @Test(timeout=5000)
    public void suuri4() {
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
        suuriTesti(puu, true);
    }
    
    static class Random {
        private long val;
        private long mul=16807;
        private long mod=((long)1<<31)-1;
        private long get(){
            val=(val*mul)%mod;
            return val;
        }
        public int getInt(int a, int b){
            return a+Math.abs((int)get()%(b-a+1));
        }
        public int getIntW(int a, int b, int w){
            int r=getInt(a, b);
            for (int i=1;i<=w;i++){
                r=Math.max(r, getInt(a, b));
            }
            for (int i=-1;i>=w;i--){
                r=Math.min(r, getInt(a, b));
            }
            return r;
        }
        public Random(int seed){
            val=seed;
        }
    }
    
    @Test(timeout=5000)
    public void suuri5() {
        Random rng=new Random(12873911);
        int n = 100000;
        ArrayList<Puu> puut = new ArrayList<Puu>();
        Puu puu = new Puu(rng.getInt(1, 1000000000), null, null);
        puut.add(puu);
        for (int i = 0; i < n-1; i++) {
            Puu uusi = new Puu(rng.getInt(1, 1000000000), null, null);
            while (true) {
                int k = rng.getInt(0,puut.size()-1);
                double x = rng.getInt(1, 2);
                if (x == 1 && puut.get(k).vasen == null) {
                    puut.get(k).vasen = uusi;
                } else if (x == 2 && puut.get(k).oikea == null) {
                    puut.get(k).oikea = uusi;                
                } else {
                    continue;
                }
                break;
            }
            puut.add(uusi);
        }
        suuriTesti(puu, false);
    }
}

