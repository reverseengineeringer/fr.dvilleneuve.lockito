package org.springframework.core;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Collection;
import java.util.Map;

public abstract class GenericCollectionTypeResolver
{
  private static Class<?> extractType(Type paramType, Class<?> paramClass, int paramInt1, Map<TypeVariable<?>, Type> paramMap, Map<Integer, Integer> paramMap1, int paramInt2, int paramInt3)
  {
    Type localType1 = paramType;
    Type localType2 = localType1;
    if ((paramType instanceof TypeVariable))
    {
      localType2 = localType1;
      if (paramMap != null)
      {
        paramType = (Type)paramMap.get((TypeVariable)paramType);
        localType2 = localType1;
        if (paramType != null) {
          localType2 = paramType;
        }
      }
    }
    if ((localType2 instanceof ParameterizedType)) {
      return extractTypeFromParameterizedType((ParameterizedType)localType2, paramClass, paramInt1, paramMap, paramMap1, paramInt2, paramInt3);
    }
    if ((localType2 instanceof Class)) {
      return extractTypeFromClass((Class)localType2, paramClass, paramInt1, paramMap, paramMap1, paramInt2, paramInt3);
    }
    if ((localType2 instanceof GenericArrayType)) {
      return extractType(((GenericArrayType)localType2).getGenericComponentType(), paramClass, paramInt1, paramMap, paramMap1, paramInt2, paramInt3 + 1);
    }
    return null;
  }
  
  private static Class<?> extractTypeFromClass(Class<?> paramClass1, Class<?> paramClass2, int paramInt)
  {
    return extractTypeFromClass(paramClass1, paramClass2, paramInt, null, null, 1, 1);
  }
  
  private static Class<?> extractTypeFromClass(Class<?> paramClass1, Class<?> paramClass2, int paramInt1, Map<TypeVariable<?>, Type> paramMap, Map<Integer, Integer> paramMap1, int paramInt2, int paramInt3)
  {
    if (paramClass1.getName().startsWith("java.util.")) {
      return null;
    }
    if ((paramClass1.getSuperclass() != null) && (isIntrospectionCandidate(paramClass1.getSuperclass()))) {
      return extractType(paramClass1.getGenericSuperclass(), paramClass2, paramInt1, paramMap, paramMap1, paramInt2, paramInt3);
    }
    Type[] arrayOfType = paramClass1.getGenericInterfaces();
    if (arrayOfType != null)
    {
      int j = arrayOfType.length;
      int i = 0;
      while (i < j)
      {
        Type localType = arrayOfType[i];
        paramClass1 = localType;
        if ((localType instanceof ParameterizedType)) {
          paramClass1 = ((ParameterizedType)localType).getRawType();
        }
        if (((paramClass1 instanceof Class)) && (isIntrospectionCandidate((Class)paramClass1))) {
          return extractType(localType, paramClass2, paramInt1, paramMap, paramMap1, paramInt2, paramInt3);
        }
        i += 1;
      }
    }
    return null;
  }
  
  private static Class<?> extractTypeFromParameterizedType(ParameterizedType paramParameterizedType, Class<?> paramClass, int paramInt1, Map<TypeVariable<?>, Type> paramMap, Map<Integer, Integer> paramMap1, int paramInt2, int paramInt3)
  {
    if (!(paramParameterizedType.getRawType() instanceof Class)) {
      paramParameterizedType = null;
    }
    Type[] arrayOfType;
    label103:
    do
    {
      return paramParameterizedType;
      Class localClass = (Class)paramParameterizedType.getRawType();
      arrayOfType = paramParameterizedType.getActualTypeArguments();
      if (paramInt2 - paramInt3 > 0)
      {
        int i = paramInt3 + 1;
        if (paramMap1 != null)
        {
          paramParameterizedType = (Integer)paramMap1.get(Integer.valueOf(i));
          if (paramParameterizedType == null) {
            break label103;
          }
        }
        for (paramInt3 = paramParameterizedType.intValue();; paramInt3 = arrayOfType.length - 1)
        {
          return extractType(arrayOfType[paramInt3], paramClass, paramInt1, paramMap, paramMap1, paramInt2, i);
          paramParameterizedType = null;
          break;
        }
      }
      if ((paramClass != null) && (!paramClass.isAssignableFrom(localClass))) {
        return null;
      }
      paramClass = extractTypeFromClass(localClass, paramClass, paramInt1, paramMap, paramMap1, paramInt2, paramInt3);
      paramParameterizedType = paramClass;
    } while (paramClass != null);
    if ((arrayOfType == null) || (paramInt1 >= arrayOfType.length)) {
      return null;
    }
    paramParameterizedType = arrayOfType[paramInt1];
    paramClass = paramParameterizedType;
    if ((paramParameterizedType instanceof TypeVariable))
    {
      paramClass = paramParameterizedType;
      if (paramMap != null)
      {
        paramMap = (Type)paramMap.get((TypeVariable)paramParameterizedType);
        paramClass = paramParameterizedType;
        if (paramMap != null) {
          paramClass = paramMap;
        }
      }
    }
    paramParameterizedType = paramClass;
    if ((paramClass instanceof WildcardType))
    {
      paramParameterizedType = (WildcardType)paramClass;
      paramMap = paramParameterizedType.getUpperBounds();
      if ((paramMap == null) || (paramMap.length <= 0) || (Object.class.equals(paramMap[0]))) {
        break label305;
      }
      paramParameterizedType = paramMap[0];
    }
    for (;;)
    {
      paramClass = paramParameterizedType;
      if ((paramParameterizedType instanceof ParameterizedType)) {
        paramClass = ((ParameterizedType)paramParameterizedType).getRawType();
      }
      if (!(paramClass instanceof GenericArrayType)) {
        break;
      }
      paramParameterizedType = ((GenericArrayType)paramClass).getGenericComponentType();
      if (!(paramParameterizedType instanceof Class)) {
        break label357;
      }
      return Array.newInstance((Class)paramParameterizedType, 0).getClass();
      label305:
      paramMap = paramParameterizedType.getLowerBounds();
      paramParameterizedType = paramClass;
      if (paramMap != null)
      {
        paramParameterizedType = paramClass;
        if (paramMap.length > 0)
        {
          paramParameterizedType = paramClass;
          if (!Object.class.equals(paramMap[0])) {
            paramParameterizedType = paramMap[0];
          }
        }
      }
    }
    if ((paramClass instanceof Class)) {
      return (Class)paramClass;
    }
    label357:
    return null;
  }
  
  public static Class<?> getCollectionFieldType(Field paramField)
  {
    return getGenericFieldType(paramField, Collection.class, 0, null, 1);
  }
  
  public static Class<?> getCollectionFieldType(Field paramField, int paramInt)
  {
    return getGenericFieldType(paramField, Collection.class, 0, null, paramInt);
  }
  
  public static Class<?> getCollectionFieldType(Field paramField, int paramInt, Map<Integer, Integer> paramMap)
  {
    return getGenericFieldType(paramField, Collection.class, 0, paramMap, paramInt);
  }
  
  public static Class<?> getCollectionParameterType(MethodParameter paramMethodParameter)
  {
    return getGenericParameterType(paramMethodParameter, Collection.class, 0);
  }
  
  public static Class<?> getCollectionReturnType(Method paramMethod)
  {
    return getGenericReturnType(paramMethod, Collection.class, 0, 1);
  }
  
  public static Class<?> getCollectionReturnType(Method paramMethod, int paramInt)
  {
    return getGenericReturnType(paramMethod, Collection.class, 0, paramInt);
  }
  
  public static Class<?> getCollectionType(Class<? extends Collection> paramClass)
  {
    return extractTypeFromClass(paramClass, Collection.class, 0);
  }
  
  private static Class<?> getGenericFieldType(Field paramField, Class<?> paramClass, int paramInt1, Map<Integer, Integer> paramMap, int paramInt2)
  {
    return extractType(paramField.getGenericType(), paramClass, paramInt1, null, paramMap, paramInt2, 1);
  }
  
  private static Class<?> getGenericParameterType(MethodParameter paramMethodParameter, Class<?> paramClass, int paramInt)
  {
    return extractType(GenericTypeResolver.getTargetType(paramMethodParameter), paramClass, paramInt, typeVariableMap, typeIndexesPerLevel, paramMethodParameter.getNestingLevel(), 1);
  }
  
  private static Class<?> getGenericReturnType(Method paramMethod, Class<?> paramClass, int paramInt1, int paramInt2)
  {
    return extractType(paramMethod.getGenericReturnType(), paramClass, paramInt1, null, null, paramInt2, 1);
  }
  
  public static Class<?> getMapKeyFieldType(Field paramField)
  {
    return getGenericFieldType(paramField, Map.class, 0, null, 1);
  }
  
  public static Class<?> getMapKeyFieldType(Field paramField, int paramInt)
  {
    return getGenericFieldType(paramField, Map.class, 0, null, paramInt);
  }
  
  public static Class<?> getMapKeyFieldType(Field paramField, int paramInt, Map<Integer, Integer> paramMap)
  {
    return getGenericFieldType(paramField, Map.class, 0, paramMap, paramInt);
  }
  
  public static Class<?> getMapKeyParameterType(MethodParameter paramMethodParameter)
  {
    return getGenericParameterType(paramMethodParameter, Map.class, 0);
  }
  
  public static Class<?> getMapKeyReturnType(Method paramMethod)
  {
    return getGenericReturnType(paramMethod, Map.class, 0, 1);
  }
  
  public static Class<?> getMapKeyReturnType(Method paramMethod, int paramInt)
  {
    return getGenericReturnType(paramMethod, Map.class, 0, paramInt);
  }
  
  public static Class<?> getMapKeyType(Class<? extends Map> paramClass)
  {
    return extractTypeFromClass(paramClass, Map.class, 0);
  }
  
  public static Class<?> getMapValueFieldType(Field paramField)
  {
    return getGenericFieldType(paramField, Map.class, 1, null, 1);
  }
  
  public static Class<?> getMapValueFieldType(Field paramField, int paramInt)
  {
    return getGenericFieldType(paramField, Map.class, 1, null, paramInt);
  }
  
  public static Class<?> getMapValueFieldType(Field paramField, int paramInt, Map<Integer, Integer> paramMap)
  {
    return getGenericFieldType(paramField, Map.class, 1, paramMap, paramInt);
  }
  
  public static Class<?> getMapValueParameterType(MethodParameter paramMethodParameter)
  {
    return getGenericParameterType(paramMethodParameter, Map.class, 1);
  }
  
  public static Class<?> getMapValueReturnType(Method paramMethod)
  {
    return getGenericReturnType(paramMethod, Map.class, 1, 1);
  }
  
  public static Class<?> getMapValueReturnType(Method paramMethod, int paramInt)
  {
    return getGenericReturnType(paramMethod, Map.class, 1, paramInt);
  }
  
  public static Class<?> getMapValueType(Class<? extends Map> paramClass)
  {
    return extractTypeFromClass(paramClass, Map.class, 1);
  }
  
  private static boolean isIntrospectionCandidate(Class<?> paramClass)
  {
    return (Collection.class.isAssignableFrom(paramClass)) || (Map.class.isAssignableFrom(paramClass));
  }
}

/* Location:
 * Qualified Name:     org.springframework.core.GenericCollectionTypeResolver
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */