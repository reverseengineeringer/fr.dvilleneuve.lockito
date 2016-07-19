package com.fasterxml.jackson.datatype.joda.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.joda.time.DateTimeZone;

public class DateTimeZoneSerializer
  extends JodaSerializerBase<DateTimeZone>
{
  public DateTimeZoneSerializer()
  {
    super(DateTimeZone.class);
  }
  
  public void serialize(DateTimeZone paramDateTimeZone, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonProcessingException
  {
    paramJsonGenerator.writeString(paramDateTimeZone.getID());
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.datatype.joda.ser.DateTimeZoneSerializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */