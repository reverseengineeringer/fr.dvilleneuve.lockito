package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
final class StdKeyDeserializer$FloatKD
  extends StdKeyDeserializer
{
  private static final long serialVersionUID = 1L;
  
  StdKeyDeserializer$FloatKD()
  {
    super(Float.class);
  }
  
  public Float _parse(String paramString, DeserializationContext paramDeserializationContext)
    throws JsonMappingException
  {
    return Float.valueOf((float)_parseDouble(paramString));
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.std.StdKeyDeserializer.FloatKD
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */