import java.util.*;
import org.junit.Test;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;
import java.util.Arrays;

@Points("5.4")
public class MainTest {
    public static int Hash_test(int a, int b, String s){
        long H=0;
        for(int i=0; i<s.length(); i++)
            H=(a*H + s.charAt(i))%b;
        return (int)H;
    }
    

    public void testi(int a, int b) {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        Main.Yhteentormays ulos=Main.generoi(a, b);
        assertTrue("Metodisi toimii väärin: kun a="+a+" ja b="+b+", metodisi palauttaa samat merkkijonot.", !ulos.s1.equals(ulos.s2));
        assertTrue("Metodisi ei onnistu generoimaan yhteentörmäystä kun a="+a+" ja b="+b+".", Hash_test(a,b,ulos.s1) == Hash_test(a,b,ulos.s2));
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    @Test(timeout=5000)
    public void suuri1() {
        testi(428374921,1000000007);
    }
    
    @Test(timeout=5000)
    public void suuri2() {
        testi(817238781,1000000007);
    }
    
    @Test(timeout=5000)
    public void suuri3() {
        testi(919292919,1000000007);
    }
    
    @Test(timeout=5000)
    public void suuri4() {
        testi(1000000000,1000000007);
    }
    
    @Test(timeout=5000)
    public void suuri5() {
        testi(1,1000000007);
    }
    
    @Test(timeout=5000)
    public void suuri6() {
        testi(162378112,1000000009);
    }
    
    @Test(timeout=5000)
    public void suuri7() {
        testi(1333337,1000000009);
    }
    
    @Test(timeout=5000)
    public void suuri8() {
        testi(99999999,1000000009);
    }
    
    @Test(timeout=5000)
    public void suuri9() {
        testi(1,1000000009);
    }
    
    @Test(timeout=5000)
    public void suuri10() {
        testi(666,1000000009);
    }
    
    @Test(timeout=5000)
    public void suuri11() {
        testi(1000000009,2147483647);
    }
    
    @Test(timeout=5000)
    public void suuri12() {
        testi(1000000007,2147483647);
    }
    
    @Test(timeout=5000)
    public void suuri13() {
        testi(562175171,2147483647);
    }
    
    @Test(timeout=5000)
    public void suuri14() {
        testi(1111111111,2147483647);
    }
    
    @Test(timeout=5000)
    public void suuri15() {
        testi(321345867,2147483647);
    }
}



