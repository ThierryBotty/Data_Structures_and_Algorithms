import org.junit.Test;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;
import java.util.Arrays;
import java.util.Random;

@Points("10.3")
public class MainTest {    
    private String kaarilista(int[] mista, int[] minne) {
        String tulos = "";
        for (int i = 0; i < mista.length; i++) {
            if (tulos.equals("")) tulos = mista[i] + "->" + minne[i];
            else tulos += ", "  + mista[i] + "->" + minne[i];
        }
        return "[" + tulos + "]";
    }
    
    public void pieniTesti(Reitinlaskija r, int a, int b, int tulos, int[] mista, int[] minne) {
        String sisalto = kaarilista(mista, minne);
        int ulos = r.lyhinReitti(a, b);
        assertTrue("Kun annetun verkon kaaret ovat "+sisalto+" ja a="+a+", b="+b+",\n"
                + "on oikea vastaus "+tulos+", mutta metodisi palauttaa "+ulos+".", tulos == ulos);
    }
    
    public void suuriTesti(Reitinlaskija r, int a, int b, int tulos) {
        int ulos = r.lyhinReitti(a, b);
        assertTrue("Metodisi toimii väärin suurella syötteellä. Kyselyllä a="+a+", b="+b+", sen tulisi palauttaa "+tulos+", mutta se palauttaa "+ulos+".", tulos == ulos);
    }
    
    @Test(timeout=5000)
    public void esimerkit() {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        int[] mista1 = {1,2,2,3,3,4,4,1};
        int[] minne1 = {2,1,3,2,4,3,1,4};
        Reitinlaskija r1 = new Reitinlaskija(4, mista1, minne1);
        
        pieniTesti(r1, 1, 3, 2, mista1, minne1);
        pieniTesti(r1, 4, 2, 2, mista1, minne1);
        pieniTesti(r1, 4, 1, 1, mista1, minne1);
        pieniTesti(r1, 1, 4, 1, mista1, minne1);
        pieniTesti(r1, 2, 3, 3, mista1, minne1);
        
        
        int[] mista2 = {1,2,1,3,1,4};
        int[] minne2 = {2,1,3,1,4,1};
        Reitinlaskija r2 = new Reitinlaskija(4, mista2, minne2);
        pieniTesti(r2, 2, 3, 2, mista2, minne2);
        pieniTesti(r2, 3, 4, 2, mista2, minne2);
        pieniTesti(r2, 4, 2, 2, mista2, minne2);
        pieniTesti(r2, 1, 1, 0, mista2, minne2);
        
        
        int[] mista3 = {1,2,3,4,5};
        int[] minne3 = {2,3,4,5,1};
        Reitinlaskija r3 = new Reitinlaskija(6, mista3, minne3);
        pieniTesti(r3, 1, 6, -1, mista3, minne3);
        pieniTesti(r3, 2, 3, 6, mista3, minne3);
        pieniTesti(r3, 5, 2, 2, mista3, minne3);
        pieniTesti(r3, 2, 5, 8, mista3, minne3);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    @Test(timeout=5000)
    public void pienet() {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        int[] mista1 = {1,2,1,3,2,4,2,5,3,6,3,7};
        int[] minne1 = {2,1,3,1,4,2,5,2,6,3,7,3};
        Reitinlaskija r1 = new Reitinlaskija(7, mista1, minne1);
        
        pieniTesti(r1, 2, 3, 2, mista1, minne1);
        pieniTesti(r1, 4, 7, 4, mista1, minne1);
        pieniTesti(r1, 4, 6, 4, mista1, minne1);
        pieniTesti(r1, 4, 5, 4, mista1, minne1);
        pieniTesti(r1, 1, 7, 2, mista1, minne1);
        pieniTesti(r1, 1, 3, 1, mista1, minne1);
        
        
        int[] mista2 = {5,6,7,1,2,3};
        int[] minne2 = {6,7,1,2,3,4};
        Reitinlaskija r2 = new Reitinlaskija(7, mista2, minne2);
        pieniTesti(r2, 5, 4, 6, mista2, minne2);
        pieniTesti(r2, 3, 5,-1, mista2, minne2);
        pieniTesti(r2, 7, 2, 2, mista2, minne2);
        pieniTesti(r2, 2, 7,-1, mista2, minne2);
        pieniTesti(r2, 6, 3, 4, mista2, minne2);
        pieniTesti(r2, 3, 6,-1, mista2, minne2);
        
        
        int[] mista3 = {1,2,1,3,1,4,2,3,2,4,3,4};
        int[] minne3 = {2,1,3,1,4,1,3,2,4,2,4,3};
        Reitinlaskija r3 = new Reitinlaskija(4, mista3, minne3);
        pieniTesti(r3, 2, 3, 2, mista3, minne3);
        pieniTesti(r3, 4, 1, 1, mista3, minne3);
        pieniTesti(r3, 1, 1, 0, mista3, minne3);
        pieniTesti(r3, 4, 3, 2, mista3, minne3);
        pieniTesti(r3, 2, 4, 2, mista3, minne3);
        pieniTesti(r3, 4, 4, 2, mista3, minne3);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
   
    @Test(timeout=5000)
    public void suuri1() {
        int[] mista = new int[196692];
        int[] minne = new int[196692];
        
        int idx=0;
        for(int i=1; i<=444; i++)
            for(int j=i+1; j<=444; j++){
                mista[idx]=i;
                minne[idx]=j;
                mista[idx+1]=j;
                minne[idx+1]=i;
                idx+=2;
            }
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        Reitinlaskija r = new Reitinlaskija(444, mista, minne);
        Random rand = new Random();
        
        for(int i=0; i<95000; i++){
            int a = rand.nextInt(443) + 2;
            int b = rand.nextInt(443) + 2;
            suuriTesti(r, a, b, 2);
        }
        
        for(int i=0; i<2500; i++){
            int a = rand.nextInt(443) + 2;
            suuriTesti(r, 1, a, 1);
            suuriTesti(r, a, 1, 1);
        }
        
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    @Test(timeout=5000)
    public void suuri2() {
        int[] mista = new int[100000];
        int[] minne = new int[100000];
        
        for(int i=0; i<99999; i++){
            mista[i]=i+1;
            minne[i]=i+2;
        }
        mista[99999]=100000;
        minne[99999]=1;
        
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        Reitinlaskija r = new Reitinlaskija(100000, mista, minne);
        Random rand = new Random();
        
        for(int i=0; i<100000; i++){
            int a = rand.nextInt(100000) + 1;
            int b = rand.nextInt(100000) + 1;
            
            if(a == b){
                if(a == 1)
                    suuriTesti(r, a, b, 0);
                else
                    suuriTesti(r, a, b, 100000);
            }else{
                if(a<b){
                    if(a==1){
                        suuriTesti(r, a, b, b-1);
                    }else{
                        suuriTesti(r, a, b, 100000+b-a);
                    }
                }else{
                    if(b==1){
                        suuriTesti(r, a, b, 100001-a);
                    }else{
                        suuriTesti(r, a, b, 100000-(a-b));
                    }
                }
            }
        }
        
        
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    @Test(timeout=5000)
    public void suuri3() {
        int[] mista = new int[99999];
        int[] minne = new int[99999];
        
        for(int i=0; i<=49999; i++){
            mista[i]=50000+i;
            minne[i]=50000+i+1;
        }
        mista[50000]=100000;
        minne[50000]=1;
        for(int i=1; i<=49998; i++){
            mista[50000+i] = i;
            minne[50000+i] = i+1;
        }
        
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        Reitinlaskija r = new Reitinlaskija(100000, mista, minne);
        Random rand = new Random();
        
        for(int i=0; i<100000; i++){
            int a = rand.nextInt(100000) + 1;
            int b = rand.nextInt(100000) + 1;
            
            if((a!=1 && a<50000) || b>=50000){
                suuriTesti(r, a, b, -1);
            }else{
                if(a!=1){
                   suuriTesti(r, a, b, (100001-a)+(b-1));
                }
            }
        }
        
        
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    int haluaisinJoNukkua(int a){
        int ret=0;
        while(a>1){
            ret++;
            a/=2;
        }
        return ret;
    }
    
    @Test(timeout=5000)
    public void suuri4() {
        int[] mista = new int[2*65536];
        int[] minne = new int[2*65536];
        
        int idx=0;
        for(int i=1; i<=(1<<15); i++){
            mista[idx]=i;
            minne[idx]=2*i;
            mista[idx+1]=2*i;
            minne[idx+1]=i;
            mista[idx+2]=i;
            minne[idx+2]=2*i+1;
            mista[idx+3]=2*i+1;
            minne[idx+3]=i;
            idx+=4;
        }    
        
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        Reitinlaskija r = new Reitinlaskija(65537, mista, minne);
        Random rand = new Random();
        
        for(int i=0; i<100000; i++){
            int a = rand.nextInt(65536) + 1;
            int b = rand.nextInt(65536) + 1;
            
            suuriTesti(r, a, b, haluaisinJoNukkua(a) + haluaisinJoNukkua(b));
        }
        
        
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
}

