import org.junit.Test;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;
import java.util.Arrays;

@Points("11.1")
public class MainTest {
    
    String str(long[][] M){
        int n=M.length-1;
        String ret="\n";
        for(int i=1; i<=n; i++){
            for(int e=1; e<=n; e++){
                ret+=M[i][e];
                if(e!=n)
                    ret+=",";
            }
            ret+="\n";
        }
        return ret;
    }
    
    boolean samanlaiset(long[][] M1, long[][] M2){
        if(M1.length!=M2.length)
            return false;
        for(int i=1; i<M1.length; i++){
            if(M1[i].length!=M2[i].length)
                return false;
            for(int e=1; e<M1[i].length; e++)
                if(M1[i][e]!=M2[i][e])
                    return false;
        }
        return true;
    }
    
    public void pieniTesti(int n, long[][] et, long[][] tulos) {
        long[][] ulos = Main.lyhinReitti(n, et);
        assertTrue("Kun syötteenä annettu matriisi (0. sarake ja rivi lukuunottamatta) on"
                + str(et)+"on oikea vastaus "+str(tulos)+"mutta ohjelmasi palauttaa"+str(ulos),
                   samanlaiset(tulos, ulos));
    }
    
    public void suuriTesti(int n, long[][] et, int i, int j, long tulos) {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        long[][] ulos = Main.lyhinReitti(n, et);
        assertTrue("Metodisi toimii väärin suurella syötteellä. ",
                   ulos[i][j]==tulos);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    @Test(timeout=5000)
    public void esimerkki() {
        long[][] et1={{0,0,0,0,0},
                      {0,0,9,1,2},
                      {0,9,0,3,1},
                      {0,1,3,0,7},
                      {0,2,1,7,0}};
        
        long[][] v1= {{0,0,0,0,0},
                      {0,0,3,1,2},
                      {0,3,0,3,1},
                      {0,1,3,0,3},
                      {0,2,1,3,0}};
        
        pieniTesti(4, et1, v1);
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
    
    
    
    void testaaPieniRandom(int n, int m, int seed, long[][] vast){
        Random rng=new Random(seed);
        long[][] et=new long[n+1][n+1];
        for(int i=1; i<=n; i++)
            for(int e=i+1; e<=n; e++){
                et[i][e]=rng.getInt(1, m);
                et[e][i]=et[i][e];
            }
        pieniTesti(n,et,vast);
    }
    
    void testaaSuuriRandom(int n, int m, int seed, long vast){
        Random rng=new Random(seed);
        long[][] et=new long[n+1][n+1];
        for(int i=1; i<=n; i++)
            for(int e=i+1; e<=n; e++){
                et[i][e]=rng.getInt(1, m);
                et[e][i]=et[i][e];
            }
        suuriTesti(n,et,rng.getInt(1, n),rng.getInt(1, n),vast);
    }
    
    @Test(timeout=5000)
    public void pienet() {
        long[][] v1={{0,0,0,0,0,0},{0,0,5,6,2,6},{0,5,0,1,5,2},{0,6,1,0,6,3},{0,2,5,6,0,5},{0,6,2,3,5,0}};
        testaaPieniRandom(5,9,1238199199, v1);
        
        long[][] v2={{0,0,0,0,0,0},{0,0,5,4,8,3},{0,5,0,5,3,2},{0,4,5,0,4,7},{0,8,3,4,0,5},{0,3,2,7,5,0}};
        testaaPieniRandom(5,9,781781811, v2);
        
        long[][] v3={{0,0,0,0,0,0},{0,0,4,2,2,3},{0,4,0,2,3,1},{0,2,2,0,4,1},{0,2,3,4,0,4},{0,3,1,1,4,0}};
        testaaPieniRandom(5,9,1234112311, v3);
        
        long[][] v4={{0,0,0,0,0,0},{0,0,7,2,2,3},{0,7,0,8,8,5},{0,2,8,0,1,3},{0,2,8,1,0,4},{0,3,5,3,4,0}};
        testaaPieniRandom(5,9,881818237, v4);
        
        long[][] v5={{0,0,0,0,0,0},{0,0,7,7,6,2},{0,7,0,4,3,5},{0,7,4,0,1,5},{0,6,3,1,0,4},{0,2,5,5,4,0}};
        testaaPieniRandom(5,9,661771911, v5);
        
        long[][] v6={{0,0,0,0,0,0},{0,0,5,3,3,4},{0,5,0,7,4,6},{0,3,7,0,6,7},{0,3,4,6,0,3},{0,4,6,7,3,0}};
        testaaPieniRandom(5,9,445261112, v6);
        
        long[][] v7={{0,0,0,0,0,0},{0,0,5,7,4,8},{0,5,0,6,3,4},{0,7,6,0,3,7},{0,4,3,3,0,6},{0,8,4,7,6,0}};
        testaaPieniRandom(5,9,111111111, v7);
        
        long[][] v8={{0,0,0,0,0,0},{0,0,3,4,1,4},{0,3,0,5,4,3},{0,4,5,0,4,2},{0,1,4,4,0,3},{0,4,3,2,3,0}};
        testaaPieniRandom(5,9,123155555, v8);
        
        long[][] v9={{0,0,0,0,0,0},{0,0,7,2,4,2},{0,7,0,6,8,5},{0,2,6,0,2,4},{0,4,8,2,0,6},{0,2,5,4,6,0}};
        testaaPieniRandom(5,9,881234111, v9);
        
        long[][] v10={{0,0,0,0,0,0},{0,0,5,9,9,5},{0,5,0,5,6,5},{0,9,5,0,7,9},{0,9,6,7,0,5},{0,5,5,9,5,0}};
        testaaPieniRandom(5,9,876543567, v10);
    }
    
    @Test(timeout=5000)
    public void suuri1() {
        testaaSuuriRandom(100, 1000000000, 1287389191, 29930172);
    }
    
    @Test(timeout=5000)
    public void suuri2() {
        testaaSuuriRandom(100, 1000000000, 512312111, 40534647);
    }
    
    @Test(timeout=5000)
    public void suuri3() {
        testaaSuuriRandom(100, 1000000000, 1235125511, 31574158);
    }
    
    @Test(timeout=5000)
    public void suuri4() {
        testaaSuuriRandom(100, 1000000000, 555115115, 45012469);
    }
    
    @Test(timeout=5000)
    public void suuri5() {
        testaaSuuriRandom(100, 1000000000, 898128111, 47841735);
    }
    
    @Test(timeout=5000)
    public void suuri6() {
        testaaSuuriRandom(100, 1000000000, 818212112, 64528715);
    }
    
    @Test(timeout=5000)
    public void suuri7() {
        testaaSuuriRandom(100, 1000000000, 781788178, 49140900);
    }
    
    @Test(timeout=5000)
    public void suuri8() {
        testaaSuuriRandom(100, 1000000000, 781728781, 25013020);
    }
    
    @Test(timeout=5000)
    public void suuri9() {
        testaaSuuriRandom(100, 1000000000, 87787877, 35624827);
    }
    
    @Test(timeout=5000)
    public void suuri10() {
        testaaSuuriRandom(100, 1000000000, 1111111111, 31822543);
    }
}

