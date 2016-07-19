package com.google.android.gms.internal;

import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.zzc;

public class zzqi<O extends Api.ApiOptions>
  extends zzpz
{
  private final zzc<O> uP;
  
  public zzqi(zzc<O> paramzzc)
  {
    super("Method is not supported by connectionless client. APIs supporting connectionless client must not call this method.");
    uP = paramzzc;
  }
  
  public Looper getLooper()
  {
    return uP.getLooper();
  }
  
  public void zza(zzrc paramzzrc)
  {
    uP.zzanu();
  }
  
  public void zzb(zzrc paramzzrc)
  {
    uP.zzanv();
  }
  
  public <A extends Api.zzb, R extends Result, T extends zzpr.zza<R, A>> T zzc(@NonNull T paramT)
  {
    return uP.zza(paramT);
  }
  
  public <A extends Api.zzb, T extends zzpr.zza<? extends Result, A>> T zzd(@NonNull T paramT)
  {
    return uP.zzb(paramT);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzqi
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */