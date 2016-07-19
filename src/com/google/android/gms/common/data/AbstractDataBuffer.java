package com.google.android.gms.common.data;

import android.os.Bundle;
import java.util.Iterator;

public abstract class AbstractDataBuffer<T>
  implements DataBuffer<T>
{
  protected final DataHolder tk;
  
  protected AbstractDataBuffer(DataHolder paramDataHolder)
  {
    tk = paramDataHolder;
    if (tk != null) {
      tk.zzv(this);
    }
  }
  
  @Deprecated
  public final void close()
  {
    release();
  }
  
  public abstract T get(int paramInt);
  
  public int getCount()
  {
    if (tk == null) {
      return 0;
    }
    return tk.getCount();
  }
  
  @Deprecated
  public boolean isClosed()
  {
    return (tk == null) || (tk.isClosed());
  }
  
  public Iterator<T> iterator()
  {
    return new zzb(this);
  }
  
  public void release()
  {
    if (tk != null) {
      tk.close();
    }
  }
  
  public Iterator<T> singleRefIterator()
  {
    return new zzg(this);
  }
  
  public Bundle zzaqy()
  {
    return tk.zzaqy();
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.common.data.AbstractDataBuffer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */