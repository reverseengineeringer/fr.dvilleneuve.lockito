package com.google.android.gms.tagmanager;

import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzh;

class zzcz
  implements zzck
{
  private final long U;
  private final int V;
  private double W;
  private final Object Y = new Object();
  private long ayg;
  private final zze zzaoa;
  
  public zzcz()
  {
    this(60, 2000L);
  }
  
  public zzcz(int paramInt, long paramLong)
  {
    V = paramInt;
    W = V;
    U = paramLong;
    zzaoa = zzh.zzavi();
  }
  
  public boolean zzade()
  {
    synchronized (Y)
    {
      long l = zzaoa.currentTimeMillis();
      if (W < V)
      {
        double d = (l - ayg) / U;
        if (d > 0.0D) {
          W = Math.min(V, d + W);
        }
      }
      ayg = l;
      if (W >= 1.0D)
      {
        W -= 1.0D;
        return true;
      }
      zzbn.zzcy("No more tokens available.");
      return false;
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzcz
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */