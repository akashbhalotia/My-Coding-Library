/*
  Centroid Decomposition.
  
  Call initDecompo(). 
  Then call decompose(0,-1).
  
  par[u] stores the parent of u in the decomposed tree.
  size[u] stores the subtree size of u.
  found[u] checks if u is the centroid of some component.
  
  getCentroid() finds the centroid of this component. 
  DFS2(u) computes the subtree sizes of all subtrees of u.
  
  Preprocessing takes O(NlogN). 
  Doesn't use a set, so the original graph is intact. 
  
  Example problem: https://codeforces.com/problemset/problem/342/E
*/

private static int par[], size[];
private static boolean[] found;

private static int DFS2(int u, int p)
{
    size[u]=1;
    for(int v:edge[u])
    {
        if(v==p||found[v]) continue;
        size[u]+=DFS2(v,u);
    }
    return size[u];
}
private static int getCentroid(int u, int p, int NN) 
{
    for(int v:edge[u])
    {
        if(found[v]||v==p) continue;
        if(size[v]>NN/2) return getCentroid(v,u,NN);
    }
    return u;
}
private static void decompose(int u, int p)
{
    u=getCentroid(u,-1,DFS2(u,u)); found[u]=true; par[u]=p;
    for(int v:edge[u]) if(!found[v]) decompose(v,u);
}
private static void initDecompo(){par=new int[N]; size=new int[N]; found=new boolean[N];}
