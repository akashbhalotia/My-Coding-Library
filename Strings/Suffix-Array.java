/*
  Suffix Array in O(NlogN).
  
  Append '$' to the string, then convert it to character array.
  Then create an object of the class, and send this array. 
  Uncomment lcp creation and buildLCP() if lcp array is needed.
  
  Remember that its length is now one more than the original, and the first suffix
  in the suffix array is "$" now.
  
  Modify the alphabet size if needed by modifying the classes variable. 
  Also modify array c in buildSA() function.
  
  p-> Suffix array. Positions of the suffixes in sorted order.
  c-> Rank or equivalence class array. Position of a suffix in the suffix array.
  lcp-> Longest Common Prefix between suffixes i and i+1 in the suffix array.
  
  lcp(i,j)=min(lcp[i],lcp[i+1],...,lcp[j-1]). Use any RMQ structure.
  For substring comparison, refer to: https://codeforces.com/edu/course/2/lesson/2/5/practice/contest/269656/problem/C
*/

static class SuffixArray
{
    int[] p,c,lcp,pn,cn,freq; int N, classes;
    SuffixArray(char[] str)
    {
        N=str.length; classes=27;
        p=new int[Math.max(N,classes)]; c=new int[Math.max(N,classes)];
        pn=new int[Math.max(N,classes)]; cn=new int[Math.max(N,classes)];
        freq=new int[Math.max(N,classes)]; //lcp=new int[N-1];

        buildSA(str);
        //buildLCP(str);
    }
    void buildSA(char[] str)
    {
        for(int i=0;i<N;i++) pn[i]=i;
        for(int i=0;i<N;i++) c[i]=Math.max(0,str[i]-'a'+1);

        countSort(0);
        radixSort();
    }
    void countSort(int len)
    {
        Arrays.fill(freq,0,classes,0);
        for(int i=0;i<N;i++) freq[c[pn[i]]]++;
        for(int i=1;i<classes;i++) freq[i]+=freq[i-1];
        for(int i=N-1;i>=0;i--) p[--freq[c[pn[i]]]]=pn[i];

        cn[p[0]]=0; classes=1;
        int prevF=p[0], prevS=add(p[0],len);
        for(int i=1;i<N;i++)
        {
            int curF=p[i], curS=add(p[i],len);
            if(c[curF]!=c[prevF]||c[curS]!=c[prevS]) ++classes;
            cn[p[i]]=classes-1; prevF=curF; prevS=curS;
        }
        if (N >= 0) System.arraycopy(cn, 0, c, 0, N);
    }
    void radixSort()
    {
        for(int len=1;len<N;len<<=1)
        {
            for(int i=0;i<N;i++) pn[i]=subtract(p[i],len);
            countSort(len);
        }
    }
    int add(int a, int b){return a+b<N?a+b:a+b-N;}
    int subtract(int a, int b){return a-b>=0?a-b:a-b+N;}
    void buildLCP(char[] str)
    {
        for(int i=0,K=0;i<N;i++)
        {
            if(c[i]==N-1){K=0; continue;}
            int j=p[c[i]+1];
            while (i+K<N&&j+K<N&&str[i+K]==str[j+K]) K++;
            lcp[c[i]]=K;
            if(K>0) K--;
        }
    }
}
