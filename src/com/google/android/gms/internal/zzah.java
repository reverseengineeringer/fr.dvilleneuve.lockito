package com.google.android.gms.internal;

import java.io.IOException;

public abstract interface zzah
{
  public static final class zza
    extends zzaow<zza>
  {
    public int level;
    public int zzum;
    public int zzun;
    
    public zza()
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
  
  public static final class zzb
    extends zzaow<zzb>
  {
    private static volatile zzb[] zzuo;
    public int name;
    public int[] zzup;
    public int zzuq;
    public boolean zzur;
    public boolean zzus;
    
    public zzb()
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
  
  public static final class zzc
    extends zzaow<zzc>
  {
    private static volatile zzc[] zzut;
    public String zzcb;
    public long zzuu;
    public long zzuv;
    public boolean zzuw;
    public long zzux;
    
    public zzc()
    {
      zzaf();
    }
    
    public static zzc[] zzae()
    {
      if (zzut == null) {}
      synchronized (zzapa.bij)
      {
        if (zzut == null) {
          zzut = new zzc[0];
        }
        return zzut;
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
          return bool1;
          bool1 = bool2;
        } while (!(paramObject instanceof zzc));
        paramObject = (zzc)paramObject;
        if (zzcb != null) {
          break;
        }
        bool1 = bool2;
      } while (zzcb != null);
      while (zzcb.equals(zzcb))
      {
        bool1 = bool2;
        if (zzuu != zzuu) {
          break;
        }
        bool1 = bool2;
        if (zzuv != zzuv) {
          break;
        }
        bool1 = bool2;
        if (zzuw != zzuw) {
          break;
        }
        bool1 = bool2;
        if (zzux != zzux) {
          break;
        }
        if ((bib != null) && (!bib.isEmpty())) {
          break label150;
        }
        if (bib != null)
        {
          bool1 = bool2;
          if (!bib.isEmpty()) {
            break;
          }
        }
        return true;
      }
      return false;
      label150:
      return bib.equals(bib);
    }
    
    public int hashCode()
    {
      int m = 0;
      int n = getClass().getName().hashCode();
      int i;
      int i1;
      int i2;
      int j;
      label65:
      int i3;
      if (zzcb == null)
      {
        i = 0;
        i1 = (int)(zzuu ^ zzuu >>> 32);
        i2 = (int)(zzuv ^ zzuv >>> 32);
        if (!zzuw) {
          break label154;
        }
        j = 1231;
        i3 = (int)(zzux ^ zzux >>> 32);
        k = m;
        if (bib != null) {
          if (!bib.isEmpty()) {
            break label161;
          }
        }
      }
      label154:
      label161:
      for (int k = m;; k = bib.hashCode())
      {
        return ((j + (((i + (n + 527) * 31) * 31 + i1) * 31 + i2) * 31) * 31 + i3) * 31 + k;
        i = zzcb.hashCode();
        break;
        j = 1237;
        break label65;
      }
    }
    
    public void zza(zzaov paramzzaov)
      throws IOException
    {
      if (!zzcb.equals("")) {
        paramzzaov.zzr(1, zzcb);
      }
      if (zzuu != 0L) {
        paramzzaov.zzb(2, zzuu);
      }
      if (zzuv != 2147483647L) {
        paramzzaov.zzb(3, zzuv);
      }
      if (zzuw) {
        paramzzaov.zzj(4, zzuw);
      }
      if (zzux != 0L) {
        paramzzaov.zzb(5, zzux);
      }
      super.zza(paramzzaov);
    }
    
    public zzc zzaf()
    {
      zzcb = "";
      zzuu = 0L;
      zzuv = 2147483647L;
      zzuw = false;
      zzux = 0L;
      bib = null;
      bik = -1;
      return this;
    }
    
    public zzc zzl(zzaou paramzzaou)
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
          zzcb = paramzzaou.readString();
          break;
        case 16: 
          zzuu = paramzzaou.M();
          break;
        case 24: 
          zzuv = paramzzaou.M();
          break;
        case 32: 
          zzuw = paramzzaou.P();
          break;
        case 40: 
          zzux = paramzzaou.M();
        }
      }
    }
    
    protected int zzy()
    {
      int j = super.zzy();
      int i = j;
      if (!zzcb.equals("")) {
        i = j + zzaov.zzs(1, zzcb);
      }
      j = i;
      if (zzuu != 0L) {
        j = i + zzaov.zze(2, zzuu);
      }
      i = j;
      if (zzuv != 2147483647L) {
        i = j + zzaov.zze(3, zzuv);
      }
      j = i;
      if (zzuw) {
        j = i + zzaov.zzk(4, zzuw);
      }
      i = j;
      if (zzux != 0L) {
        i = j + zzaov.zze(5, zzux);
      }
      return i;
    }
  }
  
  public static final class zzd
    extends zzaow<zzd>
  {
    public zzai.zza[] zzuy;
    public zzai.zza[] zzuz;
    public zzah.zzc[] zzva;
    
    public zzd()
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
  
  public static final class zze
    extends zzaow<zze>
  {
    private static volatile zze[] zzvb;
    public int key;
    public int value;
    
    public zze()
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
  
  public static final class zzf
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
    
    public zzf()
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
  
  public static final class zzg
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
    
    public zzg()
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
  
  public static final class zzh
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
    
    public zzh()
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
  
  public static final class zzi
    extends zzaow<zzi>
  {
    private static volatile zzi[] zzwm;
    public String name;
    public zzai.zza zzwn;
    public zzah.zzd zzwo;
    
    public zzi()
    {
      zzao();
    }
    
    public static zzi[] zzan()
    {
      if (zzwm == null) {}
      synchronized (zzapa.bij)
      {
        if (zzwm == null) {
          zzwm = new zzi[0];
        }
        return zzwm;
      }
    }
    
    public boolean equals(Object paramObject)
    {
      boolean bool2 = false;
      boolean bool1;
      if (paramObject == this) {
        bool1 = true;
      }
      label41:
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
            } while (!(paramObject instanceof zzi));
            paramObject = (zzi)paramObject;
            if (name != null) {
              break;
            }
            bool1 = bool2;
          } while (name != null);
          if (zzwn != null) {
            break label127;
          }
          bool1 = bool2;
        } while (zzwn != null);
        if (zzwo != null) {
          break label143;
        }
        bool1 = bool2;
      } while (zzwo != null);
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
          if (name.equals(name)) {
            break label41;
          }
          return false;
          label127:
          if (zzwn.equals(zzwn)) {
            break label57;
          }
          return false;
          label143:
          if (!zzwo.equals(zzwo)) {
            return false;
          }
        }
      }
      return bib.equals(bib);
    }
    
    public int hashCode()
    {
      int n = 0;
      int i1 = getClass().getName().hashCode();
      int i;
      int j;
      label33:
      int k;
      if (name == null)
      {
        i = 0;
        if (zzwn != null) {
          break label106;
        }
        j = 0;
        if (zzwo != null) {
          break label117;
        }
        k = 0;
        label42:
        m = n;
        if (bib != null) {
          if (!bib.isEmpty()) {
            break label128;
          }
        }
      }
      label106:
      label117:
      label128:
      for (int m = n;; m = bib.hashCode())
      {
        return (k + (j + (i + (i1 + 527) * 31) * 31) * 31) * 31 + m;
        i = name.hashCode();
        break;
        j = zzwn.hashCode();
        break label33;
        k = zzwo.hashCode();
        break label42;
      }
    }
    
    public void zza(zzaov paramzzaov)
      throws IOException
    {
      if (!name.equals("")) {
        paramzzaov.zzr(1, name);
      }
      if (zzwn != null) {
        paramzzaov.zza(2, zzwn);
      }
      if (zzwo != null) {
        paramzzaov.zza(3, zzwo);
      }
      super.zza(paramzzaov);
    }
    
    public zzi zzao()
    {
      name = "";
      zzwn = null;
      zzwo = null;
      bib = null;
      bik = -1;
      return this;
    }
    
    public zzi zzr(zzaou paramzzaou)
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
          name = paramzzaou.readString();
          break;
        case 18: 
          if (zzwn == null) {
            zzwn = new zzai.zza();
          }
          paramzzaou.zza(zzwn);
          break;
        case 26: 
          if (zzwo == null) {
            zzwo = new zzah.zzd();
          }
          paramzzaou.zza(zzwo);
        }
      }
    }
    
    protected int zzy()
    {
      int j = super.zzy();
      int i = j;
      if (!name.equals("")) {
        i = j + zzaov.zzs(1, name);
      }
      j = i;
      if (zzwn != null) {
        j = i + zzaov.zzc(2, zzwn);
      }
      i = j;
      if (zzwo != null) {
        i = j + zzaov.zzc(3, zzwo);
      }
      return i;
    }
  }
  
  public static final class zzj
    extends zzaow<zzj>
  {
    public zzah.zzi[] zzwp;
    public zzah.zzf zzwq;
    public String zzwr;
    
    public zzj()
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
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzah
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */