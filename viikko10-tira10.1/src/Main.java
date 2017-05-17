import java.util.*;

public class Main {
    static ArrayList<Integer>[] lista;
    static int[] vari;
    static boolean loytyiko;
     
    static void apu(int s){
        vari[s]=1;
        for(int seuraava:lista[s]){
            if(vari[seuraava]==0)
                apu(seuraava);
            if(vari[seuraava]==1)
                loytyiko=true;
        }
        vari[s]=2;
    }
    public static boolean onkoSyklia(int n, int[] mista, int[] minne) {
        vari=new int[n+1];
         
        lista=new ArrayList[n+1];
        for(int i=0; i<n+1; i++)
            lista[i]=new ArrayList();
         
        for(int i=0; i<mista.length; i++)
            lista[mista[i]].add(minne[i]);
         
         
        loytyiko=false;
        for(int s=1; s<=n; s++)
            if(vari[s]==0)
                apu(s);
        return loytyiko;
    
    }
    
    public static void main(String[] args) {
        System.out.println(onkoSyklia(3, new int[] {1, 2, 3}, new int[] {2, 3, 1}));    // true
        System.out.println(onkoSyklia(3, new int[] {1, 2}, new int[] {2, 3}));          // false
        System.out.println(onkoSyklia(3, new int[] {1, 2, 1}, new int[] {2, 3, 3}));    // false
        System.out.println(onkoSyklia(4, new int[] {2, 3, 4}, new int[] {3, 4, 2}));    // true
    }        
}

