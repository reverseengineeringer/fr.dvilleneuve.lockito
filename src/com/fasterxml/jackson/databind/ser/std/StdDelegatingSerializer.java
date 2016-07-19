package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonschema.SchemaAware;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.ResolvableSerializer;
import com.fasterxml.jackson.databind.util.Converter;
import java.io.IOException;
import java.lang.reflect.Type;

public class StdDelegatingSerializer
  extends StdSerializer<Object>
  implements ContextualSerializer, ResolvableSerializer, JsonFormatVisitable, SchemaAware
{
  protected final Converter<Object, ?> _converter;
  protected final JsonSerializer<Object> _delegateSerializer;
  protected final JavaType _delegateType;
  
  public StdDelegatingSerializer(Converter<?, ?> paramConverter)
  {
    super(Object.class);
    _converter = paramConverter;
    _delegateType = null;
    _delegateSerializer = null;
  }
  
  public StdDelegatingSerializer(Converter<Object, ?> paramConverter, JavaType paramJavaType, JsonSerializer<?> paramJsonSerializer)
  {
    super(paramJavaType);
    _converter = paramConverter;
    _delegateType = paramJavaType;
    _delegateSerializer = paramJsonSerializer;
  }
  
  public <T> StdDelegatingSerializer(Class<T> paramClass, Converter<T, ?> paramConverter)
  {
    super(paramClass, false);
    _converter = paramConverter;
    _delegateType = null;
    _delegateSerializer = null;
  }
  
  public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType)
    throws JsonMappingException
  {
    _delegateSerializer.acceptJsonFormatVisitor(paramJsonFormatVisitorWrapper, paramJavaType);
  }
  
  protected Object convertValue(Object paramObject)
  {
    return _converter.convert(paramObject);
  }
  
  public JsonSerializer<?> createContextual(SerializerProvider paramSerializerProvider, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    if (_delegateSerializer != null)
    {
      if ((_delegateSerializer instanceof ContextualSerializer))
      {
        paramSerializerProvider = paramSerializerProvider.handleSecondaryContextualization(_delegateSerializer, paramBeanProperty);
        if (paramSerializerProvider != _delegateSerializer) {}
      }
      else
      {
        return this;
      }
      return withDelegate(_converter, _delegateType, paramSerializerProvider);
    }
    JavaType localJavaType2 = _delegateType;
    JavaType localJavaType1 = localJavaType2;
    if (localJavaType2 == null) {
      localJavaType1 = _converter.getOutputType(paramSerializerProvider.getTypeFactory());
    }
    return withDelegate(_converter, localJavaType1, paramSerializerProvider.findValueSerializer(localJavaType1, paramBeanProperty));
  }
  
  protected Converter<Object, ?> getConverter()
  {
    return _converter;
  }
  
  public JsonSerializer<?> getDelegatee()
  {
    return _delegateSerializer;
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
    throws JsonMappingException
  {
    if ((_delegateSerializer instanceof SchemaAware)) {
      return ((SchemaAware)_delegateSerializer).getSchema(paramSerializerProvider, paramType);
    }
    return super.getSchema(paramSerializerProvider, paramType);
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType, boolean paramBoolean)
    throws JsonMappingException
  {
    if ((_delegateSerializer instanceof SchemaAware)) {
      return ((SchemaAware)_delegateSerializer).getSchema(paramSerializerProvider, paramType, paramBoolean);
    }
    return super.getSchema(paramSerializerProvider, paramType);
  }
  
  public boolean isEmpty(Object paramObject)
  {
    paramObject = convertValue(paramObject);
    return _delegateSerializer.isEmpty(paramObject);
  }
  
  public void resolve(SerializerProvider paramSerializerProvider)
    throws JsonMappingException
  {
    if ((_delegateSerializer != null) && ((_delegateSerializer instanceof ResolvableSerializer))) {
      ((ResolvableSerializer)_delegateSerializer).resolve(paramSerializerProvider);
    }
  }
  
  public void serialize(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonProcessingException
  {
    paramObject = convertValue(paramObject);
    if (paramObject == null)
    {
      paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
      return;
    }
    _delegateSerializer.serialize(paramObject, paramJsonGenerator, paramSerializerProvider);
  }
  
  public void serializeWithType(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer)
    throws IOException, JsonProcessingException
  {
    paramObject = convertValue(paramObject);
    _delegateSerializer.serializeWithType(paramObject, paramJsonGenerator, paramSerializerProvider, paramTypeSerializer);
  }
  
  protected StdDelegatingSerializer withDelegate(Converter<Object, ?> paramConverter, JavaType paramJavaType, JsonSerializer<?> paramJsonSerializer)
  {
    if (getClass() != StdDelegatingSerializer.class) {
      throw new IllegalStateException("Sub-class " + getClass().getName() + " must override 'withDelegate'");
    }
    return new StdDelegatingSerializer(paramConverter, paramJavaType, paramJsonSerializer);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.ser.std.StdDelegatingSerializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */