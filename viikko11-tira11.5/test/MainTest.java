import org.junit.Test;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@Points("11.5")
public class MainTest {
    
    private String kaarilista(int[] mista, int[] minne, long[] hinta) {
        String tulos = "";
        for (int i = 0; i < mista.length; i++) {
            if (tulos.equals("")) tulos = mista[i] + "->" + minne[i] + "(" + hinta[i] + ")";
            else tulos += ", "  + mista[i] + "->" + minne[i] + "(" + hinta[i] + ")";
        }
        return "[" + tulos + "]";
    }  
    
    public void pieniTesti(int n, int[] mista, int[] minne, long[] hinta, long tulos) {
        String sisalto = kaarilista(mista, minne, hinta);
        long ulos = Main.halvinReitti(n, mista, minne, hinta);
        assertTrue("Kun  n=" + n + " ja lennot hintoineen ovat " + sisalto +
                   ", halvimman hinnan hinnaksi tulee " + tulos +
                   ", mutta metodisi antaa " + ulos + ".",
                   tulos == ulos);
    }
    
    public void suuriTesti(int n, int[] mista, int[] minne, long[] hinta, long tulos) {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        long ulos = Main.halvinReitti(n, mista, minne, hinta);
        assertTrue("Metodisi toimii väärin suurella syötteellä. Se palauttaa "+ulos+" kun pitäisi palauttaa "+tulos+".",
                   tulos == ulos);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    @Test(timeout=5000)
    public void esimerkit() {
        pieniTesti(3, new int[] {1,2,1,2}, new int[] {2,3,3,1}, new long[] {3,1,7,5}, 2);        
        pieniTesti(4, new int[] {1,1,2,3}, new int[] {2,3,4,4}, new long[] {1,4,8,4}, 5);
        pieniTesti(4, new int[] {1,1,2,3}, new int[] {4,2,3,4}, new long[] {10,2,3,1}, 4);
        pieniTesti(4, new int[] {1,1,2,3}, new int[] {4,2,3,4}, new long[] {10,3,3,3}, 5);        
    }
    
    @Test(timeout=5000)
    public void pienet() {
        pieniTesti(5, new int[] {1,1,2,3,4}, new int[] {2,4,3,4,5}, new long[] {2,8,2,2,2}, 6);
        pieniTesti(8, new int[] {1,1,2,3,4,5,6,7}, new int[] {2,7,3,4,5,6,7,8}, new long[] {2,20,2,2,2,2,2,10}, 17);
        pieniTesti(5, new int[] {1,1,2,3,4}, new int[] {2,3,4,4,5}, new long[] {4,1,4,8,10}, 13);
    
        pieniTesti(2, new int[] {1,1,1}, new int[] {2,2,2}, new long[] {8,2,6}, 1);
        pieniTesti(6, new int[] {1,1,2,3,4,5}, new int[] {2,5,3,4,6,6}, new long[] {2,8,2,2,2,2}, 6);
    
        pieniTesti(2, new int[] {1}, new int[] {2}, new long[] {5}, 2);
        pieniTesti(2, new int[] {2,1}, new int[] {1,2}, new long[] {5,6}, 3);
        pieniTesti(3, new int[] {1}, new int[] {3}, new long[] {5}, 2);
        pieniTesti(4, new int[] {1, 1, 2, 3}, new int[] {2, 3, 4, 4}, new long[] {3, 4, 4, 3}, 5);
        pieniTesti(4, new int[] {1, 1, 2, 3}, new int[] {2, 3, 4, 4}, new long[] {3, 4, 3, 4}, 4);
        pieniTesti(4, new int[] {1, 1, 2, 3}, new int[] {2, 3, 4, 4}, new long[] {3, 4, 1, 4}, 2);
        pieniTesti(5, new int[] {1}, new int[] {5}, new long[] {10}, 5);
        pieniTesti(5, new int[] {1, 1, 2, 3, 4}, new int[] {5, 2, 3, 4, 5}, new long[] {10, 1, 1, 1, 1}, 3);
        pieniTesti(5, new int[] {1, 1, 2, 3, 4}, new int[] {5, 2, 3, 4, 5}, new long[] {10, 2, 2, 2, 2}, 5);
        pieniTesti(5, new int[] {1, 1, 2, 3, 4}, new int[] {5, 2, 3, 4, 5}, new long[] {10, 3, 3, 3, 3}, 5);  
        pieniTesti(4, new int[] {1, 2, 3}, new int[] {2, 3, 4}, new long[] {999999999, 999999999, 999999999}, 2499999997l);
        
        pieniTesti(5, new int[] {1,1,2,2,3,3,4}, new int[] {2,3,3,4,4,5,5}, new long[] {1,6,1,3,4,5,1}, 3);
        pieniTesti(5, new int[] {1,1,2,2,3,3,4}, new int[] {2,3,3,4,4,5,5}, new long[] {2,6,1,3,4,5,1}, 4);  
        pieniTesti(5, new int[] {1,1,2,2,3,3,4}, new int[] {2,3,3,4,4,5,5}, new long[] {3,6,1,3,4,5,1}, 5); 
        pieniTesti(5, new int[] {1,1,2,2,3,3,4}, new int[] {2,3,3,4,4,5,5}, new long[] {4,6,1,3,4,5,1}, 6); 
        pieniTesti(5, new int[] {1,1,2,2,3,3,4}, new int[] {2,3,3,4,4,5,5}, new long[] {5,6,1,3,4,5,1}, 6); 
        pieniTesti(5, new int[] {1,1,2,2,3,3,4}, new int[] {2,3,3,4,4,5,5}, new long[] {6,6,1,3,4,5,1}, 7); 
        pieniTesti(5, new int[] {1,1,2,2,3,3,4}, new int[] {2,3,3,4,4,5,5}, new long[] {7,6,1,3,4,5,1}, 7); 
        pieniTesti(5, new int[] {1,1,2,2,3,3,4}, new int[] {2,3,3,4,4,5,5}, new long[] {8,6,1,3,4,5,1}, 8); 
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
    
    public void testaaPieniRandom(int n, int m, int M,  int seed, long tulos){
        int[] mista=new int[m];
        int[] mihin=new int[m];
        long[] hinta=new long[m];
        
        Random rng=new Random(seed);
        for(int i=0; i<m; i++){
            mista[i]=rng.getInt(1, n);
            mihin[i]=rng.getInt(1, n);
            while(mihin[i]==mista[i])
                mihin[i]=rng.getInt(1, n);
            hinta[i]=rng.getInt(1,M);
        }
        pieniTesti(n, mista, mihin, hinta, tulos);
    }
    
    public void testaaSuuriRandom(int n, int m, int M,  int seed, long tulos){
        int[] mista=new int[m];
        int[] mihin=new int[m];
        long[] hinta=new long[m];
        
        Random rng=new Random(seed);
        for(int i=0; i<m; i++){
            mista[i]=rng.getInt(1, n);
            mihin[i]=rng.getInt(1, n);
            while(mihin[i]==mista[i])
                mihin[i]=rng.getInt(1, n);
            hinta[i]=rng.getInt(1,M);
        }
        suuriTesti(n, mista, mihin, hinta, tulos);
    }
    
    @Test(timeout=5000)
    public void pienetRandom() {
        testaaPieniRandom(10, 15, 9, 512561123, 7);
        testaaPieniRandom(10, 15, 9, 879218981, 3);
        testaaPieniRandom(10, 15, 9, 712638617, 1);
        testaaPieniRandom(10, 15, 9, 515125211, 4);
        testaaPieniRandom(10, 15, 9, 615276111, 2);
        
        testaaPieniRandom(7, 10, 10, 155151511, 1);
        testaaPieniRandom(7, 10, 10, 879218981, 10);
        testaaPieniRandom(7, 10, 10, 991989812, 4);
        testaaPieniRandom(7, 10, 10, 515125211, 8);
        testaaPieniRandom(7, 10, 10, 615276111, 4);
    }
    
    int[] lol(ArrayList<Integer> asd){
        int[] ret=new int[asd.size()];
        for(int i=0; i<asd.size(); i++)
            ret[i]=asd.get(i);
        return ret;
    }
    
    long[] l64(ArrayList<Long> asd){
        long[] ret=new long[asd.size()];
        for(int i=0; i<asd.size(); i++)
            ret[i]=asd.get(i);
        return ret;
    }
    
    @Test(timeout=5000)
    public void suuri1() {
        int n=100000;
        ArrayList<Integer> mista=new ArrayList();
        ArrayList<Integer> minne=new ArrayList();
        ArrayList<Long> hinta=new ArrayList();
        
        for(int i=0; i<49000-1; i++){
            mista.add(i+1);
            minne.add(i+2);
            if(i==47768)
                hinta.add(100000l);
            else
                hinta.add(1l);
        }
        
        mista.add(49000);
        minne.add(100000);
        hinta.add(1l);
        
        mista.add(1);
        minne.add(50000);
        hinta.add(1l);
        
        for(int i=50000; i<100000; i++){
            mista.add(i);
            minne.add(i+1);
            hinta.add(1l);
        }
        
        suuriTesti(n, lol(mista), lol(minne), l64(hinta), 50000);
    }
    
    @Test(timeout=5000)
    public void suuri2() {
        int n=100000;
        ArrayList<Integer> mista=new ArrayList();
        ArrayList<Integer> mihin=new ArrayList();
        ArrayList<Long> hinta=new ArrayList();
        
        int s=1;
        int asd=0;
        
        Random rng=new Random(98719211);
        
        while(asd<95000){
            mista.add(s);
            mihin.add(s+1);
            hinta.add((long)rng.getInt(1, 1000000000));
            mista.add(s);
            mihin.add(s+2);
            hinta.add((long)rng.getInt(1, 1000000000));
            mista.add(s+1);
            mihin.add(s+3);
            hinta.add((long)rng.getInt(1, 1000000000));
            mista.add(s+2);
            mihin.add(s+3);
            hinta.add((long)rng.getInt(1, 1000000000));
            s+=3;
            asd+=4;
        }
        mista.add(s);
        mihin.add(100000);
        hinta.add(1l);
        
        suuriTesti(n, lol(mista), lol(mihin), l64(hinta), 16636135644656l);
    }
    
    @Test(timeout=5000)
    public void suuri3() {
        int n=100000;
        ArrayList<Integer> mista=new ArrayList();
        ArrayList<Integer> mihin=new ArrayList();
        ArrayList<Long> hinta=new ArrayList();
        
        int s=1;
        int asd=0;
        
        Random rng=new Random(1726871681);
        
        while(asd<95000){
            mista.add(s);
            mihin.add(s+1);
            hinta.add((long)rng.getInt(1, 1000000000));
            mista.add(s);
            mihin.add(s+2);
            hinta.add((long)rng.getInt(1, 1000000000));
            mista.add(s+1);
            mihin.add(s+3);
            hinta.add((long)rng.getInt(1, 1000000000));
            mista.add(s+2);
            mihin.add(s+3);
            hinta.add((long)rng.getInt(1, 1000000000));
            s+=3;
            asd+=4;
        }
        mista.add(s);
        mihin.add(100000);
        hinta.add(1l);
        
        suuriTesti(n, lol(mista), lol(mihin), l64(hinta), 16602166474266l);
    }
    
    @Test(timeout=5000)
    public void suuri5() {
        int n=100000;
        ArrayList<Integer> mista=new ArrayList();
        ArrayList<Integer> minne=new ArrayList();
        ArrayList<Long> hinta=new ArrayList();
        
        ArrayList<Long> lol=new ArrayList();
        for(int i=1; i<=99999; i++)
            lol.add((long)i/1000 +1);
        
        Collections.shuffle(lol);
        
        for(int i=0; i<100000-1; i++){
            mista.add(i+1);
            minne.add(i+2);
            hinta.add(lol.get(i));
        }
        
        mista.add(1);
        minne.add(100000);
        hinta.add(10000000l);
        
        suuriTesti(n, lol(mista), lol(minne), l64(hinta), 5000000);
    }
    
    @Test(timeout=5000)
    public void suuri6() {
        int n=100000;
        ArrayList<Integer> mista=new ArrayList();
        ArrayList<Integer> minne=new ArrayList();
        ArrayList<Long> hinta=new ArrayList();
        
        ArrayList<Long> lol=new ArrayList();
        for(int i=1; i<=99999; i++)
            lol.add((long)i/1000 +1);
        
        Collections.shuffle(lol);
        
        for(int i=0; i<100000-1; i++){
            mista.add(i+1);
            minne.add(i+2);
            hinta.add(lol.get(i));
        }
        
        mista.add(1);
        minne.add(100000);
        hinta.add(12000000l);
        
        suuriTesti(n, lol(mista), lol(minne), l64(hinta), 5049949);
    }
    
    @Test(timeout=5000)
    public void suuri7() {
        testaaSuuriRandom(80000, 100000, 1000000000, 178298112, 7103599056l);
    }
    
    @Test(timeout=5000)
    public void suuri8() {
        testaaSuuriRandom(75000, 100000, 1000000000, 123412341, 24644042991l);
    }
    
    @Test(timeout=5000)
    public void suuri9() {
        testaaSuuriRandom(75000, 100000, 1000000000, 676712681, 12221987334l);
    }
    
    @Test(timeout=5000)
    public void suuri10() {
        testaaSuuriRandom(75000, 100000, 1000000000, 56172311, 9524141035l);
    }
}

