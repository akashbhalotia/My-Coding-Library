private static final long MOD=(int)(1e9+7);
private static long pow(long a, long b)
{
    a%=MOD;
    //b%=(MOD-1);//if MOD is prime
    long res=1;
    while (b>0)
    {
        if((b&1)==1)
            res=(res*a)%MOD;
        a=(a*a)%MOD;
        b>>=1;
    }
    return res%MOD;
}
