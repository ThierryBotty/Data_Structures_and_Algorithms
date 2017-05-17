import org.junit.Test;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@Points("3.1")
public class MainTest {
    public void pieniTesti(int[] luvut, int k, int tulos) {
        String sisalto = Arrays.toString(luvut);
        int ulos = Main.montakoKirjaa(luvut, k);
        assertTrue("Jos kirjakaupassa myytävien kirjojen hinnat ovat " + sisalto + " niin Uolevi voi ostaa " + tulos + 
                   " kirjaa, mutta metodisi palauttaa " + ulos +".", ulos == tulos);
    }

    public void suuriTesti(int[] luvut, int k, int tulos) {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        int ulos = Main.montakoKirjaa(luvut, k);
        assertTrue("Metodi toimii väärin suurella syötteellä, sen pitäisi palauttaa " + tulos + ", mutta se palauttaa " + ulos + ".", ulos == tulos);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    @Test(timeout = 5000)
    public void esimerkit() {
        pieniTesti(new int[] {2, 1, 4, 3}, 3, 2);
        pieniTesti(new int[] {1, 15, 5, 1}, 6, 2);
        pieniTesti(new int[] {3, 1, 2, 1}, 4, 3);
    }
    
    @Test(timeout = 5000)
    public void pienet() {
        pieniTesti(new int[] {9, 1, 11, 13}, 10, 2);
        pieniTesti(new int[] {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 11, 10);
        pieniTesti(new int[] {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 9, 9);
        pieniTesti(new int[] {1000000000}, 999999999, 0);
        pieniTesti(new int[] {500000000, 500000000}, 999999999, 1);
        pieniTesti(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 55, 10);
        pieniTesti(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 54, 9);
        pieniTesti(new int[] {1, 999999999}, 1000000000, 2);
    }
    
    @Test(timeout = 5000)
    public void suuri1() {
        int n=100000;
        ArrayList<Integer> lol=new ArrayList<Integer>();
        for(int i=0; i<n; i++)
            lol.add(i+1);
        Collections.shuffle(lol);
        int[] lol2=new int[n];
        for(int i=0; i<n; i++)
            lol2[i]=lol.get(i);
        
        suuriTesti(lol2, 1000000000, 44720);
    }
    
    @Test(timeout = 5000)
    public void suuri2() {
        int n=100000;
        ArrayList<Integer> lol=new ArrayList<Integer>();
        for(int i=0; i<n; i++)
            lol.add(i+1);
        Collections.shuffle(lol);
        int[] lol2=new int[n];
        for(int i=0; i<n; i++)
            lol2[i]=lol.get(i);
        
        suuriTesti(lol2, 500500, 1000);
    }
    
    @Test(timeout = 5000)
    public void suuri3() {
        int n=100000;
        ArrayList<Integer> lol=new ArrayList<Integer>();
        for(int i=0; i<n; i++)
            lol.add(i+1);
        Collections.shuffle(lol);
        int[] lol2=new int[n];
        for(int i=0; i<n; i++)
            lol2[i]=lol.get(i);
        
        suuriTesti(lol2, 31533769, 7941);
    }
    
    @Test(timeout = 5000)
    public void suuri4() {
        int n=100000;
        ArrayList<Integer> lol=new ArrayList<Integer>();
        for(int i=0; i<n; i++)
            lol.add(1);
        Collections.shuffle(lol);
        int[] lol2=new int[n];
        for(int i=0; i<n; i++)
            lol2[i]=lol.get(i);
        
        suuriTesti(lol2, 13337, 13337);
    }
    
    @Test(timeout = 5000)
    public void suuri5() {
        int n=100000;
        ArrayList<Integer> lol=new ArrayList<Integer>();
        for(int i=0; i<n; i++)
            lol.add(1 + (i%2));
        Collections.shuffle(lol);
        int[] lol2=new int[n];
        for(int i=0; i<n; i++)
            lol2[i]=lol.get(i);
        
        suuriTesti(lol2, 75000, 62500);
    }
}


