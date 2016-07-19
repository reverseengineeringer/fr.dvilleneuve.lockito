package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzah.zzc;
import com.google.android.gms.internal.zzah.zzd;
import com.google.android.gms.internal.zzah.zzi;
import com.google.android.gms.internal.zzai.zza;
import java.util.Map;

class zzak
{
  private static void zza(DataLayer paramDataLayer, zzah.zzd paramzzd)
  {
    paramzzd = zzuz;
    int j = paramzzd.length;
    int i = 0;
    while (i < j)
    {
      paramDataLayer.zznw(zzdl.zzg(paramzzd[i]));
      i += 1;
    }
  }
  
  public static void zza(DataLayer paramDataLayer, zzah.zzi paramzzi)
  {
    if (zzwo == null)
    {
      zzbn.zzcy("supplemental missing experimentSupplemental");
      return;
    }
    zza(paramDataLayer, zzwo);
    zzb(paramDataLayer, zzwo);
    zzc(paramDataLayer, zzwo);
  }
  
  private static void zzb(DataLayer paramDataLayer, zzah.zzd paramzzd)
  {
    paramzzd = zzuy;
    int j = paramzzd.length;
    int i = 0;
    while (i < j)
    {
      Map localMap = zzc(paramzzd[i]);
      if (localMap != null) {
        paramDataLayer.push(localMap);
      }
      i += 1;
    }
  }
  
  private static Map<String, Object> zzc(zzai.zza paramzza)
  {
    paramzza = zzdl.zzl(paramzza);
    if (!(paramzza instanceof Map))
    {
      paramzza = String.valueOf(paramzza);
      zzbn.zzcy(String.valueOf(paramzza).length() + 36 + "value: " + paramzza + " is not a map value, ignored.");
      return null;
    }
    return (Map)paramzza;
  }
  
  private static void zzc(DataLayer paramDataLayer, zzah.zzd paramzzd)
  {
    zzah.zzc[] arrayOfzzc = zzva;
    int j = arrayOfzzc.length;
    int i = 0;
    while (i < j)
    {
      zzah.zzc localzzc = arrayOfzzc[i];
      if (zzcb == null)
      {
        zzbn.zzcy("GaExperimentRandom: No key");
        i += 1;
      }
      else
      {
        Object localObject = paramDataLayer.get(zzcb);
        if (!(localObject instanceof Number))
        {
          paramzzd = null;
          label64:
          long l1 = zzuu;
          long l2 = zzuv;
          if ((!zzuw) || (paramzzd == null) || (paramzzd.longValue() < l1) || (paramzzd.longValue() > l2))
          {
            if (l1 > l2) {
              break label237;
            }
            localObject = Long.valueOf(Math.round(Math.random() * (l2 - l1) + l1));
          }
          paramDataLayer.zznw(zzcb);
          paramzzd = paramDataLayer.zzo(zzcb, localObject);
          if (zzux > 0L)
          {
            if (paramzzd.containsKey("gtm")) {
              break label245;
            }
            paramzzd.put("gtm", DataLayer.mapOf(new Object[] { "lifetime", Long.valueOf(zzux) }));
          }
        }
        for (;;)
        {
          paramDataLayer.push(paramzzd);
          break;
          paramzzd = Long.valueOf(((Number)localObject).longValue());
          break label64;
          label237:
          zzbn.zzcy("GaExperimentRandom: random range invalid");
          break;
          label245:
          localObject = paramzzd.get("gtm");
          if ((localObject instanceof Map)) {
            ((Map)localObject).put("lifetime", Long.valueOf(zzux));
          } else {
            zzbn.zzcy("GaExperimentRandom: gtm not a map");
          }
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzak
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */