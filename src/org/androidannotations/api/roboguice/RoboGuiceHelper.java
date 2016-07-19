package org.androidannotations.api.roboguice;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RoboGuiceHelper
{
  public static void callInjectViews(Object paramObject)
  {
    try
    {
      Method localMethod = Class.forName("roboguice.inject.ViewListener$ViewMembersInjector").getDeclaredMethod("injectViews", new Class[] { Object.class });
      localMethod.setAccessible(true);
      localMethod.invoke(null, new Object[] { paramObject });
      return;
    }
    catch (ClassNotFoundException paramObject)
    {
      propagateRuntimeException((Throwable)paramObject);
      return;
    }
    catch (NoSuchMethodException paramObject)
    {
      propagateRuntimeException((Throwable)paramObject);
      return;
    }
    catch (SecurityException paramObject)
    {
      propagateRuntimeException((Throwable)paramObject);
      return;
    }
    catch (IllegalAccessException paramObject)
    {
      propagateRuntimeException((Throwable)paramObject);
      return;
    }
    catch (IllegalArgumentException paramObject)
    {
      propagateRuntimeException((Throwable)paramObject);
      return;
    }
    catch (InvocationTargetException paramObject)
    {
      propagateRuntimeException((Throwable)paramObject);
    }
  }
  
  private static void propagateRuntimeException(Throwable paramThrowable)
  {
    throw new RuntimeException("Could not invoke RoboGuice method!", paramThrowable);
  }
}

/* Location:
 * Qualified Name:     org.androidannotations.api.roboguice.RoboGuiceHelper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */