package com.google.android.gms.analytics;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.analytics.internal.zza;
import com.google.android.gms.analytics.internal.zzad;
import com.google.android.gms.analytics.internal.zzaf;
import com.google.android.gms.analytics.internal.zzan;
import com.google.android.gms.analytics.internal.zzao;
import com.google.android.gms.analytics.internal.zzap;
import com.google.android.gms.analytics.internal.zzb;
import com.google.android.gms.analytics.internal.zzd;
import com.google.android.gms.analytics.internal.zzf;
import com.google.android.gms.analytics.internal.zzh;
import com.google.android.gms.analytics.internal.zzk;
import com.google.android.gms.analytics.internal.zzn;
import com.google.android.gms.analytics.internal.zzu;
import com.google.android.gms.internal.zzly;
import com.google.android.gms.internal.zzmd;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

public class Tracker
  extends zzd
{
  private final Map<String, String> zzbee = new HashMap();
  private boolean zzctw;
  private final Map<String, String> zzctx = new HashMap();
  private final zzad zzcty;
  private final zza zzctz;
  private ExceptionReporter zzcua;
  private zzan zzcub;
  
  Tracker(zzf paramzzf, String paramString, zzad paramzzad)
  {
    super(paramzzf);
    if (paramString != null) {
      zzbee.put("&tid", paramString);
    }
    zzbee.put("useSecure", "1");
    zzbee.put("&a", Integer.toString(new Random().nextInt(Integer.MAX_VALUE) + 1));
    if (paramzzad == null) {}
    for (zzcty = new zzad("tracking", zzyw());; zzcty = paramzzad)
    {
      zzctz = new zza(paramzzf);
      return;
    }
  }
  
  private static boolean zza(Map.Entry<String, String> paramEntry)
  {
    String str = (String)paramEntry.getKey();
    paramEntry = (String)paramEntry.getValue();
    return (str.startsWith("&")) && (str.length() >= 2);
  }
  
  private static String zzb(Map.Entry<String, String> paramEntry)
  {
    if (!zza(paramEntry)) {
      return null;
    }
    return ((String)paramEntry.getKey()).substring(1);
  }
  
  private static void zzb(Map<String, String> paramMap1, Map<String, String> paramMap2)
  {
    com.google.android.gms.common.internal.zzab.zzaa(paramMap2);
    if (paramMap1 == null) {}
    for (;;)
    {
      return;
      paramMap1 = paramMap1.entrySet().iterator();
      while (paramMap1.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)paramMap1.next();
        String str = zzb(localEntry);
        if (str != null) {
          paramMap2.put(str, (String)localEntry.getValue());
        }
      }
    }
  }
  
  private static void zzc(Map<String, String> paramMap1, Map<String, String> paramMap2)
  {
    com.google.android.gms.common.internal.zzab.zzaa(paramMap2);
    if (paramMap1 == null) {}
    for (;;)
    {
      return;
      paramMap1 = paramMap1.entrySet().iterator();
      while (paramMap1.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)paramMap1.next();
        String str = zzb(localEntry);
        if ((str != null) && (!paramMap2.containsKey(str))) {
          paramMap2.put(str, (String)localEntry.getValue());
        }
      }
    }
  }
  
  static String zzq(Activity paramActivity)
  {
    com.google.android.gms.common.internal.zzab.zzaa(paramActivity);
    paramActivity = paramActivity.getIntent();
    if (paramActivity == null) {}
    do
    {
      return null;
      paramActivity = paramActivity.getStringExtra("android.intent.extra.REFERRER_NAME");
    } while (TextUtils.isEmpty(paramActivity));
    return paramActivity;
  }
  
  private boolean zzww()
  {
    return zzcua != null;
  }
  
  public void enableAdvertisingIdCollection(boolean paramBoolean)
  {
    zzctw = paramBoolean;
  }
  
  public void enableAutoActivityTracking(boolean paramBoolean)
  {
    zzctz.enableAutoActivityTracking(paramBoolean);
  }
  
  public void enableExceptionReporting(boolean paramBoolean)
  {
    for (;;)
    {
      try
      {
        if (zzww() == paramBoolean) {
          return;
        }
        if (paramBoolean)
        {
          Context localContext = getContext();
          zzcua = new ExceptionReporter(this, Thread.getDefaultUncaughtExceptionHandler(), localContext);
          Thread.setDefaultUncaughtExceptionHandler(zzcua);
          zzei("Uncaught exceptions will be reported to Google Analytics");
          return;
        }
      }
      finally {}
      Thread.setDefaultUncaughtExceptionHandler(zzcua.zzvy());
      zzei("Uncaught exceptions will not be reported to Google Analytics");
    }
  }
  
  public String get(String paramString)
  {
    zzzg();
    if (TextUtils.isEmpty(paramString)) {}
    do
    {
      return null;
      if (zzbee.containsKey(paramString)) {
        return (String)zzbee.get(paramString);
      }
      if (paramString.equals("&ul")) {
        return zzao.zza(Locale.getDefault());
      }
      if (paramString.equals("&cid")) {
        return zzzc().zzaav();
      }
      if (paramString.equals("&sr")) {
        return zzzf().zzacl();
      }
      if (paramString.equals("&aid")) {
        return zzze().zzaad().zzsi();
      }
      if (paramString.equals("&an")) {
        return zzze().zzaad().zzxb();
      }
      if (paramString.equals("&av")) {
        return zzze().zzaad().zzxc();
      }
    } while (!paramString.equals("&aiid"));
    return zzze().zzaad().zzxd();
  }
  
  public void send(final Map<String, String> paramMap)
  {
    final long l = zzyw().currentTimeMillis();
    if (zzvx().getAppOptOut())
    {
      zzej("AppOptOut is set to true. Not sending Google Analytics hit");
      return;
    }
    boolean bool1 = zzvx().isDryRunEnabled();
    final HashMap localHashMap = new HashMap();
    zzb(zzbee, localHashMap);
    zzb(paramMap, localHashMap);
    final boolean bool2 = zzao.zzi((String)zzbee.get("useSecure"), true);
    zzc(zzctx, localHashMap);
    zzctx.clear();
    paramMap = (String)localHashMap.get("t");
    if (TextUtils.isEmpty(paramMap))
    {
      zzyx().zzh(localHashMap, "Missing hit type parameter");
      return;
    }
    final String str = (String)localHashMap.get("tid");
    if (TextUtils.isEmpty(str))
    {
      zzyx().zzh(localHashMap, "Missing tracking id parameter");
      return;
    }
    final boolean bool3 = zzwx();
    try
    {
      if (("screenview".equalsIgnoreCase(paramMap)) || ("pageview".equalsIgnoreCase(paramMap)) || ("appview".equalsIgnoreCase(paramMap)) || (TextUtils.isEmpty(paramMap)))
      {
        int j = Integer.parseInt((String)zzbee.get("&a")) + 1;
        int i = j;
        if (j >= Integer.MAX_VALUE) {
          i = 1;
        }
        zzbee.put("&a", Integer.toString(i));
      }
      zzyz().zzf(new Runnable()
      {
        public void run()
        {
          boolean bool = true;
          if (Tracker.zza(Tracker.this).zzwy()) {
            localHashMap.put("sc", "start");
          }
          zzao.zzd(localHashMap, "cid", zzvx().zzwb());
          Object localObject = (String)localHashMap.get("sf");
          if (localObject != null)
          {
            double d = zzao.zza((String)localObject, 100.0D);
            if (zzao.zza(d, (String)localHashMap.get("cid")))
            {
              zzb("Sampling enabled. Hit sampled out. sample rate", Double.valueOf(d));
              return;
            }
          }
          localObject = Tracker.zzb(Tracker.this);
          if (bool3)
          {
            zzao.zzb(localHashMap, "ate", ((zza)localObject).zzxz());
            zzao.zzc(localHashMap, "adid", ((zza)localObject).zzyk());
            localObject = Tracker.zzc(Tracker.this).zzaad();
            zzao.zzc(localHashMap, "an", ((zzly)localObject).zzxb());
            zzao.zzc(localHashMap, "av", ((zzly)localObject).zzxc());
            zzao.zzc(localHashMap, "aid", ((zzly)localObject).zzsi());
            zzao.zzc(localHashMap, "aiid", ((zzly)localObject).zzxd());
            localHashMap.put("v", "1");
            localHashMap.put("_v", com.google.android.gms.analytics.internal.zze.zzcwu);
            zzao.zzc(localHashMap, "ul", Tracker.zzd(Tracker.this).zzack().getLanguage());
            zzao.zzc(localHashMap, "sr", Tracker.zze(Tracker.this).zzacl());
            if ((!paramMap.equals("transaction")) && (!paramMap.equals("item"))) {
              break label383;
            }
          }
          label383:
          for (int i = 1;; i = 0)
          {
            if ((i != 0) || (Tracker.zzf(Tracker.this).zzade())) {
              break label388;
            }
            Tracker.zzg(Tracker.this).zzh(localHashMap, "Too many hits sent too quickly, rate limiting invoked");
            return;
            localHashMap.remove("ate");
            localHashMap.remove("adid");
            break;
          }
          label388:
          long l2 = zzao.zzez((String)localHashMap.get("ht"));
          long l1 = l2;
          if (l2 == 0L) {
            l1 = l;
          }
          if (bool2)
          {
            localObject = new com.google.android.gms.analytics.internal.zzab(Tracker.this, localHashMap, l1, str);
            Tracker.zzh(Tracker.this).zzc("Dry run enabled. Would have sent hit", localObject);
            return;
          }
          localObject = (String)localHashMap.get("cid");
          HashMap localHashMap = new HashMap();
          zzao.zza(localHashMap, "uid", localHashMap);
          zzao.zza(localHashMap, "an", localHashMap);
          zzao.zza(localHashMap, "aid", localHashMap);
          zzao.zza(localHashMap, "av", localHashMap);
          zzao.zza(localHashMap, "aiid", localHashMap);
          String str = zzcui;
          if (!TextUtils.isEmpty((CharSequence)localHashMap.get("adid"))) {}
          for (;;)
          {
            localObject = new zzh(0L, (String)localObject, str, bool, 0L, localHashMap);
            l2 = Tracker.zzi(Tracker.this).zza((zzh)localObject);
            localHashMap.put("_s", String.valueOf(l2));
            localObject = new com.google.android.gms.analytics.internal.zzab(Tracker.this, localHashMap, l1, str);
            Tracker.zzj(Tracker.this).zza((com.google.android.gms.analytics.internal.zzab)localObject);
            return;
            bool = false;
          }
        }
      });
      return;
    }
    finally {}
  }
  
  public void set(String paramString1, String paramString2)
  {
    com.google.android.gms.common.internal.zzab.zzb(paramString1, "Key should be non-null");
    if (TextUtils.isEmpty(paramString1)) {
      return;
    }
    zzbee.put(paramString1, paramString2);
  }
  
  public void setAnonymizeIp(boolean paramBoolean)
  {
    set("&aip", zzao.zzat(paramBoolean));
  }
  
  public void setAppId(String paramString)
  {
    set("&aid", paramString);
  }
  
  public void setAppInstallerId(String paramString)
  {
    set("&aiid", paramString);
  }
  
  public void setAppName(String paramString)
  {
    set("&an", paramString);
  }
  
  public void setAppVersion(String paramString)
  {
    set("&av", paramString);
  }
  
  public void setCampaignParamsOnNextHit(Uri paramUri)
  {
    if ((paramUri == null) || (paramUri.isOpaque())) {}
    do
    {
      return;
      paramUri = paramUri.getQueryParameter("referrer");
    } while (TextUtils.isEmpty(paramUri));
    paramUri = String.valueOf(paramUri);
    if (paramUri.length() != 0) {}
    for (paramUri = "http://hostname/?".concat(paramUri);; paramUri = new String("http://hostname/?"))
    {
      paramUri = Uri.parse(paramUri);
      String str = paramUri.getQueryParameter("utm_id");
      if (str != null) {
        zzctx.put("&ci", str);
      }
      str = paramUri.getQueryParameter("anid");
      if (str != null) {
        zzctx.put("&anid", str);
      }
      str = paramUri.getQueryParameter("utm_campaign");
      if (str != null) {
        zzctx.put("&cn", str);
      }
      str = paramUri.getQueryParameter("utm_content");
      if (str != null) {
        zzctx.put("&cc", str);
      }
      str = paramUri.getQueryParameter("utm_medium");
      if (str != null) {
        zzctx.put("&cm", str);
      }
      str = paramUri.getQueryParameter("utm_source");
      if (str != null) {
        zzctx.put("&cs", str);
      }
      str = paramUri.getQueryParameter("utm_term");
      if (str != null) {
        zzctx.put("&ck", str);
      }
      str = paramUri.getQueryParameter("dclid");
      if (str != null) {
        zzctx.put("&dclid", str);
      }
      str = paramUri.getQueryParameter("gclid");
      if (str != null) {
        zzctx.put("&gclid", str);
      }
      paramUri = paramUri.getQueryParameter("aclid");
      if (paramUri == null) {
        break;
      }
      zzctx.put("&aclid", paramUri);
      return;
    }
  }
  
  public void setClientId(String paramString)
  {
    set("&cid", paramString);
  }
  
  public void setEncoding(String paramString)
  {
    set("&de", paramString);
  }
  
  public void setHostname(String paramString)
  {
    set("&dh", paramString);
  }
  
  public void setLanguage(String paramString)
  {
    set("&ul", paramString);
  }
  
  public void setLocation(String paramString)
  {
    set("&dl", paramString);
  }
  
  public void setPage(String paramString)
  {
    set("&dp", paramString);
  }
  
  public void setReferrer(String paramString)
  {
    set("&dr", paramString);
  }
  
  public void setSampleRate(double paramDouble)
  {
    set("&sf", Double.toString(paramDouble));
  }
  
  public void setScreenColors(String paramString)
  {
    set("&sd", paramString);
  }
  
  public void setScreenName(String paramString)
  {
    set("&cd", paramString);
  }
  
  public void setScreenResolution(int paramInt1, int paramInt2)
  {
    if ((paramInt1 < 0) && (paramInt2 < 0))
    {
      zzel("Invalid width or height. The values should be non-negative.");
      return;
    }
    set("&sr", 23 + paramInt1 + "x" + paramInt2);
  }
  
  public void setSessionTimeout(long paramLong)
  {
    zzctz.setSessionTimeout(1000L * paramLong);
  }
  
  public void setTitle(String paramString)
  {
    set("&dt", paramString);
  }
  
  public void setUseSecure(boolean paramBoolean)
  {
    set("useSecure", zzao.zzat(paramBoolean));
  }
  
  public void setViewportSize(String paramString)
  {
    set("&vp", paramString);
  }
  
  void zza(zzan paramzzan)
  {
    zzei("Loading Tracker config values");
    zzcub = paramzzan;
    if (zzcub.zzaeb())
    {
      paramzzan = zzcub.getTrackingId();
      set("&tid", paramzzan);
      zza("trackingId loaded", paramzzan);
    }
    if (zzcub.zzaec())
    {
      paramzzan = Double.toString(zzcub.zzaed());
      set("&sf", paramzzan);
      zza("Sample frequency loaded", paramzzan);
    }
    if (zzcub.zzaee())
    {
      int i = zzcub.getSessionTimeout();
      setSessionTimeout(i);
      zza("Session timeout loaded", Integer.valueOf(i));
    }
    boolean bool;
    if (zzcub.zzaef())
    {
      bool = zzcub.zzaeg();
      enableAutoActivityTracking(bool);
      zza("Auto activity tracking loaded", Boolean.valueOf(bool));
    }
    if (zzcub.zzaeh())
    {
      bool = zzcub.zzaei();
      if (bool) {
        set("&aip", "1");
      }
      zza("Anonymize ip loaded", Boolean.valueOf(bool));
    }
    enableExceptionReporting(zzcub.zzaej());
  }
  
  protected void zzwv()
  {
    zzctz.initialize();
    String str = zzwe().zzxb();
    if (str != null) {
      set("&an", str);
    }
    str = zzwe().zzxc();
    if (str != null) {
      set("&av", str);
    }
  }
  
  boolean zzwx()
  {
    return zzctw;
  }
  
  private class zza
    extends zzd
    implements GoogleAnalytics.zza
  {
    private boolean zzcuk;
    private int zzcul;
    private long zzcum = -1L;
    private boolean zzcun;
    private long zzcuo;
    
    protected zza(zzf paramzzf)
    {
      super();
    }
    
    private void zzwz()
    {
      if ((zzcum >= 0L) || (zzcuk))
      {
        zzvx().zza(Tracker.zza(Tracker.this));
        return;
      }
      zzvx().zzb(Tracker.zza(Tracker.this));
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
          setCampaignParamsOnNextHit(((Intent)localObject).getData());
        }
        localHashMap = new HashMap();
        localHashMap.put("&t", "screenview");
        localTracker = Tracker.this;
        if (Tracker.zzk(Tracker.this) == null) {
          break label159;
        }
      }
      label159:
      for (Object localObject = Tracker.zzk(Tracker.this).zzr(paramActivity);; localObject = paramActivity.getClass().getCanonicalName())
      {
        localTracker.set("&cd", (String)localObject);
        if (TextUtils.isEmpty((CharSequence)localHashMap.get("&dr")))
        {
          paramActivity = Tracker.zzq(paramActivity);
          if (!TextUtils.isEmpty(paramActivity)) {
            localHashMap.put("&dr", paramActivity);
          }
        }
        send(localHashMap);
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
}

/* Location:
 * Qualified Name:     com.google.android.gms.analytics.Tracker
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */