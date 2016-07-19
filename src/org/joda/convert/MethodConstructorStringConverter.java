package org.joda.convert;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

final class MethodConstructorStringConverter<T>
  extends ReflectionStringConverter<T>
{
  private final Constructor<T> fromString;
  
  MethodConstructorStringConverter(Class<T> paramClass, Method paramMethod, Constructor<T> paramConstructor)
  {
    super(paramClass, paramMethod);
    if ((paramClass.isInterface()) || (Modifier.isAbstract(paramClass.getModifiers())) || (paramClass.isLocalClass()) || (paramClass.isMemberClass())) {
      throw new IllegalArgumentException("FromString constructor must be on an instantiable class: " + paramConstructor);
    }
    if (paramConstructor.getDeclaringClass() != paramClass) {
      throw new IllegalStateException("FromString constructor must be defined on specified class: " + paramConstructor);
    }
    fromString = paramConstructor;
  }
  
  public T convertFromString(Class<? extends T> paramClass, String paramString)
  {
    try
    {
      paramClass = fromString.newInstance(new Object[] { paramString });
      return paramClass;
    }
    catch (IllegalAccessException paramClass)
    {
      throw new IllegalStateException("Constructor is not accessible: " + fromString);
    }
    catch (InstantiationException paramClass)
    {
      throw new IllegalStateException("Constructor is not valid: " + fromString);
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
 * Qualified Name:     org.joda.convert.MethodConstructorStringConverter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */