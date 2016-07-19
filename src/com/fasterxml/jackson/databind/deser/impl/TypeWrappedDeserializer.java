package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;

public final class TypeWrappedDeserializer
  extends JsonDeserializer<Object>
{
  final JsonDeserializer<Object> _deserializer;
  final TypeDeserializer _typeDeserializer;
  
  public TypeWrappedDeserializer(TypeDeserializer paramTypeDeserializer, JsonDeserializer<Object> paramJsonDeserializer)
  {
    _typeDeserializer = paramTypeDeserializer;
    _deserializer = paramJsonDeserializer;
  }
  
  public Object deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    return _deserializer.deserializeWithType(paramJsonParser, paramDeserializationContext, _typeDeserializer);
  }
  
  public Object deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject)
    throws IOException, JsonProcessingException
  {
    return _deserializer.deserialize(paramJsonParser, paramDeserializationContext, paramObject);
  }
  
  public Object deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer)
    throws IOException, JsonProcessingException
  {
    throw new IllegalStateException("Type-wrapped deserializer's deserializeWithType should never get called");
  }
  
  public Class<?> handledType()
  {
    return _deserializer.handledType();
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.impl.TypeWrappedDeserializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */