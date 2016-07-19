package com.fasterxml.jackson.datatype.joda.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.joda.time.Period;

public class PeriodDeserializer
  extends JodaDeserializerBase<Period>
{
  private static final long serialVersionUID = 1L;
  
  public PeriodDeserializer()
  {
    super(Period.class);
  }
  
  public Period deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    switch (paramJsonParser.getCurrentToken())
    {
    default: 
      throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.START_ARRAY, "expected JSON Number or String");
    case ???: 
      return new Period(paramJsonParser.getLongValue());
    }
    return new Period(paramJsonParser.getText());
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.datatype.joda.deser.PeriodDeserializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */