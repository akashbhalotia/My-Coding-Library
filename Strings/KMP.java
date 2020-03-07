/*
	Knuth-Morris-Pratt.
	O(N) to create the pi array.
	pi[i] = length of the longest proper suffix ending at i, which is also a proper prefix.
*/

private static void calcPi(char str[], int pi[])
{
    int N=str.length,j=0;
    for(int i=1;i<N;i++)
    {
        while (j>0&&str[i]!=str[j])j=pi[j];
        if(str[i]==str[j])j++;
        pi[i]=j;
    }
}
