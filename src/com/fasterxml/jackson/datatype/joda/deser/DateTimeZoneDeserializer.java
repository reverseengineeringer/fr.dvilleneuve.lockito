package com.fasterxml.jackson.datatype.joda.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.joda.time.DateTimeZone;

public class DateTimeZoneDeserializer
  extends JodaDeserializerBase<DateTimeZone>
{
  private static final long serialVersionUID = 1L;
  
  public DateTimeZoneDeserializer()
  {
    super(DateTimeZone.class);
  }
  
  public DateTimeZone deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    JsonToken localJsonToken = paramJsonParser.getCurrentToken();
    if (localJsonToken == JsonToken.VALUE_NUMBER_INT) {
      return DateTimeZone.forOffsetHours(paramJsonParser.getIntValue());
    }
    if (localJsonToken == JsonToken.VALUE_STRING) {
      return DateTimeZone.forID(paramJsonParser.getText().trim());
    }
    throw paramDeserializationContext.mappingException(DateTimeZone.class, JsonToken.VALUE_STRING);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.datatype.joda.deser.DateTimeZoneDeserializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */