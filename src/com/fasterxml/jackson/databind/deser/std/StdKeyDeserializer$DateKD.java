package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.util.Date;

@JacksonStdImpl
final class StdKeyDeserializer$DateKD
  extends StdKeyDeserializer
{
  private static final long serialVersionUID = 1L;
  
  protected StdKeyDeserializer$DateKD()
  {
    super(Date.class);
  }
  
  public Object _parse(String paramString, DeserializationContext paramDeserializationContext)
    throws IllegalArgumentException, JsonMappingException
  {
    return paramDeserializationContext.parseDate(paramString);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.std.StdKeyDeserializer.DateKD
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */