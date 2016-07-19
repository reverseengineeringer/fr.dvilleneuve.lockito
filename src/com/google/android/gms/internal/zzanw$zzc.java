package com.google.android.gms.internal;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

abstract class zzanw$zzc<T>
  implements Iterator<T>
{
  zzanw.zzd<K, V> bfo = bfl.bfi.bfo;
  zzanw.zzd<K, V> bfp = null;
  int bfq = bfl.modCount;
  
  private zzanw$zzc(zzanw paramzzanw) {}
  
  final zzanw.zzd<K, V> c()
  {
    zzanw.zzd localzzd = bfo;
    if (localzzd == bfl.bfi) {
      throw new NoSuchElementException();
    }
    if (bfl.modCount != bfq) {
      throw new ConcurrentModificationException();
    }
    bfo = bfo;
    bfp = localzzd;
    return localzzd;
  }
  
  public final boolean hasNext()
  {
    return bfo != bfl.bfi;
  }
  
  public final void remove()
  {
    if (bfp == null) {
      throw new IllegalStateException();
    }
    bfl.zza(bfp, true);
    bfp = null;
    bfq = bfl.modCount;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzanw.zzc
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */