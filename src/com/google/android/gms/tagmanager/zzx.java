package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzaf;
import com.google.android.gms.internal.zzag;
import com.google.android.gms.internal.zzai.zza;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class zzx
  extends zzdj
{
  private static final String ID = zzaf.zzhr.toString();
  private static final String VALUE = zzag.zzue.toString();
  private static final String avY = zzag.zzmh.toString();
  private final DataLayer ava;
  
  public zzx(DataLayer paramDataLayer)
  {
    super(ID, new String[] { VALUE });
    ava = paramDataLayer;
  }
  
  private void zza(zzai.zza paramzza)
  {
    if ((paramzza == null) || (paramzza == zzdl.zzcdk())) {}
    do
    {
      return;
      paramzza = zzdl.zzg(paramzza);
    } while (paramzza == zzdl.zzcdp());
    ava.zznw(paramzza);
  }
  
  private void zzb(zzai.zza paramzza)
  {
    if ((paramzza == null) || (paramzza == zzdl.zzcdk())) {}
    for (;;)
    {
      return;
      paramzza = zzdl.zzl(paramzza);
      if ((paramzza instanceof List))
      {
        paramzza = ((List)paramzza).iterator();
        while (paramzza.hasNext())
        {
          Object localObject = paramzza.next();
          if ((localObject instanceof Map))
          {
            localObject = (Map)localObject;
            ava.push((Map)localObject);
          }
        }
      }
    }
  }
  
  public void zzax(Map<String, zzai.zza> paramMap)
  {
    zzb((zzai.zza)paramMap.get(VALUE));
    zza((zzai.zza)paramMap.get(avY));
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzx
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */