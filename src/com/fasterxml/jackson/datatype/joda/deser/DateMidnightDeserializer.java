package com.fasterxml.jackson.datatype.joda.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.joda.time.DateMidnight;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public class DateMidnightDeserializer
  extends JodaDeserializerBase<DateMidnight>
{
  static final DateTimeFormatter parser = ;
  private static final long serialVersionUID = 1L;
  
  public DateMidnightDeserializer()
  {
    super(DateMidnight.class);
  }
  
  public DateMidnight deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    Object localObject = null;
    if (paramJsonParser.isExpectedStartArrayToken())
    {
      paramJsonParser.nextToken();
      int i = paramJsonParser.getIntValue();
      paramJsonParser.nextToken();
      int j = paramJsonParser.getIntValue();
      paramJsonParser.nextToken();
      int k = paramJsonParser.getIntValue();
      if (paramJsonParser.nextToken() != JsonToken.END_ARRAY) {
        throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.END_ARRAY, "after DateMidnight ints");
      }
      paramJsonParser = new DateMidnight(i, j, k);
    }
    do
    {
      do
      {
        return paramJsonParser;
        switch (paramJsonParser.getCurrentToken())
        {
        default: 
          throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.START_ARRAY, "expected JSON Array, Number or String");
        case ???: 
          return new DateMidnight(paramJsonParser.getLongValue());
        }
        paramDeserializationContext = paramJsonParser.getText().trim();
        paramJsonParser = (JsonParser)localObject;
      } while (paramDeserializationContext.length() == 0);
      paramDeserializationContext = parser.parseLocalDate(paramDeserializationContext);
      paramJsonParser = (JsonParser)localObject;
    } while (paramDeserializationContext == null);
    return paramDeserializationContext.toDateMidnight();
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.datatype.joda.deser.DateMidnightDeserializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */