/*
  Disjoint set union.
*/

static class DSU
{
    int[] parent, size;
    DSU(int N)
    {
        parent=new int[N];
        size=new int[N];
        for(int i=0;i<N;i++) make_set(i);
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
    boolean same_set(int a, int b)
    {
        return find_set(a)==find_set(b);
    }
    boolean union_sets(int a, int b)
    {
        a=find_set(a);
        b=find_set(b);

        if(a==b) return false;

        if(size[a]<size[b])
        {
            int tmp=a;
            a=b;
            b=tmp;
        }
        parent[b]=a;
        size[a]+=size[b];

        return true;
    }
}
