package com.google.android.gms.analytics;

import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.google.android.gms.analytics.internal.zzaf;
import com.google.android.gms.analytics.internal.zzao;
import com.google.android.gms.analytics.internal.zzf;
import com.google.android.gms.analytics.internal.zzh;
import com.google.android.gms.internal.zzly;
import com.google.android.gms.internal.zzlz;
import com.google.android.gms.internal.zzma;
import com.google.android.gms.internal.zzmb;
import com.google.android.gms.internal.zzmc;
import com.google.android.gms.internal.zzmd;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzmf;
import com.google.android.gms.internal.zzmg;
import com.google.android.gms.internal.zzmh;
import com.google.android.gms.internal.zzmi;
import com.google.android.gms.internal.zzmj;
import com.google.android.gms.internal.zzmk;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class zzb
  extends com.google.android.gms.analytics.internal.zzc
  implements zzk
{
  private static DecimalFormat zzcru;
  private final zzf zzcrq;
  private final String zzcrv;
  private final Uri zzcrw;
  private final boolean zzcrx;
  private final boolean zzcry;
  
  public zzb(zzf paramzzf, String paramString)
  {
    this(paramzzf, paramString, true, false);
  }
  
  public zzb(zzf paramzzf, String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    super(paramzzf);
    com.google.android.gms.common.internal.zzab.zzhs(paramString);
    zzcrq = paramzzf;
    zzcrv = paramString;
    zzcrx = paramBoolean1;
    zzcry = paramBoolean2;
    zzcrw = zzdj(zzcrv);
  }
  
  private static void zza(Map<String, String> paramMap, String paramString, double paramDouble)
  {
    if (paramDouble != 0.0D) {
      paramMap.put(paramString, zzb(paramDouble));
    }
  }
  
  private static void zza(Map<String, String> paramMap, String paramString, int paramInt1, int paramInt2)
  {
    if ((paramInt1 > 0) && (paramInt2 > 0)) {
      paramMap.put(paramString, 23 + paramInt1 + "x" + paramInt2);
    }
  }
  
  private static void zza(Map<String, String> paramMap, String paramString, boolean paramBoolean)
  {
    if (paramBoolean) {
      paramMap.put(paramString, "1");
    }
  }
  
  private static String zzan(Map<String, String> paramMap)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    paramMap = paramMap.entrySet().iterator();
    while (paramMap.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramMap.next();
      if (localStringBuilder.length() != 0) {
        localStringBuilder.append(", ");
      }
      localStringBuilder.append((String)localEntry.getKey());
      localStringBuilder.append("=");
      localStringBuilder.append((String)localEntry.getValue());
    }
    return localStringBuilder.toString();
  }
  
  static String zzb(double paramDouble)
  {
    if (zzcru == null) {
      zzcru = new DecimalFormat("0.######");
    }
    return zzcru.format(paramDouble);
  }
  
  private static void zzb(Map<String, String> paramMap, String paramString1, String paramString2)
  {
    if (!TextUtils.isEmpty(paramString2)) {
      paramMap.put(paramString1, paramString2);
    }
  }
  
  public static Map<String, String> zzc(zze paramzze)
  {
    HashMap localHashMap = new HashMap();
    Object localObject1 = (zzmc)paramzze.zza(zzmc.class);
    Object localObject2;
    Object localObject3;
    if (localObject1 != null)
    {
      localObject1 = ((zzmc)localObject1).zzxm().entrySet().iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (Map.Entry)((Iterator)localObject1).next();
        localObject3 = zzj(((Map.Entry)localObject2).getValue());
        if (localObject3 != null) {
          localHashMap.put((String)((Map.Entry)localObject2).getKey(), localObject3);
        }
      }
    }
    localObject1 = (zzmh)paramzze.zza(zzmh.class);
    if (localObject1 != null)
    {
      zzb(localHashMap, "t", ((zzmh)localObject1).zzxx());
      zzb(localHashMap, "cid", ((zzmh)localObject1).zzwb());
      zzb(localHashMap, "uid", ((zzmh)localObject1).getUserId());
      zzb(localHashMap, "sc", ((zzmh)localObject1).zzya());
      zza(localHashMap, "sf", ((zzmh)localObject1).zzyc());
      zza(localHashMap, "ni", ((zzmh)localObject1).zzyb());
      zzb(localHashMap, "adid", ((zzmh)localObject1).zzxy());
      zza(localHashMap, "ate", ((zzmh)localObject1).zzxz());
    }
    localObject1 = (zzmi)paramzze.zza(zzmi.class);
    if (localObject1 != null)
    {
      zzb(localHashMap, "cd", ((zzmi)localObject1).zzye());
      zza(localHashMap, "a", ((zzmi)localObject1).zzyf());
      zzb(localHashMap, "dr", ((zzmi)localObject1).zzyg());
    }
    localObject1 = (zzmf)paramzze.zza(zzmf.class);
    if (localObject1 != null)
    {
      zzb(localHashMap, "ec", ((zzmf)localObject1).getCategory());
      zzb(localHashMap, "ea", ((zzmf)localObject1).getAction());
      zzb(localHashMap, "el", ((zzmf)localObject1).getLabel());
      zza(localHashMap, "ev", ((zzmf)localObject1).getValue());
    }
    localObject1 = (zzlz)paramzze.zza(zzlz.class);
    if (localObject1 != null)
    {
      zzb(localHashMap, "cn", ((zzlz)localObject1).getName());
      zzb(localHashMap, "cs", ((zzlz)localObject1).getSource());
      zzb(localHashMap, "cm", ((zzlz)localObject1).zzxe());
      zzb(localHashMap, "ck", ((zzlz)localObject1).zzxf());
      zzb(localHashMap, "cc", ((zzlz)localObject1).getContent());
      zzb(localHashMap, "ci", ((zzlz)localObject1).getId());
      zzb(localHashMap, "anid", ((zzlz)localObject1).zzxg());
      zzb(localHashMap, "gclid", ((zzlz)localObject1).zzxh());
      zzb(localHashMap, "dclid", ((zzlz)localObject1).zzxi());
      zzb(localHashMap, "aclid", ((zzlz)localObject1).zzxj());
    }
    localObject1 = (zzmg)paramzze.zza(zzmg.class);
    if (localObject1 != null)
    {
      zzb(localHashMap, "exd", ((zzmg)localObject1).getDescription());
      zza(localHashMap, "exf", ((zzmg)localObject1).zzxw());
    }
    localObject1 = (zzmj)paramzze.zza(zzmj.class);
    if (localObject1 != null)
    {
      zzb(localHashMap, "sn", ((zzmj)localObject1).zzyi());
      zzb(localHashMap, "sa", ((zzmj)localObject1).getAction());
      zzb(localHashMap, "st", ((zzmj)localObject1).getTarget());
    }
    localObject1 = (zzmk)paramzze.zza(zzmk.class);
    if (localObject1 != null)
    {
      zzb(localHashMap, "utv", ((zzmk)localObject1).zzyj());
      zza(localHashMap, "utt", ((zzmk)localObject1).getTimeInMillis());
      zzb(localHashMap, "utc", ((zzmk)localObject1).getCategory());
      zzb(localHashMap, "utl", ((zzmk)localObject1).getLabel());
    }
    localObject1 = (zzma)paramzze.zza(zzma.class);
    if (localObject1 != null)
    {
      localObject1 = ((zzma)localObject1).zzxk().entrySet().iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (Map.Entry)((Iterator)localObject1).next();
        localObject3 = zzc.zzbd(((Integer)((Map.Entry)localObject2).getKey()).intValue());
        if (!TextUtils.isEmpty((CharSequence)localObject3)) {
          localHashMap.put(localObject3, (String)((Map.Entry)localObject2).getValue());
        }
      }
    }
    localObject1 = (zzmb)paramzze.zza(zzmb.class);
    if (localObject1 != null)
    {
      localObject1 = ((zzmb)localObject1).zzxl().entrySet().iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (Map.Entry)((Iterator)localObject1).next();
        localObject3 = zzc.zzbf(((Integer)((Map.Entry)localObject2).getKey()).intValue());
        if (!TextUtils.isEmpty((CharSequence)localObject3)) {
          localHashMap.put(localObject3, zzb(((Double)((Map.Entry)localObject2).getValue()).doubleValue()));
        }
      }
    }
    localObject1 = (zzme)paramzze.zza(zzme.class);
    if (localObject1 != null)
    {
      localObject2 = ((zzme)localObject1).zzxs();
      if (localObject2 != null)
      {
        localObject2 = ((ProductAction)localObject2).build().entrySet().iterator();
        while (((Iterator)localObject2).hasNext())
        {
          localObject3 = (Map.Entry)((Iterator)localObject2).next();
          if (((String)((Map.Entry)localObject3).getKey()).startsWith("&")) {
            localHashMap.put(((String)((Map.Entry)localObject3).getKey()).substring(1), (String)((Map.Entry)localObject3).getValue());
          } else {
            localHashMap.put((String)((Map.Entry)localObject3).getKey(), (String)((Map.Entry)localObject3).getValue());
          }
        }
      }
      localObject2 = ((zzme)localObject1).zzxv().iterator();
      int i = 1;
      while (((Iterator)localObject2).hasNext())
      {
        localHashMap.putAll(((Promotion)((Iterator)localObject2).next()).zzef(zzc.zzbj(i)));
        i += 1;
      }
      localObject2 = ((zzme)localObject1).zzxt().iterator();
      i = 1;
      while (((Iterator)localObject2).hasNext())
      {
        localHashMap.putAll(((Product)((Iterator)localObject2).next()).zzef(zzc.zzbh(i)));
        i += 1;
      }
      localObject2 = ((zzme)localObject1).zzxu().entrySet().iterator();
      i = 1;
      if (((Iterator)localObject2).hasNext())
      {
        localObject3 = (Map.Entry)((Iterator)localObject2).next();
        localObject1 = (List)((Map.Entry)localObject3).getValue();
        String str1 = zzc.zzbm(i);
        Iterator localIterator = ((List)localObject1).iterator();
        int j = 1;
        if (localIterator.hasNext())
        {
          Product localProduct = (Product)localIterator.next();
          localObject1 = String.valueOf(str1);
          String str2 = String.valueOf(zzc.zzbk(j));
          if (str2.length() != 0) {}
          for (localObject1 = ((String)localObject1).concat(str2);; localObject1 = new String((String)localObject1))
          {
            localHashMap.putAll(localProduct.zzef((String)localObject1));
            j += 1;
            break;
          }
        }
        if (!TextUtils.isEmpty((CharSequence)((Map.Entry)localObject3).getKey()))
        {
          localObject1 = String.valueOf(str1);
          str1 = String.valueOf("nm");
          if (str1.length() == 0) {
            break label1280;
          }
        }
        label1280:
        for (localObject1 = ((String)localObject1).concat(str1);; localObject1 = new String((String)localObject1))
        {
          localHashMap.put(localObject1, (String)((Map.Entry)localObject3).getKey());
          i += 1;
          break;
        }
      }
    }
    localObject1 = (zzmd)paramzze.zza(zzmd.class);
    if (localObject1 != null)
    {
      zzb(localHashMap, "ul", ((zzmd)localObject1).getLanguage());
      zza(localHashMap, "sd", ((zzmd)localObject1).zzxn());
      zza(localHashMap, "sr", ((zzmd)localObject1).zzxo(), ((zzmd)localObject1).zzxp());
      zza(localHashMap, "vp", ((zzmd)localObject1).zzxq(), ((zzmd)localObject1).zzxr());
    }
    paramzze = (zzly)paramzze.zza(zzly.class);
    if (paramzze != null)
    {
      zzb(localHashMap, "an", paramzze.zzxb());
      zzb(localHashMap, "aid", paramzze.zzsi());
      zzb(localHashMap, "aiid", paramzze.zzxd());
      zzb(localHashMap, "av", paramzze.zzxc());
    }
    return localHashMap;
  }
  
  static Uri zzdj(String paramString)
  {
    com.google.android.gms.common.internal.zzab.zzhs(paramString);
    Uri.Builder localBuilder = new Uri.Builder();
    localBuilder.scheme("uri");
    localBuilder.authority("google-analytics.com");
    localBuilder.path(paramString);
    return localBuilder.build();
  }
  
  private static String zzj(Object paramObject)
  {
    if (paramObject == null) {
      paramObject = null;
    }
    String str;
    do
    {
      return (String)paramObject;
      if (!(paramObject instanceof String)) {
        break;
      }
      str = (String)paramObject;
      paramObject = str;
    } while (!TextUtils.isEmpty(str));
    return null;
    if ((paramObject instanceof Double))
    {
      paramObject = (Double)paramObject;
      if (((Double)paramObject).doubleValue() != 0.0D) {
        return zzb(((Double)paramObject).doubleValue());
      }
      return null;
    }
    if ((paramObject instanceof Boolean))
    {
      if (paramObject != Boolean.FALSE) {
        return "1";
      }
      return null;
    }
    return String.valueOf(paramObject);
  }
  
  public void zzb(zze paramzze)
  {
    com.google.android.gms.common.internal.zzab.zzaa(paramzze);
    com.google.android.gms.common.internal.zzab.zzb(paramzze.zzwk(), "Can't deliver not submitted measurement");
    com.google.android.gms.common.internal.zzab.zzhk("deliver should be called on worker thread");
    Object localObject2 = paramzze.zzwf();
    Object localObject1 = (zzmh)((zze)localObject2).zzb(zzmh.class);
    if (TextUtils.isEmpty(((zzmh)localObject1).zzxx())) {
      zzyx().zzh(zzc((zze)localObject2), "Ignoring measurement without type");
    }
    do
    {
      return;
      if (TextUtils.isEmpty(((zzmh)localObject1).zzwb()))
      {
        zzyx().zzh(zzc((zze)localObject2), "Ignoring measurement without client id");
        return;
      }
    } while (zzcrq.zzzk().getAppOptOut());
    double d = ((zzmh)localObject1).zzyc();
    if (zzao.zza(d, ((zzmh)localObject1).zzwb()))
    {
      zzb("Sampling enabled. Hit sampled out. sampling rate", Double.valueOf(d));
      return;
    }
    localObject2 = zzc((zze)localObject2);
    ((Map)localObject2).put("v", "1");
    ((Map)localObject2).put("_v", com.google.android.gms.analytics.internal.zze.zzcwu);
    ((Map)localObject2).put("tid", zzcrv);
    if (zzcrq.zzzk().isDryRunEnabled())
    {
      zzc("Dry run is enabled. GoogleAnalytics would have sent", zzan((Map)localObject2));
      return;
    }
    HashMap localHashMap = new HashMap();
    zzao.zzc(localHashMap, "uid", ((zzmh)localObject1).getUserId());
    Object localObject3 = (zzly)paramzze.zza(zzly.class);
    if (localObject3 != null)
    {
      zzao.zzc(localHashMap, "an", ((zzly)localObject3).zzxb());
      zzao.zzc(localHashMap, "aid", ((zzly)localObject3).zzsi());
      zzao.zzc(localHashMap, "av", ((zzly)localObject3).zzxc());
      zzao.zzc(localHashMap, "aiid", ((zzly)localObject3).zzxd());
    }
    localObject3 = ((zzmh)localObject1).zzwb();
    String str = zzcrv;
    if (!TextUtils.isEmpty(((zzmh)localObject1).zzxy())) {}
    for (boolean bool = true;; bool = false)
    {
      localObject1 = new zzh(0L, (String)localObject3, str, bool, 0L, localHashMap);
      ((Map)localObject2).put("_s", String.valueOf(zzwd().zza((zzh)localObject1)));
      paramzze = new com.google.android.gms.analytics.internal.zzab(zzyx(), (Map)localObject2, paramzze.zzwi(), true);
      zzwd().zza(paramzze);
      return;
    }
  }
  
  public Uri zzvu()
  {
    return zzcrw;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.analytics.zzb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */