package com.google.android.gms.internal;

import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.internal.zzd.zzf;
import java.util.Collections;
import java.util.Map;

class zzqh$zzd
  implements zzd.zzf
{
  private final zzpo<?> rG;
  private final Api.zze uJ;
  
  public zzqh$zzd(Api.zze paramzze, zzpo<?> paramzzpo)
  {
    uJ = paramzzpo;
    zzpo localzzpo;
    rG = localzzpo;
  }
  
  @WorkerThread
  public void zzh(@NonNull ConnectionResult paramConnectionResult)
  {
    if (paramConnectionResult.isSuccess())
    {
      uJ.zza(null, Collections.emptySet());
      return;
    }
    ((zzqh.zzc)zzqh.zzg(uG).get(rG)).onConnectionFailed(paramConnectionResult);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzqh.zzd
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */