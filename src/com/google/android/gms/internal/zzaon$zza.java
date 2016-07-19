package com.google.android.gms.internal;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

final class zzaon$zza<T extends Enum<T>>
  extends zzank<T>
{
  private final Map<String, T> bhi = new HashMap();
  private final Map<T, String> bhj = new HashMap();
  
  public zzaon$zza(Class<T> paramClass)
  {
    try
    {
      Enum[] arrayOfEnum = (Enum[])paramClass.getEnumConstants();
      int k = arrayOfEnum.length;
      int i = 0;
      while (i < k)
      {
        Enum localEnum = arrayOfEnum[i];
        Object localObject1 = localEnum.name();
        Object localObject2 = (zzann)paramClass.getField((String)localObject1).getAnnotation(zzann.class);
        if (localObject2 != null)
        {
          String str = ((zzann)localObject2).value();
          localObject2 = ((zzann)localObject2).zzczy();
          int m = localObject2.length;
          int j = 0;
          for (;;)
          {
            localObject1 = str;
            if (j >= m) {
              break;
            }
            localObject1 = localObject2[j];
            bhi.put(localObject1, localEnum);
            j += 1;
          }
        }
        bhi.put(localObject1, localEnum);
        bhj.put(localEnum, localObject1);
        i += 1;
      }
      return;
    }
    catch (NoSuchFieldException paramClass)
    {
      throw new AssertionError();
    }
  }
  
  public void zza(zzaor paramzzaor, T paramT)
    throws IOException
  {
    if (paramT == null) {}
    for (paramT = null;; paramT = (String)bhj.get(paramT))
    {
      paramzzaor.zztb(paramT);
      return;
    }
  }
  
  public T zzaf(zzaop paramzzaop)
    throws IOException
  {
    if (paramzzaop.h() == zzaoq.bhH)
    {
      paramzzaop.nextNull();
      return null;
    }
    return (Enum)bhi.get(paramzzaop.nextString());
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzaon.zza
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */