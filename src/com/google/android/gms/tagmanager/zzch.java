package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzag;
import com.google.android.gms.internal.zzai.zza;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public abstract class zzch
  extends zzal
{
  private static final String awj = zzag.zzlk.toString();
  private static final String axh = zzag.zzll.toString();
  
  public zzch(String paramString)
  {
    super(paramString, new String[] { awj, axh });
  }
  
  protected abstract boolean zza(zzai.zza paramzza1, zzai.zza paramzza2, Map<String, zzai.zza> paramMap);
  
  public zzai.zza zzav(Map<String, zzai.zza> paramMap)
  {
    Object localObject = paramMap.values().iterator();
    while (((Iterator)localObject).hasNext()) {
      if ((zzai.zza)((Iterator)localObject).next() == zzdl.zzcdq()) {
        return zzdl.zzar(Boolean.valueOf(false));
      }
    }
    localObject = (zzai.zza)paramMap.get(awj);
    zzai.zza localzza = (zzai.zza)paramMap.get(axh);
    if ((localObject == null) || (localzza == null)) {}
    for (boolean bool = false;; bool = zza((zzai.zza)localObject, localzza, paramMap)) {
      return zzdl.zzar(Boolean.valueOf(bool));
    }
  }
  
  public boolean zzcac()
  {
    return true;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzch
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */