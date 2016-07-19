package com.facebook.stetho.common;

import java.util.AbstractList;

final class ListUtil$OneItemImmutableList<E>
  extends AbstractList<E>
  implements ListUtil.ImmutableList<E>
{
  private final E mItem;
  
  public ListUtil$OneItemImmutableList(E paramE)
  {
    mItem = paramE;
  }
  
  public E get(int paramInt)
  {
    if (paramInt == 0) {
      return (E)mItem;
    }
    throw new IndexOutOfBoundsException();
  }
  
  public int size()
  {
    return 1;
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.common.ListUtil.OneItemImmutableList
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */