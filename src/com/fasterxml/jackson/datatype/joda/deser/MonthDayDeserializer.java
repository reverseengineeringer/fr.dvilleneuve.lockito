package com.fasterxml.jackson.datatype.joda.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.joda.time.MonthDay;

public class MonthDayDeserializer
  extends JodaDeserializerBase<MonthDay>
{
  private static final long serialVersionUID = -2360834248497553111L;
  
  public MonthDayDeserializer()
  {
    super(MonthDay.class);
  }
  
  public MonthDay deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    if (paramJsonParser.getCurrentToken() == JsonToken.VALUE_STRING)
    {
      paramJsonParser = paramJsonParser.getText().trim();
      if (paramJsonParser.isEmpty()) {
        return null;
      }
      return MonthDay.parse(paramJsonParser);
    }
    throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.VALUE_STRING, "expected JSON String");
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.datatype.joda.deser.MonthDayDeserializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */