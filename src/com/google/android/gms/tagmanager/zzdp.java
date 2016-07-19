package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzai.zza;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

class zzdp
{
  private static zzcd<zzai.zza> zza(zzcd<zzai.zza> paramzzcd)
  {
    try
    {
      zzcd localzzcd = new zzcd(zzdl.zzar(zzoz(zzdl.zzg((zzai.zza)paramzzcd.getObject()))), paramzzcd.zzcbz());
      return localzzcd;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      zzbn.zzb("Escape URI: unsupported encoding", localUnsupportedEncodingException);
    }
    return paramzzcd;
  }
  
  private static zzcd<zzai.zza> zza(zzcd<zzai.zza> paramzzcd, int paramInt)
  {
    if (!zzn((zzai.zza)paramzzcd.getObject()))
    {
      zzbn.e("Escaping can only be applied to strings.");
      return paramzzcd;
    }
    switch (paramInt)
    {
    default: 
      zzbn.e(39 + "Unsupported Value Escaping: " + paramInt);
      return paramzzcd;
    }
    return zza(paramzzcd);
  }
  
  static zzcd<zzai.zza> zza(zzcd<zzai.zza> paramzzcd, int... paramVarArgs)
  {
    int j = paramVarArgs.length;
    int i = 0;
    while (i < j)
    {
      paramzzcd = zza(paramzzcd, paramVarArgs[i]);
      i += 1;
    }
    return paramzzcd;
  }
  
  private static boolean zzn(zzai.zza paramzza)
  {
    return zzdl.zzl(paramzza) instanceof String;
  }
  
  static String zzoz(String paramString)
    throws UnsupportedEncodingException
  {
    return URLEncoder.encode(paramString, "UTF-8").replaceAll("\\+", "%20");
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzdp
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */