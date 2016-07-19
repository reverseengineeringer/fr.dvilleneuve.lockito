package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.BinderThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

public final class zzd$zzg
  extends zzt.zza
{
  private zzd xx;
  private final int xy;
  
  public zzd$zzg(@NonNull zzd paramzzd, int paramInt)
  {
    xx = paramzzd;
    xy = paramInt;
  }
  
  private void zzasd()
  {
    xx = null;
  }
  
  @BinderThread
  public void zza(int paramInt, @NonNull IBinder paramIBinder, @Nullable Bundle paramBundle)
  {
    zzab.zzb(xx, "onPostInitComplete can be called only once per call to getRemoteService");
    xx.zza(paramInt, paramIBinder, paramBundle, xy);
    zzasd();
  }
  
  @BinderThread
  public void zzb(int paramInt, @Nullable Bundle paramBundle)
  {
    Log.wtf("GmsClient", "received deprecated onAccountValidationComplete callback, ignoring", new Exception());
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.common.internal.zzd.zzg
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */