package org.springframework.util;

import java.util.Collection;
import java.util.Map;

public abstract class Assert
{
  public static void doesNotContain(String paramString1, String paramString2)
  {
    doesNotContain(paramString1, paramString2, "[Assertion failed] - this String argument must not contain the substring [" + paramString2 + "]");
  }
  
  public static void doesNotContain(String paramString1, String paramString2, String paramString3)
  {
    if ((StringUtils.hasLength(paramString1)) && (StringUtils.hasLength(paramString2)) && (paramString1.indexOf(paramString2) != -1)) {
      throw new IllegalArgumentException(paramString3);
    }
  }
  
  public static void hasLength(String paramString)
  {
    hasLength(paramString, "[Assertion failed] - this String argument must have length; it must not be null or empty");
  }
  
  public static void hasLength(String paramString1, String paramString2)
  {
    if (!StringUtils.hasLength(paramString1)) {
      throw new IllegalArgumentException(paramString2);
    }
  }
  
  public static void hasText(String paramString)
  {
    hasText(paramString, "[Assertion failed] - this String argument must have text; it must not be null, empty, or blank");
  }
  
  public static void hasText(String paramString1, String paramString2)
  {
    if (!StringUtils.hasText(paramString1)) {
      throw new IllegalArgumentException(paramString2);
    }
  }
  
  public static void isAssignable(Class<?> paramClass1, Class<?> paramClass2)
  {
    isAssignable(paramClass1, paramClass2, "");
  }
  
  public static void isAssignable(Class<?> paramClass1, Class<?> paramClass2, String paramString)
  {
    notNull(paramClass1, "Type to check against must not be null");
    if ((paramClass2 == null) || (!paramClass1.isAssignableFrom(paramClass2))) {
      throw new IllegalArgumentException(paramString + paramClass2 + " is not assignable to " + paramClass1);
    }
  }
  
  public static void isInstanceOf(Class<?> paramClass, Object paramObject)
  {
    isInstanceOf(paramClass, paramObject, "");
  }
  
  public static void isInstanceOf(Class<?> paramClass, Object paramObject, String paramString)
  {
    notNull(paramClass, "Type to check against must not be null");
    if (!paramClass.isInstance(paramObject))
    {
      paramString = new StringBuilder().append(paramString).append("Object of class [");
      if (paramObject != null) {}
      for (paramObject = paramObject.getClass().getName();; paramObject = "null") {
        throw new IllegalArgumentException((String)paramObject + "] must be an instance of " + paramClass);
      }
    }
  }
  
  public static void isNull(Object paramObject)
  {
    isNull(paramObject, "[Assertion failed] - the object argument must be null");
  }
  
  public static void isNull(Object paramObject, String paramString)
  {
    if (paramObject != null) {
      throw new IllegalArgumentException(paramString);
    }
  }
  
  public static void isTrue(boolean paramBoolean)
  {
    isTrue(paramBoolean, "[Assertion failed] - this expression must be true");
  }
  
  public static void isTrue(boolean paramBoolean, String paramString)
  {
    if (!paramBoolean) {
      throw new IllegalArgumentException(paramString);
    }
  }
  
  public static void noNullElements(Object[] paramArrayOfObject)
  {
    noNullElements(paramArrayOfObject, "[Assertion failed] - this array must not contain any null elements");
  }
  
  public static void noNullElements(Object[] paramArrayOfObject, String paramString)
  {
    if (paramArrayOfObject != null)
    {
      int i = 0;
      while (i < paramArrayOfObject.length)
      {
        if (paramArrayOfObject[i] == null) {
          throw new IllegalArgumentException(paramString);
        }
        i += 1;
      }
    }
  }
  
  public static void notEmpty(Collection<?> paramCollection)
  {
    notEmpty(paramCollection, "[Assertion failed] - this collection must not be empty: it must contain at least 1 element");
  }
  
  public static void notEmpty(Collection<?> paramCollection, String paramString)
  {
    if (CollectionUtils.isEmpty(paramCollection)) {
      throw new IllegalArgumentException(paramString);
    }
  }
  
  public static void notEmpty(Map<?, ?> paramMap)
  {
    notEmpty(paramMap, "[Assertion failed] - this map must not be empty; it must contain at least one entry");
  }
  
  public static void notEmpty(Map<?, ?> paramMap, String paramString)
  {
    if (CollectionUtils.isEmpty(paramMap)) {
      throw new IllegalArgumentException(paramString);
    }
  }
  
  public static void notEmpty(Object[] paramArrayOfObject)
  {
    notEmpty(paramArrayOfObject, "[Assertion failed] - this array must not be empty: it must contain at least 1 element");
  }
  
  public static void notEmpty(Object[] paramArrayOfObject, String paramString)
  {
    if (ObjectUtils.isEmpty(paramArrayOfObject)) {
      throw new IllegalArgumentException(paramString);
    }
  }
  
  public static void notNull(Object paramObject)
  {
    notNull(paramObject, "[Assertion failed] - this argument is required; it must not be null");
  }
  
  public static void notNull(Object paramObject, String paramString)
  {
    if (paramObject == null) {
      throw new IllegalArgumentException(paramString);
    }
  }
  
  public static void state(boolean paramBoolean)
  {
    state(paramBoolean, "[Assertion failed] - this state invariant must be true");
  }
  
  public static void state(boolean paramBoolean, String paramString)
  {
    if (!paramBoolean) {
      throw new IllegalStateException(paramString);
    }
  }
}

/* Location:
 * Qualified Name:     org.springframework.util.Assert
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */