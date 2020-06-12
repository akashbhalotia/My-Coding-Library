/*
  Smallest Prime Factor in O(NloglogN).
*/

public class SPF
{
    private static final int lim=(int)(1e7);
    private static int[] spf;
    private static void sieve()
    {
        spf=new int[lim+5];
        spf[0]=spf[1]=-1;

        for(int i=2;i<=lim;i++)
        {
            if(spf[i]==0)
            {
                spf[i] = i;
                if ((long) i * i <= lim)
                {
                    for (int j = i * i; j <= lim; j += i)
                        if (spf[j] == 0) spf[j] = i;
                }
            }
        }
    }
}
