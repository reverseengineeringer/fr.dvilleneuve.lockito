package com.google.android.gms.tagmanager;

import com.google.android.gms.common.util.zze;

class zzbl
  implements zzck
{
  private final long U;
  private final int V;
  private double W;
  private long X;
  private final Object Y = new Object();
  private final long awM;
  private final zze zzaoa;
  private final String zzcvf;
  
  public zzbl(int paramInt, long paramLong1, long paramLong2, String paramString, zze paramzze)
  {
    V = paramInt;
    W = V;
    U = paramLong1;
    awM = paramLong2;
    zzcvf = paramString;
    zzaoa = paramzze;
  }
  
  public boolean zzade()
  {
    synchronized (Y)
    {
      long l = zzaoa.currentTimeMillis();
      if (l - X < awM)
      {
        String str1 = zzcvf;
        zzbn.zzcy(String.valueOf(str1).length() + 34 + "Excessive " + str1 + " detected; call ignored.");
        return false;
      }
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
    }
    String str2 = zzcvf;
    zzbn.zzcy(String.valueOf(str2).length() + 34 + "Excessive " + str2 + " detected; call ignored.");
    return false;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzbl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */