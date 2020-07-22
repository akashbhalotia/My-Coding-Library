/*
  Finds the smallest primitive root of a number N.
  
  Primitive root, g, of a number N is a number that generates all numbers coprime to N, mod N. 
  In other words, g is a primitive root of N, if for all a such that gcd(a,N)=1, there exists an integer k,
  such that g^k=a mod n.
  
  Primitive roots exist iff N=1, 4, Prime, (odd prime)^k, 2*(odd prime)^k.
  
  _pow-> gives a^b mod m in O(log b).
  _factorization-> gives phi(N) if mode is false, 
                   else gives a list of all prime factors of N, in O(sqrt(N)).
  
  root is O(log^6 n).
  Run Time of algorithm is O(root * log phi(n) * log n), which is approximately O(log^8 n).
  
  https://cp-algorithms.com/algebra/primitive-root.html
*/

private static int phi;
private static final ArrayList<Integer> fact=new ArrayList<>();
private static long _pow(long a, long b, long m)
{
    a%=m; b%=phi;
    long res=1;
    while (b>0)
    {
        if((b&1)==1) res=res*a%m;
        a=a*a%m;
        b>>=1;
    }
    return res%m;
}
private static int _factorization(int N, boolean mode)
{
    int res=N; if(mode) fact.clear();
    for(int i=2;i*i<=N;i++)
    {
        if(N%i==0)
        {
            res-=res/i;
            while (N%i==0) N/=i;
            if(mode) fact.add(i);
        }
    }
    if(N>1) {res-=res/N; if(mode) fact.add(N);}
    return res;
}
private static int _generator(int N)
{
    phi=_factorization(N,false);
    _factorization(phi,true);

    for(int root=2;root<=N;root++)
    {
        boolean check=true;
        for(int i=0;i<fact.size()&&check;i++)
            check = _pow(root, phi / fact.get(i), N) != 1;
        if(check) return root;
    }
    return -1;
}
