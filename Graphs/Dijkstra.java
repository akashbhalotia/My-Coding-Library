/*
  Single Source Shortest Path - Dijkstra's Algorithm. 
  O(ElogV) on sparse graphs. 
  Only non-negative edge weights allowed.
  
  Priority Queue implementations are generally faster than any TreeSet ones.
*/

public class SSSP
{
    private static final long INF=(long)(1e18);
    private static ArrayDeque<Pair>[] edge;
    static class Pair implements Comparable<Pair>
    {
        int f; long s;
        Pair(int f, long s){this.f = f;this.s = s;}
        public int compareTo(Pair b){return Long.compare(this.s,b.s);}
    }
    private static void Dijkstra(int s, long[] dist)
    {
        Arrays.fill(dist,INF);
        dist[s]=0;
        
        PriorityQueue<Pair> PQ=new PriorityQueue<>();
        PQ.add(new Pair(s,0));
        
        while (!PQ.isEmpty())
        {
            Pair tmp=PQ.poll();
            int u=tmp.f; long cur=tmp.s;
            if(cur!=dist[u]) continue;
            
            for(Pair p:edge[u])
            {
                int v=p.f; long d=p.s;
                if(dist[u]+d<dist[v])
                {
                    dist[v]=dist[u]+d;
                    PQ.add(new Pair(v,dist[v]));
                }
            }
        }
    }
}
