/*
  O(N*K) to create the table.
*/

private static final long MOD=(int)(1e9+7);
private static void nCr(long C[][], int N, int K)
{
    for(int i=0;i<=N;i++)
    {
        for(int j=0;j<=Math.min(i,K);j++)
        {
            if(j==0||j==i)
                C[i][j]=1%MOD;
            else
                C[i][j]=(C[i-1][j-1]+C[i-1][j])%MOD;
        }
    }
}

/*
  O(K) for a particular nCr.
*/

private static long nCr(int N, int K)
{
    long res=1;
    K=Math.min(K,N-K);

    for(int i=0;i<K;i++)
    {
        res=(res*(N-i))%MOD;
        res=(res*inverse(i+1))%MOD;
    }
    return res%MOD;
}
