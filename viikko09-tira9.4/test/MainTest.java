import org.junit.Test;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@Points("9.4")
public class MainTest {
    private String kaarilista(int[] mista, int[] minne) {
        String tulos = "";
        for (int i = 0; i < mista.length; i++) {
            if (tulos.equals("")) tulos = mista[i] + "-" + minne[i];
            else tulos += ", "  + mista[i] + "-" + minne[i];
        }
        return "[" + tulos + "]";
    }
    
    public void pieniTesti(int n, int[] saatavat, int[] mista, int[] minne, boolean tulos) {
        String sisalto = kaarilista(mista, minne);
        boolean ulos = Main.velat(n, saatavat, mista, minne);
        assertTrue("Kun henkilöitä on " + n + ", taulukon 'saatavat' sisältö on " + Arrays.toString(saatavat) 
                + " (huomioi nollas indeksi),\n ja seuraavat henkilöt ovat puheväleissä " + sisalto +
                    ", odotettu vastaus \n on '"+tulos+"', mutta metodisi palauttaa '"+ulos+"'.", tulos == ulos);
    }
    
    public void suuriTesti(int n, int[] saatavat, int[] mista, int[] minne, boolean tulos) {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        boolean ulos = Main.velat(n, saatavat, mista, minne);
        assertTrue("Metodisi toimii väärin suurella syötteellä.\n"
                + "Se palauttaa '"+ulos+"', kun pitäisi palauttaa '"+tulos+"'.", tulos == ulos);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    @Test(timeout=5000)
    public void esimerkit() {
        int[] saatavia1 = {0,1,3,-4};
        int[] mista1 = {1,3};
        int[] mihin1 = {2,1};
        pieniTesti(3, saatavia1, mista1, mihin1, true);
        
        int[] saatavia2 = {0, 1, 1, 1, -3};
        int[] mista2 = {1, 2, 3};
        int[] mihin2 = {2, 3, 4};
        pieniTesti(4, saatavia2, mista2, mihin2, true);
        
        int[] saatavia3 = {0, 1, 1, 1, -3};
        int[] mista3 = {1, 2};
        int[] mihin3 = {2, 3};
        pieniTesti(4, saatavia3, mista3, mihin3, false);
    }
    
    @Test(timeout=5000)
    public void pienet() {
        int[] saatavia1 = {0,1,-1};
        int[] mista1 = {1};
        int[] mihin1 = {2};
        pieniTesti(2, saatavia1, mista1, mihin1, true);
        
        int[] saatavia2 = {0,1,-1};
        int[] mista2 = {};
        int[] mihin2 = {};
        pieniTesti(2, saatavia2, mista2, mihin2, false);
        
        int[] saatavia3 = {0,2,-1,1,-2};
        int[] mista3 = {1, 3};
        int[] mihin3 = {2, 4};
        pieniTesti(4, saatavia3, mista3, mihin3, false);
        
        int[] saatavia4 = {0,2,-1,1,-2};
        int[] mista4 = {1, 2};
        int[] mihin4 = {4, 3};
        pieniTesti(4, saatavia4, mista4, mihin4, true);
        
        int[] saatavia5 = {0, -2, 1, 1, 1, 0, -1};
        int[] mista5 = {2, 2, 5, 5};
        int[] mihin5 = {1, 3, 4, 6};
        pieniTesti(6, saatavia5, mista5, mihin5, true);
        
        int[] saatavia6 = {0, -2, 1, 1, 1, 0, -1, 1, -1};
        int[] mista6 = {2, 2, 5, 5};
        int[] mihin6 = {1, 3, 4, 6};
        pieniTesti(8, saatavia6, mista6, mihin6, false);
        
        int[] saatavia7 = {0, -2, 1, 1, 1, 0, -1, 1, -1};
        int[] mista7 = {2, 2, 5, 5, 7};
        int[] mihin7 = {1, 3, 4, 6, 8};
        pieniTesti(8, saatavia7, mista7, mihin7, true);
        
        int[] saatavia8 = {0,0,0};
        int[] mista8 = {};
        int[] mihin8 = {};
        pieniTesti(2, saatavia8, mista8, mihin8, true);
        
        int[] saatavia9 = {0,0};
        int[] mista9 = {};
        int[] mihin9 = {};
        pieniTesti(1, saatavia9, mista9, mihin9, true);
    }
    
    @Test(timeout=5000)
    public void suuri1() {
        int n = 100000;
        
        ArrayList<Integer> lol = new ArrayList();
        for(int i=0; i<50000; i++)
            lol.add(1);
        for(int i=0; i<50000; i++)
            lol.add(-1);
        
        Collections.shuffle(lol);
        
        int[] saatavat = new int[n+1];
        for(int i=1; i<n+1; i++)
            saatavat[i]=lol.get(i-1);
        
        int[] mista=new int[99999];
        int[] mihin=new int[99999];
        
        for(int i=0; i<99999; i++){
            mista[i]=i+1;
            mihin[i]=i+2;
        }
        
        suuriTesti(n, saatavat, mista, mihin, true);
    }    
    
    @Test(timeout=5000)
    public void suuri2() {
        int n = 100000;
        
        ArrayList<Integer> lol = new ArrayList();
        for(int i=0; i<50333; i++)
            lol.add(1);
        for(int i=0; i<50667; i++)
            lol.add(-1);
        
        Collections.shuffle(lol);
        
        int[] saatavat = new int[n+1];
        for(int i=1; i<n+1; i++)
            saatavat[i]=lol.get(i-1);
        
        int[] mista=new int[99999];
        int[] mihin=new int[99999];
        
        for(int i=0; i<99999; i++){
            mista[i]=i+1;
            mihin[i]=i+2;
        }
        
        suuriTesti(n, saatavat, mista, mihin, false);
    }    
    
    @Test(timeout=5000)
    public void suuri3() {
        int n = 100000;
        
        ArrayList<Integer> lol = new ArrayList();
        for(int i=0; i<50000; i++)
            lol.add(1);
        for(int i=0; i<50000; i++)
            lol.add(-1);
        
        Collections.shuffle(lol);
        
        int[] saatavat = new int[n+1];
        for(int i=1; i<n+1; i++)
            saatavat[i]=lol.get(i-1);
        
        int[] mista=new int[99999];
        int[] mihin=new int[99999];
        
        for(int i=0; i<99999; i++){
            mista[i]=12345;
            if(i+1<12345)
                mihin[i]=i+1;
            else
                mihin[i]=i+2;
        }
        
        suuriTesti(n, saatavat, mista, mihin, true);
    }    
    
    @Test(timeout=5000)
    public void suuri4() {
        int n = 100000;
        
        int[] saatavat = new int[n+1];
        for(int i=1; i<n+1; i++)
            saatavat[i]=1;
        
        saatavat[50000]=-49999;
        saatavat[50001]=-49999;
        
        int[] mista=new int[99998];
        int[] minne=new int[99998];
        
        for(int i=0; i<99998; i++){
            mista[i]=i+1;
            minne[i]=i+3;
        }
        
        suuriTesti(n, saatavat, mista, minne, true);
    }    
    
    @Test(timeout=5000)
    public void suuri5() {
        int n = 100000;
        
        int[] saatavat = new int[n+1];
        for(int i=1; i<n+1; i++)
            saatavat[i]=1;
        
        saatavat[50000]=-49999;
        saatavat[50001]=-49999;
        saatavat[71298]=2;
        
        int[] mista=new int[99998];
        int[] minne=new int[99998];
        
        for(int i=0; i<99998; i++){
            mista[i]=i+1;
            minne[i]=i+3;
        }
        
        suuriTesti(n, saatavat, mista, minne, false);
    }    
    
    @Test(timeout=5000)
    public void suuri6() {
        int n = 443;
        int[] mista = new int[n*(n-1)/2];
        int[] minne = new int[n*(n-1)/2];
        int c = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = i+1; j <= n; j++) {
                mista[c] = i;
                minne[c] = j;
                c++;
            }
        }
        
        int[] saatavat=new int[444];
        for(int i=1; i<444; i++)
            saatavat[(11*i)%444]=i-222;
        suuriTesti(n, saatavat, mista, minne, true);
    }
    
}



