package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
final class StdKeyDeserializer$CharKD
  extends StdKeyDeserializer
{
  private static final long serialVersionUID = 1L;
  
  StdKeyDeserializer$CharKD()
  {
    super(Character.class);
  }
  
  public Character _parse(String paramString, DeserializationContext paramDeserializationContext)
    throws JsonMappingException
  {
    if (paramString.length() == 1) {
      return Character.valueOf(paramString.charAt(0));
    }
    throw paramDeserializationContext.weirdKeyException(_keyClass, paramString, "can only convert 1-character Strings");
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.std.StdKeyDeserializer.CharKD
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */