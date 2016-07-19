package com.google.android.gms.internal;

import java.io.IOException;

public final class zzah$zzi
  extends zzaow<zzi>
{
  private static volatile zzi[] zzwm;
  public String name;
  public zzai.zza zzwn;
  public zzah.zzd zzwo;
  
  public zzah$zzi()
  {
    zzao();
  }
  
  public static zzi[] zzan()
  {
    if (zzwm == null) {}
    synchronized (zzapa.bij)
    {
      if (zzwm == null) {
        zzwm = new zzi[0];
      }
      return zzwm;
    }
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool1;
    if (paramObject == this) {
      bool1 = true;
    }
    label41:
    label57:
    do
    {
      do
      {
        do
        {
          do
          {
            return bool1;
            bool1 = bool2;
          } while (!(paramObject instanceof zzi));
          paramObject = (zzi)paramObject;
          if (name != null) {
            break;
          }
          bool1 = bool2;
        } while (name != null);
        if (zzwn != null) {
          break label127;
        }
        bool1 = bool2;
      } while (zzwn != null);
      if (zzwo != null) {
        break label143;
      }
      bool1 = bool2;
    } while (zzwo != null);
    for (;;)
    {
      if ((bib == null) || (bib.isEmpty()))
      {
        if (bib != null)
        {
          bool1 = bool2;
          if (!bib.isEmpty()) {
            break;
          }
        }
        return true;
        if (name.equals(name)) {
          break label41;
        }
        return false;
        label127:
        if (zzwn.equals(zzwn)) {
          break label57;
        }
        return false;
        label143:
        if (!zzwo.equals(zzwo)) {
          return false;
        }
      }
    }
    return bib.equals(bib);
  }
  
  public int hashCode()
  {
    int n = 0;
    int i1 = getClass().getName().hashCode();
    int i;
    int j;
    label33:
    int k;
    if (name == null)
    {
      i = 0;
      if (zzwn != null) {
        break label106;
      }
      j = 0;
      if (zzwo != null) {
        break label117;
      }
      k = 0;
      label42:
      m = n;
      if (bib != null) {
        if (!bib.isEmpty()) {
          break label128;
        }
      }
    }
    label106:
    label117:
    label128:
    for (int m = n;; m = bib.hashCode())
    {
      return (k + (j + (i + (i1 + 527) * 31) * 31) * 31) * 31 + m;
      i = name.hashCode();
      break;
      j = zzwn.hashCode();
      break label33;
      k = zzwo.hashCode();
      break label42;
    }
  }
  
  public void zza(zzaov paramzzaov)
    throws IOException
  {
    if (!name.equals("")) {
      paramzzaov.zzr(1, name);
    }
    if (zzwn != null) {
      paramzzaov.zza(2, zzwn);
    }
    if (zzwo != null) {
      paramzzaov.zza(3, zzwo);
    }
    super.zza(paramzzaov);
  }
  
  public zzi zzao()
  {
    name = "";
    zzwn = null;
    zzwo = null;
    bib = null;
    bik = -1;
    return this;
  }
  
  public zzi zzr(zzaou paramzzaou)
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
        name = paramzzaou.readString();
        break;
      case 18: 
        if (zzwn == null) {
          zzwn = new zzai.zza();
        }
        paramzzaou.zza(zzwn);
        break;
      case 26: 
        if (zzwo == null) {
          zzwo = new zzah.zzd();
        }
        paramzzaou.zza(zzwo);
      }
    }
  }
  
  protected int zzy()
  {
    int j = super.zzy();
    int i = j;
    if (!name.equals("")) {
      i = j + zzaov.zzs(1, name);
    }
    j = i;
    if (zzwn != null) {
      j = i + zzaov.zzc(2, zzwn);
    }
    i = j;
    if (zzwo != null) {
      i = j + zzaov.zzc(3, zzwo);
    }
    return i;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzah.zzi
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */