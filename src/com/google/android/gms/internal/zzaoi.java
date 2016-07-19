package com.google.android.gms.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class zzaoi
  extends zzank<Object>
{
  public static final zzanl bfE = new zzanl()
  {
    public <T> zzank<T> zza(zzams paramAnonymouszzams, zzaoo<T> paramAnonymouszzaoo)
    {
      if (paramAnonymouszzaoo.s() == Object.class) {
        return new zzaoi(paramAnonymouszzams, null);
      }
      return null;
    }
  };
  private final zzams beA;
  
  private zzaoi(zzams paramzzams)
  {
    beA = paramzzams;
  }
  
  public void zza(zzaor paramzzaor, Object paramObject)
    throws IOException
  {
    if (paramObject == null)
    {
      paramzzaor.r();
      return;
    }
    zzank localzzank = beA.zzk(paramObject.getClass());
    if ((localzzank instanceof zzaoi))
    {
      paramzzaor.p();
      paramzzaor.q();
      return;
    }
    localzzank.zza(paramzzaor, paramObject);
  }
  
  public Object zzb(zzaop paramzzaop)
    throws IOException
  {
    Object localObject = paramzzaop.h();
    switch (2.bfU[localObject.ordinal()])
    {
    default: 
      throw new IllegalStateException();
    case 1: 
      localObject = new ArrayList();
      paramzzaop.beginArray();
      while (paramzzaop.hasNext()) {
        ((List)localObject).add(zzb(paramzzaop));
      }
      paramzzaop.endArray();
      return localObject;
    case 2: 
      localObject = new zzanw();
      paramzzaop.beginObject();
      while (paramzzaop.hasNext()) {
        ((Map)localObject).put(paramzzaop.nextName(), zzb(paramzzaop));
      }
      paramzzaop.endObject();
      return localObject;
    case 3: 
      return paramzzaop.nextString();
    case 4: 
      return Double.valueOf(paramzzaop.nextDouble());
    case 5: 
      return Boolean.valueOf(paramzzaop.nextBoolean());
    }
    paramzzaop.nextNull();
    return null;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzaoi
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */