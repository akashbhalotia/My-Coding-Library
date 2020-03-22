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
