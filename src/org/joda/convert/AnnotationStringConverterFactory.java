package org.joda.convert;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

final class AnnotationStringConverterFactory
  implements StringConverterFactory
{
  static final StringConverterFactory INSTANCE = new AnnotationStringConverterFactory();
  
  private <T> StringConverter<T> findAnnotatedConverter(Class<T> paramClass)
  {
    Method localMethod1 = findToStringMethod(paramClass);
    if (localMethod1 == null) {
      return null;
    }
    Constructor localConstructor = findFromStringConstructor(paramClass);
    if (localConstructor == null) {}
    Method localMethod2;
    for (boolean bool = true;; bool = false)
    {
      localMethod2 = findFromStringMethod(paramClass, bool);
      if ((localConstructor != null) || (localMethod2 != null)) {
        break;
      }
      throw new IllegalStateException("Class annotated with @ToString but not with @FromString: " + paramClass.getName());
    }
    if ((localConstructor != null) && (localMethod2 != null)) {
      throw new IllegalStateException("Both method and constructor are annotated with @FromString: " + paramClass.getName());
    }
    if (localConstructor != null) {
      return new MethodConstructorStringConverter(paramClass, localMethod1, localConstructor);
    }
    return new MethodsStringConverter(paramClass, localMethod1, localMethod2);
  }
  
  private Method findFromString(Class<?> paramClass, Method paramMethod)
  {
    Object localObject3 = paramClass.getDeclaredMethods();
    int j = localObject3.length;
    int i = 0;
    Object localObject2;
    while (i < j)
    {
      localObject2 = localObject3[i];
      localObject1 = paramMethod;
      if ((FromString)((Method)localObject2).getAnnotation(FromString.class) != null)
      {
        if (paramMethod != null) {
          throw new IllegalStateException("Two methods are annotated with @FromString: " + paramClass.getName());
        }
        localObject1 = localObject2;
      }
      i += 1;
      paramMethod = (Method)localObject1;
    }
    localObject3 = (FromStringFactory)paramClass.getAnnotation(FromStringFactory.class);
    Object localObject1 = paramMethod;
    if (localObject3 != null)
    {
      if (paramMethod != null) {
        throw new IllegalStateException("Class annotated with @FromString and @FromStringFactory: " + paramClass.getName());
      }
      Method[] arrayOfMethod = ((FromStringFactory)localObject3).factory().getDeclaredMethods();
      j = arrayOfMethod.length;
      i = 0;
      for (;;)
      {
        localObject1 = paramMethod;
        if (i >= j) {
          break;
        }
        localObject2 = arrayOfMethod[i];
        localObject1 = paramMethod;
        if (paramClass.isAssignableFrom(((Method)localObject2).getReturnType()))
        {
          localObject1 = paramMethod;
          if ((FromString)((Method)localObject2).getAnnotation(FromString.class) != null)
          {
            if (paramMethod != null) {
              throw new IllegalStateException("Two methods are annotated with @FromString on the factory: " + ((FromStringFactory)localObject3).factory().getName());
            }
            localObject1 = localObject2;
          }
        }
        i += 1;
        paramMethod = (Method)localObject1;
      }
    }
    return (Method)localObject1;
  }
  
  private <T> Constructor<T> findFromStringConstructor(Class<T> paramClass)
  {
    try
    {
      Constructor localConstructor = paramClass.getDeclaredConstructor(new Class[] { String.class });
      paramClass = localConstructor;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      try
      {
        paramClass = paramClass.getDeclaredConstructor(new Class[] { CharSequence.class });
      }
      catch (NoSuchMethodException paramClass)
      {
        return null;
      }
    }
    if ((FromString)paramClass.getAnnotation(FromString.class) != null) {
      return paramClass;
    }
    return null;
  }
  
  private Method findFromStringMethod(Class<?> paramClass, boolean paramBoolean)
  {
    Object localObject2 = null;
    Object localObject3 = paramClass;
    for (;;)
    {
      Object localObject1 = localObject2;
      if (localObject3 != null)
      {
        localObject1 = localObject2;
        if (localObject2 == null)
        {
          localObject1 = findFromString((Class)localObject3, (Method)localObject2);
          if (paramBoolean) {
            break label94;
          }
        }
      }
      localObject2 = localObject1;
      if (!paramBoolean) {
        break;
      }
      localObject2 = localObject1;
      if (localObject1 != null) {
        break;
      }
      paramClass = paramClass.getInterfaces();
      int j = paramClass.length;
      int i = 0;
      for (;;)
      {
        localObject2 = localObject1;
        if (i >= j) {
          break;
        }
        localObject1 = findFromString(paramClass[i], (Method)localObject1);
        i += 1;
      }
      label94:
      localObject3 = ((Class)localObject3).getSuperclass();
      localObject2 = localObject1;
    }
    return (Method)localObject2;
  }
  
  private Method findToStringMethod(Class<?> paramClass)
  {
    Object localObject1 = null;
    Method[] arrayOfMethod;
    int j;
    int i;
    Object localObject4;
    Object localObject3;
    for (Object localObject2 = paramClass; (localObject2 != null) && (localObject1 == null); localObject2 = ((Class)localObject2).getSuperclass())
    {
      arrayOfMethod = ((Class)localObject2).getDeclaredMethods();
      j = arrayOfMethod.length;
      i = 0;
      while (i < j)
      {
        localObject4 = arrayOfMethod[i];
        localObject3 = localObject1;
        if ((ToString)((Method)localObject4).getAnnotation(ToString.class) != null)
        {
          if (localObject1 != null) {
            throw new IllegalStateException("Two methods are annotated with @ToString: " + paramClass.getName());
          }
          localObject3 = localObject4;
        }
        i += 1;
        localObject1 = localObject3;
      }
    }
    localObject2 = localObject1;
    if (localObject1 == null)
    {
      localObject4 = paramClass.getInterfaces();
      int k = localObject4.length;
      i = 0;
      for (;;)
      {
        localObject2 = localObject1;
        if (i >= k) {
          break;
        }
        arrayOfMethod = localObject4[i].getDeclaredMethods();
        int m = arrayOfMethod.length;
        j = 0;
        while (j < m)
        {
          localObject3 = arrayOfMethod[j];
          localObject2 = localObject1;
          if ((ToString)((Method)localObject3).getAnnotation(ToString.class) != null)
          {
            if (localObject1 != null) {
              throw new IllegalStateException("Two methods are annotated with @ToString on interfaces: " + paramClass.getName());
            }
            localObject2 = localObject3;
          }
          j += 1;
          localObject1 = localObject2;
        }
        i += 1;
      }
    }
    return (Method)localObject2;
  }
  
  public StringConverter<?> findConverter(Class<?> paramClass)
  {
    return findAnnotatedConverter(paramClass);
  }
  
  public String toString()
  {
    return getClass().getSimpleName();
  }
}

/* Location:
 * Qualified Name:     org.joda.convert.AnnotationStringConverterFactory
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */