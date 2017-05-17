import org.junit.Test;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@Points("3.5")
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
    
    public void pieniTesti(int[] a, int w, int[] heitot, int tulos) {
        String a_str = Arrays.toString(a);
        String h_str = Arrays.toString(heitot);
        int ulos = Main.parasTulos(a, w, heitot);
        assertTrue("Kun w=" + w + ", maalitaulujen vasemmat reunat ovat a="+a_str+
                   " ja heittosi osuvat kohtiin "+h_str+"\nvoit osua enintään "
                   +tulos+" maalitauluun, mutta koodisi palauttaa "+ulos+".", ulos == tulos);
    }

    public void suuriTesti(int[] a, int w, int[] heitot, int tulos) {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        int ulos = Main.parasTulos(a, w, heitot);
        assertTrue("Metodi toimii väärin suurella syötteellä. Sen kuuluisi palauttaa "+tulos+" mutta se palauttaa "+ulos+".", ulos == tulos);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    @Test(timeout=5000)
    public void esimerkit() {
        pieniTesti(new int[] {3, 1, 4, 2}, 1, new int[] {2, 2, 2, 2}, 2);
        pieniTesti(new int[] {1, 1, 1, 1}, 2, new int[] {1, 4, 2, 3}, 3);
        pieniTesti(new int[] {1, 4, 1, 4}, 2, new int[] {4, 1, 4, 1}, 4);
        pieniTesti(new int[] {0, 1, 2, 3, 4}, 3, new int[] {4, 3, 2, 1, 0}, 5);
    }
    
    @Test(timeout=5000)
    public void pienet() {
        pieniTesti(new int[] {0, 1, 2, 3, 4}, 0, new int[] {4, 3, 2, 1, 0}, 5);
        pieniTesti(new int[] {0, 1, 2, 3, 4}, 0, new int[] {5, 4, 3, 2, 1}, 4);
        pieniTesti(new int[] {0, 1, 2, 3, 4}, 1, new int[] {5, 4, 3, 2, 1}, 5);
        pieniTesti(new int[] {0, 1, 2, 3, 4}, 0, new int[] {5, 4, 3, 2, 6}, 3);
        pieniTesti(new int[] {0, 1, 2, 3, 4}, 0, new int[] {5, 4, 3, 7, 6}, 2);
        pieniTesti(new int[] {0, 1, 2, 3, 4}, 1, new int[] {5, 4, 3, 7, 6}, 3);
        pieniTesti(new int[] {0, 1, 2, 3, 4}, 2, new int[] {5, 4, 3, 7, 6}, 4);
        pieniTesti(new int[] {0, 1, 2, 3, 4}, 1, new int[] {5, 4, 8, 7, 6}, 2);
        pieniTesti(new int[] {0, 1, 2, 3, 4}, 1, new int[] {5, 9, 8, 7, 6}, 1);
        pieniTesti(new int[] {0, 1, 2, 3, 4}, 0, new int[] {5, 9, 8, 7, 6}, 0);
        
        pieniTesti(new int[] {}, 0, new int[] {}, 0);
        pieniTesti(new int[] {123, 1337}, 0, new int[] {}, 0);
        pieniTesti(new int[] {}, 0, new int[] {1337}, 0);
        pieniTesti(new int[] {0}, 1000, new int[] {1337}, 0);
        pieniTesti(new int[] {0}, 1337, new int[] {1337}, 1);
    }

    @Test(timeout=5000)
    public void suuri1() {
        ArrayList<Integer> a=new ArrayList<Integer>();
        ArrayList<Integer> heitot=new ArrayList<Integer>();
        int w=0;
        int n=100000;
        
        for(int i=0; i<n; i++)
            a.add(i);
        for(int i=0; i<n; i++)
            heitot.add(i);
        
        Collections.shuffle(a);
        Collections.shuffle(heitot);
        
        int[] a2=new int[a.size()];
        int[] h2=new int[heitot.size()];
        
        for(int i=0; i<a.size(); i++)
            a2[i]=a.get(i);
        
        for(int i=0; i<heitot.size(); i++)
            h2[i]=heitot.get(i);
        
        suuriTesti(a2, w, h2, 100000);
    }   
    
    @Test(timeout=5000)
    public void suuri2() {
        ArrayList<Integer> a=new ArrayList<Integer>();
        ArrayList<Integer> heitot=new ArrayList<Integer>();
        int w=0;
        int n=100000;
        
        for(int i=0; i<n; i++)
            a.add(i);
        for(int i=0; i<n; i++)
            heitot.add(i*3);
        
        Collections.shuffle(a);
        Collections.shuffle(heitot);
        
        int[] a2=new int[a.size()];
        int[] h2=new int[heitot.size()];
        
        for(int i=0; i<a.size(); i++)
            a2[i]=a.get(i);
        
        for(int i=0; i<heitot.size(); i++)
            h2[i]=heitot.get(i);
        
        suuriTesti(a2, w, h2, 33334);
    }
    
    @Test(timeout=5000)
    public void suuri3() {
        ArrayList<Integer> a=new ArrayList<Integer>();
        ArrayList<Integer> heitot=new ArrayList<Integer>();
        int w=50000;
        int n=100000;
        
        for(int i=0; i<n; i++)
            a.add(i);
        for(int i=0; i<n; i++)
            heitot.add(i+50000);
        
        Collections.shuffle(a);
        Collections.shuffle(heitot);
        
        int[] a2=new int[a.size()];
        int[] h2=new int[heitot.size()];
        
        for(int i=0; i<a.size(); i++)
            a2[i]=a.get(i);
        
        for(int i=0; i<heitot.size(); i++)
            h2[i]=heitot.get(i);
        
        suuriTesti(a2, w, h2, 100000);
    }
    
    @Test(timeout=5000)
    public void suuri4() {
        ArrayList<Integer> a=new ArrayList<Integer>();
        ArrayList<Integer> heitot=new ArrayList<Integer>();
        int w=2500000;
        int n=100000;
        
        for(int i=0; i<400; i++)
            for(int j=0; j<=i; j++)
                a.add(w*i);
        for(int i=0; i<n; i++)
            heitot.add(i*39393);
        
        Collections.shuffle(a);
        Collections.shuffle(heitot);
        
        int[] a2=new int[a.size()];
        int[] h2=new int[heitot.size()];
        
        for(int i=0; i<a.size(); i++)
            a2[i]=a.get(i);
        
        for(int i=0; i<heitot.size(); i++)
            h2[i]=heitot.get(i);
        
        suuriTesti(a2, w, h2, 23403);
    }
    
    @Test(timeout=5000)
    public void suuri5() {
        ArrayList<Integer> a=new ArrayList<Integer>();
        ArrayList<Integer> heitot=new ArrayList<Integer>();
        int w=11132;
        int n=100000;
        
        for(int i=0; i<n; i++)
            a.add(i);
        for(int i=0; i<n; i++)
            heitot.add(i+50000);
        
        Collections.shuffle(a);
        Collections.shuffle(heitot);
        
        int[] a2=new int[a.size()];
        int[] h2=new int[heitot.size()];
        
        for(int i=0; i<a.size(); i++)
            a2[i]=a.get(i);
        
        for(int i=0; i<heitot.size(); i++)
            h2[i]=heitot.get(i);
        
        suuriTesti(a2, w, h2, 61132);
    }
    
    @Test(timeout=5000)
    public void suuri6() {
        ArrayList<Integer> a=new ArrayList<Integer>();
        ArrayList<Integer> heitot=new ArrayList<Integer>();
        int w=100000;
        int n=100000;
        
        for(int i=0; i<n; i++)
            a.add(2*i);
        for(int i=0; i<n; i++)
            heitot.add(81271);
        
        Collections.shuffle(a);
        Collections.shuffle(heitot);
        
        int[] a2=new int[a.size()];
        int[] h2=new int[heitot.size()];
        
        for(int i=0; i<a.size(); i++)
            a2[i]=a.get(i);
        
        for(int i=0; i<heitot.size(); i++)
            h2[i]=heitot.get(i);
        
        suuriTesti(a2, w, h2, 40636);
    }
    
    @Test(timeout=5000)
    public void suuri7() {
        ArrayList<Integer> a=new ArrayList<Integer>();
        ArrayList<Integer> heitot=new ArrayList<Integer>();
        int w=25000;
        int n=100000;
        
        Random rng=new Random(12341245);
        
        for(int i=0; i<n; i++)
            a.add(rng.getInt(0, 500000));
        for(int i=0; i<n; i++)
            heitot.add(rng.getInt(0, 500000));
        
        Collections.shuffle(a);
        Collections.shuffle(heitot);
        
        int[] a2=new int[a.size()];
        int[] h2=new int[heitot.size()];
        
        for(int i=0; i<a.size(); i++)
            a2[i]=a.get(i);
        
        for(int i=0; i<heitot.size(); i++)
            h2[i]=heitot.get(i);
        
        suuriTesti(a2, w, h2, 99650);
    }
    
    @Test(timeout=5000)
    public void suuri8() {
        ArrayList<Integer> a=new ArrayList<Integer>();
        ArrayList<Integer> heitot=new ArrayList<Integer>();
        int w=0;
        int n=100000;
        
        Random rng=new Random(99182391);
        
        for(int i=0; i<n; i++)
            a.add(rng.getInt(0, 500000));
        for(int i=0; i<n; i++)
            heitot.add(rng.getInt(0, 500000));
        
        Collections.shuffle(a);
        Collections.shuffle(heitot);
        
        int[] a2=new int[a.size()];
        int[] h2=new int[heitot.size()];
        
        for(int i=0; i<a.size(); i++)
            a2[i]=a.get(i);
        
        for(int i=0; i<heitot.size(); i++)
            h2[i]=heitot.get(i);
        
        suuriTesti(a2, w, h2, 16476);
    }
}

