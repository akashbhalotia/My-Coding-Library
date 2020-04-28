/*
  Matrix Exponentiation.
  
  Need to speed it up.
  
  Refer to:
  1) https://www.hackerearth.com/practice/notes/matrix-exponentiation-1/#c225403
  2) https://github.com/Ashishgup1/Competitive-Coding/blob/master/Matrix%20Struct.cpp
  3) https://codeforces.com/contest/1117/problem/D  : uwi's and xodiac's solutions.
  4) https://codeforces.com/blog/entry/65365?#comment-493813
  
  In case pow is called multiple times, make res and store static, create a 'clear'
  function, and use them, instead of creating them in the pow function everytime.
*/

private static final long MOD=(long)(1e9+7);
static class Matrix
{
    long[][] M; int N;
    Matrix(int N){this.N=N; M=new long[N][N];}
    void makeIdentity(){for(int i=0;i<N;i++) M[i][i]=1;}
    void copy(Matrix source)
    {
        for(int i=0;i<N;i++)
            for(int j=0;j<N;j++)
                M[i][j]=source.M[i][j];
    }
    void multiply(Matrix B, Matrix C)
    {
        for(int i=0;i<N;i++) Arrays.fill(C.M[i],0);
        for(int i=0;i<N;i++)
            for(int j=0;j<N;j++)
                for(int k=0;k<N;k++)
                    C.M[i][j]=add(C.M[i][j],prod(M[i][k],B.M[k][j]));
        copy(C);
    }
    void pow(long b)
    {
        Matrix res=new Matrix(N), store=new Matrix(N);
        res.makeIdentity();
        while (b>0)
        {
            if((b&1)==1) res.multiply(this,store);
            this.multiply(this,store);
            b>>=1;
        }
        copy(res);
    }
}
private static long add(long a, long b){a+=b; return (a>=MOD)?a-MOD:a;}
private static long prod(long a, long b){a*=b; return (a>=MOD)?a%MOD:a;}
