package org.springframework.core;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.TypeVariable<*>;
import java.lang.reflect.WildcardType;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import org.springframework.util.Assert;

public abstract class GenericTypeResolver
{
  private static final Map<Class<?>, Reference<Map<TypeVariable<?>, Type>>> typeVariableCache = Collections.synchronizedMap(new WeakHashMap());
  
  private static Class<?>[] doResolveTypeArguments(Class<?> paramClass1, Class<?> paramClass2, Class<?> paramClass3)
  {
    while (paramClass2 != null)
    {
      Object localObject2;
      int i;
      Object localObject1;
      if (paramClass3.isInterface())
      {
        localObject2 = paramClass2.getGenericInterfaces();
        int j = localObject2.length;
        i = 0;
        if (i >= j) {
          break label76;
        }
        localObject1 = doResolveTypeArguments(paramClass1, localObject2[i], paramClass3);
        if (localObject1 == null) {}
      }
      do
      {
        return (Class<?>[])localObject1;
        i += 1;
        break;
        localObject2 = doResolveTypeArguments(paramClass1, paramClass2.getGenericSuperclass(), paramClass3);
        localObject1 = localObject2;
      } while (localObject2 != null);
      label76:
      paramClass2 = paramClass2.getSuperclass();
    }
    return null;
  }
  
  private static Class<?>[] doResolveTypeArguments(Class<?> paramClass1, Type paramType, Class<?> paramClass2)
  {
    if ((paramType instanceof ParameterizedType))
    {
      paramType = (ParameterizedType)paramType;
      Object localObject = paramType.getRawType();
      if (paramClass2.equals(localObject))
      {
        localObject = paramType.getActualTypeArguments();
        paramClass2 = new Class[localObject.length];
        int i = 0;
        for (;;)
        {
          paramType = paramClass2;
          if (i >= localObject.length) {
            break;
          }
          paramClass2[i] = extractClass(paramClass1, localObject[i]);
          i += 1;
        }
      }
      if (paramClass2.isAssignableFrom((Class)localObject))
      {
        paramType = doResolveTypeArguments(paramClass1, (Class)localObject, paramClass2);
        return paramType;
      }
    }
    else if ((paramType != null) && (paramClass2.isAssignableFrom((Class)paramType)))
    {
      return doResolveTypeArguments(paramClass1, (Class)paramType, paramClass2);
    }
    return null;
  }
  
  static Type extractBoundForTypeVariable(TypeVariable<?> paramTypeVariable)
  {
    paramTypeVariable = paramTypeVariable.getBounds();
    if (paramTypeVariable.length == 0) {
      paramTypeVariable = Object.class;
    }
    Object localObject;
    do
    {
      return paramTypeVariable;
      localObject = paramTypeVariable[0];
      paramTypeVariable = (TypeVariable<?>)localObject;
    } while (!(localObject instanceof TypeVariable));
    return extractBoundForTypeVariable((TypeVariable)localObject);
  }
  
  private static Class<?> extractClass(Class<?> paramClass, Type paramType)
  {
    if ((paramType instanceof ParameterizedType)) {
      return extractClass(paramClass, ((ParameterizedType)paramType).getRawType());
    }
    if ((paramType instanceof GenericArrayType)) {
      return Array.newInstance(extractClass(paramClass, ((GenericArrayType)paramType).getGenericComponentType()), 0).getClass();
    }
    Object localObject = paramType;
    if ((paramType instanceof TypeVariable))
    {
      paramType = (TypeVariable)paramType;
      localObject = (Type)getTypeVariableMap(paramClass).get(paramType);
      if (localObject != null) {
        break label98;
      }
    }
    label98:
    for (localObject = extractBoundForTypeVariable(paramType); (localObject instanceof Class); localObject = extractClass(paramClass, (Type)localObject)) {
      return (Class)localObject;
    }
    return Object.class;
  }
  
  private static void extractTypeVariablesFromGenericInterfaces(Type[] paramArrayOfType, Map<TypeVariable<?>, Type> paramMap)
  {
    int j = paramArrayOfType.length;
    int i = 0;
    if (i < j)
    {
      Object localObject = paramArrayOfType[i];
      if ((localObject instanceof ParameterizedType))
      {
        localObject = (ParameterizedType)localObject;
        populateTypeMapFromParameterizedType((ParameterizedType)localObject, paramMap);
        if ((((ParameterizedType)localObject).getRawType() instanceof Class)) {
          extractTypeVariablesFromGenericInterfaces(((Class)((ParameterizedType)localObject).getRawType()).getGenericInterfaces(), paramMap);
        }
      }
      for (;;)
      {
        i += 1;
        break;
        if ((localObject instanceof Class)) {
          extractTypeVariablesFromGenericInterfaces(((Class)localObject).getGenericInterfaces(), paramMap);
        }
      }
    }
  }
  
  static Type getRawType(Type paramType, Map<TypeVariable<?>, Type> paramMap)
  {
    Type localType = paramType;
    if ((paramType instanceof TypeVariable))
    {
      TypeVariable localTypeVariable = (TypeVariable)paramType;
      paramType = (Type)paramMap.get(localTypeVariable);
      localType = paramType;
      if (paramType == null) {
        localType = extractBoundForTypeVariable(localTypeVariable);
      }
    }
    paramType = localType;
    if ((localType instanceof ParameterizedType)) {
      paramType = ((ParameterizedType)localType).getRawType();
    }
    return paramType;
  }
  
  public static Type getTargetType(MethodParameter paramMethodParameter)
  {
    Assert.notNull(paramMethodParameter, "MethodParameter must not be null");
    if (paramMethodParameter.getConstructor() != null) {
      return paramMethodParameter.getConstructor().getGenericParameterTypes()[paramMethodParameter.getParameterIndex()];
    }
    if (paramMethodParameter.getParameterIndex() >= 0) {
      return paramMethodParameter.getMethod().getGenericParameterTypes()[paramMethodParameter.getParameterIndex()];
    }
    return paramMethodParameter.getMethod().getGenericReturnType();
  }
  
  public static Map<TypeVariable<?>, Type> getTypeVariableMap(Class<?> paramClass)
  {
    Object localObject1 = (Reference)typeVariableCache.get(paramClass);
    if (localObject1 != null) {}
    HashMap localHashMap;
    for (localObject1 = (Map)((Reference)localObject1).get();; localObject1 = null)
    {
      localObject2 = localObject1;
      if (localObject1 != null) {
        return localObject2;
      }
      localHashMap = new HashMap();
      extractTypeVariablesFromGenericInterfaces(paramClass.getGenericInterfaces(), localHashMap);
      localObject2 = paramClass.getGenericSuperclass();
      for (localObject1 = paramClass.getSuperclass(); (localObject1 != null) && (!Object.class.equals(localObject1)); localObject1 = ((Class)localObject1).getSuperclass())
      {
        if ((localObject2 instanceof ParameterizedType)) {
          populateTypeMapFromParameterizedType((ParameterizedType)localObject2, localHashMap);
        }
        extractTypeVariablesFromGenericInterfaces(((Class)localObject1).getGenericInterfaces(), localHashMap);
        localObject2 = ((Class)localObject1).getGenericSuperclass();
      }
    }
    for (localObject1 = paramClass; ((Class)localObject1).isMemberClass(); localObject1 = ((Class)localObject1).getEnclosingClass())
    {
      localObject2 = ((Class)localObject1).getGenericSuperclass();
      if ((localObject2 instanceof ParameterizedType)) {
        populateTypeMapFromParameterizedType((ParameterizedType)localObject2, localHashMap);
      }
    }
    typeVariableCache.put(paramClass, new WeakReference(localHashMap));
    Object localObject2 = localHashMap;
    return (Map<TypeVariable<?>, Type>)localObject2;
  }
  
  private static void populateTypeMapFromParameterizedType(ParameterizedType paramParameterizedType, Map<TypeVariable<?>, Type> paramMap)
  {
    if ((paramParameterizedType.getRawType() instanceof Class))
    {
      Type[] arrayOfType = paramParameterizedType.getActualTypeArguments();
      TypeVariable[] arrayOfTypeVariable = ((Class)paramParameterizedType.getRawType()).getTypeParameters();
      int i = 0;
      if (i < arrayOfType.length)
      {
        paramParameterizedType = arrayOfType[i];
        TypeVariable localTypeVariable1 = arrayOfTypeVariable[i];
        if ((paramParameterizedType instanceof Class)) {
          paramMap.put(localTypeVariable1, paramParameterizedType);
        }
        for (;;)
        {
          i += 1;
          break;
          if ((paramParameterizedType instanceof GenericArrayType))
          {
            paramMap.put(localTypeVariable1, paramParameterizedType);
          }
          else if ((paramParameterizedType instanceof ParameterizedType))
          {
            paramMap.put(localTypeVariable1, paramParameterizedType);
          }
          else if ((paramParameterizedType instanceof TypeVariable))
          {
            TypeVariable localTypeVariable2 = (TypeVariable)paramParameterizedType;
            Type localType = (Type)paramMap.get(localTypeVariable2);
            paramParameterizedType = localType;
            if (localType == null) {
              paramParameterizedType = extractBoundForTypeVariable(localTypeVariable2);
            }
            paramMap.put(localTypeVariable1, paramParameterizedType);
          }
        }
      }
    }
  }
  
  public static Class<?> resolveParameterType(MethodParameter paramMethodParameter, Class<?> paramClass)
  {
    Type localType = getTargetType(paramMethodParameter);
    Assert.notNull(paramClass, "Class must not be null");
    Map localMap = getTypeVariableMap(paramClass);
    paramClass = getRawType(localType, localMap);
    if ((paramClass instanceof Class)) {}
    for (paramClass = (Class)paramClass;; paramClass = paramMethodParameter.getParameterType())
    {
      paramMethodParameter.setParameterType(paramClass);
      typeVariableMap = localMap;
      return paramClass;
    }
  }
  
  public static Class<?> resolveReturnType(Method paramMethod, Class<?> paramClass)
  {
    Assert.notNull(paramMethod, "Method must not be null");
    Type localType = paramMethod.getGenericReturnType();
    Assert.notNull(paramClass, "Class must not be null");
    paramClass = getRawType(localType, getTypeVariableMap(paramClass));
    if ((paramClass instanceof Class)) {
      return (Class)paramClass;
    }
    return paramMethod.getReturnType();
  }
  
  public static Class<?> resolveReturnTypeArgument(Method paramMethod, Class<?> paramClass)
  {
    Assert.notNull(paramMethod, "method must not be null");
    Class localClass = paramMethod.getReturnType();
    paramMethod = paramMethod.getGenericReturnType();
    if (localClass.equals(paramClass)) {
      if ((paramMethod instanceof ParameterizedType))
      {
        paramMethod = ((ParameterizedType)paramMethod).getActualTypeArguments()[0];
        if (!(paramMethod instanceof WildcardType)) {
          return (Class)paramMethod;
        }
      }
      else
      {
        return null;
      }
    }
    return resolveTypeArgument((Class)localClass, paramClass);
  }
  
  public static Class<?> resolveType(Type paramType, Map<TypeVariable<?>, Type> paramMap)
  {
    paramType = getRawType(paramType, paramMap);
    if ((paramType instanceof Class)) {
      return (Class)paramType;
    }
    return Object.class;
  }
  
  public static Class<?> resolveTypeArgument(Class<?> paramClass1, Class<?> paramClass2)
  {
    paramClass1 = resolveTypeArguments(paramClass1, paramClass2);
    if (paramClass1 == null) {
      return null;
    }
    if (paramClass1.length != 1) {
      throw new IllegalArgumentException("Expected 1 type argument on generic interface [" + paramClass2.getName() + "] but found " + paramClass1.length);
    }
    return paramClass1[0];
  }
  
  public static Class<?>[] resolveTypeArguments(Class<?> paramClass1, Class<?> paramClass2)
  {
    return doResolveTypeArguments(paramClass1, paramClass1, paramClass2);
  }
}

/* Location:
 * Qualified Name:     org.springframework.core.GenericTypeResolver
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */