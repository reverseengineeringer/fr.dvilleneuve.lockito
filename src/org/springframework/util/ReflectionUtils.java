package org.springframework.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.UndeclaredThrowableException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public abstract class ReflectionUtils
{
  public static FieldFilter COPYABLE_FIELDS = new FieldFilter()
  {
    public boolean matches(Field paramAnonymousField)
    {
      return (!Modifier.isStatic(paramAnonymousField.getModifiers())) && (!Modifier.isFinal(paramAnonymousField.getModifiers()));
    }
  };
  public static MethodFilter NON_BRIDGED_METHODS = new MethodFilter()
  {
    public boolean matches(Method paramAnonymousMethod)
    {
      return !paramAnonymousMethod.isBridge();
    }
  };
  public static MethodFilter USER_DECLARED_METHODS = new MethodFilter()
  {
    public boolean matches(Method paramAnonymousMethod)
    {
      return (!paramAnonymousMethod.isBridge()) && (paramAnonymousMethod.getDeclaringClass() != Object.class);
    }
  };
  
  public static boolean declaresException(Method paramMethod, Class<?> paramClass)
  {
    Assert.notNull(paramMethod, "Method must not be null");
    paramMethod = paramMethod.getExceptionTypes();
    int j = paramMethod.length;
    int i = 0;
    while (i < j)
    {
      if (paramMethod[i].isAssignableFrom(paramClass)) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public static void doWithFields(Class<?> paramClass, FieldCallback paramFieldCallback)
    throws IllegalArgumentException
  {
    doWithFields(paramClass, paramFieldCallback, null);
  }
  
  public static void doWithFields(Class<?> paramClass, FieldCallback paramFieldCallback, FieldFilter paramFieldFilter)
    throws IllegalArgumentException
  {
    Object localObject;
    do
    {
      Field[] arrayOfField = paramClass.getDeclaredFields();
      int j = arrayOfField.length;
      int i = 0;
      if (i < j)
      {
        localObject = arrayOfField[i];
        if ((paramFieldFilter != null) && (!paramFieldFilter.matches((Field)localObject))) {}
        for (;;)
        {
          i += 1;
          break;
          try
          {
            paramFieldCallback.doWith((Field)localObject);
          }
          catch (IllegalAccessException paramClass)
          {
            throw new IllegalStateException("Shouldn't be illegal to access field '" + ((Field)localObject).getName() + "': " + paramClass);
          }
        }
      }
      localObject = paramClass.getSuperclass();
      if (localObject == null) {
        break;
      }
      paramClass = (Class<?>)localObject;
    } while (localObject != Object.class);
  }
  
  public static void doWithMethods(Class<?> paramClass, MethodCallback paramMethodCallback)
    throws IllegalArgumentException
  {
    doWithMethods(paramClass, paramMethodCallback, null);
  }
  
  public static void doWithMethods(Class<?> paramClass, MethodCallback paramMethodCallback, MethodFilter paramMethodFilter)
    throws IllegalArgumentException
  {
    Method[] arrayOfMethod = paramClass.getDeclaredMethods();
    int j = arrayOfMethod.length;
    int i = 0;
    if (i < j)
    {
      Method localMethod = arrayOfMethod[i];
      if ((paramMethodFilter != null) && (!paramMethodFilter.matches(localMethod))) {}
      for (;;)
      {
        i += 1;
        break;
        try
        {
          paramMethodCallback.doWith(localMethod);
        }
        catch (IllegalAccessException paramClass)
        {
          throw new IllegalStateException("Shouldn't be illegal to access method '" + localMethod.getName() + "': " + paramClass);
        }
      }
    }
    if (paramClass.getSuperclass() != null) {
      doWithMethods(paramClass.getSuperclass(), paramMethodCallback, paramMethodFilter);
    }
    for (;;)
    {
      return;
      if (paramClass.isInterface())
      {
        paramClass = paramClass.getInterfaces();
        j = paramClass.length;
        i = 0;
        while (i < j)
        {
          doWithMethods(paramClass[i], paramMethodCallback, paramMethodFilter);
          i += 1;
        }
      }
    }
  }
  
  public static Field findField(Class<?> paramClass, String paramString)
  {
    return findField(paramClass, paramString, null);
  }
  
  public static Field findField(Class<?> paramClass1, String paramString, Class<?> paramClass2)
  {
    Assert.notNull(paramClass1, "Class must not be null");
    boolean bool;
    if ((paramString != null) || (paramClass2 != null))
    {
      bool = true;
      Assert.isTrue(bool, "Either name or type of the field must be specified");
    }
    for (;;)
    {
      if ((Object.class.equals(paramClass1)) || (paramClass1 == null)) {
        break label118;
      }
      Field[] arrayOfField = paramClass1.getDeclaredFields();
      int j = arrayOfField.length;
      int i = 0;
      for (;;)
      {
        if (i >= j) {
          break label110;
        }
        Field localField = arrayOfField[i];
        if (((paramString == null) || (paramString.equals(localField.getName()))) && ((paramClass2 == null) || (paramClass2.equals(localField.getType()))))
        {
          return localField;
          bool = false;
          break;
        }
        i += 1;
      }
      label110:
      paramClass1 = paramClass1.getSuperclass();
    }
    label118:
    return null;
  }
  
  public static Method findMethod(Class<?> paramClass, String paramString)
  {
    return findMethod(paramClass, paramString, new Class[0]);
  }
  
  public static Method findMethod(Class<?> paramClass, String paramString, Class<?>... paramVarArgs)
  {
    Assert.notNull(paramClass, "Class must not be null");
    Assert.notNull(paramString, "Method name must not be null");
    while (paramClass != null)
    {
      Method[] arrayOfMethod;
      int j;
      int i;
      if (paramClass.isInterface())
      {
        arrayOfMethod = paramClass.getMethods();
        j = arrayOfMethod.length;
        i = 0;
      }
      for (;;)
      {
        if (i >= j) {
          break label95;
        }
        Method localMethod = arrayOfMethod[i];
        if ((paramString.equals(localMethod.getName())) && ((paramVarArgs == null) || (Arrays.equals(paramVarArgs, localMethod.getParameterTypes()))))
        {
          return localMethod;
          arrayOfMethod = paramClass.getDeclaredMethods();
          break;
        }
        i += 1;
      }
      label95:
      paramClass = paramClass.getSuperclass();
    }
    return null;
  }
  
  public static Method[] getAllDeclaredMethods(Class<?> paramClass)
    throws IllegalArgumentException
  {
    ArrayList localArrayList = new ArrayList(32);
    doWithMethods(paramClass, new MethodCallback()
    {
      public void doWith(Method paramAnonymousMethod)
      {
        val$methods.add(paramAnonymousMethod);
      }
    });
    return (Method[])localArrayList.toArray(new Method[localArrayList.size()]);
  }
  
  public static Object getField(Field paramField, Object paramObject)
  {
    try
    {
      paramField = paramField.get(paramObject);
      return paramField;
    }
    catch (IllegalAccessException paramField)
    {
      handleReflectionException(paramField);
      throw new IllegalStateException("Unexpected reflection exception - " + paramField.getClass().getName() + ": " + paramField.getMessage());
    }
  }
  
  public static Method[] getUniqueDeclaredMethods(Class<?> paramClass)
    throws IllegalArgumentException
  {
    ArrayList localArrayList = new ArrayList(32);
    doWithMethods(paramClass, new MethodCallback()
    {
      public void doWith(Method paramAnonymousMethod)
      {
        Object localObject2 = null;
        Iterator localIterator = val$methods.iterator();
        Method localMethod;
        do
        {
          localObject1 = localObject2;
          if (!localIterator.hasNext()) {
            break;
          }
          localMethod = (Method)localIterator.next();
        } while ((!paramAnonymousMethod.getName().equals(localMethod.getName())) || (!Arrays.equals(paramAnonymousMethod.getParameterTypes(), localMethod.getParameterTypes())));
        Object localObject1 = localObject2;
        if (localMethod.getReturnType() != paramAnonymousMethod.getReturnType())
        {
          localObject1 = localObject2;
          if (localMethod.getReturnType().isAssignableFrom(paramAnonymousMethod.getReturnType())) {
            localObject1 = localMethod;
          }
        }
        if (localObject1 != null) {
          val$methods.remove(localObject1);
        }
      }
    });
    return (Method[])localArrayList.toArray(new Method[localArrayList.size()]);
  }
  
  public static void handleInvocationTargetException(InvocationTargetException paramInvocationTargetException)
  {
    rethrowRuntimeException(paramInvocationTargetException.getTargetException());
  }
  
  public static void handleReflectionException(Exception paramException)
  {
    if ((paramException instanceof NoSuchMethodException)) {
      throw new IllegalStateException("Method not found: " + paramException.getMessage());
    }
    if ((paramException instanceof IllegalAccessException)) {
      throw new IllegalStateException("Could not access method: " + paramException.getMessage());
    }
    if ((paramException instanceof InvocationTargetException)) {
      handleInvocationTargetException((InvocationTargetException)paramException);
    }
    if ((paramException instanceof RuntimeException)) {
      throw ((RuntimeException)paramException);
    }
    throw new UndeclaredThrowableException(paramException);
  }
  
  public static Object invokeJdbcMethod(Method paramMethod, Object paramObject)
    throws SQLException
  {
    return invokeJdbcMethod(paramMethod, paramObject, new Object[0]);
  }
  
  public static Object invokeJdbcMethod(Method paramMethod, Object paramObject, Object... paramVarArgs)
    throws SQLException
  {
    try
    {
      paramMethod = paramMethod.invoke(paramObject, paramVarArgs);
      return paramMethod;
    }
    catch (IllegalAccessException paramMethod)
    {
      handleReflectionException(paramMethod);
      throw new IllegalStateException("Should never get here");
    }
    catch (InvocationTargetException paramMethod)
    {
      for (;;)
      {
        if ((paramMethod.getTargetException() instanceof SQLException)) {
          throw ((SQLException)paramMethod.getTargetException());
        }
        handleInvocationTargetException(paramMethod);
      }
    }
  }
  
  public static Object invokeMethod(Method paramMethod, Object paramObject)
  {
    return invokeMethod(paramMethod, paramObject, new Object[0]);
  }
  
  public static Object invokeMethod(Method paramMethod, Object paramObject, Object... paramVarArgs)
  {
    try
    {
      paramMethod = paramMethod.invoke(paramObject, paramVarArgs);
      return paramMethod;
    }
    catch (Exception paramMethod)
    {
      handleReflectionException(paramMethod);
      throw new IllegalStateException("Should never get here");
    }
  }
  
  public static boolean isEqualsMethod(Method paramMethod)
  {
    boolean bool = true;
    if ((paramMethod == null) || (!paramMethod.getName().equals("equals"))) {
      bool = false;
    }
    do
    {
      return bool;
      paramMethod = paramMethod.getParameterTypes();
    } while ((paramMethod.length == 1) && (paramMethod[0] == Object.class));
    return false;
  }
  
  public static boolean isHashCodeMethod(Method paramMethod)
  {
    return (paramMethod != null) && (paramMethod.getName().equals("hashCode")) && (paramMethod.getParameterTypes().length == 0);
  }
  
  public static boolean isObjectMethod(Method paramMethod)
  {
    try
    {
      Object.class.getDeclaredMethod(paramMethod.getName(), paramMethod.getParameterTypes());
      return true;
    }
    catch (SecurityException paramMethod)
    {
      return false;
    }
    catch (NoSuchMethodException paramMethod) {}
    return false;
  }
  
  public static boolean isPublicStaticFinal(Field paramField)
  {
    int i = paramField.getModifiers();
    return (Modifier.isPublic(i)) && (Modifier.isStatic(i)) && (Modifier.isFinal(i));
  }
  
  public static boolean isToStringMethod(Method paramMethod)
  {
    return (paramMethod != null) && (paramMethod.getName().equals("toString")) && (paramMethod.getParameterTypes().length == 0);
  }
  
  public static void makeAccessible(Constructor<?> paramConstructor)
  {
    if (((!Modifier.isPublic(paramConstructor.getModifiers())) || (!Modifier.isPublic(paramConstructor.getDeclaringClass().getModifiers()))) && (!paramConstructor.isAccessible())) {
      paramConstructor.setAccessible(true);
    }
  }
  
  public static void makeAccessible(Field paramField)
  {
    if (((!Modifier.isPublic(paramField.getModifiers())) || (!Modifier.isPublic(paramField.getDeclaringClass().getModifiers())) || (Modifier.isFinal(paramField.getModifiers()))) && (!paramField.isAccessible())) {
      paramField.setAccessible(true);
    }
  }
  
  public static void makeAccessible(Method paramMethod)
  {
    if (((!Modifier.isPublic(paramMethod.getModifiers())) || (!Modifier.isPublic(paramMethod.getDeclaringClass().getModifiers()))) && (!paramMethod.isAccessible())) {
      paramMethod.setAccessible(true);
    }
  }
  
  public static void rethrowException(Throwable paramThrowable)
    throws Exception
  {
    if ((paramThrowable instanceof Exception)) {
      throw ((Exception)paramThrowable);
    }
    if ((paramThrowable instanceof Error)) {
      throw ((Error)paramThrowable);
    }
    throw new UndeclaredThrowableException(paramThrowable);
  }
  
  public static void rethrowRuntimeException(Throwable paramThrowable)
  {
    if ((paramThrowable instanceof RuntimeException)) {
      throw ((RuntimeException)paramThrowable);
    }
    if ((paramThrowable instanceof Error)) {
      throw ((Error)paramThrowable);
    }
    throw new UndeclaredThrowableException(paramThrowable);
  }
  
  public static void setField(Field paramField, Object paramObject1, Object paramObject2)
  {
    try
    {
      paramField.set(paramObject1, paramObject2);
      return;
    }
    catch (IllegalAccessException paramField)
    {
      handleReflectionException(paramField);
      throw new IllegalStateException("Unexpected reflection exception - " + paramField.getClass().getName() + ": " + paramField.getMessage());
    }
  }
  
  public static void shallowCopyFieldState(Object paramObject1, final Object paramObject2)
    throws IllegalArgumentException
  {
    if (paramObject1 == null) {
      throw new IllegalArgumentException("Source for field copy cannot be null");
    }
    if (paramObject2 == null) {
      throw new IllegalArgumentException("Destination for field copy cannot be null");
    }
    if (!paramObject1.getClass().isAssignableFrom(paramObject2.getClass())) {
      throw new IllegalArgumentException("Destination class [" + paramObject2.getClass().getName() + "] must be same or subclass as source class [" + paramObject1.getClass().getName() + "]");
    }
    doWithFields(paramObject1.getClass(), new FieldCallback()
    {
      public void doWith(Field paramAnonymousField)
        throws IllegalArgumentException, IllegalAccessException
      {
        ReflectionUtils.makeAccessible(paramAnonymousField);
        Object localObject = paramAnonymousField.get(val$src);
        paramAnonymousField.set(paramObject2, localObject);
      }
    }, COPYABLE_FIELDS);
  }
  
  public static abstract interface FieldCallback
  {
    public abstract void doWith(Field paramField)
      throws IllegalArgumentException, IllegalAccessException;
  }
  
  public static abstract interface FieldFilter
  {
    public abstract boolean matches(Field paramField);
  }
  
  public static abstract interface MethodCallback
  {
    public abstract void doWith(Method paramMethod)
      throws IllegalArgumentException, IllegalAccessException;
  }
  
  public static abstract interface MethodFilter
  {
    public abstract boolean matches(Method paramMethod);
  }
}

/* Location:
 * Qualified Name:     org.springframework.util.ReflectionUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */