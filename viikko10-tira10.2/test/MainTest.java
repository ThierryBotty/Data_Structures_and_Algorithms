import org.junit.Test;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;
import java.util.Arrays;

@Points("10.2")
public class MainTest {
    
    private String kaarilista(int[] mista, int[] minne) {
        String tulos = "";
        for (int i = 0; i < mista.length; i++) {
            if (tulos.equals("")) tulos = mista[i] + "->" + minne[i];
            else tulos += ", "  + mista[i] + "->" + minne[i];
        }
        return "[" + tulos + "]";
    }
    
    public void pieniTesti(int n, int[] mista, int[] minne, int tulos) {
        String sisalto = kaarilista(mista, minne);
        int ulos = Main.komponentteja(n, mista, minne);
        assertTrue("Kun n="+n+", ja annetun verkon kaaret ovat "+sisalto+",\n"
                + "on yhtenäisiä komponentteja "+tulos+", mutta metodisi palauttaa "+ulos+".", tulos == ulos);
    }
    
    public void suuriTesti(int n, int[] mista, int[] minne, int tulos) {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        int ulos = Main.komponentteja(n, mista, minne);
        assertTrue("Metodisi toimii väärin suurella syötteellä. Sen tulisi palauttaa "+tulos+", mutta se palauttaa "+ulos+".", tulos == ulos);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    @Test(timeout=5000)
    public void esimerkit() {
        pieniTesti(3, new int[] {1,2,3}, new int[] {2,3,1}, 1);
        pieniTesti(3, new int[] {1,1}, new int[] {2,3}, 3);
        pieniTesti(3, new int[] {1,1,2}, new int[] {2,3,3}, 3);
        pieniTesti(3, new int[] {1,1,2}, new int[] {2,3,1}, 2);
    }
    
    @Test(timeout=5000)
    public void pienet() {
        pieniTesti(4, new int[] {1,3}, new int[] {2,4}, 4);
        pieniTesti(4, new int[] {1,3,2}, new int[] {2,4,1}, 3);
        pieniTesti(4, new int[] {1,3,2,4}, new int[] {2,4,1,3}, 2);
        pieniTesti(4, new int[] {1,3,2,4,2}, new int[] {2,4,1,3,3}, 2);
        pieniTesti(4, new int[] {1,3,2,4,2,4}, new int[] {2,4,1,3,3,1}, 1);
    
        pieniTesti(6, new int[] {1,1,1,1,1}, new int[] {2,3,4,5,6}, 6);
        pieniTesti(6, new int[] {1,1,1,1,1,6}, new int[] {2,3,4,5,6,1}, 5);
        pieniTesti(6, new int[] {1,1,1,1,1,6,5}, new int[] {2,3,4,5,6,1,2}, 5);
        pieniTesti(6, new int[] {1,1,1,1,1,6,5,2}, new int[] {2,3,4,5,6,1,2,3}, 5);
        pieniTesti(6, new int[] {1,1,1,1,1,6,5,2,5}, new int[] {2,3,4,5,6,1,2,3,3}, 5);
        pieniTesti(6, new int[] {1,1,1,1,1,6,5,2,5,3}, new int[] {2,3,4,5,6,1,2,3,3,5}, 3);
        
        pieniTesti(6, new int[] {}, new int[] {}, 6);
        pieniTesti(6, new int[] {3}, new int[] {5}, 6);
        pieniTesti(6, new int[] {3,5}, new int[] {5,3}, 5);
        pieniTesti(6, new int[] {3,5,2}, new int[] {5,3,4}, 5);
        pieniTesti(6, new int[] {3,5,2,6}, new int[] {5,3,4,4}, 5);
        pieniTesti(6, new int[] {3,5,2,6,4}, new int[] {5,3,4,4,3}, 5);
        pieniTesti(6, new int[] {3,5,2,6,4,5}, new int[] {5,3,4,4,3,1}, 5);
        pieniTesti(6, new int[] {3,5,2,6,4,5,1}, new int[] {5,3,4,4,3,1,2}, 2);
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
    
    public void testaaPieniRandom(int n, int m, int seed, int tulos){
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
    
    @Test(timeout=5000)
    public void pienetRandom() {
        testaaPieniRandom(10,10,187239811,10);
        testaaPieniRandom(10,10,716278681,10);
        testaaPieniRandom(10,10,871298791,10);
        testaaPieniRandom(10,10,123413111,8);
        testaaPieniRandom(10,10,671711177,9);
        
        testaaPieniRandom(5,10,761871811,1);
        testaaPieniRandom(5,10,223980111,1);
        testaaPieniRandom(5,10,991981911,3);
        testaaPieniRandom(5,10,182791111,3);
        testaaPieniRandom(5,10,817211221,3);
        testaaPieniRandom(5,10,112311233,2);
        testaaPieniRandom(5,10,412414441,3);
        testaaPieniRandom(5,10,123141555,1);
        testaaPieniRandom(5,10,123141114,2);
        testaaPieniRandom(5,10,661661611,2);
        
        testaaPieniRandom(10,20,761871811,3);
        testaaPieniRandom(10,20,223980111,2);
        testaaPieniRandom(10,20,991981911,5);
        testaaPieniRandom(10,20,182791111,8);
        testaaPieniRandom(10,20,817211221,5);
        testaaPieniRandom(10,20,112311233,4);
        testaaPieniRandom(10,20,412414441,7);
        testaaPieniRandom(10,20,123141555,8);
        testaaPieniRandom(10,20,123141114,5);
        testaaPieniRandom(10,20,661661611,4);
    }
    
    public void testaaSuuriRandom(int n, int m, int seed, int tulos){
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
    public void suuri1() {
        testaaSuuriRandom(100000, 100000, 712683718, 99926);
    }
    
    @Test(timeout=5000)
    public void suuri2() {
        testaaSuuriRandom(100000, 100000, 123451566, 99984);
    }
    
    @Test(timeout=5000)
    public void suuri3() {
        testaaSuuriRandom(100000, 100000, 918123111, 99974);
    }
    
    @Test(timeout=5000)
    public void suuri4() {
        testaaSuuriRandom(100000, 150000, 712683718, 66725);
    }
    
    @Test(timeout=5000)
    public void suuri5() {
        testaaSuuriRandom(100000, 150000, 123451566, 65919);
    }
    
    @Test(timeout=5000)
    public void suuri6() {
        testaaSuuriRandom(100000, 150000, 918123111, 65546);
    }
    
    @Test(timeout=5000)
    public void suuri7() {
        testaaSuuriRandom(100000, 200000, 712683718, 36578);
    }
    
    @Test(timeout=5000)
    public void suuri8() {
        testaaSuuriRandom(100000, 200000, 123451566, 36449);
    }
    
    @Test(timeout=5000)
    public void suuri9() {
        testaaSuuriRandom(100000, 200000, 918123111, 36225);
    }
    
    @Test(timeout=5000)
    public void suuri10() {
        int[] mista=new int[100000];
        int[] mihin=new int[100000];
        
        for(int i=0; i<100000-1; i++){
            mista[i]=i+1;
            mihin[i]=(i+2);
        }
        
        mista[99999]=100000;
        mihin[99999]=1;
        
        suuriTesti(100000, mista, mihin, 1);
    }
    
    @Test(timeout=5000)
    public void suuri11() {
        int[] mista=new int[100001];
        int[] mihin=new int[100001];
        
        for(int i=0; i<100000-1; i++){
            mista[i]=i+1;
            mihin[i]=(i+2);
        }
        
        mista[99999]=57811;
        mihin[99999]=12;
        
        mista[100000]=88888;
        mihin[100000]=65761;
        
        suuriTesti(100000, mista, mihin, 19074);
    }
}


