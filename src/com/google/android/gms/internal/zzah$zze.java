package com.google.android.gms.internal;

import java.io.IOException;

public final class zzah$zze
  extends zzaow<zze>
{
  private static volatile zze[] zzvb;
  public int key;
  public int value;
  
  public zzah$zze()
  {
    zzai();
  }
  
  public static zze[] zzah()
  {
    if (zzvb == null) {}
    synchronized (zzapa.bij)
    {
      if (zzvb == null) {
        zzvb = new zze[0];
      }
      return zzvb;
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
        do
        {
          do
          {
            return bool1;
            bool1 = bool2;
          } while (!(paramObject instanceof zze));
          paramObject = (zze)paramObject;
          bool1 = bool2;
        } while (key != key);
        bool1 = bool2;
      } while (value != value);
      if ((bib != null) && (!bib.isEmpty())) {
        break label89;
      }
      if (bib == null) {
        break;
      }
      bool1 = bool2;
    } while (!bib.isEmpty());
    return true;
    label89:
    return bib.equals(bib);
  }
  
  public int hashCode()
  {
    int j = getClass().getName().hashCode();
    int k = key;
    int m = value;
    if ((bib == null) || (bib.isEmpty())) {}
    for (int i = 0;; i = bib.hashCode()) {
      return i + (((j + 527) * 31 + k) * 31 + m) * 31;
    }
  }
  
  public void zza(zzaov paramzzaov)
    throws IOException
  {
    paramzzaov.zzae(1, key);
    paramzzaov.zzae(2, value);
    super.zza(paramzzaov);
  }
  
  public zze zzai()
  {
    key = 0;
    value = 0;
    bib = null;
    bik = -1;
    return this;
  }
  
  public zze zzn(zzaou paramzzaou)
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
      case 8: 
        key = paramzzaou.N();
        break;
      case 16: 
        value = paramzzaou.N();
      }
    }
  }
  
  protected int zzy()
  {
    return super.zzy() + zzaov.zzag(1, key) + zzaov.zzag(2, value);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzah.zze
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */