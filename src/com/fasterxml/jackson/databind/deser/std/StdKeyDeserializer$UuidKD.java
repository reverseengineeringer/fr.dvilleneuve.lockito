package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.util.UUID;

@JacksonStdImpl
final class StdKeyDeserializer$UuidKD
  extends StdKeyDeserializer
{
  private static final long serialVersionUID = 1L;
  
  protected StdKeyDeserializer$UuidKD()
  {
    super(UUID.class);
  }
  
  public Object _parse(String paramString, DeserializationContext paramDeserializationContext)
    throws IllegalArgumentException, JsonMappingException
  {
    return UUID.fromString(paramString);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.std.StdKeyDeserializer.UuidKD
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */