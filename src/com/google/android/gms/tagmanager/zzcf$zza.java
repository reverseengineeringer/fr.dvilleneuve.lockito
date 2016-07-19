package com.google.android.gms.tagmanager;

import com.google.android.gms.common.util.zze;

class zzcf$zza
  implements zzde.zza
{
  zzcf$zza(zzcf paramzzcf) {}
  
  public void zza(zzar paramzzar)
  {
    zzcf.zza(axd, paramzzar.zzcbn());
  }
  
  public void zzb(zzar paramzzar)
  {
    zzcf.zza(axd, paramzzar.zzcbn());
    long l = paramzzar.zzcbn();
    zzbn.v(57 + "Permanent failure dispatching hitId: " + l);
  }
  
  public void zzc(zzar paramzzar)
  {
    long l = paramzzar.zzcbo();
    if (l == 0L) {
      zzcf.zza(axd, paramzzar.zzcbn(), zzcf.zza(axd).currentTimeMillis());
    }
    while (l + 14400000L >= zzcf.zza(axd).currentTimeMillis()) {
      return;
    }
    zzcf.zza(axd, paramzzar.zzcbn());
    l = paramzzar.zzcbn();
    zzbn.v(47 + "Giving up on failed hitId: " + l);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzcf.zza
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */