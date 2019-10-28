/*
  0-indexed.
  Lazy propagation.
  Has feature to send an array to initialise segtree.
  
  Format: new Segtree(a,N).
  Query: minimum(l,r).
  Update: increment(l,r).
*/

static class Segtree
{
    int N;
    int[] lo,hi,min,delta;
    private static final int INF=Integer.MAX_VALUE;

    Segtree(int arr[], int N)
    {
        this.N=N;
        lo=new int[4*N+1];
        hi=new int[4*N+1];
        min=new int[4*N+1];
        delta=new int[4*N+1];

        init(1,0,N-1,arr);
    }
    void init(int i, int a, int b, int arr[])
    {
        lo[i]=a;
        hi[i]=b;

        if(a==b)
        {
            min[i]=arr[a];
            return;
        }

        int m=(a+b)/2;
        init(2*i,a,m,arr);
        init(2*i+1,m+1,b,arr);

        update(i);
    }
    void prop(int i)
    {
        delta[2*i]+=delta[i];
        delta[2*i+1]+=delta[i];
        delta[i]=0;
    }
    void update(int i)
    {
        min[i]=Math.min(min[2*i]+delta[2*i],min[2*i+1]+delta[2*i+1]);
    }
    void increment(int a, int b, int val){increment(1,a,b,val);}
    void increment(int i, int a, int b, int val)
    {
        if(b<lo[i]||hi[i]<a)
            return;
        if(a<=lo[i]&&hi[i]<=b)
        {
            delta[i]+=val;
            return;
        }
        prop(i);

        increment(2*i,a,b,val);
        increment(2*i+1,a,b,val);

        update(i);
    }
    int minimum(int a, int b){return minimum(1,a,b);}
    int minimum(int i, int a, int b)
    {
        if(b<lo[i]||hi[i]<a)
            return INF;
        if(a<=lo[i]&&hi[i]<=b)
            return min[i]+delta[i];

        prop(i);

        int minLeft=minimum(2*i,a,b);
        int minRight=minimum(2*i+1,a,b);

        update(i);

        return Math.min(minLeft,minRight);
    }
}

/*
  To not pass array as input, but update individual values as they come in.
  Format: new Segtree(N)
*/


    Segtree(int N)
    {
        this.N=N;
        lo=new int[4*N+1];
        hi=new int[4*N+1];
        min=new int[4*N+1];
        delta=new int[4*N+1];

        init(1,0,N-1);
    }
    void init(int i, int a, int b)
    {
        lo[i]=a;
        hi[i]=b;

        if(a==b)
            return;

        int m=(a+b)/2;
        init(2*i,a,m);
        init(2*i+1,m+1,b);
    }
