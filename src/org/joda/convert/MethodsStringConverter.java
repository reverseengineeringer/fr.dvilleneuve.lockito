package org.joda.convert;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

final class MethodsStringConverter<T>
  extends ReflectionStringConverter<T>
{
  private final Method fromString;
  
  MethodsStringConverter(Class<T> paramClass, Method paramMethod1, Method paramMethod2)
  {
    super(paramClass, paramMethod1);
    if (!Modifier.isStatic(paramMethod2.getModifiers())) {
      throw new IllegalStateException("FromString method must be static: " + paramMethod2);
    }
    if (paramMethod2.getParameterTypes().length != 1) {
      throw new IllegalStateException("FromString method must have one parameter: " + paramMethod2);
    }
    paramMethod1 = paramMethod2.getParameterTypes()[0];
    if ((paramMethod1 != String.class) && (paramMethod1 != CharSequence.class)) {
      throw new IllegalStateException("FromString method must take a String or CharSequence: " + paramMethod2);
    }
    if ((!paramMethod2.getReturnType().isAssignableFrom(paramClass)) && (!paramClass.isAssignableFrom(paramMethod2.getReturnType()))) {
      throw new IllegalStateException("FromString method must return specified class or a supertype: " + paramMethod2);
    }
    fromString = paramMethod2;
  }
  
  public T convertFromString(Class<? extends T> paramClass, String paramString)
  {
    try
    {
      paramClass = paramClass.cast(fromString.invoke(null, new Object[] { paramString }));
      return paramClass;
    }
    catch (IllegalAccessException paramClass)
    {
      throw new IllegalStateException("Method is not accessible: " + fromString);
    }
    catch (InvocationTargetException paramClass)
    {
      if ((paramClass.getCause() instanceof RuntimeException)) {
        throw ((RuntimeException)paramClass.getCause());
      }
      throw new RuntimeException(paramClass.getMessage(), paramClass.getCause());
    }
  }
}

/* Location:
 * Qualified Name:     org.joda.convert.MethodsStringConverter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */