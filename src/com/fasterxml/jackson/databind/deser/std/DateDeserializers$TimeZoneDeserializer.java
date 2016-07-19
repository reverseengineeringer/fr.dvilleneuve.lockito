package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.util.TimeZone;

public class DateDeserializers$TimeZoneDeserializer
  extends FromStringDeserializer<TimeZone>
{
  public static final TimeZoneDeserializer instance = new TimeZoneDeserializer();
  
  public DateDeserializers$TimeZoneDeserializer()
  {
    super(TimeZone.class);
  }
  
  protected TimeZone _deserialize(String paramString, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    return TimeZone.getTimeZone(paramString);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.std.DateDeserializers.TimeZoneDeserializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */