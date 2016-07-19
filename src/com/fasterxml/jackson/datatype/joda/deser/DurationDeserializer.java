package com.fasterxml.jackson.datatype.joda.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import java.io.IOException;
import org.joda.time.Duration;

public final class DurationDeserializer
  extends StdScalarDeserializer<Duration>
{
  private static final long serialVersionUID = 1L;
  
  public DurationDeserializer()
  {
    super(Duration.class);
  }
  
  public Duration deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    switch (paramJsonParser.getCurrentToken())
    {
    default: 
      throw paramDeserializationContext.mappingException("expected JSON Number or String");
    case ???: 
      return new Duration(paramJsonParser.getLongValue());
    }
    return new Duration(paramJsonParser.getText());
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.datatype.joda.deser.DurationDeserializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */