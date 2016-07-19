package com.google.android.gms.internal;

import java.io.IOException;

public final class zzah$zzg
  extends zzaow<zzg>
{
  private static volatile zzg[] zzvs;
  public int[] zzvt;
  public int[] zzvu;
  public int[] zzvv;
  public int[] zzvw;
  public int[] zzvx;
  public int[] zzvy;
  public int[] zzvz;
  public int[] zzwa;
  public int[] zzwb;
  public int[] zzwc;
  
  public zzah$zzg()
  {
    zzal();
  }
  
  public static zzg[] zzak()
  {
    if (zzvs == null) {}
    synchronized (zzapa.bij)
    {
      if (zzvs == null) {
        zzvs = new zzg[0];
      }
      return zzvs;
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
                          } while (!(paramObject instanceof zzg));
                          paramObject = (zzg)paramObject;
                          bool1 = bool2;
                        } while (!zzapa.equals(zzvt, zzvt));
                        bool1 = bool2;
                      } while (!zzapa.equals(zzvu, zzvu));
                      bool1 = bool2;
                    } while (!zzapa.equals(zzvv, zzvv));
                    bool1 = bool2;
                  } while (!zzapa.equals(zzvw, zzvw));
                  bool1 = bool2;
                } while (!zzapa.equals(zzvx, zzvx));
                bool1 = bool2;
              } while (!zzapa.equals(zzvy, zzvy));
              bool1 = bool2;
            } while (!zzapa.equals(zzvz, zzvz));
            bool1 = bool2;
          } while (!zzapa.equals(zzwa, zzwa));
          bool1 = bool2;
        } while (!zzapa.equals(zzwb, zzwb));
        bool1 = bool2;
      } while (!zzapa.equals(zzwc, zzwc));
      if ((bib != null) && (!bib.isEmpty())) {
        break label223;
      }
      if (bib == null) {
        break;
      }
      bool1 = bool2;
    } while (!bib.isEmpty());
    return true;
    label223:
    return bib.equals(bib);
  }
  
  public int hashCode()
  {
    int j = getClass().getName().hashCode();
    int k = zzapa.hashCode(zzvt);
    int m = zzapa.hashCode(zzvu);
    int n = zzapa.hashCode(zzvv);
    int i1 = zzapa.hashCode(zzvw);
    int i2 = zzapa.hashCode(zzvx);
    int i3 = zzapa.hashCode(zzvy);
    int i4 = zzapa.hashCode(zzvz);
    int i5 = zzapa.hashCode(zzwa);
    int i6 = zzapa.hashCode(zzwb);
    int i7 = zzapa.hashCode(zzwc);
    if ((bib == null) || (bib.isEmpty())) {}
    for (int i = 0;; i = bib.hashCode()) {
      return i + (((((((((((j + 527) * 31 + k) * 31 + m) * 31 + n) * 31 + i1) * 31 + i2) * 31 + i3) * 31 + i4) * 31 + i5) * 31 + i6) * 31 + i7) * 31;
    }
  }
  
  public void zza(zzaov paramzzaov)
    throws IOException
  {
    int j = 0;
    int i;
    if ((zzvt != null) && (zzvt.length > 0))
    {
      i = 0;
      while (i < zzvt.length)
      {
        paramzzaov.zzae(1, zzvt[i]);
        i += 1;
      }
    }
    if ((zzvu != null) && (zzvu.length > 0))
    {
      i = 0;
      while (i < zzvu.length)
      {
        paramzzaov.zzae(2, zzvu[i]);
        i += 1;
      }
    }
    if ((zzvv != null) && (zzvv.length > 0))
    {
      i = 0;
      while (i < zzvv.length)
      {
        paramzzaov.zzae(3, zzvv[i]);
        i += 1;
      }
    }
    if ((zzvw != null) && (zzvw.length > 0))
    {
      i = 0;
      while (i < zzvw.length)
      {
        paramzzaov.zzae(4, zzvw[i]);
        i += 1;
      }
    }
    if ((zzvx != null) && (zzvx.length > 0))
    {
      i = 0;
      while (i < zzvx.length)
      {
        paramzzaov.zzae(5, zzvx[i]);
        i += 1;
      }
    }
    if ((zzvy != null) && (zzvy.length > 0))
    {
      i = 0;
      while (i < zzvy.length)
      {
        paramzzaov.zzae(6, zzvy[i]);
        i += 1;
      }
    }
    if ((zzvz != null) && (zzvz.length > 0))
    {
      i = 0;
      while (i < zzvz.length)
      {
        paramzzaov.zzae(7, zzvz[i]);
        i += 1;
      }
    }
    if ((zzwa != null) && (zzwa.length > 0))
    {
      i = 0;
      while (i < zzwa.length)
      {
        paramzzaov.zzae(8, zzwa[i]);
        i += 1;
      }
    }
    if ((zzwb != null) && (zzwb.length > 0))
    {
      i = 0;
      while (i < zzwb.length)
      {
        paramzzaov.zzae(9, zzwb[i]);
        i += 1;
      }
    }
    if ((zzwc != null) && (zzwc.length > 0))
    {
      i = j;
      while (i < zzwc.length)
      {
        paramzzaov.zzae(10, zzwc[i]);
        i += 1;
      }
    }
    super.zza(paramzzaov);
  }
  
  public zzg zzal()
  {
    zzvt = zzapf.bim;
    zzvu = zzapf.bim;
    zzvv = zzapf.bim;
    zzvw = zzapf.bim;
    zzvx = zzapf.bim;
    zzvy = zzapf.bim;
    zzvz = zzapf.bim;
    zzwa = zzapf.bim;
    zzwb = zzapf.bim;
    zzwc = zzapf.bim;
    bib = null;
    bik = -1;
    return this;
  }
  
  public zzg zzp(zzaou paramzzaou)
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
        if (zzvt == null) {}
        for (i = 0;; i = zzvt.length)
        {
          arrayOfInt = new int[j + i];
          j = i;
          if (i != 0)
          {
            System.arraycopy(zzvt, 0, arrayOfInt, 0, i);
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
        zzvt = arrayOfInt;
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
        if (zzvt == null) {}
        for (i = 0;; i = zzvt.length)
        {
          arrayOfInt = new int[j + i];
          j = i;
          if (i != 0)
          {
            System.arraycopy(zzvt, 0, arrayOfInt, 0, i);
            j = i;
          }
          while (j < arrayOfInt.length)
          {
            arrayOfInt[j] = paramzzaou.N();
            j += 1;
          }
        }
        zzvt = arrayOfInt;
        paramzzaou.zzaej(k);
        break;
      case 16: 
        j = zzapf.zzc(paramzzaou, 16);
        if (zzvu == null) {}
        for (i = 0;; i = zzvu.length)
        {
          arrayOfInt = new int[j + i];
          j = i;
          if (i != 0)
          {
            System.arraycopy(zzvu, 0, arrayOfInt, 0, i);
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
        zzvu = arrayOfInt;
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
        if (zzvu == null) {}
        for (i = 0;; i = zzvu.length)
        {
          arrayOfInt = new int[j + i];
          j = i;
          if (i != 0)
          {
            System.arraycopy(zzvu, 0, arrayOfInt, 0, i);
            j = i;
          }
          while (j < arrayOfInt.length)
          {
            arrayOfInt[j] = paramzzaou.N();
            j += 1;
          }
        }
        zzvu = arrayOfInt;
        paramzzaou.zzaej(k);
        break;
      case 24: 
        j = zzapf.zzc(paramzzaou, 24);
        if (zzvv == null) {}
        for (i = 0;; i = zzvv.length)
        {
          arrayOfInt = new int[j + i];
          j = i;
          if (i != 0)
          {
            System.arraycopy(zzvv, 0, arrayOfInt, 0, i);
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
        zzvv = arrayOfInt;
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
        if (zzvv == null) {}
        for (i = 0;; i = zzvv.length)
        {
          arrayOfInt = new int[j + i];
          j = i;
          if (i != 0)
          {
            System.arraycopy(zzvv, 0, arrayOfInt, 0, i);
            j = i;
          }
          while (j < arrayOfInt.length)
          {
            arrayOfInt[j] = paramzzaou.N();
            j += 1;
          }
        }
        zzvv = arrayOfInt;
        paramzzaou.zzaej(k);
        break;
      case 32: 
        j = zzapf.zzc(paramzzaou, 32);
        if (zzvw == null) {}
        for (i = 0;; i = zzvw.length)
        {
          arrayOfInt = new int[j + i];
          j = i;
          if (i != 0)
          {
            System.arraycopy(zzvw, 0, arrayOfInt, 0, i);
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
        zzvw = arrayOfInt;
        break;
      case 34: 
        k = paramzzaou.zzaei(paramzzaou.S());
        i = paramzzaou.getPosition();
        j = 0;
        while (paramzzaou.X() > 0)
        {
          paramzzaou.N();
          j += 1;
        }
        paramzzaou.zzaek(i);
        if (zzvw == null) {}
        for (i = 0;; i = zzvw.length)
        {
          arrayOfInt = new int[j + i];
          j = i;
          if (i != 0)
          {
            System.arraycopy(zzvw, 0, arrayOfInt, 0, i);
            j = i;
          }
          while (j < arrayOfInt.length)
          {
            arrayOfInt[j] = paramzzaou.N();
            j += 1;
          }
        }
        zzvw = arrayOfInt;
        paramzzaou.zzaej(k);
        break;
      case 40: 
        j = zzapf.zzc(paramzzaou, 40);
        if (zzvx == null) {}
        for (i = 0;; i = zzvx.length)
        {
          arrayOfInt = new int[j + i];
          j = i;
          if (i != 0)
          {
            System.arraycopy(zzvx, 0, arrayOfInt, 0, i);
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
        zzvx = arrayOfInt;
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
        if (zzvx == null) {}
        for (i = 0;; i = zzvx.length)
        {
          arrayOfInt = new int[j + i];
          j = i;
          if (i != 0)
          {
            System.arraycopy(zzvx, 0, arrayOfInt, 0, i);
            j = i;
          }
          while (j < arrayOfInt.length)
          {
            arrayOfInt[j] = paramzzaou.N();
            j += 1;
          }
        }
        zzvx = arrayOfInt;
        paramzzaou.zzaej(k);
        break;
      case 48: 
        j = zzapf.zzc(paramzzaou, 48);
        if (zzvy == null) {}
        for (i = 0;; i = zzvy.length)
        {
          arrayOfInt = new int[j + i];
          j = i;
          if (i != 0)
          {
            System.arraycopy(zzvy, 0, arrayOfInt, 0, i);
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
        zzvy = arrayOfInt;
        break;
      case 50: 
        k = paramzzaou.zzaei(paramzzaou.S());
        i = paramzzaou.getPosition();
        j = 0;
        while (paramzzaou.X() > 0)
        {
          paramzzaou.N();
          j += 1;
        }
        paramzzaou.zzaek(i);
        if (zzvy == null) {}
        for (i = 0;; i = zzvy.length)
        {
          arrayOfInt = new int[j + i];
          j = i;
          if (i != 0)
          {
            System.arraycopy(zzvy, 0, arrayOfInt, 0, i);
            j = i;
          }
          while (j < arrayOfInt.length)
          {
            arrayOfInt[j] = paramzzaou.N();
            j += 1;
          }
        }
        zzvy = arrayOfInt;
        paramzzaou.zzaej(k);
        break;
      case 56: 
        j = zzapf.zzc(paramzzaou, 56);
        if (zzvz == null) {}
        for (i = 0;; i = zzvz.length)
        {
          arrayOfInt = new int[j + i];
          j = i;
          if (i != 0)
          {
            System.arraycopy(zzvz, 0, arrayOfInt, 0, i);
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
        zzvz = arrayOfInt;
        break;
      case 58: 
        k = paramzzaou.zzaei(paramzzaou.S());
        i = paramzzaou.getPosition();
        j = 0;
        while (paramzzaou.X() > 0)
        {
          paramzzaou.N();
          j += 1;
        }
        paramzzaou.zzaek(i);
        if (zzvz == null) {}
        for (i = 0;; i = zzvz.length)
        {
          arrayOfInt = new int[j + i];
          j = i;
          if (i != 0)
          {
            System.arraycopy(zzvz, 0, arrayOfInt, 0, i);
            j = i;
          }
          while (j < arrayOfInt.length)
          {
            arrayOfInt[j] = paramzzaou.N();
            j += 1;
          }
        }
        zzvz = arrayOfInt;
        paramzzaou.zzaej(k);
        break;
      case 64: 
        j = zzapf.zzc(paramzzaou, 64);
        if (zzwa == null) {}
        for (i = 0;; i = zzwa.length)
        {
          arrayOfInt = new int[j + i];
          j = i;
          if (i != 0)
          {
            System.arraycopy(zzwa, 0, arrayOfInt, 0, i);
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
        zzwa = arrayOfInt;
        break;
      case 66: 
        k = paramzzaou.zzaei(paramzzaou.S());
        i = paramzzaou.getPosition();
        j = 0;
        while (paramzzaou.X() > 0)
        {
          paramzzaou.N();
          j += 1;
        }
        paramzzaou.zzaek(i);
        if (zzwa == null) {}
        for (i = 0;; i = zzwa.length)
        {
          arrayOfInt = new int[j + i];
          j = i;
          if (i != 0)
          {
            System.arraycopy(zzwa, 0, arrayOfInt, 0, i);
            j = i;
          }
          while (j < arrayOfInt.length)
          {
            arrayOfInt[j] = paramzzaou.N();
            j += 1;
          }
        }
        zzwa = arrayOfInt;
        paramzzaou.zzaej(k);
        break;
      case 72: 
        j = zzapf.zzc(paramzzaou, 72);
        if (zzwb == null) {}
        for (i = 0;; i = zzwb.length)
        {
          arrayOfInt = new int[j + i];
          j = i;
          if (i != 0)
          {
            System.arraycopy(zzwb, 0, arrayOfInt, 0, i);
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
        zzwb = arrayOfInt;
        break;
      case 74: 
        k = paramzzaou.zzaei(paramzzaou.S());
        i = paramzzaou.getPosition();
        j = 0;
        while (paramzzaou.X() > 0)
        {
          paramzzaou.N();
          j += 1;
        }
        paramzzaou.zzaek(i);
        if (zzwb == null) {}
        for (i = 0;; i = zzwb.length)
        {
          arrayOfInt = new int[j + i];
          j = i;
          if (i != 0)
          {
            System.arraycopy(zzwb, 0, arrayOfInt, 0, i);
            j = i;
          }
          while (j < arrayOfInt.length)
          {
            arrayOfInt[j] = paramzzaou.N();
            j += 1;
          }
        }
        zzwb = arrayOfInt;
        paramzzaou.zzaej(k);
        break;
      case 80: 
        j = zzapf.zzc(paramzzaou, 80);
        if (zzwc == null) {}
        for (i = 0;; i = zzwc.length)
        {
          arrayOfInt = new int[j + i];
          j = i;
          if (i != 0)
          {
            System.arraycopy(zzwc, 0, arrayOfInt, 0, i);
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
        zzwc = arrayOfInt;
        break;
      case 82: 
        k = paramzzaou.zzaei(paramzzaou.S());
        i = paramzzaou.getPosition();
        j = 0;
        while (paramzzaou.X() > 0)
        {
          paramzzaou.N();
          j += 1;
        }
        paramzzaou.zzaek(i);
        if (zzwc == null) {}
        for (i = 0;; i = zzwc.length)
        {
          arrayOfInt = new int[j + i];
          j = i;
          if (i != 0)
          {
            System.arraycopy(zzwc, 0, arrayOfInt, 0, i);
            j = i;
          }
          while (j < arrayOfInt.length)
          {
            arrayOfInt[j] = paramzzaou.N();
            j += 1;
          }
        }
        zzwc = arrayOfInt;
        paramzzaou.zzaej(k);
      }
    }
  }
  
  protected int zzy()
  {
    int m = 0;
    int k = super.zzy();
    int i;
    if ((zzvt != null) && (zzvt.length > 0))
    {
      i = 0;
      j = 0;
      while (i < zzvt.length)
      {
        j += zzaov.zzaeo(zzvt[i]);
        i += 1;
      }
    }
    for (int j = k + j + zzvt.length * 1;; j = k)
    {
      i = j;
      if (zzvu != null)
      {
        i = j;
        if (zzvu.length > 0)
        {
          i = 0;
          k = 0;
          while (i < zzvu.length)
          {
            k += zzaov.zzaeo(zzvu[i]);
            i += 1;
          }
          i = j + k + zzvu.length * 1;
        }
      }
      j = i;
      if (zzvv != null)
      {
        j = i;
        if (zzvv.length > 0)
        {
          j = 0;
          k = 0;
          while (j < zzvv.length)
          {
            k += zzaov.zzaeo(zzvv[j]);
            j += 1;
          }
          j = i + k + zzvv.length * 1;
        }
      }
      i = j;
      if (zzvw != null)
      {
        i = j;
        if (zzvw.length > 0)
        {
          i = 0;
          k = 0;
          while (i < zzvw.length)
          {
            k += zzaov.zzaeo(zzvw[i]);
            i += 1;
          }
          i = j + k + zzvw.length * 1;
        }
      }
      j = i;
      if (zzvx != null)
      {
        j = i;
        if (zzvx.length > 0)
        {
          j = 0;
          k = 0;
          while (j < zzvx.length)
          {
            k += zzaov.zzaeo(zzvx[j]);
            j += 1;
          }
          j = i + k + zzvx.length * 1;
        }
      }
      i = j;
      if (zzvy != null)
      {
        i = j;
        if (zzvy.length > 0)
        {
          i = 0;
          k = 0;
          while (i < zzvy.length)
          {
            k += zzaov.zzaeo(zzvy[i]);
            i += 1;
          }
          i = j + k + zzvy.length * 1;
        }
      }
      j = i;
      if (zzvz != null)
      {
        j = i;
        if (zzvz.length > 0)
        {
          j = 0;
          k = 0;
          while (j < zzvz.length)
          {
            k += zzaov.zzaeo(zzvz[j]);
            j += 1;
          }
          j = i + k + zzvz.length * 1;
        }
      }
      i = j;
      if (zzwa != null)
      {
        i = j;
        if (zzwa.length > 0)
        {
          i = 0;
          k = 0;
          while (i < zzwa.length)
          {
            k += zzaov.zzaeo(zzwa[i]);
            i += 1;
          }
          i = j + k + zzwa.length * 1;
        }
      }
      j = i;
      if (zzwb != null)
      {
        j = i;
        if (zzwb.length > 0)
        {
          j = 0;
          k = 0;
          while (j < zzwb.length)
          {
            k += zzaov.zzaeo(zzwb[j]);
            j += 1;
          }
          j = i + k + zzwb.length * 1;
        }
      }
      i = j;
      if (zzwc != null)
      {
        i = j;
        if (zzwc.length > 0)
        {
          k = 0;
          i = m;
          while (i < zzwc.length)
          {
            k += zzaov.zzaeo(zzwc[i]);
            i += 1;
          }
          i = j + k + zzwc.length * 1;
        }
      }
      return i;
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzah.zzg
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */