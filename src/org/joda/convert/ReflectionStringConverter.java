package org.joda.convert;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

abstract class ReflectionStringConverter<T>
  implements StringConverter<T>
{
  private final Class<T> cls;
  private final Method toString;
  
  ReflectionStringConverter(Class<T> paramClass, Method paramMethod)
  {
    if (paramMethod.getParameterTypes().length != 0) {
      throw new IllegalStateException("ToString method must have no parameters: " + paramMethod);
    }
    if (paramMethod.getReturnType() != String.class) {
      throw new IllegalStateException("ToString method must return a String: " + paramMethod);
    }
    cls = paramClass;
    toString = paramMethod;
  }
  
  public String convertToString(T paramT)
  {
    try
    {
      paramT = (String)toString.invoke(paramT, new Object[0]);
      return paramT;
    }
    catch (IllegalAccessException paramT)
    {
      throw new IllegalStateException("Method is not accessible: " + toString);
    }
    catch (InvocationTargetException paramT)
    {
      if ((paramT.getCause() instanceof RuntimeException)) {
        throw ((RuntimeException)paramT.getCause());
      }
      throw new RuntimeException(paramT.getMessage(), paramT.getCause());
    }
  }
  
  public String toString()
  {
    return "RefectionStringConverter[" + cls.getSimpleName() + "]";
  }
}

/* Location:
 * Qualified Name:     org.joda.convert.ReflectionStringConverter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */