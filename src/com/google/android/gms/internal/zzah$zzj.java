package com.google.android.gms.internal;

import java.io.IOException;

public final class zzah$zzj
  extends zzaow<zzj>
{
  public zzah.zzi[] zzwp;
  public zzah.zzf zzwq;
  public String zzwr;
  
  public zzah$zzj()
  {
    zzap();
  }
  
  public static zzj zzf(byte[] paramArrayOfByte)
    throws zzapb
  {
    return (zzj)zzapc.zza(new zzj(), paramArrayOfByte);
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool1;
    if (paramObject == this) {
      bool1 = true;
    }
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
          } while (!(paramObject instanceof zzj));
          paramObject = (zzj)paramObject;
          bool1 = bool2;
        } while (!zzapa.equals(zzwp, zzwp));
        if (zzwq != null) {
          break;
        }
        bool1 = bool2;
      } while (zzwq != null);
      if (zzwr != null) {
        break label127;
      }
      bool1 = bool2;
    } while (zzwr != null);
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
        if (zzwq.equals(zzwq)) {
          break label57;
        }
        return false;
        label127:
        if (!zzwr.equals(zzwr)) {
          return false;
        }
      }
    }
    return bib.equals(bib);
  }
  
  public int hashCode()
  {
    int m = 0;
    int n = getClass().getName().hashCode();
    int i1 = zzapa.hashCode(zzwp);
    int i;
    int j;
    if (zzwq == null)
    {
      i = 0;
      if (zzwr != null) {
        break label104;
      }
      j = 0;
      label42:
      k = m;
      if (bib != null) {
        if (!bib.isEmpty()) {
          break label115;
        }
      }
    }
    label104:
    label115:
    for (int k = m;; k = bib.hashCode())
    {
      return (j + (i + ((n + 527) * 31 + i1) * 31) * 31) * 31 + k;
      i = zzwq.hashCode();
      break;
      j = zzwr.hashCode();
      break label42;
    }
  }
  
  public void zza(zzaov paramzzaov)
    throws IOException
  {
    if ((zzwp != null) && (zzwp.length > 0))
    {
      int i = 0;
      while (i < zzwp.length)
      {
        zzah.zzi localzzi = zzwp[i];
        if (localzzi != null) {
          paramzzaov.zza(1, localzzi);
        }
        i += 1;
      }
    }
    if (zzwq != null) {
      paramzzaov.zza(2, zzwq);
    }
    if (!zzwr.equals("")) {
      paramzzaov.zzr(3, zzwr);
    }
    super.zza(paramzzaov);
  }
  
  public zzj zzap()
  {
    zzwp = zzah.zzi.zzan();
    zzwq = null;
    zzwr = "";
    bib = null;
    bik = -1;
    return this;
  }
  
  public zzj zzs(zzaou paramzzaou)
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
        int j = zzapf.zzc(paramzzaou, 10);
        if (zzwp == null) {}
        zzah.zzi[] arrayOfzzi;
        for (i = 0;; i = zzwp.length)
        {
          arrayOfzzi = new zzah.zzi[j + i];
          j = i;
          if (i != 0)
          {
            System.arraycopy(zzwp, 0, arrayOfzzi, 0, i);
            j = i;
          }
          while (j < arrayOfzzi.length - 1)
          {
            arrayOfzzi[j] = new zzah.zzi();
            paramzzaou.zza(arrayOfzzi[j]);
            paramzzaou.J();
            j += 1;
          }
        }
        arrayOfzzi[j] = new zzah.zzi();
        paramzzaou.zza(arrayOfzzi[j]);
        zzwp = arrayOfzzi;
        break;
      case 18: 
        if (zzwq == null) {
          zzwq = new zzah.zzf();
        }
        paramzzaou.zza(zzwq);
        break;
      case 26: 
        zzwr = paramzzaou.readString();
      }
    }
  }
  
  protected int zzy()
  {
    int i = super.zzy();
    int j = i;
    if (zzwp != null)
    {
      j = i;
      if (zzwp.length > 0)
      {
        int k = 0;
        for (;;)
        {
          j = i;
          if (k >= zzwp.length) {
            break;
          }
          zzah.zzi localzzi = zzwp[k];
          j = i;
          if (localzzi != null) {
            j = i + zzaov.zzc(1, localzzi);
          }
          k += 1;
          i = j;
        }
      }
    }
    i = j;
    if (zzwq != null) {
      i = j + zzaov.zzc(2, zzwq);
    }
    j = i;
    if (!zzwr.equals("")) {
      j = i + zzaov.zzs(3, zzwr);
    }
    return j;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzah.zzj
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */