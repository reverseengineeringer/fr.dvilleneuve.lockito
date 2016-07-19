package com.facebook.stetho.common;

import java.util.AbstractList;

final class ListUtil$FourItemImmutableList<E>
  extends AbstractList<E>
  implements ListUtil.ImmutableList<E>
{
  private final E mItem0;
  private final E mItem1;
  private final E mItem2;
  private final E mItem3;
  
  public ListUtil$FourItemImmutableList(E paramE1, E paramE2, E paramE3, E paramE4)
  {
    mItem0 = paramE1;
    mItem1 = paramE2;
    mItem2 = paramE3;
    mItem3 = paramE4;
  }
  
  public E get(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      throw new IndexOutOfBoundsException();
    case 0: 
      return (E)mItem0;
    case 1: 
      return (E)mItem1;
    case 2: 
      return (E)mItem2;
    }
    return (E)mItem3;
  }
  
  public int size()
  {
    return 4;
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.common.ListUtil.FourItemImmutableList
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */