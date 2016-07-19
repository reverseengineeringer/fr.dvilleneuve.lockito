package com.google.android.gms.internal;

import java.io.IOException;

public abstract interface zzai
{
  public static final class zza
    extends zzaow<zza>
  {
    private static volatile zza[] zzws;
    public int type;
    public String zzwt;
    public zza[] zzwu;
    public zza[] zzwv;
    public zza[] zzww;
    public String zzwx;
    public String zzwy;
    public long zzwz;
    public boolean zzxa;
    public zza[] zzxb;
    public int[] zzxc;
    public boolean zzxd;
    
    public zza()
    {
      zzar();
    }
    
    public static zza[] zzaq()
    {
      if (zzws == null) {}
      synchronized (zzapa.bij)
      {
        if (zzws == null) {
          zzws = new zza[0];
        }
        return zzws;
      }
    }
    
    public boolean equals(Object paramObject)
    {
      boolean bool2 = false;
      boolean bool1;
      if (paramObject == this) {
        bool1 = true;
      }
      label54:
      label118:
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
                    } while (!(paramObject instanceof zza));
                    paramObject = (zza)paramObject;
                    bool1 = bool2;
                  } while (type != type);
                  if (zzwt != null) {
                    break;
                  }
                  bool1 = bool2;
                } while (zzwt != null);
                bool1 = bool2;
              } while (!zzapa.equals(zzwu, zzwu));
              bool1 = bool2;
            } while (!zzapa.equals(zzwv, zzwv));
            bool1 = bool2;
          } while (!zzapa.equals(zzww, zzww));
          if (zzwx != null) {
            break label260;
          }
          bool1 = bool2;
        } while (zzwx != null);
        if (zzwy != null) {
          break label276;
        }
        bool1 = bool2;
      } while (zzwy != null);
      label260:
      label276:
      while (zzwy.equals(zzwy))
      {
        bool1 = bool2;
        if (zzwz != zzwz) {
          break;
        }
        bool1 = bool2;
        if (zzxa != zzxa) {
          break;
        }
        bool1 = bool2;
        if (!zzapa.equals(zzxb, zzxb)) {
          break;
        }
        bool1 = bool2;
        if (!zzapa.equals(zzxc, zzxc)) {
          break;
        }
        bool1 = bool2;
        if (zzxd != zzxd) {
          break;
        }
        if ((bib != null) && (!bib.isEmpty())) {
          break label292;
        }
        if (bib != null)
        {
          bool1 = bool2;
          if (!bib.isEmpty()) {
            break;
          }
        }
        return true;
        if (zzwt.equals(zzwt)) {
          break label54;
        }
        return false;
        if (zzwx.equals(zzwx)) {
          break label118;
        }
        return false;
      }
      return false;
      label292:
      return bib.equals(bib);
    }
    
    public int hashCode()
    {
      int n = 1231;
      int i2 = 0;
      int i3 = getClass().getName().hashCode();
      int i4 = type;
      int i;
      int i5;
      int i6;
      int i7;
      int j;
      label71:
      int k;
      label80:
      int i8;
      int m;
      label107:
      int i9;
      int i10;
      if (zzwt == null)
      {
        i = 0;
        i5 = zzapa.hashCode(zzwu);
        i6 = zzapa.hashCode(zzwv);
        i7 = zzapa.hashCode(zzww);
        if (zzwx != null) {
          break label250;
        }
        j = 0;
        if (zzwy != null) {
          break label261;
        }
        k = 0;
        i8 = (int)(zzwz ^ zzwz >>> 32);
        if (!zzxa) {
          break label272;
        }
        m = 1231;
        i9 = zzapa.hashCode(zzxb);
        i10 = zzapa.hashCode(zzxc);
        if (!zzxd) {
          break label280;
        }
        label132:
        i1 = i2;
        if (bib != null) {
          if (!bib.isEmpty()) {
            break label288;
          }
        }
      }
      label250:
      label261:
      label272:
      label280:
      label288:
      for (int i1 = i2;; i1 = bib.hashCode())
      {
        return ((((m + ((k + (j + ((((i + ((i3 + 527) * 31 + i4) * 31) * 31 + i5) * 31 + i6) * 31 + i7) * 31) * 31) * 31 + i8) * 31) * 31 + i9) * 31 + i10) * 31 + n) * 31 + i1;
        i = zzwt.hashCode();
        break;
        j = zzwx.hashCode();
        break label71;
        k = zzwy.hashCode();
        break label80;
        m = 1237;
        break label107;
        n = 1237;
        break label132;
      }
    }
    
    public void zza(zzaov paramzzaov)
      throws IOException
    {
      int j = 0;
      paramzzaov.zzae(1, type);
      if (!zzwt.equals("")) {
        paramzzaov.zzr(2, zzwt);
      }
      int i;
      zza localzza;
      if ((zzwu != null) && (zzwu.length > 0))
      {
        i = 0;
        while (i < zzwu.length)
        {
          localzza = zzwu[i];
          if (localzza != null) {
            paramzzaov.zza(3, localzza);
          }
          i += 1;
        }
      }
      if ((zzwv != null) && (zzwv.length > 0))
      {
        i = 0;
        while (i < zzwv.length)
        {
          localzza = zzwv[i];
          if (localzza != null) {
            paramzzaov.zza(4, localzza);
          }
          i += 1;
        }
      }
      if ((zzww != null) && (zzww.length > 0))
      {
        i = 0;
        while (i < zzww.length)
        {
          localzza = zzww[i];
          if (localzza != null) {
            paramzzaov.zza(5, localzza);
          }
          i += 1;
        }
      }
      if (!zzwx.equals("")) {
        paramzzaov.zzr(6, zzwx);
      }
      if (!zzwy.equals("")) {
        paramzzaov.zzr(7, zzwy);
      }
      if (zzwz != 0L) {
        paramzzaov.zzb(8, zzwz);
      }
      if (zzxd) {
        paramzzaov.zzj(9, zzxd);
      }
      if ((zzxc != null) && (zzxc.length > 0))
      {
        i = 0;
        while (i < zzxc.length)
        {
          paramzzaov.zzae(10, zzxc[i]);
          i += 1;
        }
      }
      if ((zzxb != null) && (zzxb.length > 0))
      {
        i = j;
        while (i < zzxb.length)
        {
          localzza = zzxb[i];
          if (localzza != null) {
            paramzzaov.zza(11, localzza);
          }
          i += 1;
        }
      }
      if (zzxa) {
        paramzzaov.zzj(12, zzxa);
      }
      super.zza(paramzzaov);
    }
    
    public zza zzar()
    {
      type = 1;
      zzwt = "";
      zzwu = zzaq();
      zzwv = zzaq();
      zzww = zzaq();
      zzwx = "";
      zzwy = "";
      zzwz = 0L;
      zzxa = false;
      zzxb = zzaq();
      zzxc = zzapf.bim;
      zzxd = false;
      bib = null;
      bik = -1;
      return this;
    }
    
    public zza zzt(zzaou paramzzaou)
      throws IOException
    {
      for (;;)
      {
        int i = paramzzaou.J();
        int j;
        Object localObject;
        int k;
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
          case 4: 
          case 5: 
          case 6: 
          case 7: 
          case 8: 
            type = i;
          }
          break;
        case 18: 
          zzwt = paramzzaou.readString();
          break;
        case 26: 
          j = zzapf.zzc(paramzzaou, 26);
          if (zzwu == null) {}
          for (i = 0;; i = zzwu.length)
          {
            localObject = new zza[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(zzwu, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length - 1)
            {
              localObject[j] = new zza();
              paramzzaou.zza(localObject[j]);
              paramzzaou.J();
              j += 1;
            }
          }
          localObject[j] = new zza();
          paramzzaou.zza(localObject[j]);
          zzwu = ((zza[])localObject);
          break;
        case 34: 
          j = zzapf.zzc(paramzzaou, 34);
          if (zzwv == null) {}
          for (i = 0;; i = zzwv.length)
          {
            localObject = new zza[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(zzwv, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length - 1)
            {
              localObject[j] = new zza();
              paramzzaou.zza(localObject[j]);
              paramzzaou.J();
              j += 1;
            }
          }
          localObject[j] = new zza();
          paramzzaou.zza(localObject[j]);
          zzwv = ((zza[])localObject);
          break;
        case 42: 
          j = zzapf.zzc(paramzzaou, 42);
          if (zzww == null) {}
          for (i = 0;; i = zzww.length)
          {
            localObject = new zza[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(zzww, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length - 1)
            {
              localObject[j] = new zza();
              paramzzaou.zza(localObject[j]);
              paramzzaou.J();
              j += 1;
            }
          }
          localObject[j] = new zza();
          paramzzaou.zza(localObject[j]);
          zzww = ((zza[])localObject);
          break;
        case 50: 
          zzwx = paramzzaou.readString();
          break;
        case 58: 
          zzwy = paramzzaou.readString();
          break;
        case 64: 
          zzwz = paramzzaou.M();
          break;
        case 72: 
          zzxd = paramzzaou.P();
          break;
        case 80: 
          int m = zzapf.zzc(paramzzaou, 80);
          localObject = new int[m];
          j = 0;
          i = 0;
          if (j < m)
          {
            if (j != 0) {
              paramzzaou.J();
            }
            int n = paramzzaou.N();
            switch (n)
            {
            }
            for (;;)
            {
              j += 1;
              break;
              k = i + 1;
              localObject[i] = n;
              i = k;
            }
          }
          if (i != 0)
          {
            if (zzxc == null) {}
            for (j = 0;; j = zzxc.length)
            {
              if ((j != 0) || (i != m)) {
                break label809;
              }
              zzxc = ((int[])localObject);
              break;
            }
            int[] arrayOfInt = new int[j + i];
            if (j != 0) {
              System.arraycopy(zzxc, 0, arrayOfInt, 0, j);
            }
            System.arraycopy(localObject, 0, arrayOfInt, j, i);
            zzxc = arrayOfInt;
          }
          break;
        case 82: 
          k = paramzzaou.zzaei(paramzzaou.S());
          i = paramzzaou.getPosition();
          j = 0;
          while (paramzzaou.X() > 0) {
            switch (paramzzaou.N())
            {
            default: 
              break;
            case 1: 
            case 2: 
            case 3: 
            case 4: 
            case 5: 
            case 6: 
            case 7: 
            case 8: 
            case 9: 
            case 10: 
            case 11: 
            case 12: 
            case 13: 
            case 14: 
            case 15: 
            case 16: 
            case 17: 
              j += 1;
            }
          }
          if (j != 0)
          {
            paramzzaou.zzaek(i);
            if (zzxc == null) {}
            for (i = 0;; i = zzxc.length)
            {
              localObject = new int[j + i];
              j = i;
              if (i != 0)
              {
                System.arraycopy(zzxc, 0, localObject, 0, i);
                j = i;
              }
              while (paramzzaou.X() > 0)
              {
                i = paramzzaou.N();
                switch (i)
                {
                default: 
                  break;
                case 1: 
                case 2: 
                case 3: 
                case 4: 
                case 5: 
                case 6: 
                case 7: 
                case 8: 
                case 9: 
                case 10: 
                case 11: 
                case 12: 
                case 13: 
                case 14: 
                case 15: 
                case 16: 
                case 17: 
                  localObject[j] = i;
                  j += 1;
                }
              }
            }
            zzxc = ((int[])localObject);
          }
          paramzzaou.zzaej(k);
          break;
        case 90: 
          j = zzapf.zzc(paramzzaou, 90);
          if (zzxb == null) {}
          for (i = 0;; i = zzxb.length)
          {
            localObject = new zza[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(zzxb, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length - 1)
            {
              localObject[j] = new zza();
              paramzzaou.zza(localObject[j]);
              paramzzaou.J();
              j += 1;
            }
          }
          localObject[j] = new zza();
          paramzzaou.zza(localObject[j]);
          zzxb = ((zza[])localObject);
          break;
        case 96: 
          label809:
          zzxa = paramzzaou.P();
        }
      }
    }
    
    protected int zzy()
    {
      int m = 0;
      int j = super.zzy() + zzaov.zzag(1, type);
      int i = j;
      if (!zzwt.equals("")) {
        i = j + zzaov.zzs(2, zzwt);
      }
      j = i;
      zza localzza;
      int k;
      if (zzwu != null)
      {
        j = i;
        if (zzwu.length > 0)
        {
          j = 0;
          while (j < zzwu.length)
          {
            localzza = zzwu[j];
            k = i;
            if (localzza != null) {
              k = i + zzaov.zzc(3, localzza);
            }
            j += 1;
            i = k;
          }
          j = i;
        }
      }
      i = j;
      if (zzwv != null)
      {
        i = j;
        if (zzwv.length > 0)
        {
          i = j;
          j = 0;
          while (j < zzwv.length)
          {
            localzza = zzwv[j];
            k = i;
            if (localzza != null) {
              k = i + zzaov.zzc(4, localzza);
            }
            j += 1;
            i = k;
          }
        }
      }
      j = i;
      if (zzww != null)
      {
        j = i;
        if (zzww.length > 0)
        {
          j = 0;
          while (j < zzww.length)
          {
            localzza = zzww[j];
            k = i;
            if (localzza != null) {
              k = i + zzaov.zzc(5, localzza);
            }
            j += 1;
            i = k;
          }
          j = i;
        }
      }
      i = j;
      if (!zzwx.equals("")) {
        i = j + zzaov.zzs(6, zzwx);
      }
      j = i;
      if (!zzwy.equals("")) {
        j = i + zzaov.zzs(7, zzwy);
      }
      i = j;
      if (zzwz != 0L) {
        i = j + zzaov.zze(8, zzwz);
      }
      j = i;
      if (zzxd) {
        j = i + zzaov.zzk(9, zzxd);
      }
      i = j;
      if (zzxc != null)
      {
        i = j;
        if (zzxc.length > 0)
        {
          i = 0;
          k = 0;
          while (i < zzxc.length)
          {
            k += zzaov.zzaeo(zzxc[i]);
            i += 1;
          }
          i = j + k + zzxc.length * 1;
        }
      }
      j = i;
      if (zzxb != null)
      {
        j = i;
        if (zzxb.length > 0)
        {
          k = m;
          for (;;)
          {
            j = i;
            if (k >= zzxb.length) {
              break;
            }
            localzza = zzxb[k];
            j = i;
            if (localzza != null) {
              j = i + zzaov.zzc(11, localzza);
            }
            k += 1;
            i = j;
          }
        }
      }
      i = j;
      if (zzxa) {
        i = j + zzaov.zzk(12, zzxa);
      }
      return i;
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzai
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */