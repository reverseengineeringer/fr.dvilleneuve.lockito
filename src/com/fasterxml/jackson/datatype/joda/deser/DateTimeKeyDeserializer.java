package com.fasterxml.jackson.datatype.joda.deser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import java.io.IOException;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

public class DateTimeKeyDeserializer
  extends KeyDeserializer
{
  public Object deserializeKey(String paramString, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    if (paramString.length() == 0) {
      return null;
    }
    return new DateTime(paramString, DateTimeZone.forTimeZone(paramDeserializationContext.getTimeZone()));
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.datatype.joda.deser.DateTimeKeyDeserializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */