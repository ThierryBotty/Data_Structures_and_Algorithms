import org.junit.Test;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

@Points("12.4")
public class MainTest {
    private String kaarilista(int[] mista, int[] minne) {
        String tulos = "";
        for (int i = 0; i < mista.length; i++) {
            if (tulos.equals("")) tulos = mista[i] + "-" + minne[i];
            else tulos += ", "  + mista[i] + "-" + minne[i];
        }
        return "[" + tulos + "]";
    } 
    
    public void pieniTesti(int n, int[] mista, int[] minne, int[] poistot, int[] tulos) {
        String sisalto = kaarilista(mista, minne);
        int[] ulos = Main.komponentteja(n, mista, minne, poistot);
        assertTrue("Kun kaupunkeja on " + n + " ja yhteydet ovat " + sisalto +
                   ",\nniin poistoilla " + Arrays.toString(poistot) + " olisi"
                + " vastaustaulukon näytettävä tältä:\n "+Arrays.toString(tulos)+","
                + " mutta algoritmisi palauttaa:\n "+Arrays.toString(ulos)+".",
                   Arrays.equals(tulos, ulos));
    }
    
    public void suuriTesti(int n, int[] mista, int[] minne, int[] poistot, int[] tulos) {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        int[] ulos = Main.komponentteja(n, mista, minne, poistot);
        assertTrue("Metodi toimii väärin suurella syötteellä.",
                   Arrays.equals(tulos, ulos));
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    @Test(timeout=1000)
    public void esimerkit() {
        pieniTesti(3, new int[] {1,2,3}, new int[] {2,3,1}, new int[] {0,1,2}, new int[] {1,2,3});
        pieniTesti(5, new int[] {1,1,1,2,2,3,4}, new int[] {2,3,4,3,4,4,5}, new int[] {2,3,6,1,4}, new int[] {1,1,2,2,3});
        pieniTesti(5, new int[] {1,2,3,4}, new int[] {2,3,4,5}, new int[] {2,0,3,1}, new int[] {2,3,4,5});
        pieniTesti(5, new int[] {1,2,3,4,5}, new int[] {2,3,4,5,1}, new int[] {2,0,3,1}, new int[] {1,2,3,4});
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
    
    public void testaaPieniRandom(int n, int m, int seed, int[] vast) {
        int[] mista=new int[m];
        int[] minne=new int[m];
        int[] poistot=new int[m];
        
        HashSet<Integer> lol=new HashSet();
        Random rng=new Random(seed);
        
        for(int i=0; i<m; i++){
            mista[i]=rng.getInt(1, n);
            minne[i]=rng.getInt(1, n);
            while(mista[i]==minne[i])
                minne[i]=rng.getInt(1, n);
            while(lol.contains(1000000*mista[i]+minne[i])){
                mista[i]=rng.getInt(1, n);
                minne[i]=rng.getInt(1, n);
                while(mista[i]==minne[i])
                    minne[i]=rng.getInt(1, n);
            }
            lol.add(1000000*mista[i]+minne[i]);
            lol.add(1000000*minne[i]+mista[i]);
        }
        
        for(int i=0; i<m; i++)
            poistot[i]=i;
        
        for(int i=0; i<m; i++){
            int j=rng.getInt(i, m-1);
            int temp=poistot[i];
            poistot[i]=poistot[j];
            poistot[j]=temp;
        }
        
        pieniTesti(n,mista,minne,poistot,vast);
    }
    
    @Test(timeout=2000)
    public void pienet() {
        pieniTesti(5, new int[] {1,1,1,1}, new int[] {2,3,4,5}, new int[] {0,1,2}, new int[] {2,3,4});
        pieniTesti(5, new int[] {1,1,1,1,2,3,4}, new int[] {2,3,4,5,3,4,5}, new int[] {0,1,2,3,4,5,6}, new int[] {1,1,1,2,3,4,5});
        pieniTesti(7, new int[] {1,2,3,4,5,6,7}, new int[] {2,3,4,5,6,7,4}, new int[] {0,6,1,5,2,4,3}, new int[] {2,2,3,4,5,6,7});
        pieniTesti(4, new int[] {1,3}, new int[] {2,4}, new int[] {0,1}, new int[] {3,4});
        pieniTesti(5, new int[] {1,1,1,1,2,2,2,3,3,4}, new int[] {2,3,4,5,3,4,5,4,5,5}, new int[] {7,2,8,4,6,9,3,1,5,0}, new int[] {1,1,1,1,1,1,2,3,4,5});
        pieniTesti(7, new int[] {1,1,2,3,4,4,5,6}, new int[] {2,3,4,4,5,6,7,7}, new int[] {0,2,4,6,3,5,1,7}, new int[] {1,2,2,3,4,5,6,7});
        
        testaaPieniRandom(5, 8, 1239182912, new int[] {1, 1, 1, 1, 2, 3, 4, 5});
        testaaPieniRandom(5, 8, 561267157, new int[] {1, 1, 1, 1, 2, 3, 4, 5});
        testaaPieniRandom(5, 8, 712878112, new int[] {1, 1, 1, 1, 2, 3, 4, 5});
        testaaPieniRandom(5, 8, 1231235611, new int[] {1, 1, 1, 2, 2, 3, 4, 5});
        testaaPieniRandom(5, 8, 451667191, new int[] {1, 1, 1, 2, 2, 3, 4, 5});
        
        testaaPieniRandom(6, 10, 1239182912, new int[] {1, 2, 2, 2, 2, 3, 4, 4, 5, 6});
        testaaPieniRandom(6, 10, 561267157, new int[] {1, 1, 1, 1, 1, 2, 3, 4, 5, 6});
        testaaPieniRandom(6, 10, 712878112, new int[] {1, 1, 2, 2, 2, 2, 3, 4, 5, 6});
        testaaPieniRandom(6, 10, 1231235611, new int[] {1, 1, 1, 1, 2, 3, 3, 4, 5, 6});
        testaaPieniRandom(6, 10, 451667191, new int[] {1, 1, 1, 2, 3, 3, 3, 4, 5, 6});
        
        testaaPieniRandom(10, 15, 1239182912, new int[] {1, 2, 2, 3, 3, 3, 3, 4, 4, 5, 6, 7, 8, 9, 10});
        testaaPieniRandom(10, 15, 561267157, new int[] {1, 1, 1, 1, 1, 2, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        testaaPieniRandom(10, 15, 712878112, new int[] {2, 2, 2, 2, 2, 3, 3, 3, 4, 5, 6, 7, 8, 9, 10});
        testaaPieniRandom(10, 15, 1231235611, new int[] {1, 1, 1, 1, 1, 2, 3, 4, 4, 5, 6, 7, 8, 9, 10});
        testaaPieniRandom(10, 15, 451667191, new int[] {1, 1, 1, 1, 2, 2, 3, 3, 4, 5, 6, 7, 8, 9, 10});
    }
    
    @Test(timeout=5000)
    public void suuri1() {
        int n=100000;
        int[] mista=new int[n-1];
        int[] minne=new int[n-1];
        int[] poistot=new int[n-1];
        for(int i=0; i<n-1; i++){
            mista[i]=i+2;
            minne[i]=i+1;
        }
        
        Random rng=new Random(561872811);
        
        for(int i=0; i<n-1; i++)
            poistot[i]=i;
        
        for(int i=0; i<n-2; i++){
            int j=rng.getInt(i, n-2);
            int temp=poistot[i];
            poistot[i]=poistot[j];
            poistot[j]=temp;
        }
        
        int[] vast=new int[n-1];
        for(int i=0; i<n-1; i++)
            vast[i]=i+2;
        
        suuriTesti(n,mista,minne,poistot,vast);
    }
    
    @Test(timeout=5000)
    public void suuri2() {
        int n=100000;
        int[] mista=new int[n-1];
        int[] minne=new int[n-1];
        int[] poistot=new int[n/2];
        for(int i=0; i<n-1; i++){
            mista[i]=i+1;
            minne[i]=i+2;
        }
        
        Random rng=new Random(789797979);
        
        for(int i=0; i<n/2; i++)
            poistot[i]=i*2;
        
        for(int i=0; i<n/2; i++){
            int j=rng.getInt(i, n/2-1);
            int temp=poistot[i];
            poistot[i]=poistot[j];
            poistot[j]=temp;
        }
        
        int[] vast=new int[n/2];
        for(int i=0; i<n/2; i++)
            vast[i]=i+2;
        
        suuriTesti(n,mista,minne,poistot,vast);
    }
    
    @Test(timeout=5000)
    public void suuri3() {
        int n=444;
        int[] mista=new int[443*444/2];
        int[] minne=new int[443*444/2];
        int[] poistot=new int[443*444/2];
        int[] vast=new int[443*444/2];
        
        int asd=0;
        
        HashMap<Long, Integer> lol=new HashMap();
        
        for(int i=1; i<=444; i++)
            for(int e=i+1; e<=444; e++){
                mista[asd]=i;
                minne[asd]=e;
                lol.put(1000000l*e+i, asd);
                asd++;
            }
        
        asd=0;
        int k=1;
        for(int i=444; i>=1; i--){
            for(int e=i-1; e>=1; e--){
                if(e==1)
                    k++;
                vast[asd]=k;
                poistot[asd]=lol.get(1000000l*i+e);
                asd++;
            }
        }
        
        
        suuriTesti(n,mista,minne,poistot,vast);
    }
    
    @Test(timeout=5000)
    public void suuri4() {
        int n=100000;
        int[] mista=new int[n];
        int[] minne=new int[n];
        int[] poistot=new int[n];
        int[] vast=new int[n];
        for(int i=0; i<n-1; i++){
            mista[i]=i+2;
            minne[i]=i+1;
        }
        
        mista[99999]=100000;
        minne[99999]=50001;
        
        Random rng=new Random(561872811);
        
        for(int i=0; i<n-1; i++)
            poistot[i]=i;
        
        for(int i=0; i<49999; i++){
            int j=rng.getInt(i, 49998);
            int temp=poistot[i];
            poistot[i]=poistot[j];
            poistot[j]=temp;
            vast[i]=i+2;
        }
        
        poistot[49999]=99999;
        poistot[99999]=49999;
        vast[49999]=50000;
        for(int i=50000; i<100000; i++){
            int j=rng.getInt(i, 99999);
            int temp=poistot[i];
            poistot[i]=poistot[j];
            poistot[j]=temp;
            vast[i]=i+1;
        }
        
        suuriTesti(n,mista,minne,poistot,vast);
    }
}


