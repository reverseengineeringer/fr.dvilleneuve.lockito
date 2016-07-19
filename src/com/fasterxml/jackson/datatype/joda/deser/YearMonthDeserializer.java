package com.fasterxml.jackson.datatype.joda.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.joda.time.YearMonth;

public class YearMonthDeserializer
  extends JodaDeserializerBase<YearMonth>
{
  private static final long serialVersionUID = -3830851040664795250L;
  
  public YearMonthDeserializer()
  {
    super(YearMonth.class);
  }
  
  public YearMonth deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    if (paramJsonParser.getCurrentToken() == JsonToken.VALUE_STRING)
    {
      paramJsonParser = paramJsonParser.getText().trim();
      if (paramJsonParser.isEmpty()) {
        return null;
      }
      return YearMonth.parse(paramJsonParser);
    }
    throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.VALUE_STRING, "expected JSON String");
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.datatype.joda.deser.YearMonthDeserializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */