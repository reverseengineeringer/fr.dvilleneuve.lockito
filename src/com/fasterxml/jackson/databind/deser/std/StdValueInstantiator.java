package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.CreatorProperty;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.introspect.AnnotatedWithParams;
import java.io.IOException;
import java.io.Serializable;

public class StdValueInstantiator
  extends ValueInstantiator
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  protected final boolean _cfgEmptyStringsAsObjects;
  protected CreatorProperty[] _constructorArguments;
  protected AnnotatedWithParams _defaultCreator;
  protected CreatorProperty[] _delegateArguments;
  protected AnnotatedWithParams _delegateCreator;
  protected JavaType _delegateType;
  protected AnnotatedWithParams _fromBooleanCreator;
  protected AnnotatedWithParams _fromDoubleCreator;
  protected AnnotatedWithParams _fromIntCreator;
  protected AnnotatedWithParams _fromLongCreator;
  protected AnnotatedWithParams _fromStringCreator;
  protected AnnotatedParameter _incompleteParameter;
  protected final String _valueTypeDesc;
  protected AnnotatedWithParams _withArgsCreator;
  
  public StdValueInstantiator(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType)
  {
    boolean bool;
    if (paramDeserializationConfig == null)
    {
      bool = false;
      _cfgEmptyStringsAsObjects = bool;
      if (paramJavaType != null) {
        break label39;
      }
    }
    label39:
    for (paramDeserializationConfig = "UNKNOWN TYPE";; paramDeserializationConfig = paramJavaType.toString())
    {
      _valueTypeDesc = paramDeserializationConfig;
      return;
      bool = paramDeserializationConfig.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
      break;
    }
  }
  
  public StdValueInstantiator(DeserializationConfig paramDeserializationConfig, Class<?> paramClass)
  {
    boolean bool;
    if (paramDeserializationConfig == null)
    {
      bool = false;
      _cfgEmptyStringsAsObjects = bool;
      if (paramClass != null) {
        break label39;
      }
    }
    label39:
    for (paramDeserializationConfig = "UNKNOWN TYPE";; paramDeserializationConfig = paramClass.getName())
    {
      _valueTypeDesc = paramDeserializationConfig;
      return;
      bool = paramDeserializationConfig.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
      break;
    }
  }
  
  protected StdValueInstantiator(StdValueInstantiator paramStdValueInstantiator)
  {
    _cfgEmptyStringsAsObjects = _cfgEmptyStringsAsObjects;
    _valueTypeDesc = _valueTypeDesc;
    _defaultCreator = _defaultCreator;
    _constructorArguments = _constructorArguments;
    _withArgsCreator = _withArgsCreator;
    _delegateType = _delegateType;
    _delegateCreator = _delegateCreator;
    _delegateArguments = _delegateArguments;
    _fromStringCreator = _fromStringCreator;
    _fromIntCreator = _fromIntCreator;
    _fromLongCreator = _fromLongCreator;
    _fromDoubleCreator = _fromDoubleCreator;
    _fromBooleanCreator = _fromBooleanCreator;
  }
  
  protected Object _createFromStringFallbacks(DeserializationContext paramDeserializationContext, String paramString)
    throws IOException, JsonProcessingException
  {
    if (_fromBooleanCreator != null)
    {
      String str = paramString.trim();
      if ("true".equals(str)) {
        return createFromBoolean(paramDeserializationContext, true);
      }
      if ("false".equals(str)) {
        return createFromBoolean(paramDeserializationContext, false);
      }
    }
    if ((_cfgEmptyStringsAsObjects) && (paramString.length() == 0)) {
      return null;
    }
    throw new JsonMappingException("Can not instantiate value of type " + getValueTypeDesc() + " from String value; no single-String constructor/factory method");
  }
  
  public boolean canCreateFromBoolean()
  {
    return _fromBooleanCreator != null;
  }
  
  public boolean canCreateFromDouble()
  {
    return _fromDoubleCreator != null;
  }
  
  public boolean canCreateFromInt()
  {
    return _fromIntCreator != null;
  }
  
  public boolean canCreateFromLong()
  {
    return _fromLongCreator != null;
  }
  
  public boolean canCreateFromObjectWith()
  {
    return _withArgsCreator != null;
  }
  
  public boolean canCreateFromString()
  {
    return _fromStringCreator != null;
  }
  
  public boolean canCreateUsingDefault()
  {
    return _defaultCreator != null;
  }
  
  public boolean canCreateUsingDelegate()
  {
    return _delegateType != null;
  }
  
  public void configureFromBooleanCreator(AnnotatedWithParams paramAnnotatedWithParams)
  {
    _fromBooleanCreator = paramAnnotatedWithParams;
  }
  
  public void configureFromDoubleCreator(AnnotatedWithParams paramAnnotatedWithParams)
  {
    _fromDoubleCreator = paramAnnotatedWithParams;
  }
  
  public void configureFromIntCreator(AnnotatedWithParams paramAnnotatedWithParams)
  {
    _fromIntCreator = paramAnnotatedWithParams;
  }
  
  public void configureFromLongCreator(AnnotatedWithParams paramAnnotatedWithParams)
  {
    _fromLongCreator = paramAnnotatedWithParams;
  }
  
  public void configureFromObjectSettings(AnnotatedWithParams paramAnnotatedWithParams1, AnnotatedWithParams paramAnnotatedWithParams2, JavaType paramJavaType, CreatorProperty[] paramArrayOfCreatorProperty1, AnnotatedWithParams paramAnnotatedWithParams3, CreatorProperty[] paramArrayOfCreatorProperty2)
  {
    _defaultCreator = paramAnnotatedWithParams1;
    _delegateCreator = paramAnnotatedWithParams2;
    _delegateType = paramJavaType;
    _delegateArguments = paramArrayOfCreatorProperty1;
    _withArgsCreator = paramAnnotatedWithParams3;
    _constructorArguments = paramArrayOfCreatorProperty2;
  }
  
  public void configureFromStringCreator(AnnotatedWithParams paramAnnotatedWithParams)
  {
    _fromStringCreator = paramAnnotatedWithParams;
  }
  
  public void configureIncompleteParameter(AnnotatedParameter paramAnnotatedParameter)
  {
    _incompleteParameter = paramAnnotatedParameter;
  }
  
  public Object createFromBoolean(DeserializationContext paramDeserializationContext, boolean paramBoolean)
    throws IOException, JsonProcessingException
  {
    try
    {
      if (_fromBooleanCreator != null)
      {
        paramDeserializationContext = _fromBooleanCreator.call1(Boolean.valueOf(paramBoolean));
        return paramDeserializationContext;
      }
    }
    catch (Exception paramDeserializationContext)
    {
      throw wrapException(paramDeserializationContext);
    }
    catch (ExceptionInInitializerError paramDeserializationContext)
    {
      throw wrapException(paramDeserializationContext);
    }
    throw new JsonMappingException("Can not instantiate value of type " + getValueTypeDesc() + " from Boolean value; no single-boolean/Boolean-arg constructor/factory method");
  }
  
  public Object createFromDouble(DeserializationContext paramDeserializationContext, double paramDouble)
    throws IOException, JsonProcessingException
  {
    try
    {
      if (_fromDoubleCreator != null)
      {
        paramDeserializationContext = _fromDoubleCreator.call1(Double.valueOf(paramDouble));
        return paramDeserializationContext;
      }
    }
    catch (Exception paramDeserializationContext)
    {
      throw wrapException(paramDeserializationContext);
    }
    catch (ExceptionInInitializerError paramDeserializationContext)
    {
      throw wrapException(paramDeserializationContext);
    }
    throw new JsonMappingException("Can not instantiate value of type " + getValueTypeDesc() + " from Floating-point number; no one-double/Double-arg constructor/factory method");
  }
  
  public Object createFromInt(DeserializationContext paramDeserializationContext, int paramInt)
    throws IOException, JsonProcessingException
  {
    try
    {
      if (_fromIntCreator != null) {
        return _fromIntCreator.call1(Integer.valueOf(paramInt));
      }
      if (_fromLongCreator != null)
      {
        paramDeserializationContext = _fromLongCreator.call1(Long.valueOf(paramInt));
        return paramDeserializationContext;
      }
    }
    catch (Exception paramDeserializationContext)
    {
      throw wrapException(paramDeserializationContext);
    }
    catch (ExceptionInInitializerError paramDeserializationContext)
    {
      throw wrapException(paramDeserializationContext);
    }
    throw new JsonMappingException("Can not instantiate value of type " + getValueTypeDesc() + " from Integral number; no single-int-arg constructor/factory method");
  }
  
  public Object createFromLong(DeserializationContext paramDeserializationContext, long paramLong)
    throws IOException, JsonProcessingException
  {
    try
    {
      if (_fromLongCreator != null)
      {
        paramDeserializationContext = _fromLongCreator.call1(Long.valueOf(paramLong));
        return paramDeserializationContext;
      }
    }
    catch (Exception paramDeserializationContext)
    {
      throw wrapException(paramDeserializationContext);
    }
    catch (ExceptionInInitializerError paramDeserializationContext)
    {
      throw wrapException(paramDeserializationContext);
    }
    throw new JsonMappingException("Can not instantiate value of type " + getValueTypeDesc() + " from Long integral number; no single-long-arg constructor/factory method");
  }
  
  public Object createFromObjectWith(DeserializationContext paramDeserializationContext, Object[] paramArrayOfObject)
    throws IOException, JsonProcessingException
  {
    if (_withArgsCreator == null) {
      throw new IllegalStateException("No with-args constructor for " + getValueTypeDesc());
    }
    try
    {
      paramDeserializationContext = _withArgsCreator.call(paramArrayOfObject);
      return paramDeserializationContext;
    }
    catch (ExceptionInInitializerError paramDeserializationContext)
    {
      throw wrapException(paramDeserializationContext);
    }
    catch (Exception paramDeserializationContext)
    {
      throw wrapException(paramDeserializationContext);
    }
  }
  
  public Object createFromString(DeserializationContext paramDeserializationContext, String paramString)
    throws IOException, JsonProcessingException
  {
    if (_fromStringCreator != null) {
      try
      {
        paramDeserializationContext = _fromStringCreator.call1(paramString);
        return paramDeserializationContext;
      }
      catch (Exception paramDeserializationContext)
      {
        throw wrapException(paramDeserializationContext);
      }
      catch (ExceptionInInitializerError paramDeserializationContext)
      {
        throw wrapException(paramDeserializationContext);
      }
    }
    return _createFromStringFallbacks(paramDeserializationContext, paramString);
  }
  
  public Object createUsingDefault(DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    if (_defaultCreator == null) {
      throw new IllegalStateException("No default constructor for " + getValueTypeDesc());
    }
    try
    {
      paramDeserializationContext = _defaultCreator.call();
      return paramDeserializationContext;
    }
    catch (ExceptionInInitializerError paramDeserializationContext)
    {
      throw wrapException(paramDeserializationContext);
    }
    catch (Exception paramDeserializationContext)
    {
      throw wrapException(paramDeserializationContext);
    }
  }
  
  public Object createUsingDelegate(DeserializationContext paramDeserializationContext, Object paramObject)
    throws IOException, JsonProcessingException
  {
    if (_delegateCreator == null) {
      throw new IllegalStateException("No delegate constructor for " + getValueTypeDesc());
    }
    for (;;)
    {
      int i;
      try
      {
        if (_delegateArguments == null) {
          return _delegateCreator.call1(paramObject);
        }
        int j = _delegateArguments.length;
        arrayOfObject = new Object[j];
        i = 0;
        if (i < j)
        {
          CreatorProperty localCreatorProperty = _delegateArguments[i];
          if (localCreatorProperty == null) {
            arrayOfObject[i] = paramObject;
          } else {
            arrayOfObject[i] = paramDeserializationContext.findInjectableValue(localCreatorProperty.getInjectableValueId(), localCreatorProperty, null);
          }
        }
      }
      catch (ExceptionInInitializerError paramDeserializationContext)
      {
        Object[] arrayOfObject;
        throw wrapException(paramDeserializationContext);
        paramDeserializationContext = _delegateCreator.call(arrayOfObject);
        return paramDeserializationContext;
      }
      catch (Exception paramDeserializationContext)
      {
        throw wrapException(paramDeserializationContext);
      }
      i += 1;
    }
  }
  
  public AnnotatedWithParams getDefaultCreator()
  {
    return _defaultCreator;
  }
  
  public AnnotatedWithParams getDelegateCreator()
  {
    return _delegateCreator;
  }
  
  public JavaType getDelegateType(DeserializationConfig paramDeserializationConfig)
  {
    return _delegateType;
  }
  
  public SettableBeanProperty[] getFromObjectArguments(DeserializationConfig paramDeserializationConfig)
  {
    return _constructorArguments;
  }
  
  public AnnotatedParameter getIncompleteParameter()
  {
    return _incompleteParameter;
  }
  
  public String getValueTypeDesc()
  {
    return _valueTypeDesc;
  }
  
  public AnnotatedWithParams getWithArgsCreator()
  {
    return _withArgsCreator;
  }
  
  protected JsonMappingException wrapException(Throwable paramThrowable)
  {
    while (paramThrowable.getCause() != null) {
      paramThrowable = paramThrowable.getCause();
    }
    if ((paramThrowable instanceof JsonMappingException)) {
      return (JsonMappingException)paramThrowable;
    }
    return new JsonMappingException("Instantiation of " + getValueTypeDesc() + " value failed: " + paramThrowable.getMessage(), paramThrowable);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.std.StdValueInstantiator
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */