/*
  Finds smallest x, such that a^x (mod m) = b (mod m), 
  or returns -1, if there's no such x.
  
  O(sqrt(m)).
  
  https://cp-algorithms.com/algebra/discrete-log.html#toc-tgt-4
*/

private static final HashMap<Long,Long> map=new HashMap<>();
private static long _gcd(long a, long b){return a==0?b:_gcd(b%a,a);}
private static long _discreteLog(long a, long b, long m)
{
    a%=m; b%=m;
    long k=1,cnt=0,g;
    while ((g=_gcd(a,m))>1)
    {
        if(b==k) return cnt;
        if(b%g!=0) return -1;

        b/=g; m/=g; ++cnt;
        k=(k*a/g)%m;
    }

    map.clear();
    long N=(long)Math.sqrt(m)+1,an=1;
    for(int i=1;i<=N;i++) an=(an*a)%m;

    for(long q=0,cur=b;q<=N;q++)
    {
        map.put(cur,q);
        cur=(cur*a)%m;
    }
    for(long p=1,cur=k;p<=N;p++)
    {
        cur=(cur*an)%m;
        if(map.containsKey(cur))return N*p-map.get(cur)+cnt;
    }
    return -1;
}
