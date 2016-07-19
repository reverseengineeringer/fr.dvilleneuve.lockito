package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
final class StdKeyDeserializer$LongKD
  extends StdKeyDeserializer
{
  private static final long serialVersionUID = 1L;
  
  StdKeyDeserializer$LongKD()
  {
    super(Long.class);
  }
  
  public Long _parse(String paramString, DeserializationContext paramDeserializationContext)
    throws JsonMappingException
  {
    return Long.valueOf(_parseLong(paramString));
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.std.StdKeyDeserializer.LongKD
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */