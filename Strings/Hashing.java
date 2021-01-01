/*
    Hashing. (Converts a string into an integer, so that it can be compared with other strings in O(1)).
	
    O(N) pre-processing.
    Hash for a substring too can be obtained in O(1), using the getHash(l, r) function.
	
    Hash is computed as: str[0]+str[1]*P+str[2]*(P^2)+...str[N-1]*(P^(N-1)), all under modulo MOD.
    This is computed using Horner's rule.
    
    To get the value of substring hash in constant time, the hashes are stored as suffix sums.
    When querying for substring hash, l to r, hash[l] includes the extra value of hash[r+1].
    Thus, hash[r+1]*(P raised to size of the substring) is subtracted from hash[l],
    and the intuition can be easily gained by trying it out for a few substrings of a string.
    
    Some values for P: 31, 53, 137, 257, 991, 99991, 999917. Or choose any from here: https://tinyurl.com/vhd3xvv
    Some values for MOD: 1e9 +7, +9, +21, +33, +87.
    
    ERRORS: Make sure to modify "str[i]-'a'+1" to appropriate domain in case the domain is not lowercase.
*/

static class Hashing
{
    long[] hash,pow;
    final long P, MOD;
    int N;

    Hashing(char[] str, long P, long MOD)
    {
        this.N=str.length;this.P=P;this.MOD=MOD;
        hash=new long[N+1];pow=new long[N+1];
        init(str);
    }
    void init(char[] str)
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
    boolean isEqual(Hashing h2){return this.getHash()==h2.getHash();}
    boolean isEqual(Hashing h2, int l1, int r1, int l2, int r2){return this.getHash(l1,r1)==h2.getHash(l2,r2);}
}
static class DoubleHash
{
    Hashing h1,h2;
    DoubleHash(char str[], long P1, long MOD1, long P2, long MOD2)
    {
        h1=new Hashing(str,P1,MOD1);
        h2=new Hashing(str,P2,MOD2);
    }
    boolean isEqual(DoubleHash dh2){return this.h1.isEqual(dh2.h1)&&this.h2.isEqual(dh2.h2);}
    boolean isEqual(DoubleHash dh2, int l1, int r1, int l2, int r2)
    {
        return this.h1.isEqual(dh2.h1,l1,r1,l2,r2)&&this.h2.isEqual(dh2.h2,l1,r1,l2,r2);
    }
}

/*
	Optimised.
	CAUTION: May be prone to errors. Not well tested. 
*/

static class Hashing
{
    long[] hash,pow;
    final long P, MOD;
    int N;

    Hashing(char[] str, long P, long MOD)
    {
        this.N=str.length;this.P=P;this.MOD=MOD;
        hash=new long[N+1];pow=new long[N+1];
        init(str);
    }
    void init(char[] str)
    {
        pow[0]=1;
        for(int i=N-1;i>=0;i--)
        {
            hash[i]=add(prod(hash[i+1],P),(str[i]-'a'+1));
            pow[N-i]=prod(pow[N-i-1],P);
        }
        pow[N]=prod(pow[N-1],P);
    }
    long getHash(){return getHash(0,N-1);}
    long getHash(int l, int r){return subtract(hash[l],prod(hash[r+1],pow[r-l+1]));}

    long add(long a, long b){a+=b; return (a>=MOD)?a-MOD:a;}
    long subtract(long a, long b){a-=b; return (a<0)?a+MOD:a;}
    long prod(long a, long b){a*=b; return (a>=MOD)?a%MOD:a;}
}
