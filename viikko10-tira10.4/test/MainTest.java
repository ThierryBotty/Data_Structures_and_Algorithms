import org.junit.Test;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;
import java.util.Arrays;

@Points("10.4")
public class MainTest {    
    private String kaarilista(int[] mista, int[] minne, boolean[] mutaa) {
        String tulos = "";
        for (int i = 0; i < mista.length; i++) {
            String M = "";
            if(mutaa[i])
                M = "(T)";
            else
                M = "(F)";
            
            if (tulos.equals("")) tulos = mista[i] + "-"+M+"-" + minne[i];
            else tulos += ", "  + mista[i] + "-"+M+"-" + minne[i];
        }
        return "[" + tulos + "]";
    }
    
    public void pieniTesti(int n, int[] mista, int[] minne, boolean[] mutaa, int tulos) {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        String sisalto = kaarilista(mista, minne, mutaa);
        int ulos = Main.lyhinReitti(n, mista, minne, mutaa);
        assertTrue("Kun n="+n+", ja annetun verkon kaaret ovat "+sisalto+",\n"
                + "on lyhimmän reitin pituus "+tulos+", mutta metodisi palauttaa "+ulos+".", tulos == ulos);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    public void suuriTesti(int n, int[] mista, int[] minne, boolean[] mutaa, int tulos) {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        int ulos = Main.lyhinReitti(n, mista, minne, mutaa);
        assertTrue("Metodisi toimii väärin suurella syötteellä. Sen tulisi palauttaa "+tulos+", mutta se palauttaa "+ulos+".", tulos == ulos);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    @Test(timeout=5000)
    public void esimerkit() {
        pieniTesti(4, 
                   new int[]{1,2,3,1},
                   new int[]{2,3,4,3},
                   new boolean[]{false, false, false, true},
                   3);
        
        pieniTesti(3,
                   new int[]{1,2},
                   new int[]{2,3},
                   new boolean[]{true, false},
                   -1);
        
        pieniTesti(3,
                   new int[]{1,2},
                   new int[]{2,3},
                   new boolean[]{false, true},
                   2);
        
        pieniTesti(4, 
                   new int[]{1,2,1,3},
                   new int[]{2,4,3,2},
                   new boolean[]{false, false, true, true},
                   2);
        
        pieniTesti(3,
                   new int[]{1,1,2},
                   new int[]{2,2,3},
                   new boolean[]{false, true, false},
                   2);
    }
    
    
    @Test(timeout=5000)
    public void pienet() {
        pieniTesti(5,
                   new int[]{1,1,1,2,3,4},
                   new int[]{2,3,4,5,5,5},
                   new boolean[]{true, true, true, false, true, false},
                   2);
        
        pieniTesti(5,
                   new int[]{1,1,1,2,3,4},
                   new int[]{2,3,4,5,5,5},
                   new boolean[]{true, true, true, true, false, false},
                   2);
        
        pieniTesti(5,
                   new int[]{1,1,1,2,3},
                   new int[]{2,3,4,5,5},
                   new boolean[]{true, true, false, true, false},
                   2);
        
        pieniTesti(5,
                   new int[]{1,1,1,2,3},
                   new int[]{2,3,4,5,5},
                   new boolean[]{true, true, false, false, false},
                   -1);
        
        
        pieniTesti(6,
                   new int[]{1,2,6,4,5,3},
                   new int[]{2,6,4,5,3,1},
                   new boolean[]{true, false, false, false, false, false},
                   4);
        
        pieniTesti(6,
                   new int[]{1,2,6,4,5,3},
                   new int[]{2,6,4,5,3,1},
                   new boolean[]{true, true, false, false, false, false},
                   2);
        
        pieniTesti(5,
                   new int[]{1,1,2,3,4},
                   new int[]{2,3,4,4,5},
                   new boolean[]{false, false, true, false, false, false},
                   3);
        
        pieniTesti(5,
                   new int[]{1,1,2,3,4},
                   new int[]{2,3,4,4,5},
                   new boolean[]{false, false, false, true, false, false},
                   3);
        
        pieniTesti(5,
                   new int[]{1,1,2,3,4},
                   new int[]{2,3,4,4,5},
                   new boolean[]{false, false, true, true, false, false},
                   -1);
        
        pieniTesti(6,
                   new int[]{1,1,2,4,3,6},
                   new int[]{2,3,4,5,5,5},
                   new boolean[]{false, false, false, false, true,false},
                   4);
        
        pieniTesti(6,
                   new int[]{1,1,2,4,3,6},
                   new int[]{2,3,4,5,5,5},
                   new boolean[]{true, true, true, true, true, true},
                   3);
    }
    
    static class Random {
        private long val;
        private long mul=16807;
        private long mod=((long)1<<31)-1;
        private long get(){
            val=(val*mul)%mod;
            return val;
        }
        public boolean getBool(){
            return (getInt(0,1)==1);
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
        boolean[] mutaa=new boolean[m];
        
        Random rng=new Random(seed);
        for(int i=0; i<m; i++){
            mista[i]=rng.getInt(1, n);
            mihin[i]=rng.getInt(1, n);
            mutaa[i]=rng.getBool();
            
            while(mihin[i]==mista[i])
                mihin[i]=rng.getInt(1, n);
        }
        pieniTesti(n, mista, mihin, mutaa, tulos);
    }
    
    public void testaaSuuriRandom(int n, int m, int seed, int tulos){
        int[] mista=new int[m];
        int[] mihin=new int[m];
        boolean[] mutaa=new boolean[m];
        
        Random rng=new Random(seed);
        for(int i=0; i<m; i++){
            mista[i]=rng.getInt(1, n);
            mihin[i]=rng.getInt(1, n);
            mutaa[i]=rng.getBool();
            
            while(mihin[i]==mista[i])
                mihin[i]=rng.getInt(1, n);
        }
        suuriTesti(n, mista, mihin, mutaa, tulos);
    }
    
    @Test(timeout=5000)
    public void pienetRandom() {
        testaaPieniRandom(5, 7, 187293812, 2);
        testaaPieniRandom(5, 7, 162376471, 2);
        testaaPieniRandom(5, 7, 421827192, 1);
        testaaPieniRandom(5, 7, 789127391, 2);
        testaaPieniRandom(5, 7, 123412341, 2);
        testaaPieniRandom(10, 15, 187293812, 1);
        testaaPieniRandom(10, 15, 162376471, 4);
        testaaPieniRandom(10, 15, 421827192, 4);
        testaaPieniRandom(10, 15, 789127391, 2);
        testaaPieniRandom(10, 15, 123412341, -1);
        testaaPieniRandom(15, 15, 187293812, 2);
        testaaPieniRandom(15, 15, 162376471, -1);
        testaaPieniRandom(15, 15, 421827192, -1);
        testaaPieniRandom(15, 15, 789127391, -1);
        testaaPieniRandom(15, 15, 856362611, 3);
    }
    
    
    @Test(timeout=5000)
    public void suuri1() {
        int n=100000;
        int mista[] = new int[100000];
        int minne[] = new int[100000];
        boolean mutaa[] = new boolean[100000];
        mutaa[0]=true;
        mista[0]=1;
        minne[0]=2;
        mista[1]=2;
        minne[1]=n;
        mista[2]=n;
        minne[2]=3;
        mista[3]=1;
        minne[3]=n-1;
        
        for(int i=3; i<=n-2; i++){
            mista[3-2+i]=i;
            minne[3-2+i]=i+1;
        }
        
        suuriTesti(n, mista, minne, mutaa, 99998);
    }
    
    @Test(timeout=5000)
    public void suuri2() {
        int n=100000;
        int mista[] = new int[100000];
        int minne[] = new int[100000];
        boolean mutaa[] = new boolean[100000];
        mista[0]=1;
        minne[0]=2;
        mista[1]=2;
        minne[1]=n;
        mista[2]=n;
        minne[2]=3;
        mista[3]=1;
        minne[3]=n-1;
        
        for(int i=3; i<=n-2; i++){
            mista[3-2+i]=i;
            minne[3-2+i]=i+1;
        }
        
        suuriTesti(n, mista, minne, mutaa, 2);
    }
    
    @Test(timeout=5000)
    public void suuri3() {
        testaaSuuriRandom(100000, 200000, 412341231, 15);        
    }
    
    @Test(timeout=5000)
    public void suuri4() {
        testaaSuuriRandom(100000, 200000, 141283102, 10);
    }
    
    @Test(timeout=5000)
    public void suuri5() {
        testaaSuuriRandom(100000, 200000, 888188181, 14);
    }
    
    @Test(timeout=5000)
    public void suuri6() {
        testaaSuuriRandom(100000, 200000, 771672811, 12);
    }
    
    @Test(timeout=5000)
    public void suuri7() {
        testaaSuuriRandom(100000, 200000, 999999999, 11);
    }
    
    @Test(timeout=5000)
    public void suuri8() {
        testaaSuuriRandom(100000, 150000, 412341231, -1);
    }
    
    @Test(timeout=5000)
    public void suuri9() {
        testaaSuuriRandom(100000, 150000, 141283102, 21);
    }
    
    @Test(timeout=5000)
    public void suuri10() {
        testaaSuuriRandom(100000, 150000, 888188181, -1);
    }
    
    @Test(timeout=5000)
    public void suuri11() {
        testaaSuuriRandom(100000, 150000, 771672811, 22);
    }
    
    @Test(timeout=5000)
    public void suuri12() {
        testaaSuuriRandom(100000, 150000, 999999999, 14);
    }
    
    
}



