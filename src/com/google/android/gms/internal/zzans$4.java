package com.google.android.gms.internal;

import java.lang.reflect.Type;

class zzans$4
  implements zzanx<T>
{
  private final zzaoa beQ = zzaoa.f();
  
  zzans$4(zzans paramzzans, Class paramClass, Type paramType) {}
  
  public T a()
  {
    try
    {
      Object localObject = beQ.zzf(beR);
      return (T)localObject;
    }
    catch (Exception localException)
    {
      String str = String.valueOf(beO);
      throw new RuntimeException(String.valueOf(str).length() + 116 + "Unable to invoke no-args constructor for " + str + ". " + "Register an InstanceCreator with Gson for this type may fix this problem.", localException);
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzans.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */