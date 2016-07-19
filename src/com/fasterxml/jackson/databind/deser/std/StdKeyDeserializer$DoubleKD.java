package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
final class StdKeyDeserializer$DoubleKD
  extends StdKeyDeserializer
{
  private static final long serialVersionUID = 1L;
  
  StdKeyDeserializer$DoubleKD()
  {
    super(Double.class);
  }
  
  public Double _parse(String paramString, DeserializationContext paramDeserializationContext)
    throws JsonMappingException
  {
    return Double.valueOf(_parseDouble(paramString));
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.std.StdKeyDeserializer.DoubleKD
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */