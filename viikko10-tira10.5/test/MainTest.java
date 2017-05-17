import org.junit.Test;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;
import java.util.ArrayList;
import java.util.Arrays;

@Points("10.5")
public class MainTest {
    
    private String kaarilista(int[] mista, int[] minne) {
        String tulos = "";
        for (int i = 0; i < mista.length; i++) {
            if (tulos.equals("")) tulos = mista[i] + "->" + minne[i];
            else tulos += ", "  + mista[i] + "->" + minne[i];
        }
        return "[" + tulos + "]";
    }
    
    public void pieniTesti(int n, long[] k, int[] mista, int[] minne, long tulos) {
        String sisalto = kaarilista(mista, minne);
        long ulos = Main.kolikoita(n, k, mista, minne);
        assertTrue("Kun annetun verkon kaaret ovat "+sisalto+"\n"
                + "ja taulukon k sisältö "+Arrays.toString(k)+",\n"
                + "tulisi algoritmisi palauttaa "+tulos+", mutta se palauttaa "+ulos+".", tulos == ulos);
    }
    
    public void suuriTesti(int n, long[] k, int[] mista, int[] minne, long tulos) {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        long ulos = Main.kolikoita(n, k, mista, minne);
        assertTrue("Algoritmisi toimii väärin suurella syötteellä. Sen tulisi palauttaa "+tulos+", mutta se palauttaa "+ulos+".", tulos == ulos);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    @Test(timeout=5000)
    public void esimerkit() {
        pieniTesti(3, new long[] {0, 1, 1, 1}, new int[] {1, 2}, new int[] {2, 3}, 3);
        pieniTesti(3, new long[] {0, 1, 1, 1}, new int[] {2, 1}, new int[] {1, 3}, 2);
        pieniTesti(4, new long[] {0, 1, 1, 1, 10}, new int[] {1, 2, 1}, new int[] {2, 3, 4}, 11);
        pieniTesti(4, new long[] {0, 1, 1, 1, 1}, new int[] {1, 1, 1}, new int[] {2, 3, 4}, 2);
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
    
    public void testaaPieniRandom(int n, int m, int seed, long tulos){
        int[] mista=new int[m];
        int[] mihin=new int[m];
        
        Random rng=new Random(seed);
        for(int i=0; i<m; i++){
            mista[i]=rng.getInt(1, n);
            mihin[i]=rng.getInt(1, n);
            while(mihin[i]==mista[i])
                mihin[i]=rng.getInt(1, n);
        }
        
        long[] k=new long[n+1];
        for(int i=1; i<=n; i++)
            k[i]=rng.getInt(1, 9);
        pieniTesti(n, k, mista, mihin, tulos);
    }
    
    public void testaaSuuriRandom(int n, int m, int seed, long tulos){
        int[] mista=new int[m];
        int[] mihin=new int[m];
        
        Random rng=new Random(seed);
        for(int i=0; i<m; i++){
            mista[i]=rng.getInt(1, n);
            mihin[i]=rng.getInt(1, n);
            while(mihin[i]==mista[i])
                mihin[i]=rng.getInt(1, n);
        }
        
        long[] k=new long[n+1];
        for(int i=1; i<=n; i++)
            k[i]=rng.getInt(0, 1000000000);
        suuriTesti(n, k, mista, mihin, tulos);
    }
    
    @Test(timeout=5000)
    public void pienet() {
        pieniTesti(3, new long[] {0, 1, 1, 1}, new int[] {1, 3}, new int[] {2, 2}, 2);
        pieniTesti(5, new long[] {0, 0, 5, 4, 4, 6}, new int[] {1, 2, 1, 4}, new int[] {2, 3, 4, 5}, 10);
        pieniTesti(5, new long[] {0, 1, 2, 3, 4, 5}, new int[] {1,2,1,3,4}, new int[] {2,4,3,4,5}, 13);
        
        pieniTesti(9, new long[] {0,1,3,4,2,5,9,7,8,6}, new int[] {1, 2, 1, 3, 1, 4, 5, 6, 7, 6, 8}, new int[] {2, 5, 3, 5, 4, 5, 6, 7, 9, 8, 9}, 33);
        pieniTesti(4, new long[] {0, 1, 1, 5, 1}, new int[] {1, 2, 1, 3}, new int[] {2, 4, 3, 4}, 7);
        pieniTesti(4, new long[] {0, 1, 5, 1, 1}, new int[] {1, 2, 1, 3}, new int[] {2, 4, 3, 4}, 7);
        pieniTesti(4, new long[] {0, 1, 5, 5, 1}, new int[] {1, 2, 1, 3}, new int[] {2, 4, 3, 4}, 7);
        
        pieniTesti(6, new long[] {0, 1, 10, 1, 2, 6, 1}, new int[] {1, 2, 3, 1, 4, 5}, new int[] {2, 3, 6, 4, 5, 6}, 13);
        pieniTesti(6, new long[] {0, 1, 10, 1, 2, 7, 1}, new int[] {1, 2, 3, 1, 4, 5}, new int[] {2, 3, 6, 4, 5, 6}, 13);
        pieniTesti(6, new long[] {0, 1, 10, 1, 2, 8, 1}, new int[] {1, 2, 3, 1, 4, 5}, new int[] {2, 3, 6, 4, 5, 6}, 13);
        pieniTesti(6, new long[] {0, 1, 10, 1, 2, 9, 1}, new int[] {1, 2, 3, 1, 4, 5}, new int[] {2, 3, 6, 4, 5, 6}, 13);
        pieniTesti(6, new long[] {0, 1, 10, 1, 2, 10, 1}, new int[] {1, 2, 3, 1, 4, 5}, new int[] {2, 3, 6, 4, 5, 6}, 14);
        pieniTesti(6, new long[] {0, 1, 10, 1, 2, 11, 1}, new int[] {1, 2, 3, 1, 4, 5}, new int[] {2, 3, 6, 4, 5, 6}, 15);
        
        testaaPieniRandom(10, 10, 78127878, 6);
        testaaPieniRandom(10, 10, 801980811, 15);
        testaaPieniRandom(10, 10, 999191919, 4);
        testaaPieniRandom(10, 10, 111111111, 6);
        testaaPieniRandom(10, 10, 562341211, 11);
        testaaPieniRandom(10, 10, 415123511, 18);
        testaaPieniRandom(10, 10, 124661231, 22);
        testaaPieniRandom(10, 10, 611351211, 8);
        testaaPieniRandom(10, 10, 61611616, 12);
        testaaPieniRandom(10, 10, 999999999, 26);
        testaaPieniRandom(10, 10, 451255511, 28);
    }
    
    @Test(timeout=5000)
    public void suuri1() {
        testaaSuuriRandom(100000, 90000, 712683718, 690595766);
    }
    
    @Test(timeout=5000)
    public void suuri2() {
        testaaSuuriRandom(100000, 60000, 112122341, 2159513121l);
    }
    
    @Test(timeout=5000)
    public void suuri3() {
        testaaSuuriRandom(100000, 90000, 102983111, 2127107221l);
    }
    
    @Test(timeout=5000)
    public void suuri4() {
        testaaSuuriRandom(100000, 90000, 102983111, 2127107221l);
    }
    
    @Test(timeout=5000)
    public void suuri5() {
        testaaSuuriRandom(100000, 90000, 1273192311, 949239171l);
    }
    
    @Test(timeout=5000)
    public void suuri6() {
        int n=100000;
        int[] mista=new int[n-1];
        int[] mihin=new int[n-1];
        long[] k=new long[n+1];
         
        Random rng=new Random(71279791);
        
        for(int i=0; i<n-1; i++){
            mista[i]=i+1;
            mihin[i]=rng.getInt(i+2, n);
        }
        
        for(int i=1; i<=n; i++)
            k[i]=rng.getInt(1, 1000000000);
        
        suuriTesti(n, k, mista, mihin, 10125078855l);
    }
    
    @Test(timeout=5000)
    public void suuri7() {
        int n=100000;
        int[] mista=new int[n-1];
        int[] mihin=new int[n-1];
        long[] k=new long[n+1];

        Random rng=new Random(471236811);
        
        for(int i=0; i<n-1; i++){
            mista[i]=i+1;
            mihin[i]=rng.getInt(i+2, n);
        }
        
        for(int i=1; i<=n; i++)
            k[i]=rng.getInt(1, 1000000000);
        
        suuriTesti(n, k, mista, mihin, 3861697719l);
    }
    
    @Test(timeout=5000)
    public void suuri8() {
        int n=90000;
        int lisa=5000;
        int[] mista=new int[n-1 + lisa];
        int[] mihin=new int[n-1 + lisa];
        long[] k=new long[n+1];
        
        Random rng=new Random(112931238);
        
        for(int i=0; i<n-1; i++){
            mista[i]=i+1;
            mihin[i]=rng.getInt(i+2, n);
        }
        
        for(int i=90000-1; i<90000+lisa-1; i++){
            mista[i]=rng.getInt(1, n);
            mihin[i]=rng.getInt(1, n);
            while(mihin[i]==mista[i])
                mihin[i]=rng.getInt(1, n);
        }
        
        for(int i=1; i<=n; i++)
            k[i]=rng.getInt(1, 1000000000);
        
        suuriTesti(n, k, mista, mihin, 4239843719l);
    } 
    
    @Test(timeout=5000)
    public void suuri9() {
        int n=90000;
        int lisa=10000;
        int[] mista=new int[n-1 + lisa];
        int[] mihin=new int[n-1 + lisa];
        long[] k=new long[n+1];
        
        Random rng=new Random(18273912);
        
        for(int i=0; i<n-1; i++){
            mista[i]=i+1;
            mihin[i]=rng.getInt(i+2, n);
        }
        
        for(int i=90000-1; i<90000+lisa-1; i++){
            mista[i]=rng.getInt(1, n);
            mihin[i]=rng.getInt(1, n);
            while(mihin[i]==mista[i])
                mihin[i]=rng.getInt(1, n);
        }
        
        for(int i=1; i<=n; i++)
            k[i]=rng.getInt(1, 1000000000);
        
        suuriTesti(n, k, mista, mihin, 15804724032l);
    } 
    
    @Test(timeout=5000)
    public void suuri10() {
        int n=90000;
        int lisa=10000;
        int[] mista=new int[n-1 + lisa];
        int[] mihin=new int[n-1 + lisa];
        long[] k=new long[n+1];
        
        Random rng=new Random(376777777);
        
        for(int i=0; i<n-1; i++){
            mista[i]=i+1;
            mihin[i]=rng.getInt(i+2, n);
        }
        
        for(int i=90000-1; i<90000+lisa-1; i++){
            mista[i]=rng.getInt(1, n);
            mihin[i]=rng.getInt(1, n);
            while(mihin[i]==mista[i])
                mihin[i]=rng.getInt(1, n);
        }
        
        for(int i=1; i<=n; i++)
            k[i]=rng.getInt(1, 1000000000);
        
        suuriTesti(n, k, mista, mihin, 15116065088l);
    }
    
    @Test(timeout=5000)
    public void suuri11() {
        int n=90000;
        int lisa1=5000;
        int lisa2=5000;
        int[] mista=new int[n-1 + lisa1 + lisa2];
        int[] mihin=new int[n-1 + lisa1 + lisa2];
        long[] k=new long[n+1];
        
        Random rng=new Random(376777777);
        
        for(int i=0; i<n-1; i++){
            mista[i]=i+1;
            mihin[i]=rng.getInt(i+2, n);
        }
        
        for(int i=90000-1; i<90000+lisa1-1; i++){
            mista[i]=rng.getInt(1, n);
            mihin[i]=rng.getInt(1, n);
            while(mihin[i]==mista[i])
                mihin[i]=rng.getInt(1, n);
        }
        
        for(int i=90000+lisa1-1; i<90000+lisa1+lisa2-1; i++){
            mista[i]=1;
            mihin[i]=rng.getInt(1, n);
            while(mihin[i]==mista[i])
                mihin[i]=rng.getInt(1, n);
        }
        
        for(int i=1; i<=n; i++)
            k[i]=rng.getInt(1, 1000000000);
        
        suuriTesti(n, k, mista, mihin, 34073937790l);
    }
    
    @Test(timeout=5000)
    public void suuri12() {
        int n=90000;
        int lisa1=5000;
        int lisa2=5000;
        int[] mista=new int[n-1 + lisa1 + lisa2];
        int[] mihin=new int[n-1 + lisa1 + lisa2];
        long[] k=new long[n+1];
        
        Random rng=new Random(73457333);
        
        for(int i=0; i<n-1; i++){
            mista[i]=i+1;
            mihin[i]=rng.getInt(i+2, n);
        }
        
        for(int i=90000-1; i<90000+lisa1-1; i++){
            mista[i]=rng.getInt(1, n);
            mihin[i]=rng.getInt(1, n);
            while(mihin[i]==mista[i])
                mihin[i]=rng.getInt(1, n);
        }
        
        for(int i=90000+lisa1-1; i<90000+lisa1+lisa2-1; i++){
            mista[i]=1;
            mihin[i]=rng.getInt(1, n);
            while(mihin[i]==mista[i])
                mihin[i]=rng.getInt(1, n);
        }
        
        for(int i=1; i<=n; i++)
            k[i]=rng.getInt(1, 1000000000);
        
        suuriTesti(n, k, mista, mihin, 25755065844l);
    }
   
    @Test(timeout=5000)
    public void suuri13() {
        int n=90000;
        int lisa1=5000;
        int lisa2=5000;
        int[] mista=new int[n-1 + lisa1 + lisa2];
        int[] mihin=new int[n-1 + lisa1 + lisa2];
        long[] k=new long[n+1];
        
        Random rng=new Random(98791829);
        
        for(int i=0; i<n-1; i++){
            mista[i]=i+1;
            mihin[i]=rng.getInt(i+2, n);
        }
        
        for(int i=90000-1; i<90000+lisa1-1; i++){
            mista[i]=rng.getInt(1, n);
            mihin[i]=rng.getInt(1, n);
            while(mihin[i]==mista[i])
                mihin[i]=rng.getInt(1, n);
        }
        
        for(int i=90000+lisa1-1; i<90000+lisa1+lisa2-1; i++){
            mista[i]=1;
            mihin[i]=rng.getInt(1, n);
            while(mihin[i]==mista[i])
                mihin[i]=rng.getInt(1, n);
        }
        
        for(int i=1; i<=n; i++)
            k[i]=rng.getInt(1, 1000000000);
        
        suuriTesti(n, k, mista, mihin, 31114310620l);
    }
    
    @Test(timeout=5000)
    public void suuri14() {
        int n=100000;
        int[] mista=new int[n-1];
        int[] mihin=new int[n-1];
        long[] k=new long[n+1];

        Random rng=new Random(471236811);
        
        for(int i=0; i<n-1; i++){
            mista[i]=i+1;
            mihin[i]=i+2;
        }
        
        for(int i=1; i<=n; i++)
            k[i]=1000000000;
        
        suuriTesti(n, k, mista, mihin, 100000000000000l);
    }
    
    @Test(timeout=5000)
    public void suuri15() {
        int n=100000;
        int[] mista=new int[n-1];
        int[] mihin=new int[n-1];
        long[] k=new long[n+1];

        Random rng=new Random(471236811);
        
        for(int i=0; i<50000-1; i++){
            mista[i]=i+1;
            mihin[i]=i+2;
        }
        
        mista[50000-1]=1;
        mihin[50000-1]=50001;
        
        for(int i=50000; i<100000-1; i++){
            mista[i]=i+1;
            mihin[i]=i+2;
        }
        
        for(int i=1; i<=n; i++)
            k[i]=1000000000;
        
        k[87182]=0;
        k[51411]=0;
        k[33114]=0;
        k[23145]=0;
        k[11111]=0;
        k[57111]=0;
        
        
        suuriTesti(n, k, mista, mihin, 49998000000000l);
    }
    
    
    int[] lol(ArrayList<Integer> asd){
        int[] ret=new int[asd.size()];
        for(int i=0; i<asd.size(); i++)
            ret[i]=asd.get(i);
        return ret;
    }
    
    @Test(timeout=5000)
    public void suuri16() {
        int n=100000;
        ArrayList<Integer> mista=new ArrayList();
        ArrayList<Integer> mihin=new ArrayList();
        long[] k=new long[n+1];

        Random rng=new Random(471236811);
        for(int i=1; i<=n; i++)
            k[i]=rng.getInt(0, 1000000000);
        
        int s=1;
        int asd=0;
        
        while(asd<95000){
            mista.add(s);
            mihin.add(s+1);
            mista.add(s);
            mihin.add(s+2);
            mista.add(s+1);
            mihin.add(s+3);
            mista.add(s+2);
            mihin.add(s+3);
            s+=3;
            asd+=4;
        }
        
        
        suuriTesti(n, k, lol(mista), lol(mihin), 26480818097162l);
    }
}


