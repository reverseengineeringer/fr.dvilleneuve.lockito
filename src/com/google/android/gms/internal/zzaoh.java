package com.google.android.gms.internal;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class zzaoh
  implements zzanl
{
  private final zzans beb;
  private final boolean bfQ;
  
  public zzaoh(zzans paramzzans, boolean paramBoolean)
  {
    beb = paramzzans;
    bfQ = paramBoolean;
  }
  
  private zzank<?> zza(zzams paramzzams, Type paramType)
  {
    if ((paramType == Boolean.TYPE) || (paramType == Boolean.class)) {
      return zzaon.bgm;
    }
    return paramzzams.zza(zzaoo.zzl(paramType));
  }
  
  public <T> zzank<T> zza(zzams paramzzams, zzaoo<T> paramzzaoo)
  {
    Object localObject = paramzzaoo.t();
    if (!Map.class.isAssignableFrom(paramzzaoo.s())) {
      return null;
    }
    localObject = zzanr.zzb((Type)localObject, zzanr.zzf((Type)localObject));
    zzank localzzank1 = zza(paramzzams, localObject[0]);
    zzank localzzank2 = paramzzams.zza(zzaoo.zzl(localObject[1]));
    paramzzaoo = beb.zzb(paramzzaoo);
    return new zza(paramzzams, localObject[0], localzzank1, localObject[1], localzzank2, paramzzaoo);
  }
  
  private final class zza<K, V>
    extends zzank<Map<K, V>>
  {
    private final zzanx<? extends Map<K, V>> bfI;
    private final zzank<K> bfR;
    private final zzank<V> bfS;
    
    public zza(Type paramType1, zzank<K> paramzzank, Type paramType2, zzank<V> paramzzank1, zzanx<? extends Map<K, V>> paramzzanx)
    {
      bfR = new zzaom(paramType1, paramType2, paramzzank);
      bfS = new zzaom(paramType1, paramzzanx, paramzzank1);
      zzanx localzzanx;
      bfI = localzzanx;
    }
    
    private String zze(zzamy paramzzamy)
    {
      if (paramzzamy.zzczo())
      {
        paramzzamy = paramzzamy.zzczs();
        if (paramzzamy.zzczv()) {
          return String.valueOf(paramzzamy.zzczg());
        }
        if (paramzzamy.zzczu()) {
          return Boolean.toString(paramzzamy.zzczl());
        }
        if (paramzzamy.zzczw()) {
          return paramzzamy.zzczh();
        }
        throw new AssertionError();
      }
      if (paramzzamy.zzczp()) {
        return "null";
      }
      throw new AssertionError();
    }
    
    public void zza(zzaor paramzzaor, Map<K, V> paramMap)
      throws IOException
    {
      int m = 0;
      int k = 0;
      if (paramMap == null)
      {
        paramzzaor.r();
        return;
      }
      if (!zzaoh.zza(zzaoh.this))
      {
        paramzzaor.p();
        paramMap = paramMap.entrySet().iterator();
        while (paramMap.hasNext())
        {
          localObject = (Map.Entry)paramMap.next();
          paramzzaor.zzta(String.valueOf(((Map.Entry)localObject).getKey()));
          bfS.zza(paramzzaor, ((Map.Entry)localObject).getValue());
        }
        paramzzaor.q();
        return;
      }
      Object localObject = new ArrayList(paramMap.size());
      ArrayList localArrayList = new ArrayList(paramMap.size());
      paramMap = paramMap.entrySet().iterator();
      int i = 0;
      if (paramMap.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)paramMap.next();
        zzamy localzzamy = bfR.zzcl(localEntry.getKey());
        ((List)localObject).add(localzzamy);
        localArrayList.add(localEntry.getValue());
        if ((localzzamy.zzczm()) || (localzzamy.zzczn())) {}
        for (int j = 1;; j = 0)
        {
          i = j | i;
          break;
        }
      }
      if (i != 0)
      {
        paramzzaor.n();
        i = k;
        while (i < ((List)localObject).size())
        {
          paramzzaor.n();
          zzanz.zzb((zzamy)((List)localObject).get(i), paramzzaor);
          bfS.zza(paramzzaor, localArrayList.get(i));
          paramzzaor.o();
          i += 1;
        }
        paramzzaor.o();
        return;
      }
      paramzzaor.p();
      i = m;
      while (i < ((List)localObject).size())
      {
        paramzzaor.zzta(zze((zzamy)((List)localObject).get(i)));
        bfS.zza(paramzzaor, localArrayList.get(i));
        i += 1;
      }
      paramzzaor.q();
    }
    
    public Map<K, V> zzl(zzaop paramzzaop)
      throws IOException
    {
      Object localObject = paramzzaop.h();
      if (localObject == zzaoq.bhH)
      {
        paramzzaop.nextNull();
        return null;
      }
      Map localMap = (Map)bfI.a();
      if (localObject == zzaoq.bhz)
      {
        paramzzaop.beginArray();
        while (paramzzaop.hasNext())
        {
          paramzzaop.beginArray();
          localObject = bfR.zzb(paramzzaop);
          if (localMap.put(localObject, bfS.zzb(paramzzaop)) != null)
          {
            paramzzaop = String.valueOf(localObject);
            throw new zzanh(String.valueOf(paramzzaop).length() + 15 + "duplicate key: " + paramzzaop);
          }
          paramzzaop.endArray();
        }
        paramzzaop.endArray();
        return localMap;
      }
      paramzzaop.beginObject();
      while (paramzzaop.hasNext())
      {
        zzanu.bff.zzi(paramzzaop);
        localObject = bfR.zzb(paramzzaop);
        if (localMap.put(localObject, bfS.zzb(paramzzaop)) != null)
        {
          paramzzaop = String.valueOf(localObject);
          throw new zzanh(String.valueOf(paramzzaop).length() + 15 + "duplicate key: " + paramzzaop);
        }
      }
      paramzzaop.endObject();
      return localMap;
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzaoh
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */