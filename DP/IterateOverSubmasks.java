/*
  Iterates over all submasks of a bitmask. 
  O(2^K), where K is the number of set bits in the mask.
*/

for(int sub=mask; ;sub=(sub-1)&mask)
{
    //Do something
    if(sub==0) break;
}
