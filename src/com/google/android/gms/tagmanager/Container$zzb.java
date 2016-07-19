package com.google.android.gms.tagmanager;

import java.util.Map;

class Container$zzb
  implements zzt.zza
{
  private Container$zzb(Container paramContainer) {}
  
  public Object zzd(String paramString, Map<String, Object> paramMap)
  {
    Container.FunctionCallTagCallback localFunctionCallTagCallback = avg.zznp(paramString);
    if (localFunctionCallTagCallback != null) {
      localFunctionCallTagCallback.execute(paramString, paramMap);
    }
    return zzdl.zzcdp();
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.Container.zzb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */