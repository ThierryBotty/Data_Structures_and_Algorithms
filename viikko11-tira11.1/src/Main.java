import java.util.*;

public class Main {
    public static long[][] lyhinReitti(int n, long[][] et) {
        for(int v=1; v<=n; v++)
            for(int a=1; a<=n; a++)
                for(int  b=1; b<=n; b++)
                    et[a][b]=Math.min(et[a][b], et[a][v]+et[v][b]);
        return et;
    }
    
    public static void main(String[] args) {
        long[][] et1={{0,0,0,0,0},
                      {0,0,9,1,2},
                      {0,9,0,3,1},
                      {0,1,3,0,7},
                      {0,2,1,7,0}};
        lyhinReitti(4, et1);
        //Oikea vastaus:
        //0,0,0,0,0
        //0,0,3,1,2
        //0,3,0,3,1
        //0,1,3,0,3
        //0,2,1,3,0
    }        
}

