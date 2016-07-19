package com.google.android.gms.measurement.internal;

import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzab;

class zza
{
  private String By;
  private final zzx aja;
  private String ajg;
  private String ajh;
  private String aji;
  private long ajj;
  private long ajk;
  private long ajl;
  private long ajm;
  private String ajn;
  private long ajo;
  private long ajp;
  private boolean ajq;
  private long ajr;
  private long ajs;
  private long ajt;
  private long aju;
  private long ajv;
  private boolean ajw;
  private long ajx;
  private long ajy;
  private final String zzcjj;
  private String zzcuq;
  
  @WorkerThread
  zza(zzx paramzzx, String paramString)
  {
    zzab.zzaa(paramzzx);
    zzab.zzhs(paramString);
    aja = paramzzx;
    zzcjj = paramString;
    aja.zzwu();
  }
  
  @WorkerThread
  public void setAppVersion(String paramString)
  {
    aja.zzwu();
    boolean bool2 = ajw;
    if (!zzal.zzbb(zzcuq, paramString)) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      ajw = (bool1 | bool2);
      zzcuq = paramString;
      return;
    }
  }
  
  @WorkerThread
  public void setMeasurementEnabled(boolean paramBoolean)
  {
    aja.zzwu();
    boolean bool2 = ajw;
    if (ajq != paramBoolean) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      ajw = (bool1 | bool2);
      ajq = paramBoolean;
      return;
    }
  }
  
  @WorkerThread
  public void zzat(long paramLong)
  {
    aja.zzwu();
    boolean bool2 = ajw;
    if (ajk != paramLong) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      ajw = (bool1 | bool2);
      ajk = paramLong;
      return;
    }
  }
  
  @WorkerThread
  public void zzau(long paramLong)
  {
    aja.zzwu();
    boolean bool2 = ajw;
    if (ajl != paramLong) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      ajw = (bool1 | bool2);
      ajl = paramLong;
      return;
    }
  }
  
  @WorkerThread
  public void zzav(long paramLong)
  {
    aja.zzwu();
    boolean bool2 = ajw;
    if (ajm != paramLong) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      ajw = (bool1 | bool2);
      ajm = paramLong;
      return;
    }
  }
  
  @WorkerThread
  public void zzaw(long paramLong)
  {
    aja.zzwu();
    boolean bool2 = ajw;
    if (ajo != paramLong) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      ajw = (bool1 | bool2);
      ajo = paramLong;
      return;
    }
  }
  
  @WorkerThread
  public String zzawj()
  {
    aja.zzwu();
    return By;
  }
  
  @WorkerThread
  public void zzax(long paramLong)
  {
    aja.zzwu();
    boolean bool2 = ajw;
    if (ajp != paramLong) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      ajw = (bool1 | bool2);
      ajp = paramLong;
      return;
    }
  }
  
  @WorkerThread
  public void zzay(long paramLong)
  {
    boolean bool1 = true;
    boolean bool2;
    if (paramLong >= 0L)
    {
      bool2 = true;
      zzab.zzbn(bool2);
      aja.zzwu();
      bool2 = ajw;
      if (ajj == paramLong) {
        break label58;
      }
    }
    for (;;)
    {
      ajw = (bool2 | bool1);
      ajj = paramLong;
      return;
      bool2 = false;
      break;
      label58:
      bool1 = false;
    }
  }
  
  @WorkerThread
  public void zzaz(long paramLong)
  {
    aja.zzwu();
    boolean bool2 = ajw;
    if (ajx != paramLong) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      ajw = (bool1 | bool2);
      ajx = paramLong;
      return;
    }
  }
  
  @WorkerThread
  public void zzba(long paramLong)
  {
    aja.zzwu();
    boolean bool2 = ajw;
    if (ajy != paramLong) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      ajw = (bool1 | bool2);
      ajy = paramLong;
      return;
    }
  }
  
  @WorkerThread
  public void zzbb(long paramLong)
  {
    aja.zzwu();
    boolean bool2 = ajw;
    if (ajr != paramLong) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      ajw = (bool1 | bool2);
      ajr = paramLong;
      return;
    }
  }
  
  @WorkerThread
  public void zzbc(long paramLong)
  {
    aja.zzwu();
    boolean bool2 = ajw;
    if (ajs != paramLong) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      ajw = (bool1 | bool2);
      ajs = paramLong;
      return;
    }
  }
  
  @WorkerThread
  public void zzbd(long paramLong)
  {
    aja.zzwu();
    boolean bool2 = ajw;
    if (ajt != paramLong) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      ajw = (bool1 | bool2);
      ajt = paramLong;
      return;
    }
  }
  
  @WorkerThread
  public void zzbe(long paramLong)
  {
    aja.zzwu();
    boolean bool2 = ajw;
    if (aju != paramLong) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      ajw = (bool1 | bool2);
      aju = paramLong;
      return;
    }
  }
  
  @WorkerThread
  public void zzbf(long paramLong)
  {
    aja.zzwu();
    boolean bool2 = ajw;
    if (ajv != paramLong) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      ajw = (bool1 | bool2);
      ajv = paramLong;
      return;
    }
  }
  
  @WorkerThread
  public void zzbqn()
  {
    aja.zzwu();
    ajw = false;
  }
  
  @WorkerThread
  public String zzbqo()
  {
    aja.zzwu();
    return ajg;
  }
  
  @WorkerThread
  public String zzbqp()
  {
    aja.zzwu();
    return ajh;
  }
  
  @WorkerThread
  public String zzbqq()
  {
    aja.zzwu();
    return aji;
  }
  
  @WorkerThread
  public long zzbqr()
  {
    aja.zzwu();
    return ajk;
  }
  
  @WorkerThread
  public long zzbqs()
  {
    aja.zzwu();
    return ajl;
  }
  
  @WorkerThread
  public long zzbqt()
  {
    aja.zzwu();
    return ajm;
  }
  
  @WorkerThread
  public String zzbqu()
  {
    aja.zzwu();
    return ajn;
  }
  
  @WorkerThread
  public long zzbqv()
  {
    aja.zzwu();
    return ajo;
  }
  
  @WorkerThread
  public long zzbqw()
  {
    aja.zzwu();
    return ajp;
  }
  
  @WorkerThread
  public boolean zzbqx()
  {
    aja.zzwu();
    return ajq;
  }
  
  @WorkerThread
  public long zzbqy()
  {
    aja.zzwu();
    return ajj;
  }
  
  @WorkerThread
  public long zzbqz()
  {
    aja.zzwu();
    return ajx;
  }
  
  @WorkerThread
  public long zzbra()
  {
    aja.zzwu();
    return ajy;
  }
  
  @WorkerThread
  public void zzbrb()
  {
    aja.zzwu();
    long l2 = ajj + 1L;
    long l1 = l2;
    if (l2 > 2147483647L)
    {
      aja.zzbsz().zzbtt().log("Bundle index overflow");
      l1 = 0L;
    }
    ajw = true;
    ajj = l1;
  }
  
  @WorkerThread
  public long zzbrc()
  {
    aja.zzwu();
    return ajr;
  }
  
  @WorkerThread
  public long zzbrd()
  {
    aja.zzwu();
    return ajs;
  }
  
  @WorkerThread
  public long zzbre()
  {
    aja.zzwu();
    return ajt;
  }
  
  @WorkerThread
  public long zzbrf()
  {
    aja.zzwu();
    return aju;
  }
  
  @WorkerThread
  public long zzbrg()
  {
    aja.zzwu();
    return ajv;
  }
  
  @WorkerThread
  public void zzkz(String paramString)
  {
    aja.zzwu();
    boolean bool2 = ajw;
    if (!zzal.zzbb(By, paramString)) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      ajw = (bool1 | bool2);
      By = paramString;
      return;
    }
  }
  
  @WorkerThread
  public void zzla(String paramString)
  {
    aja.zzwu();
    String str = paramString;
    if (TextUtils.isEmpty(paramString)) {
      str = null;
    }
    boolean bool2 = ajw;
    if (!zzal.zzbb(ajg, str)) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      ajw = (bool1 | bool2);
      ajg = str;
      return;
    }
  }
  
  @WorkerThread
  public void zzlb(String paramString)
  {
    aja.zzwu();
    boolean bool2 = ajw;
    if (!zzal.zzbb(ajh, paramString)) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      ajw = (bool1 | bool2);
      ajh = paramString;
      return;
    }
  }
  
  @WorkerThread
  public void zzlc(String paramString)
  {
    aja.zzwu();
    boolean bool2 = ajw;
    if (!zzal.zzbb(aji, paramString)) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      ajw = (bool1 | bool2);
      aji = paramString;
      return;
    }
  }
  
  @WorkerThread
  public void zzld(String paramString)
  {
    aja.zzwu();
    boolean bool2 = ajw;
    if (!zzal.zzbb(ajn, paramString)) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      ajw = (bool1 | bool2);
      ajn = paramString;
      return;
    }
  }
  
  @WorkerThread
  public String zzsi()
  {
    aja.zzwu();
    return zzcjj;
  }
  
  @WorkerThread
  public String zzxc()
  {
    aja.zzwu();
    return zzcuq;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.measurement.internal.zza
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */