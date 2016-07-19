package com.google.android.gms.common.data;

import java.util.NoSuchElementException;

public class zzg<T>
  extends zzb<T>
{
  private T we;
  
  public zzg(DataBuffer<T> paramDataBuffer)
  {
    super(paramDataBuffer);
  }
  
  public T next()
  {
    if (!hasNext())
    {
      int i = vI;
      throw new NoSuchElementException(46 + "Cannot advance the iterator beyond " + i);
    }
    vI += 1;
    if (vI == 0)
    {
      we = vH.get(0);
      if (!(we instanceof zzc))
      {
        String str = String.valueOf(we.getClass());
        throw new IllegalStateException(String.valueOf(str).length() + 44 + "DataBuffer reference of type " + str + " is not movable");
      }
    }
    else
    {
      ((zzc)we).zzfm(vI);
    }
    return (T)we;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.common.data.zzg
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */