/*
  Lowest common ancestor of two nodes in a tree using binary lifting.
  Pre-processing takes O(NlogN). Queries take O(logN) each.
  
  isAncestor(u,v) tells if u is an ancestor of v.
  Call init() and DFS(root,root) for pre-processing.
  LCA will be answered by calling LCA(u,v).
*/

//created by Whiplash99
import java.io.*;
import java.util.*;
public class A
{
    private static int tin[], tout[], timer=0, N, logN, anc[][];
    private static ArrayDeque<Integer> edge[];

    private static void DFS(int u, int p)
    {
        tin[u]=timer++;
        anc[u][0]=p;

        for(int i=1;i<=logN;i++)
            anc[u][i]=anc[anc[u][i-1]][i-1];

        for(int v:edge[u])
        {
            if(v==p) continue;
            DFS(v,u);
        }

        tout[u]=timer++;
    }
    private static boolean isAncestor(int u, int v)
    {
        return tin[u]<=tin[v]&&tout[v]<=tout[u];
    }
    private static int LCA(int u, int v)
    {
        if(isAncestor(u,v)) return u;
        if(isAncestor(v,u)) return v;

        for(int i=logN;i>=0;i--)
        {
            if (!isAncestor(anc[u][i], v))
                u = anc[u][i];
        }
        return anc[u][0];
    }
    private static void init()
    {
        logN=Integer.numberOfTrailingZeros(Integer.highestOneBit(N));
        tin=new int[N];
        tout=new int[N];
        anc=new int[N][logN+1];
    }
    public static void main(String[] args) throws IOException
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        int i;
        N=Integer.parseInt(br.readLine().trim());
        edge=new ArrayDeque[N];
        for(i=0;i<N;i++) edge[i]=new ArrayDeque<>();
        init();

        DFS(0,0);
    }
}
