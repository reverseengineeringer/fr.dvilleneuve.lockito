package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Method;

public class SettableAnyProperty
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  protected final BeanProperty _property;
  protected final transient Method _setter;
  protected final JavaType _type;
  protected JsonDeserializer<Object> _valueDeserializer;
  protected final TypeDeserializer _valueTypeDeserializer;
  
  @Deprecated
  public SettableAnyProperty(BeanProperty paramBeanProperty, AnnotatedMethod paramAnnotatedMethod, JavaType paramJavaType, JsonDeserializer<Object> paramJsonDeserializer)
  {
    this(paramBeanProperty, paramAnnotatedMethod, paramJavaType, paramJsonDeserializer, null);
  }
  
  public SettableAnyProperty(BeanProperty paramBeanProperty, AnnotatedMethod paramAnnotatedMethod, JavaType paramJavaType, JsonDeserializer<Object> paramJsonDeserializer, TypeDeserializer paramTypeDeserializer)
  {
    this(paramBeanProperty, paramAnnotatedMethod.getAnnotated(), paramJavaType, paramJsonDeserializer, paramTypeDeserializer);
  }
  
  @Deprecated
  public SettableAnyProperty(BeanProperty paramBeanProperty, Method paramMethod, JavaType paramJavaType, JsonDeserializer<Object> paramJsonDeserializer)
  {
    this(paramBeanProperty, paramMethod, paramJavaType, paramJsonDeserializer, null);
  }
  
  public SettableAnyProperty(BeanProperty paramBeanProperty, Method paramMethod, JavaType paramJavaType, JsonDeserializer<Object> paramJsonDeserializer, TypeDeserializer paramTypeDeserializer)
  {
    _property = paramBeanProperty;
    _type = paramJavaType;
    _setter = paramMethod;
    _valueDeserializer = paramJsonDeserializer;
    _valueTypeDeserializer = paramTypeDeserializer;
  }
  
  private String getClassName()
  {
    return _setter.getDeclaringClass().getName();
  }
  
  protected void _throwAsIOE(Exception paramException, String paramString, Object paramObject)
    throws IOException
  {
    if ((paramException instanceof IllegalArgumentException))
    {
      if (paramObject == null)
      {
        paramObject = "[NULL]";
        paramString = new StringBuilder("Problem deserializing \"any\" property '").append(paramString);
        paramString.append("' of class " + getClassName() + " (expected type: ").append(_type);
        paramString.append("; actual type: ").append((String)paramObject).append(")");
        paramObject = paramException.getMessage();
        if (paramObject == null) {
          break label128;
        }
        paramString.append(", problem: ").append((String)paramObject);
      }
      for (;;)
      {
        throw new JsonMappingException(paramString.toString(), null, paramException);
        paramObject = paramObject.getClass().getName();
        break;
        label128:
        paramString.append(" (no error message provided)");
      }
    }
    if ((paramException instanceof IOException)) {
      throw ((IOException)paramException);
    }
    paramString = paramException;
    if ((paramException instanceof RuntimeException)) {
      throw ((RuntimeException)paramException);
    }
    while (paramString.getCause() != null) {
      paramString = paramString.getCause();
    }
    throw new JsonMappingException(paramString.getMessage(), null, paramString);
  }
  
  public Object deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    if (paramJsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
      return null;
    }
    if (_valueTypeDeserializer != null) {
      return _valueDeserializer.deserializeWithType(paramJsonParser, paramDeserializationContext, _valueTypeDeserializer);
    }
    return _valueDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
  }
  
  public final void deserializeAndSet(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject, String paramString)
    throws IOException, JsonProcessingException
  {
    set(paramObject, paramString, deserialize(paramJsonParser, paramDeserializationContext));
  }
  
  public BeanProperty getProperty()
  {
    return _property;
  }
  
  public JavaType getType()
  {
    return _type;
  }
  
  public boolean hasValueDeserializer()
  {
    return _valueDeserializer != null;
  }
  
  public void set(Object paramObject1, String paramString, Object paramObject2)
    throws IOException
  {
    try
    {
      _setter.invoke(paramObject1, new Object[] { paramString, paramObject2 });
      return;
    }
    catch (Exception paramObject1)
    {
      _throwAsIOE((Exception)paramObject1, paramString, paramObject2);
    }
  }
  
  public String toString()
  {
    return "[any property on class " + getClassName() + "]";
  }
  
  public SettableAnyProperty withValueDeserializer(JsonDeserializer<Object> paramJsonDeserializer)
  {
    return new SettableAnyProperty(_property, _setter, _type, paramJsonDeserializer, _valueTypeDeserializer);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.SettableAnyProperty
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */