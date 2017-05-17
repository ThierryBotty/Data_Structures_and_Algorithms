import org.junit.Test;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;

@Points("1.4")
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
    public String toString(int[] a){
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
    public void testaaRng(int n, int seed, int ans){
        Random rng=new Random(seed);
        int[] polku=new int[n];
        for (int i=0;i<n;i++){
            polku[i]=rng.getInt(-1000, 1000);
        }
        testaa(polku, ans);
    }
    public void testaa(int[] polku, int ans){
        int ans2=Main.harvinVali(polku);
        assertTrue("Polulla "+toString(polku)+" harvin väli on "+ans
                +", mutta metodisi palauttaa "+ans2+".", ans2==ans);
    }
    
    @Test(timeout=1000)
    public void esimerkit() {
        testaa(new int[] {-500, -500, -100, -900, -600, -500}, 1);
        testaa(new int[] {-300, -300, 200, -300, -300, -1}, 5);
        testaa(new int[] {-500, -500, -100, -200, -600, -500}, 2);
        testaa(new int[] {-300, -300, -300, -300, -300, -300}, 3);
        testaa(new int[] {1000, -1000, -1000, 0, -100, -100}, 4);
    }
    @Test(timeout=1000)
    public void pienet(){
        testaaRng(20, 1, 1);
        testaaRng(20, 13, 2);
        testaaRng(20, 24, 3);
        testaaRng(20, 11, 4);
        testaaRng(20, 12, 5);
        testaaRng(20, 22, 6);
        testaaRng(20, 5, 7);
        testaaRng(20, 37, 8);
        testaaRng(20, 15, 9);
        testaaRng(20, 47, 10);
        testaaRng(20, 4, 11);
        testaaRng(20, 61, 12);
        testaaRng(20, 57, 13);
        testaaRng(20, 323, 14);
        testaaRng(20, 55, 15);
        testaaRng(20, 150, 16);
        testaaRng(20, 646, 17);
        testaaRng(20, 29, 18);
        testaaRng(20, 7, 19);
    }
    @Test(timeout=1000)
    public void suuret(){
        testaaRng(2000, 138, 656);
        testaaRng(2000, 455, 559);
        testaaRng(2000, 1229, 786);
        testaaRng(2000, 1584, 991);
        testaaRng(2000, 1617, 994);
        testaaRng(2000, 1639, 658);
        testaaRng(2000, 2691, 642);
        testaaRng(2000, 3338, 959);
        testaaRng(2000, 5018, 808);
        testaaRng(2000, 5880, 587);
        testaaRng(2000, 19, 61);
        testaaRng(2000, 35, 53);
        testaaRng(2000, 64, 72);
        testaaRng(2000, 77, 58);
        testaaRng(2000, 89, 76);
        testaaRng(2000, 7, 11);
        testaaRng(2000, 10, 6);
        testaaRng(2000, 13, 2);
        testaaRng(2000, 51, 15);
        testaaRng(2000, 54, 3);
    }
    @Test(timeout=1000)
    public void suuri1(){
        testaaRng(100000, 20089, 41276);
    }
    @Test(timeout=1000)
    public void suuri2(){
        testaaRng(100000, 18915, 11106);
    }
    @Test(timeout=1000)
    public void suuri3(){
        testaaRng(100000, 14742, 13434);
    }
    @Test(timeout=1000)
    public void suuri4(){
        testaaRng(100000, 19, 61);
    }
    @Test(timeout=1000)
    public void suuri5(){
        testaaRng(100000, 345, 2453);
    }
}

