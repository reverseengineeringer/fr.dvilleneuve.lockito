package com.google.android.gms.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

class zzans$6
  implements zzanx<T>
{
  zzans$6(zzans paramzzans, Constructor paramConstructor) {}
  
  public T a()
  {
    try
    {
      Object localObject = beT.newInstance(null);
      return (T)localObject;
    }
    catch (InstantiationException localInstantiationException)
    {
      str = String.valueOf(beT);
      throw new RuntimeException(String.valueOf(str).length() + 30 + "Failed to invoke " + str + " with no args", localInstantiationException);
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      String str = String.valueOf(beT);
      throw new RuntimeException(String.valueOf(str).length() + 30 + "Failed to invoke " + str + " with no args", localInvocationTargetException.getTargetException());
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new AssertionError(localIllegalAccessException);
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzans.6
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */