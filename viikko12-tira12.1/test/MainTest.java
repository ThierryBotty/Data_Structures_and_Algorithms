import org.junit.Test;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;
import java.util.Arrays;

@Points("12.1")
public class MainTest {
    
    public void testaa(Tiet t, int a, int b, boolean tulos) {
        boolean ulos = t.yhteys(a, b);
        assertTrue("Luokkasi toimii väärin: metodia '.yhteys' kutsuttaessa se palauttaa '"+ulos+"', kun pitäisi palauttaa '"+tulos+"'.", tulos == ulos);
    }
    
    @Test(timeout=5000)
    public void esimerkki() {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        Tiet t=new Tiet(5);
        testaa(t,1,2,false);
        testaa(t,1,1,true);
        t.rakenna(1, 2);
        testaa(t,1,2,true);
        testaa(t,1,3,false);
        t.rakenna(2, 3);
        testaa(t,1,3,true);
        t.rakenna(1, 4);
        testaa(t,4,3,true);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    @Test(timeout=5000)
    public void pieni1() {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        Tiet t=new Tiet(10);
        testaa(t,10,10,true);
        t.rakenna(1, 2);
        t.rakenna(2, 3);
        t.rakenna(4, 5);
        t.rakenna(5, 6);
        testaa(t,1,6,false);
        t.rakenna(1, 6);
        testaa(t,1,6,true);
        testaa(t,2,6,true);
        testaa(t,3,6,true);
        testaa(t,4,6,true);
        testaa(t,5,6,true);
        testaa(t,6,6,true);
        testaa(t,7,6,false);
        t.rakenna(1, 10);
        testaa(t,10,6,true);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    @Test(timeout=5000)
    public void pieni2() {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        Tiet t=new Tiet(10);
        t.rakenna(1, 3);
        t.rakenna(1, 5);
        t.rakenna(1, 7);
        t.rakenna(1, 9);
        
        t.rakenna(2, 10);
        t.rakenna(4, 10);
        t.rakenna(6, 10);
        t.rakenna(8, 10);
        
        for(int i=1; i<=10; i++)
            for(int e=1; e<=10; e++){
                testaa(t,i,e,i%2==e%2);
            }
        
        t.rakenna(1, 2);
        
        for(int i=1; i<=10; i++)
            for(int e=1; e<=10; e++){
                testaa(t,i,e,true);
            }
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    @Test(timeout=5000)
    public void suuri1() {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        Tiet t=new Tiet(100000);
        
        for(int i=2; i<=99999; i++){
            t.rakenna(i-1, i);
            testaa(t,1,i,true);
            testaa(t,1,i+1,false);
        }
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    @Test(timeout=5000)
    public void suuri2() {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        Tiet t=new Tiet(100000);
        
        for(int i=2; i<=99999; i++){
            t.rakenna(i, i-1);
            testaa(t,1,i,true);
            testaa(t,1,i+1,false);
        }
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
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
    public void suuri3() {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        Tiet t=new Tiet(100000);
        
        for(int i=1; i<=7; i++)
            for(int e=0; i+7*e<=100000; e++){
                t.rakenna(i, i+7*e);
            }
        
        Random rng=new Random(127398719);
        
        for(int i=0; i<200000; i++){
            int a=rng.getInt(1, 100000);
            int b=rng.getInt(1, 100000);
            testaa(t,a,b,a%7==b%7);
        }
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    @Test(timeout=5000)
    public void suuri4() {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        Tiet t=new Tiet(100000);
        
        for(int i=2; i<=43336; i++){
            t.rakenna(i, i-1);
        }
        
        for(int i=43339; i<=100000; i++){
            t.rakenna(i-1, i);
        }
        t.rakenna(43336, 43338);
        
        Random rng=new Random(786178268);

        
        for(int i=0; i<200000; i++){
            int a=rng.getInt(1, 100000);
            int b=rng.getInt(1, 100000);
            if(a==43337 || b==43337)
                testaa(t,a,b,a==b);
            else
                testaa(t,a,b,true);
        }
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
}
