import java.util.HashSet;
import java.util.Random;
import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.MockStdio;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;
import java.util.ArrayList;

@Points("B.1")
public class OperaatioitaTest {

    static Node n(int value, Node next) {
        return new Node(value,next);
    }
    static Node n(int value) {
        return new Node(value);
    }
    static boolean eq(Node a, Node b) {
        HashSet<Node> ha = new HashSet<Node>();
        HashSet<Node> hb = new HashSet<Node>();

        while(a!=null || b!=null) {
            if (a==null || b==null)
                return false;

            if (ha.contains(a) || hb.contains(b)) {
                fail("Sykli havaittu listassa!");
            }
            ha.add(a);
            hb.add(b);

            if (a.getValue()!=b.getValue())
                return false;
            a=a.getNext();
            b=b.getNext();
        }
        return true;
    }

    @Test
    public void testaaReverseYksi() {
        Node l = n(3);
        Node k = Operaatioita.reverse(l);

        assertTrue("Kun käännetään yhden kokoinen lista, pitäisi palauttaa sama alkio!",
                   l==k);
        assertEquals("Kun käännetään yhden kokoinen lista, next pitäisi olla null!",
                     null,
                     k.getNext());
    }

    @Test public void testaaReverseKaksi() {
        Node lnext = n(5);
        Node l = n(4,lnext);
        Node k = Operaatioita.reverse(l);

        assertTrue("Kun käännetään kahden kokoinen lista, et palauta viitettä alkioon, joka oli alunperin listan viimeisenä.",
                   lnext==k);
        assertTrue("Kun käännettiin kahden kokoinen lista, palautit väärän listan.",
                   eq(k, n(5, n(4))));
    }

    public double reversePitempi(int... vals) {

        Node rev = null;
        for (int i=0; i<vals.length; i++) {
            rev=n(vals[i],rev);
        }
        Node lis = null;
        ArrayList<Node> lisnodes = new ArrayList<Node>();
        for (int i=vals.length-1; i>=0; i--) {
            lis=n(vals[i],lis);
            lisnodes.add(lis);
        }

        CpuStopwatch csw = new CpuStopwatch(CpuStopwatch.Mode.USER);
        Node rev2 = Operaatioita.reverse(lis);
        double aika = csw.getElapsedTime();

        assertTrue("Palautit väärän listan kun käännettiin pituuden "+vals.length+" listaa",
                   eq(rev2,rev));
        
        for (Node n = rev2; n!=null; n=n.getNext()) {
            assertTrue("Et uudelleenkäytä annetun listan solmuja kun käännettiin pituuden "+vals.length+" listaa",
                       lisnodes.contains(n));
        }

        return aika;
    }

    @Test public void testaaReversePitempi() {

        reversePitempi(1,2,3);        
        reversePitempi(1,2,3,4);
        reversePitempi(1,2,3,4,5);
        reversePitempi(1,2,3,4,5,6);
        reversePitempi(1,2,3,4,5,6,7);

    }

    @Test public void testaaAika() {

        int KOKO=30000;
        Random r = new Random();

        int[] vals = new int[KOKO];
        for (int i = 0; i<vals.length; i++) {
            vals[i]=r.nextInt();
        }

        double aika = reversePitempi(vals);
        long millisekunnit = (long) (aika*1000);
        long raja = 20;//100;

        assertTrue("Käytit liikaa aikaa! Käytit "+millisekunnit+"ms joka on yli "+raja+"ms",
                   raja>=millisekunnit);

    }

    @Test public void testaaCut() {
        Node b = n(0,n(9000));
        Node a = n(5,n(4,b));

        String s = a.toString();

        Node b2 = Operaatioita.cut(a,2);

        assertTrue("Kun kutsuttiin cut("+s+",2), palautit väärin.",eq(b2,n(0,n(9000))));

        assertTrue("Kun kutsuttiin cut("+s+",2), et palauttanut listassa ollutta solmua.",b2==b);

        assertTrue("Et muokannut alkuperäistä listaa kutsussa cut("+s+",2)",eq(a,n(5,n(4))));

                   
    }

    @Test public void testaaCut2() {

        Node x = n(3,n(5,n(6,n(7,n(9000)))));
        Node x0 = n(3,n(5,n(6,n(7,n(9000)))));
        Node x2 = Operaatioita.cut(x,0);

        assertTrue("Kutsun cut(x,0) pitäisi palauttaa x muuttumattomana!",eq(x2,x0) && x2==x);

    }

    public Node l(int... vals) {
        Node lis = null;
        for (int i=vals.length-1; i>=0; i--) {
            lis=n(vals[i],lis);
        }
        return lis;
    }

    @Test public void testaaCut3() {

        Node a = l(1,5,6,8,9,3,4,5,7,5,3,6,8,4,2,4,7,8,7);
        Node a0 = l(1,5,6,8,9,3,4,5,7,5);
        Node a1 = l(3,6,8,4,2,4,7,8,7);

        String virhe = "väärin tapauksessa cut("+a+",10)!";

        Node out = Operaatioita.cut(a,10);

        assertTrue("Palautit "+virhe, eq(out,a1));
        assertTrue("Muokkasit annettua listaa "+virhe, eq(a, a0));

    }

}
