package com.google.android.gms.analytics.internal;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.zzi;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.util.zze;

public class zzc
{
  private final zzf zzcws;
  
  protected zzc(zzf paramzzf)
  {
    zzab.zzaa(paramzzf);
    zzcws = paramzzf;
  }
  
  private void zza(int paramInt, String paramString, Object paramObject1, Object paramObject2, Object paramObject3)
  {
    Object localObject = null;
    if (zzcws != null) {
      localObject = zzcws.zzzj();
    }
    if (localObject != null) {
      ((zzaf)localObject).zza(paramInt, paramString, paramObject1, paramObject2, paramObject3);
    }
    do
    {
      return;
      localObject = (String)zzy.zzczr.get();
    } while (!Log.isLoggable((String)localObject, paramInt));
    Log.println(paramInt, (String)localObject, zzc(paramString, paramObject1, paramObject2, paramObject3));
  }
  
  protected static String zzc(String paramString, Object paramObject1, Object paramObject2, Object paramObject3)
  {
    String str1 = paramString;
    if (paramString == null) {
      str1 = "";
    }
    String str2 = zzl(paramObject1);
    paramObject2 = zzl(paramObject2);
    paramObject3 = zzl(paramObject3);
    StringBuilder localStringBuilder = new StringBuilder();
    paramString = "";
    if (!TextUtils.isEmpty(str1))
    {
      localStringBuilder.append(str1);
      paramString = ": ";
    }
    paramObject1 = paramString;
    if (!TextUtils.isEmpty(str2))
    {
      localStringBuilder.append(paramString);
      localStringBuilder.append(str2);
      paramObject1 = ", ";
    }
    paramString = (String)paramObject1;
    if (!TextUtils.isEmpty((CharSequence)paramObject2))
    {
      localStringBuilder.append((String)paramObject1);
      localStringBuilder.append((String)paramObject2);
      paramString = ", ";
    }
    if (!TextUtils.isEmpty((CharSequence)paramObject3))
    {
      localStringBuilder.append(paramString);
      localStringBuilder.append((String)paramObject3);
    }
    return localStringBuilder.toString();
  }
  
  private static String zzl(Object paramObject)
  {
    if (paramObject == null) {
      return "";
    }
    if ((paramObject instanceof String)) {
      return (String)paramObject;
    }
    if ((paramObject instanceof Boolean))
    {
      if (paramObject == Boolean.TRUE) {}
      for (paramObject = "true";; paramObject = "false") {
        return (String)paramObject;
      }
    }
    if ((paramObject instanceof Throwable)) {
      return ((Throwable)paramObject).toString();
    }
    return paramObject.toString();
  }
  
  protected Context getContext()
  {
    return zzcws.getContext();
  }
  
  public void zza(String paramString, Object paramObject)
  {
    zza(2, paramString, paramObject, null, null);
  }
  
  public void zza(String paramString, Object paramObject1, Object paramObject2)
  {
    zza(2, paramString, paramObject1, paramObject2, null);
  }
  
  public void zza(String paramString, Object paramObject1, Object paramObject2, Object paramObject3)
  {
    zza(3, paramString, paramObject1, paramObject2, paramObject3);
  }
  
  public void zzb(String paramString, Object paramObject)
  {
    zza(3, paramString, paramObject, null, null);
  }
  
  public void zzb(String paramString, Object paramObject1, Object paramObject2)
  {
    zza(3, paramString, paramObject1, paramObject2, null);
  }
  
  public void zzb(String paramString, Object paramObject1, Object paramObject2, Object paramObject3)
  {
    zza(5, paramString, paramObject1, paramObject2, paramObject3);
  }
  
  public void zzc(String paramString, Object paramObject)
  {
    zza(4, paramString, paramObject, null, null);
  }
  
  public void zzc(String paramString, Object paramObject1, Object paramObject2)
  {
    zza(5, paramString, paramObject1, paramObject2, null);
  }
  
  public void zzd(String paramString, Object paramObject)
  {
    zza(5, paramString, paramObject, null, null);
  }
  
  public void zzd(String paramString, Object paramObject1, Object paramObject2)
  {
    zza(6, paramString, paramObject1, paramObject2, null);
  }
  
  public void zze(String paramString, Object paramObject)
  {
    zza(6, paramString, paramObject, null, null);
  }
  
  public void zzei(String paramString)
  {
    zza(2, paramString, null, null, null);
  }
  
  public void zzej(String paramString)
  {
    zza(3, paramString, null, null, null);
  }
  
  public void zzek(String paramString)
  {
    zza(4, paramString, null, null, null);
  }
  
  public void zzel(String paramString)
  {
    zza(5, paramString, null, null, null);
  }
  
  public void zzem(String paramString)
  {
    zza(6, paramString, null, null, null);
  }
  
  public boolean zztc()
  {
    return Log.isLoggable((String)zzy.zzczr.get(), 2);
  }
  
  public GoogleAnalytics zzvx()
  {
    return zzcws.zzzk();
  }
  
  protected zzb zzwd()
  {
    return zzcws.zzwd();
  }
  
  protected zzap zzwe()
  {
    return zzcws.zzwe();
  }
  
  protected void zzwu()
  {
    zzcws.zzwu();
  }
  
  public zzf zzyu()
  {
    return zzcws;
  }
  
  protected void zzyv()
  {
    if (zzyy().zzabc()) {
      throw new IllegalStateException("Call only supported on the client side");
    }
  }
  
  protected zze zzyw()
  {
    return zzcws.zzyw();
  }
  
  protected zzaf zzyx()
  {
    return zzcws.zzyx();
  }
  
  protected zzr zzyy()
  {
    return zzcws.zzyy();
  }
  
  protected zzi zzyz()
  {
    return zzcws.zzyz();
  }
  
  protected zzv zzza()
  {
    return zzcws.zzza();
  }
  
  protected zzai zzzb()
  {
    return zzcws.zzzb();
  }
  
  protected zzn zzzc()
  {
    return zzcws.zzzn();
  }
  
  protected zza zzzd()
  {
    return zzcws.zzzm();
  }
  
  protected zzk zzze()
  {
    return zzcws.zzze();
  }
  
  protected zzu zzzf()
  {
    return zzcws.zzzf();
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.analytics.internal.zzc
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */