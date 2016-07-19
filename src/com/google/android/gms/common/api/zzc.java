package com.google.android.gms.common.api;

import android.content.Context;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Pair;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.internal.zzpo;
import com.google.android.gms.internal.zzpr.zza;
import com.google.android.gms.internal.zzqh;
import com.google.android.gms.internal.zzqi;
import com.google.android.gms.internal.zzqt;
import com.google.android.gms.internal.zzrb;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class zzc<O extends Api.ApiOptions>
{
  private final Context mContext;
  private final int mId;
  private final Api<O> pD;
  private final zzqt rE;
  private final O rF;
  private final zzpo<O> rG;
  private final zzqh rH;
  private final GoogleApiClient rI;
  private final AtomicBoolean rJ = new AtomicBoolean(false);
  private final AtomicInteger rK = new AtomicInteger(0);
  private final Looper zzahv;
  
  public zzc(@NonNull Context paramContext, Api<O> paramApi, O paramO) {}
  
  public zzc(@NonNull Context paramContext, Api<O> paramApi, O paramO, Looper paramLooper)
  {
    zzab.zzb(paramContext, "Null context is not permitted.");
    zzab.zzb(paramApi, "Api must not be null.");
    zzab.zzb(paramLooper, "Looper must not be null.");
    mContext = paramContext.getApplicationContext();
    pD = paramApi;
    rF = paramO;
    zzahv = paramLooper;
    rE = new zzqt();
    rG = new zzpo(pD, rF);
    rI = new zzqi(this);
    paramContext = zzqh.zza(mContext, this);
    rH = ((zzqh)first);
    mId = ((Integer)second).intValue();
  }
  
  private <A extends Api.zzb, T extends zzpr.zza<? extends Result, A>> T zza(int paramInt, @NonNull T paramT)
  {
    paramT.zzaot();
    rH.zza(this, paramInt, paramT);
    return paramT;
  }
  
  private <TResult, A extends Api.zzb> Task<TResult> zza(int paramInt, @NonNull zzrb<A, TResult> paramzzrb)
  {
    TaskCompletionSource localTaskCompletionSource = new TaskCompletionSource();
    rH.zza(this, paramInt, paramzzrb, localTaskCompletionSource);
    return localTaskCompletionSource.getTask();
  }
  
  public Context getApplicationContext()
  {
    return mContext;
  }
  
  public int getInstanceId()
  {
    return mId;
  }
  
  public Looper getLooper()
  {
    return zzahv;
  }
  
  public void release()
  {
    boolean bool = true;
    if (rJ.getAndSet(true)) {
      return;
    }
    rE.release();
    zzqh localzzqh = rH;
    int i = mId;
    if (rK.get() > 0) {}
    for (;;)
    {
      localzzqh.zzd(i, bool);
      return;
      bool = false;
    }
  }
  
  public <A extends Api.zzb, T extends zzpr.zza<? extends Result, A>> T zza(@NonNull T paramT)
  {
    return zza(0, paramT);
  }
  
  public <TResult, A extends Api.zzb> Task<TResult> zza(zzrb<A, TResult> paramzzrb)
  {
    return zza(0, paramzzrb);
  }
  
  public void zzanu()
  {
    rK.incrementAndGet();
  }
  
  public void zzanv()
  {
    if ((rK.decrementAndGet() == 0) && (rJ.get())) {
      rH.zzd(mId, false);
    }
  }
  
  public Api<O> zzanw()
  {
    return pD;
  }
  
  public O zzanx()
  {
    return rF;
  }
  
  public zzpo<O> zzany()
  {
    return rG;
  }
  
  public GoogleApiClient zzanz()
  {
    return rI;
  }
  
  public <A extends Api.zzb, T extends zzpr.zza<? extends Result, A>> T zzb(@NonNull T paramT)
  {
    return zza(1, paramT);
  }
  
  public <TResult, A extends Api.zzb> Task<TResult> zzb(zzrb<A, TResult> paramzzrb)
  {
    return zza(1, paramzzrb);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.common.api.zzc
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */