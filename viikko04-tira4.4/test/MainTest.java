import org.junit.Test;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;
import java.util.Arrays;

@Points("4.4")
public class MainTest {

    public void testi(Main.Laskuri l, long tulos) {
        long ulos = l.paastaSisaan();
        assertTrue("Luokkasi toimii väärin. eräältä "
                + "sisäänpäästettävältä on nyhdetty "+tulos+" euroa, mutta"
                + " metodisi palauttaa "+ulos+"." , ulos == tulos);
    }
    
    @Test(timeout=5000)
    public void esimerkki() {
        Main.Laskuri l=new Main.Laskuri();
        testi(l, 0);
        l.veloita(1);
        l.lisaaJonoon();
        testi(l, 0);
        l.lisaaJonoon();
        l.veloita(2);
        l.lisaaJonoon();
        l.veloita(3);
        testi(l, 5); 
        testi(l, 3);
        
        
        l.lisaaJonoon();
        l.lisaaJonoon();
        l.lisaaJonoon();
        l.veloita(1337);
        l.lisaaJonoon();
        l.veloita(1);
        l.lisaaJonoon();
        testi(l, 1338);
        testi(l, 1338);
        l.veloita(2);
        l.lisaaJonoon();
        testi(l, 1340);
        testi(l, 3);
        testi(l, 2);
        testi(l, 0);
        
        l.lisaaJonoon();
        l.veloita(1000000000);
        l.veloita(1000000000);
        l.veloita(1000000000);
        testi(l, 3000000000l); 
    }   
    
    @Test(timeout=5000)
    public void pieni1() {
        Main.Laskuri l=new Main.Laskuri();
        l.lisaaJonoon();
        l.veloita(1);
        l.lisaaJonoon();
        l.veloita(1);
        l.lisaaJonoon();
        l.veloita(1);
        l.lisaaJonoon();
        l.veloita(1);
        l.lisaaJonoon();
        l.veloita(1);
        l.lisaaJonoon();
        l.veloita(1);
        l.lisaaJonoon();
        l.veloita(1);
        testi(l, 7);
        testi(l, 6);
        testi(l, 5);
        testi(l, 4);
        testi(l, 3);
        testi(l, 2);
        testi(l, 1);
        testi(l, 0);
        testi(l, 0);
    }   
    
    @Test(timeout=5000)
    public void pieni2() {
        Main.Laskuri l=new Main.Laskuri();
        l.lisaaJonoon();
        l.lisaaJonoon();
        l.lisaaJonoon();
        l.lisaaJonoon();
        l.lisaaJonoon();
        l.lisaaJonoon();
        l.veloita(10);
        testi(l, 10);
        testi(l, 10);
        l.lisaaJonoon();
        l.lisaaJonoon();
        l.lisaaJonoon();
        l.lisaaJonoon();
        l.veloita(10); 
        testi(l, 20);
        testi(l, 20);
        testi(l, 20);
        testi(l, 20);
        testi(l, 10);
        testi(l, 10);
        testi(l, 10);
        testi(l, 10);
    }
    
    @Test(timeout=5000)
    public void suuri1() {    
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        int n = 100000;
        Main.Laskuri l=new Main.Laskuri();
        for(int i=0; i<n; i++){
            l.lisaaJonoon();
            l.veloita(1000000000);
        }
        for(int i=0; i<n; i++){
            testi(l,(n-i)*1000000000l);
        }
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    @Test(timeout=5000)
    public void suuri2() {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);    
        int n = 100000;
        Main.Laskuri l=new Main.Laskuri();
        for(int i=0; i<n; i++){
            l.lisaaJonoon();
        }
        for(int i=0; i<n; i++){
            l.veloita(1);
        }
        for(int i=0; i<n; i++){
            testi(l,n);
        }
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    @Test(timeout=5000)
    public void suuri3() {    
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        int n = 100000;
        Main.Laskuri l=new Main.Laskuri();
        for(int i=0; i<n/2; i++){
            l.lisaaJonoon();
        }
        for(int i=0; i<n/2; i++){
            l.veloita(1);
        }
        for(int i=0; i<n/4; i++){
            testi(l,n/2);
        }
        for(int i=0; i<n/2; i++){
            l.lisaaJonoon();
        }
        for(int i=0; i<n/2; i++){
            l.veloita(1);
        }
        for(int i=0; i<n/4; i++){
            testi(l,n);
        }
        for(int i=0; i<n/4; i++){
            testi(l,n/2);
        }
        for(int i=0; i<n; i++){
            l.veloita(1);
        }
        for(int i=0; i<n/4; i++){
            testi(l,3*n/2);
        }
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    } 
    
    @Test(timeout=5000)
    public void suuri4() {    
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        int n = 100000;
        Main.Laskuri l=new Main.Laskuri();
        for(int i=0; i<2*n; i++){
            l.lisaaJonoon();
            l.veloita(1000000000);
            if(i%2==0)
                testi(l,(i/2+1)*1000000000l);
        }
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    } 
}

