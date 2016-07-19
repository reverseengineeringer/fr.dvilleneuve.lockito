package com.google.android.gms.internal;

import java.io.IOException;

public abstract interface zzadx
{
  public static final class zza
    extends zzaow<zza>
  {
    public long aDp;
    public zzah.zzj aDq;
    public zzah.zzf zzwq;
    
    public zza()
    {
      zzcgt();
    }
    
    public static zza zzap(byte[] paramArrayOfByte)
      throws zzapb
    {
      return (zza)zzapc.zza(new zza(), paramArrayOfByte);
    }
    
    public boolean equals(Object paramObject)
    {
      boolean bool2 = false;
      boolean bool1;
      if (paramObject == this) {
        bool1 = true;
      }
      label55:
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
          } while (aDp != aDp);
          if (zzwq != null) {
            break;
          }
          bool1 = bool2;
        } while (zzwq != null);
        if (aDq != null) {
          break label125;
        }
        bool1 = bool2;
      } while (aDq != null);
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
            break label55;
          }
          return false;
          label125:
          if (!aDq.equals(aDq)) {
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
      int i1 = (int)(aDp ^ aDp >>> 32);
      int i;
      int j;
      if (zzwq == null)
      {
        i = 0;
        if (aDq != null) {
          break label110;
        }
        j = 0;
        label48:
        k = m;
        if (bib != null) {
          if (!bib.isEmpty()) {
            break label121;
          }
        }
      }
      label110:
      label121:
      for (int k = m;; k = bib.hashCode())
      {
        return (j + (i + ((n + 527) * 31 + i1) * 31) * 31) * 31 + k;
        i = zzwq.hashCode();
        break;
        j = aDq.hashCode();
        break label48;
      }
    }
    
    public void zza(zzaov paramzzaov)
      throws IOException
    {
      paramzzaov.zzb(1, aDp);
      if (zzwq != null) {
        paramzzaov.zza(2, zzwq);
      }
      if (aDq != null) {
        paramzzaov.zza(3, aDq);
      }
      super.zza(paramzzaov);
    }
    
    public zza zzbt(zzaou paramzzaou)
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
          aDp = paramzzaou.M();
          break;
        case 18: 
          if (zzwq == null) {
            zzwq = new zzah.zzf();
          }
          paramzzaou.zza(zzwq);
          break;
        case 26: 
          if (aDq == null) {
            aDq = new zzah.zzj();
          }
          paramzzaou.zza(aDq);
        }
      }
    }
    
    public zza zzcgt()
    {
      aDp = 0L;
      zzwq = null;
      aDq = null;
      bib = null;
      bik = -1;
      return this;
    }
    
    protected int zzy()
    {
      int j = super.zzy() + zzaov.zze(1, aDp);
      int i = j;
      if (zzwq != null) {
        i = j + zzaov.zzc(2, zzwq);
      }
      j = i;
      if (aDq != null) {
        j = i + zzaov.zzc(3, aDq);
      }
      return j;
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzadx
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */