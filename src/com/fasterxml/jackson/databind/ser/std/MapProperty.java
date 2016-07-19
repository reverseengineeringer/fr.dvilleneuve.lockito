package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import java.io.IOException;

public class MapProperty
  extends PropertyWriter
{
  protected Object _key;
  protected JsonSerializer<Object> _keySerializer;
  protected TypeSerializer _typeSerializer;
  protected Object _value;
  protected JsonSerializer<Object> _valueSerializer;
  
  public MapProperty(TypeSerializer paramTypeSerializer)
  {
    _typeSerializer = paramTypeSerializer;
  }
  
  public void depositSchemaProperty(JsonObjectFormatVisitor paramJsonObjectFormatVisitor)
    throws JsonMappingException
  {}
  
  @Deprecated
  public void depositSchemaProperty(ObjectNode paramObjectNode, SerializerProvider paramSerializerProvider)
    throws JsonMappingException
  {}
  
  public PropertyName getFullName()
  {
    return new PropertyName(getName());
  }
  
  public String getName()
  {
    if ((_key instanceof String)) {
      return (String)_key;
    }
    return String.valueOf(_key);
  }
  
  public void reset(Object paramObject1, Object paramObject2, JsonSerializer<Object> paramJsonSerializer1, JsonSerializer<Object> paramJsonSerializer2)
  {
    _key = paramObject1;
    _value = paramObject2;
    _keySerializer = paramJsonSerializer1;
    _valueSerializer = paramJsonSerializer2;
  }
  
  public void serializeAsElement(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws Exception
  {
    if (_typeSerializer == null)
    {
      _valueSerializer.serialize(_value, paramJsonGenerator, paramSerializerProvider);
      return;
    }
    _valueSerializer.serializeWithType(_value, paramJsonGenerator, paramSerializerProvider, _typeSerializer);
  }
  
  public void serializeAsField(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    _keySerializer.serialize(_key, paramJsonGenerator, paramSerializerProvider);
    if (_typeSerializer == null)
    {
      _valueSerializer.serialize(_value, paramJsonGenerator, paramSerializerProvider);
      return;
    }
    _valueSerializer.serializeWithType(_value, paramJsonGenerator, paramSerializerProvider, _typeSerializer);
  }
  
  public void serializeAsOmittedField(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws Exception
  {
    if (!paramJsonGenerator.canOmitFields()) {
      paramJsonGenerator.writeOmittedField(getName());
    }
  }
  
  public void serializeAsPlaceholder(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws Exception
  {
    paramJsonGenerator.writeNull();
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.ser.std.MapProperty
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */