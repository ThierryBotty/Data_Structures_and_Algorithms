import org.junit.Test;

import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@Points("3.2")
public class MainTest {   
    public void pieniTesti(String[] luvut, int tulos) {
        String sisalto = Arrays.toString(luvut);
        int ulos = Main.montakoErilaista(luvut);
        assertTrue("Taulukossa " + sisalto + " on " + tulos +
                   " erilaista merkkijonoa, mutta metodisi palauttaa luvun '" + ulos + "'.", ulos == tulos);
    }

    public void suuriTesti(String[] luvut, int tulos) {
        CpuStopwatch kello=new CpuStopwatch(CpuStopwatch.Mode.USER);
        int ulos = Main.montakoErilaista(luvut);
        assertTrue("Metodi toimii väärin suurella syötteellä.", ulos == tulos);
        double aika=kello.getElapsedTime();
        assertTrue("Metodisi kuluttaa liikaa aikaa: "+kello+"s, kun aikaraja on 1s.",
                   aika<=1);
    }
    
    @Test(timeout=5000)
    public void esimerkit() {
        pieniTesti(new String[] {"apina", "banaani", "cembalo"}, 3);
        pieniTesti(new String[] {"a", "b", "c", "d", "a"}, 4);
        pieniTesti(new String[] {"abba", "abbb", "bbba", "babb", "bbab"}, 5);
        pieniTesti(new String[] {"babb", "abbb", "bbba", "babb", "bbab"}, 4);
    }
    
    @Test(timeout=5000)
    public void pienet() {
        pieniTesti(new String[] {"ab", "ba", "ca", "cd", "cc", "aa"}, 6);
        pieniTesti(new String[] {"ab", "bb", "ca", "cd", "cc", "ac"}, 6);
        pieniTesti(new String[] {"ab", "ba", "ca", "cd", "cc", "ba"}, 5);
        pieniTesti(new String[] {"x", "y", "z", "x", "x", "x"}, 3);
        pieniTesti(new String[] {"x", "xx", "xxx", "xxxx", "xxxxx", "xxxxxxx"}, 6);
        pieniTesti(new String[] {"a", "a", "a", "a", "a", "a"}, 1);
        pieniTesti(new String[] {"b", "b", "b", "b", "b", "xyz"}, 2);
    }

    @Test(timeout=5000)
    public void suuri1() {
        ArrayList<String> lol=new ArrayList<String>();
        for(char c1='a'; c1<='z'; c1++)
            for(char c2='a'; c2<='z'; c2++)
                for(char c3='a'; c3<='z'; c3++)
                    lol.add(""+c1+c2+c3);
        Collections.shuffle(lol);
        String[] lol2=new String[lol.size()];
        for(int i=0; i<lol.size(); i++)
            lol2[i]=lol.get(i);
        suuriTesti(lol2, 17576);
    }
    
    @Test(timeout=5000)
    public void suuri2() {
        ArrayList<String> lol=new ArrayList<String>();
        for(int asdlol=0; asdlol<5; asdlol++)
            for(char c1='a'; c1<='z'; c1++)
                for(char c2='a'; c2<='z'; c2++)
                    for(char c3='a'; c3<='z'; c3++)
                        lol.add(""+c1+c2+c3);
        Collections.shuffle(lol);
        String[] lol2=new String[lol.size()];
        for(int i=0; i<lol.size(); i++)
            lol2[i]=lol.get(i);
        suuriTesti(lol2, 17576);
    }
    
    @Test(timeout=5000)
    public void suuri3() {
        ArrayList<String> lol=new ArrayList<String>();
        for(int asdlol=0; asdlol<5; asdlol++)
            for(char c1='a'; c1<='c'; c1++)
                for(char c2='a'; c2<='c'; c2++)
                    for(char c3='a'; c3<='c'; c3++)
                        for(char c4='a'; c4<='c'; c4++)
                            for(char c5='a'; c5<='c'; c5++)
                                for(char c6='a'; c6<='c'; c6++)
                                    for(char c7='a'; c7<='c'; c7++)
                                        for(char c8='a'; c8<='c'; c8++)
                                            for(char c9='a'; c9<='c'; c9++)
                                                lol.add("x"+c1+c2+c3+c4+c5+c6+c7+c8+c9);
        Collections.shuffle(lol);
        String[] lol2=new String[lol.size()];
        for(int i=0; i<lol.size(); i++)
            lol2[i]=lol.get(i);
        suuriTesti(lol2, 19683);
    }
    
    @Test(timeout=5000)
    public void suuri4() {
        ArrayList<String> lol=new ArrayList<String>();
        for(char c1='a'; c1<='d'; c1++)
            for(char c2='a'; c2<='d'; c2++)
                for(char c3='a'; c3<='d'; c3++)
                    for(char c4='a'; c4<='d'; c4++)
                        for(char c5='a'; c5<='d'; c5++)
                            for(char c6='a'; c6<='d'; c6++)
                                for(char c7='a'; c7<='d'; c7++)
                                    for(char c8='a'; c8<='d'; c8++)
                                        lol.add(""+c1+c2+c3+c4+c5+c6+c7+c8);
        lol.add("zezima");
        Collections.shuffle(lol);
        String[] lol2=new String[lol.size()];
        for(int i=0; i<lol.size(); i++)
            lol2[i]=lol.get(i);
        suuriTesti(lol2, 65537);
    }
}

