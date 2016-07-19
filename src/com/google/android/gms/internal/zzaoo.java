package com.google.android.gms.internal;

import java.lang.reflect.Type;

public class zzaoo<T>
{
  final Type bgg;
  final Class<? super T> bhk;
  final int bhl;
  
  protected zzaoo()
  {
    bgg = zzq(getClass());
    bhk = zzanr.zzf(bgg);
    bhl = bgg.hashCode();
  }
  
  zzaoo(Type paramType)
  {
    bgg = zzanr.zze((Type)zzanq.zzaa(paramType));
    bhk = zzanr.zzf(bgg);
    bhl = bgg.hashCode();
  }
  
  public static zzaoo<?> zzl(Type paramType)
  {
    return new zzaoo(paramType);
  }
  
  static Type zzq(Class<?> paramClass)
  {
    paramClass = paramClass.getGenericSuperclass();
    if ((paramClass instanceof Class)) {
      throw new RuntimeException("Missing type parameter.");
    }
    return zzanr.zze(((java.lang.reflect.ParameterizedType)paramClass).getActualTypeArguments()[0]);
  }
  
  public static <T> zzaoo<T> zzr(Class<T> paramClass)
  {
    return new zzaoo(paramClass);
  }
  
  public final boolean equals(Object paramObject)
  {
    return ((paramObject instanceof zzaoo)) && (zzanr.zza(bgg, bgg));
  }
  
  public final int hashCode()
  {
    return bhl;
  }
  
  public final Class<? super T> s()
  {
    return bhk;
  }
  
  public final Type t()
  {
    return bgg;
  }
  
  public final String toString()
  {
    return zzanr.zzg(bgg);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzaoo
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */