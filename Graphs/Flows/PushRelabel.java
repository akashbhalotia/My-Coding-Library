/*
  Push-Relabel algorithm to obtain the Max-Flow/Min-Cut value of a directed graph
  in O(V^2*sqrtE). Multiple edges allowed. Self-edges and bidirectional edges are
  not allowed.
  
  Uses highest-label selction and gap heuristic.
  Lightly tested.
*/

private static final int INT_MAX=Integer.MAX_VALUE;
private static final long LONG_MAX=Long.MAX_VALUE;
private static boolean prime[];
static class PushRelabel
{
    int N, source, sink, height[],count[];
    long[] excess, flow[], cap[];
    ArrayList<Integer> next;

    PushRelabel(int N, int source, int sink)
    {
        this.N=N; this.source=source;this.sink=sink;
        excess=new long[N]; height=new int[N];
        flow=new long[N][N]; cap=new long[N][N];
        next=new ArrayList<>(); count=new int[2*N+1];
    }
    void init()
    {
        height[source]=N; count[0]=N-1;
        excess[source]=LONG_MAX; count[N]=1;
        for(int i=0;i<N;i++) push(source,i); //pre-flow
    }
    void push(int u, int v)
    {
        long lim=Math.min(excess[u],cap[u][v]-flow[u][v]);
        excess[u]-=lim;
        excess[v]+=lim;
        flow[u][v]+=lim;
        flow[v][u]-=lim;
    }
    void relabel(int u)
    {
        int lim=2*N+1; count[height[u]]--;
        for(int i=0;i<N;i++) if(cap[u][i]-flow[u][i]>0) lim=Math.min(lim,height[i]);
        if(lim<2*N+1) height[u]=lim+1; count[height[u]]++;
    }
    void gap(int h)
    {
        for(int i=0;i<N;i++)
        {
            if(height[i]<h) continue;
            count[height[i]]--;
            height[i]=Math.max(height[i],N+1);
            count[height[i]]++;
        }
    }
    void findNext()
    {
        next.clear(); int max=0;
        for(int i=0;i<N;i++) if(i!=source&&i!=sink&&excess[i]>0) max=Math.max(max,height[i]);
        for(int i=0;i<N;i++) if(height[i]==max&&excess[i]>0&&i!=source&&i!=sink) next.add(i);
    }
    void add(int u, int v, long c){cap[u][v]+=c;}
    long getMaxFlow()
    {
        init();findNext();
        while (!next.isEmpty())
        {
            for(int u:next)
            {
                for(int v=0;v<N;v++)
                {
                    if(excess[u]==0) break;
                    if(height[u]==height[v]+1) push(u,v);
                }
                if(excess[u]>0)
                {
                    if(count[height[u]]==1) gap(height[u]);
                    else relabel(u); break;
                }
            }
            findNext();
        }

        long maxFlow=0;
        for(int i=0;i<N;i++) maxFlow+=flow[source][i];
        return maxFlow;
    }
}
