/*
	Knuth-Morris-Pratt.
	O(N) to create the pi array.
	pi[i] = length of the longest proper suffix ending at i, which is also a proper prefix.
	j = fallBack position+1
*/

private static void calcPi(char str[], int pi[], int N)
{
    for (int i = 1, j=0; i < N; i++)
    {
	while (j > 0 && str[i] != str[j]) j = pi[j-1];
        if (str[i] == str[j]) j++;
        pi[i] = j;
    }
}

/*
    To perform pattern matching on a text, one way is to make a new string: pattern+'$'+text.
    Then compute pi for this string, and print the positions which have a pi value of pattern.length
*/

public static int countMatches1(char text[], char pat[])
{
    String s=new String(pat)+"$"+new String(text);
    int M=text.length, N=pat.length, count=0;
    int[] pi=new int[M+N+1];
    calcPi(s.toCharArray(),pi,M+N+1);

    for(int i=N+1;i<M+N+1;i++)
    {
        if(pi[i]==N)
        {
            System.out.println("Found at index "+(i-2*N)); //0-indexed
            count++;
        }
    }
    return count;
}
