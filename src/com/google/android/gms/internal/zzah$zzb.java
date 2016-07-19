package com.google.android.gms.internal;

import java.io.IOException;

public final class zzah$zzb
  extends zzaow<zzb>
{
  private static volatile zzb[] zzuo;
  public int name;
  public int[] zzup;
  public int zzuq;
  public boolean zzur;
  public boolean zzus;
  
  public zzah$zzb()
  {
    zzad();
  }
  
  public static zzb[] zzac()
  {
    if (zzuo == null) {}
    synchronized (zzapa.bij)
    {
      if (zzuo == null) {
        zzuo = new zzb[0];
      }
      return zzuo;
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
            do
            {
              do
              {
                do
                {
                  return bool1;
                  bool1 = bool2;
                } while (!(paramObject instanceof zzb));
                paramObject = (zzb)paramObject;
                bool1 = bool2;
              } while (!zzapa.equals(zzup, zzup));
              bool1 = bool2;
            } while (zzuq != zzuq);
            bool1 = bool2;
          } while (name != name);
          bool1 = bool2;
        } while (zzur != zzur);
        bool1 = bool2;
      } while (zzus != zzus);
      if ((bib != null) && (!bib.isEmpty())) {
        break label131;
      }
      if (bib == null) {
        break;
      }
      bool1 = bool2;
    } while (!bib.isEmpty());
    return true;
    label131:
    return bib.equals(bib);
  }
  
  public int hashCode()
  {
    int j = 1231;
    int m = getClass().getName().hashCode();
    int n = zzapa.hashCode(zzup);
    int i1 = zzuq;
    int i2 = name;
    int i;
    if (zzur)
    {
      i = 1231;
      if (!zzus) {
        break label121;
      }
      label55:
      if ((bib != null) && (!bib.isEmpty())) {
        break label128;
      }
    }
    label121:
    label128:
    for (int k = 0;; k = bib.hashCode())
    {
      return k + ((i + ((((m + 527) * 31 + n) * 31 + i1) * 31 + i2) * 31) * 31 + j) * 31;
      i = 1237;
      break;
      j = 1237;
      break label55;
    }
  }
  
  public void zza(zzaov paramzzaov)
    throws IOException
  {
    if (zzus) {
      paramzzaov.zzj(1, zzus);
    }
    paramzzaov.zzae(2, zzuq);
    if ((zzup != null) && (zzup.length > 0))
    {
      int i = 0;
      while (i < zzup.length)
      {
        paramzzaov.zzae(3, zzup[i]);
        i += 1;
      }
    }
    if (name != 0) {
      paramzzaov.zzae(4, name);
    }
    if (zzur) {
      paramzzaov.zzj(6, zzur);
    }
    super.zza(paramzzaov);
  }
  
  public zzb zzad()
  {
    zzup = zzapf.bim;
    zzuq = 0;
    name = 0;
    zzur = false;
    zzus = false;
    bib = null;
    bik = -1;
    return this;
  }
  
  public zzb zzk(zzaou paramzzaou)
    throws IOException
  {
    for (;;)
    {
      int i = paramzzaou.J();
      int j;
      int[] arrayOfInt;
      switch (i)
      {
      default: 
        if (super.zza(paramzzaou, i)) {}
        break;
      case 0: 
        return this;
      case 8: 
        zzus = paramzzaou.P();
        break;
      case 16: 
        zzuq = paramzzaou.N();
        break;
      case 24: 
        j = zzapf.zzc(paramzzaou, 24);
        if (zzup == null) {}
        for (i = 0;; i = zzup.length)
        {
          arrayOfInt = new int[j + i];
          j = i;
          if (i != 0)
          {
            System.arraycopy(zzup, 0, arrayOfInt, 0, i);
            j = i;
          }
          while (j < arrayOfInt.length - 1)
          {
            arrayOfInt[j] = paramzzaou.N();
            paramzzaou.J();
            j += 1;
          }
        }
        arrayOfInt[j] = paramzzaou.N();
        zzup = arrayOfInt;
        break;
      case 26: 
        int k = paramzzaou.zzaei(paramzzaou.S());
        i = paramzzaou.getPosition();
        j = 0;
        while (paramzzaou.X() > 0)
        {
          paramzzaou.N();
          j += 1;
        }
        paramzzaou.zzaek(i);
        if (zzup == null) {}
        for (i = 0;; i = zzup.length)
        {
          arrayOfInt = new int[j + i];
          j = i;
          if (i != 0)
          {
            System.arraycopy(zzup, 0, arrayOfInt, 0, i);
            j = i;
          }
          while (j < arrayOfInt.length)
          {
            arrayOfInt[j] = paramzzaou.N();
            j += 1;
          }
        }
        zzup = arrayOfInt;
        paramzzaou.zzaej(k);
        break;
      case 32: 
        name = paramzzaou.N();
        break;
      case 48: 
        zzur = paramzzaou.P();
      }
    }
  }
  
  protected int zzy()
  {
    int j = 0;
    int k = super.zzy();
    int i = k;
    if (zzus) {
      i = k + zzaov.zzk(1, zzus);
    }
    k = zzaov.zzag(2, zzuq) + i;
    if ((zzup != null) && (zzup.length > 0))
    {
      i = 0;
      while (i < zzup.length)
      {
        j += zzaov.zzaeo(zzup[i]);
        i += 1;
      }
    }
    for (j = k + j + zzup.length * 1;; j = k)
    {
      i = j;
      if (name != 0) {
        i = j + zzaov.zzag(4, name);
      }
      j = i;
      if (zzur) {
        j = i + zzaov.zzk(6, zzur);
      }
      return j;
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzah.zzb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */