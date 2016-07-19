package com.facebook.stetho.common;

import java.util.AbstractList;

final class ListUtil$TwoItemImmutableList<E>
  extends AbstractList<E>
  implements ListUtil.ImmutableList<E>
{
  private final E mItem0;
  private final E mItem1;
  
  public ListUtil$TwoItemImmutableList(E paramE1, E paramE2)
  {
    mItem0 = paramE1;
    mItem1 = paramE2;
  }
  
  public E get(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      throw new IndexOutOfBoundsException();
    case 0: 
      return (E)mItem0;
    }
    return (E)mItem1;
  }
  
  public int size()
  {
    return 2;
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.common.ListUtil.TwoItemImmutableList
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */