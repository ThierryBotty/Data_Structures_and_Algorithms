
import fi.helsinki.cs.tmc.edutestutils.Points;
import org.junit.Test;
import static org.junit.Assert.*;

@Points("BONUS2_3")
public class RatsuTest {

    public static void t(int x0, int x1, int y0, int y1, int ret) {
        assertEquals("ratsu("+x0+","+x1+","+y0+","+y1+") meni väärin",
                ret,
                Ratsu.ratsu(x0,x1,y0,y1));
    }

    @Test
    public void testaa() {
        t(0,0,2,1,1);
        t(0,0,1,2,1);
        t(2,1,0,0,1);
        t(1,2,0,0,1);
        t(0,0,0,0,0);
        t(0,0,4,2,2);

        t(0,0,7,7,6);
        t(0,0,5,6,5);
        t(5,5,0,0,4);
        t(3,7,7,3,4);

        t(7,6,0,0,5);

        t(0,0,1,1,4);
        t(5,5,4,4,2);
    }

}
