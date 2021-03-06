package com.google.android.gms.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Result;

public abstract interface zzqe
{
  public abstract void begin();
  
  public abstract void connect();
  
  public abstract boolean disconnect();
  
  public abstract void onConnected(Bundle paramBundle);
  
  public abstract void onConnectionSuspended(int paramInt);
  
  public abstract void zza(ConnectionResult paramConnectionResult, Api<?> paramApi, int paramInt);
  
  public abstract <A extends Api.zzb, R extends Result, T extends zzpr.zza<R, A>> T zzc(T paramT);
  
  public abstract <A extends Api.zzb, T extends zzpr.zza<? extends Result, A>> T zzd(T paramT);
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzqe
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */