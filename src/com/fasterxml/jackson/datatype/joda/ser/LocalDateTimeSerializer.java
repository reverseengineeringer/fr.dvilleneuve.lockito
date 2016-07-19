package com.fasterxml.jackson.datatype.joda.ser;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.lang.reflect.Type;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalDateTime.Property;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public final class LocalDateTimeSerializer
  extends JodaSerializerBase<LocalDateTime>
{
  static final DateTimeFormatter format = ;
  
  public LocalDateTimeSerializer()
  {
    super(LocalDateTime.class);
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
  {
    if (paramSerializerProvider.isEnabled(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)) {}
    for (paramSerializerProvider = "array";; paramSerializerProvider = "string") {
      return createSchemaNode(paramSerializerProvider, true);
    }
  }
  
  public void serialize(LocalDateTime paramLocalDateTime, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonGenerationException
  {
    if (paramSerializerProvider.isEnabled(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS))
    {
      paramJsonGenerator.writeStartArray();
      paramJsonGenerator.writeNumber(paramLocalDateTime.year().get());
      paramJsonGenerator.writeNumber(paramLocalDateTime.monthOfYear().get());
      paramJsonGenerator.writeNumber(paramLocalDateTime.dayOfMonth().get());
      paramJsonGenerator.writeNumber(paramLocalDateTime.hourOfDay().get());
      paramJsonGenerator.writeNumber(paramLocalDateTime.minuteOfHour().get());
      paramJsonGenerator.writeNumber(paramLocalDateTime.secondOfMinute().get());
      paramJsonGenerator.writeNumber(paramLocalDateTime.millisOfSecond().get());
      paramJsonGenerator.writeEndArray();
      return;
    }
    paramJsonGenerator.writeString(format.print(paramLocalDateTime));
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.datatype.joda.ser.LocalDateTimeSerializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */