package com.google.android.gms.analytics.internal;

class zzi$zza$1
  implements Runnable
{
  zzi$zza$1(zzi.zza paramzza, zzac paramzzac) {}
  
  public void run()
  {
    if (!zzcxx.zzcxt.isConnected())
    {
      zzcxx.zzcxt.zzej("Connected to service after a timeout");
      zzi.zza(zzcxx.zzcxt, zzcxw);
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.analytics.internal.zzi.zza.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */