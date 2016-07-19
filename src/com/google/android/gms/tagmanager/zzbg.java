package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzadz.zza;
import com.google.android.gms.internal.zzadz.zzb;
import com.google.android.gms.internal.zzadz.zzc;
import com.google.android.gms.internal.zzadz.zzd;
import com.google.android.gms.internal.zzag;
import com.google.android.gms.internal.zzai.zza;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class zzbg
{
  private static zzai.zza zzak(Object paramObject)
    throws JSONException
  {
    return zzdl.zzar(zzal(paramObject));
  }
  
  static Object zzal(Object paramObject)
    throws JSONException
  {
    if ((paramObject instanceof JSONArray)) {
      throw new RuntimeException("JSONArrays are not supported");
    }
    if (JSONObject.NULL.equals(paramObject)) {
      throw new RuntimeException("JSON nulls are not supported");
    }
    Object localObject = paramObject;
    if ((paramObject instanceof JSONObject))
    {
      paramObject = (JSONObject)paramObject;
      localObject = new HashMap();
      Iterator localIterator = ((JSONObject)paramObject).keys();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        ((Map)localObject).put(str, zzal(((JSONObject)paramObject).get(str)));
      }
    }
    return localObject;
  }
  
  public static zzadz.zzc zzoh(String paramString)
    throws JSONException
  {
    paramString = zzak(new JSONObject(paramString));
    zzadz.zzd localzzd = zzadz.zzc.zzcgw();
    int i = 0;
    while (i < zzwv.length)
    {
      localzzd.zzc(zzadz.zza.zzcgu().zzb(zzag.zzpi.toString(), zzwv[i]).zzb(zzag.zzow.toString(), zzdl.zzos(zzn.zzcaf())).zzb(zzn.zzcag(), zzww[i]).zzcgv());
      i += 1;
    }
    return localzzd.zzcgy();
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzbg
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */