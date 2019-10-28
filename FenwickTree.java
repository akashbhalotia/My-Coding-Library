/*
  Binary-Indexed Tree or BIT.
  1-indexed.
*/

static class BIT
{
    int size;
    int[] table;
        
    BIT(int size) //1-indexed
    {
        table=new int[size+1];
        this.size=size+1;
    }
    void update(int i, int delta)
    {
        while(i<size)
        {
            table[i]+=delta;
            i+=Integer.lowestOneBit(i); //i+=i&-i
        }
    }
    int sum(int i)
    {
        int s=0;
        while (i>0)
        {
            s+=table[i];
            i-=Integer.lowestOneBit(i);//i-=i&-i
            }
        return s;
    }
    int rangeSum(int i, int j){return sum(j)-sum(i-1);}
}
