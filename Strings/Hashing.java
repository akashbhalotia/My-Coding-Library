/*
	Hashing. (Converts a string into an integer, so that it can be compared with other strings in O(1)).
	
	O(N) pre-processing.
	Hash for a substring too can be obtained in O(1), using the getHash(l, r) function.
	
	Hash is computed as: str[0]+str[1]*P+str[2]*(P^2)+...str[N-1]*(P^(N-1)), all under modulo MOD.
	More details later.
*/

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
