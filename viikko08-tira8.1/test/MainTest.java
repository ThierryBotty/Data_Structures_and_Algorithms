import org.junit.Test;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;
import java.util.Arrays;

@Points("8.1")
public class MainTest {
    String str(int[][] asd){
        String s="\n";
        for(int i=0; i<asd.length; i++)
            s+=Arrays.toString(asd[i])+"\n";
        return s;
    }
    
    public void testi(int[][] et, int tulos) {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        String sisalto = str(et);
        int ulos = Main.kierros(et);
        assertTrue("Kun kaupunkien väliset etäisyydet ovat "+sisalto
                +"antaa metodisi lyhimmän kierroksen pituudeksi "+ulos
                +", vaikka oikea vastaus on "+tulos+".", tulos == ulos);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    @Test(timeout=1000)
    public void esimerkit() {  
        int[][] e1={{0,3,2,1},
                    {3,0,4,2},
                    {2,4,0,4},
                    {1,2,4,0}};
        testi(e1, 9);
        
        int[][] e2={{0,2,1,1},
                    {2,0,1,1},
                    {1,1,0,2},
                    {1,1,2,0}};
        testi(e2, 4);
        
        int[][] e3={{0,1,2,1},
                    {1,0,1,2},
                    {2,1,0,1},
                    {1,2,1,0}};
        testi(e3, 4);
        
        int[][] e4={{0,1,2,3},
                    {1,0,4,5},
                    {2,4,0,6},
                    {3,5,6,0}};
        testi(e4, 14);
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
    
    public void randomTest(int n, int seed, int M, int vast){
        Random rng=new Random(seed);
        
        int[][] et =  new int[n][n];
        
        for(int i=0; i<n; i++)
            for(int e=i+1; e<n; e++){
                et[i][e]=et[e][i]=rng.getInt(1, M);
            }
        
        testi(et, vast);
    }
    
    @Test(timeout=1000)
    public void pienet() {  
        randomTest(5, 317278311, 9, 27);
        randomTest(5, 412831231, 9, 19);
        randomTest(5, 999928981, 9, 20);
        randomTest(5, 123312311, 9, 22);
        randomTest(5, 666716276, 9, 21);
        randomTest(5, 717717171, 9, 20);
        randomTest(5, 541626312, 9, 10);
        randomTest(5, 192819211, 9, 19);
        randomTest(5, 524515555, 9, 21);
        randomTest(5, 181881818, 9, 16);
        
        randomTest(5, 317278311, 1000, 2183);
        randomTest(5, 412831231, 1000, 1834);
        randomTest(5, 999928981, 1000, 2569);
        randomTest(5, 123312311, 1000, 1403);
        randomTest(5, 666716276, 1000, 1444);
        randomTest(5, 717717171, 1000, 1178);
        randomTest(5, 541626312, 1000, 2021);
        randomTest(5, 192819211, 1000, 2918);
        randomTest(5, 524515555, 1000, 1201);
        randomTest(5, 181881818, 1000, 2156);
        
        randomTest(5, 317278311, 1000000, 2050997);
        randomTest(5, 412831231, 1000000, 1276215);
        randomTest(5, 999928981, 1000000, 2843192);
        randomTest(5, 123312311, 1000000, 1551236);
        randomTest(5, 666716276, 1000000, 1663682);
        randomTest(5, 717717171, 1000000, 2099450);
        randomTest(5, 541626312, 1000000, 2037507);
        randomTest(5, 192819211, 1000000, 2680297);
        randomTest(5, 524515555, 1000000, 861344);
        randomTest(5, 181881818, 1000000, 2229930);
    }
    
    @Test(timeout=5000)
    public void keskisuuret() {  
        randomTest(8, 317278311, 9, 28);
        randomTest(8, 412831231, 9, 23);
        randomTest(8, 999928981, 9, 21);
        randomTest(8, 123312311, 9, 27);
        randomTest(8, 666716276, 9, 20);
        
        randomTest(8, 717717171, 1000000, 1977270);
        randomTest(8, 541626312, 1000000, 2532674);
        randomTest(8, 192819211, 1000000, 1598230);
        randomTest(8, 524515555, 1000000, 1082653);
        randomTest(8, 181881818, 1000000, 1610643);
    }
    
    @Test(timeout=5000)
    public void suuri1() {  
        randomTest(10, 717717171, 1000000, 2329134);
    }
    
    @Test(timeout=5000)
    public void suuri2() {  
        randomTest(10, 871287112, 1000000, 1613866);
    }
    
    @Test(timeout=5000)
    public void suuri3() {  
        randomTest(10, 192819211, 1000000, 1633725);
    }
    
    @Test(timeout=5000)
    public void suuri4() {  
        randomTest(10, 87817281, 1000, 1996);
    }
    
    @Test(timeout=5000)
    public void suuri5() {  
        randomTest(10, 565656125, 1000, 1494);
    }
    
    @Test(timeout=5000)
    public void suuri6() {  
        randomTest(10, 18982911, 1000, 2169);
    }
}

