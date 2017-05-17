import java.util.*;

import org.junit.Test;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;
import java.util.Arrays;

@Points("6.1")
public class MainTest {
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
    
    public void pieniTesti(Puu puu, int tulos) {
        int ulos = Main.minimi(puu);
        assertTrue("Puussa " + puu +  " pienin esiintyvä arvo on " +
                   tulos + ", mutta metodisi palauttaa " + ulos + ".",
                   tulos == ulos);
    }

    public void suuriTesti(Puu puu, int tulos) {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        int ulos = Main.minimi(puu);
        assertTrue("Metodisi toimii väärin suurella syötteellä. Se palauttaa "+ulos+" kun pitäisi palauttaa "+tulos+".",
                   tulos == ulos);
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

        pieniTesti(puu, 1);
    }
    
    @Test(timeout=1000)
    public void pieni1() {
        Puu puu = new Puu(1, null, null);

        pieniTesti(puu, 1);
    }    
    
    @Test(timeout=1000)
    public void pieni2() {
        Puu puu = new Puu(1,
                          new Puu(2, null, null),
                          new Puu(2, null, null));

        pieniTesti(puu, 1);
    } 
    
    @Test(timeout=1000)
    public void pieni3() {
        Puu puu = new Puu(10,
                          new Puu(9,
                                  new Puu(4, null, null),
                                  new Puu(5, null, null)),
                          new Puu(8,
                                  new Puu(6, null, null),
                                  new Puu(7, null, null)));

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
                                          new Puu(10, null, null))));

        pieniTesti(puu, 1);
    }
    
    @Test(timeout=5000)
    public void suuri1() {
        Random rng=new Random(123812312);
        
        int n = 100000;
        Puu puu = new Puu(rng.getInt(1, 1000000000), null, null);
        Puu solmu = puu;
        for (int i = 0; i < n-1; i++) {
            Puu uusi = new Puu(rng.getInt(1, 1000000000), null, null);
            solmu.vasen = uusi;
            solmu = uusi;
        }
        suuriTesti(puu, 976);
    }

    @Test(timeout=5000)
    public void suuri2() {
        Random rng=new Random(319231231);
        int n = 100000;
        Puu puu = new Puu(rng.getInt(1, 1000000000), null, null);
        Puu solmu = puu;
        for (int i = 0; i < n-1; i++) {
            Puu uusi = new Puu(rng.getInt(1, 1000000000), null, null);
            solmu.oikea = uusi;
            solmu = uusi;
        }
        suuriTesti(puu, 352);
    }

    @Test(timeout=5000)
    public void suuri3() {
        Random rng=new Random(718287128);
        int n = 100000;
        ArrayList<Puu> puut = new ArrayList<Puu>();
        Puu puu = new Puu(rng.getInt(1, 1000000000), null, null);
        puut.add(puu);
        int k = 0;
        for (int i = 0; i < n-1; i++) {
            Puu uusi = new Puu(rng.getInt(1, 1000000000), null, null);
            if (puut.get(k).vasen == null) {
                puut.get(k).vasen = uusi;
            } else if (puut.get(k).oikea == null) {
                puut.get(k).oikea = uusi;                
                k++;
            }
            puut.add(uusi);
        }
        suuriTesti(puu, 2301);
    }
    
    @Test(timeout=5000)
    public void suuri4() {
        Random rng=new Random(981298981);
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
        suuriTesti(puu, 2969);
    }
    
    @Test(timeout=5000)
    public void suuri5() {
        Random rng=new Random(761726781);
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
        suuriTesti(puu, 708);
    }
}

