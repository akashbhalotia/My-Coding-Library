/*
  Aho-Corasick algorithm for finding all patterns in a text in linear time O(M+N+ans).
  
  N is the total size of all the patterns. In reality, N*ALPHA, where ALPHA is the alphabet size.
  M is the size of the text. 
  ans is the sum of all occurrence of all patterns in the text, which can be O(N^2) worst case.
  
  First we build a prefix automaton of the patterns, in the form of a trie.
  Then the automaton searches for the patterns in a text, character by character.
  
  1) Insert all the patterns one by one, along with their ids: insert(id, char[] pattern). O(N).
  2) Call the build() function to build an automaton of the inserted patterns. O(N).
  3) call find(char text[]). Returns an ArrayList<Integer>, which has the indices of the 
     each pattern present in the text. O(M+ans).
  
  Does not support empty patterns. 
  
  Supports duplicate patterns, and also counts them correctly, but only stores the index
  of their last occurrence in the list of patterns. If we want all the indices, 
  make 'wordID' a list, instead of an int.
  
  If we only want the total count of all occurrences of all patterns, 
  we can get that in O(M+N), using 'totalCount'. It is int, as there can be at max 
  N number of patterns ending at each node. However, THEIR SUM CAN BE N^2, THUS MAKE SURE
  TO MAKE THE SUM LONG.
  
  Supports only lowercase latin letters. Can be modified to support only uppercase. 
  For other alphabet, make 'next' array a Hashmap (may have overheads).
  
  Refer to https://www.toptal.com/algorithms/aho-corasick-algorithm in case of any doubts.
  To find the words that start at each position, reverse all input.
  (Patterns, text and also the array which stores the number of patterns 
  ending at each position, if such an array is needed).
   
   Problem: https://codeforces.com/contest/1202/problem/E
   Solution: https://codeforces.com/contest/1202/submission/75570323
*/

private static final int ALPHA=26;
static class AhoCorasick
{
	int root=0, size=1;
	private ArrayList<Node> trie;
	private class Node
	{
	    int next[], parent, wordID, link, nextWord, count, totalCount;
	    char pch;
	    boolean isLeaf;

	    Node()
	    {
	        next=new int[ALPHA];Arrays.fill(next,-1);
	        isLeaf=false; wordID=nextWord=link=parent=-1; totalCount=count=0;pch='\0';
	    }
	}
	AhoCorasick()
	{
	    trie =new ArrayList<>();
	    trie.add(new Node());
	}
	void insert(int idx, char str[])
	{
	    int v=root;
	    for(char ch:str)
	    {
	        int c=ch-'a';
	        if(trie.get(v).next[c]==-1)
	        {
	            trie.get(v).next[c]= size;
	            trie.add(new Node());

	            trie.get(size).parent=v;
	            trie.get(size).pch=ch;
	            size++;
	        }
	        v=trie.get(v).next[c];
	    }
	    trie.get(v).isLeaf=true;
	    trie.get(v).wordID=idx;
	    trie.get(v).count++;
	    trie.get(v).totalCount++;
	}
	void clear(){trie.clear(); trie.add(new Node()); size=1;}
	void calcLink(int v)
	{
	    if(v==root||trie.get(v).parent==root){trie.get(v).link=trie.get(v).nextWord=root; return;}

	    int nextBetter=trie.get(trie.get(v).parent).link, link=root;
	    int c=trie.get(v).pch-'a';

	    while (true)
	    {
	        if(trie.get(nextBetter).next[c]!=-1){link=trie.get(nextBetter).next[c]; break;}
	        if(nextBetter==root) break;
	        nextBetter=trie.get(nextBetter).link;
	    }

	    trie.get(v).link=link;
	    if(trie.get(link).isLeaf) trie.get(v).nextWord=link;
	    else trie.get(v).nextWord=trie.get(link).nextWord;
	    trie.get(v).totalCount+=trie.get(link).totalCount;
	}
	void build()
	{
	    ArrayDeque<Integer> Q=new ArrayDeque<>();
	    Q.add(root);

	    while (!Q.isEmpty())
	    {
	        int v=Q.poll();
	        calcLink(v);

	        for(int i=0;i<ALPHA;i++)
	        {
	            if(trie.get(v).next[i]==-1) continue;
	            Q.add(trie.get(v).next[i]);
	        }
	    }
	}
	ArrayList<Integer> find(char text[])
	{
	    long count=0; int v=root;
	    ArrayList<Integer> idx=new ArrayList<>();

	    for(char ch:text)
	    {
	        int c=ch-'a';
	        while (true)
	        {
	            if(trie.get(v).next[c]!=-1) {v=trie.get(v).next[c];break;}
	            if(v==root) break;
	            v=trie.get(v).link;
	        }

	        int current=(trie.get(v).isLeaf)?v:trie.get(v).nextWord;
	        count+=trie.get(current).totalCount;
	        
	        while (current!=root) //don't include if indices aren't required
	        {
	            idx.add(trie.get(current).wordID);
	            current=trie.get(current).nextWord;
	        }
	    }
	    return idx;
	}
}
