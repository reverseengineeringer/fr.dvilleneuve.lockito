package com.google.android.gms.measurement;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.util.zzf;
import com.google.android.gms.measurement.internal.UserAttributeParcel;
import com.google.android.gms.measurement.internal.zzac;
import com.google.android.gms.measurement.internal.zzal;
import com.google.android.gms.measurement.internal.zzd;
import com.google.android.gms.measurement.internal.zzx;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Deprecated
public class AppMeasurement
{
  private final zzx aja;
  
  public AppMeasurement(zzx paramzzx)
  {
    zzab.zzaa(paramzzx);
    aja = paramzzx;
  }
  
  @Deprecated
  @Keep
  public static AppMeasurement getInstance(Context paramContext)
  {
    return zzx.zzdo(paramContext).zzbun();
  }
  
  private void zzc(String paramString1, String paramString2, Object paramObject)
  {
    aja.zzbsq().zzd(paramString1, paramString2, paramObject);
  }
  
  @Deprecated
  public void logEvent(@NonNull @Size(max=32L, min=1L) String paramString, Bundle paramBundle)
  {
    Bundle localBundle = paramBundle;
    if (paramBundle == null) {
      localBundle = new Bundle();
    }
    if ((aja.zzbtb().zzabc()) || (!"_iap".equals(paramString)))
    {
      int i = aja.zzbsv().zzml(paramString);
      if (i != 0)
      {
        paramString = aja.zzbsv().zza(paramString, aja.zzbtb().zzbrj(), true);
        aja.zzbsv().zze(i, "_ev", paramString);
        return;
      }
    }
    aja.zzbsq().zza("app", paramString, localBundle, true);
  }
  
  @Deprecated
  public void setMeasurementEnabled(boolean paramBoolean)
  {
    aja.zzbsq().setMeasurementEnabled(paramBoolean);
  }
  
  @Deprecated
  public void setMinimumSessionDuration(long paramLong)
  {
    aja.zzbsq().setMinimumSessionDuration(paramLong);
  }
  
  @Deprecated
  public void setSessionTimeoutDuration(long paramLong)
  {
    aja.zzbsq().setSessionTimeoutDuration(paramLong);
  }
  
  @Deprecated
  public void setUserId(String paramString)
  {
    zzb("app", "_id", paramString);
  }
  
  @Deprecated
  public void setUserProperty(@NonNull @Size(max=24L, min=1L) String paramString1, @Nullable @Size(max=36L) String paramString2)
  {
    int i = aja.zzbsv().zzmn(paramString1);
    if (i != 0)
    {
      paramString1 = aja.zzbsv().zza(paramString1, aja.zzbtb().zzbrk(), true);
      aja.zzbsv().zze(i, "_ev", paramString1);
      return;
    }
    zzb("app", paramString1, paramString2);
  }
  
  @WorkerThread
  public void zza(zzb paramzzb)
  {
    aja.zzbsq().zza(paramzzb);
  }
  
  @WorkerThread
  public void zza(zzc paramzzc)
  {
    aja.zzbsq().zza(paramzzc);
  }
  
  public void zza(String paramString1, String paramString2, Bundle paramBundle, long paramLong)
  {
    if (paramBundle == null) {
      paramBundle = new Bundle();
    }
    for (;;)
    {
      aja.zzbsq().zzd(paramString1, paramString2, paramBundle, paramLong);
      return;
    }
  }
  
  public void zzb(String paramString1, String paramString2, Object paramObject)
  {
    zzc(paramString1, paramString2, paramObject);
  }
  
  @WorkerThread
  public Map<String, Object> zzbz(boolean paramBoolean)
  {
    Object localObject = aja.zzbsq().zzcd(paramBoolean);
    HashMap localHashMap = new HashMap(((List)localObject).size());
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      UserAttributeParcel localUserAttributeParcel = (UserAttributeParcel)((Iterator)localObject).next();
      localHashMap.put(name, localUserAttributeParcel.getValue());
    }
    return localHashMap;
  }
  
  public void zzd(String paramString1, String paramString2, Bundle paramBundle)
  {
    Bundle localBundle = paramBundle;
    if (paramBundle == null) {
      localBundle = new Bundle();
    }
    aja.zzbsq().zze(paramString1, paramString2, localBundle);
  }
  
  public static final class zza
  {
    public static final Map<String, String> ajb = zzf.zzb(new String[] { "app_clear_data", "app_exception", "app_uninstall", "app_update", "firebase_campaign", "error", "first_open", "in_app_purchase", "notification_dismiss", "notification_foreground", "notification_open", "notification_receive", "os_update", "session_start", "user_engagement" }, new String[] { "_cd", "_ae", "_ui", "_au", "_cmp", "_err", "_f", "_iap", "_nd", "_nf", "_no", "_nr", "_ou", "_s", "_e" });
  }
  
  public static abstract interface zzb
  {
    @WorkerThread
    public abstract void zzb(String paramString1, String paramString2, Bundle paramBundle, long paramLong);
  }
  
  public static abstract interface zzc
  {
    @WorkerThread
    public abstract void zzc(String paramString1, String paramString2, Bundle paramBundle, long paramLong);
  }
  
  public static final class zzd
  {
    public static final Map<String, String> ajc = zzf.zzb(new String[] { "firebase_conversion", "engagement_time_msec", "firebase_error", "error_value", "firebase_event_origin", "message_device_time", "message_id", "message_name", "message_time", "previous_app_version", "previous_os_version", "topic" }, new String[] { "_c", "_et", "_err", "_ev", "_o", "_ndt", "_nmid", "_nmn", "_nmt", "_pv", "_po", "_nt" });
  }
  
  public static final class zze
  {
    public static final Map<String, String> ajd = zzf.zzb(new String[] { "firebase_last_notification", "first_open_time", "last_deep_link_referrer", "user_id" }, new String[] { "_ln", "_fot", "_ldl", "_id" });
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.measurement.AppMeasurement
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */