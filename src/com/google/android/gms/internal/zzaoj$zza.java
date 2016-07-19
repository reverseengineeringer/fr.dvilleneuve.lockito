package com.google.android.gms.internal;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public final class zzaoj$zza<T>
  extends zzank<T>
{
  private final zzanx<T> bfI;
  private final Map<String, zzaoj.zzb> bgb;
  
  private zzaoj$zza(zzanx<T> paramzzanx, Map<String, zzaoj.zzb> paramMap)
  {
    bfI = paramzzanx;
    bgb = paramMap;
  }
  
  public void zza(zzaor paramzzaor, T paramT)
    throws IOException
  {
    if (paramT == null)
    {
      paramzzaor.r();
      return;
    }
    paramzzaor.p();
    try
    {
      Iterator localIterator = bgb.values().iterator();
      while (localIterator.hasNext())
      {
        zzaoj.zzb localzzb = (zzaoj.zzb)localIterator.next();
        if (localzzb.zzcq(paramT))
        {
          paramzzaor.zzta(name);
          localzzb.zza(paramzzaor, paramT);
        }
      }
      paramzzaor.q();
    }
    catch (IllegalAccessException paramzzaor)
    {
      throw new AssertionError();
    }
  }
  
  public T zzb(zzaop paramzzaop)
    throws IOException
  {
    if (paramzzaop.h() == zzaoq.bhH)
    {
      paramzzaop.nextNull();
      return null;
    }
    Object localObject1 = bfI.a();
    try
    {
      paramzzaop.beginObject();
      for (;;)
      {
        if (!paramzzaop.hasNext()) {
          break label103;
        }
        localObject2 = paramzzaop.nextName();
        localObject2 = (zzaoj.zzb)bgb.get(localObject2);
        if ((localObject2 != null) && (bgd)) {
          break;
        }
        paramzzaop.skipValue();
      }
    }
    catch (IllegalStateException paramzzaop)
    {
      for (;;)
      {
        Object localObject2;
        throw new zzanh(paramzzaop);
        ((zzaoj.zzb)localObject2).zza(paramzzaop, localObject1);
      }
    }
    catch (IllegalAccessException paramzzaop)
    {
      throw new AssertionError(paramzzaop);
    }
    label103:
    paramzzaop.endObject();
    return (T)localObject1;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzaoj.zza
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */