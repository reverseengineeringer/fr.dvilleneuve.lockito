package org.springframework.util;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;

public abstract class TypeUtils
{
  private static boolean isAssignable(ParameterizedType paramParameterizedType1, ParameterizedType paramParameterizedType2)
  {
    if (paramParameterizedType1.equals(paramParameterizedType2)) {}
    for (;;)
    {
      return true;
      paramParameterizedType1 = paramParameterizedType1.getActualTypeArguments();
      paramParameterizedType2 = paramParameterizedType2.getActualTypeArguments();
      if (paramParameterizedType1.length != paramParameterizedType2.length) {
        return false;
      }
      int j = paramParameterizedType1.length;
      int i = 0;
      while (i < j)
      {
        Object localObject1 = paramParameterizedType1[i];
        Object localObject2 = paramParameterizedType2[i];
        if ((!localObject1.equals(localObject2)) && ((!(localObject1 instanceof WildcardType)) || (!isAssignable((WildcardType)localObject1, (Type)localObject2)))) {
          return false;
        }
        i += 1;
      }
    }
  }
  
  public static boolean isAssignable(Type paramType1, Type paramType2)
  {
    Assert.notNull(paramType1, "Left-hand side type must not be null");
    Assert.notNull(paramType2, "Right-hand side type must not be null");
    if ((paramType1.equals(paramType2)) || (paramType1.equals(Object.class))) {
      return true;
    }
    Object localObject1;
    Object localObject2;
    if ((paramType1 instanceof Class))
    {
      localObject1 = (Class)paramType1;
      if ((paramType2 instanceof Class)) {
        return ClassUtils.isAssignable((Class)localObject1, (Class)paramType2);
      }
      if ((paramType2 instanceof ParameterizedType))
      {
        localObject2 = ((ParameterizedType)paramType2).getRawType();
        if ((localObject2 instanceof Class)) {
          return ClassUtils.isAssignable((Class)localObject1, (Class)localObject2);
        }
      }
      else if ((((Class)localObject1).isArray()) && ((paramType2 instanceof GenericArrayType)))
      {
        paramType1 = ((GenericArrayType)paramType2).getGenericComponentType();
        return isAssignable(((Class)localObject1).getComponentType(), paramType1);
      }
    }
    if ((paramType1 instanceof ParameterizedType)) {
      if ((paramType2 instanceof Class))
      {
        localObject1 = ((ParameterizedType)paramType1).getRawType();
        if ((localObject1 instanceof Class)) {
          return ClassUtils.isAssignable((Class)localObject1, (Class)paramType2);
        }
      }
      else if ((paramType2 instanceof ParameterizedType))
      {
        return isAssignable((ParameterizedType)paramType1, (ParameterizedType)paramType2);
      }
    }
    if ((paramType1 instanceof GenericArrayType))
    {
      localObject1 = ((GenericArrayType)paramType1).getGenericComponentType();
      if ((paramType2 instanceof Class))
      {
        localObject2 = (Class)paramType2;
        if (((Class)localObject2).isArray()) {
          return isAssignable((Type)localObject1, ((Class)localObject2).getComponentType());
        }
      }
      else if ((paramType2 instanceof GenericArrayType))
      {
        return isAssignable((Type)localObject1, ((GenericArrayType)paramType2).getGenericComponentType());
      }
    }
    if ((paramType1 instanceof WildcardType)) {
      return isAssignable((WildcardType)paramType1, paramType2);
    }
    return false;
  }
  
  private static boolean isAssignable(WildcardType paramWildcardType, Type paramType)
  {
    Object localObject2 = paramWildcardType.getUpperBounds();
    Object localObject1 = localObject2;
    if (localObject2.length == 0)
    {
      localObject1 = new Type[1];
      localObject1[0] = Object.class;
    }
    localObject2 = paramWildcardType.getLowerBounds();
    paramWildcardType = (WildcardType)localObject2;
    if (localObject2.length == 0)
    {
      paramWildcardType = new Type[1];
      paramWildcardType[0] = null;
    }
    if ((paramType instanceof WildcardType))
    {
      Object localObject3 = (WildcardType)paramType;
      localObject2 = ((WildcardType)localObject3).getUpperBounds();
      paramType = (Type)localObject2;
      if (localObject2.length == 0)
      {
        paramType = new Type[1];
        paramType[0] = Object.class;
      }
      localObject3 = ((WildcardType)localObject3).getLowerBounds();
      localObject2 = localObject3;
      if (localObject3.length == 0)
      {
        localObject2 = new Type[1];
        localObject2[0] = null;
      }
      int k = localObject1.length;
      i = 0;
      int m;
      while (i < k)
      {
        localObject3 = localObject1[i];
        m = paramType.length;
        j = 0;
        while (j < m)
        {
          if (!isAssignableBound((Type)localObject3, paramType[j])) {
            return false;
          }
          j += 1;
        }
        m = localObject2.length;
        j = 0;
        while (j < m)
        {
          if (!isAssignableBound((Type)localObject3, localObject2[j])) {
            return false;
          }
          j += 1;
        }
        i += 1;
      }
      k = paramWildcardType.length;
      i = 0;
      while (i < k)
      {
        localObject1 = paramWildcardType[i];
        m = paramType.length;
        j = 0;
        while (j < m)
        {
          if (!isAssignableBound(paramType[j], (Type)localObject1)) {
            return false;
          }
          j += 1;
        }
        m = localObject2.length;
        j = 0;
        while (j < m)
        {
          if (!isAssignableBound(localObject2[j], (Type)localObject1)) {
            return false;
          }
          j += 1;
        }
        i += 1;
      }
    }
    int j = localObject1.length;
    int i = 0;
    while (i < j)
    {
      if (!isAssignableBound(localObject1[i], paramType)) {
        return false;
      }
      i += 1;
    }
    j = paramWildcardType.length;
    i = 0;
    while (i < j)
    {
      if (!isAssignableBound(paramType, paramWildcardType[i])) {
        return false;
      }
      i += 1;
    }
    return true;
  }
  
  public static boolean isAssignableBound(Type paramType1, Type paramType2)
  {
    if (paramType2 == null) {
      return true;
    }
    if (paramType1 == null) {
      return false;
    }
    return isAssignable(paramType1, paramType2);
  }
}

/* Location:
 * Qualified Name:     org.springframework.util.TypeUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */