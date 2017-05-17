import org.junit.Test;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@Points("8.5")
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
    
    public void pieniTesti(long[] a, long[] b, long[] h, long tulos) {
        long ulos = Main.pieninAita(a, b, h);
        
        assertTrue("Kun syötteenä on\n"
                + "a="+Arrays.toString(a)+"\n"
                + "b="+Arrays.toString(b)+"\n"
                + "h="+Arrays.toString(h)+"\n"
                + "palauttaa metodisi "+ulos+", vaikka oikea vastaus on "+tulos+".", tulos == ulos);
    }
    
    public void suuriTesti(long[] a, long[] b, long[] h, long tulos) {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        long ulos = Main.pieninAita(a, b, h);
        assertTrue("Metodisi toimii väärin suurella syöttellä. Se palauttaa "+ulos+", vaikka oikea vastaus on "+tulos+".", tulos == ulos);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    @Test(timeout=1000)
    public void esimerkit() {  
        long[] a1={0, 3, 6};
        long[] b1={2, 5, 7};
        long[] h1={1, 1, 1};
        pieniTesti(a1, b1, h1, 5);
        
        long[] a2={0, 1, 4, 5};
        long[] b2={4, 3, 6, 7};
        long[] h2={1, 2, 3, 2};
        pieniTesti(a2, b2, h2, 14);
        
        long[] a3={0, 1, 2, 4, 4, 4};
        long[] b3={3, 3, 3, 7, 6, 5};
        long[] h3={1, 2, 3, 1, 2, 3};
        pieniTesti(a3, b3, h3, 12);
        
        long[] a4={900000000, 32323};
        long[] b4={1000000000, 32514};
        long[] h4={1, 7};
        pieniTesti(a4, b4, h4, 100001337);
    }
    
    @Test(timeout=1000)
    public void pienet() {  
        long[] a1={0, 1};
        long[] b1={3, 2};
        long[] h1={1, 2};
        pieniTesti(a1, b1, h1, 4);
        
        long[] a2={0, 1, 3};
        long[] b2={5, 2, 4};
        long[] h2={1, 2, 2};
        pieniTesti(a2, b2, h2, 7);
        
        long[] a3={0, 0, 0};
        long[] b3={5, 5, 5};
        long[] h3={1, 3, 2};
        pieniTesti(a3, b3, h3, 15);
        
        long[] a4={};
        long[] b4={};
        long[] h4={};
        pieniTesti(a4, b4, h4, 0);
        
        long[] a5={0,1,2,5,3};
        long[] b5={9,8,4,7,6};
        long[] h5={1,2,2,2,3};
        pieniTesti(a5, b5, h5, 19);
    }
    
    void testaaPieniRandom(int n, int M, long vast, int seed){
        Random rng=new Random(seed);
        long[] a=new long[n];
        long[] b=new long[n];
        long[] h=new long[n];
        
        for(int i=0; i<n; i++){
            long lol1=rng.getInt(0, M-1);
            long lol2=rng.getInt(0, M-1);
            
            a[i]=Math.min(lol1, lol2);
            b[i]=Math.max(lol1, lol2) + 1;
            
            h[i]=rng.getInt(1, M);
        }
        
        pieniTesti(a, b, h, vast);
    }
    
    void testaaSuuriRandom(int n, int M, long vast, int seed){
        Random rng=new Random(seed);
        long[] a=new long[n];
        long[] b=new long[n];
        long[] h=new long[n];
        
        for(int i=0; i<n; i++){
            long lol1=rng.getInt(0, M-1);
            long lol2=rng.getInt(0, M-1);
            
            a[i]=Math.min(lol1, lol2);
            b[i]=Math.max(lol1, lol2) + 1;
            
            h[i]=rng.getInt(1, M);
        }
        
        suuriTesti(a, b, h, vast);
    }
    
    void testaaSuuriRandom2(int n, int M, int W, long vast, int seed){
        Random rng=new Random(seed);
        long[] a=new long[n];
        long[] b=new long[n];
        long[] h=new long[n];
        
        for(int i=0; i<n; i++){      
            a[i]=rng.getInt(0, M-W);
            b[i]=a[i]+rng.getInt(1, W);
            
            h[i]=rng.getInt(1, M);
        }
        
        suuriTesti(a, b, h, vast);
    }
    
    @Test(timeout=1000)
    public void pienetSatunnaiset() {  
        testaaPieniRandom(5,9,46,1283789127);
        testaaPieniRandom(5,9,65,781281212);
        testaaPieniRandom(5,9,76,991992819);
        testaaPieniRandom(5,9,69,1727123111);
        testaaPieniRandom(5,9,58,561257615);
        testaaPieniRandom(5,9,55,1111111111);
        testaaPieniRandom(5,9,28,231231999);
        testaaPieniRandom(5,9,62,771728181);
        testaaPieniRandom(5,9,53,1298398101);
        testaaPieniRandom(5,9,54,881727361);
        
        testaaPieniRandom(10,20,299,1283789127);
        testaaPieniRandom(10,20,293,781281212);
        testaaPieniRandom(10,20,331,991992819);
        testaaPieniRandom(10,20,317,1727123111);
        testaaPieniRandom(10,20,303,561257615);
        testaaPieniRandom(10,20,347,1111111111);
        testaaPieniRandom(10,20,380,231231999);
        testaaPieniRandom(10,20,229,771728181);
        testaaPieniRandom(10,20,304,1298398101);
        testaaPieniRandom(10,20,326,881727361);
        
        testaaPieniRandom(10,1000,801323,1283789127);
        testaaPieniRandom(10,1000,673656,781281212);
        testaaPieniRandom(10,1000,586412,991992819);
        testaaPieniRandom(10,1000,699655,1727123111);
        testaaPieniRandom(10,1000,655923,561257615);
        testaaPieniRandom(10,1000,857963,1111111111);
        testaaPieniRandom(10,1000,593020,231231999);
        testaaPieniRandom(10,1000,702750,771728181);
        testaaPieniRandom(10,1000,829910,1298398101);
        testaaPieniRandom(10,1000,842156,881727361);
        
        testaaPieniRandom(20,1000,900445,1283789127);
        testaaPieniRandom(20,1000,810292,781281212);
        testaaPieniRandom(20,1000,809028,991992819);
        testaaPieniRandom(20,1000,847848,1727123111);
        testaaPieniRandom(20,1000,742274,561257615);
        testaaPieniRandom(20,1000,859115,1111111111);
        testaaPieniRandom(20,1000,771203,231231999);
        testaaPieniRandom(20,1000,796231,771728181);
        testaaPieniRandom(20,1000,851540,1298398101);
        testaaPieniRandom(20,1000,891879,881727361);
    }
    
    @Test(timeout=1000)
    public void keskisuuretSatunnaiset() {  
        testaaSuuriRandom(1000, 100000, 9938872751l, 981797913);
        testaaSuuriRandom(1000, 100000, 9899778397l, 676781682);
        testaaSuuriRandom(1000, 100000, 9937741534l, 566555151);
        testaaSuuriRandom(1000, 100000, 9912246620l, 111616616);
        testaaSuuriRandom(1000, 100000, 9912274764l, 901891201);
        
        testaaSuuriRandom(1000, 1000000000, 988143568186836884l, 89182891);
        testaaSuuriRandom(1000, 1000000000, 992201276951295074l, 456356161);
        testaaSuuriRandom(1000, 1000000000, 991161203292741345l, 229818111);
        testaaSuuriRandom(1000, 1000000000, 991881527233020308l, 123123123);
        testaaSuuriRandom(1000, 1000000000, 995580435613856557l, 133333337);
    }
    
    
    @Test(timeout=5000)
    public void suuri1() {  
        int n=100000;
        long[] a=new long[n];
        long[] b=new long[n];
        long[] h=new long[n];
        
        ArrayList<Integer> lol=new ArrayList();
        
        for(int i=0; i<n; i++)
            lol.add(i);
        
        Collections.shuffle(lol);
        
        for(int i=0; i<n; i++){
            a[i]=lol.get(i);
            b[i]=200000-lol.get(i);
            h[i]=1+lol.get(i);
        }
        
        suuriTesti(a,b,h,5000050000l*2);
    }
    
    @Test(timeout=5000)
    public void suuri2() {  
        int n=100000;
        long[] a=new long[n];
        long[] b=new long[n];
        long[] h=new long[n];
        
        ArrayList<Integer> lol=new ArrayList();
        
        for(int i=0; i<n; i++)
            lol.add(i);
        
        Collections.shuffle(lol);
        
        for(int i=0; i<n; i++){
            a[i]=10*lol.get(i);
            b[i]=20*lol.get(i);
            h[i]=1+lol.get(i);
        }
        
        suuriTesti(a,b,h,149998499990l);
    }
    
    @Test(timeout=5000)
    public void suuri3() {  
        int n=100000;
        long[] a=new long[n];
        long[] b=new long[n];
        long[] h=new long[n];
        
        ArrayList<Integer> lol=new ArrayList();
        
        for(int i=0; i<n; i++)
            lol.add(i);
        
        Collections.shuffle(lol);
        
        for(int i=0; i<n; i++){
            a[i]=10*lol.get(i);
            b[i]=20*lol.get(i);
            h[i]=1+lol.get(i)/7;
        }
        
        suuriTesti(a,b,h,21429071420l);
    }
    
    @Test(timeout=5000)
    public void suuri4() {  
        int n=100000;
        long[] a=new long[n];
        long[] b=new long[n];
        long[] h=new long[n];
        
        a[0]=0;
        b[0]=1000000000;
        h[0]=1;
        
        ArrayList<Long> lol=new ArrayList();
        lol.add(6666666l);
        
        for(int i=1; i<n-1; i++)
            lol.add((1337*lol.get(i-1)+13)%1000000001);
        
        Collections.shuffle(lol);
        for(int i=0; i<n-1; i++){
            a[i+1]=lol.get(i);
            b[i+1]=lol.get(i)+1;
            h[i+1]=1000000000;
        }
        
        suuriTesti(a,b,h,99999999900001l);
    }
    
    @Test(timeout=5000)
    public void suuri5() {  
        testaaSuuriRandom(100000, 1000000000, 999860212331067358l, 891281289);
    }
    
    @Test(timeout=5000)
    public void suuri6() {  
        testaaSuuriRandom(100000, 1000000000, 999813102340419141l, 162612711);
    }
    
    @Test(timeout=5000)
    public void suuri7() {  
        testaaSuuriRandom(100000, 1000000000, 999792308898895106l, 1111111111);
    }
    
    @Test(timeout=5000)
    public void suuri8() {  
        testaaSuuriRandom2(100000, 1000000000, 1000, 23130360541627220l, 671267271);
    }
    
    @Test(timeout=5000)
    public void suuri9() {  
        testaaSuuriRandom2(100000, 1000000000, 1000, 23150897841745786l, 991928912);
    }
    
    @Test(timeout=5000)
    public void suuri10() {  
        testaaSuuriRandom2(100000, 1000000000, 100000, 786704913042093432l, 991928912);
    }
    
    @Test(timeout=5000)
    public void suuri11() {  
        testaaSuuriRandom2(100000, 1000000000, 100000, 783794568123948344l, 667126767);
    }
}

