import java.util.*;

public class Main {
    static int[][] taso;
    static boolean[] joKayty;
    static int matka;
    
    public static int laske(int kaupunki, int taso, int kustannus){
        joKayty[kaupunki]=true;
         
        if(taso == matka-1){
            joKayty[kaupunki]=false;
            return kustannus+Main.taso[kaupunki][0];
        }
         
        int inf=1000000000;
        for(int i=0; i<matka; i++)
            if(!joKayty[i])
                inf=Math.min(inf, laske(i, taso+1, kustannus+Main.taso[kaupunki][i]));
         
        joKayty[kaupunki]=false;
        return inf;
    }
     
    public static int kierros(int[][] taso) {
        matka=taso.length;
        Main.taso=taso;
        joKayty=new boolean[matka];
         
        return laske(0, 0, 0);
    }
    
    public static void main(String[] args) {
        int[][] e1={{0,3,2,1},
                    {3,0,4,2},
                    {2,4,0,4},
                    {1,2,4,0}};
        System.out.println(kierros(e1));    //9
        
        int[][] e2={{0,2,1,1},
                    {2,0,1,1},
                    {1,1,0,2},
                    {1,1,2,0}};
        System.out.println(kierros(e2));    //4
        
        int[][] e3={{0,1,2,1},
                    {1,0,1,2},
                    {2,1,0,1},
                    {1,2,1,0}};
        System.out.println(kierros(e3));    //4
        
        int[][] e4={{0,1,2,3},
                    {1,0,4,5},
                    {2,4,0,6},
                    {3,5,6,0}};
        System.out.println(kierros(e4));    //14
    }        
}


