package com.fasterxml.jackson.datatype.joda.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public class LocalTimeDeserializer
  extends JodaDeserializerBase<LocalTime>
{
  static final DateTimeFormatter parser = ;
  private static final long serialVersionUID = 1L;
  
  public LocalTimeDeserializer()
  {
    super(LocalTime.class);
  }
  
  public LocalTime deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    switch (paramJsonParser.getCurrentToken())
    {
    default: 
    case ???: 
      do
      {
        throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.START_ARRAY, "expected JSON Array, String or Number");
      } while (!paramJsonParser.isExpectedStartArrayToken());
      paramJsonParser.nextToken();
      int j = paramJsonParser.getIntValue();
      paramJsonParser.nextToken();
      int k = paramJsonParser.getIntValue();
      paramJsonParser.nextToken();
      int m = paramJsonParser.getIntValue();
      paramJsonParser.nextToken();
      int i = 0;
      if (paramJsonParser.getCurrentToken() != JsonToken.END_ARRAY)
      {
        i = paramJsonParser.getIntValue();
        paramJsonParser.nextToken();
      }
      if (paramJsonParser.getCurrentToken() != JsonToken.END_ARRAY) {
        throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.END_ARRAY, "after LocalTime ints");
      }
      return new LocalTime(j, k, m, i);
    case ???: 
      return new LocalTime(paramJsonParser.getLongValue());
    }
    paramJsonParser = paramJsonParser.getText().trim();
    if (paramJsonParser.length() == 0) {
      return null;
    }
    return parser.parseLocalTime(paramJsonParser);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.datatype.joda.deser.LocalTimeDeserializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */