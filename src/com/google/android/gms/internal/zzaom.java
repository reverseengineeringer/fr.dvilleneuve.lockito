package com.google.android.gms.internal;

import java.io.IOException;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

final class zzaom<T>
  extends zzank<T>
{
  private final zzank<T> bej;
  private final zzams bgf;
  private final Type bgg;
  
  zzaom(zzams paramzzams, zzank<T> paramzzank, Type paramType)
  {
    bgf = paramzzams;
    bej = paramzzank;
    bgg = paramType;
  }
  
  private Type zzb(Type paramType, Object paramObject)
  {
    Object localObject = paramType;
    if (paramObject != null) {
      if ((paramType != Object.class) && (!(paramType instanceof TypeVariable)))
      {
        localObject = paramType;
        if (!(paramType instanceof Class)) {}
      }
      else
      {
        localObject = paramObject.getClass();
      }
    }
    return (Type)localObject;
  }
  
  public void zza(zzaor paramzzaor, T paramT)
    throws IOException
  {
    zzank localzzank = bej;
    Type localType = zzb(bgg, paramT);
    if (localType != bgg)
    {
      localzzank = bgf.zza(zzaoo.zzl(localType));
      if ((localzzank instanceof zzaoj.zza)) {
        break label52;
      }
    }
    for (;;)
    {
      localzzank.zza(paramzzaor, paramT);
      return;
      label52:
      if (!(bej instanceof zzaoj.zza)) {
        localzzank = bej;
      }
    }
  }
  
  public T zzb(zzaop paramzzaop)
    throws IOException
  {
    return (T)bej.zzb(paramzzaop);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzaom
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */