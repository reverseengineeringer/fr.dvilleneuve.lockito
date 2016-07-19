package com.google.android.gms.internal;

import java.io.IOException;

public final class zzah$zza
  extends zzaow<zza>
{
  public int level;
  public int zzum;
  public int zzun;
  
  public zzah$zza()
  {
    zzab();
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
            do
            {
              return bool1;
              bool1 = bool2;
            } while (!(paramObject instanceof zza));
            paramObject = (zza)paramObject;
            bool1 = bool2;
          } while (level != level);
          bool1 = bool2;
        } while (zzum != zzum);
        bool1 = bool2;
      } while (zzun != zzun);
      if ((bib != null) && (!bib.isEmpty())) {
        break label102;
      }
      if (bib == null) {
        break;
      }
      bool1 = bool2;
    } while (!bib.isEmpty());
    return true;
    label102:
    return bib.equals(bib);
  }
  
  public int hashCode()
  {
    int j = getClass().getName().hashCode();
    int k = level;
    int m = zzum;
    int n = zzun;
    if ((bib == null) || (bib.isEmpty())) {}
    for (int i = 0;; i = bib.hashCode()) {
      return i + ((((j + 527) * 31 + k) * 31 + m) * 31 + n) * 31;
    }
  }
  
  public void zza(zzaov paramzzaov)
    throws IOException
  {
    if (level != 1) {
      paramzzaov.zzae(1, level);
    }
    if (zzum != 0) {
      paramzzaov.zzae(2, zzum);
    }
    if (zzun != 0) {
      paramzzaov.zzae(3, zzun);
    }
    super.zza(paramzzaov);
  }
  
  public zza zzab()
  {
    level = 1;
    zzum = 0;
    zzun = 0;
    bib = null;
    bik = -1;
    return this;
  }
  
  public zza zzj(zzaou paramzzaou)
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
        i = paramzzaou.N();
        switch (i)
        {
        default: 
          break;
        case 1: 
        case 2: 
        case 3: 
          level = i;
        }
        break;
      case 16: 
        zzum = paramzzaou.N();
        break;
      case 24: 
        zzun = paramzzaou.N();
      }
    }
  }
  
  protected int zzy()
  {
    int j = super.zzy();
    int i = j;
    if (level != 1) {
      i = j + zzaov.zzag(1, level);
    }
    j = i;
    if (zzum != 0) {
      j = i + zzaov.zzag(2, zzum);
    }
    i = j;
    if (zzun != 0) {
      i = j + zzaov.zzag(3, zzun);
    }
    return i;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzah.zza
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */