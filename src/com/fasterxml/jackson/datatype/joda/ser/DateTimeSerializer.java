package com.fasterxml.jackson.datatype.joda.ser;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.TimeZone;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public final class DateTimeSerializer
  extends JodaSerializerBase<DateTime>
{
  private final DateTimeFormatter defaultFormat = ISODateTimeFormat.dateTime().withZoneUTC();
  
  public DateTimeSerializer()
  {
    super(DateTime.class);
  }
  
  private DateTimeFormatter getDateTimeFormatter(SerializerProvider paramSerializerProvider)
  {
    DateTimeFormatter localDateTimeFormatter = defaultFormat;
    TimeZone localTimeZone = paramSerializerProvider.getTimeZone();
    paramSerializerProvider = localDateTimeFormatter;
    if (localTimeZone != null) {
      paramSerializerProvider = localDateTimeFormatter.withZone(DateTimeZone.forTimeZone(localTimeZone));
    }
    return paramSerializerProvider;
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
  {
    if (paramSerializerProvider.isEnabled(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)) {}
    for (paramSerializerProvider = "number";; paramSerializerProvider = "string") {
      return createSchemaNode(paramSerializerProvider, true);
    }
  }
  
  public void serialize(DateTime paramDateTime, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonGenerationException
  {
    if (paramSerializerProvider.isEnabled(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS))
    {
      paramJsonGenerator.writeNumber(paramDateTime.getMillis());
      return;
    }
    paramJsonGenerator.writeString(getDateTimeFormatter(paramSerializerProvider).print(paramDateTime));
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.datatype.joda.ser.DateTimeSerializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */