package com.google.android.gms.analytics;

import android.text.TextUtils;
import com.google.android.gms.analytics.internal.zza;
import com.google.android.gms.analytics.internal.zzab;
import com.google.android.gms.analytics.internal.zzad;
import com.google.android.gms.analytics.internal.zzaf;
import com.google.android.gms.analytics.internal.zzao;
import com.google.android.gms.analytics.internal.zzb;
import com.google.android.gms.analytics.internal.zze;
import com.google.android.gms.analytics.internal.zzh;
import com.google.android.gms.analytics.internal.zzk;
import com.google.android.gms.analytics.internal.zzu;
import com.google.android.gms.internal.zzly;
import com.google.android.gms.internal.zzmd;
import java.util.HashMap;
import java.util.Map;

class Tracker$1
  implements Runnable
{
  Tracker$1(Tracker paramTracker, Map paramMap, boolean paramBoolean1, String paramString1, long paramLong, boolean paramBoolean2, boolean paramBoolean3, String paramString2) {}
  
  public void run()
  {
    boolean bool = true;
    if (Tracker.zza(zzcuj).zzwy()) {
      zzcuc.put("sc", "start");
    }
    zzao.zzd(zzcuc, "cid", zzcuj.zzvx().zzwb());
    Object localObject = (String)zzcuc.get("sf");
    if (localObject != null)
    {
      double d = zzao.zza((String)localObject, 100.0D);
      if (zzao.zza(d, (String)zzcuc.get("cid")))
      {
        zzcuj.zzb("Sampling enabled. Hit sampled out. sample rate", Double.valueOf(d));
        return;
      }
    }
    localObject = Tracker.zzb(zzcuj);
    if (zzcud)
    {
      zzao.zzb(zzcuc, "ate", ((zza)localObject).zzxz());
      zzao.zzc(zzcuc, "adid", ((zza)localObject).zzyk());
      localObject = Tracker.zzc(zzcuj).zzaad();
      zzao.zzc(zzcuc, "an", ((zzly)localObject).zzxb());
      zzao.zzc(zzcuc, "av", ((zzly)localObject).zzxc());
      zzao.zzc(zzcuc, "aid", ((zzly)localObject).zzsi());
      zzao.zzc(zzcuc, "aiid", ((zzly)localObject).zzxd());
      zzcuc.put("v", "1");
      zzcuc.put("_v", zze.zzcwu);
      zzao.zzc(zzcuc, "ul", Tracker.zzd(zzcuj).zzack().getLanguage());
      zzao.zzc(zzcuc, "sr", Tracker.zze(zzcuj).zzacl());
      if ((!zzcue.equals("transaction")) && (!zzcue.equals("item"))) {
        break label383;
      }
    }
    label383:
    for (int i = 1;; i = 0)
    {
      if ((i != 0) || (Tracker.zzf(zzcuj).zzade())) {
        break label388;
      }
      Tracker.zzg(zzcuj).zzh(zzcuc, "Too many hits sent too quickly, rate limiting invoked");
      return;
      zzcuc.remove("ate");
      zzcuc.remove("adid");
      break;
    }
    label388:
    long l2 = zzao.zzez((String)zzcuc.get("ht"));
    long l1 = l2;
    if (l2 == 0L) {
      l1 = zzcuf;
    }
    if (zzcug)
    {
      localObject = new zzab(zzcuj, zzcuc, l1, zzcuh);
      Tracker.zzh(zzcuj).zzc("Dry run enabled. Would have sent hit", localObject);
      return;
    }
    localObject = (String)zzcuc.get("cid");
    HashMap localHashMap = new HashMap();
    zzao.zza(localHashMap, "uid", zzcuc);
    zzao.zza(localHashMap, "an", zzcuc);
    zzao.zza(localHashMap, "aid", zzcuc);
    zzao.zza(localHashMap, "av", zzcuc);
    zzao.zza(localHashMap, "aiid", zzcuc);
    String str = zzcui;
    if (!TextUtils.isEmpty((CharSequence)zzcuc.get("adid"))) {}
    for (;;)
    {
      localObject = new zzh(0L, (String)localObject, str, bool, 0L, localHashMap);
      l2 = Tracker.zzi(zzcuj).zza((zzh)localObject);
      zzcuc.put("_s", String.valueOf(l2));
      localObject = new zzab(zzcuj, zzcuc, l1, zzcuh);
      Tracker.zzj(zzcuj).zza((zzab)localObject);
      return;
      bool = false;
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.analytics.Tracker.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */