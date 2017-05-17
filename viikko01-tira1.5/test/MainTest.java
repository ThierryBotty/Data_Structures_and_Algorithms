import org.junit.Test;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import java.util.Arrays;

@Points("1.5")
public class MainTest{
    static class Random {
        private long val;
        private long mul=16807;
        private long mod=((long)1<<31)-1;
        private long get(){
            val=(val*mul)%mod;
            return val;
        }
        public int getInt(int a, int b){
            return a+(int)get()%(b-a+1);
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
    public String toString(long[] a){
        String s="[";
        if (a.length<=20){
            for (int i=0;i<a.length;i++){
                s=s+a[i];
                if (i+1<a.length){
                    s=s+", ";
                }
            }
        }
        else{
            s+="(suuri taulukko)";
        }
        s=s+"]";
        return s;
    }
    public void testaaRng(int seed, int n, long k, int mv, int ans){
        Random rnd=new Random(seed);
        long[] taulukko = new long[n];
        for (int i=0;i<n;i++){
            taulukko[i]=rnd.getInt(1, mv);
        }
        testaa(taulukko, n, k, mv, ans);
    }
    
    public void testaa(long[] taulukko, int n, long k, int mv, int ans){
        int ans2=Main.valienMaara(taulukko, k);
        assertTrue("Taulukossa "+toString(taulukko)+" on "+ans+" väliä joiden summa on "+k
                +", mutta metodisi palauttaa "+ans2+".", ans2==ans);
    }
    @Test(timeout=1000)
    public void esimerkit(){
        testaa(new long[] {1, 1, 1, 1}, 4, 4, 1, 1);
        testaa(new long[] {1, 1, 1, 1}, 4, 1, 1, 4);
        testaa(new long[] {1, 2, 3, 4}, 4, 3, 4, 2);
        testaa(new long[] {1, 3, 1, 2}, 4, 3, 4, 2);
    }
    @Test(timeout=1000)
    public void pienet(){
        testaaRng(0, 20, 40, 10, 0);
        testaaRng(12, 20, 40, 10, 1);
        testaaRng(1, 20, 40, 10, 2);
        testaaRng(6, 20, 40, 10, 3);
        testaaRng(2, 20, 40, 10, 4);
        testaaRng(24, 20, 40, 10, 5);
        testaaRng(162, 20, 40, 10, 6);
        testaaRng(191, 20, 40, 10, 7);
        testaaRng(1977, 20, 40, 10, 8);
        testaaRng(4322, 20, 40, 10, 9);
        testaaRng(850098, 20, 40, 10, 10);
        testaaRng(6120045, 20, 40, 10, 11);
    }
    @Test(timeout=1000)
    public void suuriK1(){
        testaaRng(1, 2000, (long)1e10, (int)1e9, 0);
        testaaRng(209739, 2000, (long)1e10, (int)1e9, 1);
        testaaRng(2, 2000, (long)1e10, (int)1e9, 0);
        testaaRng(957068, 2000, (long)1e10, (int)1e9, 1);
        testaaRng(3, 2000, (long)1e10, (int)1e9, 0);
        testaaRng(2284083, 2000, (long)1e10, (int)1e9, 1);
        testaaRng(4, 2000, (long)1e10, (int)1e5, 0);
        testaaRng(2790356, 2000, (long)1e10, (int)1e9, 1);
        testaaRng(5, 2000, (long)1e10, (int)1e6, 0);
    }
    @Test(timeout=1000)
    public void suuriK2(){
        testaaRng(2928572, 2000, (long)1e10, (int)1e9, 1);
        testaaRng(6, 2000, (long)1e10, (int)1e7, 0);
        testaaRng(2988602, 2000, (long)1e10, (int)1e9, 1);
        testaaRng(7, 2000, (long)1e10, (int)1e8, 0);
        testaaRng(4366482, 2000, (long)1e10, (int)1e9, 1);
        testaaRng(8, 2000, (long)1e10, (int)1e9, 0);
        testaaRng(9, 2000, (long)1e11, (int)1e9, 0);
        testaaRng(10, 2000, (long)1e12, (int)1e9, 0);
        testaaRng(11, 2000, (long)1e13, (int)1e9, 0);
    }
    @Test(timeout=1000)
    public void suuri1(){
        testaaRng(0, 100000, 50000, 1, 50001);
    }
    @Test(timeout=1000)
    public void suuri2(){
        testaaRng(1, 100000, 100000, 2, 22241);
    }
    @Test(timeout=1000)
    public void suuri3(){
        testaaRng(1, 100000, 100000, 3, 24868);
    }
    @Test(timeout=1000)
    public void suuri4(){
        testaaRng(1, 100000, 500000, 10, 1724);
    }
    @Test(timeout=1000)
    public void suuri5(){
        testaaRng(942, 100000, (long)1e9, 50000, 10);
    }
}

