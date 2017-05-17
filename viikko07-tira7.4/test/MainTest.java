import org.junit.Test;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@Points("7.4")
public class MainTest {
    public void testaa(Lauta l, int tulos) {
        int ulos=l.pisinPatka();
        assertTrue("Metodisi toimii väärin. Metodi 'pisinPatka()' palauttaa "
                + ulos + " kun pitäisi palauttaa " + tulos + ".", 
                    tulos == ulos);
    }
    
    @Test(timeout=1000)
    public void esimerkki() {
        Lauta l = new Lauta(10);
        l.leikkaa(5);
        testaa(l, 5);
        l.leikkaa(5);
        testaa(l, 5); 
        l.leikkaa(6);
        testaa(l, 5);
        l.leikkaa(3);
        testaa(l, 4);
        l.leikkaa(8);
        testaa(l, 3);
        l.leikkaa(1);
        testaa(l, 2);
    }
    
    @Test(timeout=1000)
    public void pieni1() {
        Lauta l = new Lauta(10);
        l.leikkaa(9);
        testaa(l, 9);
        l.leikkaa(8);
        testaa(l, 8);
        l.leikkaa(7);
        testaa(l, 7);
        l.leikkaa(6);
        testaa(l, 6);
        l.leikkaa(5);
        testaa(l, 5);
        l.leikkaa(4);
        testaa(l, 4);
        l.leikkaa(3);
        testaa(l, 3);
        l.leikkaa(2);
        testaa(l, 2);
        l.leikkaa(1);
        testaa(l, 1);
    }
    
    @Test(timeout=1000)
    public void pieni2() {
        Lauta l = new Lauta(10);
        l.leikkaa(8);
        l.leikkaa(6);
        l.leikkaa(4);
        l.leikkaa(2);
        testaa(l, 2);
        l.leikkaa(1);
        testaa(l, 2);
        l.leikkaa(3);
        testaa(l, 2);
        l.leikkaa(5);
        testaa(l, 2);
        l.leikkaa(7);
        testaa(l, 2);
        l.leikkaa(9);
        testaa(l, 1);
    }
    
    @Test(timeout=1000)
    public void pieni3() {
        Lauta l = new Lauta(100000);
        l.leikkaa(6731);
        l.leikkaa(73918);
        l.leikkaa(17982);
        l.leikkaa(98121);
        l.leikkaa(71821);
        l.leikkaa(45123);
        testaa(l, 27141);
    }
    
    @Test(timeout=5000)
    public void suuri1() {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        Lauta l = new Lauta(100000);
        
        for(int i=0; i<99999; i++){
            if(i%2==0){
                l.leikkaa(1+i/2);
            }else{
                l.leikkaa(99999 - i/2);
            }
            testaa(l, 99999-i);
        }
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }     
    
    @Test(timeout=5000)
    public void suuri2() {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        Lauta l = new Lauta(100000);
        
        for(int i=0; i<100; i++)
            for(int e=0; e<1000; e++){
                
                l.leikkaa(e*100 + i);
                
                if(i==0)
                    testaa(l, (1000-e)*100);
                else{
                    if(e ==0 || e==1)
                        testaa(l, 100-i+1);
                }
            }
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }   
    
    @Test(timeout=5000)
    public void suuri3() {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        ArrayList<Integer> leikkaukset = new ArrayList();
        for(int i=1; i<50000; i++)
            leikkaukset.add(2*i);
        Collections.shuffle(leikkaukset);
        
        Lauta l = new Lauta(100000);
        
        for(int i : leikkaukset)
            l.leikkaa(i);
        
        testaa(l, 2);
        ArrayList<Integer> leikkaukset2 = new ArrayList();
        for(int i=0; i<50000; i++)
            leikkaukset2.add(2*i + 1);
        Collections.shuffle(leikkaukset2);

        for(int i : leikkaukset2){
            testaa(l, 2);
            l.leikkaa(i);
        }
        testaa(l, 1);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }   
    
    @Test(timeout=5000)
    public void suuri4() {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        Lauta l = new Lauta(65536);
        int N = 65536;
        int jmp=N;
        int cnt=1;
        
        while(jmp>1){
            int a=jmp/2;
            for(int i=0; i<cnt; i++)
                l.leikkaa(a + i*jmp);
            testaa(l, jmp/2);
            
            jmp/=2;
            cnt*=2;
        }
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }   
    
    @Test(timeout=5000)
    public void suuri5() {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        Lauta l = new Lauta(100000);
        ArrayList<Integer> leikkaukset = new ArrayList();
        for(int i=1; i<=40000; i++)
            leikkaukset.add(i);
        for(int i=60000; i<100000; i++)
            leikkaukset.add(i);
        
        Collections.shuffle(leikkaukset);
        for(int asd: leikkaukset)
            l.leikkaa(asd);
        
        testaa(l, 20000);
        
        l.leikkaa(50000);
        testaa(l, 10000);
        
        for(int i=40001; i<50000; i++){
            l.leikkaa(i);
            testaa(l, 10000);
        }
        
        for(int i=50001; i<60000; i++){
            l.leikkaa(i);
            testaa(l, 10000-(i-50000));
        }
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }   
    
    @Test(timeout=5000)
    public void suuri6() {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        Lauta l = new Lauta(65536*16384);
        int N = 65536*16384;
        int jmp=N;
        int cnt=1;
        
        while(jmp>16384){
            int a=jmp/2;
            for(int i=0; i<cnt; i++)
                l.leikkaa(a + i*jmp);
            testaa(l, jmp/2);
            
            jmp/=2;
            cnt*=2;
        }
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }   
}

