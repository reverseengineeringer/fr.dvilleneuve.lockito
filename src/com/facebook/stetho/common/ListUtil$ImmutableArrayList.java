package com.facebook.stetho.common;

import java.util.AbstractList;

final class ListUtil$ImmutableArrayList<E>
  extends AbstractList<E>
  implements ListUtil.ImmutableList<E>
{
  private final Object[] mArray;
  
  public ListUtil$ImmutableArrayList(Object[] paramArrayOfObject)
  {
    mArray = paramArrayOfObject;
  }
  
  public E get(int paramInt)
  {
    return (E)mArray[paramInt];
  }
  
  public int size()
  {
    return mArray.length;
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.common.ListUtil.ImmutableArrayList
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */