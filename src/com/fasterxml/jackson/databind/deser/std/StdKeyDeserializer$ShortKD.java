package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
final class StdKeyDeserializer$ShortKD
  extends StdKeyDeserializer
{
  private static final long serialVersionUID = 1L;
  
  StdKeyDeserializer$ShortKD()
  {
    super(Integer.class);
  }
  
  public Short _parse(String paramString, DeserializationContext paramDeserializationContext)
    throws JsonMappingException
  {
    int i = _parseInt(paramString);
    if ((i < 32768) || (i > 32767)) {
      throw paramDeserializationContext.weirdKeyException(_keyClass, paramString, "overflow, value can not be represented as 16-bit value");
    }
    return Short.valueOf((short)i);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.std.StdKeyDeserializer.ShortKD
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */