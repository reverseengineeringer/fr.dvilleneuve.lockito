package com.google.android.gms.internal;

import java.io.IOException;

public final class zzah$zzd
  extends zzaow<zzd>
{
  public zzai.zza[] zzuy;
  public zzai.zza[] zzuz;
  public zzah.zzc[] zzva;
  
  public zzah$zzd()
  {
    zzag();
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
            } while (!(paramObject instanceof zzd));
            paramObject = (zzd)paramObject;
            bool1 = bool2;
          } while (!zzapa.equals(zzuy, zzuy));
          bool1 = bool2;
        } while (!zzapa.equals(zzuz, zzuz));
        bool1 = bool2;
      } while (!zzapa.equals(zzva, zzva));
      if ((bib != null) && (!bib.isEmpty())) {
        break label111;
      }
      if (bib == null) {
        break;
      }
      bool1 = bool2;
    } while (!bib.isEmpty());
    return true;
    label111:
    return bib.equals(bib);
  }
  
  public int hashCode()
  {
    int j = getClass().getName().hashCode();
    int k = zzapa.hashCode(zzuy);
    int m = zzapa.hashCode(zzuz);
    int n = zzapa.hashCode(zzva);
    if ((bib == null) || (bib.isEmpty())) {}
    for (int i = 0;; i = bib.hashCode()) {
      return i + ((((j + 527) * 31 + k) * 31 + m) * 31 + n) * 31;
    }
  }
  
  public void zza(zzaov paramzzaov)
    throws IOException
  {
    int j = 0;
    int i;
    Object localObject;
    if ((zzuy != null) && (zzuy.length > 0))
    {
      i = 0;
      while (i < zzuy.length)
      {
        localObject = zzuy[i];
        if (localObject != null) {
          paramzzaov.zza(1, (zzapc)localObject);
        }
        i += 1;
      }
    }
    if ((zzuz != null) && (zzuz.length > 0))
    {
      i = 0;
      while (i < zzuz.length)
      {
        localObject = zzuz[i];
        if (localObject != null) {
          paramzzaov.zza(2, (zzapc)localObject);
        }
        i += 1;
      }
    }
    if ((zzva != null) && (zzva.length > 0))
    {
      i = j;
      while (i < zzva.length)
      {
        localObject = zzva[i];
        if (localObject != null) {
          paramzzaov.zza(3, (zzapc)localObject);
        }
        i += 1;
      }
    }
    super.zza(paramzzaov);
  }
  
  public zzd zzag()
  {
    zzuy = zzai.zza.zzaq();
    zzuz = zzai.zza.zzaq();
    zzva = zzah.zzc.zzae();
    bib = null;
    bik = -1;
    return this;
  }
  
  public zzd zzm(zzaou paramzzaou)
    throws IOException
  {
    for (;;)
    {
      int i = paramzzaou.J();
      int j;
      Object localObject;
      switch (i)
      {
      default: 
        if (super.zza(paramzzaou, i)) {}
        break;
      case 0: 
        return this;
      case 10: 
        j = zzapf.zzc(paramzzaou, 10);
        if (zzuy == null) {}
        for (i = 0;; i = zzuy.length)
        {
          localObject = new zzai.zza[j + i];
          j = i;
          if (i != 0)
          {
            System.arraycopy(zzuy, 0, localObject, 0, i);
            j = i;
          }
          while (j < localObject.length - 1)
          {
            localObject[j] = new zzai.zza();
            paramzzaou.zza(localObject[j]);
            paramzzaou.J();
            j += 1;
          }
        }
        localObject[j] = new zzai.zza();
        paramzzaou.zza(localObject[j]);
        zzuy = ((zzai.zza[])localObject);
        break;
      case 18: 
        j = zzapf.zzc(paramzzaou, 18);
        if (zzuz == null) {}
        for (i = 0;; i = zzuz.length)
        {
          localObject = new zzai.zza[j + i];
          j = i;
          if (i != 0)
          {
            System.arraycopy(zzuz, 0, localObject, 0, i);
            j = i;
          }
          while (j < localObject.length - 1)
          {
            localObject[j] = new zzai.zza();
            paramzzaou.zza(localObject[j]);
            paramzzaou.J();
            j += 1;
          }
        }
        localObject[j] = new zzai.zza();
        paramzzaou.zza(localObject[j]);
        zzuz = ((zzai.zza[])localObject);
        break;
      case 26: 
        j = zzapf.zzc(paramzzaou, 26);
        if (zzva == null) {}
        for (i = 0;; i = zzva.length)
        {
          localObject = new zzah.zzc[j + i];
          j = i;
          if (i != 0)
          {
            System.arraycopy(zzva, 0, localObject, 0, i);
            j = i;
          }
          while (j < localObject.length - 1)
          {
            localObject[j] = new zzah.zzc();
            paramzzaou.zza(localObject[j]);
            paramzzaou.J();
            j += 1;
          }
        }
        localObject[j] = new zzah.zzc();
        paramzzaou.zza(localObject[j]);
        zzva = ((zzah.zzc[])localObject);
      }
    }
  }
  
  protected int zzy()
  {
    int m = 0;
    int i = super.zzy();
    int j = i;
    Object localObject;
    if (zzuy != null)
    {
      j = i;
      if (zzuy.length > 0)
      {
        j = 0;
        while (j < zzuy.length)
        {
          localObject = zzuy[j];
          k = i;
          if (localObject != null) {
            k = i + zzaov.zzc(1, (zzapc)localObject);
          }
          j += 1;
          i = k;
        }
        j = i;
      }
    }
    i = j;
    if (zzuz != null)
    {
      i = j;
      if (zzuz.length > 0)
      {
        i = j;
        j = 0;
        while (j < zzuz.length)
        {
          localObject = zzuz[j];
          k = i;
          if (localObject != null) {
            k = i + zzaov.zzc(2, (zzapc)localObject);
          }
          j += 1;
          i = k;
        }
      }
    }
    int k = i;
    if (zzva != null)
    {
      k = i;
      if (zzva.length > 0)
      {
        j = m;
        for (;;)
        {
          k = i;
          if (j >= zzva.length) {
            break;
          }
          localObject = zzva[j];
          k = i;
          if (localObject != null) {
            k = i + zzaov.zzc(3, (zzapc)localObject);
          }
          j += 1;
          i = k;
        }
      }
    }
    return k;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzah.zzd
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */