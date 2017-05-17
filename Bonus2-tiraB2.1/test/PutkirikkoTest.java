
import fi.helsinki.cs.tmc.edutestutils.Points;
import java.util.Arrays;
import java.util.Random;
import static org.junit.Assert.*;
import org.junit.Test;

@Points("BONUS2_1")
public class PutkirikkoTest {
    static char[][] mk(String s) {
        String[] lines = s.split("\n");
        char[][] out = new char[lines.length][];
        for (int i = 0; i < lines.length; i++) {
            out[i] = lines[i].toCharArray();
        }
        return out;
    }

    static String unmk(char[][] v) {
        String s = "";
        for (char[] rivi : v) {
            for (char c : rivi) {
                s += c;
            }
            s += "\n";
        }
        return s;
    }

    static void t(char[][] v, int out) {
        String viesti = "Palautit väärän vastauksen talolle\n" + unmk(v) + "\n";
        int ret = Putkirikko.putkirikko(v);
        assertEquals(viesti,
                out,
                ret);
    }

    static void t(String s, int out) {
        t(mk(s), out);
    }

    @Test public void testaaYksinkertaisia() {

        t("P",0);
        t("P.",1);
        t("P....",4);
        t("P....P",2);

        t("P.\n..",2);
        t("P..\n...\n...",4);
        t("P..\n.#.\n...",4);
        t("P..\n.#.\n..P",2);

        t("P#P\n.#.\n.#.\n.#.",3);

        t(
                "####...##.\n"+
                "#.##.###..\n"+
                "#.##.##..#\n"+
                "#..P....##\n"+
                "###.##.###\n"+
                "...#.#P##.\n"+
                "##........\n",
                8
                );

    }

    Random r = new Random();

    public int generoi(char[][] talo, int k) {
        int h = talo.length;
        int w = talo[0].length;

        int pituus=0;

        for (char[] rivi : talo) {
            Arrays.fill(rivi,'_');
        }

        for (int i = 0; i<k; i++) {
            int x = r.nextInt(w-1)+1;
            int y = r.nextInt(h-1)+1;
            if (talo[y][x]=='_' && c(talo,x+1,y) && c(talo,x-1,y) && c(talo,x,y+1) && c(talo,x,y-1)) {
                talo[y][x]='P';
                pituus=Math.max(pituus,mato(talo, x, y,0));
                pituus=Math.max(pituus,mato(talo, x, y,1));
                pituus=Math.max(pituus,mato(talo, x, y,2));
                pituus=Math.max(pituus,mato(talo, x, y,3));
            }
        }

        for (char[] rivi : talo) {
            for (int i = 0; i<rivi.length; i++) {
                if (rivi[i]=='_') rivi[i]='#';
            }
        }

        return pituus;
    }

    public int mato(char[][] talo, int x, int y, int dir) {
        //System.out.println("mato "+x+" "+y);
        int pituus=0;
        loop: while (true) {
            //System.out.println("  "+x+" "+y);
            int oldx = x;
            int oldy = y;
            switch (dir) {
                case 0: x--; break;
                case 1: x++; break;
                case 2: y--; break;
                case 3: y++; break;
            }
            if (!c(talo,x,y)) break loop;

            for (int i = y-1; i<=y+1; i+=2) {
                if (!(x==oldx && i==oldy) && !c(talo,x,i)) break loop;
            }
            for (int j = x-1; j<=x+1; j+=2) {
                if (!(j==oldx && y==oldy) && !c(talo,j,y)) break loop;
            }
            //System.out.println("  assign "+x+" "+y);
            talo[y][x]='.';
            pituus ++;
            dir = r.nextInt(4);
        }
        //System.out.println("M\n"+unmk(talo));

        return pituus;
    }

    private boolean c(char[][] talo, int x, int y) {
        return y >= 0 && y < talo.length && x >= 0 && x < talo[0].length
                && talo[y][x]=='_';
    }

    @Test public void testaaIsompia() {

        int N = 20;
        int W = 20;
        int H = 20;
        int K = 7;

        while (N-->0) {
            char[][] talo = new char[H][W];
            int pit = generoi(talo,7);
            //System.out.println("\n"+pit+"\n"+unmk(talo));
            t(talo,pit);
        }


    }


}
