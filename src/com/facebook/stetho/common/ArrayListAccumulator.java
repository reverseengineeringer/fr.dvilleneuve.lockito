package com.facebook.stetho.common;

import java.util.ArrayList;

public final class ArrayListAccumulator<E>
  extends ArrayList<E>
  implements Accumulator<E>
{
  public void store(E paramE)
  {
    add(paramE);
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.common.ArrayListAccumulator
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */