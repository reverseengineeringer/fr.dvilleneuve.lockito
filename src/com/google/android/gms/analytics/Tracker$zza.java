package com.google.android.gms.analytics;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import com.google.android.gms.analytics.internal.zzan;
import com.google.android.gms.analytics.internal.zzd;
import com.google.android.gms.analytics.internal.zzf;
import com.google.android.gms.common.util.zze;
import java.util.HashMap;
import java.util.Map;

class Tracker$zza
  extends zzd
  implements GoogleAnalytics.zza
{
  private boolean zzcuk;
  private int zzcul;
  private long zzcum = -1L;
  private boolean zzcun;
  private long zzcuo;
  
  protected Tracker$zza(Tracker paramTracker, zzf paramzzf)
  {
    super(paramzzf);
  }
  
  private void zzwz()
  {
    if ((zzcum >= 0L) || (zzcuk))
    {
      zzvx().zza(Tracker.zza(zzcuj));
      return;
    }
    zzvx().zzb(Tracker.zza(zzcuj));
  }
  
  public void enableAutoActivityTracking(boolean paramBoolean)
  {
    zzcuk = paramBoolean;
    zzwz();
  }
  
  public void setSessionTimeout(long paramLong)
  {
    zzcum = paramLong;
    zzwz();
  }
  
  public void zzo(Activity paramActivity)
  {
    if ((zzcul == 0) && (zzxa())) {
      zzcun = true;
    }
    zzcul += 1;
    HashMap localHashMap;
    Tracker localTracker;
    if (zzcuk)
    {
      localObject = paramActivity.getIntent();
      if (localObject != null) {
        zzcuj.setCampaignParamsOnNextHit(((Intent)localObject).getData());
      }
      localHashMap = new HashMap();
      localHashMap.put("&t", "screenview");
      localTracker = zzcuj;
      if (Tracker.zzk(zzcuj) == null) {
        break label159;
      }
    }
    label159:
    for (Object localObject = Tracker.zzk(zzcuj).zzr(paramActivity);; localObject = paramActivity.getClass().getCanonicalName())
    {
      localTracker.set("&cd", (String)localObject);
      if (TextUtils.isEmpty((CharSequence)localHashMap.get("&dr")))
      {
        paramActivity = Tracker.zzq(paramActivity);
        if (!TextUtils.isEmpty(paramActivity)) {
          localHashMap.put("&dr", paramActivity);
        }
      }
      zzcuj.send(localHashMap);
      return;
    }
  }
  
  public void zzp(Activity paramActivity)
  {
    zzcul -= 1;
    zzcul = Math.max(0, zzcul);
    if (zzcul == 0) {
      zzcuo = zzyw().elapsedRealtime();
    }
  }
  
  protected void zzwv() {}
  
  public boolean zzwy()
  {
    try
    {
      boolean bool = zzcun;
      zzcun = false;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  boolean zzxa()
  {
    return zzyw().elapsedRealtime() >= zzcuo + Math.max(1000L, zzcum);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.analytics.Tracker.zza
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */