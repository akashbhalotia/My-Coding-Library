/*
  O(N) to create the Z-function array.
  z[i] = longest common prefix between the main string and the suffix starting at i.
*/

private static void computeZArr(char[] str, int[] z)
{
    int l = 0, r = 0, N = str.length;
    for (int i = 1; i < N; i++)
    {
        if (i <= r)
            z[i] = Math.min(r - i + 1, z[i - l]);
        while (i + z[i] < N && str[z[i]] == str[i + z[i]])
            ++z[i];
        if (i + z[i] - 1 > r)
        {
            l = i;
            r = i + z[i] - 1;
        }
    }
}
