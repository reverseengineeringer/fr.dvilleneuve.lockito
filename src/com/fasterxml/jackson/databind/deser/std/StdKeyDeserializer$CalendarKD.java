package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.util.Calendar;

@JacksonStdImpl
final class StdKeyDeserializer$CalendarKD
  extends StdKeyDeserializer
{
  private static final long serialVersionUID = 1L;
  
  protected StdKeyDeserializer$CalendarKD()
  {
    super(Calendar.class);
  }
  
  public Object _parse(String paramString, DeserializationContext paramDeserializationContext)
    throws IllegalArgumentException, JsonMappingException
  {
    paramString = paramDeserializationContext.parseDate(paramString);
    if (paramString == null) {
      return null;
    }
    return paramDeserializationContext.constructCalendar(paramString);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.std.StdKeyDeserializer.CalendarKD
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */