package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

public class POJONode
  extends ValueNode
{
  protected final Object _value;
  
  public POJONode(Object paramObject)
  {
    _value = paramObject;
  }
  
  protected boolean _pojoEquals(POJONode paramPOJONode)
  {
    if (_value == null) {
      return _value == null;
    }
    return _value.equals(_value);
  }
  
  public boolean asBoolean(boolean paramBoolean)
  {
    boolean bool = paramBoolean;
    if (_value != null)
    {
      bool = paramBoolean;
      if ((_value instanceof Boolean)) {
        bool = ((Boolean)_value).booleanValue();
      }
    }
    return bool;
  }
  
  public double asDouble(double paramDouble)
  {
    if ((_value instanceof Number)) {
      paramDouble = ((Number)_value).doubleValue();
    }
    return paramDouble;
  }
  
  public int asInt(int paramInt)
  {
    if ((_value instanceof Number)) {
      paramInt = ((Number)_value).intValue();
    }
    return paramInt;
  }
  
  public long asLong(long paramLong)
  {
    if ((_value instanceof Number)) {
      paramLong = ((Number)_value).longValue();
    }
    return paramLong;
  }
  
  public String asText()
  {
    if (_value == null) {
      return "null";
    }
    return _value.toString();
  }
  
  public JsonToken asToken()
  {
    return JsonToken.VALUE_EMBEDDED_OBJECT;
  }
  
  public byte[] binaryValue()
    throws IOException
  {
    if ((_value instanceof byte[])) {
      return (byte[])_value;
    }
    return super.binaryValue();
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool1;
    if (paramObject == this) {
      bool1 = true;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramObject == null);
      bool1 = bool2;
    } while (!(paramObject instanceof POJONode));
    return _pojoEquals((POJONode)paramObject);
  }
  
  public JsonNodeType getNodeType()
  {
    return JsonNodeType.POJO;
  }
  
  public Object getPojo()
  {
    return _value;
  }
  
  public int hashCode()
  {
    return _value.hashCode();
  }
  
  public final void serialize(JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonProcessingException
  {
    if (_value == null)
    {
      paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
      return;
    }
    paramJsonGenerator.writeObject(_value);
  }
  
  public String toString()
  {
    return String.valueOf(_value);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.node.POJONode
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */