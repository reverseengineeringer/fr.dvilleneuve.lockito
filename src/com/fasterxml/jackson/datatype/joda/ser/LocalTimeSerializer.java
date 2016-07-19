package com.fasterxml.jackson.datatype.joda.ser;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.lang.reflect.Type;
import org.joda.time.LocalTime;
import org.joda.time.LocalTime.Property;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public final class LocalTimeSerializer
  extends JodaSerializerBase<LocalTime>
{
  static final DateTimeFormatter format = ;
  
  public LocalTimeSerializer()
  {
    super(LocalTime.class);
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
  {
    if (paramSerializerProvider.isEnabled(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)) {}
    for (paramSerializerProvider = "array";; paramSerializerProvider = "string") {
      return createSchemaNode(paramSerializerProvider, true);
    }
  }
  
  public void serialize(LocalTime paramLocalTime, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonGenerationException
  {
    if (paramSerializerProvider.isEnabled(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS))
    {
      paramJsonGenerator.writeStartArray();
      paramJsonGenerator.writeNumber(paramLocalTime.hourOfDay().get());
      paramJsonGenerator.writeNumber(paramLocalTime.minuteOfHour().get());
      paramJsonGenerator.writeNumber(paramLocalTime.secondOfMinute().get());
      paramJsonGenerator.writeNumber(paramLocalTime.millisOfSecond().get());
      paramJsonGenerator.writeEndArray();
      return;
    }
    paramJsonGenerator.writeString(format.print(paramLocalTime));
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.datatype.joda.ser.LocalTimeSerializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */