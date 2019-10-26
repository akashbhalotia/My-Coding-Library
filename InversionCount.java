/*
  Using mergeSort. 
  Format: countInv(array, size).
*/

private static long merge(int a[], int tmp[], int l, int mid, int r)
{
    long count=0;

    int i=l,j=mid+1,k=l;
    while(i<=mid&&j<=r)
    {
        if(a[i]<=a[j])
            tmp[k++]=a[i++];
        else
        {
            tmp[k++]=a[j++];
            count+=mid+1-i;
        }
    }
    while (i<=mid)
        tmp[k++]=a[i++];
    while (j<=r)
        tmp[k++]=a[j++];
    for(i=l;i<=r;i++)
        a[i]=tmp[i];

    return count;
}
private static long mergeSort(int a[], int tmp[], int l, int r)
{
    long count=0;
    if(l<r)
    {
        int mid=l+(r-l)/2;
        count+=mergeSort(a,tmp,l,mid);
        count+=mergeSort(a,tmp,mid+1,r);

        count+=merge(a,tmp,l,mid,r);
    }
    return count;
}
private static long countInv(int a[], int N)
{
    int tmp[]=new int[N];
    return mergeSort(a,tmp,0,N-1);
}
