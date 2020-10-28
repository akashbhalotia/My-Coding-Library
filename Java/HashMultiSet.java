/*
  Hash MultiSet in java. Implemented using HashMap.
  It can be iterated over.
  
  Methods are self-explanatory.
*/

static class HashMultiSet<T> implements Iterable<T>
{
    private final HashMap<T,Integer> map;
    private int size;

    public HashMultiSet(){map=new HashMap<>(); size=0;}
    public void clear(){map.clear(); size=0;}
    public int size(){return size;}
    public boolean contains(T a){return map.containsKey(a);}
    public boolean isEmpty(){return size==0;}
    public Integer get(T a){return map.getOrDefault(a,0);}
    public void add(T a, int count)
    {
        int cur=get(a);map.put(a,cur+count); size+=count;
        if(cur+count==0) map.remove(a);
    }
    public void addOne(T a){add(a,1);}
    public void remove(T a, int count){add(a,Math.max(-get(a),-count));}
    public void removeOne(T a){remove(a,1);}
    public void removeAll(T a){remove(a,Integer.MAX_VALUE-10);}

    public Iterator<T> iterator()
    {
        return new Iterator<>()
        {
            private final Iterator<T> iter = map.keySet().iterator();
            private int count = 0; private T curElement;

            public boolean hasNext(){return iter.hasNext()||count>0;}
            public T next()
            {
                if(count==0)
                {
                    curElement=iter.next();
                    count=get(curElement);
                }
                count--; return curElement;
            }
        };
    }
}
