package com.google.android.gms.internal;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class zzamt
{
  private final List<zzanl> bea = new ArrayList();
  private zzant bek = zzant.beU;
  private zzani bel = zzani.bev;
  private zzamr bem = zzamq.bdS;
  private final Map<Type, zzamu<?>> ben = new HashMap();
  private final List<zzanl> beo = new ArrayList();
  private int bep = 2;
  private int beq = 2;
  private boolean ber = true;
  
  private void zza(String paramString, int paramInt1, int paramInt2, List<zzanl> paramList)
  {
    if ((paramString != null) && (!"".equals(paramString.trim()))) {}
    for (paramString = new zzamn(paramString);; paramString = new zzamn(paramInt1, paramInt2))
    {
      paramList.add(zzanj.zza(zzaoo.zzr(java.util.Date.class), paramString));
      paramList.add(zzanj.zza(zzaoo.zzr(Timestamp.class), paramString));
      paramList.add(zzanj.zza(zzaoo.zzr(java.sql.Date.class), paramString));
      do
      {
        return;
      } while ((paramInt1 == 2) || (paramInt2 == 2));
    }
  }
  
  public zzamt zza(Type paramType, Object paramObject)
  {
    if (((paramObject instanceof zzang)) || ((paramObject instanceof zzamx)) || ((paramObject instanceof zzamu)) || ((paramObject instanceof zzank))) {}
    for (boolean bool = true;; bool = false)
    {
      zzanq.zzbn(bool);
      if ((paramObject instanceof zzamu)) {
        ben.put(paramType, (zzamu)paramObject);
      }
      if (((paramObject instanceof zzang)) || ((paramObject instanceof zzamx)))
      {
        zzaoo localzzaoo = zzaoo.zzl(paramType);
        bea.add(zzanj.zzb(localzzaoo, paramObject));
      }
      if ((paramObject instanceof zzank)) {
        bea.add(zzaon.zza(zzaoo.zzl(paramType), (zzank)paramObject));
      }
      return this;
    }
  }
  
  public zzamt zza(zzamo... paramVarArgs)
  {
    int j = paramVarArgs.length;
    int i = 0;
    while (i < j)
    {
      zzamo localzzamo = paramVarArgs[i];
      bek = bek.zza(localzzamo, true, true);
      i += 1;
    }
    return this;
  }
  
  public zzamt zzcze()
  {
    ber = false;
    return this;
  }
  
  public zzams zzczf()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.addAll(bea);
    Collections.reverse(localArrayList);
    localArrayList.addAll(beo);
    zza(null, bep, beq, localArrayList);
    return new zzams(bek, bem, ben, false, false, false, ber, false, false, bel, localArrayList);
  }
  
  public zzamt zzd(int... paramVarArgs)
  {
    bek = bek.zze(paramVarArgs);
    return this;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzamt
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */