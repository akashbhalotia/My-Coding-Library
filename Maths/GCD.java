/*
  Euclidean GCD.
  Format: _gcd(a,b).
*/

private static int _gcd(int a, int b)
{
    if(b==0)
        return a;
    return _gcd(b,a%b);
}
private static int _lcm(int a, int b){return a/_gcd(a,b)*b;}

/*  
  Extended GCD. Provides coefficients x,y
  for which ax+by=gcd(a,b).
  
  Format: _gcd(a,b).
*/

private static int X,Y;
private static int _gcd(int a, int b)
{
    if(a==0)
    {
        X=0;
        Y=1;
        return b;
    }
    int g=_gcd(b%a,a);
    int x1=X,y1=Y;

    X=y1-(b/a)*x1;
    Y=x1;

    return g;
}
