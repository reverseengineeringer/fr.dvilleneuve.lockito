package com.google.android.gms.internal;

import java.io.IOException;

public final class zzah$zzh
  extends zzaow<zzh>
{
  public static final zzaox<zzai.zza, zzh> zzwd = zzaox.zza(11, zzh.class, 810L);
  private static final zzh[] zzwe = new zzh[0];
  public int[] zzwf;
  public int[] zzwg;
  public int[] zzwh;
  public int zzwi;
  public int[] zzwj;
  public int zzwk;
  public int zzwl;
  
  public zzah$zzh()
  {
    zzam();
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
                  do
                  {
                    do
                    {
                      return bool1;
                      bool1 = bool2;
                    } while (!(paramObject instanceof zzh));
                    paramObject = (zzh)paramObject;
                    bool1 = bool2;
                  } while (!zzapa.equals(zzwf, zzwf));
                  bool1 = bool2;
                } while (!zzapa.equals(zzwg, zzwg));
                bool1 = bool2;
              } while (!zzapa.equals(zzwh, zzwh));
              bool1 = bool2;
            } while (zzwi != zzwi);
            bool1 = bool2;
          } while (!zzapa.equals(zzwj, zzwj));
          bool1 = bool2;
        } while (zzwk != zzwk);
        bool1 = bool2;
      } while (zzwl != zzwl);
      if ((bib != null) && (!bib.isEmpty())) {
        break label166;
      }
      if (bib == null) {
        break;
      }
      bool1 = bool2;
    } while (!bib.isEmpty());
    return true;
    label166:
    return bib.equals(bib);
  }
  
  public int hashCode()
  {
    int j = getClass().getName().hashCode();
    int k = zzapa.hashCode(zzwf);
    int m = zzapa.hashCode(zzwg);
    int n = zzapa.hashCode(zzwh);
    int i1 = zzwi;
    int i2 = zzapa.hashCode(zzwj);
    int i3 = zzwk;
    int i4 = zzwl;
    if ((bib == null) || (bib.isEmpty())) {}
    for (int i = 0;; i = bib.hashCode()) {
      return i + ((((((((j + 527) * 31 + k) * 31 + m) * 31 + n) * 31 + i1) * 31 + i2) * 31 + i3) * 31 + i4) * 31;
    }
  }
  
  public void zza(zzaov paramzzaov)
    throws IOException
  {
    int j = 0;
    int i;
    if ((zzwf != null) && (zzwf.length > 0))
    {
      i = 0;
      while (i < zzwf.length)
      {
        paramzzaov.zzae(1, zzwf[i]);
        i += 1;
      }
    }
    if ((zzwg != null) && (zzwg.length > 0))
    {
      i = 0;
      while (i < zzwg.length)
      {
        paramzzaov.zzae(2, zzwg[i]);
        i += 1;
      }
    }
    if ((zzwh != null) && (zzwh.length > 0))
    {
      i = 0;
      while (i < zzwh.length)
      {
        paramzzaov.zzae(3, zzwh[i]);
        i += 1;
      }
    }
    if (zzwi != 0) {
      paramzzaov.zzae(4, zzwi);
    }
    if ((zzwj != null) && (zzwj.length > 0))
    {
      i = j;
      while (i < zzwj.length)
      {
        paramzzaov.zzae(5, zzwj[i]);
        i += 1;
      }
    }
    if (zzwk != 0) {
      paramzzaov.zzae(6, zzwk);
    }
    if (zzwl != 0) {
      paramzzaov.zzae(7, zzwl);
    }
    super.zza(paramzzaov);
  }
  
  public zzh zzam()
  {
    zzwf = zzapf.bim;
    zzwg = zzapf.bim;
    zzwh = zzapf.bim;
    zzwi = 0;
    zzwj = zzapf.bim;
    zzwk = 0;
    zzwl = 0;
    bib = null;
    bik = -1;
    return this;
  }
  
  public zzh zzq(zzaou paramzzaou)
    throws IOException
  {
    for (;;)
    {
      int i = paramzzaou.J();
      int j;
      int[] arrayOfInt;
      int k;
      switch (i)
      {
      default: 
        if (super.zza(paramzzaou, i)) {}
        break;
      case 0: 
        return this;
      case 8: 
        j = zzapf.zzc(paramzzaou, 8);
        if (zzwf == null) {}
        for (i = 0;; i = zzwf.length)
        {
          arrayOfInt = new int[j + i];
          j = i;
          if (i != 0)
          {
            System.arraycopy(zzwf, 0, arrayOfInt, 0, i);
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
        zzwf = arrayOfInt;
        break;
      case 10: 
        k = paramzzaou.zzaei(paramzzaou.S());
        i = paramzzaou.getPosition();
        j = 0;
        while (paramzzaou.X() > 0)
        {
          paramzzaou.N();
          j += 1;
        }
        paramzzaou.zzaek(i);
        if (zzwf == null) {}
        for (i = 0;; i = zzwf.length)
        {
          arrayOfInt = new int[j + i];
          j = i;
          if (i != 0)
          {
            System.arraycopy(zzwf, 0, arrayOfInt, 0, i);
            j = i;
          }
          while (j < arrayOfInt.length)
          {
            arrayOfInt[j] = paramzzaou.N();
            j += 1;
          }
        }
        zzwf = arrayOfInt;
        paramzzaou.zzaej(k);
        break;
      case 16: 
        j = zzapf.zzc(paramzzaou, 16);
        if (zzwg == null) {}
        for (i = 0;; i = zzwg.length)
        {
          arrayOfInt = new int[j + i];
          j = i;
          if (i != 0)
          {
            System.arraycopy(zzwg, 0, arrayOfInt, 0, i);
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
        zzwg = arrayOfInt;
        break;
      case 18: 
        k = paramzzaou.zzaei(paramzzaou.S());
        i = paramzzaou.getPosition();
        j = 0;
        while (paramzzaou.X() > 0)
        {
          paramzzaou.N();
          j += 1;
        }
        paramzzaou.zzaek(i);
        if (zzwg == null) {}
        for (i = 0;; i = zzwg.length)
        {
          arrayOfInt = new int[j + i];
          j = i;
          if (i != 0)
          {
            System.arraycopy(zzwg, 0, arrayOfInt, 0, i);
            j = i;
          }
          while (j < arrayOfInt.length)
          {
            arrayOfInt[j] = paramzzaou.N();
            j += 1;
          }
        }
        zzwg = arrayOfInt;
        paramzzaou.zzaej(k);
        break;
      case 24: 
        j = zzapf.zzc(paramzzaou, 24);
        if (zzwh == null) {}
        for (i = 0;; i = zzwh.length)
        {
          arrayOfInt = new int[j + i];
          j = i;
          if (i != 0)
          {
            System.arraycopy(zzwh, 0, arrayOfInt, 0, i);
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
        zzwh = arrayOfInt;
        break;
      case 26: 
        k = paramzzaou.zzaei(paramzzaou.S());
        i = paramzzaou.getPosition();
        j = 0;
        while (paramzzaou.X() > 0)
        {
          paramzzaou.N();
          j += 1;
        }
        paramzzaou.zzaek(i);
        if (zzwh == null) {}
        for (i = 0;; i = zzwh.length)
        {
          arrayOfInt = new int[j + i];
          j = i;
          if (i != 0)
          {
            System.arraycopy(zzwh, 0, arrayOfInt, 0, i);
            j = i;
          }
          while (j < arrayOfInt.length)
          {
            arrayOfInt[j] = paramzzaou.N();
            j += 1;
          }
        }
        zzwh = arrayOfInt;
        paramzzaou.zzaej(k);
        break;
      case 32: 
        zzwi = paramzzaou.N();
        break;
      case 40: 
        j = zzapf.zzc(paramzzaou, 40);
        if (zzwj == null) {}
        for (i = 0;; i = zzwj.length)
        {
          arrayOfInt = new int[j + i];
          j = i;
          if (i != 0)
          {
            System.arraycopy(zzwj, 0, arrayOfInt, 0, i);
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
        zzwj = arrayOfInt;
        break;
      case 42: 
        k = paramzzaou.zzaei(paramzzaou.S());
        i = paramzzaou.getPosition();
        j = 0;
        while (paramzzaou.X() > 0)
        {
          paramzzaou.N();
          j += 1;
        }
        paramzzaou.zzaek(i);
        if (zzwj == null) {}
        for (i = 0;; i = zzwj.length)
        {
          arrayOfInt = new int[j + i];
          j = i;
          if (i != 0)
          {
            System.arraycopy(zzwj, 0, arrayOfInt, 0, i);
            j = i;
          }
          while (j < arrayOfInt.length)
          {
            arrayOfInt[j] = paramzzaou.N();
            j += 1;
          }
        }
        zzwj = arrayOfInt;
        paramzzaou.zzaej(k);
        break;
      case 48: 
        zzwk = paramzzaou.N();
        break;
      case 56: 
        zzwl = paramzzaou.N();
      }
    }
  }
  
  protected int zzy()
  {
    int m = 0;
    int k = super.zzy();
    int i;
    if ((zzwf != null) && (zzwf.length > 0))
    {
      i = 0;
      j = 0;
      while (i < zzwf.length)
      {
        j += zzaov.zzaeo(zzwf[i]);
        i += 1;
      }
    }
    for (int j = k + j + zzwf.length * 1;; j = k)
    {
      i = j;
      if (zzwg != null)
      {
        i = j;
        if (zzwg.length > 0)
        {
          i = 0;
          k = 0;
          while (i < zzwg.length)
          {
            k += zzaov.zzaeo(zzwg[i]);
            i += 1;
          }
          i = j + k + zzwg.length * 1;
        }
      }
      j = i;
      if (zzwh != null)
      {
        j = i;
        if (zzwh.length > 0)
        {
          j = 0;
          k = 0;
          while (j < zzwh.length)
          {
            k += zzaov.zzaeo(zzwh[j]);
            j += 1;
          }
          j = i + k + zzwh.length * 1;
        }
      }
      i = j;
      if (zzwi != 0) {
        i = j + zzaov.zzag(4, zzwi);
      }
      j = i;
      if (zzwj != null)
      {
        j = i;
        if (zzwj.length > 0)
        {
          k = 0;
          j = m;
          while (j < zzwj.length)
          {
            k += zzaov.zzaeo(zzwj[j]);
            j += 1;
          }
          j = i + k + zzwj.length * 1;
        }
      }
      i = j;
      if (zzwk != 0) {
        i = j + zzaov.zzag(6, zzwk);
      }
      j = i;
      if (zzwl != 0) {
        j = i + zzaov.zzag(7, zzwl);
      }
      return j;
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzah.zzh
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */