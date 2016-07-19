package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.internal.zzab;

public final class zzt$zzb
{
  private boolean alL;
  private final long alN;
  private final String zzaxn;
  private long zzcvh;
  
  public zzt$zzb(zzt paramzzt, String paramString, long paramLong)
  {
    zzab.zzhs(paramString);
    zzaxn = paramString;
    alN = paramLong;
  }
  
  @WorkerThread
  private void zzbui()
  {
    if (alL) {
      return;
    }
    alL = true;
    zzcvh = zzt.zza(alM).getLong(zzaxn, alN);
  }
  
  @WorkerThread
  public long get()
  {
    zzbui();
    return zzcvh;
  }
  
  @WorkerThread
  public void set(long paramLong)
  {
    SharedPreferences.Editor localEditor = zzt.zza(alM).edit();
    localEditor.putLong(zzaxn, paramLong);
    localEditor.apply();
    zzcvh = paramLong;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.measurement.internal.zzt.zzb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */