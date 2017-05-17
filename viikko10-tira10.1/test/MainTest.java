import org.junit.Test;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@Points("10.1")
public class MainTest {
    
    private String kaarilista(int[] mista, int[] minne) {
        String tulos = "";
        for (int i = 0; i < mista.length; i++) {
            if (tulos.equals("")) tulos = mista[i] + "->" + minne[i];
            else tulos += ", "  + mista[i] + "->" + minne[i];
        }
        return "[" + tulos + "]";
    }
    
    public void pieniTesti(int n, int[] mista, int[] minne, boolean tulos) {
        String sisalto = kaarilista(mista, minne);
        boolean ulos = Main.onkoSyklia(n, mista, minne);
        assertTrue("Kun annetun verkon kaaret ovat "+sisalto+",\n"
                + "tulisi algoritmisi palauttaa '"+tulos+"', mutta se palauttaa '"+ulos+"'.", tulos == ulos);
    }
    
    public void suuriTesti(int n, int[] mista, int[] minne, boolean tulos) {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        boolean ulos = Main.onkoSyklia(n, mista, minne);
        assertTrue("Metodisi toimii väärin suurella syötteellä. Sen tulisi palauttaa '"+tulos+"', mutta se palauttaa '"+ulos+"'.", tulos == ulos);
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
    
    public void testaaPieniRandom(int n, int m, int seed, boolean tulos){
        int[] mista=new int[m];
        int[] mihin=new int[m];
        
        Random rng=new Random(seed);
        for(int i=0; i<m; i++){
            mista[i]=rng.getInt(1, n);
            mihin[i]=rng.getInt(1, n);
            while(mihin[i]==mista[i])
                mihin[i]=rng.getInt(1, n);
        }
        pieniTesti(n, mista, mihin, tulos);
    }
    
    public void testaaSuuriRandom(int n, int m, int seed, boolean tulos){
        int[] mista=new int[m];
        int[] mihin=new int[m];
        
        Random rng=new Random(seed);
        for(int i=0; i<m; i++){
            mista[i]=rng.getInt(1, n);
            mihin[i]=rng.getInt(1, n);
            while(mihin[i]==mista[i])
                mihin[i]=rng.getInt(1, n);
        }
        suuriTesti(n, mista, mihin, tulos);
    }
    
    @Test(timeout=5000)
    public void esimerkit() {
        pieniTesti(3, new int[] {1, 2, 3}, new int[] {2, 3, 1}, true);
        pieniTesti(3, new int[] {1, 2}, new int[] {2, 3}, false);
        pieniTesti(3, new int[] {1, 2, 1}, new int[] {2, 3, 3}, false);
        pieniTesti(4, new int[] {2, 3, 4}, new int[] {3, 4, 2}, true);
    }
    
    @Test(timeout=5000)
    public void pienet() {
        pieniTesti(4, new int[] {1, 2, 1, 3}, new int[] {2, 4, 3, 4}, false);
        pieniTesti(4, new int[] {1, 2, 1, 4}, new int[] {2, 4, 3, 3}, false);
        pieniTesti(4, new int[] {1, 2, 3, 4}, new int[] {2, 4, 1, 3}, true);
        
        pieniTesti(9, new int[] {1, 2, 3, 4, 6, 8, 9}, new int[] {2, 3, 4, 5, 7, 9, 5}, false);
        pieniTesti(4, new int[] {1, 2, 3, 4}, new int[] {2,3,4,2}, true);
        pieniTesti(4, new int[] {1, 2, 3, 4}, new int[] {2,3,4,3}, true);
        
        pieniTesti(4, new int[] {1, 1, 1, 2, 2, 3}, new int[] {2, 3, 4, 3, 4, 4}, false);
        pieniTesti(5, new int[] {1, 1, 1, 1, 2, 2, 2, 3, 3, 4}, new int[] {2, 3, 4, 5, 3, 4, 5, 4, 5, 5}, false);
        pieniTesti(5, new int[] {1, 1, 1, 1, 2, 2, 2, 3, 3, 4, 4}, new int[] {2, 3, 4, 5, 3, 4, 5, 4, 5, 5, 2}, true);
        
        testaaPieniRandom(10, 10, 78127878, false);
        testaaPieniRandom(10, 10, 781728711, true);
        testaaPieniRandom(10, 10, 801980811, false);
        testaaPieniRandom(10, 10, 999191919, false);
        testaaPieniRandom(10, 10, 112312314, true);
        testaaPieniRandom(10, 10, 133333337, true);
        testaaPieniRandom(10, 10, 1, true);
        testaaPieniRandom(10, 10, 111111111, false);
        testaaPieniRandom(10, 10, 562341211, false);
        testaaPieniRandom(10, 10, 12341234, true);
    }
    
    @Test(timeout=5000)
    public void suuri1() {
        int n = 100000;
        ArrayList<Integer> lol=new ArrayList();
        for(int i=1; i<=n; i++)
            lol.add(i);
        
        Collections.shuffle(lol);
        int[] mista = new int[n-1];
        int[] mihin = new int[n-1];
        
        for(int i=0; i<n-1; i++){
            mista[i]=lol.get(i);
            mihin[i]=lol.get(i+1);
        }

        suuriTesti(n, mista, mihin, false);
    }  
    
    
    @Test(timeout=5000)
    public void suuri2() {
        int n = 100000;
        ArrayList<Integer> lol=new ArrayList();
        for(int i=1; i<=n; i++)
            lol.add(i);
        
        Collections.shuffle(lol);
        int[] mista = new int[n-1];
        int[] mihin = new int[n-1];
        
        for(int i=0; i<n-1; i++){
            mista[i]=lol.get(i);
            mihin[i]=lol.get(i+1);
        }

        suuriTesti(n, mista, mihin, false);
    }  
    
    @Test(timeout=5000)
    public void suuri3() {
        testaaSuuriRandom(100000, 100000, 81726387, true);
    }  
    
    @Test(timeout=5000)
    public void suuri4() {
        testaaSuuriRandom(100000, 100000, 91237911, true);
    }  
    
    @Test(timeout=5000)
    public void suuri5() {
        testaaSuuriRandom(100000, 100000, 111112313, true);
    }  
    
    @Test(timeout=5000)
    public void suuri6() {
        int n=100000;
        int[] mista=new int[n-1];
        int[] mihin=new int[n-1];
        
        Random rng=new Random(71279791);
        
        for(int i=0; i<n-1; i++){
            mista[i]=i+1;
            mihin[i]=rng.getInt(i+2, n);
        }
        
        suuriTesti(n, mista, mihin, false);
    }  
    
    @Test(timeout=5000)
    public void suuri7() {
        int n=100000;
        int[] mista=new int[n-1];
        int[] mihin=new int[n-1];
        
        Random rng=new Random(471236811);
        
        for(int i=0; i<n-1; i++){
            mista[i]=i+1;
            mihin[i]=rng.getInt(i+2, n);
        }
        
        suuriTesti(n, mista, mihin, false);
    } 
    
    @Test(timeout=5000)
    public void suuri8() {
        int n=90000;
        int lisa=10000;
        int[] mista=new int[n-1 + lisa];
        int[] mihin=new int[n-1 + lisa];
        
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
        
        suuriTesti(n, mista, mihin, true);
    } 
    
    @Test(timeout=5000)
    public void suuri9() {
        int n=90000;
        int lisa=5000;
        int[] mista=new int[n-1 + lisa];
        int[] mihin=new int[n-1 + lisa];
        
        Random rng=new Random(8192811);
        
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
        
        suuriTesti(n, mista, mihin, true);
    } 
    
    @Test(timeout=5000)
    public void suuri10() {
        int n=90000;
        int lisa=5000;
        int[] mista=new int[n-1 + lisa];
        int[] mihin=new int[n-1 + lisa];
        
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
        
        suuriTesti(n, mista, mihin, false);
    } 
}

