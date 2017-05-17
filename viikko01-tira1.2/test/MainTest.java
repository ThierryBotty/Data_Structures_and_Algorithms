import org.junit.Test;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import java.util.Random;

@Points("1.2")
public class MainTest {
    String toString(long[] t){
        if(t.length == 0)
            return "[]";
        
        String ret="[";
        ret+=t[0];
        for(int i=1; i<t.length; i++)
            ret+=", "+t[i];
        ret+="]";
        return ret;
    }
    
    public void testaaPieni(Summataulukko s, long[] t, int l, int r, long tulos) {
        long tuloste = s.summa(l, r);
        assertTrue("Taulukossa " + toString(t) + " välin " + l + "-" + r + " summa on "+
                   tulos + ", mutta metodisi palauttaa " + tuloste + ".", tuloste == tulos);
    }

    public void testaaSuuri(Summataulukko s, long[] t, int l, int r, long tulos) {
        long tuloste = s.summa(l, r);
        assertTrue("Metodisi toimii väärin suurella syötteellä.", tuloste == tulos);
    }
    
    
    @Test(timeout=1000)
    public void esim1() {
        long[] t=new long[]{4, 1, 9, 2, 7, 3};
        Summataulukko s=new Summataulukko(t);
        testaaPieni(s, t, 1, 4, 19);
        testaaPieni(s, t, 3, 5, 12);
        testaaPieni(s, t, 0, 3, 16);
    }
    
    @Test(timeout=1000)
    public void esim2() {
        long[] t=new long[]{1, 2, 3, 4, 5, 6};
        Summataulukko s=new Summataulukko(t);
        testaaPieni(s, t, 0, 3, 10);
        testaaPieni(s, t, 4, 5, 11);
        testaaPieni(s, t, 0, 0, 1);
        testaaPieni(s, t, 5, 5, 6);
    }
    
    @Test(timeout=1000)
    public void pieni1() {
        long[] t=new long[]{1000000000, 1000000000, 1000000000};
        Summataulukko s=new Summataulukko(t);
        testaaPieni(s, t, 0, 2, 3000000000l);
    }
    
    @Test(timeout=1000)
    public void pieni2() {
        long[] t=new long[]{5, 9, 1, 12, 1, -10, 12, 0};
        Summataulukko s=new Summataulukko(t);
        testaaPieni(s, t, 0, 1, 14);
        testaaPieni(s, t, 5, 6, 2);
        testaaPieni(s, t, 7, 7, 0);
        testaaPieni(s, t, 0, 7, 30);
        testaaPieni(s, t, 1, 7, 25);
        testaaPieni(s, t, 4, 5, -9);
    }
    
    @Test(timeout=1000)
    public void pieni3() {
        long[] t=new long[]{0, 0, 0, 0, 0, 0, 0, 1};
        Summataulukko s=new Summataulukko(t);
        testaaPieni(s, t, 0, 1, 0);
        testaaPieni(s, t, 5, 6, 0);
        testaaPieni(s, t, 7, 7, 1);
        testaaPieni(s, t, 0, 7, 1);
        testaaPieni(s, t, 1, 7, 1);
        testaaPieni(s, t, 4, 5, 0);
    }
    
    @Test(timeout=1000)
    public void suuri1() {
        int n=100000;
        
        long[] t=new long[n];
        t[65617]=1;
        Summataulukko s=new Summataulukko(t);
        for(int i=0; i<100000; i++)
            if(i<=65617)
                testaaSuuri(s, t, i, 99999, 1);
            else
                testaaSuuri(s, t, i, 99999, 0);
    }
    
    @Test(timeout=1000)
    public void suuri2() {
        int n=100000;
        
        long[] t=new long[n];
        for(int i=0; i<n; i++)
            t[i]=1000000000;
        Random rand=new Random();
        Summataulukko s=new Summataulukko(t);
        
        for(int i=0; i<100000; i++){
            int a=rand.nextInt(100000);
            int b=rand.nextInt(100000);
            int l=Math.min(a, b);
            int r=Math.max(a, b);
            
            testaaSuuri(s, t, l, r, (r-l+1) * 1000000000l);
        }
    }
    
    @Test(timeout=1000)
    public void suuri3() {
        int n=100000;
        
        long[] t=new long[n];
        for(int i=0; i<n; i++)
            t[i]=i;
        
        Random rand=new Random();
        Summataulukko s=new Summataulukko(t);
        
        for(int i=0; i<100000; i++){
            int a=rand.nextInt(100000);
            int b=rand.nextInt(100000);
            int l=Math.min(a, b);
            int r=Math.max(a, b);
            
            testaaSuuri(s, t, l, r, (long)(r)*(r+1)/2 - (long)(l)*(l-1)/2);
        }
    }
    
    @Test(timeout=1000)
    public void suuri4() {
        int n=100000;
        
        long[] t=new long[n];
        for(int i=0; i<n; i++)
            if(i%2==0)
                t[i]=1;
            else
                t[i]=-1;
        
        Random rand=new Random();
        Summataulukko s=new Summataulukko(t);
        
        for(int i=0; i<100000; i++){
            int a=rand.nextInt(100000);
            int b=rand.nextInt(100000);
            int l=Math.min(a, b);
            int r=Math.max(a, b);
            
            if((r-l+1)%2==0)
                testaaSuuri(s, t, l, r, 0);
            else{
                if(l%2==1)
                    testaaSuuri(s, t, l, r, -1);
                else
                    testaaSuuri(s, t, l, r, 1);
            }
        }
    }
}

