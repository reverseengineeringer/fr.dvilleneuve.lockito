package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzadz.zza;
import com.google.android.gms.internal.zzadz.zze;
import java.util.List;
import java.util.Map;
import java.util.Set;

class zzcw$3
  implements zzcw.zza
{
  zzcw$3(zzcw paramzzcw, Map paramMap1, Map paramMap2, Map paramMap3, Map paramMap4) {}
  
  public void zza(zzadz.zze paramzze, Set<zzadz.zza> paramSet1, Set<zzadz.zza> paramSet2, zzcr paramzzcr)
  {
    List localList1 = (List)axV.get(paramzze);
    List localList2 = (List)axW.get(paramzze);
    if (localList1 != null)
    {
      paramSet1.addAll(localList1);
      paramzzcr.zzcbt().zzc(localList1, localList2);
    }
    paramSet1 = (List)axX.get(paramzze);
    paramzze = (List)axY.get(paramzze);
    if (paramSet1 != null)
    {
      paramSet2.addAll(paramSet1);
      paramzzcr.zzcbu().zzc(paramSet1, paramzze);
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzcw.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */