import fi.helsinki.cs.tmc.edutestutils.Points;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import static org.junit.Assert.*;
import org.junit.Test;


@Points("8.2")
public class SudokuTest {

    public static String str(int[][] s) {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i<9; i++) {
            for (int j = 0; j<9; j++) {
                b.append(s[i][j]);
            }
            b.append("\n");
        }
        return b.toString();

    }

    public static void tarkasta(String orig, int[][] s) {
        
        String viesti = "Sudokun\n"+orig+"\nRatkaisusi\n"+str(s)+"\non väärin: ";

        for (int i=0; i<9; i++) {
            for (int j=0; j<9; j++) {
                assertTrue(viesti+"Ratkaisusi sisälsi lukuja 0.", s[i][j]!=0);
            }
        }

        HashSet oikea = new HashSet();
        Collections.addAll(oikea,1,2,3,4,5,6,7,8,9);

        HashSet tark = new HashSet();
        for (int rivi = 0; rivi<9; rivi++) {
            tark.clear();
            for (int i = 0; i<9; i++) {
                tark.add(s[rivi][i]);
            }
            assertEquals(viesti+"Rivi "+(rivi+1)+" ei ollut validi.",oikea,tark);
        }

        for (int sarake = 0; sarake<9; sarake++) {
            tark.clear();
            for (int i = 0; i<9; i++) {
                tark.add(s[i][sarake]);
            }
            assertEquals(viesti+"Sarake "+(sarake+1)+" ei ollut validi.",oikea,tark);
        }

        for (int x=0; x<3; x++) {
            for (int y=0; y<3; y++) {
                tark.clear();
                for (int i=0; i<3; i++) {
                    for (int j=0; j<3; j++) {
                        tark.add(s[3*x+i][3*y+j]);
                    }
                }
                assertEquals(viesti+"Neliö "+(x+1)+","+(y+1)+" ei ollut validi.",oikea,tark);
            }
        }
    }

    public static int[][] mk(String... rs) {
        int[][] s = new int[9][9];
        for (int i = 0; i<9; i++) {
            for (int j = 0; j<9; j++) {
                s[i][j] = Character.digit(rs[i].charAt(j), 10);
            }
        }
        return s;
    }

    public static void t(int[][] s) {
        String o = str(s);
        Sudoku.ratkaise(s);
        tarkasta(o,s);
    }

    @Test
    public void testaaYksiPuuttuu() {

        for (int i = 0; i<9; i++) {
            for (int j = 0; j<9; j++) {
                int[][] s = mk("846937152",
                               "319625847",
                               "752184963",
                               "285713694",
                               "463859271",
                               "971246385",
                               "127598436",
                               "638471529",
                               "594362718");
                s[i][j]=0;
                t(s);
            }
        }
    }

    @Test
    public void testaaKahdeksanPuuttuu() {
        Random r = new Random();
        for (int krt=0; krt<10; krt++) {
            int[][] s = mk("619842735",
                           "748635192",
                           "325971684",
                           "872459316",
                           "136287549",
                           "594316827",
                           "987164253",
                           "251793468",
                           "463528971");

            for (int i=0; i<8; i++) {
                s[r.nextInt(9)][r.nextInt(9)] = 0;
            }
            t(s);
        }
    }

    @Test
    public void testaaEsimerkit() {
        t(mk("800930002",
             "009000040",
             "702100960",
             "200000090",
             "060000070",
             "070006005",
             "027008406",
             "030000500",
             "500062008"));
        t(mk("005300000",
             "800000020",
             "070010500",
             "400005300",
             "010070006",
             "003200080",
             "060500009",
             "004000030",
             "000009700"));
        t(mk("000000000",
             "000003085",
             "001020000",
             "000507000",
             "004000100",
             "090000000",
             "500000070",
             "002010000",
             "000040009"));
    }

}
