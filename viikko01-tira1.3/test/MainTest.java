import org.junit.Test;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import java.util.ArrayList;
import java.util.Collections;

@Points("1.3")
public class MainTest {
    String toString(int[] t){
        if(t.length == 0)
            return "[]";
        
        String ret="[";
        ret+=t[0];
        for(int i=1; i<t.length; i++)
            ret+=", "+t[i];
        ret+="]";
        return ret;
    }
    
    int[] lolgen(int p, int k, int a){
        int[] ret=new int[p];
        for(int i=0; i<p; i++)
            ret[i]=1 + (int)((a + (long)i*k)%p);
        return ret;
    }
    
    public void testaaPieni(int[] a, int[] b, boolean tulos) {
        boolean tuloste=Main.ovatkoSamat(a,b);
        assertTrue("Taulukoilla " + toString(a) + " ja " + toString(b) + " odotetaan vastaukseksi '" + tulos +
                    "', mutta metodisi palauttaa '" + tuloste + "'.",
                tuloste == tulos);
        
    }
    
    public void testaaSuuri(int[] a, int[] b, boolean tulos) {
        assertTrue("Metodisi toimii väärin suurella syötteellä.",
                   Main.ovatkoSamat(a,b) == tulos);
    }
    
    @Test(timeout=1000)
    public void esimerkit() {
        testaaPieni(new int[]{1, 2, 3}, new int[]{3, 1, 2}, true);
        testaaPieni(new int[]{1, 2, 3}, new int[]{3, 2, 1}, false);
        testaaPieni(new int[]{2, 5, 3, 1, 4}, new int[]{3, 1, 4, 2, 5}, true);
        testaaPieni(new int[]{1, 2, 3, 4, 5}, new int[]{1, 2, 3, 5, 4}, false);
    }
    
    
    @Test(timeout=1000)
    public void pieni1() {
        testaaPieni(new int[]{6, 2, 3, 4, 1, 5}, new int[]{4, 1, 5, 2, 6, 3}, false);
        testaaPieni(new int[]{1}, new int[]{1}, true);
        testaaPieni(new int[]{1, 2}, new int[]{2, 1}, true);
        
        testaaPieni(lolgen(7, 2, 0), lolgen(7, 2, 4), true);
        testaaPieni(lolgen(7, 5, 3), lolgen(7, 5, 6), true);
        testaaPieni(lolgen(7, 5, 3), lolgen(7, 3, 6), false);
        testaaPieni(lolgen(11, 7, 5), lolgen(11, 2, 2), false);
        testaaPieni(lolgen(11, 7, 5), lolgen(11, 7, 2), true);
        testaaPieni(lolgen(5, 1, 0), lolgen(5, 2, 0), false);
        testaaPieni(lolgen(11, 8, 3), lolgen(11, 2, 2), false);
    }
    
    @Test(timeout=1000)
    public void suuri1() {
        testaaSuuri(lolgen(99991, 5555, 76766), lolgen(99991, 5555, 1337), true);
    }
    
    @Test(timeout=1000)
    public void suuri2() {
        testaaSuuri(lolgen(99991, 89999, 63123), lolgen(99991, 89999, 88888), true);
    }
    
    @Test(timeout=1000)
    public void suuri3() {
        testaaSuuri(lolgen(99991, 89998, 63123), lolgen(99991, 89995, 88888), false);
    }
    
    @Test(timeout=1000)
    public void suuri4() {
        testaaSuuri(lolgen(99991, 18237, 89784), lolgen(99991, 76412, 17233), false);
    }
    
    @Test(timeout=1000)
    public void suuri5() {
        int n=100000;
        ArrayList<Integer> lollero = new ArrayList<Integer>();
        for(int i=0; i<n; i++)
            lollero.add(i+1);
        Collections.shuffle(lollero);
        
        int[] a=new int[n];
        int[] b=new int[n];
        
        for(int i=0; i<n; i++)
            a[i]=lollero.get(i);
        for(int i=0; i<n; i++)
            b[(32176+i)%n]=lollero.get(i);
        testaaSuuri(a, b, true);
    }
    
    @Test(timeout=1000)
    public void suuri6() {
        int n=100000;
        ArrayList<Integer> lollero = new ArrayList<Integer>();
        for(int i=0; i<n; i++)
            lollero.add(i+1);
        Collections.shuffle(lollero);
        
        int[] a=new int[n];
        int[] b=new int[n];
        
        for(int i=0; i<n; i++)
            a[i]=lollero.get(i);
        for(int i=0; i<n; i++)
            b[(32176+i)%n]=lollero.get(i);
        
        int tmp=b[52387];
        b[52387]=b[47263];
        b[47263]=tmp;
        testaaSuuri(a, b, false);
    } 
}
