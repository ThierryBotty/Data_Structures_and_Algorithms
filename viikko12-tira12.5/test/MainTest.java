import org.junit.Test;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;
import java.util.Arrays;
import java.lang.Object;

@Points("12.5")
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
    
    public void pieniTesti(long[] aita, long tulos) {
        String s = Arrays.toString(aita);
        long ulos=Main.suurinAla(aita);
        assertTrue("Kun aita on " + s + " olisi suurimman mainosjulisteen "
                + " pinta-ala " + tulos + ", mutta metodisi palauttaa "
                + ulos + ".",
                   tulos==ulos);
    }

    public void suuriTesti(long[] aita, long tulos) {
        long ulos=Main.suurinAla(aita);
        assertTrue("Metodisi toimii väärin suurella syötteellä. Sen kuuluisi "
                + "palauttaa "+ tulos + ", mutta se palauttaa " + ulos + ".",
                   tulos==ulos);
    }
    
    public long[] gen(int n, int ala, int yla, int seed){
        long[] t = new long[n];
        Random rng=new Random(seed);
        
        for(int i=0; i<n; i++)
                t[i]=rng.getInt(ala, yla);
        
        return t;
    }
    
    @Test(timeout=1000)
    public void esimerkit() {
        pieniTesti(new long[]{1, 2, 3, 4, 5}, 9);
        pieniTesti(new long[]{5, 4, 3, 2, 1}, 9);
        pieniTesti(new long[]{5, 5, 1, 5, 5, 4}, 12);
        pieniTesti(new long[]{1, 1, 1}, 3);
    }   
    
    @Test(timeout=1000)
    public void pienet() {
        pieniTesti(new long[]{}, 0);
        pieniTesti(new long[]{2,1,2,1,2,1}, 6);
        pieniTesti(new long[]{2,1,2,1,2}, 5);
        pieniTesti(new long[]{4, 3, 2, 1}, 6);
        pieniTesti(new long[]{4, 4, 4, 4, 4, 100}, 100);
        pieniTesti(new long[]{1000, 4, 4, 4, 4, 4}, 1000);
        pieniTesti(new long[]{2,2,3,5,5,6,4,1}, 16);
    }   
    
    
    
    @Test(timeout=1000)
    public void pienetRandom() {
        pieniTesti(gen(10, 1, 10, 1234123), 21);
        pieniTesti(gen(10, 1, 10, 4151344), 10);
        pieniTesti(gen(10, 1, 10, 1231248129), 12);
        pieniTesti(gen(10, 1, 10, 1411515), 21);
        pieniTesti(gen(5, 1, 100, 1411515), 200);
        pieniTesti(gen(5, 1, 100, 66616115), 100);
        pieniTesti(gen(5, 1, 100, 12341231), 140);
        pieniTesti(gen(10, 1, 100, 12341231), 174);
        pieniTesti(gen(10, 1, 100, 961728123), 150);
        pieniTesti(gen(10, 1, 31, 961728123), 100);
        pieniTesti(gen(10, 1, 31, 231241823), 70);
        pieniTesti(gen(10, 1, 31, 423141267), 140);
    }
    
    @Test(timeout=5000)
    public void suuri1() {
        int n = 200000;
        long[] aita=new long[n];
        
        for(int i=0; i<n; i++)
            aita[i]=1;
        
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        suuriTesti(aita, 200000);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    @Test(timeout=5000)
    public void suuri2() {
        int n = 200000;
        long[] aita=new long[n];
        
        for(int i=0; i<n; i++)
            aita[i]=n-i;
        
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        suuriTesti(aita, 10000100000l);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    @Test(timeout=5000)
    public void suuri3() {
        int n = 200000;
        long[] aita=new long[n];
        
        for(int i=0; i<n/2; i++)
            aita[i]=2*(i+1);
        
        for(int i=0; i<n/2; i++)
            aita[n/2+i]=2*(n-i+1);
        
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        suuriTesti(aita, 20000400000l);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    @Test(timeout=5000)
    public void suuri4() {
        int n = 200000;
        long[] aita=gen(n, 1, 1000000000, 67867831);
        
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        suuriTesti(aita, 9348238290l);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);    
    }
    
    @Test(timeout=5000)
    public void suuri5() {
        int n = 200000;
        long[] aita=gen(n, 1, 3, 323781913);
        
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        suuriTesti(aita, 200000);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    @Test(timeout=5000)
    public void suuri6() {
        int n = 200000;
        long[] aita=gen(n, 1, 100000, 283912412);
        
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        suuriTesti(aita, 1279080);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    @Test(timeout=5000)
    public void suuri7() {
        int n = 200000;
        long[] aita=new long[n];
        
        for(int i=0; i<100000; i++)
            aita[i]=i;
        for(int i=100000; i<200000; i++)
            aita[i]=100000-(i-100000);
        
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        suuriTesti(aita, 5000050000l);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    @Test(timeout=5000)
    public void suuri8() {
        int n = 200000;
        long[] aita=new long[n];
        
        for(int i=0; i<90000; i++)
            aita[i]=100000;
        for(int i=90000; i<110000; i++)
            aita[i]=1000001;
        for(int i=110000; i<200000; i++)
            aita[i]=100000;
        
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        suuriTesti(aita, 20000020000l);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    @Test(timeout=5000)
    public void suuri9() {
        int n = 200000;
        long[] aita=new long[n];
        
        for(int i=0; i<90000; i++)
            aita[i]=100000;
        for(int i=90000; i<110000; i++)
            aita[i]=100000;
        for(int i=110000; i<200000; i++)
            aita[i]=100000;
        
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        suuriTesti(aita, 20000000000l);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    @Test(timeout=5000)
    public void suuri10() {
        int n = 200000;
        long[] aita=new long[n];
        
        for(int i=0; i<90000; i++)
            aita[i]=100000;
        for(int i=90000; i<110000; i++)
            aita[i]=999999;
        for(int i=110000; i<200000; i++)
            aita[i]=100000;
        
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        suuriTesti(aita, 20000000000l);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    public long[] gen2(int n, int H, int seed){
        long[] t = new long[n];
        Random rng=new Random(seed);
        
        for(int i=0; i<n; i++)
            t[i]=H-(long)Math.sqrt(rng.getInt(0,1000000000));
        
        return t;
    }
    
    @Test(timeout=5000)
    public void suuri11() {
        int n = 200000;
        long[] aita=gen2(200000, 31623, 98987987);
        
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        suuriTesti(aita, 225331);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    @Test(timeout=5000)
    public void suuri12() {
        int n = 200000;
        long[] aita=gen2(200000, 31623, 1273981);
        
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        suuriTesti(aita, 244720);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    @Test(timeout=5000)
    public void suuri13() {
        int n = 200000;
        long[] aita=new long[n];
        
        for(int i=0; i<200000; i++)
            aita[i]=100000;
        
        for(int i=0; i<100; i++)
            aita[i]=1000000000;
        
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        suuriTesti(aita, 100000000000l);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    @Test(timeout=5000)
    public void suuri14() {
        int n = 200000;
        long[] aita=new long[n];
        
        for(int i=0; i<200000; i++)
            aita[i]=100000;
        
        for(int i=100000-50; i<100000+50; i++)
            aita[i]=1000000000;
        
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        suuriTesti(aita, 100000000000l);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    @Test(timeout=5000)
    public void suuri15() {
        int n = 200000;
        long[] aita=new long[n];
        
        for(int i=0; i<200000; i++)
            aita[i]=100000;
        
        for(int i=199900; i<200000; i++)
            aita[i]=1000000000;
        
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        suuriTesti(aita, 100000000000l);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
}

