import org.junit.Test;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;
import java.util.Arrays;

@Points("5.7")
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
    
    String rDNA(int n, int seed){
        StringBuilder b=new StringBuilder();
        Random r=new Random(seed);
        
        for(int i=0; i<n; i++){
            int asd=r.getInt(1, 4);
            if(asd==1) b.append('A');
            if(asd==2) b.append('C');
            if(asd==3) b.append('G');
            if(asd==4) b.append('T');
        }
        return b.toString();
    }
    
    public void pieniTesti(String mjono, int tulos) {
        int ulos = Main.pisinToisto(mjono);
        assertTrue("Merkkijonon " + mjono + " pisimmän toistuvan osajonon " + 
                   "pituus on " + tulos + ", mutta metodisi antaa " + ulos + ".",
                   tulos == ulos);
    }
    
    public void suuriTesti(String mjono, int tulos) {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        int ulos = Main.pisinToisto(mjono);
        assertTrue("Metodisi toimii väärin suurella syötteellä. Se palauttaa " + ulos +" vaikka oikea vastaus on " + tulos + ".",
                   tulos == ulos);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 2s.",
                   aika<=2);
    }
    
    @Test(timeout=1000)
    public void esimerkit() {
        pieniTesti("ACGTACGT", 4);
        pieniTesti("AAAA", 3);
        pieniTesti("ACACCACCCACCCC", 6);
        pieniTesti("ACG", 0);
    }
    
    @Test(timeout=1000)
    public void pieni1() {
        pieniTesti("ACGA", 1);
        pieniTesti("AA", 1);
        pieniTesti("TATTATTATTATTAT", 12);
        pieniTesti("ACAGACATACAGACA", 7);
        pieniTesti("ACCGCCTCC", 2);
        pieniTesti("ACCGCCTCCTTTT", 3);
        pieniTesti("ACCCCCCCCCCCCCCCCCCAC", 17);
        pieniTesti("ACGTAGCTCATGAA", 1);
        pieniTesti("ACGTACGTACGT", 8);
        pieniTesti("ACTACTACTACTA", 10);
    }
    
    @Test(timeout=1000)
    public void pieni2() {
        pieniTesti(rDNA(10, 1237814912), 2);
        pieniTesti(rDNA(10, 1231231231), 2);
        pieniTesti(rDNA(10, 783718721), 2);
        pieniTesti(rDNA(10, 317289379), 1);
        pieniTesti(rDNA(10, 318273112), 5);
        pieniTesti(rDNA(20, 318273112), 5);
        pieniTesti(rDNA(20, 123142478), 2);
        pieniTesti(rDNA(20, 999999991), 3);
        pieniTesti(rDNA(20, 123412347), 3);
        pieniTesti(rDNA(20, 123712893), 5);
        pieniTesti(rDNA(20, 178293791), 2);
        pieniTesti(rDNA(20, 28378123), 4);
        pieniTesti(rDNA(20, 123412341), 3);
    }
    
    @Test(timeout=5000)
    public void suuri1() {
        int n = 200000;
        char[] mjono = new char[n];
        for (int i = 0; i < n; i++) mjono[i] = 'A';
        suuriTesti(new String(mjono), 199999);
    }
    
    @Test(timeout=5000)
    public void suuri2() {
        int n = 200000;
        char[] mjono = new char[n];
        for (int i = 0; i < n; i++) 
            mjono[i] = 'A';

        mjono[87871]='G';
        suuriTesti(new String(mjono), n - 87871 -2);
    }
    
    @Test(timeout=5000)
    public void suuri3() {
        int n = 200000;
        StringBuilder b=new StringBuilder();

        for(int i=0; i<10000; i++)
            b.append("ACGTAGCTCATGAA");
        b.setCharAt(67771, 'A');
        b.setCharAt(67888, 'C');
        
        suuriTesti(b.toString(), 72097);
    }
    
    @Test(timeout=5000)
    public void suuri4() {
        int n=200000;
        suuriTesti(rDNA(n, 23781823), 17);
    }
    
    @Test(timeout=2000)
    public void suuri5() {
        int n=200000;
        suuriTesti(rDNA(n, 13241231), 20);
    }
    
    @Test(timeout=5000)
    public void suuri6() {
        int n=200000;
        String s=rDNA(100000, 13241231);
        s+=s.substring(25555, 78781);
        s+=rDNA(40000, 3123123);
        suuriTesti(s, 53226);
    }
    
    @Test(timeout=5000)
    public void suuri7() {
        int n=200000;
        String s=rDNA(100000, 92831902);
        s+=s.substring(41721, 99999);
        s+=rDNA(40000, 312312312);
        suuriTesti(s, 58278);
    }
    
    @Test(timeout=5000)
    public void suuri8() {
        String s=rDNA(1000, 89128391);
        StringBuilder b=new StringBuilder();
        
        for(int i=0; i<200; i++)
            b.append(s);

        suuriTesti(b.toString(), 199000);
    }  
    
    @Test(timeout=5000)
    public void suuri9() {
        String s=rDNA(1000, 78127123);
        StringBuilder b=new StringBuilder();
        
        for(int i=0; i<200; i++)
            b.append(s);
        b.setCharAt(37817, 'A');
        b.setCharAt(78128, 'A');
        b.setCharAt(19023, 'A');
        b.setCharAt(12131, 'A');
        b.setCharAt(89921, 'A');
        suuriTesti(b.toString(), 109078);
    }  
    
    @Test(timeout=5000)
    public void suuri10() {
        String s="ACACACACACACACACACACACACACACACACACACACACACACACACACACACACACACACACACACACACACACACACACACACACACACACACACAG";
        StringBuilder b=new StringBuilder();
        
        for(int i=0; i<2000; i++)
            b.append(s);
        b.setCharAt(37817, 'A');
        b.setCharAt(78128, 'A');
        b.setCharAt(19023, 'A');
        b.setCharAt(12131, 'A');
        b.setCharAt(89921, 'A');
        b.setCharAt(128731, 'A');
        b.setCharAt(189891, 'A');
        b.setCharAt(190292, 'A');

        suuriTesti(b.toString(), 61059);
    }  
}

