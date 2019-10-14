private static final long MOD=(int)(1e9+7);
private static long multiply(long a, long b)
{
    a%=MOD;
    b%=MOD;
    long res=0;
    while (b>0)
    {
        if((b&1)==1)
            res=(res+a)%MOD;
        a=(a<<1)%MOD;
        b>>=1;
    }
    return res%MOD;
}
