package com.google.android.gms.internal;

import java.io.IOException;

public final class zzah$zzc
  extends zzaow<zzc>
{
  private static volatile zzc[] zzut;
  public String zzcb;
  public long zzuu;
  public long zzuv;
  public boolean zzuw;
  public long zzux;
  
  public zzah$zzc()
  {
    zzaf();
  }
  
  public static zzc[] zzae()
  {
    if (zzut == null) {}
    synchronized (zzapa.bij)
    {
      if (zzut == null) {
        zzut = new zzc[0];
      }
      return zzut;
    }
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool1;
    if (paramObject == this) {
      bool1 = true;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (!(paramObject instanceof zzc));
      paramObject = (zzc)paramObject;
      if (zzcb != null) {
        break;
      }
      bool1 = bool2;
    } while (zzcb != null);
    while (zzcb.equals(zzcb))
    {
      bool1 = bool2;
      if (zzuu != zzuu) {
        break;
      }
      bool1 = bool2;
      if (zzuv != zzuv) {
        break;
      }
      bool1 = bool2;
      if (zzuw != zzuw) {
        break;
      }
      bool1 = bool2;
      if (zzux != zzux) {
        break;
      }
      if ((bib != null) && (!bib.isEmpty())) {
        break label150;
      }
      if (bib != null)
      {
        bool1 = bool2;
        if (!bib.isEmpty()) {
          break;
        }
      }
      return true;
    }
    return false;
    label150:
    return bib.equals(bib);
  }
  
  public int hashCode()
  {
    int m = 0;
    int n = getClass().getName().hashCode();
    int i;
    int i1;
    int i2;
    int j;
    label65:
    int i3;
    if (zzcb == null)
    {
      i = 0;
      i1 = (int)(zzuu ^ zzuu >>> 32);
      i2 = (int)(zzuv ^ zzuv >>> 32);
      if (!zzuw) {
        break label154;
      }
      j = 1231;
      i3 = (int)(zzux ^ zzux >>> 32);
      k = m;
      if (bib != null) {
        if (!bib.isEmpty()) {
          break label161;
        }
      }
    }
    label154:
    label161:
    for (int k = m;; k = bib.hashCode())
    {
      return ((j + (((i + (n + 527) * 31) * 31 + i1) * 31 + i2) * 31) * 31 + i3) * 31 + k;
      i = zzcb.hashCode();
      break;
      j = 1237;
      break label65;
    }
  }
  
  public void zza(zzaov paramzzaov)
    throws IOException
  {
    if (!zzcb.equals("")) {
      paramzzaov.zzr(1, zzcb);
    }
    if (zzuu != 0L) {
      paramzzaov.zzb(2, zzuu);
    }
    if (zzuv != 2147483647L) {
      paramzzaov.zzb(3, zzuv);
    }
    if (zzuw) {
      paramzzaov.zzj(4, zzuw);
    }
    if (zzux != 0L) {
      paramzzaov.zzb(5, zzux);
    }
    super.zza(paramzzaov);
  }
  
  public zzc zzaf()
  {
    zzcb = "";
    zzuu = 0L;
    zzuv = 2147483647L;
    zzuw = false;
    zzux = 0L;
    bib = null;
    bik = -1;
    return this;
  }
  
  public zzc zzl(zzaou paramzzaou)
    throws IOException
  {
    for (;;)
    {
      int i = paramzzaou.J();
      switch (i)
      {
      default: 
        if (super.zza(paramzzaou, i)) {}
        break;
      case 0: 
        return this;
      case 10: 
        zzcb = paramzzaou.readString();
        break;
      case 16: 
        zzuu = paramzzaou.M();
        break;
      case 24: 
        zzuv = paramzzaou.M();
        break;
      case 32: 
        zzuw = paramzzaou.P();
        break;
      case 40: 
        zzux = paramzzaou.M();
      }
    }
  }
  
  protected int zzy()
  {
    int j = super.zzy();
    int i = j;
    if (!zzcb.equals("")) {
      i = j + zzaov.zzs(1, zzcb);
    }
    j = i;
    if (zzuu != 0L) {
      j = i + zzaov.zze(2, zzuu);
    }
    i = j;
    if (zzuv != 2147483647L) {
      i = j + zzaov.zze(3, zzuv);
    }
    j = i;
    if (zzuw) {
      j = i + zzaov.zzk(4, zzuw);
    }
    i = j;
    if (zzux != 0L) {
      i = j + zzaov.zze(5, zzux);
    }
    return i;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzah.zzc
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */