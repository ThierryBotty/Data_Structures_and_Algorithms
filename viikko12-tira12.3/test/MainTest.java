import org.junit.Test;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;
import java.util.*;
import org.junit.Before;

@Points("12.3")
public class MainTest {
    private String kaarilista(int[] mista, int[] minne, int[] raja) {
        String tulos = "";
        for (int i = 0; i < mista.length; i++) {
            if (tulos.equals("")) tulos = mista[i] + "-" + minne[i] + "(" + raja[i] + ")";
            else tulos += ", "  + mista[i] + "-" + minne[i] + "(" + raja[i] + ")";
        }
        return "[" + tulos + "]";
    }
    
    public void pieniTesti(int n, int[] mista, int[] minne, int[] raja, int W, int tulos) {
        String sisalto = kaarilista(mista, minne, raja);
        long ulos = Main.vahvistuksia(n, mista, minne, raja, W);
        assertTrue("Kun kaupunkeja on " + n + " ja radat ovat " + sisalto +
                   ", tarvitsee painolla W=" + W +
                   " vahvistaa "+tulos+" yhteyttä, mutta algoritmisi palauttaa "+ulos+ ".",
                   tulos == ulos);
    }
    
    public void suuriTesti(int n, int[] mista, int[] minne, int[] raja, int W, int tulos) {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        long ulos = Main.vahvistuksia(n, mista, minne, raja, W);
        assertTrue("Algoritmisi toimii väärin suurella syöttellä. Se palauttaa "+ulos+", kun pitäisi palauttaa "+tulos+ ".",
                   tulos == ulos);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    @Test(timeout=1000)
    public void esimerkit() {
        pieniTesti(2, new int[]{1}, new int[]{2}, new int[]{9}, 10, 1);
        pieniTesti(3, new int[]{1,2}, new int[]{2,3}, new int[]{9,1}, 10, 2);
        pieniTesti(3, new int[]{1,2,1}, new int[]{2,3,3}, new int[]{9,1,10}, 10, 1);
        pieniTesti(4, new int[]{1,1,2,3}, new int[]{2,3,3,4}, new int[]{10,9,9,8}, 10, 2);
    }
    
    @Test(timeout=1000)
    public void pienet() {
        pieniTesti(2, new int[] {1}, new int[] {2}, new int[] {100}, 100, 0);

        pieniTesti(3, new int[] {1, 2, 3}, new int[] {2, 3, 1}, new int[] {1, 2, 3}, 2, 0);
        pieniTesti(3, new int[] {1, 2, 3}, new int[] {2, 3, 1}, new int[] {1, 3, 2}, 2, 0);
        pieniTesti(3, new int[] {1, 2, 3}, new int[] {2, 3, 1}, new int[] {2, 1, 3}, 2, 0);
        pieniTesti(3, new int[] {1, 2, 3}, new int[] {2, 3, 1}, new int[] {2, 3, 1}, 2, 0);
        pieniTesti(3, new int[] {1, 2, 3}, new int[] {2, 3, 1}, new int[] {3, 1, 2}, 2, 0);
        pieniTesti(3, new int[] {1, 2, 3}, new int[] {2, 3, 1}, new int[] {3, 2, 1}, 2, 0);

        pieniTesti(4, new int[] {1, 2, 3}, new int[] {2, 3, 4}, new int[] {5, 5, 5}, 5, 0);
        pieniTesti(4, new int[] {1, 2, 3}, new int[] {2, 3, 4}, new int[] {1, 2, 3}, 1, 0);
        pieniTesti(4, new int[] {1, 2, 3, 4}, new int[] {2, 3, 4, 1}, new int[] {1, 2, 3, 4}, 2, 0);
        pieniTesti(4, new int[] {1, 2, 3, 4}, new int[] {2, 3, 1, 1}, new int[] {1, 2, 3, 100}, 2, 0);
        pieniTesti(4, new int[] {1, 2, 3, 4}, new int[] {2, 3, 1, 1}, new int[] {1, 2, 100, 1}, 1, 0);

        pieniTesti(5, new int[] {1, 2, 3, 3, 4}, new int[] {2, 3, 1, 4, 5}, new int[] {1, 1, 1, 5, 5}, 1, 0);
        pieniTesti(5, new int[] {1, 2, 3, 3, 4, 5}, new int[] {2, 3, 1, 4, 5, 1}, new int[] {1, 1, 1, 5, 5, 1}, 1, 0);
        
        pieniTesti(4, new int[] {1, 2, 3}, new int[] {2, 3, 4}, new int[] {999999999, 999999999, 999999999}, 999999999, 0);
        
        pieniTesti(2, new int[] {1}, new int[] {2}, new int[] {100}, 100, 0);

        pieniTesti(3, new int[] {1, 2, 3}, new int[] {2, 3, 1}, new int[] {1, 2, 3}, 3, 1);
        pieniTesti(3, new int[] {1, 2, 3}, new int[] {2, 3, 1}, new int[] {1, 3, 2}, 3, 1);
        pieniTesti(3, new int[] {1, 2, 3}, new int[] {2, 3, 1}, new int[] {2, 1, 3}, 3, 1);
        pieniTesti(3, new int[] {1, 2, 3}, new int[] {2, 3, 1}, new int[] {2, 3, 1}, 3, 1);
        pieniTesti(3, new int[] {1, 2, 3}, new int[] {2, 3, 1}, new int[] {3, 1, 2}, 3, 1);
        pieniTesti(3, new int[] {1, 2, 3}, new int[] {2, 3, 1}, new int[] {3, 2, 1}, 3, 1);

        pieniTesti(4, new int[] {1, 2, 3}, new int[] {2, 3, 4}, new int[] {5, 5, 5}, 6, 3);
        pieniTesti(4, new int[] {1, 2, 3}, new int[] {2, 3, 4}, new int[] {1, 2, 3}, 2, 1);
        pieniTesti(4, new int[] {1, 2, 3, 4}, new int[] {2, 3, 4, 1}, new int[] {1, 2, 3, 4}, 3, 1);
        pieniTesti(4, new int[] {1, 2, 3, 4}, new int[] {2, 3, 1, 1}, new int[] {1, 2, 3, 100}, 3, 1);
        pieniTesti(4, new int[] {1, 2, 3, 4}, new int[] {2, 3, 1, 1}, new int[] {1, 2, 100, 1}, 2, 1);

        pieniTesti(5, new int[] {1, 2, 3, 3, 4}, new int[] {2, 3, 1, 4, 5}, new int[] {1, 1, 1, 5, 5}, 2, 2);
        pieniTesti(5, new int[] {1, 2, 3, 3, 4, 5}, new int[] {2, 3, 1, 4, 5, 1}, new int[] {1, 1, 1, 5, 5, 1}, 2, 2);
        
        pieniTesti(4, new int[] {1, 2, 3}, new int[] {2, 3, 4}, new int[] {999999999, 999999999, 999999999}, 1000000000, 3);
        
        pieniTesti(4, new int[] {1,1,1,2,2,3}, new int[] {2,3,4,3,4,4}, new int[] {1,2,3,4,5,6}, 1, 0);
        pieniTesti(4, new int[] {1,1,1,2,2,3}, new int[] {2,3,4,3,4,4}, new int[] {1,2,3,4,5,6}, 2, 0);
        pieniTesti(4, new int[] {1,1,1,2,2,3}, new int[] {2,3,4,3,4,4}, new int[] {1,2,3,4,5,6}, 3, 0);
        pieniTesti(4, new int[] {1,1,1,2,2,3}, new int[] {2,3,4,3,4,4}, new int[] {1,2,3,4,5,6}, 4, 1);
        pieniTesti(4, new int[] {1,1,1,2,2,3}, new int[] {2,3,4,3,4,4}, new int[] {1,2,3,4,5,6}, 5, 1);
        pieniTesti(4, new int[] {1,1,1,2,2,3}, new int[] {2,3,4,3,4,4}, new int[] {1,2,3,4,5,6}, 6, 2);
        pieniTesti(4, new int[] {1,1,1,2,2,3}, new int[] {2,3,4,3,4,4}, new int[] {1,2,3,4,5,6}, 7, 3);
    }
    
    @Test(timeout=5000)
    public void suuri1() {
        int n = 100000;
        int[] mista = new int[n-1];
        int[] minne = new int[n-1];
        int[] hinta = new int[n-1];
        for (int i = 0; i < n-1; i++) {
            mista[i] = i+1;
            minne[i] = i+2;
            hinta[i] = 1;
        }
        suuriTesti(n, mista, minne, hinta, 1, 0);
    }
    
    @Test(timeout=5000)
    public void suuri2() {
        int n = 100000;
        int[] mista = new int[n-1];
        int[] minne = new int[n-1];
        int[] hinta = new int[n-1];
        for (int i = 0; i < n-1; i++) {
            mista[i] = i+1;
            minne[i] = i+2;
            hinta[i] = i+1;
        }
        suuriTesti(n, mista, minne, hinta, 1, 0);
    }
    
    @Test(timeout=5000)
    public void suuri3() {
        int n = 444;
        int[] mista = new int[n*(n-1)/2];
        int[] minne = new int[n*(n-1)/2];
        int[] hinta = new int[n*(n-1)/2];
        int c = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = i+1; j <= n; j++) {
                mista[c] = i;
                minne[c] = j;
                hinta[c] = 1;
                c++;
            }
        }
        suuriTesti(n, mista, minne, hinta, 1, 0);
    }
    
    @Test(timeout=5000)
    public void suuri4() {
        int n = 444;
        int[] mista = new int[n*(n-1)/2];
        int[] minne = new int[n*(n-1)/2];
        int[] hinta = new int[n*(n-1)/2];
        int c = 0;
        long x = 12345;
        long a = 798732191;
        long b = 921339221;         
        for (int i = 1; i <= n; i++) {
            for (int j = i+1; j <= n; j++) {
                x = (x*a)%b;
                mista[c] = i;
                minne[c] = j;
                hinta[c] = (int)x;
                c++;
            }
        }
        suuriTesti(n, mista, minne, hinta, 908246982, 0);
    }

    @Test(timeout=5000)
    public void suuri5() {
        int n = 444;
        int[] mista = new int[n*(n-1)/2];
        int[] minne = new int[n*(n-1)/2];
        int[] hinta = new int[n*(n-1)/2];
        int c = 0;
        long x = 54321;
        long a = 798732191;
        long b = 921339221;         
        for (int i = 1; i <= n; i++) {
            for (int j = i+1; j <= n; j++) {
                x = (x*a)%b;
                mista[c] = i;
                minne[c] = j;
                hinta[c] = (int)x;
                c++;
            }
        }
        suuriTesti(n, mista, minne, hinta, 908737527, 0);
    }        
    
    @Test(timeout=5000)
    public void suuri6() {
        int n = 100000;
        int[] mista = new int[n-1];
        int[] minne = new int[n-1];
        int[] hinta = new int[n-1];
        for (int i = 0; i < n-1; i++) {
            mista[i] = i+1;
            minne[i] = i+2;
            hinta[i] = 1;
        }
        suuriTesti(n, mista, minne, hinta, 2, 99999);
    }
    
    @Test(timeout=5000)
    public void suuri7() {
        int n = 100000;
        int[] mista = new int[n-1];
        int[] minne = new int[n-1];
        int[] hinta = new int[n-1];
        for (int i = 0; i < n-1; i++) {
            mista[i] = i+1;
            minne[i] = i+2;
            hinta[i] = i+1;
        }
        suuriTesti(n, mista, minne, hinta, 1338, 1337);
    }
    
    @Test(timeout=5000)
    public void suuri8() {
        int n = 444;
        int[] mista = new int[n*(n-1)/2];
        int[] minne = new int[n*(n-1)/2];
        int[] hinta = new int[n*(n-1)/2];
        int c = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = i+1; j <= n; j++) {
                mista[c] = i;
                minne[c] = j;
                hinta[c] = 1;
                c++;
            }
        }
        suuriTesti(n, mista, minne, hinta, 2, 443);
    }
    
    @Test(timeout=5000)
    public void suuri9() {
        int n = 444;
        int[] mista = new int[n*(n-1)/2];
        int[] minne = new int[n*(n-1)/2];
        int[] hinta = new int[n*(n-1)/2];
        int c = 0;
        long x = 12345;
        long a = 798732191;
        long b = 921339221;         
        for (int i = 1; i <= n; i++) {
            for (int j = i+1; j <= n; j++) {
                x = (x*a)%b;
                mista[c] = i;
                minne[c] = j;
                hinta[c] = (int)x;
                c++;
            }
        }
        suuriTesti(n, mista, minne, hinta, 908246982+10000000, 137);
    }

    @Test(timeout=5000)
    public void suuri10() {
        int n = 444;
        int[] mista = new int[n*(n-1)/2];
        int[] minne = new int[n*(n-1)/2];
        int[] hinta = new int[n*(n-1)/2];
        int c = 0;
        long x = 54321;
        long a = 798732191;
        long b = 921339221;         
        for (int i = 1; i <= n; i++) {
            for (int j = i+1; j <= n; j++) {
                x = (x*a)%b;
                mista[c] = i;
                minne[c] = j;
                hinta[c] = (int)x;
                c++;
            }
        }
        suuriTesti(n, mista, minne, hinta, 908737527+10000000, 174);
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
    
    @Test(timeout=5000)
    public void suuri11() {
        int n = 95000;
        int lisa=100000-n;
        int[] mista = new int[100000-1];
        int[] minne = new int[100000-1];
        int[] hinta = new int[100000-1];
        
        for(int i=0; i<100000-1; i++)
            hinta[i]=2;
        
        HashSet<Long> lol=new HashSet();
        
        for(int i=0; i<n-1; i++){
            mista[i]=i+1;
            minne[i]=i+2;
            lol.add(1000000l*mista[i]+minne[i]);
        }
        
        Random rng = new Random(172687111);
        
        for(int i=n-1; i<100000-1; i++){
            mista[i]=rng.getInt(1, n);
            minne[i]=rng.getInt(1, n);
            while(mista[i]==minne[i])
                minne[i]=rng.getInt(1, n);
            while(lol.contains(1000000l*mista[i]+minne[i])){
                mista[i]=rng.getInt(1, n);
                minne[i]=rng.getInt(1, n);
                while(mista[i]==minne[i])
                    minne[i]=rng.getInt(1, n);
            }
            lol.add(1000000l*mista[i]+minne[i]);
        }
        
        for(int i=0; i<10000; i++)
            hinta[rng.getInt(0, 100000-2)]=1;
        
        suuriTesti(n, mista, minne, hinta, 2, 4863);
    }      
    
    @Test(timeout=5000)
    public void suuri12() {
        int n = 95000;
        int lisa=100000-n;
        int[] mista = new int[100000-1];
        int[] minne = new int[100000-1];
        int[] hinta = new int[100000-1];
        
        for(int i=0; i<100000-1; i++)
            hinta[i]=2;
        
        HashSet<Long> lol=new HashSet();
        
        for(int i=0; i<n-1; i++){
            mista[i]=i+1;
            minne[i]=i+2;
            lol.add(1000000l*mista[i]+minne[i]);
        }
        
        Random rng = new Random(781268781);
        
        for(int i=n-1; i<100000-1; i++){
            mista[i]=rng.getInt(1, n);
            minne[i]=rng.getInt(1, n);
            while(mista[i]==minne[i])
                minne[i]=rng.getInt(1, n);
            while(lol.contains(1000000l*mista[i]+minne[i])){
                mista[i]=rng.getInt(1, n);
                minne[i]=rng.getInt(1, n);
                while(mista[i]==minne[i])
                    minne[i]=rng.getInt(1, n);
            }
            lol.add(1000000l*mista[i]+minne[i]);
        }
        
        for(int i=0; i<50000; i++)
            hinta[rng.getInt(0, 100000-2)]=1;
        
        suuriTesti(n, mista, minne, hinta, 2, 34389);
    }      
    
    @Test(timeout=5000)
    public void suuri13() {
        int n = 90000;
        int lisa=100000-n;
        int[] mista = new int[100000-1];
        int[] minne = new int[100000-1];
        int[] hinta = new int[100000-1];
        
        for(int i=0; i<100000-1; i++)
            hinta[i]=2;
        
        HashSet<Long> lol=new HashSet();
        
        for(int i=0; i<n-1; i++){
            mista[i]=i+1;
            minne[i]=i+2;
            lol.add(1000000l*mista[i]+minne[i]);
        }
        
        Random rng = new Random(991919191);
        
        for(int i=n-1; i<100000-1; i++){
            mista[i]=rng.getInt(1, n);
            minne[i]=rng.getInt(1, n);
            while(mista[i]==minne[i])
                minne[i]=rng.getInt(1, n);
            while(lol.contains(1000000l*mista[i]+minne[i])){
                mista[i]=rng.getInt(1, n);
                minne[i]=rng.getInt(1, n);
                while(mista[i]==minne[i])
                    minne[i]=rng.getInt(1, n);
            }
            lol.add(1000000l*mista[i]+minne[i]);
        }
        
        for(int i=0; i<50000; i++)
            hinta[rng.getInt(0, 100000-2)]=1;
        
        suuriTesti(n, mista, minne, hinta, 2, 29414);
    }      
    
    @Test(timeout=5000)
    public void suuri14() {
        int n = 90000;
        int lisa=100000-n;
        int[] mista = new int[100000-1];
        int[] minne = new int[100000-1];
        int[] hinta = new int[100000-1];
        
        for(int i=0; i<100000-1; i++)
            hinta[i]=2;
        
        HashSet<Long> lol=new HashSet();
        
        for(int i=0; i<n-1; i++){
            mista[i]=i+1;
            minne[i]=i+2;
            lol.add(1000000l*mista[i]+minne[i]);
        }
        
        Random rng = new Random(414411565);
        
        for(int i=n-1; i<100000-1; i++){
            mista[i]=rng.getInt(1, n);
            minne[i]=rng.getInt(1, n);
            while(mista[i]==minne[i])
                minne[i]=rng.getInt(1, n);
            while(lol.contains(1000000l*mista[i]+minne[i])){
                mista[i]=rng.getInt(1, n);
                minne[i]=rng.getInt(1, n);
                while(mista[i]==minne[i])
                    minne[i]=rng.getInt(1, n);
            }
            lol.add(1000000l*mista[i]+minne[i]);
        }
        
        for(int i=0; i<10000; i++)
            hinta[rng.getInt(0, 100000-2)]=1;
        
        suuriTesti(n, mista, minne, hinta, 2, 2727);
    }
    
    @Test(timeout=5000)
    public void suuri15() {
        int n = 100000;
        int lisa=100000-n;
        int[] mista = new int[100000-1];
        int[] minne = new int[100000-1];
        int[] hinta = new int[100000-1];
        
        for(int i=0; i<100000-1; i++)
            hinta[i]=2;
        
        HashSet<Long> lol=new HashSet();
        
        for(int i=0; i<n-1; i++){
            mista[i]=i+1;
            minne[i]=i+2;
            lol.add(1000000l*mista[i]+minne[i]);
        }
        
        Random rng = new Random(1279871912);
        
        for(int i=n-1; i<100000-1; i++){
            mista[i]=rng.getInt(1, n);
            minne[i]=rng.getInt(1, n);
            while(mista[i]==minne[i])
                minne[i]=rng.getInt(1, n);
            while(lol.contains(1000000l*mista[i]+minne[i])){
                mista[i]=rng.getInt(1, n);
                minne[i]=rng.getInt(1, n);
                while(mista[i]==minne[i])
                    minne[i]=rng.getInt(1, n);
            }
            lol.add(1000000l*mista[i]+minne[i]);
        }
        
        for(int i=0; i<10000; i++)
            hinta[rng.getInt(0, 100000-2)]=1;
        
        suuriTesti(n, mista, minne, hinta, 2, 9548);
    }
}


