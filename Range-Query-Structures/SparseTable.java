/*
  Sparse Table.
  O(NlogN) time and space for building.
  O(1) query for max/min/gcd (Any idempotent function).
*/

static class SparseTable
{
    int pow[], log[], table[][];
    SparseTable(int[] a)
    {
        log=new int[a.length+1]; computeLog(a.length);
        pow=new int[log[a.length]+1]; computePow(log[a.length]);
        table=new int[log[a.length]+1][a.length];build(a);
    }
    void computeLog(int N){log[1]=0;for(int i=2;i<=N;i++) log[i]=log[i>>1]+1;}
    void computePow(int lim){pow[0]=1;for(int i=1;i<=lim;i++) pow[i]=pow[i-1]<<1;}
    int func(int a, int b){return Math.min(a,b);}
    void build(int[] a)
    {
        for(int i=0;i<a.length;i++) table[0][i]=a[i];
        for(int i=1;i<=log[a.length];i++)
            for(int j=0;j+pow[i]<=a.length;j++)
                table[i][j]=func(table[i-1][j],table[i-1][j+pow[i-1]]);
    }
    int query(int l, int r)
    {
        int k=log[r-l+1];
        return func(table[k][l],table[k][r-pow[k]+1]);
    }
}
