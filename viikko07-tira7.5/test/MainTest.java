import org.junit.Test;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@Points("7.5")
public class MainTest {
    public void pieniTesti(int[] y, int[] v, int[] o, int[] autoX, int[] autoY, int tulos) {
        int ulos = Main.rikkinaisiaAutoja(y, v, o, autoX, autoY);
        assertTrue("Syötteellä\n"
                + "y=" + Arrays.toString(y)+"\n"
                + "v=" + Arrays.toString(v)+"\n"
                + "o=" + Arrays.toString(o)+"\n"
                + "autoX=" + Arrays.toString(autoX)+"\n"
                + "autoY=" + Arrays.toString(autoY)+"\n"
                + "kuuluisi palauttaa "+tulos+", mutta metodisi palauttaa " + ulos + ".",
                   tulos == ulos);
    }
    
    public void suuriTesti(int[] y, int[] v, int[] o, int[] autoX, int[] autoY, int tulos) {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        int ulos = Main.rikkinaisiaAutoja(y, v, o, autoX, autoY);
        assertTrue("Metodisi toimii väärin suurella syötteellä. Sen kuuluisi palauttaa "+tulos+", mutta se palauttaa " + ulos + ".",
                   tulos == ulos);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    @Test(timeout=1000)
    public void esimerkki() {
        int[] y = {1, 3, 5, 5};
        int[] v = {0, 1, 1, 2};
        int[] o = {3, 2, 4, 5};

        int[] autoX = {1, 3, 4, 1};
        int[] autoY = {1, 4, 1, 3};
        pieniTesti(y,v,o,autoX,autoY,2);
    }
    
    @Test(timeout=1000)
    public void pieni1() {
        int[] y = {4};
        int[] v = {0};
        int[] o = {5};

        int[] autoY = {4,4,4,4,4,4};
        int[] autoX = {0,1,2,3,4,5};
        pieniTesti(y,v,o,autoX,autoY,6);
    }
    
    @Test(timeout=1000)
    public void pieni2() {
        int[] y = {4,4};
        int[] v = {1,2};
        int[] o = {2,3};

        int[] autoY = {4};
        int[] autoX = {2};
        pieniTesti(y,v,o,autoX,autoY,1);
    }
    
    @Test(timeout=1000)
    public void pieni3() {
        int[] y = {0,0,0,0,0};
        int[] v = {0,1,2,3,4};
        int[] o = {1,2,3,4,5};

        int[] autoY = {0,0,0,0,0,0};
        int[] autoX = {0,1,2,3,4,5};
        pieniTesti(y,v,o,autoX,autoY,6);
    }
    
    @Test(timeout=1000)
    public void pieni4() {
        int[] y = {141278,  222,    222};
        int[] v = {1273,    1,      1000};
        int[] o = {3718212, 2000,   3000};

        int[] autoY = {222, 141278};
        int[] autoX = {1111, 3718212+100};
        pieniTesti(y,v,o,autoX,autoY,1);
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
    
    void testaaPieniRandom(int seed, int n, int m, int raja, int vast){
        Random rng=new Random(seed);
        
        int[] y=new int[n];
        int[] v=new int[n];
        int[] o=new int[n];
        
        int[] autoX=new int[m];
        int[] autoY=new int[m];
        
        HashSet<Long> onkojo = new HashSet();
        
        for(int i=0; i<n; i++){
            y[i]=rng.getInt(0, raja);
            
            int asd1=rng.getInt(0, raja-1);
            int asd2=rng.getInt(0, raja-1);
            
            v[i]=Math.min(asd1, asd2);
            o[i]=Math.max(asd1, asd2)+1;
        }
        
        for(int i=0; i<m; i++){
            autoX[i]=rng.getInt(0, raja);
            autoY[i]=rng.getInt(0, raja);
            while(onkojo.contains(((long)raja+1) * autoX[i] + autoY[i])){
                autoX[i]=rng.getInt(0, raja);
                autoY[i]=rng.getInt(0, raja);
            }
            onkojo.add(((long)raja+1) * autoX[i] + autoY[i]);
        }
        
        pieniTesti(y,v,o,autoX,autoY,vast);
    }
    
    void testaaSuuriRandom(int seed, int n, int m, int raja, int vast){
        Random rng=new Random(seed);
        
        int[] y=new int[n];
        int[] v=new int[n];
        int[] o=new int[n];
        
        int[] autoX=new int[m];
        int[] autoY=new int[m];
        
        HashSet<Long> onkojo = new HashSet();
        
        for(int i=0; i<n; i++){
            y[i]=rng.getInt(0, raja);
            
            int asd1=rng.getInt(0, raja-1);
            int asd2=rng.getInt(0, raja-1);
            
            v[i]=Math.min(asd1, asd2);
            o[i]=Math.max(asd1, asd2)+1;
        }
        
        for(int i=0; i<m; i++){
            autoX[i]=rng.getInt(0, raja);
            autoY[i]=rng.getInt(0, raja);
            while(onkojo.contains(((long)raja+1) * autoX[i] + autoY[i])){
                autoX[i]=rng.getInt(0, raja);
                autoY[i]=rng.getInt(0, raja);
            }
            onkojo.add(((long)raja+1) * autoX[i] + autoY[i]);
        }
        
        suuriTesti(y,v,o,autoX,autoY,vast);
    }
    
    @Test(timeout=1000)
    public void randomPienet() {
        testaaPieniRandom(989182911, 5, 5, 5, 2);
        testaaPieniRandom(89192192, 5, 5, 5, 2);
        testaaPieniRandom(891289111, 5, 5, 5, 2);
        testaaPieniRandom(133333337, 5, 5, 5, 3);
        testaaPieniRandom(819181191, 5, 5, 5, 1);
        
        testaaPieniRandom(128731123, 5, 10, 5, 2);
        testaaPieniRandom(1278813123, 5, 10, 5, 5);
        testaaPieniRandom(818278192, 5, 10, 5, 3);
        testaaPieniRandom(718279811, 5, 10, 5, 5);
        testaaPieniRandom(989182911, 5, 10, 5, 4);
        
        testaaPieniRandom(128731123, 10, 15, 10, 7);
        testaaPieniRandom(712873123, 10, 15, 10, 6);
        testaaPieniRandom(176237812, 10, 15, 10, 6);
        testaaPieniRandom(672371231, 10, 15, 10, 11);
        testaaPieniRandom(128129312, 10, 15, 10, 2);
    }
    
    @Test(timeout=5000)
    public void suuri1() {
        int n=100000;
        
        int[] y=new int[n];
        int[] v=new int[n];
        int[] o=new int[n];
        int[] autoX=new int[n];
        int[] autoY=new int[n];
        
        for(int i=0; i<n; i++){
            y[i]=0;
            v[i]=0;
            o[i]=i;
            autoX[i]=i;
            autoY[i]=0;
        }
        suuriTesti(y,v,o,autoX,autoY,100000);
    }
    
    @Test(timeout=5000)
    public void suuri2() {
        int n=100000;
        
        int[] y=new int[n];
        int[] v=new int[n];
        int[] o=new int[n];
        int[] autoX=new int[n];
        int[] autoY=new int[n];
        
        for(int i=0; i<n; i++){
            y[i]=0;
            v[i]=i;
            o[i]=2*i;
            autoX[i]=2*i;
            autoY[i]=0;
        }
        suuriTesti(y,v,o,autoX,autoY,100000);
    }
    
    @Test(timeout=5000)
    public void suuri3() {
        int n=100000;
        
        Random rng=new Random(71928739);
        
        int[] y=new int[n];
        int[] v=new int[n];
        int[] o=new int[n];
        int[] autoX=new int[n];
        int[] autoY=new int[n];
        
        HashSet<Long> onkojo = new HashSet();
        
        for(int i=0; i<n; i++){
            int asd1=rng.getInt(0, 100000);
            int asd2=rng.getInt(0, 100000);
            
            y[i]=i;
            v[i]=Math.min(asd1, asd2);
            o[i]=Math.max(asd1, asd2)+1;
            autoX[i]=rng.getInt(0, 100000);
            autoY[i]=rng.getInt(0, 100000);
            
            while(onkojo.contains(((long)100000+1) * autoX[i] + autoY[i])){
                autoX[i]=rng.getInt(0, 100000);
                autoY[i]=rng.getInt(0, 100000);
            }
            onkojo.add(((long)100000+1) * autoX[i] + autoY[i]);
        }
        suuriTesti(y,v,o,autoX,autoY,33559);
    }
    
    @Test(timeout=5000)
    public void suuri4() {
        int n=100000;
        
        Random rng=new Random(78127831);
        
        int[] y=new int[n];
        int[] v=new int[n];
        int[] o=new int[n];
        int[] autoX=new int[n];
        int[] autoY=new int[n];
        
        HashSet<Long> onkojo = new HashSet();
        
        for(int i=0; i<n; i++){
            int asd1=rng.getInt(0, 100000);
            int asd2=rng.getInt(0, 100000);
            
            y[i]=i;
            v[i]=Math.min(asd1, asd2);
            o[i]=Math.max(asd1, asd2)+1;
            autoX[i]=rng.getInt(0, 100000);
            autoY[i]=rng.getInt(0, 100000);
            
            while(onkojo.contains(((long)100000+1) * autoX[i] + autoY[i])){
                autoX[i]=rng.getInt(0, 100000);
                autoY[i]=rng.getInt(0, 100000);
            }
            onkojo.add(((long)100000+1) * autoX[i] + autoY[i]);
        }
        suuriTesti(y,v,o,autoX,autoY,33204);
    }
    
    @Test(timeout=5000)
    public void suuri5() {
        testaaSuuriRandom(38123812, 100000, 100000, 100000, 27423);
    }
    
    @Test(timeout=5000)
    public void suuri6() {
        testaaSuuriRandom(41278311, 100000, 100000, 50000, 46269);
    }
    
    @Test(timeout=5000)
    public void suuri7() {
        testaaSuuriRandom(37182712, 100000, 100000, 1000000000, 2);
    }
    
    @Test(timeout=5000)
    public void suuri8() {
        testaaSuuriRandom(71293811, 100000, 100000, 1000, 99049);
    }
    
    @Test(timeout=5000)
    public void suuri9() {
        testaaSuuriRandom(78281911, 100000, 100000, 10000, 88558);
    }
    
    @Test(timeout=5000)
    public void suuri10() {
        testaaSuuriRandom(142411414, 100000, 100000, 50000, 46297);
    }
    
    @Test(timeout=5000)
    public void suuri11() {
        int n=100000;
        
        Random rng=new Random(78127831);
        
        int[] y=new int[n];
        int[] v=new int[n];
        int[] o=new int[n];
        int[] autoX=new int[n];
        int[] autoY=new int[n];
        
        HashSet<Long> onkojo = new HashSet();
        
        ArrayList<Integer> asd=new ArrayList();
        for(int i=0; i<100000; i++)
            asd.add(i);
        
        Collections.shuffle(asd);
        for(int i=0; i<n; i++)
            autoX[i]=asd.get(i);
        
        for(int i=0; i<n; i++){
            int asd1=rng.getInt(0, 100000);
            int asd2=rng.getInt(0, 100000);
            
            y[i]=0;
            v[i]=Math.min(asd1, asd2);
            o[i]=Math.max(asd1, asd2)+1;
        }
        suuriTesti(y,v,o,autoX,autoY,99997);
    }
}

