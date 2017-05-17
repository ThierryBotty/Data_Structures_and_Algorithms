import java.util.*;

public class Main {
        
    public static class Pari implements Comparable<Pari>{
        public Integer s;
        public Long w;
         
        Pari(int s, long w){
            this.s=s;
            this.w=w;
        }
    
    @Override
        public int compareTo(Pari o) {
            if(w.equals(o.w))
                return s.compareTo(o.s);
            return w.compareTo(o.w);
        }
    }
     
    public static long lyhinReitti(int n, int[] mista, int[] minne, long[] matka) {
        ArrayList<Pari>[] vl = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
            vl[i]=new ArrayList();
         
        for(int i=0; i<mista.length; i++){
            vl[mista[i]].add(new Pari(minne[i], matka[i]));
            vl[minne[i]].add(new Pari(mista[i], matka[i]));
        }
         
        long[] dist = new long[n+1];
        boolean[] kasitelty=new boolean[n+1];
         
        for(int i=1; i<=n; i++)
            dist[i]=1000000000000000000l;
         
        
         
        PriorityQueue<Pari> pq=new PriorityQueue();
        dist[1]=0;
        pq.add(new Pari(1,0l));
         
        while(!pq.isEmpty()){
            Pari p=pq.poll();
            int s=p.s;
            long w=p.w;
             
            if(kasitelty[s])
                continue;
             
            kasitelty[s]=true;
             
            for(Pari seuraava:vl[s]){
                if(dist[seuraava.s]>w+seuraava.w){
                    dist[seuraava.s]=w+seuraava.w;
                    pq.add(new Pari(seuraava.s, dist[seuraava.s]));
                }
            }
        }
         
        if(dist[n]==1000000000000000000l)
            return -1;
        return dist[n];
    }
    
    public static void main(String[] args) {
        System.out.println(lyhinReitti(3, new int[] {1, 2}, new int[] {2, 3}, new long[] {5, 3}));
        System.out.println(lyhinReitti(3, new int[] {1, 1}, new int[] {2, 3}, new long[] {2, 3}));
        System.out.println(lyhinReitti(3, new int[] {1, 2, 1}, new int[] {3, 3, 2}, new long[] {9, 1, 1}));
        System.out.println(lyhinReitti(3, new int[] {1, 2, 1}, new int[] {3, 3, 2}, new long[] {1, 9, 9}));
    }        
}

