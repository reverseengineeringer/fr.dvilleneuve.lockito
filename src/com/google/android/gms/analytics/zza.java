package com.google.android.gms.analytics;

import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.analytics.internal.zzf;
import com.google.android.gms.analytics.internal.zzn;
import com.google.android.gms.analytics.internal.zzu;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.internal.zzmh;
import java.util.List;
import java.util.ListIterator;

public class zza
  extends zzh<zza>
{
  private final zzf zzcrq;
  private boolean zzcrr;
  
  public zza(zzf paramzzf)
  {
    super(paramzzf.zzyz(), paramzzf.zzyw());
    zzcrq = paramzzf;
  }
  
  public void enableAdvertisingIdCollection(boolean paramBoolean)
  {
    zzcrr = paramBoolean;
  }
  
  protected void zza(zze paramzze)
  {
    paramzze = (zzmh)paramzze.zzb(zzmh.class);
    if (TextUtils.isEmpty(paramzze.zzwb())) {
      paramzze.setClientId(zzcrq.zzzn().zzaav());
    }
    if ((zzcrr) && (TextUtils.isEmpty(paramzze.zzxy())))
    {
      com.google.android.gms.analytics.internal.zza localzza = zzcrq.zzzm();
      paramzze.zzdy(localzza.zzyk());
      paramzze.zzao(localzza.zzxz());
    }
  }
  
  public void zzdh(String paramString)
  {
    zzab.zzhs(paramString);
    zzdi(paramString);
    zzwr().add(new zzb(zzcrq, paramString));
  }
  
  public void zzdi(String paramString)
  {
    paramString = zzb.zzdj(paramString);
    ListIterator localListIterator = zzwr().listIterator();
    while (localListIterator.hasNext()) {
      if (paramString.equals(((zzk)localListIterator.next()).zzvu())) {
        localListIterator.remove();
      }
    }
  }
  
  zzf zzvq()
  {
    return zzcrq;
  }
  
  public zze zzvr()
  {
    zze localzze = zzwq().zzwf();
    localzze.zza(zzcrq.zzze().zzaad());
    localzze.zza(zzcrq.zzzf().zzack());
    zzd(localzze);
    return localzze;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.analytics.zza
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */