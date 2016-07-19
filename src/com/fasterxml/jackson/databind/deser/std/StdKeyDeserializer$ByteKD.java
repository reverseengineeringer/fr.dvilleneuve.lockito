package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
final class StdKeyDeserializer$ByteKD
  extends StdKeyDeserializer
{
  private static final long serialVersionUID = 1L;
  
  StdKeyDeserializer$ByteKD()
  {
    super(Byte.class);
  }
  
  public Byte _parse(String paramString, DeserializationContext paramDeserializationContext)
    throws JsonMappingException
  {
    int i = _parseInt(paramString);
    if ((i < -128) || (i > 255)) {
      throw paramDeserializationContext.weirdKeyException(_keyClass, paramString, "overflow, value can not be represented as 8-bit value");
    }
    return Byte.valueOf((byte)i);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.std.StdKeyDeserializer.ByteKD
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */