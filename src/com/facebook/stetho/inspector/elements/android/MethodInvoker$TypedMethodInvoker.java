package com.facebook.stetho.inspector.elements.android;

import com.facebook.stetho.common.LogUtil;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

abstract class MethodInvoker$TypedMethodInvoker<T>
{
  private final Class<T> mArgType;
  
  MethodInvoker$TypedMethodInvoker(Class<T> paramClass)
  {
    mArgType = paramClass;
  }
  
  abstract T convertArgument(String paramString);
  
  boolean invoke(Object paramObject, String paramString1, String paramString2)
  {
    try
    {
      paramObject.getClass().getMethod(paramString1, new Class[] { mArgType }).invoke(paramObject, new Object[] { convertArgument(paramString2) });
      return true;
    }
    catch (InvocationTargetException paramObject)
    {
      LogUtil.w("InvocationTargetException: " + ((InvocationTargetException)paramObject).getMessage());
      return false;
    }
    catch (IllegalAccessException paramObject)
    {
      for (;;)
      {
        LogUtil.w("IllegalAccessException: " + ((IllegalAccessException)paramObject).getMessage());
      }
    }
    catch (IllegalArgumentException paramObject)
    {
      for (;;)
      {
        LogUtil.w("IllegalArgumentException: " + ((IllegalArgumentException)paramObject).getMessage());
      }
    }
    catch (NoSuchMethodException paramObject)
    {
      for (;;) {}
    }
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.elements.android.MethodInvoker.TypedMethodInvoker
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */