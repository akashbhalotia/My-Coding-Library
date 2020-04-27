/*
  Chinese-Remainder theorem. O(NlogM).
  
  Finds the smallest value of x (modulo product of all m), which satisfies:
  - x = b1 (mod m1)
  - x = b2 (mod m2)
  - x = b3 (mod m3)
  .
  .
  .
  - x = bn (mod mn)
  
  All m should be co-prime.
  Product of all m should fit inside long.
  
  _gcd: extended euclidean gcd, useful for obtaining inverse
        when m is not prime.
*/


private static long X,Y;
private static long _gcd(long a, long b)
{
	if(a==0){X=0; Y=1; return b;}
	long g=_gcd(b%a,a);
	long x1=X,y1=Y;

	X=y1-(b/a)*x1;
	Y=x1;

	return g;
}
private static long inverse(long a, long mod){_gcd(a,mod); return (X%mod+mod)%mod;}
private static long CRT(long[] b, long[] m)
{
   long prod=1, res=0; int i, N=b.length;

   for(i=0;i<N;i++) prod*=m[i];
   for(i=0;i<N;i++) res=(res+b[i]*prod/m[i]*inverse(m[i],prod)%prod)%prod;
   
   return res%prod;
}
