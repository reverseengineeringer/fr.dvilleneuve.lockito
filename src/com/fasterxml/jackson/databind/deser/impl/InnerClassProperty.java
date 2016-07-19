package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;

public final class InnerClassProperty
  extends SettableBeanProperty
{
  private static final long serialVersionUID = 1L;
  protected final Constructor<?> _creator;
  protected final SettableBeanProperty _delegate;
  
  public InnerClassProperty(SettableBeanProperty paramSettableBeanProperty, Constructor<?> paramConstructor)
  {
    super(paramSettableBeanProperty);
    _delegate = paramSettableBeanProperty;
    _creator = paramConstructor;
  }
  
  protected InnerClassProperty(InnerClassProperty paramInnerClassProperty, JsonDeserializer<?> paramJsonDeserializer)
  {
    super(paramInnerClassProperty, paramJsonDeserializer);
    _delegate = _delegate.withValueDeserializer(paramJsonDeserializer);
    _creator = _creator;
  }
  
  protected InnerClassProperty(InnerClassProperty paramInnerClassProperty, PropertyName paramPropertyName)
  {
    super(paramInnerClassProperty, paramPropertyName);
    _delegate = _delegate.withName(paramPropertyName);
    _creator = _creator;
  }
  
  public void deserializeAndSet(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject)
    throws IOException, JsonProcessingException
  {
    Object localObject1 = null;
    Object localObject2 = null;
    if (paramJsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
      if (_nullProvider == null) {
        paramJsonParser = (JsonParser)localObject2;
      }
    }
    for (;;)
    {
      set(paramObject, paramJsonParser);
      return;
      paramJsonParser = _nullProvider.nullValue(paramDeserializationContext);
      continue;
      if (_valueTypeDeserializer != null) {
        paramJsonParser = _valueDeserializer.deserializeWithType(paramJsonParser, paramDeserializationContext, _valueTypeDeserializer);
      }
      try
      {
        localObject2 = _creator.newInstance(new Object[] { paramObject });
        localObject1 = localObject2;
      }
      catch (Exception localException)
      {
        for (;;)
        {
          ClassUtil.unwrapAndThrowAsIAE(localException, "Failed to instantiate class " + _creator.getDeclaringClass().getName() + ", problem: " + localException.getMessage());
        }
      }
      _valueDeserializer.deserialize(paramJsonParser, paramDeserializationContext, localObject1);
      paramJsonParser = (JsonParser)localObject1;
    }
  }
  
  public Object deserializeSetAndReturn(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject)
    throws IOException, JsonProcessingException
  {
    return setAndReturn(paramObject, deserialize(paramJsonParser, paramDeserializationContext));
  }
  
  public <A extends Annotation> A getAnnotation(Class<A> paramClass)
  {
    return _delegate.getAnnotation(paramClass);
  }
  
  public AnnotatedMember getMember()
  {
    return _delegate.getMember();
  }
  
  public final void set(Object paramObject1, Object paramObject2)
    throws IOException
  {
    _delegate.set(paramObject1, paramObject2);
  }
  
  public Object setAndReturn(Object paramObject1, Object paramObject2)
    throws IOException
  {
    return _delegate.setAndReturn(paramObject1, paramObject2);
  }
  
  public InnerClassProperty withName(PropertyName paramPropertyName)
  {
    return new InnerClassProperty(this, paramPropertyName);
  }
  
  public InnerClassProperty withValueDeserializer(JsonDeserializer<?> paramJsonDeserializer)
  {
    return new InnerClassProperty(this, paramJsonDeserializer);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.impl.InnerClassProperty
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */