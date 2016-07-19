package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
final class StdKeyDeserializer$IntKD
  extends StdKeyDeserializer
{
  private static final long serialVersionUID = 1L;
  
  StdKeyDeserializer$IntKD()
  {
    super(Integer.class);
  }
  
  public Integer _parse(String paramString, DeserializationContext paramDeserializationContext)
    throws JsonMappingException
  {
    return Integer.valueOf(_parseInt(paramString));
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.std.StdKeyDeserializer.IntKD
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */