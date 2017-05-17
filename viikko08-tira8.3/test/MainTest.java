import org.junit.Test;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;
import java.util.Arrays;

@Points("8.3")
public class MainTest {
    public String teeOsa(String osa) {
        if (osa == null) return "asunto vapautuu";
        else return "lisätään (" + osa + ")";
    }
    
    public String teeKuvaus(String[] lista, int loppu) {
        if (loppu == 0) return teeOsa(lista[0]);
        String kuvaus = teeOsa(lista[0]);
        for (int i = 1; i <= loppu-1; i++) {
            kuvaus += ", " + teeOsa(lista[i]);
        }
        kuvaus += " ja " + teeOsa(lista[loppu]);
        return kuvaus;
    }
    
    public void pieniTesti(String[] lista, String[] tulos) {
        Toimisto t = new Toimisto();
        int k = 0;
        for (int i = 0; i < lista.length; i++) {
            if (lista[i] == null) {
                String uusi = t.annaAsunto();
                String kuvaus = teeKuvaus(lista, i);
                assertTrue("Luokkasi toimii väärin, kun " + kuvaus + ".",
                           tulos[k].equals(uusi));
                k++;
            } else {
                String[] osat = lista[i].split(",");
                t.lisaaJonoon(osat[0], Integer.parseInt(osat[1]));
            }
        }
    }
    
    public void suuriTesti(String[] lista, String[] tulos) {
        Toimisto t = new Toimisto();
        int k = 0;
        for (int i = 0; i < lista.length; i++) {
            if (lista[i] == null) {
                String uusi = t.annaAsunto();
                assertTrue("Luokkasi toimii väärin suurella syötteellä.",
                           tulos[k].equals(uusi));
                k++;
            } else {
                String[] osat = lista[i].split(",");
                t.lisaaJonoon(osat[0], Integer.parseInt(osat[1]));
            }
        }
    }
    
    @Test(timeout=1000)
    public void esimerkki() {
        String[] lista = {"Uolevi,8", "Maija,4", null, "Liisa,5", null, null};
        String[] tulos = {"Uolevi", "Liisa", "Maija"};
        pieniTesti(lista, tulos);
    }

    @Test(timeout=1000)
    public void pieni1() {
        String[] lista = {"Uolevi,3", "Maija,2", "Liisa,1", null, null, null};
        String[] tulos = {"Uolevi", "Maija", "Liisa"};
        pieniTesti(lista, tulos);
    }

    @Test(timeout=1000)
    public void pieni2() {
        String[] lista = {"Uolevi,1", "Maija,2", "Liisa,3", null, null, null};
        String[] tulos = {"Liisa", "Maija", "Uolevi"};
        pieniTesti(lista, tulos);
    }
    
    @Test(timeout=1000)
    public void pieni3() {
        String[] lista = {"Liisa,1", "Maija,2", "Uolevi,3", null, null, null};
        String[] tulos = {"Uolevi", "Maija", "Liisa"};
        pieniTesti(lista, tulos);
    }
    
    @Test(timeout=1000)
    public void pieni4() {
        String[] lista = {"Liisa,3", "Maija,2", "Uolevi,1", null, null, null};
        String[] tulos = {"Liisa", "Maija", "Uolevi"};
        pieniTesti(lista, tulos);
    }
    
    @Test(timeout=1000)
    public void pieni5() {
        String[] lista = {"Uolevi,1", "Uolevi,2", null, null};
        String[] tulos = {"Uolevi", "Uolevi"};
        pieniTesti(lista, tulos);
    }    
    
    @Test(timeout=1000)
    public void pieni6() {
        String[] lista = {"Uolevi,1", "Liisa,1", null, null};
        String[] tulos = {"Liisa", "Uolevi"};
        pieniTesti(lista, tulos);
    }    
        
    @Test(timeout=5000)
    public void suuri1() {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        int n = 100000;
        String lista[] = new String[n];
        for (int i = 0; i < n/2; i++) lista[i] = "Uolevi,"+(i+1);
        for (int i = n/2; i < n; i++) lista[i] = null;
        String tulos[] = new String[n/2];
        for (int i = 0; i < n/2; i++) tulos[i] = "Uolevi";
        suuriTesti(lista, tulos);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    @Test(timeout=5000)
    public void suuri2() {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        int n = 100000;
        String lista[] = new String[n];
        String nimet[] = {"Uolevi", "Maija", "Liisa"};
        for (int i = 0; i < n/2; i++) lista[i] = nimet[i%3]+",1";
        for (int i = n/2; i < n; i++) lista[i] = null;
        String tulos[] = new String[n/2];
        for (int i = 0; i < n/2; i++) tulos[i] = nimet[i%3];
        Arrays.sort(tulos);
        suuriTesti(lista, tulos);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    
}
