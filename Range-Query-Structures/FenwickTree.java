/*
  Binary-Indexed Tree or BIT.
  0-indexed. (Internally it is 1-indexed, but we don't care about that while using it).
  Format: new BIT(N).
  
  Take care to make the table long if any range sum has a chance to exceed int size.
  MOD inside fenwick tree, when needed, will be applied to update, sum and rangeSum functions. 
  MOD inside fenwick tree affects its speed significantly.
*/

static class BIT
{
    int size;
    int[] table;
        
    BIT(int size) 
    {
        table=new int[size+1];
        this.size=size+1;
    }
    void update(int i, int delta)
    {
        i++;
        while(i<size)
        {
            table[i]+=delta;
            i+=Integer.lowestOneBit(i); //i+=i&-i
        }
    }
    int sum(int i)
    {
        int s=0; i++;
        while (i>0)
        {
            s+=table[i];
            i-=Integer.lowestOneBit(i);//i-=i&-i
            }
        return s;
    }
    int rangeSum(int i, int j){return sum(j)-sum(i-1);}
}