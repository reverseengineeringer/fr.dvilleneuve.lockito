package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
final class StdKeyDeserializer$BoolKD
  extends StdKeyDeserializer
{
  private static final long serialVersionUID = 1L;
  
  StdKeyDeserializer$BoolKD()
  {
    super(Boolean.class);
  }
  
  public Boolean _parse(String paramString, DeserializationContext paramDeserializationContext)
    throws JsonMappingException
  {
    if ("true".equals(paramString)) {
      return Boolean.TRUE;
    }
    if ("false".equals(paramString)) {
      return Boolean.FALSE;
    }
    throw paramDeserializationContext.weirdKeyException(_keyClass, paramString, "value not 'true' or 'false'");
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.std.StdKeyDeserializer.BoolKD
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */