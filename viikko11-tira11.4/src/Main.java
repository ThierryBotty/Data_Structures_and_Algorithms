import java.util.*;

public class Main {
    public static class Pari implements Comparable<Pari>{
        public Integer s;
        public Integer w;
          
        Pari(int s, int w){
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
     
    public static int lentomaara(int n, int[] juna1, int[] juna2, int[] lento1, int[] lento2) {
        ArrayList<Pari>[] vl = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
            vl[i]=new ArrayList();
          
        for(int i=0; i<juna1.length; i++){
            vl[juna1[i]].add(new Pari(juna2[i], 0));
            vl[juna2[i]].add(new Pari(juna1[i], 0));
        }
         
        for(int i=0; i<lento1.length; i++){
            vl[lento1[i]].add(new Pari(lento2[i], 1));
            vl[lento2[i]].add(new Pari(lento1[i], 1));
        }
          
        int[] dist = new int[n+1];
        boolean[] kasitelty=new boolean[n+1];
          
        for(int i=1; i<=n; i++)
            dist[i]=1000000000;
       
          
        PriorityQueue<Pari> pq=new PriorityQueue();
        dist[1]=0;
        pq.add(new Pari(1,0));
          
        while(!pq.isEmpty()){
            Pari p=pq.poll();
            int s=p.s;
            int w=p.w;
              
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
          
        if(dist[n]==1000000000)
            return -1;
        return dist[n];
    }
     
    public static void main(String[] args) {
        System.out.println(lentomaara(3, new int[] {1}, new int[] {2}, new int[] {1, 2}, new int[] {2, 3}));
        System.out.println(lentomaara(3, new int[] {1}, new int[] {3}, new int[] {1, 2}, new int[] {2, 3}));
        System.out.println(lentomaara(3, new int[] {}, new int[] {}, new int[] {1, 2}, new int[] {2, 3}));
        System.out.println(lentomaara(3, new int[] {1, 2}, new int[] {2, 3}, new int[] {1, 2}, new int[] {2, 3}));
    }       
}
    
    public static void main(String[] args) {
        System.out.println(yhteysaika(4, new int[] {1,2,3}, new int[] {2,3,4}, new long[] {1,2,1})); //2
        System.out.println(yhteysaika(4, new int[] {1,1,2,3}, new int[] {2,3,4,4}, new long[] {1,5,8,7})); //7
        System.out.println(yhteysaika(5, new int[] {1,2,2,3,4}, new int[] {2,3,4,5,5}, new long[] {10,1,8,1,9})); //10
        System.out.println(yhteysaika(5, new int[] {1,2,2,3,4}, new int[] {2,3,4,5,5}, new long[] {1,1,8,1,9})); //1
    }        
}

