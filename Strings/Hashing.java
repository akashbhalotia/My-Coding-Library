static class Hashing
{
    long[] hash,pow;
    long P, MOD;
    int N;

    Hashing(char str[], long P, long MOD)
    {
        this.N=str.length;this.P=P;this.MOD=MOD;
		    hash=new long[N+1];pow=new long[N+1];
        init(str);
    }
    void init(char str[])
    {
		    pow[0]=1;
        for(int i=N-1;i>=0;i--)
        {
            hash[i]=((hash[i+1]*P)%MOD+(str[i]-'a'+1))%MOD;
            pow[N-i]=(pow[N-i-1]*P)%MOD;
        }
        pow[N]=(pow[N-1]*P)%MOD;
    }
    long getHash(){return getHash(0,N-1);}
    long getHash(int l, int r){return (MOD-(hash[r+1]*pow[r-l+1])%MOD+hash[l])%MOD;}
}
