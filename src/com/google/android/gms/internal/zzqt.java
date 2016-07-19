package com.google.android.gms.internal;

import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzab;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.WeakHashMap;

public class zzqt
{
  private final Set<zzqs<?>> mg = Collections.newSetFromMap(new WeakHashMap());
  
  public void release()
  {
    Iterator localIterator = mg.iterator();
    while (localIterator.hasNext()) {
      ((zzqs)localIterator.next()).clear();
    }
    mg.clear();
  }
  
  public <L> zzqs<L> zzb(@NonNull L paramL, Looper paramLooper)
  {
    zzab.zzb(paramL, "Listener must not be null");
    zzab.zzb(paramLooper, "Looper must not be null");
    paramL = new zzqs(paramLooper, paramL);
    mg.add(paramL);
    return paramL;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzqt
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */