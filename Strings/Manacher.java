/*
  Manacher's algorithm for finding all palindromic substrings in O(N).
  
  d1[i] = radius of odd length palindrome around i. Actual length = 2 * d1[i] - 1.
  d2[i] = radius of even length palindrome around i-1 and i. Actual length = 2 * d2[i].
  
  Similar to Z algorithm.
  Let l to r be a palindrome. Let i<=r. Both halves are symmetrical, thus answer for i 
  can be obtained from the first half.
  
  For shorter code, refer to this: https://tinyurl.com/v4lc4h5
*/

private static void oddLengthPalin(char str[], int N, int d1[]) //centre = i
{
    int i,l=0,r=-1,len;
    for(i=0;i<N;i++)
    {
        len=(i>r)?1:Math.min(d1[l+r-i],r-i+1);
        while (i-len>=0&&i+len<N && str[i-len]==str[i+len]) len++;

        d1[i]=len--;
        if(i+len>r){l=i-len;r=i+len;}
    }
}
private static void evenLengthPalin(char str[], int N, int d2[]) //centre is i-1 and i. Stored in i.
{
    int i,l=0,r=-1,len;
    for(i=0;i<N;i++)
    {
        len=(i>r)?0:Math.min(d2[l+r-i+1],r-i+1);
        while (i-len-1>=0&&i+len<N && str[i-len-1]==str[i+len]) len++;

        d2[i]=len--;
        if(i+len>r){l=i-len-1;r=i+len;}
    }
}
