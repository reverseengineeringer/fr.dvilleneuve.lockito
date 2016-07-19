package com.google.android.gms.internal;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;
import java.util.List;

public final class zzaob<E>
  extends zzank<Object>
{
  public static final zzanl bfE = new zzanl()
  {
    public <T> zzank<T> zza(zzams paramAnonymouszzams, zzaoo<T> paramAnonymouszzaoo)
    {
      paramAnonymouszzaoo = paramAnonymouszzaoo.t();
      if ((!(paramAnonymouszzaoo instanceof GenericArrayType)) && ((!(paramAnonymouszzaoo instanceof Class)) || (!((Class)paramAnonymouszzaoo).isArray()))) {
        return null;
      }
      paramAnonymouszzaoo = zzanr.zzh(paramAnonymouszzaoo);
      return new zzaob(paramAnonymouszzams, paramAnonymouszzams.zza(zzaoo.zzl(paramAnonymouszzaoo)), zzanr.zzf(paramAnonymouszzaoo));
    }
  };
  private final Class<E> bfF;
  private final zzank<E> bfG;
  
  public zzaob(zzams paramzzams, zzank<E> paramzzank, Class<E> paramClass)
  {
    bfG = new zzaom(paramzzams, paramzzank, paramClass);
    bfF = paramClass;
  }
  
  public void zza(zzaor paramzzaor, Object paramObject)
    throws IOException
  {
    if (paramObject == null)
    {
      paramzzaor.r();
      return;
    }
    paramzzaor.n();
    int i = 0;
    int j = Array.getLength(paramObject);
    while (i < j)
    {
      Object localObject = Array.get(paramObject, i);
      bfG.zza(paramzzaor, localObject);
      i += 1;
    }
    paramzzaor.o();
  }
  
  public Object zzb(zzaop paramzzaop)
    throws IOException
  {
    if (paramzzaop.h() == zzaoq.bhH)
    {
      paramzzaop.nextNull();
      return null;
    }
    ArrayList localArrayList = new ArrayList();
    paramzzaop.beginArray();
    while (paramzzaop.hasNext()) {
      localArrayList.add(bfG.zzb(paramzzaop));
    }
    paramzzaop.endArray();
    paramzzaop = Array.newInstance(bfF, localArrayList.size());
    int i = 0;
    while (i < localArrayList.size())
    {
      Array.set(paramzzaop, i, localArrayList.get(i));
      i += 1;
    }
    return paramzzaop;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzaob
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */