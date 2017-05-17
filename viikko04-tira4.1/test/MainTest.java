import org.junit.Test;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;
import java.util.Arrays;

@Points("4.1")
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
    
    public void pieniTesti(int[] kivet, int tulos) {
        int ulos = Main.montakoKivea(kivet);
        String s=Arrays.toString(kivet);
        assertTrue("Kun uolevi törmää kiviin " + s + " on hänellä lopuksi " 
                    + tulos + " kiveä, mutta ohjelmasi palauttaa "+ulos+".", 
                    tulos == ulos);
    }
    
    public void suuriTesti(int[] kivet, int tulos) {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        int ulos = Main.montakoKivea(kivet);
        String s=Arrays.toString(kivet);
        assertTrue("Metodisi toimii väärin suurella syötteellä. Sen kuuluisi"
                    + " palauttaa "+tulos+", mutta se palauttaa "+ulos+".", 
                    tulos == ulos);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    @Test(timeout=5000)
    public void esimerkit() {
        pieniTesti(new int[]{1,1,1,1}, 0);
        pieniTesti(new int[]{1,1,1}, 1);
        pieniTesti(new int[]{1,2,3,3,2,1}, 0);
        pieniTesti(new int[]{1,2,3,2,1}, 5);
    }
    
    @Test(timeout=5000)
    public void pienet() {
        pieniTesti(new int[]{}, 0);
        pieniTesti(new int[]{1}, 1);
        pieniTesti(new int[]{1,2}, 2);
        pieniTesti(new int[]{1,2,3,4,5,5,4,5,5,4,5,5,4,3,2,1}, 0);
        pieniTesti(new int[]{1,2,3,1,2,3}, 6);
        pieniTesti(new int[]{1,1,2,2,3,3,1}, 1);
        pieniTesti(new int[]{1,2,2,3,3,1}, 0);
        pieniTesti(new int[]{1,1,1,1,1,1,2,2,1}, 1);
    }
    
    @Test(timeout=5000)
    public void suuri1() {
        int n=100000;
        int[] kivet=new int[n];
        for(int i=0; i<n; i++){
            if(i<n/2)
                kivet[i]=i;
            else
                kivet[i]=n-i-1;
        }
        suuriTesti(kivet, 0);
    }
    
    @Test(timeout=5000)
    public void suuri2() {
        int n=100000;
        int[] kivet=new int[n];
        for(int i=0; i<n; i++){
            kivet[i]=1;
        }
        suuriTesti(kivet, 0);
    }
    
    @Test(timeout=5000)
    public void suuri3() {
        int n=100000;
        int[] kivet=new int[n];
        for(int i=0; i<n; i++){
            kivet[i]=i;
        }
        suuriTesti(kivet, 100000);
    }
    
    @Test(timeout=5000)
    public void suuri4() {
        int n=100000;
        
        Random rng=new Random(12341231);
        
        int[] kivet=new int[n];
        for(int i=0; i<n; i++){
            kivet[i]=rng.getInt(0, 10);
        }
        suuriTesti(kivet, 81824);
    }
    
    @Test(timeout=5000)
    public void suuri5() {
        int n=100000;
        
        Random rng=new Random(714516134);
        
        int[] kivet=new int[n];
        for(int i=0; i<n; i++){
            kivet[i]=rng.getInt(0, 3);
        }
        suuriTesti(kivet, 49598);
    }
    
    @Test(timeout=5000)
    public void suuri6() {
        int n=100000;
        
        Random rng=new Random(1333333337);
        
        int[] kivet=new int[n];
        for(int i=0; i<n; i++){
            kivet[i]=rng.getInt(0, 1000000000);
        }
        suuriTesti(kivet, 100000);
    }    
}

