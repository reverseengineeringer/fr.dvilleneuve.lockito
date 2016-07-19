package com.google.android.gms.internal;

import java.io.IOException;

public final class zzah$zzf
  extends zzaow<zzf>
{
  public String version;
  public String[] zzvc;
  public String[] zzvd;
  public zzai.zza[] zzve;
  public zzah.zze[] zzvf;
  public zzah.zzb[] zzvg;
  public zzah.zzb[] zzvh;
  public zzah.zzb[] zzvi;
  public zzah.zzg[] zzvj;
  public String zzvk;
  public String zzvl;
  public String zzvm;
  public zzah.zza zzvn;
  public float zzvo;
  public boolean zzvp;
  public String[] zzvq;
  public int zzvr;
  
  public zzah$zzf()
  {
    zzaj();
  }
  
  public static zzf zze(byte[] paramArrayOfByte)
    throws zzapb
  {
    return (zzf)zzapc.zza(new zzf(), paramArrayOfByte);
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool1;
    if (paramObject == this) {
      bool1 = true;
    }
    label169:
    label185:
    label201:
    label217:
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
                            do
                            {
                              do
                              {
                                return bool1;
                                bool1 = bool2;
                              } while (!(paramObject instanceof zzf));
                              paramObject = (zzf)paramObject;
                              bool1 = bool2;
                            } while (!zzapa.equals(zzvc, zzvc));
                            bool1 = bool2;
                          } while (!zzapa.equals(zzvd, zzvd));
                          bool1 = bool2;
                        } while (!zzapa.equals(zzve, zzve));
                        bool1 = bool2;
                      } while (!zzapa.equals(zzvf, zzvf));
                      bool1 = bool2;
                    } while (!zzapa.equals(zzvg, zzvg));
                    bool1 = bool2;
                  } while (!zzapa.equals(zzvh, zzvh));
                  bool1 = bool2;
                } while (!zzapa.equals(zzvi, zzvi));
                bool1 = bool2;
              } while (!zzapa.equals(zzvj, zzvj));
              if (zzvk != null) {
                break;
              }
              bool1 = bool2;
            } while (zzvk != null);
            if (zzvl != null) {
              break label348;
            }
            bool1 = bool2;
          } while (zzvl != null);
          if (zzvm != null) {
            break label364;
          }
          bool1 = bool2;
        } while (zzvm != null);
        if (version != null) {
          break label380;
        }
        bool1 = bool2;
      } while (version != null);
      if (zzvn != null) {
        break label396;
      }
      bool1 = bool2;
    } while (zzvn != null);
    label348:
    label364:
    label380:
    label396:
    while (zzvn.equals(zzvn))
    {
      bool1 = bool2;
      if (Float.floatToIntBits(zzvo) != Float.floatToIntBits(zzvo)) {
        break;
      }
      bool1 = bool2;
      if (zzvp != zzvp) {
        break;
      }
      bool1 = bool2;
      if (!zzapa.equals(zzvq, zzvq)) {
        break;
      }
      bool1 = bool2;
      if (zzvr != zzvr) {
        break;
      }
      if ((bib != null) && (!bib.isEmpty())) {
        break label412;
      }
      if (bib != null)
      {
        bool1 = bool2;
        if (!bib.isEmpty()) {
          break;
        }
      }
      return true;
      if (zzvk.equals(zzvk)) {
        break label169;
      }
      return false;
      if (zzvl.equals(zzvl)) {
        break label185;
      }
      return false;
      if (zzvm.equals(zzvm)) {
        break label201;
      }
      return false;
      if (version.equals(version)) {
        break label217;
      }
      return false;
    }
    return false;
    label412:
    return bib.equals(bib);
  }
  
  public int hashCode()
  {
    int i3 = 0;
    int i4 = getClass().getName().hashCode();
    int i5 = zzapa.hashCode(zzvc);
    int i6 = zzapa.hashCode(zzvd);
    int i7 = zzapa.hashCode(zzve);
    int i8 = zzapa.hashCode(zzvf);
    int i9 = zzapa.hashCode(zzvg);
    int i10 = zzapa.hashCode(zzvh);
    int i11 = zzapa.hashCode(zzvi);
    int i12 = zzapa.hashCode(zzvj);
    int i;
    int j;
    label105:
    int k;
    label114:
    int m;
    label124:
    int n;
    label134:
    int i13;
    int i1;
    label155:
    int i14;
    int i15;
    if (zzvk == null)
    {
      i = 0;
      if (zzvl != null) {
        break label318;
      }
      j = 0;
      if (zzvm != null) {
        break label329;
      }
      k = 0;
      if (version != null) {
        break label340;
      }
      m = 0;
      if (zzvn != null) {
        break label352;
      }
      n = 0;
      i13 = Float.floatToIntBits(zzvo);
      if (!zzvp) {
        break label364;
      }
      i1 = 1231;
      i14 = zzapa.hashCode(zzvq);
      i15 = zzvr;
      i2 = i3;
      if (bib != null) {
        if (!bib.isEmpty()) {
          break label372;
        }
      }
    }
    label318:
    label329:
    label340:
    label352:
    label364:
    label372:
    for (int i2 = i3;; i2 = bib.hashCode())
    {
      return (((i1 + ((n + (m + (k + (j + (i + (((((((((i4 + 527) * 31 + i5) * 31 + i6) * 31 + i7) * 31 + i8) * 31 + i9) * 31 + i10) * 31 + i11) * 31 + i12) * 31) * 31) * 31) * 31) * 31) * 31 + i13) * 31) * 31 + i14) * 31 + i15) * 31 + i2;
      i = zzvk.hashCode();
      break;
      j = zzvl.hashCode();
      break label105;
      k = zzvm.hashCode();
      break label114;
      m = version.hashCode();
      break label124;
      n = zzvn.hashCode();
      break label134;
      i1 = 1237;
      break label155;
    }
  }
  
  public void zza(zzaov paramzzaov)
    throws IOException
  {
    int j = 0;
    int i;
    Object localObject;
    if ((zzvd != null) && (zzvd.length > 0))
    {
      i = 0;
      while (i < zzvd.length)
      {
        localObject = zzvd[i];
        if (localObject != null) {
          paramzzaov.zzr(1, (String)localObject);
        }
        i += 1;
      }
    }
    if ((zzve != null) && (zzve.length > 0))
    {
      i = 0;
      while (i < zzve.length)
      {
        localObject = zzve[i];
        if (localObject != null) {
          paramzzaov.zza(2, (zzapc)localObject);
        }
        i += 1;
      }
    }
    if ((zzvf != null) && (zzvf.length > 0))
    {
      i = 0;
      while (i < zzvf.length)
      {
        localObject = zzvf[i];
        if (localObject != null) {
          paramzzaov.zza(3, (zzapc)localObject);
        }
        i += 1;
      }
    }
    if ((zzvg != null) && (zzvg.length > 0))
    {
      i = 0;
      while (i < zzvg.length)
      {
        localObject = zzvg[i];
        if (localObject != null) {
          paramzzaov.zza(4, (zzapc)localObject);
        }
        i += 1;
      }
    }
    if ((zzvh != null) && (zzvh.length > 0))
    {
      i = 0;
      while (i < zzvh.length)
      {
        localObject = zzvh[i];
        if (localObject != null) {
          paramzzaov.zza(5, (zzapc)localObject);
        }
        i += 1;
      }
    }
    if ((zzvi != null) && (zzvi.length > 0))
    {
      i = 0;
      while (i < zzvi.length)
      {
        localObject = zzvi[i];
        if (localObject != null) {
          paramzzaov.zza(6, (zzapc)localObject);
        }
        i += 1;
      }
    }
    if ((zzvj != null) && (zzvj.length > 0))
    {
      i = 0;
      while (i < zzvj.length)
      {
        localObject = zzvj[i];
        if (localObject != null) {
          paramzzaov.zza(7, (zzapc)localObject);
        }
        i += 1;
      }
    }
    if (!zzvk.equals("")) {
      paramzzaov.zzr(9, zzvk);
    }
    if (!zzvl.equals("")) {
      paramzzaov.zzr(10, zzvl);
    }
    if (!zzvm.equals("0")) {
      paramzzaov.zzr(12, zzvm);
    }
    if (!version.equals("")) {
      paramzzaov.zzr(13, version);
    }
    if (zzvn != null) {
      paramzzaov.zza(14, zzvn);
    }
    if (Float.floatToIntBits(zzvo) != Float.floatToIntBits(0.0F)) {
      paramzzaov.zzc(15, zzvo);
    }
    if ((zzvq != null) && (zzvq.length > 0))
    {
      i = 0;
      while (i < zzvq.length)
      {
        localObject = zzvq[i];
        if (localObject != null) {
          paramzzaov.zzr(16, (String)localObject);
        }
        i += 1;
      }
    }
    if (zzvr != 0) {
      paramzzaov.zzae(17, zzvr);
    }
    if (zzvp) {
      paramzzaov.zzj(18, zzvp);
    }
    if ((zzvc != null) && (zzvc.length > 0))
    {
      i = j;
      while (i < zzvc.length)
      {
        localObject = zzvc[i];
        if (localObject != null) {
          paramzzaov.zzr(19, (String)localObject);
        }
        i += 1;
      }
    }
    super.zza(paramzzaov);
  }
  
  public zzf zzaj()
  {
    zzvc = zzapf.bir;
    zzvd = zzapf.bir;
    zzve = zzai.zza.zzaq();
    zzvf = zzah.zze.zzah();
    zzvg = zzah.zzb.zzac();
    zzvh = zzah.zzb.zzac();
    zzvi = zzah.zzb.zzac();
    zzvj = zzah.zzg.zzak();
    zzvk = "";
    zzvl = "";
    zzvm = "0";
    version = "";
    zzvn = null;
    zzvo = 0.0F;
    zzvp = false;
    zzvq = zzapf.bir;
    zzvr = 0;
    bib = null;
    bik = -1;
    return this;
  }
  
  public zzf zzo(zzaou paramzzaou)
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
        if (zzvd == null) {}
        for (i = 0;; i = zzvd.length)
        {
          localObject = new String[j + i];
          j = i;
          if (i != 0)
          {
            System.arraycopy(zzvd, 0, localObject, 0, i);
            j = i;
          }
          while (j < localObject.length - 1)
          {
            localObject[j] = paramzzaou.readString();
            paramzzaou.J();
            j += 1;
          }
        }
        localObject[j] = paramzzaou.readString();
        zzvd = ((String[])localObject);
        break;
      case 18: 
        j = zzapf.zzc(paramzzaou, 18);
        if (zzve == null) {}
        for (i = 0;; i = zzve.length)
        {
          localObject = new zzai.zza[j + i];
          j = i;
          if (i != 0)
          {
            System.arraycopy(zzve, 0, localObject, 0, i);
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
        zzve = ((zzai.zza[])localObject);
        break;
      case 26: 
        j = zzapf.zzc(paramzzaou, 26);
        if (zzvf == null) {}
        for (i = 0;; i = zzvf.length)
        {
          localObject = new zzah.zze[j + i];
          j = i;
          if (i != 0)
          {
            System.arraycopy(zzvf, 0, localObject, 0, i);
            j = i;
          }
          while (j < localObject.length - 1)
          {
            localObject[j] = new zzah.zze();
            paramzzaou.zza(localObject[j]);
            paramzzaou.J();
            j += 1;
          }
        }
        localObject[j] = new zzah.zze();
        paramzzaou.zza(localObject[j]);
        zzvf = ((zzah.zze[])localObject);
        break;
      case 34: 
        j = zzapf.zzc(paramzzaou, 34);
        if (zzvg == null) {}
        for (i = 0;; i = zzvg.length)
        {
          localObject = new zzah.zzb[j + i];
          j = i;
          if (i != 0)
          {
            System.arraycopy(zzvg, 0, localObject, 0, i);
            j = i;
          }
          while (j < localObject.length - 1)
          {
            localObject[j] = new zzah.zzb();
            paramzzaou.zza(localObject[j]);
            paramzzaou.J();
            j += 1;
          }
        }
        localObject[j] = new zzah.zzb();
        paramzzaou.zza(localObject[j]);
        zzvg = ((zzah.zzb[])localObject);
        break;
      case 42: 
        j = zzapf.zzc(paramzzaou, 42);
        if (zzvh == null) {}
        for (i = 0;; i = zzvh.length)
        {
          localObject = new zzah.zzb[j + i];
          j = i;
          if (i != 0)
          {
            System.arraycopy(zzvh, 0, localObject, 0, i);
            j = i;
          }
          while (j < localObject.length - 1)
          {
            localObject[j] = new zzah.zzb();
            paramzzaou.zza(localObject[j]);
            paramzzaou.J();
            j += 1;
          }
        }
        localObject[j] = new zzah.zzb();
        paramzzaou.zza(localObject[j]);
        zzvh = ((zzah.zzb[])localObject);
        break;
      case 50: 
        j = zzapf.zzc(paramzzaou, 50);
        if (zzvi == null) {}
        for (i = 0;; i = zzvi.length)
        {
          localObject = new zzah.zzb[j + i];
          j = i;
          if (i != 0)
          {
            System.arraycopy(zzvi, 0, localObject, 0, i);
            j = i;
          }
          while (j < localObject.length - 1)
          {
            localObject[j] = new zzah.zzb();
            paramzzaou.zza(localObject[j]);
            paramzzaou.J();
            j += 1;
          }
        }
        localObject[j] = new zzah.zzb();
        paramzzaou.zza(localObject[j]);
        zzvi = ((zzah.zzb[])localObject);
        break;
      case 58: 
        j = zzapf.zzc(paramzzaou, 58);
        if (zzvj == null) {}
        for (i = 0;; i = zzvj.length)
        {
          localObject = new zzah.zzg[j + i];
          j = i;
          if (i != 0)
          {
            System.arraycopy(zzvj, 0, localObject, 0, i);
            j = i;
          }
          while (j < localObject.length - 1)
          {
            localObject[j] = new zzah.zzg();
            paramzzaou.zza(localObject[j]);
            paramzzaou.J();
            j += 1;
          }
        }
        localObject[j] = new zzah.zzg();
        paramzzaou.zza(localObject[j]);
        zzvj = ((zzah.zzg[])localObject);
        break;
      case 74: 
        zzvk = paramzzaou.readString();
        break;
      case 82: 
        zzvl = paramzzaou.readString();
        break;
      case 98: 
        zzvm = paramzzaou.readString();
        break;
      case 106: 
        version = paramzzaou.readString();
        break;
      case 114: 
        if (zzvn == null) {
          zzvn = new zzah.zza();
        }
        paramzzaou.zza(zzvn);
        break;
      case 125: 
        zzvo = paramzzaou.readFloat();
        break;
      case 130: 
        j = zzapf.zzc(paramzzaou, 130);
        if (zzvq == null) {}
        for (i = 0;; i = zzvq.length)
        {
          localObject = new String[j + i];
          j = i;
          if (i != 0)
          {
            System.arraycopy(zzvq, 0, localObject, 0, i);
            j = i;
          }
          while (j < localObject.length - 1)
          {
            localObject[j] = paramzzaou.readString();
            paramzzaou.J();
            j += 1;
          }
        }
        localObject[j] = paramzzaou.readString();
        zzvq = ((String[])localObject);
        break;
      case 136: 
        zzvr = paramzzaou.N();
        break;
      case 144: 
        zzvp = paramzzaou.P();
        break;
      case 154: 
        j = zzapf.zzc(paramzzaou, 154);
        if (zzvc == null) {}
        for (i = 0;; i = zzvc.length)
        {
          localObject = new String[j + i];
          j = i;
          if (i != 0)
          {
            System.arraycopy(zzvc, 0, localObject, 0, i);
            j = i;
          }
          while (j < localObject.length - 1)
          {
            localObject[j] = paramzzaou.readString();
            paramzzaou.J();
            j += 1;
          }
        }
        localObject[j] = paramzzaou.readString();
        zzvc = ((String[])localObject);
      }
    }
  }
  
  protected int zzy()
  {
    int i2 = 0;
    int i1 = super.zzy();
    int i;
    int k;
    Object localObject;
    int n;
    int m;
    if ((zzvd != null) && (zzvd.length > 0))
    {
      i = 0;
      j = 0;
      for (k = 0; i < zzvd.length; k = m)
      {
        localObject = zzvd[i];
        n = j;
        m = k;
        if (localObject != null)
        {
          m = k + 1;
          n = j + zzaov.zztg((String)localObject);
        }
        i += 1;
        j = n;
      }
    }
    for (int j = i1 + j + k * 1;; j = i1)
    {
      i = j;
      if (zzve != null)
      {
        i = j;
        if (zzve.length > 0)
        {
          i = j;
          j = 0;
          while (j < zzve.length)
          {
            localObject = zzve[j];
            k = i;
            if (localObject != null) {
              k = i + zzaov.zzc(2, (zzapc)localObject);
            }
            j += 1;
            i = k;
          }
        }
      }
      j = i;
      if (zzvf != null)
      {
        j = i;
        if (zzvf.length > 0)
        {
          j = 0;
          while (j < zzvf.length)
          {
            localObject = zzvf[j];
            k = i;
            if (localObject != null) {
              k = i + zzaov.zzc(3, (zzapc)localObject);
            }
            j += 1;
            i = k;
          }
          j = i;
        }
      }
      i = j;
      if (zzvg != null)
      {
        i = j;
        if (zzvg.length > 0)
        {
          i = j;
          j = 0;
          while (j < zzvg.length)
          {
            localObject = zzvg[j];
            k = i;
            if (localObject != null) {
              k = i + zzaov.zzc(4, (zzapc)localObject);
            }
            j += 1;
            i = k;
          }
        }
      }
      j = i;
      if (zzvh != null)
      {
        j = i;
        if (zzvh.length > 0)
        {
          j = 0;
          while (j < zzvh.length)
          {
            localObject = zzvh[j];
            k = i;
            if (localObject != null) {
              k = i + zzaov.zzc(5, (zzapc)localObject);
            }
            j += 1;
            i = k;
          }
          j = i;
        }
      }
      i = j;
      if (zzvi != null)
      {
        i = j;
        if (zzvi.length > 0)
        {
          i = j;
          j = 0;
          while (j < zzvi.length)
          {
            localObject = zzvi[j];
            k = i;
            if (localObject != null) {
              k = i + zzaov.zzc(6, (zzapc)localObject);
            }
            j += 1;
            i = k;
          }
        }
      }
      j = i;
      if (zzvj != null)
      {
        j = i;
        if (zzvj.length > 0)
        {
          j = 0;
          while (j < zzvj.length)
          {
            localObject = zzvj[j];
            k = i;
            if (localObject != null) {
              k = i + zzaov.zzc(7, (zzapc)localObject);
            }
            j += 1;
            i = k;
          }
          j = i;
        }
      }
      i = j;
      if (!zzvk.equals("")) {
        i = j + zzaov.zzs(9, zzvk);
      }
      j = i;
      if (!zzvl.equals("")) {
        j = i + zzaov.zzs(10, zzvl);
      }
      i = j;
      if (!zzvm.equals("0")) {
        i = j + zzaov.zzs(12, zzvm);
      }
      j = i;
      if (!version.equals("")) {
        j = i + zzaov.zzs(13, version);
      }
      k = j;
      if (zzvn != null) {
        k = j + zzaov.zzc(14, zzvn);
      }
      i = k;
      if (Float.floatToIntBits(zzvo) != Float.floatToIntBits(0.0F)) {
        i = k + zzaov.zzd(15, zzvo);
      }
      j = i;
      if (zzvq != null)
      {
        j = i;
        if (zzvq.length > 0)
        {
          j = 0;
          k = 0;
          for (m = 0; j < zzvq.length; m = n)
          {
            localObject = zzvq[j];
            i1 = k;
            n = m;
            if (localObject != null)
            {
              n = m + 1;
              i1 = k + zzaov.zztg((String)localObject);
            }
            j += 1;
            k = i1;
          }
          j = i + k + m * 2;
        }
      }
      k = j;
      if (zzvr != 0) {
        k = j + zzaov.zzag(17, zzvr);
      }
      i = k;
      if (zzvp) {
        i = k + zzaov.zzk(18, zzvp);
      }
      j = i;
      if (zzvc != null)
      {
        j = i;
        if (zzvc.length > 0)
        {
          k = 0;
          m = 0;
          j = i2;
          while (j < zzvc.length)
          {
            localObject = zzvc[j];
            i1 = k;
            n = m;
            if (localObject != null)
            {
              n = m + 1;
              i1 = k + zzaov.zztg((String)localObject);
            }
            j += 1;
            k = i1;
            m = n;
          }
          j = i + k + m * 2;
        }
      }
      return j;
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzah.zzf
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */