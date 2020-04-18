/*
  Disjoint set union.
*/

static class DSU
{
    int parent[], size[];
    DSU(int N)
    {
        parent=new int[N];
        size=new int[N];
    }
    void make_set(int v)
    {
        parent[v]=v;
        size[v]=1;
    }
    int find_set(int v)
    {
        if(v==parent[v])
            return v;
        return parent[v]=find_set(parent[v]);
    }
    void union_sets(int a, int b)
    {
        a=find_set(a);
        b=find_set(b);
      
        if(a==b) return;
            
        if(size[a]<size[b])
        {
            int tmp=a;
            a=b;
            b=tmp;
        }
        parent[b]=a;
        size[a]+=size[b];
    }
}
