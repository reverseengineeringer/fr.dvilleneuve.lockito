package com.google.android.gms.analytics.internal;

import com.google.android.gms.common.util.zze;

public class zzad
{
  private final long U;
  private final int V;
  private double W;
  private long X;
  private final Object Y = new Object();
  private final zze zzaoa;
  private final String zzcvf;
  
  public zzad(int paramInt, long paramLong, String paramString, zze paramzze)
  {
    V = paramInt;
    W = V;
    U = paramLong;
    zzcvf = paramString;
    zzaoa = paramzze;
  }
  
  public zzad(String paramString, zze paramzze)
  {
    this(60, 2000L, paramString, paramzze);
  }
  
  public boolean zzade()
  {
    synchronized (Y)
    {
      long l = zzaoa.currentTimeMillis();
      if (W < V)
      {
        double d = (l - X) / U;
        if (d > 0.0D) {
          W = Math.min(V, d + W);
        }
      }
      X = l;
      if (W >= 1.0D)
      {
        W -= 1.0D;
        return true;
      }
      String str = zzcvf;
      zzae.zzcy(String.valueOf(str).length() + 34 + "Excessive " + str + " detected; call ignored.");
      return false;
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.analytics.internal.zzad
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */