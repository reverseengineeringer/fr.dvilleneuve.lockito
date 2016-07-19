package com.fasterxml.jackson.datatype.joda.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public class LocalDateDeserializer
  extends JodaDeserializerBase<LocalDate>
{
  static final DateTimeFormatter parser = ;
  private static final long serialVersionUID = 1L;
  
  public LocalDateDeserializer()
  {
    super(LocalDate.class);
  }
  
  public LocalDate deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    if (paramJsonParser.isExpectedStartArrayToken())
    {
      paramJsonParser.nextToken();
      int i = paramJsonParser.getIntValue();
      paramJsonParser.nextToken();
      int j = paramJsonParser.getIntValue();
      paramJsonParser.nextToken();
      int k = paramJsonParser.getIntValue();
      if (paramJsonParser.nextToken() != JsonToken.END_ARRAY) {
        throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.END_ARRAY, "after LocalDate ints");
      }
      return new LocalDate(i, j, k);
    }
    switch (paramJsonParser.getCurrentToken())
    {
    default: 
      throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.START_ARRAY, "expected JSON Array, String or Number");
    case ???: 
      return new LocalDate(paramJsonParser.getLongValue());
    }
    paramJsonParser = paramJsonParser.getText().trim();
    if (paramJsonParser.length() == 0) {
      return null;
    }
    return parser.parseLocalDate(paramJsonParser);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.datatype.joda.deser.LocalDateDeserializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */