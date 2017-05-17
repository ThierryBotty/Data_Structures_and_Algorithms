import org.junit.Test;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

@Points("11.4")
public class MainTest {
    
    private String kaarilista(int[] mista, int[] minne, long[] matka) {
        String tulos = "";
        for (int i = 0; i < mista.length; i++) {
            if (tulos.equals("")) tulos = mista[i] + "-" + minne[i] + "(" + matka[i] + ")";
            else tulos += ", "  + mista[i] + "-" + minne[i] + "(" + matka[i] + ")";
        }
        return "[" + tulos + "]";
    }    
    
    public void pieniTesti(int n, int[] mista, int[] minne, long[] matka, long tulos) {
        String sisalto = kaarilista(mista, minne, matka);
        long ulos = Main.yhteysaika(n, mista, minne, matka);
        assertTrue("Kun koneita on " + n + " ja yhteydet muodostetaan seuraavasti " + sisalto +
                   ", on haettu vastaus " + tulos +
                   ", mutta metodisi antaa " + ulos + ".",
                   tulos == ulos);
    }
    
    public void suuriTesti(int n, int[] mista, int[] minne, long[] matka, long tulos) {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        long uusi = Main.yhteysaika(n, mista, minne, matka);
        assertTrue("Metodisi toimii väärin suurella syötteellä. Se palauttaa "+uusi+" kun pitäisi palauttaa "+tulos+".",
                   tulos == uusi);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    @Test(timeout=5000)
    public void esimerkit() {
        pieniTesti(4, new int[] {1,2,3}, new int[] {2,3,4}, new long[] {1,2,1}, 2);
        pieniTesti(4, new int[] {1,1,2,3}, new int[] {2,3,4,4}, new long[] {1,5,8,7}, 7);
        pieniTesti(5, new int[] {1,2,2,3,4}, new int[] {2,3,4,5,5}, new long[] {10,1,8,1,9}, 10);
        pieniTesti(5, new int[] {1,2,2,3,4}, new int[] {2,3,4,5,5}, new long[] {1,1,8,1,9}, 1);
    }
    
    @Test(timeout=5000)
    public void pienet() {
        pieniTesti(2, new int[] {1}, new int[] {2}, new long[] {5}, 5);
        pieniTesti(2, new int[] {2}, new int[] {1}, new long[] {5}, 5);
        pieniTesti(3, new int[] {1}, new int[] {3}, new long[] {5}, 5);


        pieniTesti(3, new int[] {2, 2}, new int[] {1, 3}, new long[] {1, 1}, 1);

        pieniTesti(4, new int[] {1, 1, 2, 3}, new int[] {2, 3, 4, 4}, new long[] {3, 4, 4, 3}, 4);
        pieniTesti(4, new int[] {1, 1, 2, 3}, new int[] {2, 3, 4, 4}, new long[] {3, 4, 3, 4}, 3);
        pieniTesti(4, new int[] {1, 1, 2, 3}, new int[] {2, 3, 4, 4}, new long[] {3, 4, 1, 4}, 3);

        pieniTesti(5, new int[] {1}, new int[] {5}, new long[] {10}, 10);
        pieniTesti(5, new int[] {1, 1, 2, 3, 4}, new int[] {5, 2, 3, 4, 5}, new long[] {10, 1, 1, 1, 1}, 1);
        pieniTesti(5, new int[] {1, 1, 2, 3, 4}, new int[] {5, 2, 3, 4, 5}, new long[] {10, 2, 2, 2, 2}, 2);
        pieniTesti(5, new int[] {1, 1, 2, 3, 4}, new int[] {5, 2, 3, 4, 5}, new long[] {10, 3, 3, 3, 3}, 3);        
        
        pieniTesti(4, new int[] {1, 2, 3}, new int[] {2, 3, 4}, new long[] {1000000000000000000l, 1000000000000000000l, 1000000000000000000l}, 1000000000000000000l);
        
        pieniTesti(5, new int[] {1,1,1,1,2,2,2,3,3,4}, new int[] {2,3,4,5,3,4,5,4,5,5}, new long[] {10, 10, 1, 10, 1, 1, 10, 10, 1, 10}, 1);
    }
    
    static class Random {
        private long val;
        private long mul=1000000009;
        private long mod=((long)1<<31)-1;
        private long get(){
            val=(val*mul)%1000000000000000007l;
            return val;
        }
        public long getInt(long a, long b){
            return a+Math.abs((int)get()%(b-a+1));
        }
        
        public Random(long seed){
            val=seed;
        }
    }
    
    public void testaaPieniRandom(int n, int m, long M, long seed, long vast){
        Random rng=new Random(seed);
        HashSet<Long> k=new HashSet();
        
        int[] mista = new int[m];
        int[] minne = new int[m];
        long[] milloin = new long[m];
        
        for(int i=0; i<m; i++){
            mista[i]=(int)rng.getInt(1, n);
            minne[i]=(int)rng.getInt(1, n);
            while(minne[i]==mista[i])
                minne[i]=(int)rng.getInt(1, n);
            
            while(k.contains(1000000l*mista[i]+minne[i])){
                mista[i]=(int)rng.getInt(1, n);
                minne[i]=(int)rng.getInt(1, n);
                while(minne[i]==mista[i])
                    minne[i]=(int)rng.getInt(1, n);
            }
            
            k.add(1000000l*mista[i]+minne[i]);
            
            milloin[i]=rng.getInt(1, M);
        }
        
        pieniTesti(n, mista, minne, milloin, vast);
    }
    
    public void testaaSuuriRandom(int n, int m, long M, long seed, long vast){
        Random rng=new Random(seed);
        HashSet<Long> k=new HashSet();
        
        int[] mista = new int[m];
        int[] minne = new int[m];
        long[] milloin = new long[m];
        
        for(int i=0; i<m; i++){
            mista[i]=(int)rng.getInt(1, n);
            minne[i]=(int)rng.getInt(1, n);
            while(minne[i]==mista[i])
                minne[i]=(int)rng.getInt(1, n);
            
            while(k.contains(1000000l*mista[i]+minne[i])){
                mista[i]=(int)rng.getInt(1, n);
                minne[i]=(int)rng.getInt(1, n);
                while(minne[i]==mista[i])
                    minne[i]=(int)rng.getInt(1, n);
            }
            
            k.add(1000000l*mista[i]+minne[i]);
            
            milloin[i]=rng.getInt(1, M);
        }
        
        suuriTesti(n, mista, minne, milloin, vast);
    }
    
    @Test(timeout=5000)
    public void pienetRandom() {
        testaaPieniRandom(10, 10, 9, 123412341231l, 3);
        testaaPieniRandom(10, 10, 9, 98998991811283l, 9);
        testaaPieniRandom(10, 10, 9, 3124123142131l, 2);
        testaaPieniRandom(10, 10, 9, 3124123123111l, 3);
        testaaPieniRandom(10, 10, 9, 11234111234688l, 9);
        
        testaaPieniRandom(10, 15, 9, 123412341231l, 3);
        testaaPieniRandom(10, 15, 9, 98998991811283l, 3);
        testaaPieniRandom(10, 15, 9, 3124123142131l, 2);
        testaaPieniRandom(10, 15, 9, 3124123123111l, 3);
        testaaPieniRandom(10, 15, 9, 11234111234688l, 7);
        
        testaaPieniRandom(15, 20, 9, 123412341231l, 4);
        testaaPieniRandom(15, 20, 9, 98998991811283l, 5);
        testaaPieniRandom(15, 20, 9, 3124123142131l, 5);
        testaaPieniRandom(15, 20, 9, 3124123123111l, 4);
        testaaPieniRandom(15, 20, 9, 11234111234688l, 1);
    }
    
    @Test(timeout=5000)
    public void suuri1() {
        int n=100000;
        int[] mista=new int[n-1];
        int[] minne=new int[n-1];
        long[] milloin=new long[n-1];
        
        for(int i=0; i<n-1; i++){
            minne[i]=i+1;
            mista[i]=i+2;
            milloin[i]=1;
        }
        
        milloin[57517]=10000000000l;
        milloin[67677]=1000000000000000000l;
        suuriTesti(n, mista, minne, milloin, 1000000000000000000l);
    }
    
    int[] lol(ArrayList<Integer> asd){
        int[] ret=new int[asd.size()];
        for(int i=0; i<asd.size(); i++)
            ret[i]=asd.get(i);
        return ret;
    }
    
    long[] lol64(ArrayList<Long> asd){
        long[] ret=new long[asd.size()];
        for(int i=0; i<asd.size(); i++)
            ret[i]=asd.get(i);
        return ret;
    }
    
    @Test(timeout=5000)
    public void suuri2() {
        int n=100000;
        ArrayList<Integer> mista=new ArrayList();
        ArrayList<Integer> mihin=new ArrayList();
        ArrayList<Long> milloin=new ArrayList();

        Random rng=new Random(471236811);
        
        int s=1;
        int asd=0;
        
        while(asd<95000){
            mista.add(s);
            mihin.add(s+1);
            milloin.add(rng.getInt(1, 1000000000000000000l));
            mista.add(s);
            mihin.add(s+2);
            milloin.add(rng.getInt(1, 1000000000000000000l));
            mista.add(s+1);
            mihin.add(s+3);
            milloin.add(rng.getInt(1, 1000000000000000000l));
            mista.add(s+2);
            mihin.add(s+3);
            milloin.add(rng.getInt(1, 1000000000000000000l));
            s+=3;
            asd+=4;
        }
        mista.add(s);
        mihin.add(100000);
        milloin.add(1l);
        
        
        suuriTesti(n, lol(mista), lol(mihin), lol64(milloin), 2135806197);
    }
    
    @Test(timeout=5000)
    public void suuri3() {
        int n = 100000;
        int[] mista = new int[(n-1)/3*4];
        int[] minne = new int[(n-1)/3*4];
        long[] matka = new long[(n-1)/3*4];
        int c = 0;
        Random rng=new Random(1231234567898765l);
        for (int i = 0; i < (n-1)/3*4; i += 4) {
            mista[i] = i+1-c;
            minne[i] = i+2-c;
            mista[i+1] = i+1-c;
            minne[i+1] = i+3-c;
            mista[i+2] = i+2-c;
            minne[i+2] = i+4-c;
            mista[i+3] = i+3-c;
            minne[i+3] = i+4-c;
            if (c%2 == 0) {
                matka[i] = 1;
                matka[i+1] = 2;
                matka[i+2] = 1;
                matka[i+3] = 2;
            } else {
                matka[i] = rng.getInt(1, 1000);
                matka[i+1] = rng.getInt(1, 1000);
                matka[i+2] = rng.getInt(1, 1000);
                matka[i+3] = rng.getInt(1, 1000);                
            }
            c++;
        }
        suuriTesti(n, mista, minne, matka, 996);
    }
    
    @Test(timeout=5000)
    public void suuri4() {
        int n=100000;
        int m=100000;
        Random rng=new Random(1278391879l);
        HashSet<Long> k=new HashSet();
        
        int[] mista = new int[m];
        int[] minne = new int[m];
        long[] milloin = new long[m];
        
        for(int i=0; i<m; i++){
            mista[i]=(int)rng.getInt(1, n);
            minne[i]=(int)rng.getInt(1, n);
            while(minne[i]==mista[i])
                minne[i]=(int)rng.getInt(1, n);
            
            while(k.contains(1000000l*mista[i]+minne[i])){
                mista[i]=(int)rng.getInt(1, n);
                minne[i]=(int)rng.getInt(1, n);
                while(minne[i]==mista[i])
                    minne[i]=(int)rng.getInt(1, n);
            }
            
            k.add(1000000l*mista[i]+minne[i]);
            
            milloin[i]=1000000000000000000l;
        }
        
        for(int i=0; i<85439; i++)
            milloin[(int)rng.getInt(1,m) -1]=1;
        
        suuriTesti(n, mista, minne, milloin, 1000000000000000000l);
    }
    
    @Test(timeout=5000)
    public void suuri5() {
        int n=100000;
        int m=100000;
        Random rng=new Random(1278391879l);
        HashSet<Long> k=new HashSet();
        
        int[] mista = new int[m];
        int[] minne = new int[m];
        long[] milloin = new long[m];
        
        for(int i=0; i<m; i++){
            mista[i]=(int)rng.getInt(1, n);
            minne[i]=(int)rng.getInt(1, n);
            while(minne[i]==mista[i])
                minne[i]=(int)rng.getInt(1, n);
            
            while(k.contains(1000000l*mista[i]+minne[i])){
                mista[i]=(int)rng.getInt(1, n);
                minne[i]=(int)rng.getInt(1, n);
                while(minne[i]==mista[i])
                    minne[i]=(int)rng.getInt(1, n);
            }
            
            k.add(1000000l*mista[i]+minne[i]);
            
            milloin[i]=1000000000000000000l;
        }
        
        for(int i=0; i<85440; i++)
            milloin[(int)rng.getInt(1,m) -1]=1;
        
        suuriTesti(n, mista, minne, milloin, 1);
    }
    
    @Test(timeout=5000)
    public void suuri6() {
        testaaSuuriRandom(80000, 100000, 1000000000000000000l, 189273817293871l, 1080407994);
    }
    
    @Test(timeout=5000)
    public void suuri7() {
        testaaSuuriRandom(80000, 100000, 1000000000000000000l, 908871239112378941l, 2055780354);
    }
    
    @Test(timeout=5000)
    public void suuri8() {
        testaaSuuriRandom(80000, 100000, 1000000000000000000l, 12873618726378111l, 1472187678);
    }
    
    @Test(timeout=5000)
    public void suuri9() {
        testaaSuuriRandom(80000, 100000, 1000000000000000000l, 878172171777717771l, 1485932330);
    }
    
    @Test(timeout=5000)
    public void suuri10() {
        testaaSuuriRandom(80000, 100000, 1000000000000000000l, 9191991919191919l, 1143748358);
    }
}

