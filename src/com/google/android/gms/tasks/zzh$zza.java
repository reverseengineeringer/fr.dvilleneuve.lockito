package com.google.android.gms.tasks;

import android.app.Activity;
import android.support.annotation.MainThread;
import com.google.android.gms.internal.zzqo;
import com.google.android.gms.internal.zzqp;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class zzh$zza
  extends zzqo
{
  private final List<WeakReference<zzf<?>>> mListeners = new ArrayList();
  
  private zzh$zza(zzqp paramzzqp)
  {
    super(paramzzqp);
    va.zza("TaskOnStopCallback", this);
  }
  
  public static zza zzv(Activity paramActivity)
  {
    zzqp localzzqp = zzs(paramActivity);
    zza localzza = (zza)localzzqp.zza("TaskOnStopCallback", zza.class);
    paramActivity = localzza;
    if (localzza == null) {
      paramActivity = new zza(localzzqp);
    }
    return paramActivity;
  }
  
  @MainThread
  public void onStop()
  {
    synchronized (mListeners)
    {
      Iterator localIterator = mListeners.iterator();
      while (localIterator.hasNext())
      {
        zzf localzzf = (zzf)((WeakReference)localIterator.next()).get();
        if (localzzf != null) {
          localzzf.cancel();
        }
      }
    }
    mListeners.clear();
  }
  
  public <T> void zzb(zzf<T> paramzzf)
  {
    synchronized (mListeners)
    {
      mListeners.add(new WeakReference(paramzzf));
      return;
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tasks.zzh.zza
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */