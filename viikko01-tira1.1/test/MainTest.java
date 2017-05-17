import org.junit.Test;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;

@Points("1.1")
public class MainTest {
    
    //ebin testi
    public void testaaPieni(String s, char c, int tulos) {
        int ulos=Main.laske(s,c);
        assertTrue("Merkkijonossa "+s+" esiintyy kirjain '"+c+"' "+tulos+" kertaa, mutta ohjelmasi antaa vastaukseksi "+ulos+".",
                    Main.laske(s, c) == tulos);
    }
    
    public void testaaSuuri(String s, char c, int tulos) {
        assertTrue("Metodisi toimii väärin suurella syötteellä.",
                   Main.laske(s, c) == tulos);
        
    }
    
    @Test(timeout=1000)
    public void esimerkit() {
        testaaPieni("ABBA", 'B', 2);
        testaaPieni("BANAANI", 'A', 3);
        testaaPieni("APINA", 'Z', 0);
        testaaPieni("CEMBALO", 'C', 1);
    }

    @Test(timeout=1000)
    public void pienet() {
        testaaPieni("AAAA", 'A', 4);
        testaaPieni("ABCDE", 'E', 1);
        testaaPieni("LOLLERO", 'L', 3);
        testaaPieni("ZEBRA", 'Z', 1);
    }

    @Test(timeout=1000)
    public void suuri1() {
        StringBuilder s=new StringBuilder();
        for(int i=0; i<100000; i++)
            s.append('A');
        testaaSuuri(s.toString(), 'A', 100000);
    }

    @Test(timeout=1000)
    public void suuri2() {
        StringBuilder s=new StringBuilder();
        for(int i=0; i<100000; i++)
            s.append('A');
        testaaSuuri(s.toString(), 'B', 0);
    }

    @Test(timeout=1000)
    public void suuri3() {
        StringBuilder s=new StringBuilder();
        for(int i=0; i<100000; i++)
            if(i%3==0)
                s.append('A');
            else
                s.append('B');
        testaaSuuri(s.toString(), 'B', 66666);
    }

    @Test(timeout=1000)
    public void suuri4() {
        StringBuilder s=new StringBuilder();
        String asd="ABCDEFGHIJKLMNOPQRSTUWVXYZ";
        for(int i=0; i<1000; i++)
            s.append(asd);

        testaaSuuri(s.toString(), 'L', 1000);
        
    }

}
