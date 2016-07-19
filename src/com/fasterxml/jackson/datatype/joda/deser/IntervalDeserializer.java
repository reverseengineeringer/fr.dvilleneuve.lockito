package com.fasterxml.jackson.datatype.joda.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.joda.time.Interval;

public class IntervalDeserializer
  extends JodaDeserializerBase<Interval>
{
  private static final long serialVersionUID = 5196071166239332742L;
  
  public IntervalDeserializer()
  {
    super(Interval.class);
  }
  
  public Interval deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    if (paramJsonParser.getCurrentToken() == JsonToken.VALUE_STRING)
    {
      paramJsonParser = paramJsonParser.getText();
      int i = paramJsonParser.indexOf('-');
      return new Interval(Long.valueOf(paramJsonParser.substring(0, i)).longValue(), Long.valueOf(paramJsonParser.substring(i + 1)).longValue());
    }
    throw paramDeserializationContext.mappingException("expected JSON String");
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.datatype.joda.deser.IntervalDeserializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */