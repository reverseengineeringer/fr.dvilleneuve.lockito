package com.fasterxml.jackson.datatype.joda.ser;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.lang.reflect.Type;
import org.joda.time.DateMidnight;
import org.joda.time.DateMidnight.Property;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public final class DateMidnightSerializer
  extends JodaSerializerBase<DateMidnight>
{
  static final DateTimeFormatter format = ;
  
  public DateMidnightSerializer()
  {
    super(DateMidnight.class);
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
  {
    if (paramSerializerProvider.isEnabled(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)) {}
    for (paramSerializerProvider = "array";; paramSerializerProvider = "string") {
      return createSchemaNode(paramSerializerProvider, true);
    }
  }
  
  public void serialize(DateMidnight paramDateMidnight, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonGenerationException
  {
    if (paramSerializerProvider.isEnabled(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS))
    {
      paramJsonGenerator.writeStartArray();
      paramJsonGenerator.writeNumber(paramDateMidnight.year().get());
      paramJsonGenerator.writeNumber(paramDateMidnight.monthOfYear().get());
      paramJsonGenerator.writeNumber(paramDateMidnight.dayOfMonth().get());
      paramJsonGenerator.writeEndArray();
      return;
    }
    paramJsonGenerator.writeString(format.print(paramDateMidnight));
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.datatype.joda.ser.DateMidnightSerializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */