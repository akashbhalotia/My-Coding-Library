/*
  Basic Trie. Insert strings and check if they are present in the trie.
  
  To add a string: insert(char str[]). 
  To check if a string is present: contains(char str[]).
  To clear the trie: clear().
  
  Inserting takes O(ALPHA*N) total, where ALPHA is the size of the alphabet, and N 
  is the total length of all the input strings. To make it O(N), we can use a Hashmap, 
  but it may have high overheads. Hashmap can also be used for higher alphabet sizes,
  or when the string may contain characters other than lowercase latin letters.
  
  Clear takes O(N).
  Checking if a string exists in the trie just takes O(M), where M is the size of the string to be searched.
*/

private static final int ALPHA=26;
static class Trie
{
    int root=0;
    private ArrayList<Node> trie;
    private class Node
    {
        int next[];
        boolean isLeaf;

        Node()
        {
            next=new int[ALPHA];Arrays.fill(next,-1);
            isLeaf=false;
        }
    }
    Trie()
    {
        trie =new ArrayList<>();
        trie.add(new Node());
    }
    void insert(char str[])
    {
        int v=root;
        for(char ch:str)
        {
            int c=ch-'a';
            if(trie.get(v).next[c]==-1)
            {
                trie.get(v).next[c]= trie.size();
                trie.add(new Node());
            }
            v=trie.get(v).next[c];
        }
        trie.get(v).isLeaf=true;
    }
    boolean contains(char str[])
    {
        int v=root;
        for(char ch:str)
        {
            int c=ch-'a';
            if(trie.get(v).next[c]==-1) return false;
            v=trie.get(v).next[c];
        }
        return trie.get(v).isLeaf;
    }
    void clear(){trie.clear(); trie.add(new Node());}
}
