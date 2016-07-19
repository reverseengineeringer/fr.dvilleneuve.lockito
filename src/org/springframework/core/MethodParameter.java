package org.springframework.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.Map;
import org.springframework.util.Assert;

public class MethodParameter
{
  private final Constructor<?> constructor;
  private Type genericParameterType;
  private int hash = 0;
  private final Method method;
  private int nestingLevel = 1;
  private Annotation[] parameterAnnotations;
  private final int parameterIndex;
  private String parameterName;
  private ParameterNameDiscoverer parameterNameDiscoverer;
  private Class<?> parameterType;
  Map<Integer, Integer> typeIndexesPerLevel;
  Map<TypeVariable<?>, Type> typeVariableMap;
  
  public MethodParameter(Constructor<?> paramConstructor, int paramInt)
  {
    this(paramConstructor, paramInt, 1);
  }
  
  public MethodParameter(Constructor<?> paramConstructor, int paramInt1, int paramInt2)
  {
    Assert.notNull(paramConstructor, "Constructor must not be null");
    constructor = paramConstructor;
    parameterIndex = paramInt1;
    nestingLevel = paramInt2;
    method = null;
  }
  
  public MethodParameter(Method paramMethod, int paramInt)
  {
    this(paramMethod, paramInt, 1);
  }
  
  public MethodParameter(Method paramMethod, int paramInt1, int paramInt2)
  {
    Assert.notNull(paramMethod, "Method must not be null");
    method = paramMethod;
    parameterIndex = paramInt1;
    nestingLevel = paramInt2;
    constructor = null;
  }
  
  public MethodParameter(MethodParameter paramMethodParameter)
  {
    Assert.notNull(paramMethodParameter, "Original must not be null");
    method = method;
    constructor = constructor;
    parameterIndex = parameterIndex;
    parameterType = parameterType;
    genericParameterType = genericParameterType;
    parameterAnnotations = parameterAnnotations;
    parameterNameDiscoverer = parameterNameDiscoverer;
    parameterName = parameterName;
    nestingLevel = nestingLevel;
    typeIndexesPerLevel = typeIndexesPerLevel;
    typeVariableMap = typeVariableMap;
    hash = hash;
  }
  
  public static MethodParameter forMethodOrConstructor(Object paramObject, int paramInt)
  {
    if ((paramObject instanceof Method)) {
      return new MethodParameter((Method)paramObject, paramInt);
    }
    if ((paramObject instanceof Constructor)) {
      return new MethodParameter((Constructor)paramObject, paramInt);
    }
    throw new IllegalArgumentException("Given object [" + paramObject + "] is neither a Method nor a Constructor");
  }
  
  private AnnotatedElement getAnnotatedElement()
  {
    if (method != null) {
      return method;
    }
    return constructor;
  }
  
  private Member getMember()
  {
    if (method != null) {}
    for (Object localObject = method;; localObject = constructor) {
      return (Member)localObject;
    }
  }
  
  private Map<Integer, Integer> getTypeIndexesPerLevel()
  {
    if (typeIndexesPerLevel == null) {
      typeIndexesPerLevel = new HashMap(4);
    }
    return typeIndexesPerLevel;
  }
  
  public void decreaseNestingLevel()
  {
    getTypeIndexesPerLevel().remove(Integer.valueOf(nestingLevel));
    nestingLevel -= 1;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if ((paramObject == null) || (!(paramObject instanceof MethodParameter))) {
        break;
      }
      paramObject = (MethodParameter)paramObject;
      if (parameterIndex != parameterIndex) {
        return false;
      }
    } while (getMember().equals(((MethodParameter)paramObject).getMember()));
    return false;
    return false;
  }
  
  public Constructor<?> getConstructor()
  {
    return constructor;
  }
  
  public Class<?> getDeclaringClass()
  {
    return getMember().getDeclaringClass();
  }
  
  public Type getGenericParameterType()
  {
    if (genericParameterType == null)
    {
      if (parameterIndex >= 0) {
        break label44;
      }
      if (method == null) {
        break label39;
      }
    }
    label39:
    for (Type localType = method.getGenericReturnType();; localType = null)
    {
      genericParameterType = localType;
      return genericParameterType;
    }
    label44:
    if (method != null) {}
    for (localType = method.getGenericParameterTypes()[parameterIndex];; localType = constructor.getGenericParameterTypes()[parameterIndex])
    {
      genericParameterType = localType;
      break;
    }
  }
  
  public Method getMethod()
  {
    return method;
  }
  
  public <T extends Annotation> T getMethodAnnotation(Class<T> paramClass)
  {
    return getAnnotatedElement().getAnnotation(paramClass);
  }
  
  public Annotation[] getMethodAnnotations()
  {
    return getAnnotatedElement().getAnnotations();
  }
  
  public int getNestingLevel()
  {
    return nestingLevel;
  }
  
  public <T extends Annotation> T getParameterAnnotation(Class<T> paramClass)
  {
    Annotation[] arrayOfAnnotation = getParameterAnnotations();
    int j = arrayOfAnnotation.length;
    int i = 0;
    while (i < j)
    {
      Annotation localAnnotation = arrayOfAnnotation[i];
      if (paramClass.isInstance(localAnnotation)) {
        return localAnnotation;
      }
      i += 1;
    }
    return null;
  }
  
  public Annotation[] getParameterAnnotations()
  {
    Annotation[][] arrayOfAnnotation;
    if (parameterAnnotations == null)
    {
      if (method == null) {
        break label53;
      }
      arrayOfAnnotation = method.getParameterAnnotations();
      if ((parameterIndex < 0) || (parameterIndex >= arrayOfAnnotation.length)) {
        break label64;
      }
    }
    label53:
    label64:
    for (parameterAnnotations = arrayOfAnnotation[parameterIndex];; parameterAnnotations = new Annotation[0])
    {
      return parameterAnnotations;
      arrayOfAnnotation = constructor.getParameterAnnotations();
      break;
    }
  }
  
  public int getParameterIndex()
  {
    return parameterIndex;
  }
  
  public String getParameterName()
  {
    if (parameterNameDiscoverer != null) {
      if (method == null) {
        break label52;
      }
    }
    label52:
    for (String[] arrayOfString = parameterNameDiscoverer.getParameterNames(method);; arrayOfString = parameterNameDiscoverer.getParameterNames(constructor))
    {
      if (arrayOfString != null) {
        parameterName = arrayOfString[parameterIndex];
      }
      parameterNameDiscoverer = null;
      return parameterName;
    }
  }
  
  public Class<?> getParameterType()
  {
    if (parameterType == null)
    {
      if (parameterIndex >= 0) {
        break label44;
      }
      if (method == null) {
        break label39;
      }
    }
    label39:
    for (Class localClass = method.getReturnType();; localClass = null)
    {
      parameterType = localClass;
      return parameterType;
    }
    label44:
    if (method != null) {}
    for (localClass = method.getParameterTypes()[parameterIndex];; localClass = constructor.getParameterTypes()[parameterIndex])
    {
      parameterType = localClass;
      break;
    }
  }
  
  public Integer getTypeIndexForCurrentLevel()
  {
    return getTypeIndexForLevel(nestingLevel);
  }
  
  public Integer getTypeIndexForLevel(int paramInt)
  {
    return (Integer)getTypeIndexesPerLevel().get(Integer.valueOf(paramInt));
  }
  
  public <T extends Annotation> boolean hasParameterAnnotation(Class<T> paramClass)
  {
    return getParameterAnnotation(paramClass) != null;
  }
  
  public boolean hasParameterAnnotations()
  {
    return getParameterAnnotations().length != 0;
  }
  
  public int hashCode()
  {
    int j = hash;
    int i = j;
    if (j == 0)
    {
      i = getMember().hashCode() * 31 + parameterIndex;
      hash = i;
    }
    return i;
  }
  
  public void increaseNestingLevel()
  {
    nestingLevel += 1;
  }
  
  public void initParameterNameDiscovery(ParameterNameDiscoverer paramParameterNameDiscoverer)
  {
    parameterNameDiscoverer = paramParameterNameDiscoverer;
  }
  
  void setParameterType(Class<?> paramClass)
  {
    parameterType = paramClass;
  }
  
  public void setTypeIndexForCurrentLevel(int paramInt)
  {
    getTypeIndexesPerLevel().put(Integer.valueOf(nestingLevel), Integer.valueOf(paramInt));
  }
}

/* Location:
 * Qualified Name:     org.springframework.core.MethodParameter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */